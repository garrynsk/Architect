
package architect
package algebra.stones

import bricks.MultiplicativeInverse
import neocat.Groupoid
import algebra.{ MultiplicativeMonoid, Group }

trait MultiplicativeGroupComponent[A] extends MultiplicativeInverse[A] with MultiplicativeMonoidComponent[A] { self =>

    def divide(a: A, b: A): A = mult(a, reciprocal(b))
    
    def multGroupoid: Groupoid[A] = new Groupoid{

        override def id(a: A): A = self.id(a)

        override def inverse(a: A): A = self.reciprocal(a)

    }

    def multiplicativeMonoid: MultiplicativeMonoid[A] = new MultiplicativeMonoid{

        override def one: A = self.one

        override def id(a: A): A = self.id(a)

        override def mult(a: A, b: A): A = self.mult(a, b)

    }

    def multGroup: Group[A] = new Group{

        override def id: A = self.one

        override def id(a: A): A = self.id(a)

        override def inverse(a: A): A = self.reciprocal(a)

        override def combine(a: A, b: A): A = self.mult(a, b)

    }
}

object MultiplicativeGroupComponent {

    trait Ops[A] extends MultiplicativeInverse.Ops[A] with MultiplicativeMonoidComponent.Ops[A] {

        def typeClassInstance: MultiplicativeGroupComponent[A]
        def target: A
        
        // An alias for the operation 'divide'
        def |/|(b: A): A = typeClassInstance.divide(target,b)

    }
    
}
