package com.ex2.test8

import scala.annotation.tailrec

object Test8 {
  def main(args: Array[String]): Unit = {
    println(fact(5)) // 120
    println(tailFact(5)) // 120
  }

  // 1) 求数字n的阶乘 公式: n! = n * (n - 1)! (这里的叹号指该数的阶乘
  // 2) 1的阶乘等于1, 即: 1! = 1
  // 规律:
  // 5! = 5 * 4 * 3 * 2 * 1 = 5 * 4!
  // 4! = 4 * 3 * 2 * 1 = 4 * 3!
  // 3! = 3 * 2 * 1 = 3 * 2!
  // 2! = 2 * 1 = 2 * 1!
  // 1! = 1

  // 递归实现计算阶乘
  def fact(n: Int): Int = {
    if (n == 0) return 1
    n * fact(n - 1)
  }

  // 尾递归实现: 调用自身的部分放置到函数执行过程的最后语句
  def tailFact(n: Int): Int = {
    // 该注解是 Scala提供的, 检查当前函数是否为正确的尾递归, 如不是则报错
    @tailrec
    def loop(n: Int, currRes: Int): Int = {
      if (n == 0) return currRes // 之前的结果放到该参数里
      loop(n - 1, currRes * n) // 最后语句是调用自身的部分
      // 4, 5
      // 3, 20
      // 2, 60
      // 1, 120
      // 0 return 120
    }
    loop(n, 1)
  }
}