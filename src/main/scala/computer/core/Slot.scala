package computer.core

sealed abstract class Slot(val sign: Char)

object Slot {
  case object Up extends Slot('^')
  case object Down extends Slot('v')
  case object Left extends Slot('<')
  case object Right extends Slot('>')
  case object Empty extends Slot(' ')
}
