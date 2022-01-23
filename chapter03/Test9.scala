package com.ex3.test9

abstract class Person {
  // 非抽象属性
  val name: String = "person"
  // 抽象属性
  var age: Int
  // 非抽象方法
  def eat(): Unit = {
    println("person eat")
  }
  // 抽象方法
  def sleep(): Unit
}

class Student extends Person {
  var age: Int = 18
  override val name: String = "student"

  override def eat(): Unit = {
    super.eat()
    println("student eat")
  }

  def sleep(): Unit = {
    println("student sleep")
  }
}

object Test9 {
  def main(args: Array[String]): Unit = {
    val student = new Student
    println(student.name) // student
    student.eat()
    //person eat
    //student eat
    student.sleep()
    //student sleep
  }
}
