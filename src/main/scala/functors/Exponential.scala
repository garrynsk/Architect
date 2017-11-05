
package architect
package functors

import functors.glue.ExponentialComponent
import annotation.implicitNotFound

// TODO: Monad

/**
    In any cartesian closed category C, exponentiation by a fixed
    object A is a functor, (−)^A : C → C. It is just the representable functor Hom(A, −)

    Given some function β : B → C, we put β^A : B^A → C^A defined by f → β ◦ f. (Category Theory, Awodey §6.2 : Proposition 6.7)
    
    Let us consider a function of sets,
        f(x, y) : A × B → C
    written using variables x over A and y over B. If we now hold a ∈ A fixed, we have a function
        f(a, y) : B → C
    and thus an element f(a, y) ∈ C^B of the set of all such functions. (Category Theory, Awodey §6.1)
*/
@implicitNotFound("A value of type ExpFunctor[${F}] cannot be found")
trait Exponential[A] extends ExponentialComponent[A] 

object Exponential {

    def apply[A](implicit instance: Exponential[A]): Exponential[A] = instance
    
    object Ops {
 
        implicit class toExponential[A](value: A)(implicit tc: Exponential[A]) extends ExponentialComponent.Ops[A] { 
            override val typeClassInstance = tc
            override val target = value
        }
    }
}