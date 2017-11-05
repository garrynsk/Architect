package scala

import org.junit.Test
import org.junit.Assert._

import architect.algebra.Monoid
/**
class NaturalTransformationTest {

    def f = new (Option ~> List) {
            def apply[T](opt: Option[T]): List[T] = opt.toList
        }
    def g = new (List ~> Option) {
            def apply[T](list: List[T]): Option[T] = list match {
                                                  case head :: rest => Some(head)
                                                  case Nil => None  
                                                }
        }      
                          
    def h = new (List ~> List) {
         def apply[T](list: List[T]): List[T] =
           list
       }


    val c1: Option[Int] = Some(2)
    val c2: List[Int] = List(4,3,6)

    @Test def associativity(): Unit = {
        def left = (f compose g) compose h
        def right = f compose (g compose h)

        assertEquals(left(c2), right(c2))
    }

}
*/