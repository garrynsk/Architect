
package architect
package algebra.stones

trait RingComponent[A] extends SemiringComponent[A] with AdditiveGroupComponent[A]

object RingComponent {

    trait Ops[A] extends SemiringComponent.Ops[A] with AdditiveGroupComponent.Ops[A]{

        def typeClassInstance: RingComponent[A]
        def target: A
   
    }
}
