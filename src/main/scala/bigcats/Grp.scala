
package architect
package bigcats

import algebra.Group
import annotation.implicitNotFound
// TODO: In developing
/**
object Grp extends Category[Group[_]] with Homomorphism[Group] with Invariant[Group] {
    
    def id[A](a: Group[A]): Group[A] = a

    override def xmap[A, B](ma: Group[A], f: A => B, g: B => A): Group[B] =
        new Group{
            override def combine(a: B, b: B): B = f(ma.combine(g(a),g(b)))
            override def id: B = f(ma.id)  
            override def inverse(a: B): B = f(ma.inverse(g(a)))
        }

    trait Ops extends  Homomorphism.Ops with Invariant.Ops

}
*/