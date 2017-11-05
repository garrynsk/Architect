package architect
package functors

import functors.glue.CorepresentableComponent
import annotation.implicitNotFound

// https://bartoszmilewski.com/2015/07/29/representable-functors/
/** 
    A representable functor is a functor of a special form from an arbitrary category into the category of sets.
    Such functors give representations of an abstract category in terms of known structures (i.e. sets and functions) 
    allowing one to utilize, as much as possible, knowledge about the category of sets in other settings.
    Any functor F that is naturally isomorphic to the Hom(A,–) for some object A of C, is called representable. 

    All typeclasses fixed in A, can be representables.
 */
@implicitNotFound("Values of type ${F} cannot be found")
trait Corepresentable[G[_,_], F[_], A] extends CorepresentableComponent[G, F, A]

object Corepresentable {

    def apply[G[_,_], F[_], A](implicit instance: Corepresentable[G, F, A]): Corepresentable[G, F, A] = instance

    object Ops {
 
        implicit class toCorepresentable[G[_,_], F[_], A](self: F[A])(implicit tc: Corepresentable[G, F, A]) extends CorepresentableComponent.Ops[G, F, A] { 
            override val typeClassInstance = tc
            override val target = self
        }
    }
}