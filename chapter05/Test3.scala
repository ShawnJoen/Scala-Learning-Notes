package com.ex5.test3

object Test3 {
  def main(args: Array[String]): Unit = {
    // 1. 变量声明匹配
    val (x, y) = (35, "Shawn")
    println(s"x: $x, y: $y") // x: 35, y: Shawn

    val List(first, second, _*) = List(1, 2, 3, 4, "Shawn")
    println(s"first: $first, second: $second") // first: 1, second: 2

    val fir :: sec :: rest = List(11, 22, 33, 44, "Shawn")
    println(s"first: $fir, second: $sec, rest: $rest") // first: 11, second: 22, rest: List(33, 44, Shawn)

    val list: List[(String, Int)] = List(("a", 10), ("b", 20), ("c", 30), ("a", 40))
    // 2. for推导式中进行模式匹配
    // 2.1 原本的遍历方式
    for (elem <- list) {
      print(elem._1 + "," + elem._2 + " ") // a,10 b,20 c,30 a,40
    }
    println()

    // 2.2 将 List的元素直接定义为元组, 对变量赋值
    for ((word, count) <- list) {
      print(word + ": " + count + " ") // a: 10 b: 20 c: 30 a: 40
    }
    println()

    // 2.3 可以不考虑某个位置的变量，只遍历key或者value
    for ((word, _) <- list) print(word + " ") // a b c a
    println()

    // 2.4 可以指定某个位置的值必须是多少
    for (("a", count) <- list) {
      println(count)
    }
    //    10
    //    40
  }
}