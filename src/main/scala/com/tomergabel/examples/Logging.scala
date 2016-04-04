package com.tomergabel.examples

import org.slf4j.LoggerFactory


trait Logging {
  private val logger = LoggerFactory.getLogger(this.getClass)

  def warn(message: => String): Unit =
    if (logger.isWarnEnabled)
      logger.warn(message)
  
  def warn(message: => String, error: Throwable): Unit =
    if (logger.isWarnEnabled)
      logger.warn(message, error)

  def debug(message: => String): Unit =
    if (logger.isDebugEnabled)
      logger.debug(message)

  def debug(message: => String, error: Throwable): Unit =
    if (logger.isDebugEnabled)
      logger.debug(message, error)

  def info(message: => String): Unit =
    if (logger.isInfoEnabled)
      logger.info(message)

  def info(message: => String, error: Throwable): Unit =
    if (logger.isInfoEnabled)
      logger.info(message, error)

  def error(message: => String): Unit =
    if (logger.isErrorEnabled)
      logger.error(message)

  def error(message: => String, error: Throwable): Unit =
    if (logger.isErrorEnabled)
      logger.error(message, error)
}
