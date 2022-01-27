package com.ex4.test12

object Test12 {
  def main(args: Array[String]): Unit = {
    println("############## Set:")
    val set1 = Set(1, 3, 5, 7, 9, 11)
    val set2 = Set(11, 33, 55, 77, 99, 111, 200)

    val union2 = set1.union(set2) // Set并集(合并)后会去重, 和 ++函数一样效果
    println("union: " + union2) // union: Set(5, 1, 33, 9, 77, 7, 3, 11, 99, 55, 200, 111)
    println(set1 ++ set2) // Set(5, 1, 33, 9, 77, 7, 3, 11, 99, 55, 200, 111)

    println("############## List:")
    val list1 = List(1, 3, 5, 7, 9, 11)
    val list2 = List(11, 33, 55, 77, 99, 111, 200)
    // 获取集合的头元素值
    println(list1.head) // 1

    // 获取集合的尾(不是头的就是尾
    println(list1.tail) // List(3, 5, 7, 9, 11)

    // 集合最后一个数据
    println(list2.last) // 200

    // 集合初始数据(不包含最后一个
    println(list2.init) // List(11, 33, 55, 77, 99, 111)

    // 反转(自身不改动
    println(list1.reverse) // List(11, 9, 7, 5, 3, 1)

    // 取前/后的 n个元素; 返回取出的元素 (自身不改动
    println(list1.take(3)) // List(1, 3, 5)
    println(list1.takeRight(4)) // List(5, 7, 9, 11)

    // 去掉前/后的 n个元素; 返回剩余的元素 (自身不改动
    println(list1.drop(3)) // List(7, 9, 11)
    println(list1.dropRight(4)) // List(1, 3)
    println(list1) // List(1, 3, 5, 7, 9, 11)

    // 并集(合并)
    val union = list1.union(list2) // 和 :::函数一样效果
    println("union: " + union) // union: List(1, 3, 5, 7, 9, 11, 11, 33, 55, 77, 99, 111, 200)
    println(list1 ::: list2) // List(1, 3, 5, 7, 9, 11, 11, 33, 55, 77, 99, 111, 200)

    println(list1) // List(1, 3, 5, 7, 9, 11)
    println(list2) // List(11, 33, 55, 77, 99, 111, 200)

    // 交集(只返回相同元素)
    val intersection = list1.intersect(list2)
    println("intersection: " + intersection) // intersection: List(11)

    // 差集(独有的不重复的元素
    val diff1 = list1.diff(list2) // 只返回不存在于 list2的 list1的元素
    println("diff1: " + diff1) // diff1: List(1, 3, 5, 7, 9)
    val diff2 = list2.diff(list1) // 只返回 list2的元素, 不存在于 list1的元素
    println("diff2: " + diff2) // diff2: List(33, 55, 77, 99, 111, 200)

    // 拉链(如果两个集合的元素个数不相等, 多余的元素会被省略(合并后就成了2元组的列表
    println("zip: " + list1.zip(list2)) // zip: List((1,11), (3,33), (5,55), (7,77), (9,99), (11,111))
    println("zip: " + list2.zip(list1)) // zip: List((11,1), (33,3), (55,5), (77,7), (99,9), (111,11))

    // 滑窗
    println(list2.sliding(3)) // <iterator>
    for (elem <- list1.sliding(3)) println(elem)
    //    List(1, 3, 5)
    //    List(3, 5, 7)
    //    List(5, 7, 9)
    //    List(7, 9, 11)

    // 滑窗(第二个参数为步长, 默认为1
    // 最后列表如果不够了, 也按不够的输出, 最后的元组可能达不到长度
    for (elem <- list1.sliding(5, 2)) println(elem)
    //    List(1, 3, 5, 7, 9)
    //    List(5, 7, 9, 11)
  }
}
