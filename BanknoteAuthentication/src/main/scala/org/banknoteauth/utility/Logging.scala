package org.banknoteauth.utility

import org.apache.log4j._
import org.apache.log4j.Level._

trait Logging {
  private[this] val logger = Logger.getLogger(getClass().getName());

  def debug(message: => String) = if (logger.isEnabledFor(DEBUG)) logger.debug(message)
  def debug(message: => String, ex: Throwable) = if (logger.isEnabledFor(DEBUG)) logger.debug(message, ex)

  def info(message: => String) = if (logger.isEnabledFor(INFO)) logger.info(message)
  def info(message: => String, ex: Throwable) = if (logger.isEnabledFor(INFO)) logger.info(message, ex)

  def warn(message: => String) = if (logger.isEnabledFor(WARN)) logger.warn(message)
  def warn(message: => String, ex: Throwable) = if (logger.isEnabledFor(WARN)) logger.warn(message, ex)

  def error(message: => String) = if (logger.isEnabledFor(ERROR)) logger.error(message)
  def error(message: => String, ex: Throwable) = if (logger.isEnabledFor(ERROR)) logger.error(message, ex)
}