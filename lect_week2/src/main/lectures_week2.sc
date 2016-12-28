object session {

  import math.abs

  1+1
  // Lect 2.1
  def sum(f: Int => Int, a: Int, b: Int): Int = {
    def loop(a: Int, acc: Int): Int = {
      if (a > b) acc
      else loop(a + 1, acc + f(a))
    }
    loop(a,0)
  }

  sum(x=> x*x, 3, 5)

  // Note: sum(cube)(1,10) is the same as
  // (sum(cube))(1, 10)
  // function application associates to the left

  def sum2(f: Int => Int)(a: Int, b: Int): Int =
    if (a>b) 0 else f(a) + sum2(f)(a+1, b)

  sum2(x=>x*x)(2, 5)

  // define a product function
  def product(f: Int => Int)(a: Int, b: Int): Int = {
    if (a>b) 1
    else f(a)*product(f)(a+1, b)
  }
  product(x=>x*x)(3,4)


  //now write a factorial function and use product fn above
  def fact(n: Int): Int =
    product(x=>x)(1,n)

  fact(5)

  //Can we generalize sum & product in a general function?
  // effectively a map-reduce
  def mapReduce(f: Int => Int, combine: (Int, Int)=> Int, zero: Int)(a: Int, b: Int): Int = {
    if (a>b) zero
    else combine(f(a), mapReduce(f, combine, zero)(a+1, b))
  }


  def product2(f: Int => Int)(a: Int, b: Int): Int =
    mapReduce(f, (x,y) => x*y, 1)(a,b)

  product2(x=>x*x)(3,4)

  def fact2(n: Int): Int =
    product2(x=>x)(1,n)

  fact2(5)

  // Lect 2.3

  //finding a fixed point of a function
  // x is a fixed point if f(x) = x

  val tolerance = 0.0001
  def isCloseEnough(x: Double, y: Double) =
    abs((x-y)/x)/x < tolerance

  def fixedPoint(f: Double => Double)(firstGuess: Double) = {
    def iterate(guess: Double): Double = {
      println("guess = " + guess)
      val next = f(guess)
      if (isCloseEnough(guess, next)) next
      else iterate(next)
    }
    iterate(firstGuess)
  }

  fixedPoint(x => 1+x/2)(1)

  // return to square roots

  def sqrt(x:Double) = fixedPoint(y=> (y+x/y)/2)(1)
  sqrt(2)

  // stabilizing by averaging
  def averageDamp(f: Double => Double)(x:Double) = (x + f(x))/2

  def sqrt3(x: Double) =
    fixedPoint(averageDamp(y=>x/y))(1)
  sqrt3(2)

  //See rationals worksheet

  // Operators
  // step 1: infix notation: e.g. r add s is used in plce of r.add(s)
  // step 2: relaxed identifiers.
  // example in rationals



}

