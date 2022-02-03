package com.ex6.test13

object Test13 {
  class Person
  class Policeman extends Person
  class Superman extends Policeman
  // 限定 Array元素类型只能是 Person或 Policeman
  //          下界          上界
  def demo[T >: Superman <: Policeman](arr: Array[T]) = println(arr)
  def main(args: Array[String]): Unit = {
    demo(Array(new Policeman))
    demo(Array(new Superman))
    //demo(Array(new Person)) // 会报错
  }
}