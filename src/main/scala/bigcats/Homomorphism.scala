
package architect
package bigcats

import bricks.Binary._
import algebra.{ Group, Monoid, Semigroup }
/**

    In algebra, a homomorphism is a structure-preserving map between two algebraic structures of the same type 
    (such as two groups, two rings, or two vector spaces).
    Homomorphism must preserve the operation of the structure.

    f(a*b) = f(a) x f(b) 

*/

trait GroupHomomorphism[A: {type λ[α] = Group[α]}#λ] {
    
    def kernel[B: Group](set: Set[A])(f: A => B)(g: A => B): Set[A] = 
        set.foldRight(Set(Group[A].id))(
            (x,acc) => 
                if (f(x) == g(x)){
                    acc + x
                }
                else{
                    acc
                })
    
    def cokernel[B: Group](set: Set[A])(f: A => B)(g: A => B): Set[B] = 
        set.foldRight(Set(Group[B].id))(
            (x,acc) => 
                if (f(x) == g(x)){
                    acc + f(x)
                }
                else{
                    acc
                })

    def preserveInverse[B: Group](a: A, b: B)(f: A => B): Boolean =
        f(Group[A].inverse(a)) == Group[B].inverse(b)

    def preserveId[B: Group](f: A => B): Boolean =
        f(Group[A].id) == Group[B].id

    def preserveOperation[B: Group](a: A, b: A)(f: A => B): Boolean =
        f(Group[A].combine(a,b)) == Group[B].combine(f(a),f(b))

}

trait MonoidHomomorphism {
    
    def preserveId[A: Monoid, B: Monoid](f: A => B): Boolean =
        f(Monoid[A].id) == Monoid[B].id

    def preserveOperation[A: Monoid, B: Monoid](a: A, b: A)(f: A => B): Boolean =
        f(Monoid[A].combine(a,b)) == Monoid[B].combine(f(a),f(b))

}

trait SemigroupHomomorphism {
    
    def preserveOperation[A: Semigroup, B: Semigroup](a: A, b: A)(f: A => B): Boolean =
        f(Semigroup[A].combine(a,b)) == Semigroup[B].combine(f(a),f(b))
        
}