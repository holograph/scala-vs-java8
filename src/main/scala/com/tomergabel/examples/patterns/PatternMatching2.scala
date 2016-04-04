package com.tomergabel.examples.patterns

/**
  * Created by tomerga on 04/04/2016.
  */
object PatternMatching2 extends App {

  val Url = "(\\w+)://([a-zA-Z0-9.-]+?)(/[a-zA-Z0-9.-/]*)?".r

  args.head match {
    case Url(scheme, host, path) => println(s"Scheme: $scheme\nHost: $host\nPath: $path")
    case arg => println(s"Not a recognized URL: $arg")
  }
}
