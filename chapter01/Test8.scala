package com.ex.test8

object Test8 {
  def main(args: Array[String]): Unit = {
    //数值类型转 String
    var str1 : String = true + "" // true
    var str2 : String = 4.5 + "" // 4.5
    var str3 : String = 100 +"" // 100

    //String类型转数值类型(s1.toByte, s1.toShort, S1.toInt, s1.toLong, s1.toFloat, s1.toDouble)
    var s1 : String = "12"
    var n1 : Byte = s1.toByte
    var n2 : Short = s1.toShort
    var n3 : Int = s1.toInt
    var n4 : Long = s1.toLong
    var n5 : Float = s1.toFloat
    println(s1) // 12
    println(n1) // 12
    println(n2) // 12
    println(n3) // 12
    println(n4) // 12
    println(n5) // 12.0

    //无法将浮点数字符串, 直接转整数
    //val f2: Int = "12.3".toInt // 该行会抛 NumberFormatException异常
    val f3: Int = "12.3".toDouble.toInt // 需先转成浮点类型再转整数类型
  }
}
