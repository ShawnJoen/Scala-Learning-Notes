package com.ex6.test14

object Test14 {
  def main(args: Array[String]): Unit = {
    // 上下文限定是将泛型和隐式转换的结合产物, 以下两者功能相同，使用上下文限定[A : Ordering]之后, 方法内无法使用隐式参数名调用隐式参数, 需要通过 implicitly[Ordering[A]]
    def f[A:Ordering](a:A,b:A) =implicitly[Ordering[A]].compare(a,b)
    def f[A](a: A, b: A)(implicit ord: Ordering[A]) = ord.compare(a, b)

    // 这里 A是泛型名称, 后面 B是隐式参数类型名称
    def f[A : B](a: A) = println(a)
    // 等同于
    def f[A](a:A)(implicit arg:B[A]) = println(a)
  }
}