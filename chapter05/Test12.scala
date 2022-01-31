package com.ex5.test12

object Test12 {
  def main(args: Array[String]): Unit = {
    // 通过偏函数, 求绝对值 (对传的数据分为不同的情形: 正, 负, 0
    // 传入正数
    val positiveAbs: PartialFunction[Int, Int] = {
      case x if x > 0 => x
    }
    // 传入负数
    val negativeAbs: PartialFunction[Int, Int] = {
      case x if x < 0 => -x
    }
    // 传入0
    val zeroAbs: PartialFunction[Int, Int] = {
      case 0 => 0
    }

    def abs(x: Int): Int = (positiveAbs orElse negativeAbs orElse zeroAbs) (x)

    println(abs(-67)) // 67
    println(abs(35)) // 35
    println(abs(0)) // 0

    // 定义偏函数(返回 List集合的第二个元素
    val second: PartialFunction[List[Int], Option[Int]] = {
      case x :: y :: _ => Some(y)
    }
    // 上述代码会被 scala编译器翻译成以下代码, 这与普通函数多了一个用于参数检查的函数: isDefinedAt, 其返回类型为 Boolean
    /*val second = new PartialFunction[List[Int], Option[Int]] {
      // 检查输入参数是否合格
      override def isDefinedAt(list: List[Int]): Boolean = list match {
        case x :: y :: _ => true
        case _ => false
      }
      // 执行函数逻辑
      override def apply(list: List[Int]): Option[Int] = list match {
        case x :: y :: _ => Some(y)
      }
    }*/

    println(second(List(1,2,3))) // Some(2)
    // 这个偏函数, 不能直接调用, 因为这样会直接调用 apply方法. 而需要使用 applyOrElse方法, 并传入一个未模式匹配条件(用于不匹配时的返回)
    // println(second(List(8))) // 抛 scala.MatchError
    println(second.applyOrElse(List(1,2,3), (_: List[Int]) => None)) // Some(2)
    println(second.applyOrElse(List(8), (_: List[Int]) => None)) // None
  }
}