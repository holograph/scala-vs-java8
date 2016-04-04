package com.tomergabel.examples.patterns

/**
  * Created by tomerga on 04/04/2016.
  */
object PatternMatching3 extends App {

  val Url = "(\\w+)://([a-zA-Z0-9.-]+?)(/[a-zA-Z0-9.-/]*)?".r

  object Http {
    def unapply(url: String): Option[(String, String, Boolean)] = url match {
      case Url("http", host, path) => Some(host, path, false)
      case Url("https", host, path) => Some(host, path, true)
      case _ => None
    }
  }

  object Ftp {
    def unapply(url: String) = url match {
      case Url("ftp", host, path) => Some(host, path)
      case _ => None
    }
  }

  args.head match {
    case Http(host, path, false) => println(s"HTTP URL detected\nHost: $host\nPath: $path")
    case Http(host, path, true) => println(s"SSL-enabled HTTP URL detected\nHost: $host\nPath: $path")
    case Ftp(host, path) => println(s"FTP URL detected\nHost: $host\nPath: $path")
    case arg @ Url(scheme, _, _) => println(s"Unsupported scheme $scheme for URL: $arg")
    case arg => println(s"Not a recognized URL: $arg")
  }
}
