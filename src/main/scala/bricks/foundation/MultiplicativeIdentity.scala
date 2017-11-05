
package architect
package bricks

import Binary._

trait MultiplicativeIdentity[A] {
    
    def one: A

    def oneLeft(mult: Binary[A])(a: A): Boolean = 
        mult(one, a) == a

    def oneRight(mult: Binary[A])(a: A): Boolean = 
        mult(a, one) == mult(one, a)

    def oneIdentity(mult: Binary[A])(a: A): Boolean =
        oneLeft(mult)(a) == oneRight(mult)(a)
            
}

object MultiplicativeIdentity {

    trait Ops[A] {
        def typeClassInstance: MultiplicativeIdentity[A]
        def target: A 
    }
    
}

trait MultiplicativeIdentityK[F[_]] {
    
    def one[A]: F[A]

    def oneLeft[A](mult: Binary[F[A]])(a: F[A]): Boolean = 
        mult(one, a) == a

    def oneRight[A](mult: Binary[F[A]])(a: F[A]): Boolean = 
        mult(a, one) == mult(one, a)

    def oneIdentity[A](mult: Binary[F[A]])(a: F[A]): Boolean =
        oneLeft(mult)(a) == oneRight(mult)(a)
            
}

object MultiplicativeIdentityK {
   
    trait Ops[F[_],A] {
        def typeClassInstance: MultiplicativeIdentityK[F]
        def target: F[A] 
    }
    
}