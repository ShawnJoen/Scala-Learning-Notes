package com.ex5.test5

object Test5 {
  def main(args: Array[String]): Unit = {
    val email = "shawnjeon@163.com"
    val regex = """^[a-zA-Z0-9_\-]{2,}@([a-zA-Z0-9_\-]{2,})(\.[a-zA-Z0-9_\-]+)$""".r
    if (regex.findAllMatchIn(email).size != 0) {
      println("合法邮箱!")
    } else {
      println("非法邮箱")
    }
    // 合法邮箱!


    val emailList = List("83007003@qq.com", "shawnjeon@163.com", "qcl108@163.com", "quanchunlin.com")
    // 获取所有不合法邮箱
    val list = emailList.filter(regex.findAllMatchIn(_).size == 0)
    println(list)
    // List(quanchunlin.com)


    val result = emailList.map {
      case ele @ regex(company, ex) => (ele -> s"${company}${ex}")
      case ele => ele -> "未匹配"
    }
    println(result)
    // List((83007003@qq.com,qq.com), (shawnjeon@163.com,163.com), (qcl108@163.com,163.com), (quanchunlin.com,未匹配))
  }
}