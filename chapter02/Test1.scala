package com.ex2.test1

object Test1 {
  // 定义方法
  def m1(name: String): Unit = {
    println("m1() " + name)
  }

  def main(args: Array[String]): Unit = {
    // 调用对象方法
    Test1.m1("shawn")

    // Scala可以在任何语法结构中声明任何语法
    import java.util.Date
    new Date()

    // 函数没有重载和重写的概念, 程序报错
    def f1(): Unit = {
      println("调用 f1()")
    }
    f1()
    //def f1(name: String): Unit = {} // method f1 is defined twice

    // 嵌套定义函数
    def f2(): Unit = {
      def f3(): Unit = {
        println("调用 f3()")
      }
      f3()
    }
    f2()
  }

  // 方法的重载或重写
  def main(): Unit = {}
}
//m1() shawn
//调用 f1()
//调用 f3()