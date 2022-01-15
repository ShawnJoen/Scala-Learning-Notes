# Scala简介
官网 https://www.scala-lang.org
- Martin Odersky, 从2001年开始设计 Scala. 将函数式编程语言的特点融合到 Java中, 以此推出了两种语言 Pizza& Scala
- Pizza和 Scala极大地推动了 Java的发展:
1) Java5的泛型, 增强型 for循环, 自动类型转换等, 都是从 Pizza引入的新特性
2) Java8的类型推断, Lambda表达式等就是从 Scala引入的特性
- Scala是运行在 JVM上的, 可以面向对象和函数式编程的静态类型(有提前编译的过程)编程语言
- 通过 scalac编译 .scala文件后, 也会生成 Java字节码文件(.class)
- Scala是先编译后, 再 JVM解释(半编译半解释型)的语言
- Scala Repl(命令行): 输入 scala进入 :quit退出
- 可以使用 Java的所有类库
```
> -移除的 Java关键字& 语法部分:
> static: 由 object实现类(伴生所属类)来代替(也就是 Object伴生对象是单例对象)
> void: 由 Unit表示无值的类来代替
> continue:
> break:
> i++, ++i
> 各种基本数据类型: byte, short, int, long, char, boolean等
```
## 下载安装
- 下载 Java:
  https://www.oracle.com/java/technologies/downloads
- 下载 Scala:
  https://www.scala-lang.org/download
- Windows配置环境变量: 注路径不能含中文, 最好不要有空格等特殊符号
  SCALA_HOME=D:\scala-2.12.11
  path=%SCALA_HOME%\bin

### 创建 Maven项目
1) IDEA安装插件: 默认不支持 Scala开发, 需安装插件
2) 项目点击右键 -> Add Framework Support... -> 勾选 Scala(选择 Scala目录) -> 点击 OK
3) 新建 src/main/scala目录, 右键点击 -> 选择 Mark Directory as -> 选择 Generated Sources Root
```

// 类名()传入参数对应 Java的构造器(constructor)
// 参数名前加[var/val], 初始化同时定义为该类属性. 否则是一次性的
class Student(name: String, var age: Int) { 
  def printInfo(): Unit = {
    println(name + " " + age + " " + Student.school)
  }
}

// Student类的伴生对象(用于代替 java的 static关键字-单例对象
// 与 class类相伴相生, 名称相同, 且必须放到同一个文件里, 内部私有成员都可以相互访问
object Student {
  val school: String = "jd"
  def main(args: Array[String]): Unit = { // 泛型是通过[]号表示
    val alice = new Student("alice", 20)
    val bob = new Student("bob", 23)
    alice.printInfo()
    bob.printInfo()
  }
}

```
- 反编译 Scala字节码文件: 使用 Java反编译工具 jd-gui.exe打开字节码(.class)文件
- 开发时为了分析源码, 关联 Scala源码 scala-sources-2.12.11.tar.gz解压后拷贝到 D:\scala-2.12.11\lib目录, 并 CTRL + 点击一些自带方法 -> 点击 Attach Sources... -> 选择该目录

## 变量和常量
- var 变量名 [: 变量类型] = 初始值
- val 常量名 [: 常量类型] = 初始值
- `注: 因为函数式编程里本来是没有变量的, 所以能使用常量就用常量, 尽可能避免使用变量`
1) 声明变量时, 类型可以省略, 编译器自动推导, 即类型推导
2) 类型确定后, 就不能修改, 说明 Scala是强数据类型语言
3) 变量声明时, 必须要有初始值
- 标识符的命名规范:
1) 以字母或者下划线开头, 后接字母, 数字, 下划线
2) 以操作符开头, 且只包含操作符(+-*/#!等
3) 用反引号``包括的任意字符串, 即使是 Scala关键字

