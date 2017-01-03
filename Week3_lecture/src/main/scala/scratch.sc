// import week3.Rational
// import week3._  to import all members in package week 3
// import week3.{Rational, Hello" // imports Rational and Hello

import week3._

object scratch{
  // if no import: new week3.Rational(1,2)
  new Rational(1,2)

  def error(msg: String) = throw new Error(msg)
  //  error("test") --> will cause exception

  val x = null
  val y: String = x

 // val z: Int = null

  if (true) 1 else false // type AnyVal, not Boolean or Int

  // Polymorphism

  val l1 = List(1, 2, 3)
  val l2 = List(List(true, false), List(3))


  def nth[T](n: Int, xs: List[T]): T = {
    if (n==0) xs.head
    else nth(n-1, xs.tail)
    // else throw new IndexOutOfBoundsException
  }

  val list = new Cons(1, new Cons(2, new Cons(3, new Nil)))
  nth(2, list)
  nth(-1, list)


}