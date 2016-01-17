package org.banknoteauth.utility

import com.typesafe.config.ConfigFactory

object AppConfig {
  private val cf = ConfigFactory.load()

  val AppName = cf.getString("spark.appname")

  val Master = cf.getString("spark.master")
  
  val DataFile = cf.getString("app.data")
}