# 模式匹配
1) 模式匹配时, 会从第一个 case分支开始, 如果匹配成功, 则执行对应的逻辑代码, 并结束当前模式匹配. 如果所有 case都不匹配, 那么会执行 case _分支, 类似于 Java中 default语句
2) 如果没有 case _分支, 那会抛 scala.MatchError
3) match case语句, 可以匹配任何类型, 而不单是字面量
4) (=>)后面的代码块, 直到下一个 case语句之前的代码是作为一个整体执行, 可以使用{}括起来, 也可以不括
5) 如果需要匹配某个范围的数据, 可以在 case分支中加条件守卫(if)
```
package com.ex5.test1

object Test1 {
  def main(args: Array[String]): Unit = {
    // 1. 基本定义语法
    val x: Int = 4
    val y: String = x match {
      case 1 => "one"
      case _ => "other"
    }
    println(y) // other

    // 2. 模式匹配实现简单二元运算
    val a = 20.0
    val b = 3
    def matchDualOp(op: Char): Double = op match {
      case '+' => a + b
      case '-' => a - b
      case '*' => a * b
      case '/' => a / b
      case '%' => a % b
      case ooo => -1
    }
    println(matchDualOp('+')) // 23.0
    println(matchDualOp('/')) // 6.666666666666667
    println(matchDualOp('?')) // -1.0

    // 3. 模式守卫
    // 求一个整数的绝对值
    def abs(num: Int): Int = {
      num match {
        case i if (i >= 0) => i
        case (i: Int) if i < 0 => { -i }
      }
    }
    println(abs(67)) // 67
    println(abs(0)) // 0
    println(abs(-24)) // 24( --转正
  }
}

```

