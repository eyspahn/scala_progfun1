import week3._

object nth {

  def old_nth[T](n: Int, xs: List[T]): T =
    if (n==0) xs.head
    else old_nth(n-1, xs.tail)

  def nth[T](n: Int, xs: List[T]): T =
    if (xs.isEmpty) throw new IndexOutOfBoundsException
    else if (n==0) xs.head
    else nth(n-1, xs.tail)

  val list = new Cons(1, new Cons(2, new Cons(3, new Nil)))

  println(list)

  val old1 = old_nth(2, list)
  val n1 = nth(2, list)

   val n2 = nth(-1, list) // IndexOutOfBoundsExcption
  //  val old2 = old_nth(-1, list)  // NoSuchElementException

}