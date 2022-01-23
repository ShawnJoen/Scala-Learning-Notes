package com.ex3.test5

object Test5 {
  def main(args: Array[String]): Unit = {
    val stu1 = new Student // 1. 主构造方法被调用
    stu1.Student() // 一般方法被调用
    val stu2 = new Student("Shawn")
    // 1. 主构造方法被调用
    // 2. 辅助构造方法一被调用
    // name: Shawn age: 0
    val stu3 = new Student("Mia", 30)
    // 1. 主构造方法被调用
    // 2. 辅助构造方法一被调用
    // name: Mia age: 0
    // 3. 辅助构造方法二被调用
    // name: Mia age: 30
  }
}

class Student() /* 主构造器*/ { // 如果主构造器无参数, 小括号可省略
  var name: String = _
  var age: Int = _
  println("1. 主构造方法被调用")
  // 辅助构造器
  def this(name: String) {
    this() // 直接调用主构造器
    println("2. 辅助构造方法一被调用")
    this.name = name
    println(s"name: $name age: $age")
  }

  // 辅助构造器(可以重载多个
  def this(name: String, age: Int){
    this(name)
    println("3. 辅助构造方法二被调用")
    this.age = age
    println(s"name: $name age: $age")
  }

  // 不同于 Java, 在 Scala与类名相同的方法不是构造器, 而只是一般方法
  def Student(): Unit = {
    println("一般方法被调用")
  }
}