## 模式匹配-类型
```
package com.ex5.test2

object Test2 {
  def main(args: Array[String]): Unit = {
    // 1. 匹配常量: 可以匹配所有的字面量 如字符串, 字符, 数字, 布尔值等
    def describeConst(x: Any): String = x match {
      case 1 => "Int one"
      case "hello" => "String hello"
      case true => "Boolean true"
      case '+' => "Char +"
      case _ => "未匹配!"
    }

    println(describeConst("hello")) // String hello
    println(describeConst(true)) // Boolean true
    println(describeConst(0.3)) // 未匹配!

    // 2. 匹配类型: 集合类型, 即使设置了泛型, 依然不会按泛型匹配, 因此无需写, 泛型类型可以写成(_)通配符代替(注: 数组的泛型类型是有效的)
    def describeType(x: Any): String = x match {
      case i: Int => "Int " + i
      case s: String => "String " + x
      case list: List[_] => list.toString
      case set: Set[_] => set.toString
      case map: Map[String, Int] => map.toString
      case array: Array[Int] => "Array[Int] " + array.mkString(",")
      case _ => "Something else: " + x
    }

    println(describeType(35)) // Int 35
    println(describeType("hello")) // String hello
    // 集合类型, 即使设置了泛型, 依然不会按泛型匹配, 因此无需写类型, 而使用(_)通配符代替
    println(describeType(List("hi", "hello"))) // List(hi, hello)
    println(describeType(List(2, 23))) // List(2, 23)
    println(describeType(Set(3, 5))) // Set(3, 5)
    println(describeType(Map(("a", 5), "b" -> 6, 8 -> "abc"))) // Map(a -> 5, b -> 6, 8 -> abc)
    // 注: 数组的泛型类型是有效的
    println(describeType(Array("hi", "hello"))) // Something else: [Ljava.lang.String;@75bd9247
    println(describeType(Array(2, 23))) // Array[Int] 2,23

    // 3. 匹配数组
    for (arr <- List(
      Array(0), // case Array(0)
      Array(1, 0), // case Array(1, 0)
      Array(0, 1, 0), // case Array(0, _*)
      Array(1, 1, 0), // case Array(x, 1, z)
      Array(2, 3, 7, 15), // case _
      Array("A", 1, 30) // case Array(x, 1, z)
    )) {
      val result = arr match {
        case Array(0) => "0"
        case Array(1, 0) => "Array(1, 0)"
        case Array(x, y) => "Array: " + x + ", " + y
        case Array(0, _*) => "以0开头的数组"
        case Array(x, 1, z) => "中间为1的三元素数组"
        case _ => "something else"
      }
      println(result)
    }
    //    0
    //    Array(1, 0)
    //    以0开头的数组
    //    中间为1的三元素数组
    //    something else
    //    中间为1的三元素数组

    // 4. 匹配列表
    // 方式一
    for (list <- List(
      List(0), // case List(0)
      List(1, 0), // case List(x, y)
      List(0, 0, 0), // case List(0, _*)
      List(1, 1, 0), // case _
      List(88), // case List(a)
      List("hello") // case List(a)
    )) {
      val result = list match {
        case List(0) => "0"
        case List(x, y) => "List(x, y): " + x + ", " + y
        case List(0, _*) => "List(0, ...)"
        case List(a) => "List(a): " + a
        case _ => "something else"
      }
      println(result)
    }
    //    0
    //    List(x, y): 1, 0
    //    List(0, ...)
    //    something else
    //    List(a): 88
    //    List(a): hello

    // 方式二
    val list1 = List(1, 2, 5, 7, 24)
    val list = List(1, 2, List(5, 7, 24))
    list match {
      case first :: second :: rest =>
        println(s"first: $first, second: $second, rest: $rest")
      case _ => println("something else")
    }
    // first: 1, second: 2, rest: List(List(5, 7, 24))

    // 5. 匹配元组
    for (tuple <- List(
      (0, 1), // case (a, b)
      (0, 0), // case (a, b)
      (0, 1, 0), // case (a, 1, _)
      (0, 1, 1), // case (a, 1, _)
      (1, 23, 56), // case (x, y, z)
      ("hello", true, 0.5) // case (x, y, z)
    )) {
      val result = tuple match {
        case (a, b) => "" + a + ", " + b
        case (0, _) => "(0, _)"
        case (a, 1, _) => "(a, 1, _) " + a
        case (x, y, z) => "(x, y, z) " + x + " " + y + " " + z
        case _ => "something else"
      }
      println(result)
    }
    //    0, 1
    //    0, 0
    //    (a, 1, _) 0
    //    (a, 1, _) 0
    //    (x, y, z) 1 23 56
    //    (x, y, z) hello true 0.5
  }
}


package com.ex5.test3

object Test3 {
  def main(args: Array[String]): Unit = {
    // 1. 变量声明匹配
    val (x, y) = (35, "Shawn")
    println(s"x: $x, y: $y") // x: 35, y: Shawn

    val List(first, second, _*) = List(1, 2, 3, 4, "Shawn")
    println(s"first: $first, second: $second") // first: 1, second: 2

    val fir :: sec :: rest = List(11, 22, 33, 44, "Shawn")
    println(s"first: $fir, second: $sec, rest: $rest") // first: 11, second: 22, rest: List(33, 44, Shawn)

    val list: List[(String, Int)] = List(("a", 10), ("b", 20), ("c", 30), ("a", 40))
    // 2. for推导式中进行模式匹配
    // 2.1 原本的遍历方式
    for (elem <- list) {
      print(elem._1 + "," + elem._2 + " ") // a,10 b,20 c,30 a,40
    }
    println()

    // 2.2 将 List的元素直接定义为元组, 对变量赋值
    for ((word, count) <- list) {
      print(word + ": " + count + " ") // a: 10 b: 20 c: 30 a: 40
    }
    println()

    // 2.3 可以不考虑某个位置的变量，只遍历key或者value
    for ((word, _) <- list) print(word + " ") // a b c a
    println()

    // 2.4 可以指定某个位置的值必须是多少
    for (("a", count) <- list) {
      println(count)
    }
    //    10
    //    40
  }
}


package com.ex5.test4

object Test4 {
  def main(args: Array[String]): Unit = {
    val map = Map("A" -> 1, "B" -> 0, "C" -> 3)
    // if v等于或大于0的剩余k-v
    for ((k, v) <- map if v >= 1) {
      println(k + " ---> " + v)
    }
    //    A ---> 1
    //    C ---> 3

    val list: List[(String, Int)] = List(("a", 1), ("b", 2), ("c", 3), ("a", 4))
    // 1. map转换, 实现 key不变, value 2倍
    val newList = list.map(tuple => (tuple._1, tuple._2 * 2))
    println(newList) // List((a,2), (b,4), (c,6), (a,8))

    // 2. 用模式匹配对元组元素赋值, 实现功能
    val newList2 = list.map(
      tuple => {
        tuple match {
          case (word, count) => (word, count * 2)
        }
      }
    )
    println(newList2) // List((a,2), (b,4), (c,6), (a,8))

    // 3. 省略lambda表达式的写法，进行简化
    val newList3 = list.map {
      case (word, count) => (word, count * 2)
    }
    println(newList3) // List((a,2), (b,4), (c,6), (a,8))

    // 集合元素中, 数据类型为 Int的元素 + 1
    List(1,2,3,4,5,6,"test").filter(_.isInstanceOf[Int]).map(_.asInstanceOf[Int] + 1).foreach(print) // 234567
    println()
    List(1,2,3,4,5,6,"test").map{ case x: Int => x + 1 case _ => None }.foreach(print) // 234567None
    println()
    List(1,2,3,4,5,6,"test").collect{ case x: Int => x + 1 }.foreach(print) // 234567
  }
}

```

