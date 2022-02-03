# 异常处理
- 为了不让程序异常终止, 使用 try-catch语句. 将可能会出异常的代码封装在 try块中, 当发生异常时, catch捕获异常, 并处理
1) Scala没有`checked(编译期)`异常, 而异常都是`Runtime异常`运行的时候捕获
2) 当异常发生, catch子句, 是按顺序捕捉的. 因此, 范围小的异常类写在前面, 范围大的异常类写在后面, 一旦前面的异常被捕获, 写在后面的异常类, 即使也在范围内也不会被捕获
3) finally子句是, 异常发生或未发生, 只要执行了 try语句, 最后肯定被执行的语句, 与 Java一样
4) 所有异常类都是 Throwable的子类. 通过 throw关键字, 抛异常类对象, 接收类型设置为 Nothing
5) 可能会抛异常的函数上方, 声明 @throws(classOf[Exception])异常类注解. 和 Java方法上的 throws关键字相同效果
```
package com.ex6.test1

object Test1 {
  def main(args: Array[String]): Unit = {
    try {
      val n = 10 / 0
    } catch {
      case e: ArithmeticException => println("发生算术异常！") // 捕获
      case e: Exception => {
        println("发生一般异常!")
      }
    } finally {
      println("处理结束")
    }

    def test1(): Nothing = {
      throw new Exception("发生异常!")
    }
    test1(); // java.lang.Exception: 自定义异常!

    @throws(classOf[NumberFormatException])
    def test2(arg: String) = {
      arg.toInt
    }
    println(test2("123")) // 123
    println(test2("abc")) // java.lang.NumberFormatException: For input string: "abc"
  }
}

```

# 隐式转换
- 当编译器第一次编译失败时, 编译器会尝试在当前作用域范围内查找能调用的转换规则, 如果找到了, 则会二次编译. 这个过程是由编译器完成的, 所以称之为隐式转换
- 隐式转换是 Scala独有的功能, 后续 Akka并发编程会经常用到它
- 值, 参数, 方法或函数和类, 都可以通过 implicit关键字声明为隐式(implicit关键字是在 Scala的2.10版本开始出现的

- 隐式函数案例 1: 
```
package com.ex6.test2

class MyRichInt(val self: Int) {
  def myMax(n: Int): Int = if (n < self) self else n
  def myMin(n: Int): Int = if (n < self) n else self
}

object Test2 {
  def main(args: Array[String]): Unit = {
    // 普通方式
    val new12 = new MyRichInt(12)
    println(new12.myMax(15)) // 15

    // 隐式函数(一个参数列表中只能有一个参数
    implicit def test1(num: Int): MyRichInt = new MyRichInt(num)
    // 通过隐式转换给 Int类型增加方法
    println(12.myMax(15)) // 15
  }
}

```
- 隐式函数案例 2:
```
package com.ex6.test3

import java.io.File
import scala.io.Source

object Test3 {
  class RichFile(file: File) {
    def read() = Source.fromFile(file).mkString
  }
  // 单例对象中定义隐式转换
  object ImplicitDemo {
    // 隐式函数(一个参数列表中只能有一个参数
    implicit def file2RichFile(file: File) = new RichFile(file)
  }

  def main(args: Array[String]): Unit = {
    import ImplicitDemo.file2RichFile
    val file = new File("chapter06/test.txt")
    println(file.read())
  }
}
//hello world
//hello scala
//你好 Shawn!
//函数式编程!

```

- 隐式参数:
> - 调用声明隐式参数的方法时, 编译器会在当前作用域内寻找符合条件的隐式值
> 1) 同一个作用域中, 相同类型的隐式值只能有一个, 因为, 编译器是按照隐式参数的类型去寻找对应的隐式值, 与隐式值的名称无关
> 2) 隐式参数优先于默认参数
>
> `*通过 implicitly函数获取隐式变量(标准库提供的泛型的 type class interface), 在当前作用域内查找对应类型的隐式变量`
```
package com.ex6.test4

object Test4 {
  def main(args: Array[String]): Unit = {
    // 隐式值
    implicit val name: String = "Shawn"
    implicit val num: Int = 18
    // 隐士参数
    def sayHello()(implicit arg: String): Unit = {
      println("hello, " + arg)
    }
    sayHello // hello, Shawn
    sayHello()("Sam") // hello, Sam

    def sayHi(implicit arg: String = "Mia"): Unit = {
      println("hi, " + arg)
    }
    sayHi // hi, Shawn
    sayHi("Tom") // hi, Tom

    // 代码简化
    def hiAge(): Unit = {
      println("hi, " + implicitly[Int])
    }
    hiAge() // hi, 18
  }
}


package com.ex6.test5

object Test5 {
  // 1. 接收一个姓名, 在接受一个前缀, 后缀信息(隐式参数
  def show(name: String)(implicit delimit: (String, String)) = delimit._1 + name +
    delimit._2

  // 2. 定义一个单例对象, 给隐式参数设置默认值
  object ImplicitParam {
    implicit val delimit_defalut = "<<<" -> ">>>"
  }

  def main(args: Array[String]): Unit = {
    // 3. 导入隐式参数.
    import ImplicitParam.delimit_defalut
    println(show("Shawn")) // <<<Shawn>>>
    println(show("Mia")("(((" -> ")))")) // (((Mia)))
  }
}


package com.ex6.test6

object Test6 {
  // 1. 接收一个姓名, 在接受一个前缀, 后缀信息(隐式参数
  def show(name: String)(implicit delimit: (String, String)) = delimit._1 + name +
    delimit._2

  def main(args: Array[String]): Unit = {
    // 2. 自动导入隐式参数
    implicit val delimit_defalut = "<<<" -> ">>>"
    println(show("Shawn")) // <<<Shawn>>>
    println(show("Mia")("(((" -> ")))")) // (((Mia)))
  }
}

```
- 隐式类:
1) 隐式类的构造器必须有参, 且只能有一个
2) 隐式类必须被定义在“类”或“伴生对象”或“包对象”里, 即隐式类不能是顶级类
```
package com.ex6.test7

object Test7 {
  def main(args: Array[String]): Unit = {
    // 隐式类
    implicit class MyRichInt(val self: Int) {
      // 自定义比较大小的方法
      def myMax(n: Int): Int = if (n < self) self else n
      def myMin(n: Int): Int = if (n < self) n else self
    }
    println(12.myMin(15)) // 12
  }
}

```
- 列表加获取所有元素平均值的方法
```
package com.ex6.test8

object Test8 {
  class RichList(list: List[Int]) {
    // 1. 定义 avg()方法, 用来获取 List列表中所有元素的平均值
    def avg() = {
      if (list.size == 0) None else Some(list.sum / list.size)
    }
  }

  def main(args: Array[String]): Unit = {
    // 2. 定义隐式转换方法.
    implicit def list2RichList(list: List[Int]) = new RichList(list)
    val list1 = List(1, 2, 3, 4, 5)
    println(list1.avg()) // Some(3)
  }
}

```

