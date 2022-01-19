package com.ex2.test7

object Test7 {
  def main(args: Array[String]): Unit = {

    //### 固定一个 +数的场景: 就像配一把钥匙开100个门, 配100把钥匙开1万个门的场景 ###
    // 1) 固定一个参数
    def addByFour(b: Int): Int = {
      4 + b
    }
    def addByFive(b: Int): Int = {
      5 + b
    }
    println(addByFour(10)) // 14
    println(addByFive(20)) // 25

    // 2) 嵌套函数: 固定一个参数
    def addByFour1(): Int => Int = {
      val a = 4
      def addB(b: Int): Int = {
        a + b
      }
      addB
    }
    println(addByFour1()(100)) // 104

    // 3) 嵌套函数
    def addByA(a: Int): Int => Int = {
      def addB(b: Int): Int = {
        a + b
      }
      addB
    }
    println(addByA(20)(10)) // 30
    // 固定一个参数
    val addByFour2 = addByA(4) // 配4把钥匙
    val addByFive2 = addByA(5) // 配5把钥匙
    // 设置第二个参数, 并计算返回
    println(addByFour2(10)) // 14
    println(addByFive2(20)) // 25

    // 4) 将嵌套函数, 按 lambda表达式简化 1
    def addByA1(a: Int): Int => Int = {
      (b: Int) => {
        a + b
      }
    }
    // 将嵌套函数, 按 lambda表达式简化 2
    def addByA2(a: Int): Int => Int = {
      b => a + b
    }
    // 将嵌套函数, 按 lambda表达式简化 3
    def addByA3(a: Int): Int => Int = a + _
    // 固定一个参数
    val addByFour3 = addByA3(4) // 配4把钥匙
    val addByFive3 = addByA3(5) // 配5把钥匙
    // 设置第二个参数, 并计算返回
    println(addByFour3(13)) // 17
    println(addByFive3(25)) // 30

    // 5) 函数柯里化(函数柯里化中一定存在闭包)
    def addCurrying(a: Int)(b: Int): Int = {
      a + b
    }
    println(addCurrying(100)(200)) // 300


    // 其它: 计算器案例
    def calculate(a:Int)(b:Int)(op: (Int, Int) => Int) = op(a, b)
    println(calculate(10)(20)(_ + _)) // 30
    println(calculate(10)(20)(_ - _)) // -10
  }
}

