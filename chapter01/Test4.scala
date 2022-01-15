package com.ex.test4

object Test4 {
  def main(args: Array[String]): Unit = {
    // 字符类型
    val c1: Char = 'a'
    val c2: Char = '9'
    // 控制字符
    val c3: Char = '\t' // 制表符
    val c4: Char = '\n' // 换行符
    // 转义字符
    val c5 = '\\' // 表示\自身
    val c6 = '\"' // 表示"
    // 字符变量底层保存ASCII码
    val i1: Int = c1
    println("i1: " + i1) // 97
    val i2: Int = c2
    println("i2: " + i2) // 57
    // 强转
    val c7: Char = (i1 + 1).toChar
    println(c7) // 97('a') + 1 = 'b'
    val c8: Char = (i2 - 1).toChar
    println(c8) // 57('9') - 1 = '8'
  }
}
