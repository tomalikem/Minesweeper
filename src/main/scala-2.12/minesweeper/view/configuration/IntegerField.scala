package minesweeper.view.configuration

import scala.util.Try
import scala.swing.TextField

class IntegerField(initialValue: Option[Int]) extends TextField {
  def this() { this(None) }
  def this(initialValue: Int) { this(Some(initialValue)) }

  def value: Option[Int] = Try(text.toInt).toOption
  def value_=(value: Int): Unit = { text = value.toString }
  def value_=(value: Option[Int]): Unit = {
    text = value.map(i => i.toString).getOrElse("")
  }

  verifier = text => Try(text.toInt).isSuccess
  value = initialValue
}
