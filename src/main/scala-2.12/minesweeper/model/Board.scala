package minesweeper.model

class Board(val width: Int, val height: Int, val bombs: Int)
{
  private val board = Array.ofDim[Int](width, height)
  private val visible = Array.ofDim[Boolean](width, height)
  private var fieldsUnrevealed = width * height
  private var ongoing: Boolean = true
  var updateFlags: Boolean = false
  private var gameStarted: Boolean = false
  def isOngoing: Boolean = ongoing
  def alreadyStarted(): Boolean = gameStarted
  def endGame(): Unit = ongoing = false


  for(i <- 0 until width ; j <- 0 until height)
  {
    board(i)(j) = -1
    visible(i)(j) = false
  }


  def create(initialX: Int, initialY: Int): Unit =
  {
    gameStarted = true
    for(i <- 0 until width ; j <- 0 until height)
    {
      board(i)(j) = 0
    }

    var bombsPut = 0
    val list: List[Int] = List.range(0, height * width)
    val shuffled = scala.util.Random.shuffle(list).slice(0, bombs)
    for(position <- shuffled)
    {
      if(bombsPut < bombs)
      {
        val x = position % width
        val y = position / width
        if(x != initialX && y != initialY)
        {
          if(addBomb(x,y)) bombsPut += 1
        }
      }
    }
  }

  def gameIsWon(): Boolean =
  {
    if(fieldsUnrevealed > bombs) false
    else true
  }

  def isBomb(x: Int, y: Int): Boolean =
  {
    board(x)(y) > 8
  }

  def isRevealed(x: Int, y: Int): Boolean =
  {
    visible(x)(y)
  }

  def neighbourCount(x: Int, y: Int): Int =
  {
    if(board(x)(y) < 9 ) board(x)(y)
    else -1
  }

  def fieldsToReveal: Int ={
    fieldsUnrevealed
  }

  def reveal(x: Int, y: Int): Int =
  {
    var fieldsRevealed = 0

    if(board(x)(y) == -1) create(x, y)

    if(!isRevealed(x, y)) {
      fieldsRevealed = 1
      fieldsUnrevealed -= 1
      visible(x)(y) = true

      if(board(x)(y) == 0)
        for(i <- x-1 to x+1; j <- y-1 to y+1)
        {
          if(fieldInsideBoard(i, j) && !isRevealed(i,j)) fieldsRevealed += reveal(i,j)
        }
    }

    fieldsRevealed
  }

  private def addBomb(x: Int, y: Int): Boolean =
  {
    if(board(x)(y) > 8) false
    else
    {
      board(x)(y) = 9
      for(i <- x-1 to x+1; j <- y-1 to y+1)
      {
        if(fieldInsideBoard(i, j)) increaseField(i, j)
      }
      true
    }
  }

  private def increaseField(x: Int, y: Int): Unit =
  {
    if(fieldInsideBoard(x, y)) board(x)(y) += 1
  }

  private def fieldInsideBoard(x: Int, y: Int): Boolean =
  {
    x >= 0 && y >= 0 && x < width && y < height
  }

}
