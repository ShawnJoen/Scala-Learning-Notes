package com.ex4.test9

object Test9 {
  def main(args: Array[String]): Unit = {
    val map1: Map[String, Int] = Map("a" -> 20, "b" -> 10, "c" -> 30)
    println(map1) // Map(a -> 20, b -> 10, c -> 30)
    println(map1.getClass) // class scala.collection.immutable.Map$Map3

    // 遍历元素
    map1.foreach((kv: (String, Int)) => print(kv)) // (a,20)(b,10)(c,30)
    println()
    map1.foreach(print) // (a,20)(b,10)(c,30)
    println()

    // 取所有键
    for (key <- map1.keys) {
      println(s"$key -> ${map1.get(key)} -> ${map1.get(key).get}")
    }
    //    a -> Some(20) -> 20
    //    b -> Some(10) -> 10
    //    c -> Some(30) -> 30

    // 不可变 Map集合, 没有 put函数
    // map1.put("d", 100) // value put is not a member of Map[String,Int]

    println("a: " + map1.get("a").get) // 20
    println("b: " + map1.get("c")) // Some(30)
    println("d: " + map1.get("d")) // None
    println("d: " + map1.getOrElse("d", 0)) // 0 如果 key不存在, 返回 0
  }
}
