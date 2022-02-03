package com.ex6.test11

object Test11 {
  def main(args: Array[String]): Unit = {
    // 上限(泛型必须为 Child或是它的子级
    def test1[A <: Child](a: A): Unit = {
      println(a.getClass.getName) // 查看当前类型
    }
    test1[Child](new SubChild) // com.ex6.test11.SubChild
    test1[Child](new Child) // com.ex6.test11.Child
    //test1[Parent](new Child) // 限制

    // 下限(泛型必须为 Child或是它的父级
    def test2[A >: Child](a: A): Unit = {
      println(a.getClass.getName) // 查看当前类型
    }
    test2[Parent](new Child) // com.ex6.test11.Child
    test2[Child](new Child) // com.ex6.test11.Child
    test2[Child](new SubChild) // com.ex6.test11.SubChild

    def test3[A <: Child](a:Class[A]): Unit = {
      println(a)
    }
    test3(classOf[SubChild]) // class com.ex6.test11.SubChild
    test3(classOf[Child]) // class com.ex6.test11.Child
    //test3(classOf[Parent]) // 限制
  }
}
// 定义继承关系
class Parent {}
class Child extends Parent {}
class SubChild extends Child {}