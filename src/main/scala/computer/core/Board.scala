package computer.core

class Board(slots: IndexedSeq[IndexedSeq[Slot]]) {
  private val _slots = slots
  val rowSize: Int = _slots.length
  val colSize: Int = _slots.head.length

  def this(rowSize: Int, colSize: Int) = this(
    for (_ <- 0 until rowSize) yield for (_ <- 0 until colSize) yield Slot.Empty)

  def Create(kind: Slot, row: Int, col: Int, id: Int): Either[Board, Board] = ???
  def Rotate(id: Int, angle: Int): Either[Board, Board] = ???
  def Move(id: Int, steps: Int): Either[Board, Board] = ???

}
