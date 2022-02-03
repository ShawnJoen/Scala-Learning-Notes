package com.ex6.test10

object Test10 {
  class Super
  class Sub extends Super

  class Temp1[T] // 非变
  class Temp2[+T] // 协变
  class Temp3[-T] // 逆变

  def main(args: Array[String]): Unit = {
    // 演示非变
    val t1: Temp1[Sub] = new Temp1[Sub]
    //val t2:Temp1[Super] = t1 // 编译报错, 因为非变是 Super和 Sub有父子类关系, 但是 Temp1[Super]和 Temp1[Sub]之间没有关系

    // 演示协变
    val t3: Temp2[Sub] = new Temp2[Sub]
    val t4: Temp2[Super] = t3 // 不报错, 因为协变是 Super和 Sub有父子类关系, 所以 Temp2[Super]和 Temp2[Sub]之间也有父子关系
    // Temp2[Super]是父类型, Temp2[Sub]是子类型

    // 演示逆变
    val t5: Temp3[Super] = new Temp3[Super]
    val t6: Temp3[Sub] = t5 // 不报错, 因为逆变是 Super和 Sub有父子类关系, 所以 Temp3[Super]和 Temp3[Sub]之间也有子父关系
    // Temp3[Super]是子类型, Temp3[Sub]是父类型
  }
}