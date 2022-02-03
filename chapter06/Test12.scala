package com.ex6.test12

object Test12 {
  class Person
  class Student extends Person
  // 限定 Array元素类型只能是 Person或 Person的子类
  def demo[T <: Person](arr: Array[T]) = println(arr)
  def main(args: Array[String]): Unit = {
    //demo(Array(1, 2, 3)) // 会报错, 因为只能传入 Person或者它的子类型
    demo(Array(new Person()))
    demo(Array(new Student()))
  }
}