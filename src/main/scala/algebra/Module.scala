
package architect
package algebra

import annotation.implicitNotFound
// TODO: In developing
/**
    A module M consists of an abelian group (M, |+|) and an operation |*|: R × M → M such that for all r, s in R and x, y in M, we have:

        r |*| (x |+| y) = (r |*| x) |+| (r |*| y)
        (r |+| s) |*| x = (r |*| x) |+| (s |*| x)
        (r |*| s) |*| x = r |*| (s |*| x)
        one |*| x = x
*/
/**
@implicitNotFound("Values of types ${A} cannot be found")
trait Module[A <: AdditiveGroup, B <: Ring] extends Ring[_] with AdditiveGroup[_]

object Module {

    def apply[A <: AdditiveGroup, B <: Ring](implicit instance: Module[A,B]): Module[A,B] = instance

    trait Ops extends Ring.Ops with AdditiveGroup.Ops

}*/
