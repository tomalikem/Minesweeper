package minesweeper.view

import java.awt.Color
import java.awt.event.MouseEvent

import minesweeper.model.{Board, Flags}
import minesweeper.resources.{Fonts, Icons}

import scala.swing.{Button, Dialog}
import scala.swing.event.MouseClicked

class CellView(private val boardView: BoardView, val cell: (Int, Int)) extends Button {
  private val board: Board = boardView.board
  private val flags: Flags = boardView.flags

  font = Fonts.neighborCountFont

  def repaintCell(): Unit = {
    if(board.isRevealed(cell._1, cell._2) && board.isBomb(cell._1, cell._2)) {
      text = ""
      icon = Icons.bomb
      background = Color.RED
    }
    else if(board.isRevealed(cell._1, cell._2)) {
      val neighborCount = board.neighbourCount(cell._1, cell._2)

      icon = null
      text = if(neighborCount > 0) neighborCount.toString else ""
      foreground = ColorScheme.default.colorFor(neighborCount)
      background = ColorScheme.default.background(revealed=true)
    }
    else {
      text = ""
      icon = if(flags(cell)) Icons.flag else null
      background = ColorScheme.default.background(revealed=false)
    }
    revalidate()
    repaint()
  }

  private def reveal(): Unit = {
    val countRevealed = board.reveal(cell._1, cell._2)
    if(countRevealed == 1)
      repaintCell()
    else if(countRevealed > 1)
      boardView.repaintAll()

    if(board.isBomb(cell._1, cell._2))
      boardView.onFailure()
    else if(board.gameIsWon())
      boardView.onVictory()
  }

  private def putFlag(): Unit = {
    if(!board.isRevealed(cell._1, cell._2)) {
      flags.toggleFlag(cell)
      repaintCell()
    }
  }

  listenTo(mouse.clicks)
  reactions += {
    case mouseEvent @ MouseClicked(source, point, _, _, _) =>
      if(boardView.isOngoing) {
        mouseEvent.peer.getButton match {
          case MouseEvent.BUTTON1 => reveal()
          case MouseEvent.BUTTON3 => putFlag()
        }
        board.updateFlags = true
      }
  }
}
