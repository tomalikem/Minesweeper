package minesweeper.view.util

import java.awt.Dimension

import scala.math._

case class Bounds private(width: Int, height: Int) {
  if(width < 0 || height < 0)
    throw new IllegalArgumentException("The width and height of GUI element can't be negative.")

  def +(other: Bounds): Bounds =
    Bounds(width + other.width, height + other.height)

  def *(factor: Double): Bounds =
    Bounds(width * factor, height * factor)

  def aspectRatio: Double =
    width.toDouble / height.toDouble

  def resizeToAspectRatio(relativeSize: Bounds): Bounds =
    resizeToAspectRatio(relativeSize.aspectRatio)

  def resizeToAspectRatio(newRatio: Double): Bounds = {
    if(aspectRatio >= newRatio)
      Bounds(width, width / newRatio)
    else
      Bounds(height * newRatio, width)
  }

  def toAWTDimension: Dimension =
    new Dimension(width, height)
}

object Bounds {
  def apply(width: Int, height: Int): Bounds =
    new Bounds(width, height)

  def apply(width: Double, height: Double): Bounds =
    Bounds(width.ceil.toInt, height.ceil.toInt)
  
  def max(a: Bounds, b: Bounds) =
    Bounds(math.max(a.width, b.width), math.max(a.height, b.height))

  def min(a: Bounds, b: Bounds) =
    Bounds(math.min(a.width, b.width), math.min(a.height, b.height))
}