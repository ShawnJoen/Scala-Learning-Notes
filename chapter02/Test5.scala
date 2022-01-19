package com.ex2.test5

object Test5 {
  def main(args: Array[String]): Unit = {

    //### 函数可作为值进行传递(类似赋予额外的名称) ###
    def f1(): Int = {
      println(s"调用 f1()")
      123
    }
    // 调用 f1函数, 将返回值赋给 a和 b变量中
    val a = f1()
    println(s"a=${a}")
    // 调用 f1()
    // a=123
    val b = f1
    println(s"b=${b}")
    // 调用 f1()
    // b=123

    def f2(arg: String, arg2: String = "shawn"): Int = {
      println(s"调用 f2(${arg}, ${arg2})")
      222
    }
    // 在调用 f2函数处后方 + 空格_ 或(_); (语义为将函数 f2当做一个整体, 传递给变量 c)
    //val c = f2(_, _) // 加了()括号的话, _下划线需要按个数指定
    val c = f2 _
    println(f2("直接调用"))
    // 调用 f2(直接调用, shawn)
    // 222
    println(c); // 输出 f2函数的引用地址
    // test.Test5$$$Lambda$5/431687835@4563e9ab
    // println(c("通过变量 c调用")) f2函数, 虽使用了参数默认值, 不过通过引用变量调用该函数时, 是必须传入值的
    // not enough arguments for method apply: (v1: String, v2: String)Int in trait Function2.
    // Unspecified value parameter v2.
    println(c("通过变量 c调用", "춘림"))
    // 调用 f2(通过变量 c调用, 춘림)
    // 222
    // 如果明确变量类型, 则不使用下划线, 也可以将函数作为整体传递给变量; 否则就得写 f2(_, _)或 f2 _
    val d: (String, String) => Int = f2
    println(d("通过变量 d调用", "ddd"))
    // 调用 f2(通过变量 d调用, ddd)
    // 222


    //### 函数可作为参数进行传递 ###
    // 函数 f3, 参数 func为函数(lambda表达式), 该参数函数的参数是(Int, Int)表示两个 Int类型, 返回值是 :Int
    def f3(func: (Int, Int) => Int, a: Int, b: Int): Int = {
      func(a, b)
    }
    // 定义一个函数, 用于 f3函数的参数, 参数类型和返回值类型必须一致
    def add(a: Int, b: Int): Int = a + b
    println(f3(add, 10, 20)) // 30
    // add函数的参数在函数体内都只使用了一次, 因此, 可以省略参数列表包括(=>), 再将指定参数按顺序写(_)
    println(f3(_+_, 20, 20)) // 40
    // 如果要(-)减操作, 并第1个参数和第2个参数反过来
    println(f3(-_ + _, 5, 10)) // 5


    //### 函数可作为函数返回值返回 ###
    /*def f4() = {
      def f5(a: Int) = {
        println(a)
      }
      f5 _ // f4()函数如果定义了, 返回值类型, 则可以省略(_)
    }*/
    def f4(): Int => Unit = {
      def f5(a: Int): Unit = {
        println(a)
      }
      f5
    }
    val e = f4()
    // 返回 f5内部函数的引用地址
    println(e) // test.Test5$$$Lambda$9/2101440631@7dc36524
    f4()(100) // 100
    e(200) // 200
  }
}
