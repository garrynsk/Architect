
package architect
package algebra.stones

import neocat.rocks.GroupoidComponent
import neocat.{ Category, Groupoid }
import algebra.Monoid

trait GroupComponent[A] extends GroupoidComponent[A] with MonoidComponent[A] { self =>

    def remove(a: A, b: A): A = combine(a, inverse(b))
    
    override def category: Category[A] = new Category{

        override def id(a: A): A = self.id(a)

    }

    def groupoid: Groupoid[A] = new Groupoid{

        override def id(a: A): A = self.id(a)

        override def inverse(a: A): A = self.inverse(a)

    }

    def monoid: Monoid[A] = new Monoid{

        override def id: A = self.id

        override def id(a: A): A = self.id(a)

        override def combine(a: A, b: A): A = self.combine(a, b)

    }
}

object GroupComponent {

    trait Ops[A] extends GroupoidComponent.Ops[A] with MonoidComponent.Ops[A] {

        def typeClassInstance: GroupComponent[A]
        def target: A

    }
    
}
