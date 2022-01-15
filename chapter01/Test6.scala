package com.ex.test6

object Test6 {
  def main(args: Array[String]): Unit = {
    val n1: Double = 10 / 3 // 3.0: 计算时10和3都是 Int, 计算后转 Double
    println(n1)
    val n2: Double = 10.0 / 3 // 3.3333333...5(二进制表示小数有偏差): 其中某一个是浮点数(默认 Double), 所以按照 Double计算, 再赋值
    println(n2)
  }
}
