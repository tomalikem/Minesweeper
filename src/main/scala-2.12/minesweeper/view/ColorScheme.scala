package minesweeper.view

import java.awt.Color


class ColorScheme(hiddenBg: Color, revealedBg: Color, neighborColors: Color*) {
  if(neighborColors == null || neighborColors.length != 8)
    throw new IllegalArgumentException("Color scheme for Minesweeper must consist of exactly 8 colors.")

  def colorFor(neighborCount: Int): Color = {
    if(neighborCount == 0)
      new Color(0, true)
    else
      neighborColors(neighborCount - 1)
  }

  def background(revealed: Boolean): Color = {
    if(revealed) revealedBg else hiddenBg
  }
}

object ColorScheme {
  val default: ColorScheme = new ColorScheme(
    new Color(192, 192, 192),
    new Color(220, 220, 220),

    Color.RED,
    Color.GREEN,
    Color.BLUE,
    Color.CYAN,
    Color.YELLOW,
    Color.MAGENTA,
    Color.RED,
    Color.GREEN
  )
}