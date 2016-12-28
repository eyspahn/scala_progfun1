object rationals {

  //lecture 2.5, 2.6
  class Rational(x: Int, y: Int){
    require(y !=0, "denominator must not be zero")

    def this(x: Int) = this(x, 1)  // if used as fn, it's another constructor for the class

    private def gcd(a: Int, b:Int): Int = if (b==0) a else gcd(b, a%b)

    //val g =gcd(numer, denom)
  //  def numer = x/g // better to use val numer = x/gcd(x,y) if fn called often
  //  def denom = y/g
    def numer = x
    def denom = y

    def add(that: Rational) =
      new Rational(
        numer * that.denom + that.numer*denom,
        denom * that.denom)

    override def toString = {
      val g =gcd(numer, denom)
      numer / g + "/" + denom / g
    }

    def origtoString = numer + "/" + denom

    def neg: Rational = new Rational(-numer, denom)

    def sub(that: Rational) =
      new Rational(numer * that.denom - denom * that.numer,
        denom*that.denom)

    def + (that: Rational) = new Rational(numer*that.denom + that.numer*denom, denom*that.denom)

    // DRY principle -- add the neg of that to subtract
    def sub2(that: Rational) = add(that.neg)
    def - (that: Rational) = this + that.neg

    def mul(that: Rational) = new Rational(numer*that.numer, denom*that.denom)

    def less(that: Rational) = numer * that.denom < that.numer * denom
    def less2(that:Rational) = this.numer * that.denom < that.numer * this.denom

    def < (that: Rational) = numer * that.denom < that.numer * denom
    def max2(that: Rational) = if (this < that) that else this

    def max(that: Rational) = if (this.less(that)) that else this

    // to write -x and have it mean take the negative
    def unary_- : Rational = new Rational(-new, denom)
    // then can use def - (that: Rational) = this + -that



  }
  val xx = new Rational(1, 2)
  println(x.numer)
  xx.numer
  xx.denom

  val yy = new Rational(2, 3)
  xx.add(yy)

  val x = new Rational(1,3)
  val y = new Rational(5,7)
  val z = new Rational(3,2)
  x.sub(y).sub(z)
  y.add(y)
 // x.less(y)
  x < y
  x max2 y
  y - y
  y - z

  new Rational(2)

  new Rational(1,2).numer

  new Rational(1, 2).less(new Rational(2,3))

}

