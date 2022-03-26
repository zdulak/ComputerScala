package computer.core

import scala.language.implicitConversions

sealed abstract class Slot(val sign: Char)

object Slot {
  case object Up extends Slot('^')
  case object Down extends Slot('v')
  case object Left extends Slot('<')
  case object Right extends Slot('>')
  case object Empty extends Slot(' ')

  implicit def stringToSlot(str: String): Slot = {
    str match {
      case Slot.Up.sign.toString => Slot.Up
      case Slot.Down.sign.toString => Slot.Down
      case Slot.Left.sign.toString => Slot.Left
      case Slot.Right.sign.toString => Slot.Right
      case Slot.Empty.sign.toString => Slot.Empty
      case _ => throw new IllegalArgumentException("Invalid type of arrow")
    }
  }
}