## 模式匹配-正则表达式
```
package com.ex5.test5

object Test5 {
  def main(args: Array[String]): Unit = {
    val email = "shawnjeon@163.com"
    val regex = """^[a-zA-Z0-9_\-]{2,}@([a-zA-Z0-9_\-]{2,})(\.[a-zA-Z0-9_\-]+)$""".r
    if (regex.findAllMatchIn(email).size != 0) {
      println("合法邮箱!")
    } else {
      println("非法邮箱")
    }
    // 合法邮箱!


    val emailList = List("83007003@qq.com", "shawnjeon@163.com", "qcl108@163.com", "quanchunlin.com")
    // 获取所有不合法邮箱
    val list = emailList.filter(regex.findAllMatchIn(_).size == 0)
    println(list)
    // List(quanchunlin.com)


    val result = emailList.map {
      case ele @ regex(company, ex) => (ele -> s"${company}${ex}")
      case ele => ele -> "未匹配"
    }
    println(result)
    // List((83007003@qq.com,qq.com), (shawnjeon@163.com,163.com), (qcl108@163.com,163.com), (quanchunlin.com,未匹配))
  }
}

```

## 模式匹配-对象
- match case匹配对象, 需在类的伴生对象中, 重写 unapply(Extractor提取器: 提取指定对象属性的)方法
- 若只提取对象的一个属性, 则提取器为 unapply(obj:Obj): Option[T]
- 若提取对象的多个属性, 则提取器为 unapply(obj:Obj): Option[(T1,T2,T3…)]
- 若提取对象的可变个属性, 则提取器为 unapplySeq(obj:Obj): Option[Seq[T]]
```
package com.ex5.test6

// 定义类
class Student(val name: String, val age: Int)

// 定义伴生对象
object Student {
  def apply(name: String, age: Int): Student = new Student(name, age)
  // 必须实现 unapply方法, 用来对对象属性进行拆解
  def unapply(student: Student): Option[(String, Int)] = {
    if (student == null) {
      None
    } else {
      Some((student.name, student.age))
    }
  }
}

object Test6 {
  def main(args: Array[String]): Unit = {
    val student = new Student("Shawn", 35)
    // 针对对象实例的内容进行匹配
    val result = student match {
      case Student("Shawn", 35) => "Shawn, 35"
      case _ => "Else"
    }
    println(result) // Shawn, 35

    val result2 = Student.unapply(student)
    println(result2) // Some((Shawn,35))
  }
}

```

