package com.ex3.test7

object Test7 {
  class Person() {
    var name: String = _
    var age: Int = _

    println("1. 父类的主构造器调用")

    def this(name: String, age: Int) {
      this()
      println("2. 父类的辅助构造器调用")
      this.name = name
      this.age = age
    }

    def print(): Unit = {
      println(s"Person: $name $age")
    }
  }

  class Student(name: String, age: Int) extends Person(name, age) { // 继承了父类的辅助构造器
    var stdNo: String = _

    println("3. 子类的主构造器调用")

    def this(name: String, age: Int, stdNo: String) {
      this(name, age)
      println("4. 子类的辅助构造器调用")
      this.stdNo = stdNo
    }

    override def print(): Unit = {
      println(s"Student: $name $age $stdNo")
    }
  }

  class Teacher extends Person {
    override def print(): Unit = {
      println(s"Teacher")
    }
  }

  def main(args: Array[String]): Unit = {
    val student1: Student = new Student("Mia", 30)
    //1. 父类的主构造器调用
    //2. 父类的辅助构造器调用
    //3. 子类的主构造器调用
    student1.print()
    //Student: Mia 30 null
    val student2 = new Student("Shawn", 35, "S0001")
    //1. 父类的主构造器调用
    //2. 父类的辅助构造器调用
    //3. 子类的主构造器调用
    //4. 子类的辅助构造器调用
    student2.print()
    //Student: Shawn 35 S0001
    val teacher = new Teacher
    //1. 父类的主构造器调用
    teacher.print()
    //Teacher
    def print(person: Person): Unit = {
      person.print()
    }
    print(student1) // 向上转型
    //Student: Mia 30 null
    print(student2) // 向上转型
    //Student: Shawn 35 S0001
    print(teacher) // 向上转型
    //Teacher
    val person = new Person
    //1. 父类的主构造器调用
    print(person)
    //Person: null 0
  }
}