package com.ex6.test2

class MyRichInt(val self: Int) {
  def myMax(n: Int): Int = if (n < self) self else n
  def myMin(n: Int): Int = if (n < self) n else self
}

object Test2 {
  def main(args: Array[String]): Unit = {
    // 普通方式
    val new12 = new MyRichInt(12)
    println(new12.myMax(15)) // 15

    // 隐式函数(一个参数列表中只能有一个参数
    implicit def test1(num: Int): MyRichInt = new MyRichInt(num)
    // 通过隐式转换给 Int类型增加方法
    println(12.myMax(15)) // 15
  }
}