package com {
  // 父包内的类访问子包需要导包
  import com.company.scala.Inner
  // 在外层包中定义单例对象
  object Outer {
    var out: String = "out1"
    def main(args: Array[String]): Unit = {
      println(Inner.in) // in
      val inner: Inner = new Inner("shawn")
      inner.print // in: shawn, seq: 10
    }
  }

  package company {
    package scala {
      class Inner(var in: String, var seq: Int = 10) {
        // 输出属性
        def print(): Unit = {
          println(s"in: ${in}, seq: ${seq}")
        }
      }
      // 内层包中定义单例对象
      object Inner {
        var in: String = "in"
        def main(args: Array[String]): Unit = {
          println(Outer.out) // out1
          // 可以改外层类或伴生对象的属性值
          Outer.out = "out2"
          println(Outer.out) // out2
        }
      }
    }
  }
}

// 在同一文件中定义不同的包
package org {
  package firm {
    object Test {
      def main(args: Array[String]): Unit = {
        import com.company.scala.Inner
        println(Inner.in) // in
      }
    }
  }
}