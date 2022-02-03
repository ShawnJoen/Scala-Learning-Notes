package com.ex6.test5

object Test5 {
  // 1. 接收一个姓名, 在接受一个前缀, 后缀信息(隐式参数
  def show(name: String)(implicit delimit: (String, String)) = delimit._1 + name +
    delimit._2

  // 2. 定义一个单例对象, 给隐式参数设置默认值
  object ImplicitParam {
    implicit val delimit_defalut = "<<<" -> ">>>"
  }

  def main(args: Array[String]): Unit = {
    // 3. 导入隐式参数.
    import ImplicitParam.delimit_defalut
    println(show("Shawn")) // <<<Shawn>>>
    println(show("Mia")("(((" -> ")))")) // (((Mia)))
  }
}