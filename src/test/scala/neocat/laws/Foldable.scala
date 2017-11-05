package scala

import org.junit.Test
import org.junit.Assert._
/**
import instances.IntRing._
import instances.IntMonoid._

import neocat.Foldable

class FoldableTest { 
        
    val c2: List[Int] = List(0,1,2,3,4)

    @Test def checkFoldLeft(): Unit = {
        assertEquals(Foldable[List].foldLeft(c2)(0)(_ + _), 10)
    }

    @Test def checkFoldMap(): Unit = {
        assertEquals(Foldable[List].foldMap(c2)(a => a), 10) // TODO: check
    }

}
*/