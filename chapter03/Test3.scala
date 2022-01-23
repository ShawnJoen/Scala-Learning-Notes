package com.ex3.test3

import scala.beans.BeanProperty

class Student {
  private var name: String = "Shawn"
  // val status: Int = _ // val修饰的属性不能赋默认值, 必须显示指定; 编译时会报错
  var age: Int = _ // 所有的类型, 可以用(_)下划线设置初始值
  // 当使用一些 java框架时, 类需要有 setgetter方法, 为了兼容这些 java框架, Scala在属性上方加 @BeanProperty注解, 让其自动生成 setgetter方法
  @BeanProperty
  var sex: String = _
}

object Test3 {
  def main(args: Array[String]): Unit = {
    val student = new Student()
    println(student.age) // 0
    student.age = 35
    println(student.age) // 35
    println(student.sex) // null
    student.setSex("Male")
    println(student.getSex) // Male
  }
}
