
package architect
package instances

import algebra.{ Ring, Semiring, Monoid, MultiplicativeMonoid, AdditiveMonoid, Semigroup, Group, MultiplicativeGroup, AdditiveGroup, MonoidK }
import neocat.{ Category, Semicategory, Groupoid }

trait OptionInstances extends   OptionSemicategory with OptionCategory with OptionGroupoid with OptionSemigroup with 
                                OptionGroup with OptionRing with OptionMonoid with OptionMultMonoid with OptionAddMonoid
                                with OptionAddGroup with OptionMultGroup with OptionSemiring with OptionMonoidK
trait OptionSemigroup {

   import Semigroup.Ops._

    implicit def optionSemigroup[A: Semigroup]: Semigroup[Option[A]] = new Semigroup {

        override def combine(a: Option[A], b: Option[A]): Option[A] = 
            a match {
                case Some(x) => b match {
                    case Some(y) => Some(x <:> y)
                    case None => a
                }
                case None => b match {
                    case Some(y) => b
                    case None => None
                }
            }
    }
} 

trait OptionRing {

    import Ring.Ops._

    implicit def optionRing[A: Ring]: Ring[Option[A]] = new Ring[Option[A]] {
        
        override def negation(a: Option[A]): Option[A] = a.map(Ring[A].negation)  

        override def zero: Option[A] = Some(Ring[A].zero) 

        override def add(a: Option[A], b: Option[A]): Option[A] = a.map(x => x |+| b.getOrElse(Ring[A].zero)) 
      
        override def one: Option[A] = Some(Ring[A].one)

        override def mult(a: Option[A], b: Option[A]): Option[A] = a.map(x => x |*| b.getOrElse(Ring[A].one))   

    }
} 

trait OptionMultGroup {

    import MultiplicativeGroup.Ops._

    implicit def optionMultGroup[A: MultiplicativeGroup]: MultiplicativeGroup[Option[A]] = new MultiplicativeGroup[Option[A]] {
        
        override def reciprocal(a: Option[A]): Option[A] = a.map(MultiplicativeGroup[A].reciprocal)  

        override def one: Option[A] = Some(MultiplicativeGroup[A].one)

        override def mult(a: Option[A], b: Option[A]): Option[A] = a.map(x => x |*| b.getOrElse(MultiplicativeGroup[A].one))   

    }
} 

trait OptionAddGroup {

    import AdditiveGroup.Ops._

    implicit def optionAddGroup[A: AdditiveGroup]: AdditiveGroup[Option[A]] = new AdditiveGroup[Option[A]] {
        
        override def negation(a: Option[A]): Option[A] = a.map(AdditiveGroup[A].negation)  

        override def zero: Option[A] = Some(AdditiveGroup[A].zero) 

        override def add(a: Option[A], b: Option[A]): Option[A] = a.map(x => x |+| b.getOrElse(AdditiveGroup[A].zero))  

    }
} 

trait OptionSemiring {

    import Semiring.Ops._

    implicit def optionSemiring[A: Semiring]: Semiring[Option[A]] = new Semiring[Option[A]] {
        
        override def zero: Option[A] = Some(Semiring[A].zero) 

        override def add(a: Option[A], b: Option[A]): Option[A] = a.map(x => x |+| b.getOrElse(Semiring[A].zero)) 
      
        override def one: Option[A] = Some(Semiring[A].one)

        override def mult(a: Option[A], b: Option[A]): Option[A] = a.map(x => x |*| b.getOrElse(Semiring[A].one))   

    }
}

trait OptionGroup  {

    import Group.Ops._

    implicit def optionGroup[A: Group]: Group[Option[A]] = new Group[Option[A]] {

        override def inverse(a: Option[A]): Option[A] = a.map(Group[A].inverse)  
        
        override def id: Option[A] = Some(Group[A].id)

        override def combine(a: Option[A], b: Option[A]): Option[A] = a.map(x => x <:> b.getOrElse(Group[A].id)) 

    }
}

trait OptionMonoid  {

    import Monoid.Ops._

    implicit def optionMonoid[A: Monoid]: Monoid[Option[A]] = new Monoid[Option[A]] {
        
        override def id: Option[A] = Some(Monoid[A].id)

        override def combine(a: Option[A], b: Option[A]): Option[A] = a.map(x => x <:> b.getOrElse(Monoid[A].id)) 

    }
}

trait OptionAddMonoid  {

    import AdditiveMonoid.Ops._

    implicit def optionAddMonoid[A: AdditiveMonoid]: AdditiveMonoid[Option[A]] = new AdditiveMonoid[Option[A]] {
        
        override def zero: Option[A] = Some(AdditiveMonoid[A].zero)

        override def add(a: Option[A], b: Option[A]): Option[A] = a.map(x => x |+| b.getOrElse(AdditiveMonoid[A].zero)) 

    }
}

trait OptionMultMonoid  {

    import MultiplicativeMonoid.Ops._

    implicit def optionMultMonoid[A: MultiplicativeMonoid]: MultiplicativeMonoid[Option[A]] = new MultiplicativeMonoid[Option[A]] {
        
        override def one: Option[A] = Some(MultiplicativeMonoid[A].one) 

        override def mult(a: Option[A], b: Option[A]): Option[A] = a.map(x => x |*| b.getOrElse(MultiplicativeMonoid[A].one)) 

    }
}

trait OptionGroupoid {

    import Groupoid.Ops._

    implicit def optionGroupoid[A: Groupoid]: Groupoid[Option[A]] = new Groupoid[Option[A]] {

        override def inverse(a: Option[A]): Option[A] = a.map(Groupoid[A].inverse)  

    }
}

trait OptionCategory {

    implicit def optionCategory[A]: Category[Option[A]] = new Category[Option[A]] {}
}

trait OptionSemicategory {

    implicit def optionSemicategory[A]: Semicategory[Option[A]] = new Semicategory[Option[A]] {}
}


trait OptionMonoidK {

    import MonoidK.Ops._

    implicit def optionMonoidK: MonoidK[Option] = new MonoidK[Option] {

        override def combine[A](a: Option[A], b: Option[A]): Option[A] = a orElse b

        override def id[A]: Option[A] = None

    }
}
