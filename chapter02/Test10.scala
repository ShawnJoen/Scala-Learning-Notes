package com.ex2.test10

import java.io.File

object Test10 {
  def printFilePath(dir: File): Unit = {
    if (!dir.exists()) {
      println("不存在的路径!")
    } else {
      val listFiles: Array[File] = dir.listFiles()
      for (filePath <- listFiles) {
        if (filePath.isFile) println(filePath) else printFilePath(filePath)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    printFilePath(new File("E:\\Download"))
  }
}
