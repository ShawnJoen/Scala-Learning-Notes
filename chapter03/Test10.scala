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
