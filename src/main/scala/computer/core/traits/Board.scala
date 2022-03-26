package computer.core.traits

import computer.core.Slot

trait Board {
  val rowSize: Int
  val colSize: Int

  def create(kind: Slot, row: Int, col: Int, id: Int): Either[String, Board]
  def rotate(id: Int, angle: Int): Either[String, Board]
  def move(id: Int, steps: Int): Either[String, Board]
}
