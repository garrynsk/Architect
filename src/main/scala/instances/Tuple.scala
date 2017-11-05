package architect
package instances

import functors.Bifunctor

trait TupleInstances 

trait TupleBifunctor {

   import Bifunctor.Ops._

    implicit def tupleBifunctor: Bifunctor[Tuple2] = new Bifunctor {  

        override def bimap[A, B, C, D](fab: Tuple2[A,B])(f: A => C, g: B => D): Tuple2[C,D] =
            (f(fab._1),g(fab._2))
    }
} 
