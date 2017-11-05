
package architect
package bricks

import Binary._

trait Operation[A] {
    
    def combine(a: A, b: A): A
     
}

object Operation {

    trait Ops[A] {

        def typeClassInstance: Operation[A]
        def target: A

            // An alias for the operation 'combine'
            def <:>(b: A): A = typeClassInstance.combine(target, b)

    }
}

trait OperationK[F[_]] {
    
    def combine[A](a: F[A], b: F[A]): F[A]
            
}

object OperationK {
    
    trait Ops[F[_], A] {

        def typeClassInstance: OperationK[F]
        def target: F[A]

            // An alias for the operation 'combine'
            def <>(b: F[A]): F[A] = typeClassInstance.combine(target, b)
            
    }
    
}