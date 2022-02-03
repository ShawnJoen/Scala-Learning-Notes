package com.ex6.test4

object Test4 {
  def main(args: Array[String]): Unit = {
    // 隐式值
    implicit val name: String = "Shawn"
    implicit val num: Int = 18
    // 隐士参数
    def sayHello()(implicit arg: String): Unit = {
      println("hello, " + arg)
    }
    sayHello // hello, Shawn
    sayHello()("Sam") // hello, Sam

    def sayHi(implicit arg: String = "Mia"): Unit = {
      println("hi, " + arg)
    }
    sayHi // hi, Shawn
    sayHi("Tom") // hi, Tom

    // 代码简化
    def hiAge(): Unit = {
      println("hi, " + implicitly[Int])
    }
    hiAge() // hi, 18
  }
}