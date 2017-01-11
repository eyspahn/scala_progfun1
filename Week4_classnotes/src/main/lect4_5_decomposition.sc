package lect4_5

  // we have expression trees
// we want to find a general and convenient way to access
  // objects in an extensible class hierarchy

trait Expr {
  def isNumber: Boolean
  def isSum: Boolean
  def numValue: Int
  def leftOp: Expr
  def rightOp: Expr
}

class Number(n: Int) extends Expr {
  def isNumber: Boolean = true
  def isSum: Boolean = false
  def numValue: Int = n
  def leftOp: Expr = throw new Error("Number.leftOp")
  def rightOp: Expr = throw new Error("Number.rightOp")
}

class Sum(e1: Expr, e2: Expr) extends Expr {
  def isNumber: Boolean = false
  def isSum: Boolean = true
  def numValue: Int = throw new Error("Sum.numValue")
  def leftOp: Expr = e1
  def rightOp: Expr = e2
}

  // example: want eval(sum(Num(1), Num(2)) = 3
def eval(e: Expr): Int = {
  if (e.isNumber) e.numValue
  else if (e.isSum) eval(e.leftOp) + eval(e.rightOp)
  else throw new Error("unknown expression: " + e)
}

// but, writing all these funtions is tedious
// example of adding new forms of expressions -- need to define a lot of methods. etc
// example: class Product (multiplicative product) & class Var (variable)
// would need name, isVar, isProd, added to trait Expr (8 classes)
// then there would need to be 8 methods for each of the classes
// --> quadratic increase of methods!

// use of type casts & type tests are not encouraged (but it exists)

// soluion1 - Object-oriented solution; but problems.
// but would need test, access methods for all subclasses
  // some methods (e.g. simplify - a*b + a*c -> a*(b+c)) need non-local knowledge
trait Expr {
  def eval: Int
  def show: String // and needs to get propagated
}
class Number(n:Int) extends Expr{
  def eval: Int = n
}
class Sum(e1: Expr, e2: Expr) extends Expr{
  def eval: Int = e1.eval + e2.eval
}

// pattern matching to the rescue
// Functional Decomposition with Pattern Matching
// case class will implicitly define companion objects with apply methods
trait Expr
case class Number(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr

//example:
def eval(e: Expr): Int = e match {
  case Number(n) => n
  case Sum(e1, e2) => eval(e1) + eval(e2)
}

eval(Sum(Number(1), Number(2)))
// --> eval(Number(1) + eval(Number(2)) --> 3

// can have pattern matching as a mthod of base trait:
trait Expr {
  def eval: Int = this match {
    case Number(n) => n
    case Sum(e1, e2) => e1.eval + e2.eval
  }
}
}

object exprs {
  def show(e: Expr): String = e match {
    case Number(n) => n.toString
    case Sum(left, right) => show(left) + " + " show(r)
  }

  show(Sum(Number(1), Number(44)))

}
