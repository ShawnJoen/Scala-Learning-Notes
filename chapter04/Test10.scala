package com.ex4.test10

import scala.collection.mutable

object Test10 {
  def main(args: Array[String]): Unit = {
    val map1: mutable.Map[String, Int] = mutable.Map("a" -> 20, "b" -> 10, "c" -> 30)
    println(map1) // Map(b -> 10, a -> 20, c -> 30)
    println(map1.getClass) // class scala.collection.mutable.HashMap

    // 添加元素
    map1.put("d", 50)
    map1.put("e", 40)
    println(map1) // Map(e -> 40, b -> 10, d -> 50, a -> 20, c -> 30)

    map1 += (("d", 60)) // 存在相同 key时, 后加的 value覆盖已有的 value
    map1 += (("f", 70)) // 不存在, 则创建
    println(map1) // Map(e -> 40, b -> 10, d -> 60, a -> 20, c -> 30, f -> 70)

    // 删除元素
    println(map1.remove("c")) // Some(30) 删除并返回
    println(map1.get("c")) // None
    println(map1.getOrElse("c", 0)) // 0 key不存在时返回0
    println(map1) // Map(e -> 40, b -> 10, d -> 60, a -> 20, f -> 70)

    map1 -= "a" // 指定要删除的 key
    map1 -= ("d","f") // 可以指定多个 key
    map1("shawn") = 35
    println(map1) // Map(e -> 40, b -> 10, shawn -> 35)

    // 修改元素 (和 put方法是等效的, 底层实现也是相同
    map1.update("d", 80)
    map1.update("e", 10)
    map1.update("f", 70) // 不存在也会新建
    println(map1) // Map(e -> 10, b -> 10, shawn -> 35, d -> 80, f -> 70)

    // 合并两个Map
    val map2: Map[String, Int] = Map("aa" -> 200, "bb" -> 100, "cc" -> 300)
    map1 ++= map2 // 将 map2合并到 map1集合中(更新 map1自身; map必须为可变集合, map2可以是不可变
    println(map2) // Map(aa -> 200, bb -> 100, cc -> 300)
    println(map1) // Map(e -> 10, b -> 10, shawn -> 35, bb -> 100, d -> 80, cc -> 300, f -> 70, aa -> 200)

    val map3: Map[String, Int] = map2 ++ map1 // 新建新的集合
  }
}
