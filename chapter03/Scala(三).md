# 包管理
> - 两种包管理风格(package):
> 1) 一种和 Java一样, 每一个源文件一个包(包名和源文件所在路径可以不一致, 编译后, 编译器会自动按包结构放置字节码文件), 包名用(.)进行分隔, 以表示包的层级关系 com.company.scala
>> 1-1) 该方式一个源文件只能属于一个包
> 2) 另一种是通过嵌套的方式, 表示层级关系. package com { package company { package scala {} } }
>> 2-1) 该方式一个源文件内可以有多个 package
>
>> 2-2) 子包的类, 无需 import引入外层的类, 可以直接使用(下层可以直接访问上层的内容)
>
>> 2-3) 上层访问下层内容时, 需导包`(import)或写全包名`的形式实现
>
>> 2-4) 上下层有类重名时, 优先使用下层的类(采用就近原则), 如果需要访问上层的类, 可通过上层路径 + 类名的形式实现
> - 包的命名: 允许包含数字, 字母, 下划线, (.)点, 但不能数字开头, 也不能包含关键字
> - 命名规范: com.公司名.项目名.业务模块名

- 嵌套的方式定义包实例:
```

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

```
> - 包对象(package object):
>
>> (-) 包对象名必须与包名相同
>
>> (-) 一个包只能存在一个包对象
>
>> (-) 每个包都可以定义一个包对象, 包对象名字和包名必须一致, 且它们之间是平级关系, 不能嵌套定义
```

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

```
> - 导包(import):
> 1) 源文件顶部使用 import导入, 作用域为当前文件内所有类
> 2) 局部导入, 作用域为当前上下文
> 3) 通配符导入: import java.util._引入 java.util下的所有成员(对应 java的*号)
> 4) 指定导入: import com.util.DateUtil引入 com.util包下的 DateUtil(class和 object)
> 5) import com.util.DateUtil._引入 DateUtil(object)的所有成员
> 6) 导入 java.util包下指定几个类: import java.util.{HashSet, ArrayList}
> 7) 导入 java.util包下的 HashSet, 并更名为 HS: import java.util.{HashSet => HS}
> 8) 导入 java.util包下的所有类, 其中将 HashSet更名为 HS: import java.util.{HashSet => HS,_}
> 9) 导入 java.util包下的所有成员, 除了 HashSet(屏蔽): import java.util.{HashSet => _,_}
> 10) 引入 Java的绝对路径: new _root_.scala.collection.mutable.ArrayBuffer[String]

> `*Scala的三个默认导入: import java.lang._  import scala._  import scala.Predef._`
```

	5) import com.util.DateUtil._引入 DateUtil(object)的所有成员
	import com.company.scala.Inner._
	println(in) // in
	
	8) 导入 java.util包下的所有成员, 并将 HashSet更名为 HS: import java.util.{HashSet => HS,_}
	import java.util.{HashSet => HS,_}
    var map: Map[Int, String] = new HashMap
    map.put(35, "shawn")
    println(map) // {35=shawn}

    var set: HS[Int] = new HS
    set.add(999)
    println(set) // [999]

```

# 面对对象
- Scala是多范式编程语言, 既是函数式编程语言同时也是面向对象语言

## 类(class):
- 类: 可以看做一个模板
- 对象: 表示具体的事物
> - Java类: 如果类是 public, 则必须类名与文件名相同
> - Scala类: 
> 1) 在 Scala没有关键字 public, 所有的类都具有公有可见性(即默认是 public). (类和内部成员也都默认为 public)
> 2) 还有源文件可以包含多个类
```
package com.ex3.test3

import scala.beans.BeanProperty

class Student {
  private var name: String = "Shawn"
  // val status: Int = _ // val修饰的属性不能赋默认值, 必须显示指定; 编译时会报错
  var age: Int = _ // 所有的类型, 可以用(_)下划线设置初始值
  // 当使用一些 java框架时, 类需要有 setgetter方法, 为了兼容这些 java框架, Scala在属性上方加 @BeanProperty注解, 让其自动生成 setgetter方法
  @BeanProperty
  var sex: String = _
}

object Test3 {
  def main(args: Array[String]): Unit = {
    val student = new Student()
    println(student.age) // 0
    student.age = 35
    println(student.age) // 35
    println(student.sex) // null
    student.setSex("Male")
    println(student.getSex) // Male
  }
}

```

