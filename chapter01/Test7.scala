package com.ex.test7

object Test7 {
  def main(args: Array[String]): Unit = {
    //Java: Long num = (long) 2.5 // 2 浮点转长整数存在精度损失
    //Scala:
    val num : Long = 2.5.toLong // 2
    val n1: Int = -2.9.toInt // -2

    val n: Int = 128
    val b: Byte = n.toByte
    // 溢出, 8位有符号补码 Byte整数, 正数最大值为127, 因此超出1, 会回到最小的-128
    println(b); // -128
  }
}
