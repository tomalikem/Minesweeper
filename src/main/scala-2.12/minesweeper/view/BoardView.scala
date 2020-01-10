package minesweeper.view

import minesweeper.model.{Board, Flags}
import minesweeper.resources.Strings

import scala.swing.{Dialog, GridPanel}

class BoardView(val board: Board, val flags: Flags) extends GridPanel(board.height, board.width) {
  def isOngoing: Boolean = board.isOngoing

  private val cellViews = Array.ofDim[CellView](board.width, board.height)
  for(y <- 0 until board.height; x <- 0 until board.width) {
    cellViews(x)(y) = new CellView(this, (x, y))
    cellViews(x)(y).repaintCell()
    contents += cellViews(x)(y)
  }

  def repaintCell(flagSet: Set[(Int, Int)], cell: (Int, Int)): Unit = {
    val cellView = cellViews(cell._1)(cell._2)
    cellView.repaintCell()
  }

  def repaintAll(): Unit =
    for(x <- 0 until board.width; y <- 0 until board.height)
      cellViews(x)(y).repaintCell()

  def onVictory(): Unit = {
    board.endGame()
    Dialog.showMessage(this, Strings.victoryTitle, Strings.victoryText)
  }

  def onFailure(): Unit = {
    board.endGame()
    Dialog.showMessage(this, Strings.failureText, Strings.failureText)
  }
}
