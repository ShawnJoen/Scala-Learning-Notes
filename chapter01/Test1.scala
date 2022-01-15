package com.ex.test1

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