### 封装
- 是指把抽象出的数据和操作该数据的方法, 将数据封存在内部, 通过被授权的方法进行操作 如属性私有化后, 通过set/get方法进行操作

`*Scala中的 public属性, 底层实际为 private属性, 操作是通过底层 get方法(obj.field())和 set方法(obj.field_=(value))对其进行操作. 所以 Scala并不推荐将属性设为 private, 和为属性再写 get/set方法. 但由于很多 Java框架都利用反射调用 get/set方法, 所以有时为了兼容这些框架, 在属性上方加 @BeanProperty注解自动生成 get/set方法`

### 访问权限
> - 在 Java访问权限分为: public, private, protected和默认. 而在 Scala中只有 默认(public), private和 protected
> 1) Scala的默认访问权限是 public, 但 Scala中没有 public关键字
> 2) private为私有权限, 只能在类内部和伴生对象中可以使用
> 3) protected为受保护权限, Scala中受保护权限, 比 Java更严格. 它只允许同类和子类可以访问, 同包是无法访问的
> 4) 包访问权限: private[包名], 在该包下的所有类以及子包都可以使用
```
package com.ex3.test4

class Person {
  private var idCard: String = "888-999"
  protected var name: String = "Shawn"
  var sex: String = "Male"
  //private[test4] var age: Int = 35
  private[ex3] var age: Int = 35

  def print(): Unit = {
    println(s"Person: $idCard $name $sex $age")
  }
}

class Worker extends Person {
  override def print(): Unit = {
    // println(idCard) // not found: value idCard(私有属性, 子类无法访问
    name = "Mia"
    age = 30 // 该属性设置了, 包访问权限: 设置 test4包 private[test4]时无权修改, 设置 company包后可以修改(当前类的上级包)
    sex = "Female"
    println(s"Worker: $name $sex $age")
  }
}

object Test4 {
  def main(args: Array[String]): Unit = {
    val person: Person = new Person()
    //person.idCard // variable idCard in class Person cannot be accessed in com.ex3.test4.Person(私有属性, 无权访问
    //person.name // protected受保护权限(同类, 子类, 可以访问. 同包无法访问
    println(person.sex) // Male
    println(person.age) // 35
    person.print() // Person: 888-999 Shawn Male 35

    var worker: Worker = new Worker()
    worker.print() // Worker: Mia Female 30
  }
}

```

### 创建对象
1) val修饰对象, 不能改变对象的引用(即内存地址), 但可以改变对象属性的值
2) var修饰对象, 可以修改对象的引用(包括内部属性值
3) 未设置变量类型(自动推导), 不能多态, 如果需要类型转型需要显示声明

