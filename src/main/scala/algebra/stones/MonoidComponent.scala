
package architect
package algebra.stones

import neocat.rocks.CategoryComponent
import bricks.IdentityK
import neocat.{ Category, Semicategory }
import algebra.Semigroup

trait MonoidComponent[A] extends SemigroupComponent[A] with CategoryComponent[A] { self =>
    
    def id: A

    override def semicategory: Semicategory[A] = new Semicategory{}

    def semigroup: Semigroup[A] = new Semigroup{

        override def combine(a: A, b: A): A = self.combine(a, b)

    }

    def category: Category[A] = new Category{

        override def id(a: A): A = self.id(a)

    }
}

object MonoidComponent {

    trait Ops[A] extends SemigroupComponent.Ops[A] with CategoryComponent.Ops[A] {

        def typeClassInstance: MonoidComponent[A]
        def target: A
        
    }
    
}

trait MonoidKComponent[F[_]] extends SemigroupKComponent[F] with IdentityK[F]{

    def id[A]: F[A]
    
}

object MonoidKComponent {

    trait Ops[F[_],A] extends SemigroupKComponent.Ops[F,A] with IdentityK.Ops[F,A] {

        def typeClassInstance: MonoidKComponent[F]
        def target: F[A]

    }
}