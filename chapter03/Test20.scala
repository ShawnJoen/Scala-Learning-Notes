package com.ex3.test20

object Test20 {
  abstract class Template {
    // 该方法记录所有要执行的代码(需要子类实现
    def code()
    // 用来获取某些代码的执行时间(模板方法
    def getRuntime() = {
      // 获取当前时间毫秒值
      val start = System.currentTimeMillis()
      // 具体要执行的业务代码
      code()
      // 获取当前时间毫秒值
      val end = System.currentTimeMillis()
      // 返回消耗时间
      end - start
    }
  }
  //2. Demo类, 重写 getRuntime()模板方法, 用来输出 Hello, Scala!, 并返回执行时间
  class Demo extends Template {
    override def code(): Unit = for (i <- 1 to 5000) println("Hello, Scala!")
  }

  def main(args: Array[String]): Unit = {
    println(new Demo().getRuntime())
  }
}
