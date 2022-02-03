package com.ex6.test6

object Test6 {
  // 1. 接收一个姓名, 在接受一个前缀, 后缀信息(隐式参数
  def show(name: String)(implicit delimit: (String, String)) = delimit._1 + name +
    delimit._2

  def main(args: Array[String]): Unit = {
    // 2. 自动导入隐式参数
    implicit val delimit_defalut = "<<<" -> ">>>"
    println(show("Shawn")) // <<<Shawn>>>
    println(show("Mia")("(((" -> ")))")) // (((Mia)))
  }
}