package minesweeper

import minesweeper.view.configuration.ConfigurationDialog

object Main {
  def main(args: Array[String]): Unit = {
    ConfigurationDialog.show()
  }
}
