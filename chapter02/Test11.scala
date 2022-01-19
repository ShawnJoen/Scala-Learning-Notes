package com.ex2.test11

object Test11 {
  def main(args: Array[String]): Unit = {
    // 传值参数
    def f0(a: Int): Unit = {
      println("f0.a: " + a)
      println("f0.a: " + a)
    }
    f0(100)
    // f0.a: 100
    // f0.a: 100
    def f1(): Int = {
      println("f1调用")
      11
    }
    f0(f1())
    // f1调用
    // f0.a: 11
    // f0.a: 11


    //传名参数: a:=> Int意思为, 传入的代码块的返回值是 Int(注: 名调用的类型中是没有参数类型的, 而只有代码块的返回类型）
    // 传名参数: 传代码块
    def f2(a: => Int): Unit = {
      println("f2.a: " + a)
      println("f2.a: " + a)
    }
    // f2({200}) // 也是有效的参数
    f2(200) // 也是有效的参数
    // f2.a: 200
    // f2.a: 200
    f2(f1()) // 传函数也是有效的, 与值调用不同的是, f2内部有输出两次变量, 所以内部会执行两次f1函数(控制抽象就是指这部分); 这种参数不会在 f2(f1())调用时执行, 而是在 f2函数内部使用该参数时执行, 使用多少次执行多少次)
    // f1调用
    // f2.a: 11
    // f1调用
    // f2.a: 11
    f2({
      println("一个代码块")
      22
    })
    // 一个代码块
    // f2.a: 22
    // 一个代码块
    // f2.a: 22
  }
}