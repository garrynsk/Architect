
package architect
package bricks

import Binary._

trait MultiplicativeInverse[A] {

    def reciprocal(a: A): A 
    
    def leftReciprocal(binary: Binary[A])(a: A): Boolean =
        binary(reciprocal(a), a) == binary(a, reciprocal(a))
    
    def rightReciprocal(binary: Binary[A])(a: A): Boolean =
        binary(reciprocal(a), a) == binary(a, reciprocal(a))

}

object MultiplicativeInverse {

    trait Ops[A] {

        def typeClassInstance: MultiplicativeInverse[A]
        def target: A

    }
}