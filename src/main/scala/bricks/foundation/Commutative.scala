
package architect
package bricks

import Binary._

trait Commutative[A] {

     def commutative(binary: Binary[A])(a: A, b: A): Boolean = 
        binary(a, b) == binary(b, a)

}

object Commutative {
  
    trait Ops[A] {
        def typeClassInstance: Commutative[A]
        def target: A 
    }
    
}