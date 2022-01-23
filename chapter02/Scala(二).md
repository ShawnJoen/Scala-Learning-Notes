> - 面向对象编程: 构成问题的各个事务分解成各个对象, 建立对象的目的不是为了完成一个步骤, 而是为了描述解决问题步骤中的行为(`*对象的本质: 数据和行为的一个封装`)
> - 函数式编程: 将问题分解成一个个步骤, 并将每个步骤进行封装(函数), 通过调用这些封装好的步骤, 解决问题

# 函数式编程
- 函数式编程中的函数并不是指编程语言中的函数(或方法), 它是指数学意义上的函数, 即映射关系 y = f(x), 也就是 y和 x的对应关系
- 函数式编程关注的是业务逻辑(数据的映射关系, 即 y = f(x)
- 函数式编程并不关注计算机底层的实现, 它对人更加友好, 所以它的编译器更加复杂
- 优点: 代码简洁, 接近自然语言(易于理解), 易于"并发编程", 代码的热升级

- 特点: 不可变性(Immutable), 只要往函数传入相同的参数, 则永远是同一个结果(结果是不可变的), 得到的结果都是确定性的
- 使用场景: 并行处理大量数据集合时, 非常适合用函数式编程

# Scala函数
- 为完成某一功能的程序语句的集合, 称之为函数
- 函数和方法的区别:
1) Scala的类中的函数称之为方法(等同 Java)
2) Scala的基于对象调用的是方法(等同 Java)
3) Scala的方法可以进行重载和重写(等同 Java), 而函数没有重载和重写的概念
4) Scala中函数是可以定义在方法内的(嵌套定义)
```

object Test1 {
  // 定义方法
  def m1(name: String): Unit = {
    println("m1() " + name)
  }

  def main(args: Array[String]): Unit = {
    // 调用对象方法
    Test1.m1("shawn")

    // Scala可以在任何语法结构中声明任何语法
    import java.util.Date
    new Date()

    // 函数没有重载和重写的概念, 程序报错
    def f1(): Unit = {
      println("调用 f1()")
    }
    f1()
    //def f1(name: String): Unit = {} // method f1 is defined twice

    // 嵌套定义函数
    def f2(): Unit = {
      def f3(): Unit = {
        println("调用 f3()")
      }
      f3()
    }
    f2()
  }

  // 方法的重载或重写
  def main(): Unit = {}
}
> m1() shawn
> 调用 f1()
> 调用 f3()

```

## 函数参数
1) 可变参数, 当参数列表中存在多个参数时, 可变参数一般放置在最后
2) 参数默认值, 有默认值的参数一般放置在参数列表的后面
3) 带名参数
```

object Test2 {
  def main(args: Array[String]): Unit = {
    // 可变参数; 如果不传 s为空 WrappedArray()
    def f1(s: String*): Unit = { // 对应java的 String... s
      println(s)
      println(s.mkString(", "))
    }
    f1("shawn")
    // WrappedArray(shawn)
    // shawn
    f1("a", "b", "c")
    // WrappedArray(a, b, c)
    // a, b, c

    // 当参数列表中存在多个参数时, 可变参数一般放置在最后
    def f2(s: String, ss: String*): Unit = {
      println("s: " + s + " ss: " + ss.mkString(", "))
    }
    f2("shawn")
    // s: shawn ss:
    f2("a", "b", "c")
    // s: a ss: b, c

    // 参数默认值, 有默认值的参数一般放置在参数列表的后面
    def f3(seq: Int, name: String = "shawn"): Unit = {
      println(s"seq:${seq}, name=${name}")
    }
    f3(1)
    f3(2, "Tom")
    // seq:1, name=shawn
    // seq:2, name=Tom

    // 带名参数
    def f4(name: String = "shawn", age: Int): Unit = {
      println(s"name=${name}, age=${age}")
    }
    f4("Tom", 20)
    f4(age = 30, name = "Mia")
    // name=Tom, age=20
    // name=Mia, age=30
  }
}

```

