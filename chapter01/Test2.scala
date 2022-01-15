package com.ex.test2

object Test2 {
  def main(args: Array[String]): Unit = {
    val name: String = "alice"
    // 通过+号连接
    val age: Int = 18
    println(age + "岁")

    // *用于将一个字符串复制多次并拼接
    println(name * 3)

    // printf: 通过 %传值
    printf("%s%d岁\n", name, age)

    // s""字符串模板: 通过$获取变量值
    println(s"${name}${age}岁")

    // 在 Scala小数是默认为 Double, 所以定义 Float值时必须加f
    val num: Double = 2.3456
    // f"": 格式化模板字符串
    println(f"The num is ${num}%2.2f")
    // raw"": 将字符串数据按原始的输出
    println(raw"The num is ${num}%2.2f")

    // Double和 Float类型有格式化输出的方法
    val n: Double = 10.123
    println(n.formatted("%.2f")); // 10.12: 小数保留两位
    println(n.formatted("%1.2f")); // 10.12: 总个数包含(.)最少1位, 超过了也不会限制
    println(n.formatted("%5.1f")); //  10.1: 总个数包含(.)最少5位, 如果未达到前面补空格

    // 三引号包括字符串, 可以将多行字符串原格式输出
    // 对变量进行运算, 可以加${}
    val sql =
    s"""
       |select *
       |from
       |  student
       |where
       |  name = $name and age=${age+2}
       |""".stripMargin
    println(sql)
  }
}
