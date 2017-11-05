
package architect
package bricks

import Binary._

trait AdditiveOperation[A] {
    
    def add(a: A, b: A): A
     
}

object AdditiveOperation {

    trait Ops[A] {

        def typeClassInstance: AdditiveOperation[A]
        def target: A 

            // An alias for the operation 'add'
            def |+|(b: A): A = typeClassInstance.add(target, b)
    }
}

trait AdditiveOperationK[F[_]] {
    
    def add[A](a: F[A], b: F[A]): F[A]
            
}

object AdditiveOperationK {

    trait Ops[F[_], A] {

        def typeClassInstance: AdditiveOperationK[F]
        def target: F[A] 
        
            // An alias for the operation 'add'
            def <+>(b: F[A]): F[A] = typeClassInstance.add(target, b)

        
    }
    
}