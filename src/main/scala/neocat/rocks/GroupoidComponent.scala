package architect
package neocat.rocks

import neocat.Category
import bricks.Inverse

trait GroupoidComponent[A] extends CategoryComponent[A] with Inverse[A] { self =>

    def category: Category[A] = new Category{

        override def id(a: A): A = self.id(a)

    }

}

object GroupoidComponent {

    trait Ops[A] extends CategoryComponent.Ops[A] with Inverse.Ops[A] {

        def typeClassInstance: GroupoidComponent[A]
        def target: A

    }
    
}
