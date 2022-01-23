package com.ex3.test6

object Test6 {
  def main(args: Array[String]): Unit = {
    val student1 = new Student1
    // student1.name = "Mia" reassignment to val
    student1.age = 30
    println(s"Student1: name = ${student1.name}, age = ${student1.age}")
    // Student1: name = Shawn, age = 30

    val student2 = new Student2("Shawn", 35)
    println(s"student2: name = ${student2.name}, age = ${student2.age}")
    // student2: name = Shawn, age = 35

    val student3 = new Student3("Tom", 20)
    // student3.age = 25 // value age is not a member of com.ex3.test6.Student3(不是内部属性
    student3.printInfo() // Student3: name = Tom, age = 20

    val student4 = new Student4("Sam", 10, "bl")
    println(s"Student4: name = ${student4.name}, age = ${student4.age}")
    // Student4: name = Sam, age = 10
    student4.printInfo()
    // Student4: name = Sam, age = 10, school = bl
  }
}

// 无参构造器
class Student1 {
  val name: String = "Shawn"
  var age: Int = _
}
// 等价于
class Student2(val name: String, var age: Int) // 没有其它可以省略{}

// 主构造器参数无修饰
class Student3(name: String, age: Int) {
  def printInfo() {
    println(s"Student3: name = ${name}, age = $age")
  }
}

// 辅助构造器(辅助构造器不能直接构建对象, 必须直接或者间接调用主构造器
class Student4(var name: String, var age: Int) {
  var school: String = _

  def this(name: String, age: Int, school: String) {
    this(name, age)
    this.school = school
  }

  def printInfo() {
    println(s"Student4: name = ${name}, age = $age, school = $school")
  }
}