package com.ex3.test14

class Person {
  val name: String = "person"
  var age: Int = 18
  def sayHello(): Unit = {
    println("hello from " + name)
  }
  def increase(): Unit = {
    println("Person increase")
  }
}

trait Young {
  var age: Int
  val name: String = "young"
  def play(): Unit = {
    println(s"Young people $name is playing")
  }
  def dating(): Unit
}

class Student extends Person with Young with java.io.Serializable  {
  // 必须重写冲突的属性(Person& Young
  override val name: String = "student"

  // 重写父类方法(Person
  override def sayHello(): Unit = {
    super.sayHello()
    println(s"hello from Student $name")
  }

  // 重写特征方法(Young
  def dating(): Unit = println(s"Student $name is dating")

  def study(): Unit = println(s"Student $name is studying")
}

object Test14 {
  def main(args: Array[String]): Unit = {
    val student: Student = new Student
    student.sayHello()
    // hello from student(动态绑定属性
    // hello from Student student
    student.play() // Young people student is playing
    student.dating() // Student student is dating
    student.study() // Student student is studying
  }
}
