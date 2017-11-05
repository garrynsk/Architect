
package architect
package bricks

import Binary._

trait AdditiveInverse[A] {

    def negation(a: A): A 

    def leftNegation(binary: Binary[A])(a: A): Boolean =
        binary(negation(a), a) == binary(a, negation(a))
    
    def rightNegation(binary: Binary[A])(a: A): Boolean =
        binary(negation(a), a) == binary(a, negation(a))
}

object AdditiveInverse {

    trait Ops[A] {

        def typeClassInstance: AdditiveInverse[A]
        def target: A 

    }
    
}