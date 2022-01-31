package com.ex5.test4

object Test4 {
  def main(args: Array[String]): Unit = {
    val map = Map("A" -> 1, "B" -> 0, "C" -> 3)
    // if v等于或大于0的剩余k-v
    for ((k, v) <- map if v >= 1) {
      println(k + " ---> " + v)
    }
    //    A ---> 1
    //    C ---> 3

    val list: List[(String, Int)] = List(("a", 1), ("b", 2), ("c", 3), ("a", 4))
    // 1. map转换, 实现 key不变, value 2倍
    val newList = list.map(tuple => (tuple._1, tuple._2 * 2))
    println(newList) // List((a,2), (b,4), (c,6), (a,8))

    // 2. 用模式匹配对元组元素赋值, 实现功能
    val newList2 = list.map(
      tuple => {
        tuple match {
          case (word, count) => (word, count * 2)
        }
      }
    )
    println(newList2) // List((a,2), (b,4), (c,6), (a,8))

    // 3. 省略lambda表达式的写法，进行简化
    val newList3 = list.map {
      case (word, count) => (word, count * 2)
    }
    println(newList3) // List((a,2), (b,4), (c,6), (a,8))

    // 集合元素中, 数据类型为 Int的元素 + 1
    List(1,2,3,4,5,6,"test").filter(_.isInstanceOf[Int]).map(_.asInstanceOf[Int] + 1).foreach(print) // 234567
    println()
    List(1,2,3,4,5,6,"test").map{ case x: Int => x + 1 case _ => None }.foreach(print) // 234567None
    println()
    List(1,2,3,4,5,6,"test").collect{ case x: Int => x + 1 }.foreach(print) // 234567
  }
}