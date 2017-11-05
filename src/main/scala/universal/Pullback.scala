
package architect
package universal

import functors.Functor
import algebra.Monoid

import annotation.implicitNotFound


trait Pullback[F[_]: Functor ] {

    /**
        The notion of a pullback is a generalization of both intersection and inverse image.
        It is the limit of a diagram consisting of two morphisms f : X → Z and g : Y → Z with a common codomain. The pullback is often written
            P = X ×Z Y
        and comes equipped with two natural morphisms P → X and P → Y.
    */
    def pullback[A,B,C](fa: F[A], fb: F[B])(f: A => C)(g: B => C): F[(A,B)]
    /**
        A pushout is the colimit of a diagram consisting of two morphisms f : Z → X and g : Z → Y with a common domain. 
        The pushout consists of an object P along with two morphisms X → P and Y → P 
        that complete a commutative square with the two given morphisms f and g. 
        A common notation for the pushout is P=X +Z Y
    */
    def pushout[A,B,C](fa: F[C])(f: C => B)(g: C => A): (F[A],F[B])
}

object Pullback {
    
    def apply[F[_]](implicit instance: Pullback[F]): Pullback[F] = instance
}

