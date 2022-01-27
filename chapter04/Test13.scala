package com.ex4.test13

object Test13 {
  def main(args: Array[String]): Unit = {
    val list1 = List(3, 2, 1, -2, 5, 4)
    val list2 = List(("a", 2), ("b", 3), ("c", 4), ("d", 5), ("o", 6), ("f", -7))
    // 求和
    println(list1.sum) // 13

    // 求乘积
    println(list1.product) // -240( 3*2*1*-2*5*4

    // 最大值
    println(list1.max) // 5
    println(list2.maxBy((tuple: (String, Int)) => tuple._2)) // (e,6)
    println(list2.maxBy(_._2)) // (e,6)

    // 最小值
    println(list1.min) // -2
    println(list2.minBy(_._2)) // (f,-7)


    //1) sorted: 对一个集合进行自然排序, 通过传递隐式的 Ordering
    //2) sortBy: 对一个属性或多个属性进行排序
    //3) sortWith: 基于函数的排序, comparator函数, 实现自定义排序的逻辑

    // 排序 sorted
    val sortedList = list1.sorted
    println(sortedList) // List(-2, 1, 2, 3, 4, 5)

    // 从大到小逆序排序
    println(list1.sorted.reverse) // List(5, 4, 3, 2, 1, -2)

    // 传入隐式参数
    println(list1.sorted(Ordering[Int].reverse)) // List(5, 4, 3, 2, 1, -2)

    println(list2.sorted) // List((a,2), (b,3), (c,4), (d,5), (f,-7), (o,6))

    // sortBy
    println(list2.sortBy(_._2)) // List((f,-7), (a,2), (b,3), (c,4), (d,5), (o,6))
    println(list2.sortBy(_._2)(Ordering[Int].reverse)) // List((o,6), (d,5), (c,4), (b,3), (a,2), (f,-7))

    // sortWith
    println(list1.sortWith((a: Int, b: Int) => { // 升序排序
      a < b
    })) // List(-2, 1, 2, 3, 4, 5)
    println(list1.sortWith(_ < _)) // List(-2, 1, 2, 3, 4, 5) 升序排序
    println(list1.sortWith(_ > _)) // List(5, 4, 3, 2, 1, -2) 降序排序
  }
}
