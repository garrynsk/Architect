package architect
package algebra

import algebra.stones.MonoidComponent
import annotation.implicitNotFound

/**
    In abstract algebra a monoid is an algebraic structure with a single associative binary operation 
    and an identity element. So a monoid is a category with one object, they are also semigroups with identity. 
    
    - Instances are required to satisfy the category laws:

        (associativity of functions) if f : a → b, g : b → c and h : c → d then h <<< (g <<< f) = (h <<< g) <<< f

        (identity of functions) for every object x, there exists a morphism id : x → x, such that 
            for every morphism f : a → x and every morphism g : x → b, we have id <<< f = f and g <<< id = g.

    - The semigroup law:

        (associativity) (x <:> y) <:> z = x <:> (y <:> z)

    - And the additional law:

        (identity) id <:> x = x <:> id = x
*/
 

@implicitNotFound("A value of type Monoid[${A}] cannot be found")
trait Monoid[A] extends MonoidComponent[A] 
object Monoid {

    def apply[A](implicit instance: Monoid[A]): Monoid[A] = instance

    object Ops {
        
        implicit class toMonoidObject[A](value: A)(implicit tc: Monoid[A]) extends MonoidComponent.Ops[A]  { 
            override val typeClassInstance = tc
            override val target = value
            
        }

    }
    
}

/**
    A very powerful application of monoids are 2-3 finger trees, first described by Ralf Hinze and Ross Patterson.
    Basically, they allow you to write fast implementations for pretty much every abstract data type 
    mentioned in Okasaki’s book on purely functional data structures. 
    For example, you can do sequences, priority queues, search trees and priority search queues. 
    Moreover, any fancy and custom data structures like interval trees or something 
    for stock trading are likely to be implementable in this framework as well.
    https://www.codementor.io/haskell/tutorial/monoids-fingertrees-implement-abstract-data

    http://fsharpforfunandprofit.com/posts/monoids-without-tears/
*/