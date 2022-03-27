package computer.core.traits

import computer.core.slots.Slot

trait Board {
  val rowSize: Int
  val colSize: Int

  def apply(row: Int, col: Int): Slot
  def isSlotFull(row: Int, col: Int): Boolean
  def exists(id: Int): Boolean
  def create(kind: Slot, row: Int, col: Int, id: Int): Either[String, Board]
  def rotate(id: Int, angle: Int): Either[String, Board]
  def move(id: Int, steps: Int): Either[String, Board]
}
