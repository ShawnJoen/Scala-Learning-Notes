package com.ex6.test7

object Test7 {
  def main(args: Array[String]): Unit = {
    // 隐式类
    implicit class MyRichInt(val self: Int) {
      // 自定义比较大小的方法
      def myMax(n: Int): Int = if (n < self) self else n
      def myMin(n: Int): Int = if (n < self) n else self
    }
    println(12.myMin(15)) // 12
  }
}