## 函数精简化
- 简化细节
1) 函数体的最后一行代码为返回值, 该行可以省略 return
2) 当函数体只有一行代码, 函数体可以省略花括号
3) 当返回值类型可以推断出时, 可以省略返回类型包括(:), 只保留等号
4) 而函数体内使用了 return, 则不能省略返回值类型
5) 无返回值的函数可以省略包括等号
6) 某个函数无参, 但又声明了参数列表()括号, 当调用该函数时, 小括号, 可加或可不加
7) 如果函数没有参数列表()括号, 那么调用时小括号必须省略
8) 如果不关心函数名称, 只需要逻辑处理, 那么函数名包括(def)可以省略
```

object Test3 {
  def main(args: Array[String]): Unit = {
    // 函数体的最后一行代码为返回值, 该行可以省略 return
    def f1(name: String): String = {
      name
    }
    println(f1("1 shawn")) // 1 shawn

    // 当函数体只有一行代码, 函数体可以省略花括号
    def f2(name: String): String = name
    println(f2("2 shawn")) // 2 shawn

    // 当返回值类型可以推断出时, 可以省略返回类型包括(:), 只保留等号
    def f3(name: String) = name
    println(f3("3 shawn")) // 3 shawn

    // 而函数体内使用了 return, 则不能省略返回值类型
//    def f4(name: String) = {
//        return name
//    }
//    println(f4("4 shawn"))

    // 无返回值的函数可以省略包括等号
    def f5(name: String) {
      println(name)
    }
    println(f5("5 shawn"))
    // 5 shawn
    // ()

    // 某个函数无参, 但又声明了参数列表()括号, 当调用该函数时, 小括号, 可加或可不加
    def f6(): Unit = {
      println("6 shawn")
    }
    f6() // 6 shawn
    f6 // 6 shawn

    // 如果函数没有参数列表()括号, 那么调用时小括号必须省略
    def f7: String = "7 shawn"
    // f7()
    println(f7) // 7 shawn

    // 如果不关心函数名称, 只需要逻辑处理, 那么函数名包括(def)可以省略(匿名函数, lambda表达式
    def f8 = (name: String) => {
      println(name)
    }
    def f9(f: String => Unit) = { // 参数为匿名函数(lambda表达式)
      f("8 shawn")
    }
    f9(f8) // 8 shawn
    f9((name: String) => println(name)) // 传入匿名函数: 8 shawn
  }
}

```

