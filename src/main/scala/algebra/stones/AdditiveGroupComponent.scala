
package architect
package algebra.stones

import bricks.AdditiveInverse
import neocat.Groupoid
import algebra.{ AdditiveMonoid, Group }

trait AdditiveGroupComponent[A] extends AdditiveInverse[A] with AdditiveMonoidComponent[A] { self =>

    def substract(a: A, b: A): A = add(a, negation(b))

    def groupoid: Groupoid[A] = new Groupoid{

        override def id(a: A): A = self.id(a)

        override def inverse(a: A): A = self.negation(a)

    }

    def additiveMonoid: AdditiveMonoid[A] = new AdditiveMonoid{

        override def zero: A = self.zero

        override def id(a: A): A = self.id(a)

        override def add(a: A, b: A): A = self.add(a, b)

    }

    def addGroup: Group[A] = new Group{

        override def id: A = self.zero

        override def id(a: A): A = self.id(a)

        override def inverse(a: A): A = self.negation(a)

        override def combine(a: A, b: A): A = self.add(a, b)

    }
    
}

object AdditiveGroupComponent {

    trait Ops[A] extends AdditiveInverse.Ops[A] with AdditiveMonoidComponent.Ops[A] {

        def typeClassInstance: AdditiveGroupComponent[A]
        def target: A
        
        // An alias for the operation 'substract'
        def |-|(b: A): A = typeClassInstance.substract(target,b)
        
    }
    
}
