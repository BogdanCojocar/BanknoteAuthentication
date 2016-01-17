package org.banknoteauth.ml

import org.apache.spark.mllib.classification.LogisticRegressionModel
import org.banknoteauth.data.CsvReader
import org.banknoteauth.driver.SparkSetup
import org.apache.spark.sql.DataFrame
import org.banknoteauth.utility.Logging
class BanknoteAuth extends Logging {
  private val spark = new SparkSetup
  private val csvReader = new CsvReader(spark.sqlCt)
  private val model: Model = ModelFactory(Model.LogRegClassifier)
  private val classifier = model.load

  def createModel {
    debug("Model creation started...")
    val modelCreator = new ModelCreator(csvReader, model)
    modelCreator.train
    modelCreator.save
  }

  def check(data: DataFrame): DataFrame = {
    classifier.transform(data)
  }

  def getCsvReader = csvReader
}