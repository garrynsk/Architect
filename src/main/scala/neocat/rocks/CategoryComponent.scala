package architect
package neocat.rocks

import neocat.Semicategory
import bricks.Identity

trait CategoryComponent[A] extends SemicategoryComponent[A] with Identity[A] {

    def semicategory: Semicategory[A] = new Semicategory{}

}

object CategoryComponent {

    trait Ops[A] extends SemicategoryComponent.Ops[A] with Identity.Ops[A] {

        def typeClassInstance: CategoryComponent[A]
        def target: A
        
    }
    
}