## 匿名函数(Lambda)
- 基本语法结构 (x: Int) => { 函数体 }
- 匿名函数精简化细节:
1) 参数类型可以省略, 会根据形参(接收该匿名函数的函数参数)进行自动推导
2) 当参数只有1个时, 可以省略()圆括号; 但无参或有1个以上的参数的话, 必须写()
3) 当函数体只有一行, 则可以省略{}大括号
4) 参数在函数体内, 只使用一次, 则可以省略, 之后函数体内使用(_)下划线代替指定参数
5) 所有参数都只使用了一次, 甚至可以省略参数列表包括 (=>)
```

object Test4 {
  def main(args: Array[String]): Unit = {
    // 定义以函数作为参数的函数
    def f1(func: String => Unit): Unit = {
      func("shawn")
    }
    f1((name: String) => {
      println(name)
    })

    // 参数类型可以省略, 会根据形参(接收该匿名函数的函数参数)进行自动推导
    f1((name) => {
      println(name)
    })

    // 当参数只有1个时, 可以省略()圆括号; 但无参或有1个以上的参数的话, 必须写()
    f1(name => {
      println(name)
    })

    // 当函数体只有一行, 则可以省略{}大括号
    f1(name => println(name))

    // 参数在函数体内, 只使用一次, 则可以省略, 之后函数体内使用(_)下划线代替指定参数
    // 所有参数都只使用了一次, 甚至可以省略参数列表包括 (=>)
    f1(println(_))

    // 当传入的是一个函数体, 而不是调用语句, 可以直接省略下划线
    f1(println)
  }
}

```
> 1) 函数可作为值进行传递(类似赋予额外的名称)
> 2) 函数可作为参数进行传递
> 3) 函数可作为函数返回值返回
```

object Test5 {
  def main(args: Array[String]): Unit = {

	### 函数可作为值进行传递(类似赋予额外的名称) ###
    def f1(): Int = {
      println(s"调用 f1()")
      123
    }
    // 调用 f1函数, 将返回值赋给 a和 b变量中
    val a = f1()
    println(s"a=${a}")
    // 调用 f1()
    // a=123
    val b = f1
    println(s"b=${b}")
    // 调用 f1()
    // b=123

    def f2(arg: String, arg2: String = "shawn"): Int = {
      println(s"调用 f2(${arg}, ${arg2})")
      222
    }
    // 在调用 f2函数处后方 + 空格_ 或(_); (语义为将函数 f2当做一个整体, 传递给变量 c)
    //val c = f2(_, _) // 加了()括号的话, _下划线需要按个数指定
    val c = f2 _
    println(f2("直接调用"))
    // 调用 f2(直接调用, shawn)
    // 222
    println(c); // 输出 f2函数的引用地址
    // test.Test5$$$Lambda$5/431687835@4563e9ab
    // println(c("通过变量 c调用")) f2函数, 虽使用了参数默认值, 不过通过引用变量调用该函数时, 是必须传入值的
    // not enough arguments for method apply: (v1: String, v2: String)Int in trait Function2.
    // Unspecified value parameter v2.
    println(c("通过变量 c调用", "춘림"))
    // 调用 f2(通过变量 c调用, 춘림)
    // 222
    // 如果明确变量类型, 则不使用下划线, 也可以将函数作为整体传递给变量; 否则就得写 f2(_, _)或 f2 _
    val d: (String, String) => Int = f2
    println(d("通过变量 d调用", "ddd"))
    // 调用 f2(通过变量 d调用, ddd)
    // 222


	### 函数可作为参数进行传递 ###
    // 函数 f3, 参数 func为函数(lambda表达式), 该参数函数的参数是(Int, Int)表示两个 Int类型, 返回值是 :Int
    def f3(func: (Int, Int) => Int, a: Int, b: Int): Int = {
      func(a, b)
    }
    // 定义一个函数, 用于 f3函数的参数, 参数类型和返回值类型必须一致
    def add(a: Int, b: Int): Int = a + b
    println(f3(add, 10, 20)) // 30
    // add函数的参数在函数体内都只使用了一次, 因此, 可以省略参数列表包括(=>), 再将指定参数按顺序写(_)
    println(f3(_+_, 20, 20)) // 40
    // 如果要(-)减操作, 并第1个参数和第2个参数反过来
    println(f3(-_ + _, 5, 10)) // 5


	### 函数可作为函数返回值返回 ###
    /*def f4() = {
      def f5(a: Int) = {
        println(a)
      }
      f5 _ // f4()函数如果定义了, 返回值类型, 则可以省略(_)
    }*/
    def f4(): Int => Unit = {
      def f5(a: Int): Unit = {
        println(a)
      }
      f5
    }
    val e = f4()
    // 返回 f5内部函数的引用地址
    println(e) // test.Test5$$$Lambda$9/2101440631@7dc36524
    f4()(100) // 100
    e(200) // 200
  }
}

```

- 匿名函数参数使用实例: &函数简化过程
```

object Test6 {
  def main(args: Array[String]): Unit = {
	### 将集合的每个元素值+1或-1(Map映射) ###
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


    ### Filter过滤 ###
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


    ### Reduce聚合 ###
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


    ### 操作1和2两个数, 具体运算通过参数传入 ###
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


	### 三个类型为 Int，String，Char的参数比较, 同时 Int=0, String="", Char='0'则 false, 否则 true
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

```

