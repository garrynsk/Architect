
package architect
package bricks

import Binary._

trait MultiplicativeOperation[A] {
    
    def mult(a: A, b: A): A
     
}

object MultiplicativeOperation {
 
    trait Ops[A] {

        def typeClassInstance: MultiplicativeOperation[A]
        def target: A 

            // An alias for the operation 'mult'
            def |*|(b: A): A = typeClassInstance.mult(target, b)

    }
    
}

trait MultiplicativeOperationK[F[_]] {
    
    def mult[A](a: F[A], b: F[A]): F[A]
            
}

object MultiplicativeOperationK {
 
    trait Ops[F[_], A] {

        def typeClassInstance: MultiplicativeOperationK[F]
        def target: F[A] 

            // An alias for the operation 'mult'
            def <*>(b: F[A]): F[A] = typeClassInstance.mult(target, b)

        
    }
    
}