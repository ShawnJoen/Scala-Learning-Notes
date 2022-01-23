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