### 构造器
- Scala有两种构造器包括: 
1) 主构造器
2) 辅助构造器(辅助构造器不能直接构建对象, 必须直接或者间接调用主构造器
```
package com.ex3.test5

object Test5 {
  def main(args: Array[String]): Unit = {
    val stu1 = new Student // 1. 主构造方法被调用
    stu1.Student() // 一般方法被调用
    val stu2 = new Student("Shawn")
    // 1. 主构造方法被调用
    // 2. 辅助构造方法一被调用
    // name: Shawn age: 0
    val stu3 = new Student("Mia", 30)
    // 1. 主构造方法被调用
    // 2. 辅助构造方法一被调用
    // name: Mia age: 0
    // 3. 辅助构造方法二被调用
    // name: Mia age: 30
  }
}

class Student() /* 主构造器*/ { // 如果主构造器无参数, 小括号可省略
  var name: String = _
  var age: Int = _
  println("1. 主构造方法被调用")
  // 辅助构造器
  def this(name: String) {
    this() // 直接调用主构造器
    println("2. 辅助构造方法一被调用")
    this.name = name
    println(s"name: $name age: $age")
  }

  // 辅助构造器(可以重载多个
  def this(name: String, age: Int){
    this(name)
    println("3. 辅助构造方法二被调用")
    this.age = age
    println(s"name: $name age: $age")
  }

  // 不同于 Java, 在 Scala与类名相同的方法不是构造器, 而只是一般方法
  def Student(): Unit = {
    println("一般方法被调用")
  }
}

```
> - 构造器参数: 主构造器形参修饰类型
> 1) 无修饰: 不会作为成员属性使用, 而是一个局部变量
> 2) var修饰: 作为类的成员属性, 可以修改
> 3) val修饰: 作为类的成员属性, 不能修改
```
package com.ex3.test6

object Test6 {
  def main(args: Array[String]): Unit = {
    val student1 = new Student1
    // student1.name = "Mia" reassignment to val
    student1.age = 30
    println(s"Student1: name = ${student1.name}, age = ${student1.age}")
    // Student1: name = Shawn, age = 30

    val student2 = new Student2("Shawn", 35)
    println(s"student2: name = ${student2.name}, age = ${student2.age}")
    // student2: name = Shawn, age = 35

    val student3 = new Student3("Tom", 20)
    // student3.age = 25 // value age is not a member of com.ex3.test6.Student3(不是内部属性
    student3.printInfo() // Student3: name = Tom, age = 20

    val student4 = new Student4("Sam", 10, "bl")
    println(s"Student4: name = ${student4.name}, age = ${student4.age}")
    // Student4: name = Sam, age = 10
    student4.printInfo()
    // Student4: name = Sam, age = 10, school = bl
  }
}

// 无参构造器
class Student1 {
  val name: String = "Shawn"
  var age: Int = _
}
// 等价于
class Student2(val name: String, var age: Int) // 没有其它可以省略{}

// 主构造器参数无修饰
class Student3(name: String, age: Int) {
  def printInfo() {
    println(s"Student3: name = ${name}, age = $age")
  }
}

// 辅助构造器(辅助构造器不能直接构建对象, 必须直接或者间接调用主构造器
class Student4(var name: String, var age: Int) {
  var school: String = _

  def this(name: String, age: Int, school: String) {
    this(name, age)
    this.school = school
  }

  def printInfo() {
    println(s"Student4: name = ${name}, age = $age, school = $school")
  }
}

```

### 继承和多态
1) Scala也与 Java一样是单继承多实现, 也就是 extends只能有一个父类, 当然父类上方再有父类是可以的
2) 构造器的调用顺序也是父先子后
3) 父类的主构造器有形参时, 子类继承该类时, 子类的主构造器也需要继承相同的形参否则报错
4) 父类的主构造器无形参时, 子类的构造器是可以拥有形参的
5) Scala覆写父级方法(非抽象)时, 必须写上 override才会覆盖的也就是可以用于多态调用的; 抽象方法和属性可以省略 override
```
package com.ex3.test7

object Test7 {
  class Person() {
    var name: String = _
    var age: Int = _

    println("1. 父类的主构造器调用")

    def this(name: String, age: Int) {
      this()
      println("2. 父类的辅助构造器调用")
      this.name = name
      this.age = age
    }

    def print(): Unit = {
      println(s"Person: $name $age")
    }
  }

  class Student(name: String, age: Int) extends Person(name, age) { // 继承了父类的辅助构造器
    var stdNo: String = _

    println("3. 子类的主构造器调用")

    def this(name: String, age: Int, stdNo: String) {
      this(name, age)
      println("4. 子类的辅助构造器调用")
      this.stdNo = stdNo
    }

    override def print(): Unit = {
      println(s"Student: $name $age $stdNo")
    }
  }

  class Teacher extends Person {
    override def print(): Unit = {
      println(s"Teacher")
    }
  }

  def main(args: Array[String]): Unit = {
    val student1: Student = new Student("Mia", 30)
    //1. 父类的主构造器调用
    //2. 父类的辅助构造器调用
    //3. 子类的主构造器调用
    student1.print()
    //Student: Mia 30 null
    val student2 = new Student("Shawn", 35, "S0001")
    //1. 父类的主构造器调用
    //2. 父类的辅助构造器调用
    //3. 子类的主构造器调用
    //4. 子类的辅助构造器调用
    student2.print()
    //Student: Shawn 35 S0001
    val teacher = new Teacher
    //1. 父类的主构造器调用
    teacher.print()
    //Teacher
    def print(person: Person): Unit = {
      person.print()
    }
    print(student1) // 向上转型
    //Student: Mia 30 null
    print(student2) // 向上转型
    //Student: Shawn 35 S0001
    print(teacher) // 向上转型
    //Teacher
    val person = new Person
    //1. 父类的主构造器调用
    print(person)
    //Person: null 0
  }
}

```
- 动态绑定: 什么是多态? 简单来讲就是多种状态. 一种接口可以有多种不同的实现方式
在 Scala中属性和方法都是动态绑定的, 而 Java是只有方法为动态绑定
```
package com.ex3.test8

class Person { // 这个可以是接口(在 Scala就是Trait)
  val name: String = "person"

  def hello(): Unit = {
    println("hello person")
  }
}

class Student(var age: Int) extends Person {
  override val name: String = "student"

  override def hello(): Unit = { // 在 Scala覆写父级方法(非抽象)时, 必须写上 override才会覆盖的也就是可以用于多态调用的; 抽象方法和属性可以省略 override
    println("hello student")
  }

  def hi(): Unit = {
    println("hi student")
  }
}

object Test8 {
  def main(args: Array[String]): Unit = {
    val student: Person = new Student(0)
    println(student.name) // student(Scala:动态绑定属性, Java:静态绑定属性, 这里如果是 Java则会输出 person
    student.hello() // hello student(Scala:动态绑定方法, Java:动态绑定方法
    //student.hi() value hi is not a member of com.ex3.test8.Person必须有覆写方法才可以动态调用方法

    val student2: Student = new Student(20)
    println(student2.age) // 20
  }
}

```

