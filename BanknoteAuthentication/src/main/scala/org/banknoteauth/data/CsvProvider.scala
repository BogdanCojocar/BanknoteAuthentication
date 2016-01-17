package org.banknoteauth.data

import org.apache.spark.sql.SQLContext
import org.banknoteauth.utility.AppConfig

class CsvReader(sqlSQL: SQLContext) {
  val schema = CsvSchema.create
  val banknoteAuthData = readBanknoteAuthData

  private def readBanknoteAuthData = {
    sqlSQL.read
      .format("com.databricks.spark.csv")
      .option("header", "false")
      .schema(schema)
      .load(AppConfig.DataFile)
  }
  
  def getData = banknoteAuthData

  def split(train: Int, test: Int) = {
    assert(train + test == 100)
    banknoteAuthData.randomSplit(Array(train, test))
  }

}