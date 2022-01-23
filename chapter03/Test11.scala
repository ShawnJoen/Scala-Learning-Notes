package com.ex3.test11

class Student private(val name: String, val age: Int) {
  def print() {
    println(s"Student: name = ${name}, age = $age, school = ${Student.school}")
  }
}

object Student {
  val school: String = "bl"
  def newStudent(name: String, age: Int): Student = new Student(name, age)
  def apply(name: String, age: Int): Student = new Student(name, age)
}

object Test11 {
  def main(args: Array[String]): Unit = {
    //val student = new Student("Shawn", 35) // 构造器私有不允许在外部 new; constructor Student in class Student cannot be accessed in object Test11
    //student.print()

    val student1 = Student.newStudent("Mia", 30)
    student1.print() // Student: name = Mia, age = 30, school = bl
    println(student1) // com.ex3.test11.Student@c818063

    val student2 = Student.apply("Tom", 20)
    student2.print() // Student: name = Tom, age = 20, school = bl
    println(student2) // com.ex3.test11.Student@3f0ee7cb

    val student3 = Student("Sam", 10)
    student3.print() // Student: name = Sam, age = 10, school = bl
    println(student3) // com.ex3.test11.Student@75bd9247
  }
}
