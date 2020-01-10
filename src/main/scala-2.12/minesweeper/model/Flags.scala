package minesweeper.model

class Flags {
  private var positions: Set[(Int, Int)] = Set.empty

  def count: Int = positions.size

  def apply(cell: (Int, Int)): Boolean =
    positions.contains(cell)

  def update(cell: (Int, Int), newState: Boolean): Boolean = {
    if (newState)
      putFlag(cell)
    else
      removeFlag(cell)

    newState
  }

  def putFlag(cell: (Int, Int)): Unit =
    positions = positions + cell

  def removeFlag(cell: (Int, Int)): Unit =
    positions = positions - cell

  def toggleFlag(cell: (Int, Int)): Unit =
    this(cell) = !this(cell)

  override def toString: String = "Flags(" ++ positions.toString ++ ")"
}
