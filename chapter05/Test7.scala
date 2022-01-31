package com.ex5.test7

object Test7 {
  case class Person(name: String, age: Int)
  def main(args: Array[String]): Unit = {
    val p1 = new Person("Shawn", 35)
    val p2 = p1.copy(name = "Mia", age = 30)
    println(p1) // Person(Shawn,35)
    println(p2) // Person(Mia,30)
    val p3 = p1.copy()
    println(p1 == p2) // 判断成员属性: false(对象不同, 成员属性值也不同
    println(p1 == p3) // 判断成员属性: true(对象不同, 成员属性值相同
    println(p1.eq(p2)) // 判断对象引用地址: false
    println(p1.eq(p3)) // 判断对象引用地址: false
  }
}