- 字符串输出:
```

    val name: String = "alice"
    // 通过+号连接
    val age: Int = 18
    println(age + "岁")

    // *用于将一个字符串复制多次并拼接
    println(name * 3)

    // printf: 通过 %传值
    printf("%s%d岁\n", name, age)

    // s""字符串模板: 通过$获取变量值
    println(s"${name}${age}岁")

    // 在 Scala小数是默认为 Double, 所以定义 Float值时必须加f
    val num: Double = 2.3456
    // f"": 格式化模板字符串
    println(f"The num is ${num}%2.2f")
    // raw"": 将字符串数据按原始的输出
    println(raw"The num is ${num}%2.2f")

	// Double和 Float类型有格式化输出的方法
	val n: Double = 10.123
    println(n.formatted("%.2f")); // 10.12: 小数保留两位
    println(n.formatted("%1.2f")); // 10.12: 总个数包含(.)最少1位, 超过了也不会限制
    println(n.formatted("%5.1f")); //  10.1: 总个数包含(.)最少5位, 如果未达到前面补空格

    // 三引号包括字符串, 可以将多行字符串原格式输出
	// 对变量进行运算, 可以加${}
    val sql =
      s"""
         |select *
         |from
         |  student
         |where
         |  name = $name and age=${age+2}
         |""".stripMargin
    println(sql)

```

- 键盘输入: 对应 Java的  System.in(标准输入流)
```

    println("请输入名字:")
    val name: String = StdIn.readLine()
    println("请输入年龄:")
    val age: Int = StdIn.readInt()
    println(s"${name}${age}岁")

```
## 数据类型

![image](https://github.com/ShawnJoen/Scala-Learning-Notes/blob/master/images/scala数据类型.png)

1) Scala中一切数据都是对象, 都是 Any的子类
2) Scala中数据类型分为两大类: 数值类型(AnyVal), 引用类型(AnyRef)都是对象
3) Scala数据类型仍然遵守, 低精度的数据类型到高精度的类型自动转换(隐式转换
4) Scala中的 StringOps是对 Java String的增强
5) Unit是一个类型: 只有一个对象就是(), 表示方法没有返回值. 它对应 Java中的 void关键字
6) Null是一个类型: 只有一个对象就是null. 它是所有引用类型(AnyRef)的子类
7) Nothing是所有数据类型的子类: 主要用于函数没有明确的返回值时使用 如抛异常

