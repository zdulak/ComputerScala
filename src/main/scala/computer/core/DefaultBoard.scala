package computer.core

import computer.core.slots.{Arrow, Slot}
import computer.core.traits.Board

class DefaultBoard(slots: IndexedSeq[IndexedSeq[Slot]]) extends Board {
  private val _slots = slots
  val rowSize: Int = _slots.length
  val colSize: Int = _slots.head.length

  def this(rowSize: Int, colSize: Int) = this(
    for (_ <- 0 until rowSize) yield for (_ <- 0 until colSize) yield Slot.Empty)

  def apply(row: Int, col: Int): Slot = _slots(row)(col)

  def isSlotFull(row: Int, col: Int): Boolean = apply(row, col) != Slot.Empty

  def existsId(id: Int): Boolean = _slots.exists(_.exists {
    case arrow: Arrow => arrow.id == id
    case _ => false
  })

  def create(kind: Slot, row: Int, col: Int): Either[String, Board] = {
    if(row >= 0 && row < rowSize && col >= 0 && col < colSize){
      if (isSlotFull())
    }
    else
      Left("Invalid coordinates")
  }
  def rotate(id: Int, angle: Int): Either[String, Board] = ???
  def move(id: Int, steps: Int): Either[String, Board] = ???


}