### 样例类
1) 样例类与普通类不同的是, 编译器会自动生成伴生对象和一些方法 如: apply, toString, equals, hashCode, copy(对应 Java的 clone), unapply(提取属性的)等方法及自动继承序列化(Seralizable)等
2) 样例类是为模式匹配而优化的类, 因为无需自己实现 apply和 unapply方法
3) 构造器中的每一个参数都默认为 val, 除非它被显式地声明为 var(不建议)
```
package com.ex5.test7

object Test7 {
  case class Person(name: String, age: Int)
  def main(args: Array[String]): Unit = {
    val p1 = new Person("Shawn", 35)
    val p2 = p1.copy(name = "Mia", age = 30)
    println(p1) // Person(Shawn,35)
    println(p2) // Person(Mia,30)
    val p3 = p1.copy()
    println(p1 == p2) // 判断成员属性: false(对象不同, 成员属性值也不同
    println(p1 == p3) // 判断成员属性: true(对象不同, 成员属性值相同
    println(p1.eq(p2)) // 判断对象引用地址: false
    println(p1.eq(p3)) // 判断对象引用地址: false
  }
}


package com.ex5.test8

// 定义样例类
case class Student(name: String, age: Int)

object Test8 {
  def main(args: Array[String]): Unit = {
    val student = Student("Shawn", 35)
    // 针对对象实例的内容进行匹配
    val result = student match {
      case Student("Shawn", 35) => "Shawn, 35"
      case _ => "Else"
    }
    println(result)
  }
}

```
#### 样例对象
`*注: 样例对象没有主构造器`
- 主要用于: 1. 当做枚举值 2. 作为没有任何参数的消息传递
```
package com.ex5.test9

object Test9 {
  // 1. 定义性别特质 Sex
  trait Sex
  // 2. 样例对象 Male
  case object Male extends Sex
  // 3. 样例对象 Female
  case object Female extends Sex
  // 4. 定义 Person样例类
  case class Person(name:String, sex:Sex)
  def main(args: Array[String]): Unit = {
    val p = Person("Shawn", Male)
    println(p) // Person(Shawn,Male)
  }
}

```

### Option类型
- Option类型表示可选值, 该类型有两种形式:
1) Some(x): 表示实际的值
2) None: 表示没有值

`*注: 使用 getOrElse方法, 当值为 None时可以指定一个默认值`
```
package com.ex5.test10

object Test10 {
  def div(a: Int, b: Int): Option[Int] = {
    if (b == 0) {
      None
    } else {
      Some(a / b)
    }
  }

  def main(args: Array[String]): Unit = {
    val result = div(10, 0)
    result match {
      case Some(x) => println(x)
      case None => println("除数不能为0")
    }
    // 除数不能为0
    println(result.getOrElse(0)) // 0
  }
}

```

### 偏函数(PartialFunction)
- 偏函数是通过模式匹配实现的, 偏函数是一个实例对象 PartialFunction[A, B], 其中 A代表参数类型, B代表返回结果类型
- 使用偏函数可以很方便的对参数进行更精确的检查 例如: 参数类型 List[Int], 而需要的是第一个元素是0的集合(也就是只处理自己感兴趣的)
```
package com.ex5.test11

object Test11 {
  def main(args: Array[String]): Unit = {
    val pf: PartialFunction[Int, String] = {
      case 1 => "One"
      case 2 => "Two"
      case 3 => "Three"
      case _ => "Other"
    }
    println(pf(1)) // One
    println(pf(2)) // Two
    println(pf(5)) // Other
  }
}


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

```