package com.ex5.test9

object Test9 {
  // 1. 定义性别特质 Sex
  trait Sex
  // 2. 样例对象 Male
  case object Male extends Sex
  // 3. 样例对象 Female
  case object Female extends Sex
  // 4. 定义 Person样例类
  case class Person(name:String, sex:Sex)
  def main(args: Array[String]): Unit = {
    val p = Person("Shawn", Male)
    println(p) // Person(Shawn,Male)
  }
}