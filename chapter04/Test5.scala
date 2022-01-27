package com.ex4.test5

object Test5 {
  def main(args: Array[String]): Unit = {
    val list1 = List(20, 10, 30)
    println(list1) // List(20, 10, 30)

    // 底层没有索引, 但使用时, 可以索引形式使用(该功能是在 LinearSeqOptimized特征的 apply方法, 通过遍历实现
    println(list1(1)) // 10
    // list1(1) = 12 value update is not a member of List[Int] 由于是不可变列表

    // 遍历
    list1.foreach(println)

    // 添加元素
    val list2 = 1 +: list1 // 往前追加
    val list3 = list1 :+ 31 // 往后追加
    println(list1) // List(20, 10, 30)
    println(list2) // List(1, 20, 10, 30)
    println(list3) // List(20, 10, 30, 31)

    val list4 = list2.::(2) // (::)这个函数用于新增元素, 只会加到前面
    println(list4) // List(2, 1, 20, 10, 30)

    val list5 = Nil.::(100)
    println(list5) // List(100)
    val list6 = 10 :: 20 :: Nil
    val list7 = 20 :: 10 :: 30 :: 40 :: Nil
    println(list6) // List(10, 20)
    println(list7) // List(20, 10, 30, 40)

    // 合并列表
    val list8 = list6 :: list7 // (::)双:是新增元素的符号函数, 不是用于合并集合, 所以这样合并无法扁平化
    println(list8) // List(List(10, 20), 20, 10, 30, 40) 靠左的集合会占靠右的集合的一个元素位

    val list9 = list6 ::: list7 // (:::)三个:, 专用于合并集合的, 扁平化合并
    println(list9) // List(10, 20, 20, 10, 30, 40)

    val list10 = list6 ++ list7 // (++)双+, 效果和(:::)一样, 其实内部用了(:::)函数
    println(list10) // List(10, 20, 20, 10, 30, 40)

    println(list7.contains(30)) // true 判断元素是否存在
    println(list7.contains(999)) // false
    println(list7.size) // 4 长度
    println(list7.length) // 4 长度
  }
}