### 函数柯里化(Currying) &闭包(Closure)
- 闭包: 内层函数访问到它的外层定义的变量或参数, 该函数所处的环境叫做闭包
> `*解析嵌套函数: def f0(i: Int): String => (Char => Boolean) {f1..{f2..}}的执行过程`
> `*闭包原理: 一般的执行过程是 f0(0)执行完后, 会在 JVM栈中探出, 同时会释放传入的 i参数(局部变量). 之后执行内部函数 f1("")再释放参数 s; 此时执行 f2时应该参数 i和 s应该不存在了; 不过闭包是可以使用外层函数的 i s c参数(局部变量). 外层栈帧释放后依然可以使用相关参数(局部变量)的, 原因是, Scala每次调用函数时, 都会在 JVM的堆空间中生成对象实例, 并将所有的相关变量保存到实例内部; 栈帧的出栈不影响堆中的实例`
- 柯里化: 将多个参数的函数列表, 分解成多个参数列表, 且每个参数列表中只留一个参数的过程(*其核心原理是闭包
```

object Test7 {
  def main(args: Array[String]): Unit = {

    ### 固定一个 +数的场景: 就像配一把钥匙开100个门, 配100把钥匙开1万个门的场景 ###
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

```

## 递归(Recursion)
- 一个函数在该函数体内调用了自己, 称之为递归调用
- 递归原理: 递归调用当前函数时, 每个递归调用的函数不会在 JVM栈中弹出, 而是一直叠加栈帧(递归多了 JVM会抛 StackOverFlow), 直到结束递归后一一出栈
- `*纯函数式编程是没有变量的以及没有循环语法的 比如 Haskell, 所以循环相关的需求是通过递归处理的`
- 在函数式编程语言通常可以优化递归方式; 在 Scala递归中, 可以将调用自身的部分放置到函数执行过程的最后语句做成函数的返回值;  这样递归调用, 则不会每次递归时, 压栈叠加, 而是调用一次弹出一次直到结束(可以理解成后面的栈帧都是覆盖前面的栈帧, 也就是一个递归只会耗费一份栈帧, 不会消耗太多的栈资源), 这种递归叫做`尾递归`
- 注意事项:
1) 方法必须要有跳出的逻辑
2) 递归必须要有规律
3) 构造方法不能递归
4) 递归方法必有 返回值的数据类型
- 求阶乘案例:
```
package com.ex2.test8

import scala.annotation.tailrec

object Test8 {
  def main(args: Array[String]): Unit = {
    println(fact(5)) // 120
    println(tailFact(5)) // 120
  }

  // 1) 求数字n的阶乘 公式: n! = n * (n - 1)! (这里的叹号指该数的阶乘
  // 2) 1的阶乘等于1, 即: 1! = 1
  // 规律:
  // 5! = 5 * 4 * 3 * 2 * 1 = 5 * 4!
  // 4! = 4 * 3 * 2 * 1 = 4 * 3!
  // 3! = 3 * 2 * 1 = 3 * 2!
  // 2! = 2 * 1 = 2 * 1!
  // 1! = 1

  // 递归实现计算阶乘
  def fact(n: Int): Int = {
    if (n == 0) return 1
    n * fact(n - 1)
  }

  // 尾递归实现: 调用自身的部分放置到函数执行过程的最后语句
  def tailFact(n: Int): Int = {
    // 该注解是 Scala提供的, 检查当前函数是否为正确的尾递归, 如不是则报错
    @tailrec
    def loop(n: Int, currRes: Int): Int = {
      if (n == 0) return currRes // 之前的结果放到该参数里
      loop(n - 1, currRes * n) // 最后语句是调用自身的部分
      // 4, 5
      // 3, 20
      // 2, 60
      // 1, 120
      // 0 return 120
    }
    loop(n, 1)
  }
}

```
- 斐波那契(Fibonacci)数列案例
传说在罗马时期有个意大利青年叫斐波那契, 有一天他提出了一个非常有意思的问题:
1) 一对小兔子一个月之后会成长为一对大兔子.
2) 每一对大兔子每个月都会生一对小兔子.
3) 假设所有兔子都不死亡的情况下, 问: 1对小兔子, 1年之后会变为多少对兔子?
3.1) 思路分析
```
月份 | 兔子总对数 | 兔子详情
--|:--:|:--:
1 | 1 | 1对小兔子
2 | 1 | 1对大兔子
3 | 2 | 1对大兔子, 1对小兔子
4 | 3 | 2对大兔子, 1对小兔子
5 | 5 | 3对大兔子, 2对小兔子
12 | ? | ?
即: 已知数列1, 1, 2, 3, 5, 8, 13..., 问: 第12个数字是多少?

package com.ex2.test9

object Test9 {
  // 获取兔子的对数
  def rabbit(month: Int): Int = {
    if (month == 1 || month == 2) 1
    else rabbit(month - 1) + rabbit(month - 2)
  }

  def main(args: Array[String]): Unit = {
    // 获取第12个月的兔子对数
    val num = rabbit(12)
    println(num) // 144
  }
}

```
- 打印目录文件案例
```
package com.ex2.test10

import java.io.File

object Test10 {
  def printFilePath(dir: File): Unit = {
    if (!dir.exists()) {
      println("不存在的路径!")
    } else {
      val listFiles: Array[File] = dir.listFiles()
      for (filePath <- listFiles) {
        if (filePath.isFile) println(filePath) else printFilePath(filePath)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    printFilePath(new File("E:\\Download"))
  }
}

```

