package com.ex6.test9

object Test9 {
  def main(args: Array[String]): Unit = {
    // 协变和逆变
    val child: Parent = new Child // OOP的多态向上转型
    //    // 不变 [T] 默认( MyList[SubChild]和 MyList[Child]无父母关系
    //    val childList3: MyList[Parent] = new MyList[Child]

    //    // 协变 [+T] ( MyList[Parent]是 MyList[Child]的父类
    //    val childList1: MyList[Parent] = new MyList[Child]

    // 逆变 [-T] ( MyList[Child]是 MyList[Parent]的父类
    val childList2: MyList[Child] = new MyList[Parent]
  }
}
// 定义继承关系
class Parent {}
class Child extends Parent {}
// 定义带泛型的集合类型
class MyList[-E] {}