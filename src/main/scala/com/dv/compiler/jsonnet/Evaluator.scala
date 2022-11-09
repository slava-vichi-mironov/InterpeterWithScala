package com.dv.compiler.jsonnet

object Evaluator {
  def evaluate(expr: Expr, scope: Map[String, Value]): Value = expr match{
    case Expr.Str(s) => Value.Str(s)
    case Expr.Integer(i) => Value.Integer(i)
    case Expr.Dict(kvs) => Value.Dict(kvs.map{case (k, v) => (k, evaluate(v, scope))})
    case Expr.Plus(items) =>
      def f(v: Value) = {
        v match {
          case Value.Str(s) => s
          case Value.Integer(i) => i
        }
      }
      val seqOfValues = items.map(evaluate(_, scope)).map(f)
      if (seqOfValues.forall(_.isInstanceOf[String])) Value.Str(seqOfValues.map(_.toString).mkString)
      else Value.Integer(seqOfValues.map(_.asInstanceOf[Int]).reduce(_ + _))
    case Expr.Local(name, assigned, body) =>
      val assignedValue = evaluate(assigned, scope)
      evaluate(body, scope + (name -> assignedValue))
    case Expr.Ident(name) => scope(name)
    case Expr.Call(expr, args) =>
      val Value.Func(call) = evaluate(expr, scope)
      val evaluatedArgs = args.map(evaluate(_, scope))
      call(evaluatedArgs)
    case Expr.Func(argNames, body) =>
      Value.Func(args => evaluate(body, scope ++ argNames.zip(args)))
  }
}
