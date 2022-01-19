package com.ex2.test13

object Test13 {
  def main(args: Array[String]): Unit = {
    lazy val result: Int = sum(10, 20) // lazy loading意味着将, sum(10, 30)整个代码传递 result常量中
    println("1. result = " + result) // 在此处会调用执行 sum(10, 20)
    println("2. result = " + result)
  }

  def sum(a: Int, b: Int): Int = {
    println("sum()调用")
    a + b
  }
}
