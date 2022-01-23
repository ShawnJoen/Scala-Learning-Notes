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