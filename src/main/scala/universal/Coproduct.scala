package architect
package universal

case class Coproduct[A](override val carrier: A) extends Mu[A, Cozero] { self =>
    override val functor: Cozero = Cozero
}

trait Cozero extends Empty
case object Cozero extends Cozero 