- 整数类型(Byte, Short, Int, Long
```
数据类型| 描述
--|:--:
Byte [1] | 8位有符号补码整数, 数值区间为 -128到 127
Short [2] | 16位有符号补码整数, 数值区间为 -32768到 32767
Int [4] | 32位有符号补码整数, 数值区间为 -2147483648到 2147483647
Long [8] | 64位有符号补码整数, 数值区间为 -9223372036854775808到 9223372036854775807 = 2的(64-1)次方-1

2的32次方 = 4294967296
2的16次方 = 65536
2的10次方 = 1024 = 1K
2的8次方 = 256
2的7次方 = 128

*8位整数=(2的8次方 = 256), 有符号补码256 / 2 = 128 - 1 = 127 ~ -128
0 000 0001 = +1
0 111 1111 = +127
0 000 0000 = +0
1 000 0000 = -128
1 000 0001 = -127
1 000 0010 = -126

```
- Byte 8位有符号补码整数: -128 ~ +127
- `0 000 0000: 第一个是符号位和7个2进制位(7bit), 该7个二进制位能表示的数为2的7次方 = 128, 正数包含0: 因此0到 128 - 1 = 127(减0数的个数位), 负数是从-1 ~ -128`
```

// 上下最大& 最小值
var n1:Byte = 127
var n2:Byte = -128

```

- 浮点类型(Float, Double
- `*Scala的浮点型常量默认为 Double型, 声明 Float, 需加‘f’或‘F’`
```
数据类型| 描述
--|:--:
Float [4] | 32位, IEEE 754标准的单精度浮点数
Double [8] | 64位, IEEE 754标准的双精度浮点数
```
- 字符类型(Char 表示单个字符
```

    // 字符类型
    val c1: Char = 'a'
    val c2: Char = '9'
    // 控制字符
    val c3: Char = '\t' // 制表符
    val c4: Char = '\n' // 换行符
    // 转义字符
    val c5 = '\\' // 表示\自身
    val c6 = '\"' // 表示"
    // 字符变量底层保存ASCII码
    val i1: Int = c1
    println("i1: " + i1) // 97
    val i2: Int = c2
    println("i2: " + i2) // 57
    // 强转
    val c7: Char = (i1 + 1).toChar
    println(c7) // 97('a') + 1 = 'b'
    val c8: Char = (i2 - 1).toChar
    println(c8) // 57('9') - 1 = '8'

```

- 布尔类型( Boolean占1个字节
- Unit类型, Null类型和 Nothing类型
```
数据类型 | 描述
--|:--:
Unit | 表示无值, 对应 Java的 void. 用于不返回结果的方法的结果类型. Unit只有一个实例值()
Null | Null类型只有一个实例值 null. Null可以赋值给任意引用类型(AnyRef)
Nothing | Nothing类型在 Scala的类层级最低端, 它是任何其他类型的子类型. 没有正常的返回值是使用该类型比如抛异常的方法(很直观的告知该方法不会正常返回)
```
```

    //val n: Int = null // error
    var student: Student = new Student("alice", 20)
    student = null // 引用类型可以
    println(student) // null

    // 设置返回 Nothing, 意味着注定不会正常执行
    def fun0(n: Int): Nothing = throw new IllegalArgumentException
    val r0: Int = fun0(2) // 调用方法直接抛异常
    println("r0: " + r0) // 不会执行到该行

    def fun(n: Int): Int = {
      if (n == 0) throw new IllegalArgumentException
      else return n
    }
    val r1: Int = fun(3)
    println("r1: " + r1) // r1: 3

```
### 自动类型转换(隐式转换)
- 赋值或运算时, 小精度类型自动转换为大精度数值类型
1) 把精度大的数值类型赋值给精度小的数值类型时，就会报错，反之就会进行自动
   类型转换。
2) (Byte或 Short)与 Char之间是无法自动转换的, 不过 Char可以转 Int类型, 所以三者可以先转换成 Int, 再进行转换操作是可以的
- 从小到大自动转换顺序:
1) Byte -> Short -> Int -> Long -> Float -> Double
2) Char -> Int
```

val n: Double = 10 / 3 // 3.0: 计算时10和3都是 Int, 计算后转 Double
val n: Double = 10.0 / 3 // 3.3333333...5(二进制表示小数有偏差): 其中某一个是浮点数(默认 Double), 所以按照 Double计算, 再赋值

```

### 强制类型转换
```

	Java: Long num = (long) 2.5 // 2 浮点转长整数存在精度损失
	Scala: val num : Long = 2.5.toLong // 2
	val n1: Int = -2.9.toInt // -2

	val n: Int = 128
	val b: Byte = n.toByte
	// 溢出, 8位有符号补码 Byte整数, 正数最大值为127, 因此超出1, 会回到最小的-128
	println(b); // -128

```
`*注:高精度转换为低精度. 需要使用强转函数, 但可能会造成精度降低或溢出`

### 数值类型与 String类型间的转换
```

	数值类型转 String
	var str1 : String = true + "" // true
	var str2 : String = 4.5 + "" // 4.5
	var str3 : String = 100 +"" // 100

	String类型转数值类型(s1.toByte, s1.toShort, S1.toInt, s1.toLong, s1.toFloat, s1.toDouble)
    var s1 : String = "12"
    var n1 : Byte = s1.toByte
    var n2 : Short = s1.toShort
    var n3 : Int = s1.toInt
    var n4 : Long = s1.toLong
    var n5 : Float = s1.toFloat
    println(s1) // 12
    println(n1) // 12
    println(n2) // 12
    println(n3) // 12
    println(n4) // 12
    println(n5) // 12.0

	无法将浮点数字符串, 直接转整数
	val f2: Int = "12.3".toInt // 该行会抛 NumberFormatException异常
	val f2: Int = "12.3".toDouble.toInt // 需先转成浮点类型再转整数类型

```
`*注: String类型转成数值类型时, 需确保 String的数据能够转成有效的数据 如: "123"可以一个整数, 但是不能把"hello"转成整数`

