package com.ex.test9

object Test9 {
  def main(args: Array[String]): Unit = {
    //Java:
    //  1) == 比较字符串变量时, 对象的内存地址
    //  2) equals 比较字符串的内容
    //String s1 = "abc";
    //String s2 = new String("abc");
    //System.out.println(s1 == s2); // false
    //System.out.println(s1.equals(s2)); // true

    //Scala:
    //1) == 比较字符串的内容, 与 Java的 equals相同效果
    //2) eq 判断引用地址
    val s1 = "abc"
    val s2 = new String("abc")
    println(s1 == s2) // true
    println(s1.eq(s2)) // false
  }
}
