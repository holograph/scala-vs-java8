package com.tomergabel.examples.logging

import ch.qos.logback.classic.{Level, Logger => LogbackLogger}
import org.slf4j.{Logger, LoggerFactory}

/**
  * Created by tomerga on 04/04/2016.
  */
object LoggingSample extends App with Logging {

  // CLI --

  val (level, limit, errorAt) =
    args match {
      case Array(levelStr)                       => (Level.valueOf(levelStr), 100,            None)
      case Array(levelStr, limitStr)             => (Level.valueOf(levelStr), limitStr.toInt, None)
      case Array(levelStr, limitStr, errorAtStr) => (Level.valueOf(levelStr), limitStr.toInt, Some(errorAtStr.toInt))

      case _ =>
        error(s"Illegal arguments: $args")
        sys.exit(-1)
    }

  // Reset logging level --

  val rootLogger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME).asInstanceOf[LogbackLogger]
  rootLogger.setLevel(level)

  // Test code --

  info("Starting up!")

  try {
    for (x <- 0 until limit)
      if (errorAt.contains(x))
        throw new Exception(s"Artifical error thrown at index $x")
      else
        debug(s"At $x")
  } catch {
    case e: Throwable => error("Encountered some error", e)
  }

  info("Complete!")
}
