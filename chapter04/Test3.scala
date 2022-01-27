package com.ex4.test3

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object Test3 {
  def main(args: Array[String]): Unit = {
    val arr: ArrayBuffer[Int] = ArrayBuffer(2, 3, 1)
    val newArr: Array[Int] = arr.toArray // 可变数组转换为不可变数组
    println(arr) // ArrayBuffer(2, 3, 1)
    println(newArr.mkString(", ")) // 2, 3, 1

    val buffer: mutable.Buffer[Int] = newArr.toBuffer // 不可变数组转换为可变数组
    println(newArr.mkString(", ")) // 2, 3, 1
    println(buffer) // ArrayBuffer(2, 3, 1)
  }
}
