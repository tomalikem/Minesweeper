package minesweeper.model

import minesweeper.resources.Strings

class Configuration (val name: String, val boardSize: (Int, Int), val bombCount: Int) {
  if(boardSize._1 < 0 || boardSize._2 < 0)
    throw new IllegalArgumentException(Strings.negativeBoardSizeError)
  if(bombCount < 0)
    throw new IllegalArgumentException(Strings.negativeBombCountError)
  if(bombCount >= boardArea)
    throw new IllegalArgumentException(Strings.bombCountOverflowError)

  def boardWidth: Int = boardSize._1
  def boardHeight: Int = boardSize._2
  def boardArea: Int = boardWidth * boardHeight

  def description: String = s"$name (${boardWidth}x$boardHeight)"

  override def toString: String = s"Configuration($name, $boardSize, $bombCount)"
}
