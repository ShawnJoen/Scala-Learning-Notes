package com.ex5.test11

object Test11 {
  def main(args: Array[String]): Unit = {
    val pf: PartialFunction[Int, String] = {
      case 1 => "One"
      case 2 => "Two"
      case 3 => "Three"
      case _ => "Other"
    }
    println(pf(1)) // One
    println(pf(2)) // Two
    println(pf(5)) // Other
  }
}