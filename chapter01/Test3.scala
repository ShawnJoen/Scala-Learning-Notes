package com.ex.test3

import scala.io.StdIn

object Test3 {
  def main(args: Array[String]): Unit = {
    println("请输入名字:")
    val name: String = StdIn.readLine()
    println("请输入年龄:")
    val age: Int = StdIn.readInt()
    println(s"${name}${age}岁")
  }
}
