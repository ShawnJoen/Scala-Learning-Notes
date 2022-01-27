package com.ex4.test2

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object Test2 {
  def main(args: Array[String]): Unit = {
    val arr1: ArrayBuffer[Int] = new ArrayBuffer[Int]() // 通过 new关键字(无参创建, 默认长度为16
    println(arr1) // ArrayBuffer()
    val arr2 = ArrayBuffer(5, 4, 6) // 通过伴生对象
    println(arr2)  // ArrayBuffer(5, 4, 6)

    // 访问元素
    // println(arr1(0)) IndexOutOfBoundsException: 0 索引溢出
    println(arr2(1)) // 4 获取索引位1的元素值
    arr2(1) = 40 // 将索引位1的元素值更改为40
    println(arr2) // ArrayBuffer(5, 40, 6)

    // 添加元素
    // 1. 可变数组同样可以使用符号函数来计算, 但不建议这样用
    // 2. arr1追加元素值20, 但可变数组特点是修改自身的元素个数, 如按照以下方式计算, 效果是和不可变一样会创建新数组, 自身是不会变化的
    val newArr1 = arr1 :+ 20
    println(arr1) // ArrayBuffer() 依然是空数组
    println(newArr1) // ArrayBuffer(20)
    println(arr1 == newArr1) // false 新生成的数组和原数组不关联的

    arr1 += 30 // 可变数组追加并改自身元素个数(计算顺序为从左到右-往后追加
    println(arr1) // ArrayBuffer(30)

    15 +=: arr1 // 加到首位(计算顺序为从右到左-往前追加
    println(arr1) // ArrayBuffer(15, 30)

    // 可变集合建议用英文名称的函数
    arr1.append(400, 500, 600) // 加到后面
    arr1.prepend(100, 200, 300) // 加到前面
    println(arr1) // ArrayBuffer(100, 200, 300, 15, 30, 400, 500, 600)

    arr1.insert(1, 101, 102, 103) // 索引位1, 插入1个或多个元素
    println(arr1) // ArrayBuffer(100, 101, 102, 103, 200, 300, 15, 30, 400, 500, 600)

    arr1.insertAll(8, arr2) // 索引位8, 插入 arr2数组的所有元素
    println(arr1) // ArrayBuffer(100, 101, 102, 103, 200, 300, 15, 30, 5, 40, 6, 400, 500, 600)
    arr1.prependAll(arr2) // 数组 arr1的开始处, 插入数组 arr2的所有元素;
    println(arr1) // ArrayBuffer(5, 40, 6, 100, 101, 102, 103, 200, 300, 15, 30, 5, 40, 6, 400, 500, 600)

    // 删除元素
    arr1.remove(2) // 删除索引位2的元素
    println(arr1) // ArrayBuffer(5, 40, 100, 101, 102, 103, 200, 300, 15, 30, 5, 40, 6, 400, 500, 600)

    arr1.remove(0, 2) // 删除索引位0, 开始2个元素(包含索引位0)
    println(arr1) // ArrayBuffer(100, 101, 102, 103, 200, 300, 15, 30, 5, 40, 6, 400, 500, 600)

    arr1.append(500, 500, 500)
    println(arr1) // ArrayBuffer(100, 101, 102, 103, 200, 300, 15, 30, 5, 40, 6, 400, 500, 600, 500, 500, 500)
    arr1 -= 500 // 删除值为500的元素, 每次只会删除一个, 优先删除越靠左的元素
    arr1 -= 500
    println(arr1) // ArrayBuffer(100, 101, 102, 103, 200, 300, 15, 30, 5, 40, 6, 400, 600, 500, 500)
  }
}
