package architect
package universal

trait FAlgebra

trait Empty extends FAlgebra {}

trait Mu[A, B <: FAlgebra] extends FAlgebra{ self =>
    val carrier: A
    val functor: B
    
}
object Mu{
    def apply[A, B <: FAlgebra](head: A, tail: B): Mu[A, B] = new Mu{
        val carrier: A = head
        val functor: B = tail
    }
}