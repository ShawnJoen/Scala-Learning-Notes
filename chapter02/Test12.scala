package com.ex2.test12

object Test12 {
  def main(args: Array[String]): Unit = {
    println("=== 1. 常规的 While循环")
    var n = 10 // 常规的 While循环, 需要定义一个变量在外部
    while (n >= 1) {
      print(n + ", ")
      n -= 1
    }
    println()

    println("=== 2. 用闭包实现一个函数, 将代码块作为参数传入(名调用), 递归调用")
    def myWhile(condition: => Boolean): (=> Unit) => Unit = {
      // 内层函数需要递归调用, 参数就是循环体
      def doLoop(op: => Unit): Unit = {
        if (condition) {
          op // print(n + ", "); n -= 1
          myWhile(condition)(op)
        }
      }
      doLoop
    }
    n = 10
    myWhile(n >= 1) ({
      print(n + ", ")
      n -= 1
    })
    println()

    n = 10
    myWhile(n >= 1) {
      print(n + ", ")
      n -= 1
    }
    println()

    n = 10
    myWhile(n >= 1) (n -= 1)
    println("n=" + n)

    println("=== 3. 用匿名函数实现")
    def myWhile2(condition: => Boolean): (=> Unit) => Unit = {
      // 内层函数需要递归调用, 参数就是循环体
      op => {
        if (condition) {
          op
          myWhile2(condition)(op)
        }
      }
    }
    n = 10
    myWhile2(n >= 1) {
      print(n + ", ")
      n -= 1
    }
    println()

    println("=== 4. 用柯里化实现")
    def myWhile3(condition: => Boolean)(op: => Unit): Unit = {
      if (condition) {
        op
        myWhile3(condition)(op)
      }
    }
    n = 10
    myWhile3(n >= 1) {
      print(n + ", ")
      n -= 1
    }
  }
}