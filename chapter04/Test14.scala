package com.ex4.test14

object Test14 {
  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
    // 过滤(选取偶数
    println(list.filter(_ % 2 == 0)) // List(2, 4, 6, 8)
    // 过滤(选取奇数
    println(list.filter(_ % 2 == 1)) // List(1, 3, 5, 7, 9)

    // map映射(把集合中每个数乘2
    println(list.map(_ * 2)) // List(2, 4, 6, 8, 10, 12, 14, 16, 18)
    // map映射(平方数
    println(list.map(x => x * x)) // List(1, 4, 9, 16, 25, 36, 49, 64, 81)

    val nestedList: List[List[Int]] = List(List(1, 2, 3), List(4, 5), List(6, 7, 8, 9))
    // 扁平化
    val flatList = nestedList(0) ::: nestedList(1) ::: nestedList(2)
    println(flatList) // List(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val flatList2 = nestedList.flatten
    println(flatList2) // List(1, 2, 3, 4, 5, 6, 7, 8, 9)

    val strings: List[String] = List("hello world", "hello scala", "hello java", "we study")
    // 扁平映射
    // 将一组字符串进行分词, 并保存成单词的列表
    val splitList: List[Array[String]] = strings.map(_.split(" ")) // 分词
    println(splitList) // List([Ljava.lang.String;@6a5fc7f7, [Ljava.lang.String;@3b6eb2ec, [Ljava.lang.String;@1e643faf, [Ljava.lang.String;@6e8dacdf)

    val flattenList = splitList.flatten // 打散扁平化
    println(flattenList) // List(hello, world, hello, scala, hello, java, we, study)

    val flatmapList = strings.flatMap(_.split(" ")) // 扁平映射
    println(flatmapList) // List(hello, world, hello, scala, hello, java, we, study)

    // groupBy分组
    val groupMap: Map[Int, List[Int]] = list.groupBy(_ % 2) // 分成奇偶两组
    println(groupMap) // Map(1 -> List(1, 3, 5, 7, 9), 0 -> List(2, 4, 6, 8))
    val groupMap2: Map[String, List[Int]] = list.groupBy(data => if (data % 2 == 0) "偶数" else "奇数")
    println(groupMap2) // Map(奇数 -> List(1, 3, 5, 7, 9), 偶数 -> List(2, 4, 6, 8))

    // 给定一组词汇, 按照单词的首字母进行分组
    val wordList = List("China", "America", "Korea", "Japan", "Canada", "Kasahstan")
    println(wordList.groupBy(_.charAt(0))) // Map(J -> List(Japan), A -> List(America), C -> List(China, Canada), K -> List(Korea, Kasahstan))
  }
}
