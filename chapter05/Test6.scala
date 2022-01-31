package com.ex5.test6

// 定义类
class Student(val name: String, val age: Int)

// 定义伴生对象
object Student {
  def apply(name: String, age: Int): Student = new Student(name, age)
  // 必须实现 unapply方法, 用来对对象属性进行拆解
  def unapply(student: Student): Option[(String, Int)] = {
    if (student == null) {
      None
    } else {
      Some((student.name, student.age))
    }
  }
}

object Test6 {
  def main(args: Array[String]): Unit = {
    val student = new Student("Shawn", 35)
    // 针对对象实例的内容进行匹配
    val result = student match {
      case Student("Shawn", 35) => "Shawn, 35"
      case _ => "Else"
    }
    println(result) // Shawn, 35

    val result2 = Student.unapply(student)
    println(result2) // Some((Shawn,35))
  }
}