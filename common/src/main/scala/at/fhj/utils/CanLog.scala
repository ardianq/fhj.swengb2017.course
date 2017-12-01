package at.fhj.utils

import java.util.logging.{Level, Logger}

trait CanLog {

  private final val log: Logger = Logger.getLogger(this.getClass.getName)

  private def toLogString(msg: Object): String = Option(msg).getOrElse("null").toString

  def logInfo(msg: String): Unit = log.info(msg)

  def logInfo(msg: Object): Unit = log.info(toLogString(msg))

  def logWarn(msg: String): Unit = log.warning(msg)

  def logWarn(msg: Object): Unit = log.warning(toLogString(msg))

  def logTrace(msg: String): Unit = log.finest(msg)

  def logTrace(msg: Object): Unit = log.finest(toLogString(msg))

  def logException(t: Throwable): Unit = log.log(Level.SEVERE, t.getMessage, t)

  def logException(msg: String, t: Throwable): Unit = log.log(Level.SEVERE, msg, t)

  def logException(msg: Object, t: Throwable): Unit = log.log(Level.SEVERE, toLogString(msg), t)

  def logError(msg: String): Unit = log.severe(msg)

  def logError(msg: Object): Unit = log.severe(toLogString(msg))

  def logNotImplemented(string: String): Unit = {
    logError("----------------------- NOT IMPLEMENTED -------------------- >> " + string)
  }

  /**
    * function to measure execution time of first function, optionally executing a display function.
    */
  def time[A](a: => A, display: Long => Unit = s => (), divisor: Int = 1000 * 1000): A = {
    val now = System.nanoTime
    val result = a
    val millis = (System.nanoTime - now) / divisor
    display(millis)
    result
  }
}