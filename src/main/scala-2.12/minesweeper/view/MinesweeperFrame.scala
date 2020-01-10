package minesweeper.view

import minesweeper.model.{Board, Configuration, Flags}
import minesweeper.resources.Icons
import minesweeper.view.configuration.ConfigurationDialog
import minesweeper.view.util.Bounds

import scala.swing._

class MinesweeperFrame(configuration: Configuration, cellSize: Int) extends Frame {
  val board = new Board(configuration.boardWidth, configuration.boardHeight, configuration.bombCount)
  val flags = new Flags

  title = configuration.description
  contents = new MinesweeperView(board, flags)
  size = calculateFrameSize()
  iconImage = Icons.app.getImage

  override def closeOperation(): Unit =
    ConfigurationDialog.show(configuration)

  private def calculateFrameSize(): Dimension = {
    val sizem1 = Bounds(board.width, board.height)
    val size0 = sizem1 * cellSize
    val size1 = Bounds.max(size0, MinesweeperFrame.minFrameSize)
    val size2 = size1.resizeToAspectRatio(sizem1)
    val size3 = size2 + MinesweeperFrame.headerMargin
    size3.toAWTDimension
  }
}

object MinesweeperFrame {
  val minFrameSize = Bounds(520, 0)
  val headerMargin = Bounds(0, 64)
  val defaultCellSize = 64

  def show(configuration: Configuration, cellSize: Int = defaultCellSize): Unit = {
    val frame = new MinesweeperFrame(configuration, cellSize)
    frame.visible = true
  }
}

