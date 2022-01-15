package com.ex.test5

import com.ex.test1.Student

class Student(name: String, var age: Int) {
  def printInfo(): Unit = {
    println(name + " " + age + " " + Student.school)
  }
}

object Test5 {
  def main(args: Array[String]): Unit = {
    //val n: Int = null // error
    var student: Student = new Student("alice", 20)
    student = null // 引用类型可以
    println(student) // null

    // 设置返回 Nothing, 意味着注定不会正常执行
//    def fun0(n: Int): Nothing = throw new IllegalArgumentException
//    val r0: Int = fun0(2) // 调用方法直接抛异常
//    println("r0: " + r0) // 不会执行到该行

    def fun(n: Int): Int = {
      if (n == 0) throw new IllegalArgumentException
      else return n
    }
    val r1: Int = fun(3)
    println("r1: " + r1) // r1: 3
  }
}
