package computer.core.slots

abstract class Slot(val sign: Char)

object Slot {
  case object Empty extends Slot(' ')
}
