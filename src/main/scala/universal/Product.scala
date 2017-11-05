package architect
package universal

/**
    In the strict sense of the word, a cartesian product is a product in Set, the category of sets. 
    Hence for S1 and S2 two sets, their cartesian product is the set denoted S1×S2 
    whose elements are ordered pairs of elements in S1 and S2, respectively.

    For sets A and B, the Cartesian product A × B is the set of all ordered pairs (a, b) where a ∈ A and b ∈ B.
*/
case class Product[A, B <: FAlgebra](override val carrier: A, override val functor: B) extends Mu[A, B] { self =>
    def :*:[C](value: C) : Product[C, Product[A, B]] = Product(value, self)
}
trait Zero extends Empty
case object Zero extends Zero { self =>
    def :*:[A](value: A) : Product[A, Zero] = Product(value, self)
}
