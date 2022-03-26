package computer.core

import computer.core.traits.Board

class DefaultBoard(slots: IndexedSeq[IndexedSeq[Slot]]) extends Board {
  private val _slots = slots
  val rowSize: Int = _slots.length
  val colSize: Int = _slots.head.length

  def this(rowSize: Int, colSize: Int) = this(
    for (_ <- 0 until rowSize) yield for (_ <- 0 until colSize) yield Slot.Empty)

  def create(kind: Slot, row: Int, col: Int, id: Int): Either[String, Board] = ???
  def rotate(id: Int, angle: Int): Either[String, Board] = ???
  def move(id: Int, steps: Int): Either[String, Board] = ???

}