### 运算符
- Java和 Scala中关于 == 的区别
```

Java: 
1) == 比较字符串变量时, 对象的内存地址
2) equals 比较字符串的内容
String s1 = "abc";
String s2 = new String("abc");
System.out.println(s1 == s2); // false
System.out.println(s1.equals(s2)); // true

Scala: 
1) == 比较字符串的内容, 与 Java的 equals相同效果
2) eq 判断引用地址
val s1 = "abc"
val s2 = new String("abc")
println(s1 == s2) // true
println(s1.eq(s2)) // false

```

- 赋值运算符
```
运算符 | 描述 | 实例
--|:--:|:--:
= | 简单的赋值运算符 | C = A + B
+= | 相加后再赋值 | C += A 等于 C = C + A
-= | 相减后再赋值 | C -= A 等于 C = C - A
*= | 相乘后再赋值 | C *= A 等于 C = C * A
/= | 相除后再赋值 | C /= A 等于 C = C / A
%= | 求余后再赋值 | C %= A 等于 C = C % A
<<= | 按位左移后赋值 | C <<= 2 等于 C = C << 2
>>= | 按位右移后赋值 | C >>= 2 等于 C = C >> 2
&= | 按位与后赋值 | C &= 2 等于 C = C & 2
^= | 按位异或后赋值 | C ^= 2 等于 C = C ^ 2
|= | 按位或后赋值 | C |= 2 等于 C = C | 2
`注: Scala中没有++, --操作符`
```
```

Java: 
byte b = 10;
b = (byte) (b + 1); // 该行会报错
b += 1; // 正常默认强转

Scala: 
var b: Byte = 10
b +=1 // 该行会报错, 如果 b是 Int则可以正常执行

```

- 位运算符
```
下表中变量 a为 60, b为 13
运算符 | 描述 | 实例
--|:--:|:--:
& | 按位与运算符 | (a & b) 输出结果 12, 二进制解释: 0000 1100
| | 按位或运算符 | (a | b) 输出结果 61, 二进制解释: 0011 1101
^ | 按位异或运算符 | (a ^ b) 输出结果 49, 二进制解释: 0011 0001
~ | 按位取反运算符 | (~a ) 输出结果 -61, 二进制解释: 1100 0011, 在一个有符号二进制数的补码形式
<< | 左移动运算符 | a << 2 输出结果 240, 二进制解释: 0011 0000
>> | 右移动运算符 | a >> 2 输出结果 15, 二进制解释: 0000 1111
>>> | 无符号右移 | a >>> 2 输出结果 15, 二进制解释: 0000 1111
`*以上a,b变量类型, 按照 Byte来计算, 如果 Short, Int, 则前面还需要补零`
```
```

2的32次方 = 4294967296
2的16次方 = 65536
2的10次方 = 1024 = 1K
2的8次方 = 256
2的7次方 = 128
2的6次方 = 64
2的5次方 = 32
2的4次方 = 16

- 数值类型皆为有符号补码型整数, 因此字节的首位为符号位

#  手动计算60的二进制, 选离60最接近的64(2的6次方)
1) 64的二进制: 0 011 1111, 因为2的6次方, 所以6个1
2) 正数包含0, 因此减0数的个数位, 64 - 1 = 63
3) 63 - 60 = 3, 因为目前二进制与实际目标数大3; 01=1,10=2,11=3
4) 0 011 1111 - 11(十进制数3) = 0 011 1100; 也就是60的二进制为 0011 1100

#  手动计算13的二进制, 选离13最接近的16(2的4次方)
1) 13的二进制: 0 000 1111, 因为2的4次方, 所以4个1
2) 正数包含0, 因此减0数的个数位, 16 - 1 = 15
3) 15 - 13 = 2, 因为目前二进制与实际目标数大2; 01=1,10=2
4) 0 000 1111 - 10(十进制数2) = 0 000 1101; 也就是13的二进制为 0000 1101

#  按位与运算符: (a & b)
0011 1100
0000 1101
--------- 上下2个皆为1时就1否则0
0000 1100 再转十进制数则为: 12

# 按位或运算符: (a | b)
0011 1100
0000 1101
--------- 上下其中1个为1时就1否则0
0011 1101 再转十进制数则为: 61

# 按位异或运算符: (a ^ b)
0011 1100
0000 1101
--------- 上下相同是0不同则1
0011 0001 再转十进制数则为: 49

#  按位取反运算符: (~a )
0011 1100
--------- 取反
1100 0011 
--------- 由于符号位发生变化, 需要再取反 + 1(有符号二进制数的补码表达形式
0011 1100 + 1
0011 1101 = 61, 然后因符号位是(-), 结果为: -61


# `<<` `>>` 两个尖括号位移动是符号位不变的(意思为开始是正数结果也是正数, 或负数结果也为负数
## 左移动运算符: (a << 2)
0011 1100
--------- 左移2位, 相当于原数 x 4(因为2的2次方, 如果 << 3的话, 原数 x 8; 或 << 4的话, 原数 x 16
1111 0000 再转十进制数则为: 240

val a: Byte = 60
println(a << 2) // 此时结果超出了 Byte, 所以 Scala自动将结果转为 Int

## 右移动运算符: (a >> 2)
0011 1100 a
--------- 右移2位, 相当于原数 ÷ 4
0000 1111 再转十进制数则为: 15

# 三个尖括号位移动是用于无符号的二进制, 也就是不考虑符号, 移到右边的低位被舍弃，对于无符号数高位补0
## 无符号右移: (a >>> 2)
0011 1100 a
--------- 右移2位
0000 1111 再转十进制数则为: 15

```
> - Scala运算符的本质是所有运算符都是方法
> 1) 点.可以省略
> 2) 参数只有一个, 或没有参数时, ()可以省略
```

// 标准的加法运算
val i:Int = 1.+(1)
// 省略点(.)
val j:Int = 1 + (1)
// 省略括号
val k:Int = 1 + 1
println(1.toString())
println(1 toString())
println(1 toString)
val n1: Int = 12
val n2: Int = 37
println(n1.+(n2))

```

