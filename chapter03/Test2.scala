package object company {
  // 定义当前包共享的属性和方法
  val commonValue = "通用变量"
  def commonMethod() = {
    println(s"通用方法")
  }
}

package company {
  object Test2 {
    def main(args: Array[String]): Unit = {
      println(commonValue) // 通用变量
      commonMethod() // 通用方法
    }
  }

  package scala {
    package p1 {
      object Test2_1 {
        def main(args: Array[String]): Unit = {
          // 子包也可以使用上级包的通用成员
          println(commonValue) // 通用变量
          commonMethod() // 通用方法
        }
      }
    }
  }
}

package object company2 {
  val name: String = "shawn"
}

package company2 {
  package p2 {
    object Test2_2 {
      def main(args: Array[String]): Unit = {
        println(name) // shawn
      }
    }
  }
}