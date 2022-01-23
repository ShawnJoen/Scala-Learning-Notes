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
