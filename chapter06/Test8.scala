package com.ex6.test8

object Test8 {
  class RichList(list: List[Int]) {
    // 1. 定义 avg()方法, 用来获取 List列表中所有元素的平均值
    def avg() = {
      if (list.size == 0) None else Some(list.sum / list.size)
    }
  }

  def main(args: Array[String]): Unit = {
    // 2. 定义隐式转换方法.
    implicit def list2RichList(list: List[Int]) = new RichList(list)
    val list1 = List(1, 2, 3, 4, 5)
    println(list1.avg()) // Some(3)
  }
}