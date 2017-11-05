package architect

import universal.{ Zero, Product, Cozero, Coproduct, Mu }
import universal.Alias._

object Main {

  def main(args: Array[String]): Unit = {
    val test: List[Int] :*: Int :*: String :*: Zero = List(5) :*: 4 :*: "ddd" :*: Zero
    val cotest:  Int :*: (List[Int] :+: Int :+: Cozero) :*: Zero = 5 :*: Coproduct(List(5)) :*: Zero

    sealed trait Xml
    case class Math(title: Title, body: Body) extends Xml
    case class Title(text: String) extends Xml
    case class Body(equations: List[Equation]) extends Xml
    case class Equation(name: Option[Name], text: Formula) extends Xml
    case class Name(text: String) extends Xml

    sealed trait Formula
    case class Mult(eq1: Formula, eq2: Formula) extends Formula
    case class Add(eq1: Formula, eq2: Formula) extends Formula
    case class Minus(eq1: Formula, eq2: Formula) extends Formula
    case class Divide(eq1: Formula, eq2: Formula) extends Formula
    case class Number(name: Double) extends Formula

    sealed trait Rules
    case class Rule(current: String, target: String) extends Rules


    sealed trait Nat[+A]
    case class Empty() extends Nat[Nothing]
    case class Succ[+A](value: A, next: Nat[A]) extends Nat[A]

    def converge[A](value: A)(f: A => A): A =
      if (f(value) == value) then {
        println("If: " + value)
        f(value)
      } else {
        println("Else: " + value)
        converge(f(value))(f)
      }

    def plus(a: Nat[Int], acc: Nat[Int]): (Nat[Int], Nat[Int]) = (a, acc) match {
      case (Empty(), x) => (Empty(), x)
      case (Succ(value, next), x) => (next, Succ(value + 1, x))
    }

    val natural: Nat[Int] =
      Succ(1, (Succ(2, Succ(3, Empty()))))

    def naturalPlus(value: Nat[Int], init: Nat[Int]): (Nat[Int], Nat[Int]) =
      converge(value, init)(plus)

    println(naturalPlus(natural, Empty()))

    trait Tree  extends Rec[Tree] { self =>
     override def exp[B](f: Tree => B): () => B =
        () => f(self)

    }
    case class NoFill() extends Tree
    case class Node(value: Int, left: Tree, right: Tree) extends Tree

    val tree = Node(1, Node(2, NoFill(), NoFill()), Node(3, Node(4, NoFill(), NoFill()), NoFill()))

    //def map[A](tree: Tree[A], num: Int): Tree[A] = depths[A](map)(tree, num)
    

    def depths(tree: Tree): Int = tree match {
        case NoFill() => 0
        case Node(v, left, right) => 1 + depths(left) max depths(right)
    }
      
    val smth = depths(tree)

     println("depths: " + smth)


// catamorphism
def depthsCPS(rtree: Tree): Int ={
  def rec(rtree: Tree)(dep: Int => Int): Int = rtree match {
    case NoFill() => dep(0)
    case Node(v, left, right) => 
      rec(left){(leftNum: Int) => rec(right){(rightNum: Int) => dep(1 + leftNum max rightNum)} }
  }
  rec(tree)((newtree: Int) => newtree)
}

println("depthsCPS: " + depthsCPS(tree))


// anamorphism
//def from(n: Int)(list: List[Int]): List[Int] = 
//  from(n + 1)(n::list)

//apomorphism
def insert(value: Int, ctree: Tree): Tree = ctree match{
  case NoFill() => Node(value, NoFill(), NoFill())
  case Node(v, left, right) => if (value <= v) then  Node(v, insert(value, left), right) 
                                else Node(v, left, insert(value, right))  
                                
                                
}

  
println(insert(1, tree) )

// zygomorphism
def depthsCPSPerfect(rtree: Tree)(dep: Boolean => Boolean): Boolean = rtree match {
    case NoFill() => dep(true)
    case Node(v, left, right) => 
    depthsCPSPerfect(left){(leftTree: Boolean) => 
    depthsCPSPerfect(right){(rightTree: Boolean) => 
      dep(leftTree && rightTree && depthsCPS(left) == depthsCPS(right))
    }
  }

} 

println("isperfect: " + depthsCPSPerfect(tree)((newtree: Boolean) => newtree))

// paramorphism

def wordCount(words: List[String]): Int = {
  def rec(num: Int)(words: List[String]): Int = num match{
    case 0 => num
    case num => if(num < 500) then rec(num - 1)(words) else rec(num - 2)(words)
  }
  rec(2000)(words)
}    

val string = List("", "sfgdg"," sdfsdf",""," ","sdf")

println("words count: " + wordCount(string))


    case class Test(value: Int) extends Rec[Int]{ self =>
       override def exp[B](f: Int => B): () => B =
          () => f(self.value)

    }
    val ineger = Test(2)

// mutumorphism
    def numbers(n: Either[Int, Int]): String = n match {
      case Left(n) => numbers(Right(n - 1))
      case Right(n) => if(n <= 0) then {
        println(n)
        "done"
       } else numbers(Left(n-1))
    }
      
    println(numbers(Right(200000)))



case class NodeP[+A](left: A, right: A)
sealed trait TreeP[+A]
case class SuccP[+A](subtree: A) extends TreeP[A]
case class LeafP[+A](value: A) extends TreeP[A]

def fmap[A, B](value: TreeP[A])(f: A => B): TreeP[B] = value match {
  case LeafP(a) => LeafP(f(a))
  case SuccP(a) => SuccP(f(a))
}
  
val treeP = SuccP(
  SuccP(
    LeafP(
      NodeP(
        NodeP(1, 2),
        NodeP(3, 4)
      )
    )
  )
)



// catamorphism
def depthsCPST(rtree: Tree): Int ={
  def rec(rtree: Tree)(dep: Int => Int): Int = rtree match {
    case NoFill() => dep(0)
    case Node(v, left, right) => 
      rec(left){(leftNum: Int) => rec(right){(rightNum: Int) => dep(1 + leftNum max rightNum)} }
  }
  rec(tree)((newtree: Int) => newtree)
}

// anamorphism
//def from(n: Int)(list: List[Int]): List[Int] = 
//  from(n + 1)(n::list)

//apomorphism
def insertT(value: Int, ctree: Tree): Tree = ctree match{
  case NoFill() => Node(value, NoFill(), NoFill())
  case Node(v, left, right) => if (value <= v) then  Node(v, insertT(value, left), right) 
                                else Node(v, left, insertT(value, right))  
                                
                                
}

// zygomorphism
def depthsCPSPerfectT(rtree: Tree)(dep: Boolean => Boolean): Boolean = rtree match {
    case NoFill() => dep(true)
    case Node(v, left, right) => 
    depthsCPSPerfect(left){(leftTree: Boolean) => 
    depthsCPSPerfect(right){(rightTree: Boolean) => 
      dep(leftTree && rightTree && depthsCPST(left) == depthsCPST(right))
    }
  }

} 

// paramorphism

def wordCountT(words: List[String]): Int = {
  def rec(num: Int)(words: List[String]): Int = num match{
    case 0 => num
    case num => if(num < 500) then rec(num - 1)(words) else rec(num - 2)(words)
  }
  rec(2000)(words)
}    

// mutumorphism
    def numbersT(n: Either[Int, Int]): String = n match {
      case Left(n) => numbers(Right(n - 1))
      case Right(n) => if(n <= 0) then {
        println(n)
        "done"
       } else numbers(Left(n-1))
    }


trait Rec[A] { self =>
  
  def exp[B](f: A => B): () => B

  def transpose[B](g: A => B): Rec[B] = new Rec { 

    override def exp[C](f: B => C): () => C =
        self.exp(g andThen f)

  }

  def eval[B](f: A => B): B ={

    self.exp(f)()

  }
}

case class Stop[A](value: A)
case class Tup(value: Tuple2[List[Int], Int]) extends Rec[Tuple2[List[Int], Int]] {
    
    override def exp(f: Tuple2[List[Int], Int] => Tuple2[List[Int], Int]): () => Tuple2[List[Int], Int] = () => 
        f(value)  

  }
val list: Tuple2[List[Int], Int] = Tuple2(List(1, 2, 3, 4, 5), 0)
/**
def algebra[A](value: Tuple2[List[A], A]): Tuple2[List[A], A] = value match{
  case (Nil, a) => (Nil, a) 
  case (tail::head, a) => (tail, head)
}
  
}
*/
def f(value: List[Int], acc: Int): (List[Int], Int) = (value, acc) match {
  case (Nil, a) => (Nil, a) 
  case (tail, head) => (tail, head + acc)
}

type Algebra[F[_,_],D[_], A] = Function[F[D[A],A], F[D[A],A]]
type TypeFunctor[F[_,_],D[_], A] = Function[F[D[A],A], F[D[A],A]]

def calcSum[F[_,_],D[_], A](fun: TypeFunctor[F, D, A])(implicit t: Rec[F[D[A], A]]): F[D[A], A] = {
     t.transpose(a => fun(a)).eval(a => a)
}

//println("Sum of List: " + calcSum(f)(Tup(list)))
 /**
  value match{
   case Nil => sum => sum
   case head::tail => (a: Int) =>{println("first"); calcSum(tail)(head + a)}
 }

 def calcNum(value: List[Int]): Int => Int = value match{
   case Nil => sum => sum
   case head::tail => (a: Int) => {println("second"); calcNum(tail)(1 + a)}
 }


println("Number of elements: " + calcNum(list)(0))*/
//println("Compose: " + ((calcSum(list) andThen calcNum(list))(0)))
}
}