package com.ex4.test6

import scala.collection.mutable.ListBuffer

object Test6 {
  def main(args: Array[String]): Unit = {
    val list1: ListBuffer[Int] = new ListBuffer[Int]()
    println(list1) // ListBuffer()

    val list2 = ListBuffer(3, 2, 4) // 建议使用伴生对象
    println(list2) // ListBuffer(3, 2, 4)

    // 添加元素
    list1.append(20, 10)
    println(list1) // ListBuffer(20, 10)

    list2.prepend(1)
    println(list2) // ListBuffer(1, 3, 2, 4)

    list1.insert(1, 8, 9) // 索引位1, 插入两个元素一个8一个9
    println(list1) // ListBuffer(20, 8, 9, 10)

    18 +=: 19 +=: list1 += 11 += 12 // 可变集合改自身, 符号函数带(=)
    println(list1) // ListBuffer(18, 19, 20, 8, 9, 10, 11, 12)

    // 合并列表
    val list3 = list1 ++ list2 // 和不可变合并使用相同方式, 则返回新的列表, 自身不变
    println(list1) // ListBuffer(18, 19, 20, 8, 9, 10, 11, 12)
    println(list2) // ListBuffer(1, 3, 2, 4)
    println(list3) // ListBuffer(18, 19, 20, 8, 9, 10, 11, 12, 1, 3, 2, 4)

    list1 ++= list2 // 将 list2合并到 list1(也就是list1为主体, 将 list2新加到 list1的后面(尾部)), list2是不会变的
    println(list1) // ListBuffer(18, 19, 20, 8, 9, 10, 11, 12, 1, 3, 2, 4)
    println(list2) // ListBuffer(1, 3, 2, 4)

    list1 ++=: list2 // 将 list1合并到 list2(也就是list2为主体, 将 list1新加到 list2的前面(头部)), list1是不会变的
    println(list1) // ListBuffer(18, 19, 20, 8, 9, 10, 11, 12, 1, 3, 2, 4)
    println(list2) // ListBuffer(18, 19, 20, 8, 9, 10, 11, 12, 1, 3, 2, 4, 1, 3, 2, 4)

    // 修改元素
    list2(1) = 100 // 内部实现的就是.update
    list2.update(2, 200)
    println(list2) // ListBuffer(18, 100, 200, 8, 9, 10, 11, 12, 1, 3, 2, 4, 1, 3, 2, 4)

    // 删除元素
    list2.remove(2) // 通过索引位指定元素删除
    println(list2) // ListBuffer(18, 100, 8, 9, 10, 11, 12, 1, 3, 2, 4, 1, 3, 2, 4)

    list2 -= 3 // 删除值为3的元素, 每次只会删除一个, 优先删除越靠左的元素
    println(list2) // ListBuffer(18, 100, 8, 9, 10, 11, 12, 1, 2, 4, 1, 3, 2, 4)
  }
}
