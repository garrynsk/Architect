
package architect
package instances

import algebra.{ Ring, Monoid, MultiplicativeMonoid, MonoidK }
import neocat.Groupoid 
import neocat.Perhaps
/**
object ReachBoolean extends MonoidK.Ops with Ring.Ops with Monoid.Ops with MultiplicativeMonoid.Ops with Groupoid.Ops {

    implicit def booleanRing[A](implicit monoid: Monoid[A] = null, 
                                        multMonoid: MultiplicativeMonoid[A] = null, 
                                        groupoid: Groupoid[A] = null): Ring[Option[A]] = new Ring[Option[A]] {
        
        // We use id method here as it is equal to zero by default
        override def zero: Option[A] = Perhaps.fold[Monoid[A], Option[A]]{ Some(Monoid[A].id) }{ None }

        override def add(a: Option[A], b: Option[A]): Option[A] = Perhaps.fold[Monoid[A], Option[A]]{ 
        // We use combine method here as it is equal to add by default
                a.map(x => x <:> b.getOrElse(Monoid[A].id)) 
            }{ 
                a orElse b 
            } 

        override def one: Option[A] = Perhaps.fold[MultiplicativeMonoid[A], Option[A]]{
                Some(MultiplicativeMonoid[A].one) 
            }{
                None
            }   

        override def mult(a: Option[A], b: Option[A]): Option[A] = Perhaps.fold[MultiplicativeMonoid[A], Option[A]]{
                a.map(x => x |*| b.getOrElse(MultiplicativeMonoid[A].one)) 
            }{
                a orElse b
            }  

        override def inverse(a: Option[A]): Option[A]  = Perhaps.fold[MultiplicativeMonoid[A], Option[A]]{
                a.map(x => Groupoid[A].inverse(x)) 
            }{
                None // TODO: check
            }  
    }

    implicit def reachOptionK: MonoidK[Option] = new MonoidK[Option] {

        override def combine[A](a: Option[A], b: Option[A]): Option[A] = a orElse b

        override def id[A]: Option[A] = None

    }
}
*/