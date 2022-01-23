package com.ex3.test12

import com.ex3.test11.{Student => S0}

class Student private(var name: String, var age: Int) {
  def print() {
    println(s"Student: name = ${name}, age = $age, school = ${S0.school}")
  }
}

//// 饿汉式
//object Student {
//  private val student: Student = new Student("Mia", 30)
//  def getInstance(): Student = student
//}

// 懒汉式
object Student {
  private var student: Student = _
  def getInstance(): Student = {
    if (student == null) {
      // 如果没有对象实例的话，就创建一个
      student = new Student("Shawn", 35)
    }
    student
  }
}

object Test12 {
  def main(args: Array[String]): Unit = {
    val student1 = Student.getInstance()
    student1.print() // Student: name = Shawn, age = 35, school = bl
    println(student1) // com.ex3.test12.Student@c818063

    val student2 = Student.getInstance()
    println(student2) // com.ex3.test12.Student@c818063
  }
}
