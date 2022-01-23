package com.ex3.test19

object Test19 {
  class Message {
    def printMsg() = println("Hello, Shawn!")
  }
  trait Logger extends Message
  class ConsoleLogger extends Logger

  def main(args: Array[String]): Unit = {
    val cl = new ConsoleLogger
    cl.printMsg() // Hello, Shawn!
  }
}
