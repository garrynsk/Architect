package architect
package instances

import algebra.{ Ring, Semiring, Monoid, MultiplicativeMonoid, AdditiveMonoid, Semigroup, Group, MultiplicativeGroup, AdditiveGroup, MonoidK }
import neocat.{ Category, Semicategory, Groupoid }

trait ListInstances extends ListSemicategory with ListCategory with ListGroupoid with ListSemigroup with 
                            ListGroup with ListRing with ListMonoid with ListMultMonoid with ListAddMonoid
                            with ListAddGroup with ListMultGroup with ListSemiring

trait ListRing  {

    import Ring.Ops._

    implicit def listRing[A: Ring]: Ring[List[A]] = new Ring {
        
        override def negation(a: List[A]): List[A] = a.map(Ring[A].negation)                                    
        
        override def zero: List[A] = List(Ring[A].zero) 
        
        override def add(a: List[A], b: List[A]): List[A] =
            {       
               val list = List(a, b)

                list.foldLeft(zero){
                     (acc, item) => acc.flatMap(x => item.map(_ |+| x))
                    }.toSet.toList
            }
        override def one: List[A] = List(Ring[A].one)

        override def mult(a: List[A], b: List[A]): List[A] = 
            {   
                val list = List(a, b)

                list.foldLeft(one){
                     (acc, item) => acc.flatMap(x => item.map(_ |*| x))
                    }
            }
    }
}

trait ListMultGroup  {

    import MultiplicativeGroup.Ops._

    implicit def listMultGroup[A: MultiplicativeGroup]: MultiplicativeGroup[List[A]] = new MultiplicativeGroup {
        
        override def reciprocal(a: List[A]): List[A] = a.map(MultiplicativeGroup[A].reciprocal)                                      
        
        override def one: List[A] = List(MultiplicativeGroup[A].one)

        override def mult(a: List[A], b: List[A]): List[A] = 
            {   
                val list = List(a, b)

                list.foldLeft(one){
                     (acc, item) => acc.flatMap(x => item.map(_ |*| x))
                    }
            }
    }
}

trait ListAddGroup  {

    import AdditiveGroup.Ops._

    implicit def listAddGroup[A: AdditiveGroup]: AdditiveGroup[List[A]] = new AdditiveGroup {
        
        override def negation(a: List[A]): List[A] = a.map(AdditiveGroup[A].negation)                                      
        
        override def zero: List[A] = List(AdditiveGroup[A].zero) 
        
        override def add(a: List[A], b: List[A]): List[A] =
            {       
               val list = List(a, b)

                list.foldLeft(zero){
                     (acc, item) => acc.flatMap(x => item.map(_ |+| x))
                    }.toSet.toList
            }
    }
}

trait ListGroup  {

    import Group.Ops._

    implicit def listGroup[A: Group]: Group[List[A]] = new Group {
        
        override def inverse(a: List[A]): List[A] = a.map(Group[A].inverse)                                      
        
        override def id: List[A] = List(Group[A].id)

        override def combine(a: List[A], b: List[A]): List[A] = {       
               val list = List(a, b)

                list.foldLeft(id){
                     (acc, item) => acc.flatMap(x => item.map(_ <:> x))
                    }.toSet.toList
            } 
    }
}

trait ListSemiring  {

    import Semiring.Ops._

    implicit def listSemiring[A: Semiring]: Semiring[List[A]] = new Semiring {
                                         
        override def zero: List[A] = List(Semiring[A].zero) 
        
        override def add(a: List[A], b: List[A]): List[A] =
            {       
               val list = List(a, b)

                list.foldLeft(zero){
                     (acc, item) => acc.flatMap(x => item.map(_ |+| x))
                    }.toSet.toList
            }
        override def one: List[A] = List(Semiring[A].one)

        override def mult(a: List[A], b: List[A]): List[A] = 
            {   
                val list = List(a, b)

                list.foldLeft(one){
                     (acc, item) => acc.flatMap(x => item.map(_ |*| x))
                    }
            }
    }
}

trait ListMonoid   {

    import Monoid.Ops._

    implicit def listMonoid[A: Monoid]: Monoid[List[A]] = new Monoid[List[A]] {
        
        override def id: List[A] = List(Monoid[A].id)

        override def combine(a: List[A], b: List[A]): List[A] = 
            {   
                val list = List(a, b)

                list.foldLeft(id){
                     (acc, item) => acc.flatMap(x => item.map(_ <:> x))
                    }
            }
    }
}

trait ListMonoidK {

    import MonoidK.Ops._

    implicit def listMonoidK: MonoidK[List] = new MonoidK {

        override def combine[A](a: List[A], b: List[A]): List[A] = a ::: b

        override def id[A]: List[A] = List.empty 

    }
}

trait ListMultMonoid   {

    import MultiplicativeMonoid.Ops._

    implicit def listMultMonoid[A: MultiplicativeMonoid]: MultiplicativeMonoid[List[A]] = new MultiplicativeMonoid {
                                            
        override def one: List[A] = List(MultiplicativeMonoid[A].one) 

        override def mult(a: List[A], b: List[A]): List[A] = 
            {   
                val list = List(a, b)

                list.foldLeft(one){
                     (acc, item) => acc.flatMap(x => item.map(_ |*| x))
                    }
            }
    }

}

trait ListAddMonoid   {

    import AdditiveMonoid.Ops._

    implicit def listAddMonoid[A: AdditiveMonoid]: AdditiveMonoid[List[A]] = new AdditiveMonoid{
                                             
        override def zero: List[A] = List(AdditiveMonoid[A].zero)
        
        override def add(a: List[A], b: List[A]): List[A] =
            {   
                val list = List(a, b)

                list.foldLeft(zero){
                     (acc, item) => acc.flatMap(x => item.map(_ |+| x))
                    }
            }
            
    }
}

trait ListSemigroup   {

    import Semigroup.Ops._

    implicit def listSemigroup[A: Semigroup]: Semigroup[List[A]] = new Semigroup{

        override def combine(a: List[A], b: List[A]): List[A] =
            {  
                (a zip b).map(_ <:> _)

            }
            
    }
}

trait ListGroupoid   {

    implicit def listGroupoid[A: Groupoid]: Groupoid[List[A]] = new Groupoid {
                                             
        override def inverse(a: List[A]): List[A] = a.map(Groupoid[A].inverse)
        
    }
}

trait ListCategory   {

    implicit def listCategory[A]: Category[List[A]] = new Category{}
}

trait ListSemicategory   {

    implicit def listSemicategory[A]: Semicategory[List[A]] = new Semicategory{}
}