package com.ex5.test8

// 定义样例类
case class Student(name: String, age: Int)

object Test8 {
  def main(args: Array[String]): Unit = {
    val student = Student("Shawn", 35)
    // 针对对象实例的内容进行匹配
    val result = student match {
      case Student("Shawn", 35) => "Shawn, 35"
      case _ => "Else"
    }
    println(result)
  }
}