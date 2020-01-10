package minesweeper.view

import minesweeper.model.{Board, Flags}

import scala.swing.BorderPanel
import scala.swing.BorderPanel.Position._

class MinesweeperView(board: Board, flags: Flags) extends BorderPanel {
  private val boardView = new BoardView(board, flags)
  private val gameInfoView = new GameInfoView(board, flags)

  layout(gameInfoView) = North
  layout(boardView) = Center
}