# 泛型
## 协变和逆变
- 用于泛型参数的类的父子级关系, 通过协/逆变影响当前类的父子关系

`*不变(非变): Child是 Parent的子类, 则 MyList[Parent]与 MyList[Child]“无父子关系”`
`*协变: Child是 Parent的子类, 则 MyList[Child]也作为 MyList[Parent]的“子类”`
`*逆变: Child是 Parent的子类, 则 MyList[Child]作为 MyList[Parent]的“父类”`

- 语法:
class MyList[T] {} // 不变(非变; 默认
class MyList[+T] {} // 协变
class MyList[-T] {} // 逆变

- 列子 1:
```
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

```
- 列子 2:
```
package com.ex6.test10

object Test10 {
  class Super
  class Sub extends Super

  class Temp1[T] // 非变
  class Temp2[+T] // 协变
  class Temp3[-T] // 逆变

  def main(args: Array[String]): Unit = {
    // 演示非变
    val t1: Temp1[Sub] = new Temp1[Sub]
    //val t2:Temp1[Super] = t1 // 编译报错, 因为非变是 Super和 Sub有父子类关系, 但是 Temp1[Super]和 Temp1[Sub]之间没有关系

    // 演示协变
    val t3: Temp2[Sub] = new Temp2[Sub]
    val t4: Temp2[Super] = t3 // 不报错, 因为协变是 Super和 Sub有父子类关系, 所以 Temp2[Super]和 Temp2[Sub]之间也有父子关系
    // Temp2[Super]是父类型, Temp2[Sub]是子类型

    // 演示逆变
    val t5: Temp3[Super] = new Temp3[Super]
    val t6: Temp3[Sub] = t5 // 不报错, 因为逆变是 Super和 Sub有父子类关系, 所以 Temp3[Super]和 Temp3[Sub]之间也有子父关系
    // Temp3[Super]是子类型, Temp3[Sub]是父类型
  }
}

```

## 泛型上下限(上下界)
- 泛型的上下限的作用是对传入的泛型进行限定(限定该泛型必须从哪个类继承, 或必须是哪个类的父类或子类等)
- 如果泛型, 既有上界, 又有下界. 则下界写在前面, 上界写在后面. 即: [T >: 类型1  <: 类型2]
- 语法:

Class PersonList[T <: Person] {} // 泛型上限(上界)(泛型 T的数据类型, 必须为 Person类型或 Person的子类型
Class PersonList[T >: Person] {} // 泛型下限(下界)(泛型 T的数据类型, 必须为 Person类型或 Person的父类型
- 例子 1:
```
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

```
`*一般函数设置泛型时, 这个泛型都会出现在参数里面, 用于设置参数的类型`
- 例子 2: 上界
```
package com.ex6.test12

object Test12 {
  class Person
  class Student extends Person
  // 限定 Array元素类型只能是 Person或 Person的子类
  def demo[T <: Person](arr: Array[T]) = println(arr)
  def main(args: Array[String]): Unit = {
    //demo(Array(1, 2, 3)) // 会报错, 因为只能传入 Person或者它的子类型
    demo(Array(new Person()))
    demo(Array(new Student()))
  }
}

```
- 例子 3: 下界
```
package com.ex6.test13

object Test13 {
  class Person
  class Policeman extends Person
  class Superman extends Policeman
  // 限定 Array元素类型只能是 Person或 Policeman
  //          下界          上界
  def demo[T >: Superman <: Policeman](arr: Array[T]) = println(arr)
  def main(args: Array[String]): Unit = {
    demo(Array(new Policeman))
    demo(Array(new Superman))
    //demo(Array(new Person)) // 会报错
  }
}

```

### 上下文限定
```

// 上下文限定是将泛型和隐式转换的结合产物, 以下两者功能相同，使用上下文限定[A : Ordering]之后, 方法内无法使用隐式参数名调用隐式参数, 需要通过 implicitly[Ordering[A]]
def f[A:Ordering](a:A,b:A) =implicitly[Ordering[A]].compare(a,b)
def f[A](a: A, b: A)(implicit ord: Ordering[A]) = ord.compare(a, b)

// 这里 A是泛型名称, 后面 B是隐式参数类型名称
def f[A : B](a: A) = println(a) 
// 等同于
def f[A](a:A)(implicit arg:B[A]) = println(a)

```