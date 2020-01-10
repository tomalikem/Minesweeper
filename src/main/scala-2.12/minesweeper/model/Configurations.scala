package minesweeper.model

import minesweeper.resources.Strings

object Configurations {
  val predefined: List[Configuration] = List(
    new Configuration(Strings.beginnerConfiguration, (9, 9), 10),
    new Configuration(Strings.intermediateConfiguration, (16, 16), 40),
    new Configuration(Strings.expertConfiguration, (30, 16), 99)
  )
}
