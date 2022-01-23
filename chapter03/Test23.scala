package com.ex3.test23

// 定义枚举类对象
object WorkDay extends Enumeration {
  val MONDAY = Value(1, "Monday")
  val TUESDAY = Value(2, "TuesDay")
}

object Test23 {
  def main(args: Array[String]): Unit = {
    println(WorkDay.MONDAY) // Monday
  }
}
