package week4

object exprs{

println("hello world")

trait Expr
case class Number(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr

// can have pattern matching as a mthod of base trait:
trait Expr {
  def eval: Int = this match {
    case Number(n) => n
    case Sum(e1, e2) => e1.eval + e2.eval
  }
}


  def show(e: Expr): String = e match {
    case Number(n) => n.toString
    case Sum(left, right) => show(left) + " + " show(r)
  }

  show(Sum(Number(1), Number(44)))

}