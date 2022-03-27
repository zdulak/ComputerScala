package computer.core.slots

abstract class Slot(val sign: Char) {
  def hasId(id: Int): Boolean = this match {
    case arrow: Arrow => arrow.id == id
    case _ => false
  }
}

object Slot {
  case object Empty extends Slot(' ')
}
