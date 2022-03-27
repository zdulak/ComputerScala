package computer.core.traits

import computer.core.slots.Slot

trait View {
  def printBoard(board: Board): Unit
  def printMsg(msg: String): Unit

}
