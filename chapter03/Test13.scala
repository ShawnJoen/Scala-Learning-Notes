package com.ex3.test13

object Test13 {
  class Person {
    var name = ""
    def sayHello() = println(s"Hello, ${name}!")
  }
  // 定义单例对象 Student, 继承 Person类
  object Student extends Person
  def main(args: Array[String]): Unit = {
    Student.name = "Shawn"
    println(Student.name) // Shawn
    Student.sayHello() // Hello, Shawn!
  }
}
