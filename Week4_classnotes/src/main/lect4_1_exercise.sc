package exerciseweek4

// Peano numbers
abstract class Nat{
  // represents non-negative integers
  def isZero: Boolean
  def predecessor: Nat
  def successor = new Succ(this)
  def + (that: Nat): Nat
  def - (that: Nat): Nat

}


object Zero extends Nat {
  def isZero = true
  def predecessor = throw new Error("0.predecessor")
  def + (that: Nat) = that
  def - (that: Nat) = if (that.isZero) this else throw new Error("negative number")
}


class Succ(n: Nat) extends Nat  {
  // nat number 1 larger than n
  def isZero = false
  def predecessor = n // because n represents the NEXT number
  def +(that: Nat) = new Succ(n + that)
  def -(that: Nat) = if (that.isZero) n else n - that.predecessor

}