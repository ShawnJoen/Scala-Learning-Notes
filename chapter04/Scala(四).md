# 集合
> 1) Scala有三大集合: Seq序列, Set集, Map映射, 所有的集合都扩展自 Iterable特质
> 2) Scala对几乎所有的集合类, 提供了可变和不可变的版本, 部分可变和不可变集合名称相同(*可通过包名称知晓集合类型
>> 2-1) 不可变集合: scala.collection.immutable
>
>> 2-2) 可变集合: scala.collection.mutable
> 3) Scala不可变集合, 是指集合元素不可修改, 如果修改了集合元素就会返回一个新的集合, 而原有集合元素不会被改动. 类似 Java的 String对象
> 4) 可变集合, 就是可以直接对原有集合元素进行增删改操作. 类似于 Java的 StringBuilder对象

`*集合增删操作函数使用建议: 不可变集合使用(+-=:)的符号函数, 可变集合使用英文名称的函数`

- 不可变集合继承图:

![image](https://github.com/ShawnJoen/Scala-Learning-Notes/blob/master/images/不可变集合继承图.jpg)

- 可变集合继承图:

![image](https://github.com/ShawnJoen/Scala-Learning-Notes/blob/master/images/可变集合继承图.jpg)

`*在 Scala List归属到 Seq下, Seq的直接子级有 IndexedSeq(索引序列)和 LinearSeq(线性序列)`
- IndexedSeq和 LinearSeq的区别:
1) IndexedSeq是通过索引来查找和定位的 如 String就是一个索引集合, 通过索引即可定位
2) LinearSeq是线型的, 即有头尾的概念, 这种数据结构一般是通过遍历来查找

## 数组(Array/ArrayBuffer)
- 数组属 Seq特征, 直属上级特征为 IndexedSeq(索引序列)
### 不可变数组: Array(Scala推荐使用不可变数组
- Scala: Array[Int](对应 Java是 int[]或 List<Integer>
- 查看 Array类源码, 可以发现直接父级是没有实现 IndexedSeq特征的, 但其实 Scala编译时, 将 Array在 Predef.scala中隐式转型成 abstract class WrappedArray[T]()抽象类(这个类实现了 IndexedSeq特征), 所以 Array可以使用 IndexedSeq特征的所有方法(可以理解为隐式实现 IndexedSeq特征)
- `*数组的不可变是指大小(元素个数), 已有元素的值是可以修改的`
- `*新增元素的函数中(:)必须朝向数组本身, 再使用符号(+)朝向要新增的元素值; 在这里元素值在前在后决定元素加到数组的末尾还是首位`
- 创建方式有两种:
1) val arr1 = new Array[Int](3) // 通过 new关键字(指定数据类型, 任意类型, 则 Any; 并指定大小(元素个数)
2) val arr2 = Array(1,2,3) // 通过伴生对象
```
package com.ex4.test1

object Test1 {
  def main(args: Array[String]): Unit = {
    // 通过 new关键字创建数组
    val arr1 = new Array[Int](3)
    arr1(0) = 8 // 索引位0的元素值改为8(Scala编译时将代码该写成 arr1.update(0, 8)
    println(arr1) // [I@4c203ea1 输出的是地址, 因为 Array, 没有 toString()方法
    println(arr1.mkString(",")) // 8,0,0
    // 通过伴生对象创建数组(推荐
    val arr2 = Array(5,6,7)
    println(arr2.mkString(",")) // 5,6,7

    // 访问元素
    println(arr2(0)) // 5(获取索引位0的元素值 (Scala编译时会改写成 arr2.apply(0)

    // 修改元素值
    arr2(1) = 9 // 将索引位1的元素值更改为9
    println(arr2.mkString(",")) // 5,9,7
    // arr2(3) ArrayIndexOutOfBoundsException: 3 数组越界

    // 遍历数组 1
    for (i <- 0 until arr2.length) {
      print(arr2(i) + ",")
    }
    println()
    // 5,9,7,

    // 遍历数组 2 (遍历指定数组的所有索引
    println(arr2.indices.mkString(",")) // 0,1,2 输出所有索引
    for (i <- arr2.indices) print(arr2(i) + ",")
    println()
    // 5,9,7,

    // 遍历数组 3
    for (elem <- arr2) print(elem + ",")
    println()
    // 5,9,7,

    // 遍历数组 4(迭代器
    val iter = arr2.iterator
    while (iter.hasNext) print(iter.next() + ",")
    println()
    // 5,9,7,

    // 遍历数组 5(foreach方法
    //    arr2.foreach((elem: Int) => println(elem)) 匿名函数方式
    //    arr2.foreach(println) 简化匿名函数

    val arr3 = Array(35, "Shawn") // 类型为 Any
    for (elem <- arr3) print(elem + ",") // 35,Shawn,
    println()

    // 添加元素
    val newArr = arr2.:+(10) // 数组的末尾新加元素(不可变数组, 新加元素只能生成新的
    println(arr2.mkString(",")) // 5,9,7 不变
    println(newArr.mkString(",")) // 5,9,7,10

    val newArr2 = newArr.+:(30) // 数组的开始处插入新元素
    println(newArr2.mkString(",")) // 30,5,9,7,10

    // 将以上新增元素方式简化
    val newArr3 = newArr2 :+ 99 // 加到末尾(计算顺序为从左到右
    println(newArr3.mkString(",")) // 30,5,9,7,10,99

    // 计算函数中(:)必须朝向数组本身, 再加符号(+)朝向要新增的元素值; 在这里元素值在前在后决定元素加到数组的末尾还是首位
    // val newArr4 = newArr3 +: 11 // value +: is not a member of Int
    val newArr4 = 11 +: newArr3 // 加到首位(计算顺序为从右到左
    println(newArr4.mkString(",")) // 11,30,5,9,7,10,99

    // 前后可以同时进行加减
    val newArr5 = 33 +: 22 +: newArr4 :+ 999 :+ 9
    println(newArr5.mkString(", ")) // 33, 22, 11, 30, 5, 9, 7, 10, 99, 999, 9
  }
}

```

