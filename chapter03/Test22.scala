package com.ex3.test22

class Person(val name: String, val age: Int) {
  def sayHi(): Unit = {
    println("hi from Person " + name)
  }
}

class Student(name: String, age: Int) extends Person(name, age) {
  override def sayHi(): Unit = {
    println("hi from Student " + name)
  }

  def study(): Unit = {
    println("Student study")
  }
}

object Test22 {
  def main(args: Array[String]): Unit = {
    // 1. 类型的检测和转换
    val student: Student = new Student("Shawn", 35)
    student.study() // Student study
    student.sayHi() // hi from Student Shawn
    val person: Person = new Student("Mia", 30)
    person.sayHi() // hi from Student Mia

    // 类型判断
    println("student is Student: " + student.isInstanceOf[Student]) // true
    println("student is Person: " + student.isInstanceOf[Person]) // true Student -> Person向上匹配
    println("person is Person: " + person.isInstanceOf[Person]) // true
    println("person is Student: " + person.isInstanceOf[Student]) // true
    val person2: Person = new Person("cary", 35)
    println("person2 is Student: " + person2.isInstanceOf[Student]) // false 不能向下匹配

    // ClassOf判断对象类型
    println(person.getClass == classOf[Student])    // true
    println(person2.getClass == classOf[Student])   // false

    // 类型转换
    if (person.isInstanceOf[Student]) {
      val newStudent = person.asInstanceOf[Student]
      newStudent.study() // Student study
    }

    // classOf获取对象的类名
    println(classOf[Student]) // class com.ex3.test22.Student
  }
}
