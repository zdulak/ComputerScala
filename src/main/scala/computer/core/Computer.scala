package computer.core

import computer.core.slots.Arrow.StringExtensions
import computer.core.traits.{Board, View}
import scala.annotation.tailrec
import scala.util.{Failure, Success, Try}

class Computer(view: View) {
  @tailrec
  final def run(board: Board, commands: Seq[String]): Unit = {
    view.printBoard(board)
    if (commands.nonEmpty) {
      view.printMsg(commands.head)
      execute(board, commands.head) match {
        case Right(newBoard) => run(newBoard, commands.tail)
        case Left(msg) => {
          view.printMsg(msg)
          run(board, commands.tail)
        }
      }
    }
  }

  def execute(board: Board, command: String): Either[String, Board] = {
    Try {
      command.toLowerCase match {
        case s"create $kind $row $col $id" => board.create(kind.toArrow(id.toInt), row.toInt, col.toInt)
        case s"rotate $id $angel" => board.rotate(id.toInt, angel.toInt)
        case s"move $id $step" => board.move(id.toInt, step.toInt)
        case _ => Left("Invalid command")
      }
    } match {
      case Failure(exception) => Left("Invalid command parameters")
      case Success(value) => value
    }
  }
}
