package computer.presentation

import computer.core.{DefaultBoard, Computer}
import scopt.OParser
import java.io.File
import scala.io.Source
import scala.util.Using

object Main {
  def main(args: Array[String]): Unit = {
    OParser.parse(getParser, args, Config()) match {
      case Some(config) => {
        val commands = Using(Source.fromFile(config.fileName)) {
          source => source.getLines().toSeq
        }.getOrElse {
          println("Invalid file")
          Seq.empty[String]
        }

        new Computer(ConsoleView).run(new DefaultBoard(config.rowSize, config.colSize), commands)
      }
      case None => println("Invalid configuration")
    }
  }

  private def getParser: OParser[String, Config] = {
    val builder = OParser.builder[Config]
    val parser = {
      import builder._
      OParser.sequence(
        arg[String]("<file name>")
          .required()
          .validate(name => if (new File(name).exists()) success else failure("File does not exist"))
          .action((name, config) => config.copy(fileName = name))
          .text("Name of a file"),
        arg[Int]("<number of rows>")
          .required()
          .validate(rowSize => if (rowSize > 0) success else failure("Number of rows must be a positive number"))
          .action((rowSize, config) => config.copy(rowSize = rowSize))
          .text("Number of rows"),
        arg[Int]("<number of columns>")
          .required()
          .validate(colSize =>
                      if (colSize > 0) success else failure("Number of columns must be a positive number"))
          .action((colSize, config) => config.copy(colSize = colSize))
          .text("Number of columns"),
        help("help").text("prints this usage text")
        )
    }
    parser
  }
}
