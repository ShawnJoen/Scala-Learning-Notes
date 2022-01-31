package com.ex5.test10

object Test10 {
  def div(a: Int, b: Int): Option[Int] = {
    if (b == 0) {
      None
    } else {
      Some(a / b)
    }
  }

  def main(args: Array[String]): Unit = {
    val result = div(10, 0)
    result match {
      case Some(x) => println(x)
      case None => println("除数不能为0")
    }
    // 除数不能为0
    println(result.getOrElse(0)) // 0
  }
}