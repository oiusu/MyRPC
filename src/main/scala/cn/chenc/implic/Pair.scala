package cn.chenc.implic

/**
  * Created by ChenC on 2016/7/13 0013.
  */
class Pair [T <: Comparable[T]] {

  def bigger (first : T , second : T) : T= {
    if (first.compareTo(second) > 0) first else second
  }


}



object Pair {

  def main(args: Array[String]) {
    val p = new Pair[Integer]
    println(p.bigger(1,2))

  }

}