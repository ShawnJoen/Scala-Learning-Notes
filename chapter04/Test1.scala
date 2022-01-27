package com.ex4.test1

object Test1 {
  def main(args: Array[String]): Unit = {
    // 通过 new关键字创建数组
    val arr1 = new Array[Int](3)
    arr1(0) = 8 // 索引位0的元素值改为8(Scala编译时将代码该写成 arr1.update(0, 8)
    println(arr1) // [I@4c203ea1 输出的是地址, 因为 Array, 没有 toString()方法
    println(arr1.mkString(",")) // 8,0,0
    // 通过伴生对象创建数组(推荐
    val arr2 = Array(5,6,7)
    println(arr2.mkString(",")) // 5,6,7

    // 访问元素
    println(arr2(0)) // 5(获取索引位0的元素值 (Scala编译时会改写成 arr2.apply(0)

    // 修改元素值
    arr2(1) = 9 // 将索引位1的元素值更改为9
    println(arr2.mkString(",")) // 5,9,7
    // arr2(3) ArrayIndexOutOfBoundsException: 3 数组越界

    // 遍历数组 1
    for (i <- 0 until arr2.length) {
      print(arr2(i) + ",")
    }
    println()
    // 5,9,7,

    // 遍历数组 2 (遍历指定数组的所有索引
    println(arr2.indices.mkString(",")) // 0,1,2 输出所有索引
    for (i <- arr2.indices) print(arr2(i) + ",")
    println()
    // 5,9,7,

    // 遍历数组 3
    for (elem <- arr2) print(elem + ",")
    println()
    // 5,9,7,

    // 遍历数组 4(迭代器
    val iter = arr2.iterator
    while (iter.hasNext) print(iter.next() + ",")
    println()
    // 5,9,7,

    // 遍历数组 5(foreach方法
    //    arr2.foreach((elem: Int) => println(elem)) 匿名函数方式
    //    arr2.foreach(println) 简化匿名函数

    val arr3 = Array(35, "Shawn") // 类型为 Any
    for (elem <- arr3) print(elem + ",") // 35,Shawn,
    println()

    // 添加元素
    val newArr = arr2.:+(10) // 数组的末尾新加元素(不可变数组, 新加元素只能生成新的
    println(arr2.mkString(",")) // 5,9,7 不变
    println(newArr.mkString(",")) // 5,9,7,10

    val newArr2 = newArr.+:(30) // 数组的开始处插入新元素
    println(newArr2.mkString(",")) // 30,5,9,7,10

    // 将以上新增元素方式简化
    val newArr3 = newArr2 :+ 99 // 加到末尾(计算顺序为从左到右
    println(newArr3.mkString(",")) // 30,5,9,7,10,99

    // 计算函数中(:)必须朝向数组本身, 再加符号(+)朝向要新增的元素值; 在这里元素值在前在后决定元素加到数组的末尾还是首位
    // val newArr4 = newArr3 +: 11 // value +: is not a member of Int
    val newArr4 = 11 +: newArr3 // 加到首位(计算顺序为从右到左
    println(newArr4.mkString(",")) // 11,30,5,9,7,10,99

    // 前后可以同时进行加减
    val newArr5 = 33 +: 22 +: newArr4 :+ 999 :+ 9
    println(newArr5.mkString(", ")) // 33, 22, 11, 30, 5, 9, 7, 10, 99, 999, 9
  }
}