package com.ex4.test7

object Test7 {
  def main(args: Array[String]): Unit = {
    val set1 = Set(20, 10, 30, 10, 40)
    println(set1) // Set(20, 10, 30, 40)

    // 添加元素
    val set2 = set1 + 50
    println(set1) // Set(20, 10, 30, 40)
    println(set2) // Set(10, 20, 50, 40, 30)

    // 合并集合
    val set3 = Set(50,30,100,200)
    val set4 = set2 ++ set3
    println(set3) // Set(50, 30, 100, 200)
    println(set4) // Set(10, 20, 50, 40, 30, 200, 100)

    // 删除元素
    val set5 = set3 - 100 // 删除指定元素值
    println(set5) // Set(50, 30, 200)

    println(set3.contains(200)) // true 判断元素是否存在
    println(set3.contains(999)) // false
    println(set3.size) // 4 长度
  }
}
