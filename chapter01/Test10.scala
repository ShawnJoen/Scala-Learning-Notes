package com.ex.test10

object Test10 {
  def main(args: Array[String]): Unit = {
    //Java:
    //byte b = 10;
    //b = (byte) (b + 1); // 该行会报错
    //b += 1; // 正常默认强转

    //Scala:
    var b: Byte = 10
    //b +=1 // 该行会报错, 如果 b是 Int则可以正常执行
  }
}
