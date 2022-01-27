package com.ex4.test11

object Test11 {
  def main(args: Array[String]): Unit = {
    // 创建4元组
    val tuple: (String, Int, Char, Boolean) = ("Shawn", 35, 'M', true)
    println(tuple) // (Shawn,35,M,true)

    // 访问数据( ._序号, 头一个从1开始
    println(tuple._1) // Shawn
    println(tuple._2) // 35
    println(tuple._3) // M
    println(tuple._4) // true

    // 通过索引访问数据
    println(tuple.productElement(1)) // 35

    // 遍历元组数据
    for (elem <- tuple.productIterator)
      print(elem + ",") // Shawn,35,M,true,
    println()

    // 嵌套元组
    val mulTuple = (10, 0.2, "Mia", (50, "Female"), 100)
    println(mulTuple._4._1) // 50

    // 元组形式创建 Map键值对
    val map = Map(("a",1), ("b",2), ("c",3))
    map.foreach(tuple => {
      println(tuple._1 + "=" + tuple._2)
    })
    //    a=1
    //    b=2
    //    c=3
  }
}
