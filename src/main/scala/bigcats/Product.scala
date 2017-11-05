
package architect
package bigcats

import annotation.implicitNotFound
// TODO: In developing
/**
    For C and D two categories, the product category C×D is the category whose
    objects are ordered pairs (c,d) (c,d) with c an object of C and d an object of D;
    morphisms are ordered pairs ((c→c'),(d→d')), as arrows from (c,d) to (c',d'),
    composition of morphisms is defined componentwise by composition in C and D:
    (f', g') o (f, g) = (f' o f, g' o g);
    This operation is the cartesian product in the 1-category Cat.
*/
//β
/**
trait Product[A,B] extends Category[(A,B)] with Cartesian[{type λ[α] = (α,B)}#λ]

object Product {

    def apply[A, B](implicit instance: Product[A,B]): Product[A,B] = instance

    trait Ops 
}*/