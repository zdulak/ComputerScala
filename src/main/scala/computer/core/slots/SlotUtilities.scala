package computer.core.slots

object SlotUtilities {
  implicit class StringExtensions(val str: String) {
    def toSlot: Slot = {
      str match {
        case Slot.Up.sign.toString => Slot.Up
        case Slot.Down.sign.toString => Slot.Down
        case Slot.Left.sign.toString => Slot.Left
        case Slot.Right.sign.toString => Slot.Right
        case _ => throw new IllegalArgumentException("Invalid type of arrow")
      }
    }
  }
}
