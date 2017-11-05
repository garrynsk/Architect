
package architect
package universal

import functors.Functor
import algebra.Monoid

import annotation.implicitNotFound
trait Limit[F[_]] {

    def limit[A,B](fa: F[A])(f: F[A => B]): F[B]

    def colimit[A,B](fa: F[B])(f: F[A => B]): F[A]
}

object Limit {
    
    def apply[F[_]](implicit instance: Limit[F]): Limit[F] = instance
}

