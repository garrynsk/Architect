
package architect
package algebra.stones

import bricks.Binary._
import bricks.{ MultiplicativeIdentity, MultiplicativeOperation, CompositionK, IdentityK, MultiplicativeIdentityK, MultiplicativeOperationK }
import neocat.rocks.CategoryComponent
import algebra.{ Semigroup, Monoid }

trait MultiplicativeMonoidComponent[A] extends CategoryComponent[A] with MultiplicativeIdentity[A] with MultiplicativeOperation[A]{ self =>

    def multSemigroup: Semigroup[A] = new Semigroup{

        override def combine(a: A, b: A): A = self.mult(a, b)

    }

    def multMonoid: Monoid[A] = new Monoid {

        override def id: A = self.one

        override def id(a: A): A = self.id(a)

        override def combine(a: A, b: A): A = self.mult(a, b)
        
    }

}

object MultiplicativeMonoidComponent {

    trait Ops[A] extends CategoryComponent.Ops[A] with MultiplicativeIdentity.Ops[A] with MultiplicativeOperation.Ops[A] {

        def typeClassInstance: MultiplicativeMonoidComponent[A]
        def target: A

    }
    
}

trait MultiplicativeMonoidKComponent[F[_]] extends CompositionK[F] with IdentityK[F] with MultiplicativeIdentityK[F] with MultiplicativeOperationK[F]

object MultiplicativeMonoidKComponent {

    trait Ops[F[_],A] extends CompositionK.Ops[F,A] with IdentityK.Ops[F,A] with MultiplicativeIdentityK.Ops[F,A] with MultiplicativeOperationK.Ops[F,A]{

        def typeClassInstance: MultiplicativeMonoidKComponent[F]
        def target: F[A]

    }
}