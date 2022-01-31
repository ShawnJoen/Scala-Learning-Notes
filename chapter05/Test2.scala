package com.ex5.test2

object Test2 {
  def main(args: Array[String]): Unit = {
    // 1. 匹配常量: 可以匹配所有的字面量 如字符串, 字符, 数字, 布尔值等
    def describeConst(x: Any): String = x match {
      case 1 => "Int one"
      case "hello" => "String hello"
      case true => "Boolean true"
      case '+' => "Char +"
      case _ => "未匹配!"
    }

    println(describeConst("hello")) // String hello
    println(describeConst(true)) // Boolean true
    println(describeConst(0.3)) // 未匹配!

    // 2. 匹配类型: 集合类型, 即使设置了泛型, 依然不会按泛型匹配, 因此无需写, 泛型类型可以写成(_)通配符代替(注: 数组的泛型类型是有效的)
    def describeType(x: Any): String = x match {
      case i: Int => "Int " + i
      case s: String => "String " + x
      case list: List[_] => list.toString
      case set: Set[_] => set.toString
      case map: Map[String, Int] => map.toString
      case array: Array[Int] => "Array[Int] " + array.mkString(",")
      case _ => "Something else: " + x
    }

    println(describeType(35)) // Int 35
    println(describeType("hello")) // String hello
    // 集合类型, 即使设置了泛型, 依然不会按泛型匹配, 因此无需写类型, 而使用(_)通配符代替
    println(describeType(List("hi", "hello"))) // List(hi, hello)
    println(describeType(List(2, 23))) // List(2, 23)
    println(describeType(Set(3, 5))) // Set(3, 5)
    println(describeType(Map(("a", 5), "b" -> 6, 8 -> "abc"))) // Map(a -> 5, b -> 6, 8 -> abc)
    // 注: 数组的泛型类型是有效的
    println(describeType(Array("hi", "hello"))) // Something else: [Ljava.lang.String;@75bd9247
    println(describeType(Array(2, 23))) // Array[Int] 2,23

    // 3. 匹配数组
    for (arr <- List(
      Array(0), // case Array(0)
      Array(1, 0), // case Array(1, 0)
      Array(0, 1, 0), // case Array(0, _*)
      Array(1, 1, 0), // case Array(x, 1, z)
      Array(2, 3, 7, 15), // case _
      Array("A", 1, 30) // case Array(x, 1, z)
    )) {
      val result = arr match {
        case Array(0) => "0"
        case Array(1, 0) => "Array(1, 0)"
        case Array(x, y) => "Array: " + x + ", " + y
        case Array(0, _*) => "以0开头的数组"
        case Array(x, 1, z) => "中间为1的三元素数组"
        case _ => "something else"
      }
      println(result)
    }
    //    0
    //    Array(1, 0)
    //    以0开头的数组
    //    中间为1的三元素数组
    //    something else
    //    中间为1的三元素数组

    // 4. 匹配列表
    // 方式一
    for (list <- List(
      List(0), // case List(0)
      List(1, 0), // case List(x, y)
      List(0, 0, 0), // case List(0, _*)
      List(1, 1, 0), // case _
      List(88), // case List(a)
      List("hello") // case List(a)
    )) {
      val result = list match {
        case List(0) => "0"
        case List(x, y) => "List(x, y): " + x + ", " + y
        case List(0, _*) => "List(0, ...)"
        case List(a) => "List(a): " + a
        case _ => "something else"
      }
      println(result)
    }
    //    0
    //    List(x, y): 1, 0
    //    List(0, ...)
    //    something else
    //    List(a): 88
    //    List(a): hello

    // 方式二
    val list1 = List(1, 2, 5, 7, 24)
    val list = List(1, 2, List(5, 7, 24))
    list match {
      case first :: second :: rest =>
        println(s"first: $first, second: $second, rest: $rest")
      case _ => println("something else")
    }
    // first: 1, second: 2, rest: List(List(5, 7, 24))

    // 5. 匹配元组
    for (tuple <- List(
      (0, 1), // case (a, b)
      (0, 0), // case (a, b)
      (0, 1, 0), // case (a, 1, _)
      (0, 1, 1), // case (a, 1, _)
      (1, 23, 56), // case (x, y, z)
      ("hello", true, 0.5) // case (x, y, z)
    )) {
      val result = tuple match {
        case (a, b) => "" + a + ", " + b
        case (0, _) => "(0, _)"
        case (a, 1, _) => "(a, 1, _) " + a
        case (x, y, z) => "(x, y, z) " + x + " " + y + " " + z
        case _ => "something else"
      }
      println(result)
    }
    //    0, 1
    //    0, 0
    //    (a, 1, _) 0
    //    (a, 1, _) 0
    //    (x, y, z) 1 23 56
    //    (x, y, z) hello true 0.5
  }
}