## 流程控制
- 和 Java的区别
```

# if else表达式有返回值的
## 条件内最后一行表达式的值为它的返回值
请输入您的年龄：
50
    val result: Any = if (age <= 6) { // 返回值类型设置, 共同上级类型
      println("童年")
      "童年"
    } else if (age < 18) {
      println("青少年")
      "青少年"
    } else if (age < 35) {
      println("青年")
      age
    } else if (age < 60) {
      println("中年")
      age
    } else {
      println("老年")
      age
    }
    println("result: " + result)
中年
result: 50

# 在 Scala实现类似 Java的三元运算
val res: Any = if (50 < 18) "童年" else "成年"
println(res) // 成年

```

### For循环控制
- Scala的 For循环有非常多的特性
```

* to 方法调用了 RichInt.scala里的方法. Int没有继承 RichInt, 而是继承了 AnyVal& Any, 可是能用这个方法的原因是和 Scala的隐式转换有关
Scala内部 Predef.scala对象内定义了很多基本类型, 和很多低优先级的隐式转换, 其中有 如 intWrapper(x: Int)对 Int类型进行包装, 该方法包装后就是成了 runtime.RichInt(x); 编译过程就是, 会先在 Int里找有没有 .to方法. 如果没有它就会到隐式转换方法里继续找

# 1. 范围数据循环 To: 
	输出0~10的范围包含10
	for(i <- 0 to 10) { // 可以写成 0.to(10)
		println(i)
	}
    for (i <- Array(11, 22, 33)) {
      println(i)
    }
    for (i <- List(44, 55, 66)) {
      println(i)
    }
    for (i <- Set(77, 88, 99)) {
      println(i)
    }

# 2. 范围数据循环 Until: 
	输出0~10的范围不包含10
	for(i <- 1 until 10) { // 输出直到10,  不包含10; 等同 1 to 10 - 1 
		println(i)
	}

# 3. 循环守卫
for(i <- 1 to 3 if i != 2) { // 该条件类似 Java的 continue关键字; 当条件为 true则进入循环, 否则跳过
	print(i)
}
// 1
// 3

# 4. 循环步长
输出 1 到 6以内的所有奇数
for (i <- 1 to 6 by 2) { // by 表示步长
	println("i=" + i)
}
// i=1
// i=3
// i=5
for (i <- 6 to 1 by -2) { // 如果要倒序, 跳减2
  println("i=" + i)
}
// i=6
// i=4
// i=2

// 按浮点类型跳
for (i <- 1.0 to 2.0 by 0.3) { // 不过该方法 to已过期, 因为 Flaot/Double处理浮点数有精度缺失的问题, 官方推荐使用 BigDecimal
  println("i=" + i)
} 
// i=1.0
// i=1.3
// i=1.6
// i=1.9000000000000001

# 5. 嵌套循环
for(i <- 1 to 3; j <- 1 to 3) { // 通过分号; 来隔断逻辑
	println(" i =" + i + " j = " + j)
}

# 6. 引入变量
for(i <- 1 to 3; j = 4 - i) {
	println("i=" + i + " j=" + j)
}
或, 多个表达式时, 可以每行一个表达式, 并用花括号代替圆括号
for {
	i <- 1 to 3
	j = 4 - i
} {
	println("i=" + i + " j=" + j)
}
// i=1 j=3
// i=2 j=2
// i=3 j=1

# 7. 循环返回值
- 将遍历过程中处理的结果返回到 Vector集合中
- 循环加 yield后默认返回值是 IndexedSeq; 该类的伴生对象中有个实现, newBuilder实际上用的 Vector.newBuilder[A], 所以加了 yield后默认返回 Vector
val res = for(i <- 1 to 10) yield i * 2
println(res) // Vector(2, 4, 6, 8, 10, 12, 14, 16, 18, 20)

# 8. 倒序
for(i <- 10 to 1 by -1) {
	println(i)
}
for(i <- 1 to 10 reverse) {
	println(i)
}

// 99乘法表:
for (i <- 1 to 9) {
  for (j <- 1 to i) {
	print(s"$j * $i = ${i * j} \t")
  }
  println()
}
for (i <- 1 to 9; j <- 1 to i) {
  print(s"$j * $i = ${i * j} \t")
  if (j == i) println()
}
1 * 1 = 1
1 * 2 = 2 	2 * 2 = 4
1 * 3 = 3 	2 * 3 = 6 	3 * 3 = 9
1 * 4 = 4 	2 * 4 = 8 	3 * 4 = 12 	4 * 4 = 16
1 * 5 = 5 	2 * 5 = 10 	3 * 5 = 15 	4 * 5 = 20 	5 * 5 = 25
1 * 6 = 6 	2 * 6 = 12 	3 * 6 = 18 	4 * 6 = 24 	5 * 6 = 30 	6 * 6 = 36
1 * 7 = 7 	2 * 7 = 14 	3 * 7 = 21 	4 * 7 = 28 	5 * 7 = 35 	6 * 7 = 42 	7 * 7 = 49
1 * 8 = 8 	2 * 8 = 16 	3 * 8 = 24 	4 * 8 = 32 	5 * 8 = 40 	6 * 8 = 48 	7 * 8 = 56 	8 * 8 = 64
1 * 9 = 9 	2 * 9 = 18 	3 * 9 = 27 	4 * 9 = 36 	5 * 9 = 45 	6 * 9 = 54 	7 * 9 = 63 	8 * 9 = 72 	9 * 9 = 81

// 画三角形:
for (i <- 1 to 9) {
  val stars = 2 * i - 1
  val spaces = 9 - i
  println(" " * spaces + "*" * stars)
}
for (i <- 1 to 9; stars = 2 * i - 1; spaces = 9 - i) {
  println(" " * spaces + "*" * stars)
}
for (stars <- 1 to 17 by 2; spaces = (17 - stars) / 2) {
  println(" " * spaces + "*" * stars)
}

```
### While和 do..While循环控制
- 和 Java语言中用法相同, 不能使用 for循环的各种特性, 因此不推荐使用
- 与 for语句不同, while语句没有返回值, 它的返回固定死了 Unit类型()

