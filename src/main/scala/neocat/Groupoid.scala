
package architect
package neocat

import neocat.rocks.GroupoidComponent
import annotation.implicitNotFound

/**
    A groupoid can be seen as a:

    - Group with a partial function replacing the binary operation;
    - Category in which every morphism is invertible.
    
    Here we implement a category definition.

    Algebraic Definition

        A groupoid is a set G with a unary operation inverse: G -> G, and a partial function *:G * G. 
        Here * is not a binary operation because it is not necessarily defined for all possible pairs of G-elements. 

        * and inverse have the following axiomatic properties. 
        Let a, b, and c be elements of G. Then:

            (associativity) (a * b) * c = a * (b * c)
            (inverse) inverse(a) * a = id and a * inverse(a) = id are always defined.

    Category theoretic Definition

        A groupoid is a small category in which every morphism is an isomorphism
        
        - Instances must satisfy the category laws:

            (associativity of functions) if f : a → b, g : b → c and h : c → d then h <<< (g <<< f) = (h <<< g) <<< f
            (identity of functions) for every object x, there exists a morphism id : x → x, such that 
                for every morphism f : a → x and every morphism g : x → b, we have id <<< f = f and g <<< id = g.

        - And an additional law. For any f : a → b:

            (inverse of functions) inverse(f) * id = id * inverse(f) = inverse(f).

*/

@implicitNotFound("A value of type Groupoid[${A}] cannot be found")
trait Groupoid[A] extends GroupoidComponent[A]

object Groupoid {

    def apply[A](implicit instance: Groupoid[A]): Groupoid[A] = instance

    object Ops {
        
        implicit class toGroupoidObject[A](value: A)(implicit tc: Groupoid[A]) extends GroupoidComponent.Ops[A] { 

            override def typeClassInstance = tc
            override def target = value
        }
    }
}

/**
    In geometry, bounding boxes (represented as two points - bottom-left corner and top-right corner) 
    give an example where a groupoid may be more satisfying than a monoid. 
    The union operation on bounding boxes is essential to track the extent of shapes after their superimposition. 
    To fit bounding box union into the Monoid typeclass one can do a clever trick 
    representing mempty with the bottom-left corner at positive infinity and the top-right corner at negative infinity, 
    the standard implementation of union which uses min and max will still proceed to identify 
    the extreme corners correctly. This is nice enough if the bounding box coordinates are represented by Doubles, 
    but a problem if they are Ints (say representing grid coordinates) - one might decide it is better 
    simply to consider concrete bounding boxes and not their empty/infinite cousins.

    http://hackage.haskell.org/package/groupoid
*/