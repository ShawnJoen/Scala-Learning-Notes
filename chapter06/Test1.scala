package com.ex6.test1

object Test1 {
  def main(args: Array[String]): Unit = {
    try {
      val n = 10 / 0
    } catch {
      case e: ArithmeticException => println("发生算术异常！") // 捕获
      case e: Exception => {
        println("发生一般异常!")
      }
    } finally {
      println("处理结束")
    }

    def test1(): Nothing = {
      throw new Exception("发生异常!")
    }
    test1(); // java.lang.Exception: 自定义异常!

    @throws(classOf[NumberFormatException])
    def test2(arg: String) = {
      arg.toInt
    }
    println(test2("123")) // 123
    println(test2("abc")) // java.lang.NumberFormatException: For input string: "abc"
  }
}