### 可变数组: ArrayBuffer
- 继承自 IndexedSeq特征, 也就是有序的
```
package com.ex4.test2

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object Test2 {
  def main(args: Array[String]): Unit = {
    val arr1: ArrayBuffer[Int] = new ArrayBuffer[Int]() // 通过 new关键字(无参创建, 默认长度为16
    println(arr1) // ArrayBuffer()
    val arr2 = ArrayBuffer(5, 4, 6) // 通过伴生对象
    println(arr2)  // ArrayBuffer(5, 4, 6)

    // 访问元素
    // println(arr1(0)) IndexOutOfBoundsException: 0 索引溢出
    println(arr2(1)) // 4 获取索引位1的元素值
    arr2(1) = 40 // 将索引位1的元素值更改为40
    println(arr2) // ArrayBuffer(5, 40, 6)

    // 添加元素
    // 1. 可变数组同样可以使用符号函数来计算, 但不建议这样用
    // 2. arr1追加元素值20, 但可变数组特点是修改自身的元素个数, 如按照以下方式计算, 效果是和不可变一样会创建新数组, 自身是不会变化的
    val newArr1 = arr1 :+ 20
    println(arr1) // ArrayBuffer() 依然是空数组
    println(newArr1) // ArrayBuffer(20)
    println(arr1 == newArr1) // false 新生成的数组和原数组不关联的

    arr1 += 30 // 可变数组追加并改自身元素个数(计算顺序为从左到右-往后追加
    println(arr1) // ArrayBuffer(30)

    15 +=: arr1 // 加到首位(计算顺序为从右到左-往前追加
    println(arr1) // ArrayBuffer(15, 30)

    // 可变集合建议用英文名称的函数
    arr1.append(400, 500, 600) // 加到后面
    arr1.prepend(100, 200, 300) // 加到前面
    println(arr1) // ArrayBuffer(100, 200, 300, 15, 30, 400, 500, 600)

    arr1.insert(1, 101, 102, 103) // 索引位1, 插入1个或多个元素
    println(arr1) // ArrayBuffer(100, 101, 102, 103, 200, 300, 15, 30, 400, 500, 600)

    arr1.insertAll(8, arr2) // 索引位8, 插入 arr2数组的所有元素
    println(arr1) // ArrayBuffer(100, 101, 102, 103, 200, 300, 15, 30, 5, 40, 6, 400, 500, 600)
    arr1.prependAll(arr2) // 数组 arr1的开始处, 插入数组 arr2的所有元素;
    println(arr1) // ArrayBuffer(5, 40, 6, 100, 101, 102, 103, 200, 300, 15, 30, 5, 40, 6, 400, 500, 600)

    // 删除元素
    arr1.remove(2) // 删除索引位2的元素
    println(arr1) // ArrayBuffer(5, 40, 100, 101, 102, 103, 200, 300, 15, 30, 5, 40, 6, 400, 500, 600)

    arr1.remove(0, 2) // 删除索引位0, 开始2个元素(包含索引位0)
    println(arr1) // ArrayBuffer(100, 101, 102, 103, 200, 300, 15, 30, 5, 40, 6, 400, 500, 600)

    arr1.append(500, 500, 500)
    println(arr1) // ArrayBuffer(100, 101, 102, 103, 200, 300, 15, 30, 5, 40, 6, 400, 500, 600, 500, 500, 500)
    arr1 -= 500 // 删除值为500的元素, 每次只会删除一个, 优先删除越靠左的元素
    arr1 -= 500
    println(arr1) // ArrayBuffer(100, 101, 102, 103, 200, 300, 15, 30, 5, 40, 6, 400, 600, 500, 500)
  }
}

```

### 不可变数组与可变数组的转换
1) toArray: 转换为不可变数组
2) toBuffer: 转换为可变数组
```
package com.ex4.test3

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object Test3 {
  def main(args: Array[String]): Unit = {
    val arr: ArrayBuffer[Int] = ArrayBuffer(2, 3, 1)
    val newArr: Array[Int] = arr.toArray // 可变数组转换为不可变数组
    println(arr) // ArrayBuffer(2, 3, 1)
    println(newArr.mkString(", ")) // 2, 3, 1

    val buffer: mutable.Buffer[Int] = newArr.toBuffer // 不可变数组转换为可变数组
    println(newArr.mkString(", ")) // 2, 3, 1
    println(buffer) // ArrayBuffer(2, 3, 1)
  }
}

```

### 多维数组
- Scala最多支持5维数组
```
package com.ex4.test4

object Test4 {
  def main(args: Array[String]): Unit = {
    // 创建一维数组
    val arr1 = Array.ofDim[Int](2)
    arr1(1) = 10
    println(arr1.mkString(",")) // 0,10

    // 创建二维数组(最多可以创建5维数组
    val arr2: Array[Array[Int]] = Array.ofDim[Int](2, 3) // 2行3列
    // 访问元素
    arr2(0)(2) = 19
    arr2(1)(0) = 25
    println(arr2.mkString(", ")) // [I@17c68925, [I@7e0ea639
    for (i <- arr2.indices; j <- arr2(i).indices) {
      print(arr2(i)(j) + "\t")
      if (j == arr2(i).length - 1) println()
    }
    //    0   0   19
    //    25	0   0

    // 通过 lambda函数, 输出
    arr2.foreach(line => line.foreach(println))
    arr2.foreach(_.foreach(println))
  }
}

```

