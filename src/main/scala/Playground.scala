
import com.dv.compiler.jsonnet.Parser.expr
import com.dv.compiler.jsonnet.{Evaluator, JsonSerializer}

object Playground extends App{
  //val exprOfFile = fastparse.parse("""local bla = 555;""", expr(_)).get.value
  println(JsonSerializer.serialize(Evaluator.evaluate(fastparse.parse("""local bla = 555;local blu = 345;{"a":bla + blu}""",expr(_)).get.value,Map.empty)))
}
