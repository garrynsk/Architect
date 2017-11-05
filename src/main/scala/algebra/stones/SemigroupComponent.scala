
package architect
package algebra.stones

import bricks.{ Operation, CompositionK, AssociativeK, OperationK }
import neocat.rocks.SemicategoryComponent
import neocat.Semicategory

trait SemigroupComponent[A] extends SemicategoryComponent[A] with Operation[A] {

    def semicategory: Semicategory[A] = new Semicategory{}

}

object SemigroupComponent {

    trait Ops[A] extends SemicategoryComponent.Ops[A] with Operation.Ops[A]{

        def typeClassInstance: SemigroupComponent[A]
        def target: A

       
    }
    
}

trait SemigroupKComponent[F[_]] extends CompositionK[F] with AssociativeK[F] with OperationK[F]

object SemigroupKComponent {

    trait Ops[F[_],A] extends CompositionK.Ops[F,A] with AssociativeK.Ops[F,A] with OperationK.Ops[F,A] {

        def typeClassInstance: SemigroupKComponent[F]
        def target: F[A]

    }
}