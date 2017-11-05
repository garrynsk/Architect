
package architect
package algebra.stones

import bricks.Binary._
import bricks.Commutative

trait SemiringComponent[A] extends AdditiveMonoidComponent[A] with MultiplicativeMonoidComponent[A] with Commutative[A]{
    
    def annihilation(a: A): Boolean =
        mult(zero, a) == mult(a, zero) && mult(zero, a) == zero

}

object SemiringComponent {

    trait Ops[A] extends AdditiveMonoidComponent.Ops[A] with MultiplicativeMonoidComponent.Ops[A] {

        def typeClassInstance: SemiringComponent[A]
        def target: A
        
    }
    
}
