package com.ex4.test16

object Test16 {
  def main(args: Array[String]): Unit = {
    val stringList: List[String] = List(
      "hello",
      "hello world",
      "hello scala",
      "hello spark from scala",
      "hello flink from scala"
    )
    // 1. 对字符串进行切分, 得到一个打散所有单词的列表
    //    val wordList1: List[Array[String]] = stringList.map(_.split(" "))
    //    val wordList2: List[String] = wordList1.flatten // 扁平化
    //    println(wordList2) // List(hello, hello, world, hello, scala, hello, spark, from, scala, hello, flink, from, scala)
    val wordList = stringList.flatMap(_.split(" "))
    println(wordList) // List(hello, hello, world, hello, scala, hello, spark, from, scala, hello, flink, from, scala)

    // 2. 相同的单词进行分组
    val groupMap: Map[String, List[String]] = wordList.groupBy(word => word)
    println(groupMap)
    /* Map(world -> List(world), flink -> List(flink), spark -> List(spark), scala -> List(scala, scala, scala),
     from -> List(from, from), hello -> List(hello, hello, hello, hello, hello))*/

    // 3. 对分组之后的 list取长度, 得到每个单词的个数
    val countMap: Map[String, Int] = groupMap.map(kv => (kv._1, kv._2.length))
    println(countMap) // Map(world -> 1, flink -> 1, spark -> 1, scala -> 3, from -> 2, hello -> 5)

    // 4. 将 map转换为 list, 并排序取前3
    val sortList: List[(String, Int)] = countMap.toList
      .sortWith(_._2 > _._2)
      .take(3)
    println(sortList) // List((hello,5), (scala,3), (from,2))
  }
}
