package minesweeper.view.configuration


import java.util.NoSuchElementException

import minesweeper.model.Configuration
import minesweeper.resources.{Icons, Strings}
import minesweeper.view.MinesweeperFrame

import scala.swing.{Action, BorderPanel, Button, Dialog, Dimension, Frame}

class ConfigurationDialog(previousConfiguration: Option[Configuration]) extends Frame {
  private val configurationPanel = new ConfigurationPanel(previousConfiguration)
  private val okButton = new Button()

  title = Strings.title
  iconImage = Icons.app.getImage

  contents = new BorderPanel {
    layout(configurationPanel) = BorderPanel.Position.Center
    layout(okButton) = BorderPanel.Position.South
  }
  size = new Dimension(800, 600)

  def configuration: Option[Configuration] =
    configurationPanel.configuration

  def configuration_=(configuration: Option[Configuration]): Unit =
    configurationPanel.configuration = configuration

  def configuration_=(configuration: Configuration): Unit =
    configurationPanel.configuration = configuration

  okButton.action = new Action(Strings.ok) {
    override def apply(): Unit =
      try {
        startGame(configuration.get)
      }
      catch {
        case e: IllegalArgumentException =>
          Dialog.showMessage(ConfigurationDialog.this, e.getMessage, Strings.errorDialogTitle)
        case e: NoSuchElementException =>
          Dialog.showMessage(ConfigurationDialog.this, Strings.noInitialConfigurationError, Strings.errorDialogTitle)
      }
  }

  override def closeOperation(): Unit = System.exit(0)

  private def startGame(startConfiguration: Configuration): Unit = {
    visible = false
    dispose()

    MinesweeperFrame.show(startConfiguration)
  }
}

object ConfigurationDialog {
  def show(): Unit =
    show(None)

  def show(initialConfiguration: Configuration): Unit =
    show(Some(initialConfiguration))

  def show(initialConfiguration: Option[Configuration]): Unit = {
    val frame = new ConfigurationDialog(initialConfiguration)
    frame.visible = true
  }
}