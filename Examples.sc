import scala.annotation.tailrec

// Infix example
3 + 4

// Prefix example
!true

// Variables as values vs. variables as references

// Scala variables are mutable (i.e,. they can change)
// That is, they are variables as values.
var a: Int = 5

a = 25

var sum: Int = 10 + a

// Scala values are immutable (i.e., they can't change)
// That is, they are variables as references.

val b : Int = 15

// not legal b = 25

// Expression-oriented Scala
var c = if (a < b) 25 else 30

// Statement-oriented Scala
if (a < b)
  c = 25
else
  c = 30

// Assignment syntactic sugar
c += 1
 println(c)
// but this doesn't work in Scala: c++

// Scala selection statements - if-then-else
var args : Array[String] = new Array[String](1)
  args(0) = "myfile.txt"
if (args.length != 1) {
  println("USAGE ERROR: wrong number of args fool!")
  //System.exit(1)
}
else if (! args(0).endsWith(".mkd")) {
  println("USAGE ERROR: wrong extension fool!")
  //System.exit(1)
}

// Scala selection statements - case/switch
val chr : Char = '['
caseswitch(chr)


def caseswitch(chr : Char) = {
  chr match {
    case '\\' => println("case 1 - begin, end, use, def, title, paragraph or newline")
    case '#' => println("case 2 - heading")
    case '*' => println("case 3 - bold or italics")
    case '+' => println("case 4 - list item")
    case '[' => println("case 5 - link")
    case '!' => println("case 6 - image")
    // default/error case
    case doh => println("DOH! Unexpected match fool!")
  }
}

// Scala iteration - boring example
var l = 0;

// for loop execution with a range
for( a <- 1 to 10){
  println( "Value of a: " + a );
}

// nested for loop
for (i <- 1 to 2)
  for (j <- 1 to 2)
    yield (i, j)

// Scala iteration - interesting example
var parseTree = new scala.collection.mutable.Stack[String]

parseTree.push("\\BEGIN")
parseTree.push("\\TITLE[My Page]")
parseTree.push("This is a simple Gittex document.")
parseTree.push("\\END")


parseTree.reverse.foreach{
  println
  // do something interesting
}

parseTree.foreach{
  println
  // do something interesting
}


// Scala recursion
val list = List.range(1, 100)


// (1) yields a "java.lang.StackOverflowError" with large lists
def sum(ints: List[Int]): Int = ints match {
  case Nil => 0
  case x :: tail => x + sum(tail)
}

// (2) tail-recursive solution
def sum2(ints: List[Int]): Int = {
  @tailrec
  def sumAccumulator(ints: List[Int], accum: Int): Int = {
    ints match {
      case Nil => accum
      case x :: tail => sumAccumulator(tail, accum + x)
    }
  }
  sumAccumulator(ints, 0)
}

// (3) good descriptions of recursion here:
// stackoverflow.com/questions/12496959/summing-values-in-a-list
// this example is from that page:
def sum3(xs: List[Int]): Int = {
  if (xs.isEmpty) 0
  else xs.head + sum3(xs.tail)
}

// and a fancy, functional way to sum lists
def sumWithReduce(ints: List[Int]) = {
  ints.reduceLeft(_ + _)
}


println(sum(list))
println(sum2(list))
println(sum3(list))
println(sumWithReduce(list))

// User-defined types - this is dumb
type Double = String
var josh : Double = "Josh"

// Constructed type
type IntList = List[Int]

Int.MaxValue

// Scala type annotations
var noTypeAnnotation = 10
var givenTypAnnotation : Int = 100

// Type inferencing
def noTypeFunction(paramater : Any) = 10

noTypeFunction("josh")
noTypeFunction(10)


// simple type inference
var anInt : Int = 10
val aVal = anInt * 2.5 // Scala infers the type Double

// static type checking
// val anotherInt : Int = "Josh" not legal

// Type equivalence - structural vs. name

type fahrenheit = Int
type celsius = Int

// Scala example of name
var var1 : fahrenheit = 10
var var2 : celsius = var1







