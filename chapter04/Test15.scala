package com.ex4.test15

import scala.collection.mutable

object Test15 {
  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3, 4)
    // Reduce(归约)
    // def reduce[A1 >: A](op: (A1, A1) => A1): A1 = reduceLeft(op)
    println(list.reduce(_ + _)) // 10 (内部调用了 reduceLeft
    // def reduceLeft[B >: A](op: (B, A) => B): B = {...}
    println(list.reduceLeft(_ + _)) // 10 (从左往右加
    println(list.reduceRight(_ + _)) // 10 (从右往左加

    val list2 = List(3, 4, 5, 8, 10)
    println(list2.reduce(_ - _)) // -24; 3-4-5-8-10
    println(list2.reduceLeft(_ - _)) // -24
    println(list2.reduceRight(_ - _)) // 6; 3 - (4 - (5 - (8 - 10))); --转正

    // Fold(折叠)
    println(list.fold(10)(_ + _)) // 20; 10 + 1 + 2 + 3 + 4
    println(list.foldLeft(10)(_ - _)) // 0; 10 - 1 - 2 - 3 - 4
    // foldRight方法内部实际调用 foldLeft, 只不过反转了一下
    // override def foldRight[B](z: B)(op: (A, B) => B): B = reverse.foldLeft(z)((right, left) => op(left, right))
    println(list2.foldRight(11)(_ - _)) // -5; 3 - (4 - (5 - (8 - (10 - 11))))

    // ==== 合并集合
    val map1 = Map("a" -> 1, "b" -> 3, "c" -> 5)
    val map2 = mutable.Map("a" -> 2, "b" -> 4, "c" -> 6, "d" -> 8)

    // 默认后面的集合覆盖前面的集合中重复 key的 value
    println(map1 ++ map2) // Map(a -> 2, b -> 4, c -> 6, d -> 8)

    // 下面的方式, 由于 map2是作为初始值参数, 传入到内部后, 将 key重复的 value累加后最终值覆盖到 map2中, 并返回
    // op匿名函数的首个参数 mergedMap是传入的初始值 map2
    // def fold[A1 >: A](z: A1)(op: (A1, A1) => A1): A1 = foldLeft(z)(op)
    // def foldLeft[B](z: B)(@deprecatedName('f) op: (B, A) => B): B = {...}
    // 在此使用 foldLeft, 而不是 fold的原因就是, 查看源码可以发现 op匿名函数部分
    // 匿名函数参数 kv是 map1集合的键值元组
    val map3 = map1.foldLeft(map2)((mergedMap, kv) => {
      val key = kv._1
      val value = kv._2
      mergedMap(key) = mergedMap.getOrElse(key, 0) + value
      mergedMap
    }
    )
    println(map3 == map1) // false
    println(map3 == map2) // true
    println(map3) // Map(b -> 7, d -> 8, a -> 3, c -> 11)
  }
}