## 列表(List/ListBuffer)
- 列表属 Seq特征, 直属上级特征为 LinearSeq(线性序列). 线性序列底层没有索引, 而是有头尾的概念, 以及需要查找元素时, 会通过遍历来解决
### 不可变列表: List
1) List默认为不可变集合
2) 数据有顺序, 可重复
3) 创建只能通过伴生对象创建
4) 源码上 sealed abstract class List[+A], 其中 sealed是声明该抽象类是密封类, 该类的子类只能定义在同一个 List.scals文件内, 无法在其它文件中导入继承该抽象类
5) 底层没有索引, 但使用时, 可以索引的形式使用(该功能是在 LinearSeqOptimized特征的 apply方法, 通过遍历实现
6) 但修改元素值时, 无法通过锁定索引的形式修改(例 list(1) = 123), 因为内部没有 update方法
7) 空列表 Nil
8) (::)这个函数用于新增元素, 只会加到前面(主要用于通过 Nil创建列表
9) (:::)/(++)合并列表(所有列表的元素拆分扁平化后, 再合并
```
package com.ex4.test5

object Test5 {
  def main(args: Array[String]): Unit = {
    val list1 = List(20, 10, 30)
    println(list1) // List(20, 10, 30)

    // 底层没有索引, 但使用时, 可以索引形式使用(该功能是在 LinearSeqOptimized特征的 apply方法, 通过遍历实现
    println(list1(1)) // 10
    // list1(1) = 12 value update is not a member of List[Int] 由于是不可变列表

    // 遍历
    list1.foreach(println)

    // 添加元素
    val list2 = 1 +: list1 // 往前追加
    val list3 = list1 :+ 31 // 往后追加
    println(list1) // List(20, 10, 30)
    println(list2) // List(1, 20, 10, 30)
    println(list3) // List(20, 10, 30, 31)

    val list4 = list2.::(2) // (::)这个函数用于新增元素, 只会加到前面
    println(list4) // List(2, 1, 20, 10, 30)

    val list5 = Nil.::(100)
    println(list5) // List(100)
    val list6 = 10 :: 20 :: Nil
    val list7 = 20 :: 10 :: 30 :: 40 :: Nil
    println(list6) // List(10, 20)
    println(list7) // List(20, 10, 30, 40)

    // 合并列表
    val list8 = list6 :: list7 // (::)双:是新增元素的符号函数, 不是用于合并集合, 所以这样合并无法扁平化
    println(list8) // List(List(10, 20), 20, 10, 30, 40) 靠左的集合会占靠右的集合的一个元素位

    val list9 = list6 ::: list7 // (:::)三个:, 专用于合并集合的, 扁平化合并
    println(list9) // List(10, 20, 20, 10, 30, 40)

    val list10 = list6 ++ list7 // (++)双+, 效果和(:::)一样, 其实内部用了(:::)函数
    println(list10) // List(10, 20, 20, 10, 30, 40)

    println(list7.contains(30)) // true 判断元素是否存在
    println(list7.contains(999)) // false
    println(list7.size) // 4 长度
    println(list7.length) // 4 长度
  }
}

```

### 可变列表: ListBuffer
```
package com.ex4.test6

import scala.collection.mutable.ListBuffer

object Test6 {
  def main(args: Array[String]): Unit = {
    val list1: ListBuffer[Int] = new ListBuffer[Int]()
    println(list1) // ListBuffer()

    val list2 = ListBuffer(3, 2, 4) // 建议使用伴生对象
    println(list2) // ListBuffer(3, 2, 4)

    // 添加元素
    list1.append(20, 10)
    println(list1) // ListBuffer(20, 10)

    list2.prepend(1)
    println(list2) // ListBuffer(1, 3, 2, 4)

    list1.insert(1, 8, 9) // 索引位1, 插入两个元素一个8一个9
    println(list1) // ListBuffer(20, 8, 9, 10)

    18 +=: 19 +=: list1 += 11 += 12 // 可变集合改自身, 符号函数带(=)
    println(list1) // ListBuffer(18, 19, 20, 8, 9, 10, 11, 12)

    // 合并列表
    val list3 = list1 ++ list2 // 和不可变合并使用相同方式, 则返回新的列表, 自身不变
    println(list1) // ListBuffer(18, 19, 20, 8, 9, 10, 11, 12)
    println(list2) // ListBuffer(1, 3, 2, 4)
    println(list3) // ListBuffer(18, 19, 20, 8, 9, 10, 11, 12, 1, 3, 2, 4)

    list1 ++= list2 // 将 list2合并到 list1(也就是list1为主体, 将 list2新加到 list1的后面(尾部)), list2是不会变的
    println(list1) // ListBuffer(18, 19, 20, 8, 9, 10, 11, 12, 1, 3, 2, 4)
    println(list2) // ListBuffer(1, 3, 2, 4)

    list1 ++=: list2 // 将 list1合并到 list2(也就是list2为主体, 将 list1新加到 list2的前面(头部)), list1是不会变的
    println(list1) // ListBuffer(18, 19, 20, 8, 9, 10, 11, 12, 1, 3, 2, 4)
    println(list2) // ListBuffer(18, 19, 20, 8, 9, 10, 11, 12, 1, 3, 2, 4, 1, 3, 2, 4)

    // 修改元素
    list2(1) = 100 // 内部实现的就是.update
    list2.update(2, 200)
    println(list2) // ListBuffer(18, 100, 200, 8, 9, 10, 11, 12, 1, 3, 2, 4, 1, 3, 2, 4)

    // 删除元素
    list2.remove(2) // 通过索引位指定元素删除
    println(list2) // ListBuffer(18, 100, 8, 9, 10, 11, 12, 1, 3, 2, 4, 1, 3, 2, 4)

    list2 -= 3 // 删除值为3的元素, 每次只会删除一个, 优先删除越靠左的元素
    println(list2) // ListBuffer(18, 100, 8, 9, 10, 11, 12, 1, 2, 4, 1, 3, 2, 4)
  }
}

```

## Set集合(Set/mutable.Set)
- Set是特征, 所以只能通过单例对象创建集合
- Set默认是不可变集合, 数据无序& 不可重复
- 不可变和可变集合名称相同, 需要通过包名称来区分
### 不可变: Set
```
package com.ex4.test7

object Test7 {
  def main(args: Array[String]): Unit = {
    val set1 = Set(20, 10, 30, 10, 40)
    println(set1) // Set(20, 10, 30, 40)

    // 添加元素
    val set2 = set1 + 50
    println(set1) // Set(20, 10, 30, 40)
    println(set2) // Set(10, 20, 50, 40, 30)

    // 合并集合
    val set3 = Set(50,30,100,200)
    val set4 = set2 ++ set3
    println(set3) // Set(50, 30, 100, 200)
    println(set4) // Set(10, 20, 50, 40, 30, 200, 100)

    // 删除元素
    val set5 = set3 - 100 // 删除指定元素值
    println(set5) // Set(50, 30, 200)

    println(set3.contains(200)) // true 判断元素是否存在
    println(set3.contains(999)) // false
    println(set3.size) // 4 长度
  }
}

```

### 可变: mutable.Set
```
package com.ex4.test8

import scala.collection.mutable

object Test8 {
  def main(args: Array[String]): Unit = {
    val set1: mutable.Set[Int] = mutable.Set(20, 10, 30, 10, 40)
    println(set1) // Set(30, 20, 10, 40)

    // 添加元素
    val set2 = set1 + 50 // set1自身不会被改动
    println(set1) // Set(30, 20, 10, 40)
    println(set2) // Set(30, 20, 50, 10, 40)

    set1 += 200 // 加200到 set1(加到自身
    //set1 +=: 100 // value +=: is not a member of Int
    println(set1) // Set(30, 20, 10, 40, 200)

    val flag1 = set1.add(300) // 可变集合, 建议使用英文字母函数
    println(flag1) // true(判断成功与否
    println(set1) // Set(30, 300, 20, 10, 40, 200)
    val flag2 = set1.add(30)
    println(flag2) // false
    println(set1) // Set(30, 300, 20, 10, 40, 200)

    // 删除元素
    set1 -= 10
    println(set1) // Set(30, 300, 20, 40, 200)

    val flag3 = set1.remove(20) // 通过指定元素值, 删除
    println(flag3) // true
    println(set1) // Set(30, 300, 40, 200)
    val flag4 = set1.remove(10)
    println(flag4) // false
    println(set1) // Set(30, 300, 40, 200)

    // 合并两个Set
    println(set2) // Set(30, 20, 50, 10, 40)
    val set3 = set1 ++ set2 // 新建集合
    println(set3) // Set(30, 300, 20, 50, 10, 40, 200)

    set1 ++= set2 // 将 set2合并到 set1(也就是set1为主体, 将 set2(扁平化后)新加到 set1, set2是不会变的
    println(set1) // Set(30, 300, 20, 50, 10, 40, 200)
  }
}

```

## Map集合(Map/mutable.Map)
- Scala的 Map, 也是一个散列表. 存储键值对(key-value)映射
- Map是特征, 所以只能通过单例对象创建集合
- Map默认是不可变集合
- 不可变和可变集合名称相同, 需要通过包名称来区分
- Map集合 get函数返回一个特殊类型 Option(选项): Some(有值), None(无值)
### 不可变: Map
```
package com.ex4.test9

object Test9 {
  def main(args: Array[String]): Unit = {
    val map1: Map[String, Int] = Map("a" -> 20, "b" -> 10, "c" -> 30)
    println(map1) // Map(a -> 20, b -> 10, c -> 30)
    println(map1.getClass) // class scala.collection.immutable.Map$Map3

    // 遍历元素
    map1.foreach((kv: (String, Int)) => print(kv)) // (a,20)(b,10)(c,30)
    println()
    map1.foreach(print) // (a,20)(b,10)(c,30)
    println()

    // 取所有键
    for (key <- map1.keys) {
      println(s"$key -> ${map1.get(key)} -> ${map1.get(key).get}")
    }
    //    a -> Some(20) -> 20
    //    b -> Some(10) -> 10
    //    c -> Some(30) -> 30

    // 不可变 Map集合, 没有 put函数
    // map1.put("d", 100) // value put is not a member of Map[String,Int]

    println("a: " + map1.get("a").get) // 20
    println("b: " + map1.get("c")) // Some(30)
    println("d: " + map1.get("d")) // None
    println("d: " + map1.getOrElse("d", 0)) // 0 如果 key不存在, 返回 0
  }
}

```

### 可变: mutable.Map
```
package com.ex4.test10

import scala.collection.mutable

object Test10 {
  def main(args: Array[String]): Unit = {
    val map1: mutable.Map[String, Int] = mutable.Map("a" -> 20, "b" -> 10, "c" -> 30)
    println(map1) // Map(b -> 10, a -> 20, c -> 30)
    println(map1.getClass) // class scala.collection.mutable.HashMap

    // 添加元素
    map1.put("d", 50)
    map1.put("e", 40)
    println(map1) // Map(e -> 40, b -> 10, d -> 50, a -> 20, c -> 30)

    map1 += (("d", 60)) // 存在相同 key时, 后加的 value覆盖已有的 value
    map1 += (("f", 70)) // 不存在, 则创建
    println(map1) // Map(e -> 40, b -> 10, d -> 60, a -> 20, c -> 30, f -> 70)

    // 删除元素
    println(map1.remove("c")) // Some(30) 删除并返回
    println(map1.get("c")) // None
    println(map1.getOrElse("c", 0)) // 0 key不存在时返回0
    println(map1) // Map(e -> 40, b -> 10, d -> 60, a -> 20, f -> 70)

    map1 -= "a" // 指定要删除的 key
    map1 -= ("d","f") // 可以指定多个 key
    map1("shawn") = 35
    println(map1) // Map(e -> 40, b -> 10, shawn -> 35)

    // 修改元素 (和 put方法是等效的, 底层实现也是相同
    map1.update("d", 80)
    map1.update("e", 10)
    map1.update("f", 70) // 不存在也会新建
    println(map1) // Map(e -> 10, b -> 10, shawn -> 35, d -> 80, f -> 70)

    // 合并两个Map
    val map2: Map[String, Int] = Map("aa" -> 200, "bb" -> 100, "cc" -> 300)
    map1 ++= map2 // 将 map2合并到 map1集合中(更新 map1自身; map必须为可变集合, map2可以是不可变
    println(map2) // Map(aa -> 200, bb -> 100, cc -> 300)
    println(map1) // Map(e -> 10, b -> 10, shawn -> 35, bb -> 100, d -> 80, cc -> 300, f -> 70, aa -> 200)

    val map3: Map[String, Int] = map2 ++ map1 // 新建新的集合
  }
}

```

## 元组
- 元组是元素的组合, 说简单就是多个无关的数据封装为一个整体, 做成元素的组合

`*注: Scala中, 元组最大只能有22个元素`

- Map中的键值对其实就是元组(2元组, 也叫对耦元组)
```
package com.ex4.test11

object Test11 {
  def main(args: Array[String]): Unit = {
    // 创建4元组
    val tuple: (String, Int, Char, Boolean) = ("Shawn", 35, 'M', true)
    println(tuple) // (Shawn,35,M,true)

    // 访问数据( ._序号, 头一个从1开始
    println(tuple._1) // Shawn
    println(tuple._2) // 35
    println(tuple._3) // M
    println(tuple._4) // true

    // 通过索引访问数据
    println(tuple.productElement(1)) // 35

    // 遍历元组数据
    for (elem <- tuple.productIterator)
      print(elem + ",") // Shawn,35,M,true,
    println()

    // 嵌套元组
    val mulTuple = (10, 0.2, "Mia", (50, "Female"), 100)
    println(mulTuple._4._1) // 50

    // 元组形式创建 Map键值对
    val map = Map(("a",1), ("b",2), ("c",3))
    map.foreach(tuple => {
      println(tuple._1 + "=" + tuple._2)
    })
    //    a=1
    //    b=2
    //    c=3
  }
}

```

## 衍生集合
```
package com.ex4.test12

object Test12 {
  def main(args: Array[String]): Unit = {
    println("############## Set:")
    val set1 = Set(1, 3, 5, 7, 9, 11)
    val set2 = Set(11, 33, 55, 77, 99, 111, 200)

    val union2 = set1.union(set2) // Set并集(合并)后会去重, 和 ++函数一样效果
    println("union: " + union2) // union: Set(5, 1, 33, 9, 77, 7, 3, 11, 99, 55, 200, 111)
    println(set1 ++ set2) // Set(5, 1, 33, 9, 77, 7, 3, 11, 99, 55, 200, 111)

    println("############## List:")
    val list1 = List(1, 3, 5, 7, 9, 11)
    val list2 = List(11, 33, 55, 77, 99, 111, 200)
    // 获取集合的头元素值
    println(list1.head) // 1

    // 获取集合的尾(不是头的就是尾
    println(list1.tail) // List(3, 5, 7, 9, 11)

    // 集合最后一个数据
    println(list2.last) // 200

    // 集合初始数据(不包含最后一个
    println(list2.init) // List(11, 33, 55, 77, 99, 111)

    // 反转(自身不改动
    println(list1.reverse) // List(11, 9, 7, 5, 3, 1)

    // 取前/后的 n个元素; 返回取出的元素 (自身不改动
    println(list1.take(3)) // List(1, 3, 5)
    println(list1.takeRight(4)) // List(5, 7, 9, 11)

    // 去掉前/后的 n个元素; 返回剩余的元素 (自身不改动
    println(list1.drop(3)) // List(7, 9, 11)
    println(list1.dropRight(4)) // List(1, 3)
    println(list1) // List(1, 3, 5, 7, 9, 11)

    // 并集(合并)
    val union = list1.union(list2) // 和 :::函数一样效果
    println("union: " + union) // union: List(1, 3, 5, 7, 9, 11, 11, 33, 55, 77, 99, 111, 200)
    println(list1 ::: list2) // List(1, 3, 5, 7, 9, 11, 11, 33, 55, 77, 99, 111, 200)

    println(list1) // List(1, 3, 5, 7, 9, 11)
    println(list2) // List(11, 33, 55, 77, 99, 111, 200)

    // 交集(只返回相同元素)
    val intersection = list1.intersect(list2)
    println("intersection: " + intersection) // intersection: List(11)

    // 差集(独有的不重复的元素
    val diff1 = list1.diff(list2) // 只返回不存在于 list2的 list1的元素
    println("diff1: " + diff1) // diff1: List(1, 3, 5, 7, 9)
    val diff2 = list2.diff(list1) // 只返回 list2的元素, 不存在于 list1的元素
    println("diff2: " + diff2) // diff2: List(33, 55, 77, 99, 111, 200)

    // 拉链(如果两个集合的元素个数不相等, 多余的元素会被省略(合并后就成了2元组的列表
    println("zip: " + list1.zip(list2)) // zip: List((1,11), (3,33), (5,55), (7,77), (9,99), (11,111))
    println("zip: " + list2.zip(list1)) // zip: List((11,1), (33,3), (55,5), (77,7), (99,9), (111,11))

    // 滑窗
    println(list2.sliding(3)) // <iterator>
    for (elem <- list1.sliding(3)) println(elem)
    //    List(1, 3, 5)
    //    List(3, 5, 7)
    //    List(5, 7, 9)
    //    List(7, 9, 11)

    // 滑窗(第二个参数为步长, 默认为1
    // 最后列表如果不够了, 也按不够的输出, 最后的元组可能达不到长度
    for (elem <- list1.sliding(5, 2)) println(elem)
    //    List(1, 3, 5, 7, 9)
    //    List(5, 7, 9, 11)
  }
}

```

## 集合计算简单函数
```
package com.ex4.test13

object Test13 {
  def main(args: Array[String]): Unit = {
    val list1 = List(3, 2, 1, -2, 5, 4)
    val list2 = List(("a", 2), ("b", 3), ("c", 4), ("d", 5), ("o", 6), ("f", -7))
    // 求和
    println(list1.sum) // 13

    // 求乘积
    println(list1.product) // -240( 3*2*1*-2*5*4

    // 最大值
    println(list1.max) // 5
    println(list2.maxBy((tuple: (String, Int)) => tuple._2)) // (e,6)
    println(list2.maxBy(_._2)) // (e,6)

    // 最小值
    println(list1.min) // -2
    println(list2.minBy(_._2)) // (f,-7)


    //1) sorted: 对一个集合进行自然排序, 通过传递隐式的 Ordering
    //2) sortBy: 对一个属性或多个属性进行排序
    //3) sortWith: 基于函数的排序, comparator函数, 实现自定义排序的逻辑

    // 排序 sorted
    val sortedList = list1.sorted
    println(sortedList) // List(-2, 1, 2, 3, 4, 5)

    // 从大到小逆序排序
    println(list1.sorted.reverse) // List(5, 4, 3, 2, 1, -2)

    // 传入隐式参数
    println(list1.sorted(Ordering[Int].reverse)) // List(5, 4, 3, 2, 1, -2)

    println(list2.sorted) // List((a,2), (b,3), (c,4), (d,5), (f,-7), (o,6))

    // sortBy
    println(list2.sortBy(_._2)) // List((f,-7), (a,2), (b,3), (c,4), (d,5), (o,6))
    println(list2.sortBy(_._2)(Ordering[Int].reverse)) // List((o,6), (d,5), (c,4), (b,3), (a,2), (f,-7))

    // sortWith
    println(list1.sortWith((a: Int, b: Int) => { // 升序排序
      a < b
    })) // List(-2, 1, 2, 3, 4, 5)
    println(list1.sortWith(_ < _)) // List(-2, 1, 2, 3, 4, 5) 升序排序
    println(list1.sortWith(_ > _)) // List(5, 4, 3, 2, 1, -2) 降序排序
  }
}

```

## 集合高级函数
1) filter(过滤): 获取满足指定条件的元素集合
2) map(转化/映射):将集合中的每一个元素映射到某一个函数
3) flatten(扁平化): 打散元素
4) flatMap(扁平化 + 映射): 相当于先进行 map操作, 在进行 flatten操作集合中的每个元素的子元素映射到某个函数并返回新集合
5) group(分组): 按照指定的规则对集合的元素进行分组
6) Reduce(归约): 通过指定的逻辑将集合中的数据进行聚合, 最后将所有的数据做成归约处理(第一个元素为初始值
7) Fold(折叠): Fold 是 Reduce的一种扩展(使用了函数柯里化, 有两个参数列表)(相比 Reduce, 可以指定初始值
```
package com.ex4.test14

object Test14 {
  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
    // 过滤(选取偶数
    println(list.filter(_ % 2 == 0)) // List(2, 4, 6, 8)
    // 过滤(选取奇数
    println(list.filter(_ % 2 == 1)) // List(1, 3, 5, 7, 9)

    // map映射(把集合中每个数乘2
    println(list.map(_ * 2)) // List(2, 4, 6, 8, 10, 12, 14, 16, 18)
    // map映射(平方数
    println(list.map(x => x * x)) // List(1, 4, 9, 16, 25, 36, 49, 64, 81)

    val nestedList: List[List[Int]] = List(List(1, 2, 3), List(4, 5), List(6, 7, 8, 9))
    // 扁平化
    val flatList = nestedList(0) ::: nestedList(1) ::: nestedList(2)
    println(flatList) // List(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val flatList2 = nestedList.flatten
    println(flatList2) // List(1, 2, 3, 4, 5, 6, 7, 8, 9)

    val strings: List[String] = List("hello world", "hello scala", "hello java", "we study")
    // 扁平映射
    // 将一组字符串进行分词, 并保存成单词的列表
    val splitList: List[Array[String]] = strings.map(_.split(" ")) // 分词
    println(splitList) // List([Ljava.lang.String;@6a5fc7f7, [Ljava.lang.String;@3b6eb2ec, [Ljava.lang.String;@1e643faf, [Ljava.lang.String;@6e8dacdf)

    val flattenList = splitList.flatten // 打散扁平化
    println(flattenList) // List(hello, world, hello, scala, hello, java, we, study)

    val flatmapList = strings.flatMap(_.split(" ")) // 扁平映射
    println(flatmapList) // List(hello, world, hello, scala, hello, java, we, study)

    // groupBy分组
    val groupMap: Map[Int, List[Int]] = list.groupBy(_ % 2) // 分成奇偶两组
    println(groupMap) // Map(1 -> List(1, 3, 5, 7, 9), 0 -> List(2, 4, 6, 8))
    val groupMap2: Map[String, List[Int]] = list.groupBy(data => if (data % 2 == 0) "偶数" else "奇数")
    println(groupMap2) // Map(奇数 -> List(1, 3, 5, 7, 9), 偶数 -> List(2, 4, 6, 8))

    // 给定一组词汇, 按照单词的首字母进行分组
    val wordList = List("China", "America", "Korea", "Japan", "Canada", "Kasahstan")
    println(wordList.groupBy(_.charAt(0))) // Map(J -> List(Japan), A -> List(America), C -> List(China, Canada), K -> List(Korea, Kasahstan))
  }
}


package com.ex4.test15

import scala.collection.mutable

object Test15 {
  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3, 4)
    // Reduce(归约)
    // def reduce[A1 >: A](op: (A1, A1) => A1): A1 = reduceLeft(op)
    println(list.reduce(_ + _)) // 10 (内部调用了 reduceLeft
    // def reduceLeft[B >: A](op: (B, A) => B): B = {...}
    println(list.reduceLeft(_ + _)) // 10 (从左往右加
    println(list.reduceRight(_ + _)) // 10 (从右往左加

    val list2 = List(3, 4, 5, 8, 10)
    println(list2.reduce(_ - _)) // -24; 3-4-5-8-10
    println(list2.reduceLeft(_ - _)) // -24
    println(list2.reduceRight(_ - _)) // 6; 3 - (4 - (5 - (8 - 10))); --转正

    // Fold(折叠)
    println(list.fold(10)(_ + _)) // 20; 10 + 1 + 2 + 3 + 4
    println(list.foldLeft(10)(_ - _)) // 0; 10 - 1 - 2 - 3 - 4
    // foldRight方法内部实际调用 foldLeft, 只不过反转了一下
    // override def foldRight[B](z: B)(op: (A, B) => B): B = reverse.foldLeft(z)((right, left) => op(left, right))
    println(list2.foldRight(11)(_ - _)) // -5; 3 - (4 - (5 - (8 - (10 - 11))))

    // ==== 合并集合
    val map1 = Map("a" -> 1, "b" -> 3, "c" -> 5)
    val map2 = mutable.Map("a" -> 2, "b" -> 4, "c" -> 6, "d" -> 8)

    // 默认后面的集合覆盖前面的集合中重复 key的 value
    println(map1 ++ map2) // Map(a -> 2, b -> 4, c -> 6, d -> 8)

    // 下面的方式, 由于 map2是作为初始值参数, 传入到内部后, 将 key重复的 value累加后最终值覆盖到 map2中, 并返回
    // op匿名函数的首个参数 mergedMap是传入的初始值 map2
    // def fold[A1 >: A](z: A1)(op: (A1, A1) => A1): A1 = foldLeft(z)(op)
    // def foldLeft[B](z: B)(@deprecatedName('f) op: (B, A) => B): B = {...}
    // 在此使用 foldLeft, 而不是 fold的原因就是, 查看源码可以发现 op匿名函数部分
    // 匿名函数参数 kv是 map1集合的键值元组
    val map3 = map1.foldLeft(map2)((mergedMap, kv) => {
      val key = kv._1
      val value = kv._2
      mergedMap(key) = mergedMap.getOrElse(key, 0) + value
      mergedMap
    }
    )
    println(map3 == map1) // false
    println(map3 == map2) // true
    println(map3) // Map(b -> 7, d -> 8, a -> 3, c -> 11)
  }
}

```

### WordCount案例(单词计数)
- 实例1: 将集合中出现的相同的单词, 进行计数, 并取计数排名前三的结果
```
package com.ex4.test16

object Test16 {
  def main(args: Array[String]): Unit = {
    val stringList: List[String] = List(
      "hello",
      "hello world",
      "hello scala",
      "hello spark from scala",
      "hello flink from scala"
    )
    // 1. 对字符串进行切分, 得到一个打散所有单词的列表
    //    val wordList1: List[Array[String]] = stringList.map(_.split(" "))
    //    val wordList2: List[String] = wordList1.flatten // 扁平化
    //    println(wordList2) // List(hello, hello, world, hello, scala, hello, spark, from, scala, hello, flink, from, scala)
    val wordList = stringList.flatMap(_.split(" "))
    println(wordList) // List(hello, hello, world, hello, scala, hello, spark, from, scala, hello, flink, from, scala)

    // 2. 相同的单词进行分组
    val groupMap: Map[String, List[String]] = wordList.groupBy(word => word)
    println(groupMap)
    /* Map(world -> List(world), flink -> List(flink), spark -> List(spark), scala -> List(scala, scala, scala),
     from -> List(from, from), hello -> List(hello, hello, hello, hello, hello))*/

    // 3. 对分组之后的 list取长度, 得到每个单词的个数
    val countMap: Map[String, Int] = groupMap.map(kv => (kv._1, kv._2.length))
    println(countMap) // Map(world -> 1, flink -> 1, spark -> 1, scala -> 3, from -> 2, hello -> 5)

    // 4. 将 map转换为 list, 并排序取前3
    val sortList: List[(String, Int)] = countMap.toList
      .sortWith(_._2 > _._2)
      .take(3)
    println(sortList) // List((hello,5), (scala,3), (from,2))
  }
}

```
- 实例2
```
package com.ex4.test17

object Test17 {
  def main(args: Array[String]): Unit = {
    val tupleList: List[(String, Int)] = List(
      ("hello", 1),
      ("hello world", 2),
      ("hello scala", 3),
      ("hello spark from scala", 1),
      ("hello flink from scala", 2)
    )

    // 思路一: 直接展开为普通版本
    val newStringList: List[String] = tupleList.map(kv => {
      (kv._1 + " ") * kv._2
    }
    )
    println(newStringList)
    /*List(hello , hello world hello world , hello scala hello scala hello scala , hello spark from scala , hello flink from scala hello flink from scala )*/

    // 接下来操作与普通版本完全一致
    val wordCountList: List[(String, Int)] = newStringList
      .flatMap(_.split(" ")) // 空格分词
      .groupBy(word => word) // 按照单词分组
      .map(kv => (kv._1, kv._2.size)) // 统计出每个单词的个数
      .toList
      .sortBy(_._2)(Ordering[Int].reverse)
      .take(3)
    println(wordCountList) // List((hello,9), (scala,6), (from,3))

    // 思路二: 直接基于预统计的结果进行转换
    // 1. 将字符串打散为单词，并结合对应的个数包装成二元组
    val preCountList: List[(String, Int)] = tupleList.flatMap(tuple => {
      val strings: Array[String] = tuple._1.split(" ")
      strings.map(word => (word, tuple._2))
    }
    )
    println(preCountList)
    /*List((hello,1), (hello,2), (world,2), (hello,3), (scala,3), (hello,1), (spark,1), (from,1), (scala,1), (hello,2), (flink,2), (from,2), (scala,2))*/

    // 2. 对二元组按照单词进行分组
    val preCountMap: Map[String, List[(String, Int)]] = preCountList.groupBy(_._1)
    println(preCountMap)
    /*Map(world -> List((world,2)), flink -> List((flink,2)), spark -> List((spark,1)), scala -> List((scala,3), (scala,1), (scala,2)),
      from -> List((from,1), (from,2)), hello -> List((hello,1), (hello,2), (hello,3), (hello,1), (hello,2)))*/

    // 3. 叠加每个单词预统计的个数值
    val countMap: Map[String, Int] = preCountMap.mapValues(
      tupleList => tupleList.map(_._2).sum
    )
    println(countMap) // Map(world -> 2, flink -> 2, spark -> 1, scala -> 6, from -> 3, hello -> 9)

    // 4. 转换成 list, 排序取前3
    val countList = countMap.toList
      .sortWith(_._2 > _._2)
      .take(3)
    println(countList) // List((hello,9), (scala,6), (from,3))
  }
}

```

## 队列(Queue/mutable.Queue)
- 队列属 Seq特征, 直属上级特征为 LinearSeq(线性序列). 线性序列底层没有索引, 而是有头尾的概念, 以及需要查找元素时, 会通过遍历来解决
- 队列的特点就是`先进先出`. 线性数据接口, 头和尾两段进行进队和出队操作, 方法分别为 enqueue和 dequeue
```
package com.ex4.test18

import scala.collection.immutable.Queue
import scala.collection.mutable

object Test18 {
  def main(args: Array[String]): Unit = {
    // 创建一个可变队列
    val queue: mutable.Queue[String] = new mutable.Queue[String]()
    // 入队
    queue.enqueue("a", "b", "c", "ddd")
    println(queue) // Queue(a, b, c, ddd)
    println(queue.dequeue()) // a (出队
    println(queue) // Queue(b, c, ddd)
    println(queue.dequeue()) // b (出队
    println(queue) // Queue(c, ddd)

    queue.enqueue("d", "e")
    println(queue) // Queue(c, ddd, d, e)

    // 不可变队列
    val queue2: Queue[String] = Queue("a", "b", "c")
    val queue3 = queue2.enqueue("d") // 不可变自身不会更新, 而是会返回新的队列
    println(queue2) // Queue(a, b, c)
    println(queue3) // Queue(a, b, c, d)
  }
}

```

## 并行集合(Parallel)
- Scala为了充分使用多核 CPU, 提供了并行集合(默认串行集合
```
package com.ex4.test19

import scala.collection.immutable
import scala.collection.parallel.immutable.ParSeq

object Test19 {
  def main(args: Array[String]): Unit = {
    // 串行
    val result: immutable.IndexedSeq[Long] = (1 to 20).map(
      x => Thread.currentThread.getId
    )
    println(result)
    /*Vector(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)*/

    // 并行
    val result2: ParSeq[Long] = (1 to 20).par.map(
      x => Thread.currentThread.getId
    )
    println(result2)
    /*ParVector(11, 11, 11, 11, 11, 14, 14, 14, 14, 14, 12, 12, 12, 12, 12, 13, 13, 14, 14, 14)*/
  }
}

```
