
package architect
package bigcats

import algebra.Monoid
import annotation.implicitNotFound
// TODO: In developing
/**
    For C a monoidal category, the category of monoids Mon(C) in the C is the category whose
    objects are monoids in C;
    morphisms are morphisms in C of the underlying objects that respect the monoid structure.
    https://ncatlab.org/nlab/show/category+of+monoids
*/
/**
@implicitNotFound("Values of types ${A} cannot be found")
trait Mon extends Category[Monoid[_]] with Homomorphism with Invariant[Monoid] { self =>
    def id[A](a: Monoid[A]): Monoid[A] = a


    // If there is an invertible morphism, which is not always true
    override def xmap[A, B](ma: Monoid[A], f: A => B, g: B => A): Monoid[B] =
        new Monoid{
            override def combine(a: B, b: B): B = f(ma.combine(g(a),g(b)))
            override def id: B = f(ma.id)  
        }

    trait Ops extends  Homomorphism.Ops with Invariant.Ops
}
*/