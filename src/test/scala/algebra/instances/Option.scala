package scala

import org.junit.Test
import org.junit.Assert._

import architect.instances._
import option._
import int._
import list._
import coordinates._
import architect.algebra.Monoid.Ops._
import architect.algebra.MonoidK.Ops._

class OptionTest {

    val m: Coordinates = Coordinates(2,3)

    val a: Option[Coordinates] = Some(Coordinates(2,5))
    val b: Option[Coordinates] = Some(Coordinates(-2,5))
    val c: Option[Coordinates] = None
    
    val d: Option[List[Int]] = Some(List(2,5,8))
    val e: Option[List[Int]] = Some(List(9,1,2,3))
    val f: Option[List[Int]] = Some(List(1,2))

    val g: Option[String] = Some("Hello")
    val h: Option[List[String]] = Some(List("One", "Two"))
    val i: Option[Option[Int]] = Some(Option(1))

    @Test def monoidCombine(): Unit = {
        assertEquals(a <:> b <:> c, Some(Coordinates(0,10)))
    }
    
    @Test def monoidCombineNested(): Unit = {
        assertEquals(d <:> e <:> f, Some(List(12, 13, 4, 5, 5, 6, 6, 7, 15, 16, 7, 8, 8, 9, 9, 10, 18, 19, 10, 11, 11, 12, 12, 13)))
    }

    @Test def multMonoidMult(): Unit = {
       
        import architect.algebra.MultiplicativeMonoid.Ops._
        
        assertEquals(a |*| b |*| c, Some(Coordinates(-4,25)))
    }

    @Test def multMonoidMultNested(): Unit = {
       
        import architect.algebra.MultiplicativeMonoid.Ops._
        
        assertEquals(d |*| e |*| f, Some(List(18, 36, 2, 4, 4, 8, 6, 12, 45, 90, 5, 10, 10, 20, 15, 30, 72, 144, 8, 16, 16, 32, 24, 48)))
    }

    @Test def addMonoidAdd(): Unit = {

        import architect.algebra.AdditiveMonoid.Ops._

        assertEquals(a |+| b |+| c, Some(Coordinates(0,10)))
    }

    @Test def addMonoidAddNested(): Unit = {

    import architect.algebra.AdditiveMonoid.Ops._

        assertEquals(d |+| e |+| f, Some(List(12, 13, 4, 5, 5, 6, 6, 7, 15, 16, 7, 8, 8, 9, 9, 10, 18, 19, 10, 11, 11, 12, 12, 13)))
    }

    @Test def monoidKCombine(): Unit = {
        assertEquals(a <> b <> c, Some(Coordinates(2,5)))
    }
    
    @Test def monoidKCombineNested(): Unit = {
        assertEquals(d <> e <> f, Some(List(2, 5, 8)))
    }
    
    @Test def monoidKCombineMix(): Unit = {
        assertEquals(g <> h <> i, Some("Hello"))
    }
}