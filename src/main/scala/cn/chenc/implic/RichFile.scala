package cn.chenc.implic

import java.io.File

import cn.chenc.implic.MyPredef._

import scala.io.Source

/**
  * Created by root on 2016/5/13.
  */




class RichFile(val f: File) {
  def read() = Source.fromFile(f).mkString
}

object RichFile {
  def main(args: Array[String]) {
    val f = new File("c://words.txt")
    //装饰 ，显示的增强
    //val contents = new RichFile(f).read()
    val contents = f.read()
    println(contents)
  }
}