### 循环中断
- Scala去掉了 break和 continue关键字, 加了 breakable方法实现了 break和 continue的功能
```

采用异常的方式退出循环
try { // scala的 try/catch是使用了模式匹配
  for (i <- 1 to 6) {
	println(i)
	if (i == 3) throw new IllegalArgumentException
  }
} catch {
  case e => println("e:" + e.getMessage)
}
println("正常结束循环")
// 1
// 2
// 3
// e:null
// 正常结束循环

采用 Scala的 breakable方法实现, 退出循环
import scala.util.control.Breaks
Breaks.breakable(
  for (i <- 1 to 10) {
	println(i)
	if (i == 5) Breaks.break()
  }
)
println("正常结束循环")

import scala.util.control.Breaks._ // _表示统配, 引入该包内所有方法
breakable(
  for (i <- 1 to 10) {
	println(i)
	if (i == 5) break()
  }
)
println("正常结束循环")


```

## 文件 IO操作
- Source(import scala.io.Source), 从数据源读取数据(文本文件, URL地址等
- 读取二进制文件: Scala没有提供读取二进制文件的方法, 所以需要引入 Java类库来实现
- 写入数据到文件: Scala没有提供对文件写入的方法, 所以需要引入 Java类库来实现
- 序列化和反序列化: 其内部也是使用的 Java的 Serializable
```
package com.company.test0

import java.io._
import scala.io.Source
import scala.io.BufferedSource

object Test0 {
  def main(args: Array[String]): Unit = {
    // 获取数据源文件对象
    //Source.fromFile("src/main/resources/test.txt").foreach(print)
    var source = Source.fromFile("src/main/resources/test.txt", "utf-8")
    // 以`行`为单位读取数据
    val lines: Iterator[String] = source.getLines()
    // 将读取到的数据封装到列表中.
    val list: List[String] = lines.toList
    println(list) // List(hello world, hello scala, 你好 Shawn!, 函数式编程!)
    source.close()

    var source2 = Source.fromFile("src/main/resources/test.txt", "utf-8")
    // 以`字符`为单位读取数据
    val iter: BufferedIterator[Char] = source2.buffered
    // 将读取到的数据封装到列表中.
    while (iter.hasNext) {
      print(iter.next())
    }
    //hello world
    //hello scala
    //你好 Shawn!
    //函数式编程!
    source2.close()
    println()

    var source3 = Source.fromFile("src/main/resources/test.txt", "utf-8")
    // 通过 mkString方法, 将文件中的所有数据封装到一个字符串中.
    println(source3.mkString)
    source3.close()

    val source4: BufferedSource = Source.fromFile("src/main/resources/test2.txt", "utf-8")
    //    10 8
    //    2 26
    val arr: Array[String] = source4.mkString.split("\\s+")
    arr.map(_.toInt).foreach(println)
    source.close()
    //    10
    //    8
    //    2
    //    26

    // 读指定 URL地址中的数据
    val source5 = Source.fromURL("https://www.baidu.com")
    //println(source5.mkString)
    source5.close()

    // 获取数据源文件对象.
    val source6 = Source.fromString("世界你们好!\n我好\n你好\n大家好")
    println(source6.getLines().mkString("\n"))
    //世界你们好!
    //我好
    //你好
    //大家好

    // 引入 Java类库来实现, 读取二进制文件
    val file = new File("src/main/resources/favicon.png")
    val fis = new FileInputStream(file)
    val bys = new Array[Byte](file.length().toInt)
    fis.read(bys)
    fis.close()
    println(bys.length) // 321

    // 将数据写入文件(字节流
    val fos = new FileOutputStream("src/main/resources/output1.txt")
    fos.write("你好世界!\n".getBytes())
    fos.write("Hello world!".getBytes())
    fos.close()

    // 将数据写入文件(字符流
    val writer = new PrintWriter(new File("src/main/resources/output2.txt"))
    writer.write("How are you!\nHi!")
    writer.write("你好!")
    writer.close()

    // 序列化和反序列化
    // 序列化: 文件不存在则会创建, 否则覆盖
    //    val oos = new ObjectOutputStream(new FileOutputStream("src/main/resources/person.txt"))
    //    oos.writeObject(new Person("Shawn", 35))
    //    oos.close()
    // 反序列化
    val ois = new ObjectInputStream(new FileInputStream("src/main/resources/person.txt"))
    var person: Person = ois.readObject().asInstanceOf[Person]
    println(person) // Person(Shawn,35)
  }

  // 样例类默认实现了 Serializable
  case class Person(var name: String, var age: Int)
}

```