### 抽象类
- 一般类本身是对于对象的抽象, 然后抽象类又是类的基础上进一步抽象的类
- 规则与 Java的抽象类完全相同
> - 继承&重写:
> 1) 当父类为抽象类时, 子类需将实现抽象属性和方法, 否则子类也需声明为抽象类
> 2) 覆写非抽象方法或属性时, 需 override修饰, 但覆写抽象方法或属性可以不加 override
> 3) 在子类, 调用父类方法, 使用 super关键字
> 4) 当子类实现抽象属性时, 该父类抽象属性可以用 var修饰
> 5) 子类覆写非抽象属性时, 该父类非抽象属性只可以用 val类型(禁用 var: 父类用 var修饰的变量, 子类不能重写). 因为 var修饰是可变变量, 子类继承后无需覆写, 就可以使用; 如果该非抽象属性无需覆写, 则可以用 var修饰
```
package com.ex3.test9

abstract class Person {
  // 非抽象属性
  val name: String = "person"
  // 抽象属性
  var age: Int
  // 非抽象方法
  def eat(): Unit = {
    println("person eat")
  }
  // 抽象方法
  def sleep(): Unit
}

class Student extends Person {
  var age: Int = 18
  override val name: String = "student"

  override def eat(): Unit = {
    super.eat()
    println("student eat")
  }

  def sleep(): Unit = {
    println("student sleep")
  }
}

object Test9 {
  def main(args: Array[String]): Unit = {
    val student = new Student
    println(student.name) // student
    student.eat()
    //person eat
    //student eat
    student.sleep()
    //student sleep
  }
}

```
- 匿名子类(又称匿名内部类/子类对象): 与 Java相同, 可以代码块的方式创建匿名子类
使用场景: 当成员方法仅掉用一次的时候
```
package com.ex3.test10

object Test10 {
  abstract class Person {
    var name: String
    def eat(): Unit
  }

  def show(p:Person) = p.eat()

  def main(args: Array[String]): Unit = {
    val person: Person = new Person { // 这里就是`匿名子类继承了 Person类`
      // 重写类中所有的抽象成员
      var name: String = "Shawn"
      override def eat(): Unit = println("person eat")
    }
    println(person.name) // Shawn
    person.eat() // person eat

    // 匿名子类可当作方法的参数进行传递
    val p = new Person {
      var name: String = "Shawn2"
      override def eat(): Unit = println("person eat2")
    }
    show(p) // person eat2
  }
}

```

