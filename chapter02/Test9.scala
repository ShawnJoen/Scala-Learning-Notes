package com.ex2.test9

object Test9 {
  // 获取兔子的对数
  def rabbit(month: Int): Int = {
    if (month == 1 || month == 2) 1
    else rabbit(month - 1) + rabbit(month - 2)
  }

  def main(args: Array[String]): Unit = {
    // 获取第12个月的兔子对数
    val num = rabbit(12)
    println(num) // 144
  }
}
