package com.ex5.test1

object Test1 {
  def main(args: Array[String]): Unit = {
    // 1. 基本定义语法
    val x: Int = 4
    val y: String = x match {
      case 1 => "one"
      case _ => "other"
    }
    println(y) // other

    // 2. 模式匹配实现简单二元运算
    val a = 20.0
    val b = 3
    def matchDualOp(op: Char): Double = op match {
      case '+' => a + b
      case '-' => a - b
      case '*' => a * b
      case '/' => a / b
      case '%' => a % b
      case ooo => -1
    }
    println(matchDualOp('+')) // 23.0
    println(matchDualOp('/')) // 6.666666666666667
    println(matchDualOp('?')) // -1.0

    // 3. 模式守卫
    // 求一个整数的绝对值
    def abs(num: Int): Int = {
      num match {
        case i if (i >= 0) => i
        case (i: Int) if i < 0 => { -i }
      }
    }
    println(abs(67)) // 67
    println(abs(0)) // 0
    println(abs(-24)) // 24( --转正
  }
}