### 单例对象(伴生对象)
- Scala是完全面向对象语言, 所以没有静态操作
- 一个包内有类, 再同一个文件同一个包内又有个与该类名称相同的, 关键字 object开始的代码块就是单例对象(又称伴生对象), 类称作伴生类
- 如果需要定义类似 Java一样的静态操作的属性或方法时, 可以定义在该伴生对象内(调用时通过`类名.方法()`)
- 伴生类和伴生对象是互通的可以直接访问包括私有的成员
> - 单例设计模式: Scala中, 实现 Java的单例设计模式, 可以在类名称前加上 private, 再单例对象内部实例化伴生类
> - apply方法:
> 1) 单例对象内定义的 apply方法, 在外部掉用时, 不用写 apply方法名称, 而直接传参来掉用方法
> 2) apply方法可以重载
```
package com.ex3.test11

class Student private(val name: String, val age: Int) {
  def print() {
    println(s"Student: name = ${name}, age = $age, school = ${Student.school}")
  }
}

object Student {
  val school: String = "bl"
  def newStudent(name: String, age: Int): Student = new Student(name, age)
  def apply(name: String, age: Int): Student = new Student(name, age)
}

object Test11 {
  def main(args: Array[String]): Unit = {
    //val student = new Student("Shawn", 35) // 构造器私有不允许在外部 new; constructor Student in class Student cannot be accessed in object Test11
    //student.print()

    val student1 = Student.newStudent("Mia", 30)
    student1.print() // Student: name = Mia, age = 30, school = bl
    println(student1) // com.ex3.test11.Student@c818063

    val student2 = Student.apply("Tom", 20)
    student2.print() // Student: name = Tom, age = 20, school = bl
    println(student2) // com.ex3.test11.Student@3f0ee7cb

    val student3 = Student("Sam", 10)
    student3.print() // Student: name = Sam, age = 10, school = bl
    println(student3) // com.ex3.test11.Student@75bd9247
  }
}

package com.ex3.test12

import com.ex3.test11.{Student => S0}

class Student private(var name: String, var age: Int) {
  def print() {
    println(s"Student: name = ${name}, age = $age, school = ${S0.school}")
  }
}

//// 饿汉式
//object Student {
//  private val student: Student = new Student("Mia", 30)
//  def getInstance(): Student = student
//}

// 懒汉式
object Student {
  private var student: Student = _
  def getInstance(): Student = {
    if (student == null) {
      // 如果没有对象实例的话，就创建一个
      student = new Student("Shawn", 35)
    }
    student
  }
}

object Test12 {
  def main(args: Array[String]): Unit = {
    val student1 = Student.getInstance()
    student1.print() // Student: name = Shawn, age = 35, school = bl
    println(student1) // com.ex3.test12.Student@c818063

    val student2 = Student.getInstance()
    println(student2) // com.ex3.test12.Student@c818063
  }
}

```
- 单例对象也可以继承类:
```
package com.ex3.test13

object Test13 {
  class Person {
    var name = ""
    def sayHello() = println(s"Hello, ${name}!")
  }
  // 定义单例对象 Student, 继承 Person类
  object Student extends Person
  def main(args: Array[String]): Unit = {
    Student.name = "Shawn"
    println(Student.name) // Shawn
    Student.sayHello() // Hello, Shawn!
  }
}

```

### 特质/特征(Trait)
- 在 Java多实现是通过 interface来做, 而在 Scala中是通过 Trait来实现多实现的; Scala没有 interface 
- 不同于 Java的 interface, Trait可以有普通方法和普通属性, 且还可以有私有成员, 非常类似抽象类, 但又不同于抽象类 Trait可以多实现(一个类可以 mixin混入多特质)又像 Java的 interface. 可以看作 Java的 interface + abstrate的结合(字节码成面看也是如此)
1) 类和特质的关系是使用继承的关系. 一个类去继承特质时, 第一个连接词是 extends, 后面是 with
2) 一个类同时继承特质和父类时, 父类写在 extends后之后再写 with 特质
3) 特质可以同时拥有抽象方法和普通方法
4) 一个类可以混入(mixin)多个特质
5) 所有的 Java接口都可以当做 Scala特质来使用
6) 动态混入(又称对象混入): 可灵活的扩展类的功能(不改变类继承关系的情况下, 对对象的功能进行扩展
6-1) 动态混入: 创建对象时混入 Trait, 而无需使类混入该 Trait
6-2) 如果混入的 Trait中有未实现的方法, 则需要实现
7) 特质和抽象类的选择: 优先使用特质. 一个类扩展多个特质是很方便的, 但却只能扩展一个抽象类. 不过如果需要用构造器参数, 就选择抽象类. 因为抽象类可以定义带参数的构造器, 而特质不行

