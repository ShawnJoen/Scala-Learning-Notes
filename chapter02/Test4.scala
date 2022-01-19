package com.ex2.test4

object Test4 {
  def main(args: Array[String]): Unit = {
    // 定义以函数作为参数的函数
    def f1(func: String => Unit): Unit = {
      func("shawn")
    }
    f1((name: String) => {
      println(name)
    })

    // 参数类型可以省略, 会根据形参(接收该匿名函数的函数参数)进行自动推导
    f1((name) => {
      println(name)
    })

    // 当参数只有1个时, 可以省略()圆括号; 但无参或有1个以上的参数的话, 必须写()
    f1(name => {
      println(name)
    })

    // 当函数体只有一行, 则可以省略{}大括号
    f1(name => println(name))

    // 参数在函数体内, 只使用一次, 则可以省略, 之后函数体内使用(_)下划线代替指定参数
    // 所有参数都只使用了一次, 甚至可以省略参数列表包括 (=>)
    f1(println(_))

    // 当传入的是一个函数体, 而不是调用语句, 可以直接省略下划线
    f1(println)
  }
}
