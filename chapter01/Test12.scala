package com.ex.test12

import java.io._
import scala.io.Source
import scala.io.BufferedSource

object Test12 {
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