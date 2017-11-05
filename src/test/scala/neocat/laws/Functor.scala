package scala

import org.junit.Test
import org.junit.Assert._
/**
import algebra.Monoid
import neocat.Functor
import instances.OptionSemiring._
import instances.IntRing._
import instances.IntMonoid._


class FunctorTest {

    def f(a: Option[Int]): List[Int] = a match {
                                                case Some(x) => List(x)
                                                case None => List.empty
                                            }
    def g(a: List[Int]): Option[Int] = a match {
                                                case x::rest => Some(x)
                                                case Nil => None
                                            }
    def h(a: List[Int]): List[Int] = a 

    val c1: Option[Int] = Some(2)
    val c2: List[Int] = List(4,3,6)

    val FL = Functor[List]
    val FO = Functor[Option]

    val FL_FO = FL compose FO

    val values = List(Some(1))

    @Test def composition(): Unit = {
       assertEquals(FL_FO.map(values)(_ + 100), FL_FO.map(values)(_ + 100))
    }

    @Test def associativity(): Unit = {
        def left = (f compose g) compose h
        def right = f compose (g compose h)

        assertEquals(left(c2), right(c2))
    }

    @Test def identity(): Unit = {
        assertEquals(Monoid[Option[Int]].combine(Monoid[Option[Int]].id, c1), Some(2))
    }
  
    @Test def listMap(): Unit = {
       assertEquals(Functor[List].map(c2)(a => a*2), List(8,6,12))
    }
}
*/