- 基本语法:

没有父类: class类名 extends 特质1 with 特质2 with 特质3 ...

有父类: class类名 extends 父类 with 特质1 with 特质2 with 特质3 ...
```
package com.ex3.test14

class Person {
  val name: String = "person"
  var age: Int = 18
  def sayHello(): Unit = {
    println("hello from " + name)
  }
  def increase(): Unit = {
    println("Person increase")
  }
}

trait Young {
  var age: Int
  val name: String = "young"
  def play(): Unit = {
    println(s"Young people $name is playing")
  }
  def dating(): Unit
}

class Student extends Person with Young with java.io.Serializable  {
  // 必须重写冲突的属性(Person& Young
  override val name: String = "student"

  // 重写父类方法(Person
  override def sayHello(): Unit = {
    super.sayHello()
    println(s"hello from Student $name")
  }

  // 重写特征方法(Young
  def dating(): Unit = println(s"Student $name is dating")

  def study(): Unit = println(s"Student $name is studying")
}

object Test14 {
  def main(args: Array[String]): Unit = {
    val student: Student = new Student
    student.sayHello()
    // hello from student(动态绑定属性
    // hello from Student student
    student.play() // Young people student is playing
    student.dating() // Student student is dating
    student.study() // Student student is studying
  }
}

package com.ex3.test15

trait Knowledge {
  var amount: Int = 0
  def increase(): Unit
}

trait Talent {
  def singing(): Unit
  def dancing(): Unit
}

import com.ex3.test14.{Person, Young}

class Student extends Person with Young with Knowledge {
  // 重写冲突的属性(Person& Young
  override val name: String = "student"

  // 重写父类方法(Person
  override def sayHello(): Unit = {
    super.sayHello()
    println(s"hello from: Student $name")
  }

  // 实现抽象方法(Young
  def dating(): Unit = println(s"Student $name is dating")

  // 实现特质中的抽象方法(Knowledge
  override def increase(): Unit = {
    amount += 1
    println(s"Student $name Knowledge increased: $amount")
  }

  def study(): Unit = println(s"Student $name is studying")
}

object Test15 {
  def main(args: Array[String]): Unit = {
    val student = new Student
    student.study() // Student student is studying
    student.increase() // Student student Knowledge increased: 1
    student.play() // Young people student is playing
    student.increase() // Student student Knowledge increased: 2
    student.dating() // Student student is dating
    student.increase() // Student student Knowledge increased: 3

    // 动态混入(代码块的方式创建匿名子类; 灵活扩展, 类的功能
    val studentWithTalent = new Student with Talent {
      override def dancing(): Unit = println("Student is good at dancing")
      override def singing(): Unit = println("Student is good at singing")
    }
    studentWithTalent.sayHello()
    //hello from student
    //hello from: Student student
    studentWithTalent.play() // Young people student is playing
    studentWithTalent.study() // Student student is studying
    studentWithTalent.dating() // Student student is dating
    studentWithTalent.dancing() // Student is good at dancing
    studentWithTalent.singing() // Student is good at singing
  }
}

```
- 特质叠加: 由于一个类可以继承父类& 抽象类, 并又可以混入(mixin)多个 Trait等原因, 有时会出现继承冲突问题. 一般可以重写冲突方法便可以解决, 但如果又想用 super关键字调用上级的该方法

