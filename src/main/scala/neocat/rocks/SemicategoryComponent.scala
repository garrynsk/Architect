package architect
package neocat.rocks

import bricks.{ Composition, Associative }

trait SemicategoryComponent[A] extends Composition[A] with Associative[A] 

object SemicategoryComponent {

    trait Ops[A] extends Composition.Ops[A] with Associative.Ops[A] {

        def typeClassInstance: SemicategoryComponent[A]
        def target: A
        
    }
    
}
