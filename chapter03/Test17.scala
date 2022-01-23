package com.ex3.test17

object Test17 {
  // 1. 创建 Logger父特质
  trait Logger {
    println("执行 Logger构造器")
  }
  // 2. 创建 MyLogger子特质, 继承 Logger特质
  trait MyLogger extends Logger {
    println("执行 MyLogger构造器")
  }
  // 3. 创建 TimeLogger子特质, 继承 Logger特质
  trait TimeLogger extends Logger {
    println("执行 TimeLogger构造器")
  }
  // 4. 创建父类 Person
  class Person {
    println("执行 Person构造器")
  }
  // 5. 创建子类 Student, 继承 Person类及 TimeLogger和 MyLogger特质
  class Student extends Person with TimeLogger with MyLogger {
    println("执行 Student构造器")
  }
  def main(args: Array[String]): Unit = {
    new Student
  }
}
