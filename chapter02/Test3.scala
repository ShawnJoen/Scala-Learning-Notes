package com.ex2.test3

object Test3 {
  def main(args: Array[String]): Unit = {
    // 函数体的最后一行代码为返回值, 该行可以省略 return
    def f1(name: String): String = {
      name
    }
    println(f1("1 shawn")) // 1 shawn

    // 当函数体只有一行代码, 函数体可以省略花括号
    def f2(name: String): String = name
    println(f2("2 shawn")) // 2 shawn

    // 当返回值类型可以推断出时, 可以省略返回类型包括(:), 只保留等号
    def f3(name: String) = name
    println(f3("3 shawn")) // 3 shawn

    // 而函数体内使用了 return, 则不能省略返回值类型
    //    def f4(name: String) = {
    //        return name
    //    }
    //    println(f4("4 shawn"))

    // 无返回值的函数可以省略包括等号
    def f5(name: String) {
      println(name)
    }
    println(f5("5 shawn"))
    // 5 shawn
    // ()

    // 某个函数无参, 但又声明了参数列表()括号, 当调用该函数时, 小括号, 可加或可不加
    def f6(): Unit = {
      println("6 shawn")
    }
    f6() // 6 shawn
    f6 // 6 shawn

    // 如果函数没有参数列表()括号, 那么调用时小括号必须省略
    def f7: String = "7 shawn"
    // f7()
    println(f7) // 7 shawn

    // 如果不关心函数名称, 只需要逻辑处理, 那么函数名包括(def)可以省略(匿名函数, lambda表达式
    def f8 = (name: String) => {
      println(name)
    }
    def f9(f: String => Unit) = { // 参数为匿名函数(lambda表达式)
      f("8 shawn")
    }
    f9(f8) // 8 shawn
    f9((name: String) => println(name)) // 传入匿名函数: 8 shawn
  }
}
