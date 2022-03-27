package computer.presentation

import computer.core.traits.{Board, View}

object ConsoleView extends View {
  def printBoard(board: Board): Unit = {
    val border = "-" * (2 * board.colSize + 1)
    println(border)
    (0 until board.rowSize)
      .map(i => board(i))
      .map(rowSlots => rowSlots.mkString("|", "|", "|"))
      .foreach(rowString => {
        println(rowString)
        println(border)
      })
  }

  def printMsg(msg: String): Unit = println(msg)
}