`注:super不是表示其父特质对象, 而是表示叠加顺序中的下一个特质`
1) 冲突的继承方法的执行顺序(从右到左的顺序执行
```
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

```
- trait的构造机制: 特质只有一个无参数构造器, 也就是说 trait也有构造代码, 但和类不一样, 特质不能有构造器参数
trait的构造器初始化顺序:
1) 执行父类的构造器
2) 按照`从左到右`的顺序, 依次执行 trait的构造器
3) 如果 trait有父 trait, 则先执行父 trait的构造器
4) 如果多个 trait有同样的父 trait, 则父 trait的构造器只初始化一次
5) 执行子类构造器
```
package com.ex3.test17

object Test17 {
  // 1. 创建 Logger父特质
  trait Logger {
    println("执行 Logger构造器")
  }
  // 2. 创建 MyLogger子特质, 继承 Logger特质
  trait MyLogger extends Logger {
    println("执行 MyLogger构造器")
  }
  // 3. 创建 TimeLogger子特质, 继承 Logger特质
  trait TimeLogger extends Logger {
    println("执行 TimeLogger构造器")
  }
  // 4. 创建父类 Person
  class Person {
    println("执行 Person构造器")
  }
  // 5. 创建子类 Student, 继承 Person类及 TimeLogger和 MyLogger特质
  class Student extends Person with TimeLogger with MyLogger {
    println("执行 Student构造器")
  }
  def main(args: Array[String]): Unit = {
    new Student
  }
}
//执行 Person构造器
//执行 Logger构造器
//执行 TimeLogger构造器
//执行 MyLogger构造器
//执行 Student构造器

```

### 特质(Trait)自身类型
- `*Trait声明自身类型(Trait或类), 相当于它拥有了指定类型里的所有属性和方法(依赖注入的功能), 效果和继承差不多`
```
package com.ex3.test18

// 用户类
class User(val name: String, val password: String)

trait UserDao {
  // UserDao要拥有 User的属性, 但又不想继承 User, 此时使用自身类型
  abc: User =>
  // 向数据库插入数据
  def insert(): Unit = {
    println(s"insert into db: ${abc.name}, ${abc.password}")
  }
}

// 定义注册用户类
class RegisterUser(name: String, password: String) extends User(name, password) with UserDao

object Test18 {
  def main(args: Array[String]): Unit = {
    val user = new RegisterUser("Shawn", "123456")
    user.insert() // insert into db: Shawn, 123456
  }
}

```
- trait也可以继承类(class)
```
package com.ex3.test19

object Test19 {
  class Message {
    def printMsg() = println("Hello, Shawn!")
  }
  trait Logger extends Message
  class ConsoleLogger extends Logger

  def main(args: Array[String]): Unit = {
    val cl = new ConsoleLogger
    cl.printMsg() // Hello, Shawn!
  }
}

```

### 设计模式(Design Pattern)

