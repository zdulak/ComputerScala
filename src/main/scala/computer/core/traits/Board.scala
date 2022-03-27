package computer.core.traits

import computer.core.slots.{Arrow, Slot}

trait Board {
  val rowSize: Int
  val colSize: Int

  def apply(row: Int, col: Int): Slot
  def isSlotFull(row: Int, col: Int): Boolean
  def getArrow(id: Int): Option[(Int, Int, Arrow)]
  def mark(row: Int, col: Int, slot: Slot): Board
  def unmark(row: Int, col: Int): Board
  def create(kind: Slot, row: Int, col: Int): Either[String, Board]
  def move(id: Int, steps: Int): Either[String, Board]
  def rotate(id: Int, angle: Int): Either[String, Board]

}
