package minesweeper.view.configuration

import javax.swing.Box

import minesweeper.model.Configuration
import minesweeper.resources.{Fonts, Strings}

import scala.swing.{Component, GridPanel, Label}

class CustomConfigurationBox(initialConfiguration: Option[Configuration]) extends GridPanel(2, 5) {
  def this() { this(None) }
  def this(initialConfiguration: Configuration) { this(Some(initialConfiguration)) }

  private val widthField = new IntegerField()
  private val heightField = new IntegerField()
  private val bombCountField = new IntegerField()

  contents ++= List[Component](
    box(), label(Strings.boardWidth), label(Strings.boardHeight), label(Strings.bombCount), box(),
    box(), widthField, heightField, bombCountField, box()
  )

  def configuration: Option[Configuration] = {
    val width = widthField.value
    val height = heightField.value
    val bombCount = bombCountField.value

    if(width.isDefined && height.isDefined && bombCount.isDefined)
      Some(new Configuration(Strings.nonstandardConfiguration, (width.get, height.get), bombCount.get))
    else
      None
  }

  def configuration_=(configuration: Option[Configuration]): Unit = {
    if(configuration.isDefined)
      configuration_=(configuration.get)
    else
      clear()
  }

  def configuration_=(configuration: Configuration): Unit = {
    widthField.value = configuration.boardWidth
    heightField.value = configuration.boardHeight
    bombCountField.value = configuration.bombCount
  }
  
  def clear(): Unit = {
    widthField.value = None
    heightField.value = None
    bombCountField.value = None
  }

  configuration = initialConfiguration

  private def box() = Component.wrap(Box.createHorizontalBox())
  private def label(text: String) = {
    val label = new Label(text)
    //label.font = Fonts.configurationFont
    label
  }
}