- 模板方法模式
```
优点:
1. 扩展性更强: 父类中封装了公共的部分, 而可变的部分交给子类来实现
2. 符合开闭原则: 部分方法是由子类实现的, 因此子类可以通过扩展方式增加相应的功能
缺点:
1. 类的个数增加, 导致系统更加庞大, 设计也更加抽象(因为要对每个不同的实现都需要定义一个子类
2. 提高了代码阅读的难度(父类中的抽象方法由子类实现, 子类执行的结果会影响父类的结果, 这导致一种反向的控制结构

package com.ex3.test20

object Test20 {
  abstract class Template {
    // 该方法记录所有要执行的代码(需要子类实现
    def code()
    // 用来获取某些代码的执行时间(模板方法
    def getRuntime() = {
      // 获取当前时间毫秒值
      val start = System.currentTimeMillis()
      // 具体要执行的业务代码
      code()
      // 获取当前时间毫秒值
      val end = System.currentTimeMillis()
      // 返回消耗时间
      end - start
    }
  }
  //2. Demo类, 重写 getRuntime()模板方法, 用来输出 Hello, Scala!, 并返回执行时间
  class Demo extends Template {
    override def code(): Unit = for (i <- 1 to 5000) println("Hello, Scala!")
  }

  def main(args: Array[String]): Unit = {
    println(new Demo().getRuntime())
  }
}

```
- 职责链设计模式(又称调用链模式): 多个 trait中出现了同一个方法, 且该方法最后都调用了super.该方法名(), 当类继承了这多个 trait后, 就可以依次调用多个 trait中的此同一个方法了, 这就形成了一个调用链
> - 实现一个模拟支付过程的调用链
> 需要执行一系列的验证后完成支付 如:
> 1. 进行支付签名校验
> 2. 数据合法性校验
> 3. ...
```
package com.ex3.test21

object Test21 {
  // 1. 定义一个父特质 Handler, 表示处理数据(具体的支付逻辑)
  trait Handler {
    def handle(data: String) = {
      println("具体处理数据的代码(例如: 转账逻辑)")
      println(data)
    }
  }
  // 2. 定义一个子特质 DataValidHandler, 表示校验数据
  trait DataValidHandler extends Handler {
    override def handle(data: String) = {
      println("校验数据...")
      super.handle(data)
    }
  }
  // 3. 定义一个子特质 SignatureValidHandler, 表示 校验签名
  trait SignatureValidHandler extends Handler {
    override def handle(data: String) = {
      println("校验签名...")
      super.handle(data)
    }
  }
  // 4. 定义一个类 Payment, 表示用户发起的支付请求
  class Payment extends DataValidHandler with SignatureValidHandler {
    def pay(data: String) = {
      println("用户发起支付请求...")
      super.handle(data)
    }
  }
  def main(args: Array[String]): Unit = {
    // 5. 创建Payment类的对象, 模拟: 调用链
    val pm = new Payment
    pm.pay("张三转账给小李5000元")
  }
}
//用户发起支付请求...
//校验签名...
//校验数据...
//具体处理数据的代码(例如: 转账逻辑)
//张三转账给小李5000元

```

## 类型检查和转换
1) obj.isInstanceOf[T]: 判断 obj是不是 T类型。
2) obj.asInstanceOf[T]: 将 obj强转成 T类型。
3) classOf获取对象的类名
```
package com.ex3.test22

class Person(val name: String, val age: Int) {
  def sayHi(): Unit = {
    println("hi from Person " + name)
  }
}

class Student(name: String, age: Int) extends Person(name, age) {
  override def sayHi(): Unit = {
    println("hi from Student " + name)
  }

  def study(): Unit = {
    println("Student study")
  }
}

object Test22 {
  def main(args: Array[String]): Unit = {
    // 1. 类型的检测和转换
    val student: Student = new Student("Shawn", 35)
    student.study() // Student study
    student.sayHi() // hi from Student Shawn
    val person: Person = new Student("Mia", 30)
    person.sayHi() // hi from Student Mia

    // 类型判断
    println("student is Student: " + student.isInstanceOf[Student]) // true
    println("student is Person: " + student.isInstanceOf[Person]) // true Student -> Person向上匹配
    println("person is Person: " + person.isInstanceOf[Person]) // true
    println("person is Student: " + person.isInstanceOf[Student]) // true
    val person2: Person = new Person("cary", 35)
    println("person2 is Student: " + person2.isInstanceOf[Student]) // false 不能向下匹配

    // ClassOf判断对象类型
    println(person.getClass == classOf[Student])    // true
    println(person2.getClass == classOf[Student])   // false

    // 类型转换
    if (person.isInstanceOf[Student]) {
      val newStudent = person.asInstanceOf[Student]
      newStudent.study() // Student study
    }

    // classOf获取对象的类名
    println(classOf[Student]) // class com.ex3.test22.Student
  }
}

```

## 枚举类和应用类
- 枚举类: 单例对象继承 Enumeration
```
package com.ex3.test23

// 定义枚举类对象
object WorkDay extends Enumeration {
  val MONDAY = Value(1, "Monday")
  val TUESDAY = Value(2, "TuesDay")
}

object Test23 {
  def main(args: Array[String]): Unit = {
    println(WorkDay.MONDAY) // Monday
  }
}

```
- 应用类: 单例对象继承 App. 内部无需再定义main方法
```

// 定义应用类对象
object Test18 extends App {
  println("app start")
}

```

## Type定义新类型
- 通过 type关键字定义新的数据类型(本质上是类型的一个别名
```

object Test18 extends App {
	type SS = String
	val a: SS = "测试类型"
	println(a)
}

```
