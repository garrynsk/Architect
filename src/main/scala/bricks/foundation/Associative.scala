
package architect
package bricks

import Binary._

trait Associative[A] {

    def associative(binary: Binary[A])(a: A, b: A, c: A): Boolean = 
        binary(binary(a, b), c) == binary(a, binary(b, c))

}

object Associative {

    trait Ops[A]{
        def typeClassInstance: Associative[A]
        def target: A 
    }
    
}

trait AssociativeK[F[_]] {

    def associative[A](binary: Binary[F[A]])(a: F[A], b: F[A], c: F[A]): Boolean = 
        binary(binary(a, b), c) == binary(a, binary(b, c))

}

object AssociativeK {

    trait Ops[F[_],A]{
        def typeClassInstance: AssociativeK[F]
        def target: F[A] 
    }
    
}