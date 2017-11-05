
package architect
package bricks

import Binary._

trait Inverse[A] {

    def inverse(a: A): A 
    
    def leftInverse(binary: Binary[A])(a: A): Boolean =
        binary(inverse(a), a) == binary(a, inverse(a))
    
    def rightInverse(binary: Binary[A])(a: A): Boolean =
        binary(inverse(a), a) == binary(a, inverse(a))
}

object Inverse {

    trait Ops[A] {
        def typeClassInstance: Inverse[A]
        def target: A 
    }
    
}