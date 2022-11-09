
import com.dv.compiler.jsonnet.Parser.expr
import com.dv.compiler.jsonnet.{Evaluator, JsonSerializer, Value}

import scala.io.Source

object Main extends App{
  val resource = Source.fromResource("a.jsonnet")
  val lines: Iterator[String] = resource.getLines()
  val inputFileStr = lines.mkString("\n")
  println()
  println("----------------------------------------------")
  println("input file")
  println("----------------------------------------------")
  println(inputFileStr)
  println("----------------------------------------------")
  println("----------------------------------------------")
  println("----------------------------------------------")
  println("input file's expression tree")
  println("----------------------------------------------")
  val exprOfFile = fastparse.parse(inputFileStr, expr(_)).get.value
  println(exprOfFile)
  println("----------------------------------------------")
  println("input file's value")
  println("----------------------------------------------")
  val value = Evaluator.evaluate(exprOfFile, Map("percentOfExposure" -> Value.Integer(42)))
  println(value)
  println("----------------------------------------------")
  println("json of value")
  println("----------------------------------------------")
  println(JsonSerializer.serialize(value))
}
