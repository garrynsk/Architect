package scala

import org.junit.Test
import org.junit.Assert._
/**
import algebra.MonoidK
import architect.instances.ListMonoidK._
import architect.instances.OptionMonoidK._

class MonoidKTest {

    def f(a: Option[_]): Option[_] = a orElse Some(List(2,5,8))
    def g(a: Option[_]): Option[_] = None
    def h(a: Option[_]): Option[_] = a

    val a: Option[List[Int]] = Some(List(2,5,8))
    val b: Option[List[Int]] = Some(List(9,1,2,3))
    val c: Option[List[Int]] = None

    @Test def associativityAlias(): Unit = {
        assertEquals((a <> b) <> c, a <> (b <> c))
    }

    @Test def associativity(): Unit = {
        assertEquals(MonoidK[Option].associative(MonoidK[Option].combine)(a, b, c), true)
    }

    @Test def identity(): Unit = {
        assertEquals(MonoidK[Option].id <> a, a )
    }

    @Test def binaryOperation(): Unit = {
       assertEquals(MonoidK[Option].combine(a, b), Some(List(2, 5, 8)))
    }

    @Test def associativityFunctions(): Unit = {
        assertEquals(MonoidK[Option].composition(a)(f, g, h), true)
    }

    @Test def leftIdentity(): Unit = {
       assertEquals(MonoidK[Option].unitLeft(a)(f), true)
    }

    @Test def rightIdentity(): Unit = {
       assertEquals(MonoidK[Option].unitRight(a)(f), true)
    }
}*/