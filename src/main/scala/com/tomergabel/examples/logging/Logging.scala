package com.tomergabel.examples.logging

import org.slf4j.LoggerFactory


trait Logging {
  private val logger = LoggerFactory.getLogger(this.getClass)

  protected def warn(message: => String): Unit =
    if (logger.isWarnEnabled)
      logger.warn(message)

  protected def warn(message: => String, error: Throwable): Unit =
    if (logger.isWarnEnabled)
      logger.warn(message, error)

  protected def debug(message: => String): Unit =
    if (logger.isDebugEnabled)
      logger.debug(message)

  protected def debug(message: => String, error: Throwable): Unit =
    if (logger.isDebugEnabled)
      logger.debug(message, error)

  protected def info(message: => String): Unit =
    if (logger.isInfoEnabled)
      logger.info(message)

  protected def info(message: => String, error: Throwable): Unit =
    if (logger.isInfoEnabled)
      logger.info(message, error)

  protected def error(message: => String): Unit =
    if (logger.isErrorEnabled)
      logger.error(message)

  protected def error(message: => String, error: Throwable): Unit =
    if (logger.isErrorEnabled)
      logger.error(message, error)
}
