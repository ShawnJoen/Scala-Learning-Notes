package com.ex4.test18

import scala.collection.immutable.Queue
import scala.collection.mutable

object Test18 {
  def main(args: Array[String]): Unit = {
    // 创建一个可变队列
    val queue: mutable.Queue[String] = new mutable.Queue[String]()
    // 入队
    queue.enqueue("a", "b", "c", "ddd")
    println(queue) // Queue(a, b, c, ddd)
    println(queue.dequeue()) // a (出队
    println(queue) // Queue(b, c, ddd)
    println(queue.dequeue()) // b (出队
    println(queue) // Queue(c, ddd)

    queue.enqueue("d", "e")
    println(queue) // Queue(c, ddd, d, e)

    // 不可变队列
    val queue2: Queue[String] = Queue("a", "b", "c")
    val queue3 = queue2.enqueue("d") // 不可变自身不会更新, 而是会返回新的队列
    println(queue2) // Queue(a, b, c)
    println(queue3) // Queue(a, b, c, d)
  }
}
