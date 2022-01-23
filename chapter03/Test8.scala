package com.ex3.test8

class Person { // 这个可以是接口(在 Scala就是Trait)
  val name: String = "person"

  def hello(): Unit = {
    println("hello person")
  }
}

class Student(var age: Int) extends Person {
  override val name: String = "student"

  override def hello(): Unit = { // 在 Scala覆写父级方法(非抽象)时, 必须写上 override才会覆盖的也就是可以用于多态调用的; 抽象方法和属性可以省略 override
    println("hello student")
  }

  def hi(): Unit = {
    println("hi student")
  }
}

object Test8 {
  def main(args: Array[String]): Unit = {
    val student: Person = new Student(0)
    println(student.name) // student(Scala:动态绑定属性, Java:静态绑定属性, 这里如果是 Java则会输出 person
    student.hello() // hello student(Scala:动态绑定方法, Java:动态绑定方法
    //student.hi() value hi is not a member of com.ex3.test8.Person必须有覆写方法才可以动态调用方法

    val student2: Student = new Student(20)
    println(student2.age) // 20
  }
}
