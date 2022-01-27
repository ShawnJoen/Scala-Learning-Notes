package com.ex4.test4

object Test4 {
  def main(args: Array[String]): Unit = {
    // 创建一维数组
    val arr1 = Array.ofDim[Int](2)
    arr1(1) = 10
    println(arr1.mkString(",")) // 0,10

    // 创建二维数组(最多可以创建5维数组
    val arr2: Array[Array[Int]] = Array.ofDim[Int](2, 3) // 2行3列
    // 访问元素
    arr2(0)(2) = 19
    arr2(1)(0) = 25
    println(arr2.mkString(", ")) // [I@17c68925, [I@7e0ea639
    for (i <- arr2.indices; j <- arr2(i).indices) {
      print(arr2(i)(j) + "\t")
      if (j == arr2(i).length - 1) println()
    }
    //    0   0   19
    //    25	0   0

    // 通过 lambda函数, 输出
    arr2.foreach(line => line.foreach(println))
    arr2.foreach(_.foreach(println))
  }
}
