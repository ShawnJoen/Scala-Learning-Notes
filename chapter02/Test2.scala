package com.ex2.test2

object Test2 {
  def main(args: Array[String]): Unit = {
    // 可变参数; 如果不传 s为空 WrappedArray()
    def f1(s: String*): Unit = { // 对应java的 String... s
      println(s)
      println(s.mkString(", "))
    }
    f1("shawn")
    // WrappedArray(shawn)
    // shawn
    f1("a", "b", "c")
    // WrappedArray(a, b, c)
    // a, b, c

    // 当参数列表中存在多个参数时, 可变参数一般放置在最后
    def f2(s: String, ss: String*): Unit = {
      println("s: " + s + " ss: " + ss.mkString(", "))
    }
    f2("shawn")
    // s: shawn ss:
    f2("a", "b", "c")
    // s: a ss: b, c

    // 参数默认值, 有默认值的参数一般放置在参数列表的后面
    def f3(seq: Int, name: String = "shawn"): Unit = {
      println(s"seq:${seq}, name=${name}")
    }
    f3(1)
    f3(2, "Tom")
    // seq:1, name=shawn
    // seq:2, name=Tom

    // 带名参数
    def f4(name: String = "shawn", age: Int): Unit = {
      println(s"name=${name}, age=${age}")
    }
    f4("Tom", 20)
    f4(age = 30, name = "Mia")
    // name=Tom, age=20
    // name=Mia, age=30
  }
}
