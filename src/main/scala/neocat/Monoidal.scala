
package architect
package neocat


import annotation.implicitNotFound
// TODO: In developing
/**
@implicitNotFound("Values of types ${A} cannot be found")
trait Monoidal[A] extends Category[A] with Bifunctor[{type λ[α, β]= Product[α, β]}#λ]{ self =>
    def associator( x: A, y: A, z: A): {type λ[α]= Product[Product[α, A],A]}#λ ~> {type λ[α]= Product[Product[α, A],A]}#λ
}

object Monoidal {

    def apply[A](implicit instance: Monoidal[A]): Monoidal[A] = instance

    trait Ops 
}
*/