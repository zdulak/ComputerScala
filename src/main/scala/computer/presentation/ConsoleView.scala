package computer.presentation

import computer.core.slots.Slot
import computer.core.traits.{Board, View}

object ConsoleView extends View {
  def printBoard(board: Board): Unit = {
    val border = "-" * (4 * board.colSize + 1)
    println(border)
    (0 until board.rowSize)
      .map(i => board(i).map(slotToString))
      .map(_.mkString("|", "|", "|"))
      .foreach(rowString => {
        println(rowString)
        println(border)
      })
    println()
  }

  def printMsg(msg: String): Unit = println(msg)

  private def slotToString(slot: Slot): String = " " + slot.sign + " "
}
