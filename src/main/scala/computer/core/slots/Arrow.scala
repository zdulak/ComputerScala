package computer.core.slots

sealed abstract class Arrow(override val sign: Char, val angle: Int, val id: Int) extends Slot(sign) {
  def rotate(angle: Int): Arrow = ((this.angle + angle) % 360) match {
    case newAngle if (newAngle == Arrow.Up().angle) => Arrow.Up(id)
    case newAngle if (Math.abs(newAngle) == Arrow.Down().angle) => Arrow.Down(id)
    case newAngle if (newAngle == Arrow.Left().angle || newAngle == -Arrow.Right().angle) => Arrow.Left(id)
    case newAngle if (newAngle == Arrow.Right().angle || newAngle == -Arrow.Left().angle) => Arrow.Right(id)
    case _ => throw new IllegalArgumentException("Invalid angle")
  }

}

object Arrow {
  case class Up(override val id: Int = 0) extends Arrow('^', 0, id)
  case class Down(override val id: Int = 0) extends Arrow('v',180,id)
  case class Left(override val id: Int = 0) extends Arrow('<', 270, id)
  case class Right(override val id: Int = 0) extends Arrow('>', 90, id)

  implicit class StringExtensions(val str: String) {
    def toArrow(id: Int): Arrow = {
      str match {
        case _ if (str == Arrow.Up().sign.toString) => Arrow.Up(id)
        case _ if (str == Arrow.Down().sign.toString) => Arrow.Down(id)
        case _ if (str == Arrow.Left().sign.toString) => Arrow.Left(id)
        case _ if (str == Arrow.Right().sign.toString) => Arrow.Right(id)
        case _ => throw new IllegalArgumentException("Invalid type of arrow")
      }
    }
  }
}
