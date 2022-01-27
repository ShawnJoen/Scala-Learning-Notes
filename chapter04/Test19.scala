package com.ex4.test19

import scala.collection.immutable
import scala.collection.parallel.immutable.ParSeq

object Test19 {
  def main(args: Array[String]): Unit = {
    // 串行
    val result: immutable.IndexedSeq[Long] = (1 to 20).map(
      x => Thread.currentThread.getId
    )
    println(result)
    /*Vector(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)*/

    // 并行
    val result2: ParSeq[Long] = (1 to 20).par.map(
      x => Thread.currentThread.getId
    )
    println(result2)
    /*ParVector(11, 11, 11, 11, 11, 14, 14, 14, 14, 14, 12, 12, 12, 12, 12, 13, 13, 14, 14, 14)*/
  }
}
