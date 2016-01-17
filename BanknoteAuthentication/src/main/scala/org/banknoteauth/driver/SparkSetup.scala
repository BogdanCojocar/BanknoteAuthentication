package org.banknoteauth.driver
import org.banknoteauth.utility.AppConfig._
import org.apache.spark.sql.SQLContext
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
class SparkSetup {
  
  private val conf = new SparkConf()
    .setAppName(AppName)
    .setMaster(Master)

  private val sparkContext = new SparkContext(conf)
  private val sqlContext = new SQLContext(sparkContext)

  def sc = sparkContext
  def sqlCt = sqlContext
  def stop = sparkContext.stop
}