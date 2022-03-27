package computer.core.slots

sealed abstract class Arrow(override val sign: Char, val id: Int ) extends Slot(sign)

object Arrow {
  case class Up(override val id: Int = 0) extends Arrow('^', id)
  case class Down(override val id: Int = 0) extends Arrow('v', id)
  case class Left(override val id: Int = 0) extends Arrow('<', id)
  case class Right(override val id: Int = 0) extends Arrow('>', id)

  implicit class StringExtensions(val str: String) {
    def toArrow(id: Int): Slot = {
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
