
package architect
package bricks

import Binary._

trait AdditiveIdentity[A] {
    
    def zero: A

    def zeroLeft(add: Binary[A])(a: A): Boolean = 
        add(zero, a) == a

    def zeroRight(add: Binary[A])(a: A): Boolean = 
        add(a, zero) == add(zero, a)

    def zeroIdentity(add: Binary[A])(a: A): Boolean =
        zeroLeft(add)(a) == zeroRight(add)(a)
        
}

object AdditiveIdentity {

    trait Ops[A] {
        def typeClassInstance: AdditiveIdentity[A]
        def target: A 
    }
    
}

trait AdditiveIdentityK[F[_]] {
    
    def zero[A]: F[A]

    def zeroLeft[A](add: Binary[F[A]])(a: F[A]): Boolean = 
        add(zero, a) == a

    def zeroRight[A](add: Binary[F[A]])(a: F[A]): Boolean = 
        add(a, zero) == add(zero, a)

    def zeroIdentity[A](add: Binary[F[A]])(a: F[A]): Boolean =
        zeroLeft(add)(a) == zeroRight(add)(a)
        
}

object AdditiveIdentityK {

    trait Ops[F[_], A] {
        def typeClassInstance: AdditiveIdentityK[F]
        def target: F[A] 
    }
    
}