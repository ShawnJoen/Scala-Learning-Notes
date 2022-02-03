package com.ex6.test3

import java.io.File
import scala.io.Source

object Test3 {
  class RichFile(file: File) {
    def read() = Source.fromFile(file).mkString
  }
  // 单例对象中定义隐式转换
  object ImplicitDemo {
    // 隐式函数(一个参数列表中只能有一个参数
    implicit def file2RichFile(file: File) = new RichFile(file)
  }

  def main(args: Array[String]): Unit = {
    import ImplicitDemo.file2RichFile
    val file = new File("chapter06/test.txt")
    println(file.read())
  }
}