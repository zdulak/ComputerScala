package computer.core

import computer.core.slots.{Arrow, Slot}
import computer.core.traits.Board

class DefaultBoard(slots: IndexedSeq[IndexedSeq[Slot]]) extends Board {
  private val _slots = slots
  val rowSize: Int = _slots.length
  val colSize: Int = _slots.head.length

  def this(rowSize: Int, colSize: Int) = this(
    for (_ <- 0 until rowSize) yield for (_ <- 0 until colSize) yield Slot.Empty)

  def apply(row: Int): Seq[Slot] = _slots(row)

  def apply(row: Int, col: Int): Slot = _slots(row)(col)

  def isSlotFull(row: Int, col: Int): Boolean = apply(row, col) != Slot.Empty

  def getArrow(id: Int): Option[(Int, Int, Arrow)] = {
    _slots.indexWhere(_.exists(_.hasId(id))) match {
      case -1 => None
      case row => {
        val col = _slots(row).indexWhere(_.hasId(id))
        Some(row, col, apply(row, col).asInstanceOf[Arrow])
      }
    }
  }

  def mark(row: Int, col: Int, slot: Slot): Board = new DefaultBoard(
    _slots.updated(row, _slots(row).updated(col, slot)))

  def unmark(row: Int, col: Int): Board = new DefaultBoard(
    _slots.updated(row, _slots(row).updated(col, Slot.Empty)))

  def create(kind: Arrow, row: Int, col: Int): Either[String, Board] = {
    if (row >= 0 && row < rowSize && col >= 0 && col < colSize) {
      if (!isSlotFull(row, col)) {
        if (getArrow(kind.id).isEmpty) Right(mark(row, col, kind)) else Left("The id is already used")
      }
      else Left("The slot is full")
    }
    else Left("Invalid coordinates")
  }

  //noinspection DuplicatedCode
  def move(id: Int, steps: Int): Either[String, Board] = {
    val fullTargetMsg = "The target slot is already occupied"

    getArrow(id) match {
      case None => Left("Arrow with the given id does not exist")
      case Some((row, col, arrow)) => Right {
        (arrow match {
          case arrow: Arrow.Up =>
            val newRow = (row + steps % rowSize) % rowSize
            if(!isSlotFull(newRow, col)) mark(newRow, col, arrow) else return Left(fullTargetMsg)
          case arrow: Arrow.Down =>
            val newRow = (row - steps % rowSize) % rowSize
            if(!isSlotFull(newRow, col)) mark(newRow, col, arrow) else return Left(fullTargetMsg)
          case arrow: Arrow.Left =>
            val newCol = (col - steps % colSize) % colSize
            if(!isSlotFull(row, newCol)) mark(row, newCol, arrow) else return Left(fullTargetMsg)
          case arrow: Arrow.Right =>
            val newCol = (col + steps % colSize) % colSize
            if(!isSlotFull(row, newCol)) mark(row, newCol, arrow) else return Left(fullTargetMsg)
        }).unmark(row, col)
      }
    }
  }

  def rotate(id: Int, angle: Int): Either[String, Board] = {
    if (!List(0, 90, 180, 270, 360).contains(Math.abs(angle))) Left("Invalid angle")
    else {
      getArrow(id) match {
        case None => Left("Arrow with the given id does not exist")
        case Some((row, col, arrow)) => Right(mark(row, col, arrow.rotate(angle)))
      }
    }
  }
}
