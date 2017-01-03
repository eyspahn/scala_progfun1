package week3

import java.util.NoSuchElementException

/**
  * Created by emily on 1/3/17.
  */
trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false // Cons cells are never empty
}

class Nil[T] extends List[T] {
  def isEmpty = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
  // Nothing is the type of the exception; Nothing is a subtype of any other type
}
