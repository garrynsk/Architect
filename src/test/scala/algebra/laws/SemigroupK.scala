package scala

import org.junit.Test
import org.junit.Assert._
/**
import algebra.SemigroupK
import architect.instances.IntRing._
import architect.instances.IntMonoid._
import architect.instances.ListMonoidK._
import architect.instances.OptionMonoidK._

class SemigroupKTest {

    // We have to write type explicitly to use <> operation
    val a: Option[List[Int]] = None
    val b: Option[List[Int]] = Some(List(2,5,8))
    val c: Option[List[Int]] = Some(List(9,1,2,3))
    
    def f(a: Option[_]): Option[_] = a orElse None
    def g(a: Option[_]): Option[_] = None
    def h(a: Option[_]): Option[_] = a

    @Test def binaryOperation(): Unit = {
       assertEquals(SemigroupK[Option].combine(a, b), Some(List(2,5,8)))
    }
    
    @Test def associativity(): Unit = {
        assertEquals(SemigroupK[Option].associative(SemigroupK[Option].combine)(a, b, c), true)
    }

    @Test def associativityAlias(): Unit = {
        assertEquals((a <> b) <> c, a <> (b <> c))
    }

    @Test def associativityFunctions(): Unit = {
        assertEquals(SemigroupK[Option].composition(b)(f, g, h), true)
    }

}*/