## 控制抽象(Control abstraction)
- 值调用: 就是普通传入不同的参数的过程
- 名调用: 把代码传递过去(控制抽象就是指`名调用`)
```
package com.ex2.test11

object Test11 {
  def main(args: Array[String]): Unit = {
    // 传值参数
    def f0(a: Int): Unit = {
      println("f0.a: " + a)
      println("f0.a: " + a)
    }
    f0(100)
    // f0.a: 100
    // f0.a: 100
    def f1(): Int = {
      println("f1调用")
      11
    }
    f0(f1())
    // f1调用
    // f0.a: 11
    // f0.a: 11


    //传名参数: a:=> Int意思为, 传入的代码块的返回值是 Int(注: 名调用的类型中是没有参数类型的, 而只有代码块的返回类型）
    // 传名参数: 传代码块
    def f2(a: => Int): Unit = {
      println("f2.a: " + a)
      println("f2.a: " + a)
    }
    // f2({200}) // 也是有效的参数
    f2(200) // 也是有效的参数
    // f2.a: 200
    // f2.a: 200
    f2(f1()) // 传函数也是有效的, 与值调用不同的是, f2内部有输出两次变量, 所以内部会执行两次f1函数(控制抽象就是指这部分); 这种参数不会在 f2(f1())调用时执行, 而是在 f2函数内部使用该参数时执行, 使用多少次执行多少次)
    // f1调用
    // f2.a: 11
    // f1调用
    // f2.a: 11
    f2({
      println("一个代码块")
      22
    })
    // 一个代码块
    // f2.a: 22
    // 一个代码块
    // f2.a: 22
  }
}

```

### 自定义 While循环
```
package com.ex2.test12

object Test12 {
  def main(args: Array[String]): Unit = {
    println("=== 1. 常规的 While循环")
    var n = 10 // 常规的 While循环, 需要定义一个变量在外部
    while (n >= 1) {
      print(n + ", ")
      n -= 1
    }
    println()

    println("=== 2. 用闭包实现一个函数, 将代码块作为参数传入(名调用), 递归调用")
    def myWhile(condition: => Boolean): (=> Unit) => Unit = {
      // 内层函数需要递归调用, 参数就是循环体
      def doLoop(op: => Unit): Unit = {
        if (condition) {
          op // print(n + ", "); n -= 1
          myWhile(condition)(op)
        }
      }
      doLoop
    }
    n = 10
    myWhile(n >= 1) ({
      print(n + ", ")
      n -= 1
    })
    println()

    n = 10
    myWhile(n >= 1) {
      print(n + ", ")
      n -= 1
    }
    println()

    n = 10
    myWhile(n >= 1) (n -= 1)
    println("n=" + n)

    println("=== 3. 用匿名函数实现")
    def myWhile2(condition: => Boolean): (=> Unit) => Unit = {
      // 内层函数需要递归调用, 参数就是循环体
      op => {
        if (condition) {
          op
          myWhile2(condition)(op)
        }
      }
    }
    n = 10
    myWhile2(n >= 1) {
      print(n + ", ")
      n -= 1
    }
    println()

    println("=== 4. 用柯里化实现")
    def myWhile3(condition: => Boolean)(op: => Unit): Unit = {
      if (condition) {
        op
        myWhile3(condition)(op)
      }
    }
    n = 10
    myWhile3(n >= 1) {
      print(n + ", ")
      n -= 1
    }
  }
}

=== 1. 常规的 While循环
10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 
=== 2. 用闭包实现一个函数, 将代码块作为参数传入(名调用), 递归调用
10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 
10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 
n=0
=== 3. 用匿名函数实现
10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 
=== 4. 用柯里化实现
10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 

```

## 惰性加载/懒加载(Lazy loading)
- 当函数将接收返回值的不可变变量(val)声明为 lazy时, 函数的执行将被推迟, 直到使用时才会执行该函数
- 类似于`名调用参数`
1) 但它不是作为函数的参数, 而是作为变量. 
2) 以及名调用参数每次都会重新执行该代码块, 而懒加载是只有首次执行并赋返回值, 第2次开始不会在调用该函数只是使用第1次获取到的值
- `*注: lazy不能修饰 var变量, 只能修饰 val常量`
```
package com.ex2.test13

object Test13 {
  def main(args: Array[String]): Unit = {
    lazy val result: Int = sum(10, 20) // lazy loading意味着将, sum(10, 30)整个代码传递 result常量中
    println("1. result = " + result) // 在此处会调用执行 sum(10, 20)
    println("2. result = " + result)
  }

  def sum(a: Int, b: Int): Int = {
    println("sum()调用")
    a + b
  }
}
sum()调用
1. result = 30
2. result = 30

```
