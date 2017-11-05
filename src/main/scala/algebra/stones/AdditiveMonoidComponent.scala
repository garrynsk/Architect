
package architect
package algebra.stones

import bricks.Binary._
import bricks.{ AdditiveOperation, AdditiveIdentity, CompositionK, IdentityK, AdditiveIdentityK, AdditiveOperationK }
import neocat.rocks.CategoryComponent
import algebra.{ Monoid, Semigroup }

trait AdditiveMonoidComponent[A] extends CategoryComponent[A] with AdditiveIdentity[A] with AdditiveOperation[A] { self =>
    
    def addSemigroup: Semigroup[A] = new Semigroup{

        override def combine(a: A, b: A): A = add(a, b)

    }

    def addMonoid: Monoid[A] = new Monoid {

        override def id: A = self.zero

        override def id(a: A): A = self.id(a)

        override def combine(a: A, b: A): A = self.add(a, b)
        
    }

}

object AdditiveMonoidComponent {

    trait Ops[A] extends CategoryComponent.Ops[A] with AdditiveIdentity.Ops[A] with AdditiveOperation.Ops[A] {

        def typeClassInstance: AdditiveMonoidComponent[A]
        def target: A

    }
    
}


trait AdditiveMonoidKComponent[F[_]] extends CompositionK[F] with IdentityK[F] with AdditiveIdentityK[F] with AdditiveOperationK[F]

object AdditiveMonoidKComponent {

    trait Ops[F[_],A] extends CompositionK.Ops[F,A] with IdentityK.Ops[F,A] with AdditiveIdentityK.Ops[F,A] with AdditiveOperationK.Ops[F,A]{

        def typeClassInstance: AdditiveMonoidKComponent[F]
        def target: F[A]

    }
}