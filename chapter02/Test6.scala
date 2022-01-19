package com.ex2.test6

object Test6 {
  def main(args: Array[String]): Unit = {
    //### 将集合的每个元素值+1或-1(Map映射) ###
    // 参数为: 集合和逻辑函数
    def operation(arr: Array[Int], op: Int => Int) = {
      for (elem <- arr) yield op(elem)
    }
    // 定义逻辑函数
    def op(ele: Int): Int = {
      ele + 1
    }
    // 标准函数调用
    val arr = operation(Array(1, 2, 3, 4), op)
    println(arr.mkString(",")) // 2,3,4,5

    // 将逻辑函数改为匿名函数
    val arr1 = operation(Array(1, 2, 3, 4), (ele: Int) => {
      ele + 1
    })
    println(arr1.mkString(",")) // 2,3,4,5

    // 参数类型可以省略, 会根据形参(接收该匿名函数的函数参数)进行自动推导
    val arr2 = operation(Array(1, 2, 3, 4), (ele) => {
      ele + 1
    })
    println(arr2.mkString(",")) // 2,3,4,5

    // 当参数只有1个时, 可以省略()圆括号; 但无参或有1个以上的参数的话, 必须写()
    val arr3 = operation(Array(1, 2, 3, 4), ele => {
      ele + 1
    })
    println(arr3.mkString(",")) // 2,3,4,5

    // 当函数体只有一行, 则可以省略{}大括号
    val arr4 = operation(Array(1, 2, 3, 4), ele => ele + 1)
    println(arr4.mkString(",")) // 2,3,4,5

    // 参数在函数体内, 只使用一次, 则可以省略, 之后函数体内使用(_)下划线代替指定参数
    // 所有参数都只使用了一次, 甚至可以省略参数列表包括 (=>)
    val arr5 = operation(Array(1, 2, 3, 4), _ + 1)
    println(arr5.mkString(",")) // 2,3,4,5


    //### Filter过滤 ###
    import scala.collection.mutable.ArrayBuffer
    def filter(arr: Array[Int], op: Int => Boolean): Array[Int] = {
      var arrBuf: ArrayBuffer[Int] = ArrayBuffer[Int]()
      for (elem <- arr if op(elem)) {
        arrBuf.append(elem)
      }
      arrBuf.toArray
    }

    var res0 = filter(Array(1, 2, 3, 4), _ % 2 == 1)
    println("输出所有基数: " + res0.mkString(",")) // 输出所有基数: 1,3


    //### Reduce聚合 ###
    def reduce(arr: Array[Int], op: (Int, Int) => Int): Int = {
      var init: Int = arr(0)
      for (elem <- arr) {
        init = op(init, elem)
      }
      init
    }
    val res1 = reduce(Array(1, 2, 3), (x, y) => x * y)
    println(res1) // 6
    val res2 = reduce(Array(1, 2, 3), _ * _)
    println(res2) // 6


    //### 操作1和2两个数, 具体运算通过参数传入 ###
    def dualFunctionOneAndTwo(fun: (Int, Int) => Int): Int = {
      fun(1, 2)
    }
    val add = (a: Int, b: Int) => a + b
    val minus = (a: Int, b: Int) => a - b
    println("1: " + dualFunctionOneAndTwo(add)) // 1: 3
    println("2: " + dualFunctionOneAndTwo(minus)) // 2: -1
    println("3: " + dualFunctionOneAndTwo((a: Int, b: Int) => a + b)) // 3: 3
    println("4: " + dualFunctionOneAndTwo((a: Int, b: Int) => a - b)) // 4: -1
    println("5: " + dualFunctionOneAndTwo((a, b) => a + b)) // 5: 3
    println("6: " + dualFunctionOneAndTwo(_ + _)) // 6: 3
    println("7: " + dualFunctionOneAndTwo(_ - _)) // 7: -1
    println("8: " + dualFunctionOneAndTwo((a, b) => b - a)) // 8: 1
    println("9: " + dualFunctionOneAndTwo(-_ + _)) // 9: 1


    //### 三个类型为 Int，String，Char的参数比较, 同时 Int=0, String="", Char='0'则 false, 否则 true
    // 通过普通函数实现
    val fun = (i: Int, s: String, c: Char) => {
      if (i == 0 && s == "" && c == '0') false else true
    }
    println("fun: " + fun(0, "", '0')) // fun: false
    println("fun: " + fun(0, "", '1')) // fun: true

    // 通过嵌套函数实现(闭包(Closure)
    def f0(i: Int): String => (Char => Boolean) = {
      def f1(s: String): Char => Boolean = {
        def f2(c: Char): Boolean = {
          if (i == 0 && s == "" && c == '0') false else true
        }
        f2
      }
      f1
    }

    // f0执行完毕后, 参数(局部变量)i, 应该随着栈帧弹出同时释放掉
    val ff0 = f0(0)
    // 但对于  Scala调用函数, 会在 JVM的堆空间中生成对象实例. 再将该对象包含到 f2函数的内部形成闭合, 这样保留了外层变量的值在内部使用
    println("f0: " + ff0("")('0')) // f0: false
    println("f0: " + f0(35)("shawn")('1')) // f0: true

    // 通过匿名函数实现(Lambda表达式)
    def f3(i: Int): String => (Char => Boolean) = {
      s => c => if (i == 0 && s == "" && c == '0') false else true
    }
    println("f3: " + f3(0)("")('0')) // f3: false
    println("f3: " + f3(35)("shawn")('1')) // f3: true

    // 通过函数柯里化(Currying)
    def f4(i: Int)(s: String)(c: Char): Boolean = {
      if (i == 0 && s == "" && c == '0') false else true
    }
    println("f4: " + f4(0)("")('0')) // f4: false
    println("f4: " + f4(0)("")('1')) // f4: true
    println("f4: " + f4(30)("Mia")('0')) // f4: true
    println("f4: " + f4(35)("Shawn")('0')) // f4: true
  }
}
