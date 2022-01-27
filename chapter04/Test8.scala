package com.ex4.test8

import scala.collection.mutable

object Test8 {
  def main(args: Array[String]): Unit = {
    val set1: mutable.Set[Int] = mutable.Set(20, 10, 30, 10, 40)
    println(set1) // Set(30, 20, 10, 40)

    // 添加元素
    val set2 = set1 + 50 // set1自身不会被改动
    println(set1) // Set(30, 20, 10, 40)
    println(set2) // Set(30, 20, 50, 10, 40)

    set1 += 200 // 加200到 set1(加到自身
    //set1 +=: 100 // value +=: is not a member of Int
    println(set1) // Set(30, 20, 10, 40, 200)

    val flag1 = set1.add(300) // 可变集合, 建议使用英文字母函数
    println(flag1) // true(判断成功与否
    println(set1) // Set(30, 300, 20, 10, 40, 200)
    val flag2 = set1.add(30)
    println(flag2) // false
    println(set1) // Set(30, 300, 20, 10, 40, 200)

    // 删除元素
    set1 -= 10
    println(set1) // Set(30, 300, 20, 40, 200)

    val flag3 = set1.remove(20) // 通过指定元素值, 删除
    println(flag3) // true
    println(set1) // Set(30, 300, 40, 200)
    val flag4 = set1.remove(10)
    println(flag4) // false
    println(set1) // Set(30, 300, 40, 200)

    // 合并两个Set
    println(set2) // Set(30, 20, 50, 10, 40)
    val set3 = set1 ++ set2 // 新建集合
    println(set3) // Set(30, 300, 20, 50, 10, 40, 200)

    set1 ++= set2 // 将 set2合并到 set1(也就是set1为主体, 将 set2(扁平化后)新加到 set1, set2是不会变的
    println(set1) // Set(30, 300, 20, 50, 10, 40, 200)
  }
}

