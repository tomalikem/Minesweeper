package minesweeper.view

import javax.swing.Icon

import minesweeper.model.{Board, Flags}
import minesweeper.resources.{Fonts, Icons}

import scala.swing.{Alignment, GridPanel, Label}

class GameInfoView(board: Board, flags: Flags) extends GridPanel(1, 3) {
  private val bombDisplay = createLabel(Icons.bomb, board.bombs.toString)
  private val flagDisplay = createLabel(Icons.flag, "0")
  private val clockDisplay = createLabel(Icons.clock, "0:00")

  contents += bombDisplay
  contents += flagDisplay
  contents += clockDisplay

  val clockThread = new Thread
  {
    override def run(): Unit = clock()
  }
  clockThread.start()



  private def createLabel(icon: Icon, startValue: String): Label = {
    val label = new Label(startValue, icon, Alignment.Left)
    label.font = Fonts.gameInfoFont
    label.iconTextGap = 20
    label
  }

  private def clock(): Unit =
  {
    var startTime = System.currentTimeMillis
    var timeDiff: Long = 0
    while(board.isOngoing)
    {
      print("")
      if(board.alreadyStarted())
      {
        val currentTime = System.currentTimeMillis
        if(currentTime > startTime + timeDiff + 1000)
        {
          timeDiff = currentTime - startTime
          val inSeconds = timeDiff / 1000
          val seconds = inSeconds % 60
          val minutes = inSeconds / 60
          val timeAsText =
            if(seconds > 9) minutes.toString + ":" + seconds.toString
            else minutes.toString + ":0" + seconds.toString
          clockDisplay.text = timeAsText
        }
        if(board.updateFlags)
        {
          board.updateFlags = false
          flagDisplay.text = flags.count.toString
        }
      }
      else
      {
        startTime = System.currentTimeMillis
      }

    }
  }
}
