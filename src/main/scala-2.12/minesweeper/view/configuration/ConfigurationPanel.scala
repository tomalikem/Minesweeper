package minesweeper.view.configuration

import minesweeper.model.{Configuration, Configurations}
import minesweeper.resources.Strings

import scala.swing.{ButtonGroup, GridPanel, Label, RadioButton}

class ConfigurationPanel(initialConfiguration: Option[Configuration]) extends GridPanel(0, 1) {
  def this() { this(None) }
  def this(initialConfiguration: Configuration) { this(Some(initialConfiguration)) }

  private val predefinedRadioButtons = Configurations.predefined.map(c => new RadioButton(c.description))
  private val customRadioButton = new RadioButton(Strings.nonstandardConfiguration)
  private val customConfigurationBox = new CustomConfigurationBox()
  private val buttonGroup = new ButtonGroup(predefinedRadioButtons:_*)

  buttonGroup.buttons += customRadioButton

  contents += new Label(Strings.configurationPrompt)
  contents ++= predefinedRadioButtons
  contents += customRadioButton
  contents += customConfigurationBox

  def configuration: Option[Configuration] = {
    buttonGroup.selected match {
      case None => None
      case Some(`customRadioButton`) => customConfigurationBox.configuration
      case Some(radioButton) =>
        val position = predefinedRadioButtons.indexOf(radioButton)
        Some(Configurations.predefined(position))
    }
  }

  def configuration_=(configuration: Option[Configuration]): Unit = {
    configuration match {
      case None =>
        buttonGroup.peer.clearSelection()
        customConfigurationBox.configuration = None
      case Some(someConfiguration) =>
        configuration_=(someConfiguration)
    }
  }

  def configuration_=(configuration: Configuration): Unit = {
    Configurations.predefined.indexOf(configuration) match {
      case (-1) =>
        customConfigurationBox.configuration = configuration
        buttonGroup.select(customRadioButton)
      case index =>
        buttonGroup.select(predefinedRadioButtons(index))
    }
  }

  configuration = initialConfiguration
}
