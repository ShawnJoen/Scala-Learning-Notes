package com.ex3.test16

// 定义球类特征
trait Ball {
  def describe(): String = "ball"
}

// 定义颜色特征
trait Color extends Ball {
  var color: String = "red"
  override def describe(): String = color + "-" + super.describe()
}

// 定义种类特征
trait Category extends Ball {
  var category: String = "foot"
  override def describe(): String = category + "-" + super.describe()
}

// 定义一个自定义球的类
class MyBall extends Category with Color {
  //override def describe(): String = "My ball is a " + super[Category].describe() 指定执行, 目标特质的方法
  override def describe(): String = "My ball is a " + super.describe()
}

trait Knowledge {
  var amount: Int = 0
  def increase(): Unit = {
    println("Knowledge increased")
  }
}

trait Talent {
  def singing(): Unit
  def dancing(): Unit
  def increase(): Unit = {
    println("Talent increased")
  }
}

import com.ex3.test14.{Person}

class Student extends Person with Talent with Knowledge {
  override def dancing(): Unit = println("dancing")
  override def singing(): Unit = println("singing")
  override def increase(): Unit = {
    super[Person].increase()
  }
}

object Test16 {
  def main(args: Array[String]): Unit = {
    val student = new Student
    student.increase() // Person increase
    // 钻石问题特征叠加
    val myBall = new MyBall
    // 叠加继承顺序(super执行的优先顺序) MyBall -> Color -> Category -> Ball
    println(myBall.describe()) // My ball is a red-foot-ball
  }
}
