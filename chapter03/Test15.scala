package com.ex3.test15

trait Knowledge {
  var amount: Int = 0
  def increase(): Unit
}

trait Talent {
  def singing(): Unit
  def dancing(): Unit
}

import com.ex3.test14.{Person, Young}

class Student extends Person with Young with Knowledge {
  // 重写冲突的属性(Person& Young
  override val name: String = "student"

  // 重写父类方法(Person
  override def sayHello(): Unit = {
    super.sayHello()
    println(s"hello from: Student $name")
  }

  // 实现抽象方法(Young
  def dating(): Unit = println(s"Student $name is dating")

  // 实现特质中的抽象方法(Knowledge
  override def increase(): Unit = {
    amount += 1
    println(s"Student $name Knowledge increased: $amount")
  }

  def study(): Unit = println(s"Student $name is studying")
}

object Test15 {
  def main(args: Array[String]): Unit = {
    val student = new Student
    student.study() // Student student is studying
    student.increase() // Student student Knowledge increased: 1
    student.play() // Young people student is playing
    student.increase() // Student student Knowledge increased: 2
    student.dating() // Student student is dating
    student.increase() // Student student Knowledge increased: 3

    // 动态混入(代码块的方式创建匿名子类; 灵活扩展, 类的功能
    val studentWithTalent = new Student with Talent {
      override def dancing(): Unit = println("Student is good at dancing")
      override def singing(): Unit = println("Student is good at singing")
    }
    studentWithTalent.sayHello()
    //hello from student
    //hello from: Student student
    studentWithTalent.play() // Young people student is playing
    studentWithTalent.study() // Student student is studying
    studentWithTalent.dating() // Student student is dating
    studentWithTalent.dancing() // Student is good at dancing
    studentWithTalent.singing() // Student is good at singing
  }
}