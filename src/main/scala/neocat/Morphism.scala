
package architect
package neocat
// TODO: In developing
// Morphism refers to a structure-preserving map from one mathematical structure to another.
// To denote the hom-class of all morphisms from a to b we write Mor[A,B].
trait Mor[-T,+R]
object Mor{
    def apply[A,B](implicit instance: Mor[A,B]): Mor[A,B] = Mor[A,B](instance)

    trait Ops {
        implicit class MorOps[-T,+R](target: T => R)(implicit tc: Mor[T,R]){
            // The composition of morphisms
            def <<<[A](g: A => T): A => R = target compose g
            // The composition of morphisms flipped
            def >>>[A](g: R => A): T => A = target andThen g
        }
    }
}

// A category C consists of a class A of objects and a class Hom[A] of morphisms
trait Hom[+A] extends Mor[A,A] 
object Hom{
    def apply[A](implicit instance: Hom[A]): Hom[A] = Hom[A](instance)

    trait Ops extends Mor.Ops
}


//trait NaturalTransformation[A <: Functor[_],B <: Functor[_]] extends Mor[A,B]


object Arrows {
   
    implicit class arrowOps[C <: Map[A,B],A,B](input: C) { 

         def invertible(output: C): Map[A,B] = {
              (input epic output).toMap monic
         }
        // A function func: B -> C is monic if there is not two distinct inputs, which lead to the same output.
        // Monic will always produce distinct output for a Map
        //
        // eg: Map(1 -> "abc", 2 -> "abv", 3 -> "abc", 4 -> "abc") monic = Map(1 -> "abc", 2 -> "abv")
        def monic: Map[A,B] = input.groupBy((a,b) => b).mapValues(grouped => (grouped.keys.head,grouped.values.head)).toMap((key,value) => value)

        // A function func: B -> C is epic if every value from C is in its codomain.
        // Epic will return Map with values plus values from output
        //
        // eg: Map(1 -> "abc", 2 -> "abv", 3 -> "abc", 4 -> "abc") epic Map(2 -> "dff", 6 -> "abc") = 
        // = Map(1 -> "abc", 2 -> "abv", 3 -> "abc", 4 -> "abc", 5 -> "dff", 6 -> "abc")
        def epic(output: C): IndexedSeq[(A,B)] = {
            input.toIndexedSeq.union(output.toIndexedSeq)
        }

    }
   
}
 