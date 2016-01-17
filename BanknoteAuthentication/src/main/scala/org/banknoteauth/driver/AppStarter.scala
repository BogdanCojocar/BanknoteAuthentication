package org.banknoteauth.driver

import org.apache.spark.annotation.Since
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.evaluation.BinaryClassificationEvaluator
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.tuning.CrossValidator
import org.apache.spark.ml.tuning.ParamGridBuilder
import org.banknoteauth.utility.Logging
import org.banknoteauth.data.CsvReader
import org.banknoteauth.ml.ModelFactory
import org.banknoteauth.data.CsvSchema
import org.banknoteauth.ml.ModelCreator
import org.banknoteauth.ml.Model
import org.banknoteauth.ml.BanknoteAuth
import org.banknoteauth.data.CsvReader

object AppStarter extends Logging {

  // entry point in the app
  def main(args: Array[String]): Unit = {
    debug("BanknoteApp started...")
    val banknoteAuth = new BanknoteAuth
    
    debug("Train and save a model...")
    banknoteAuth.createModel
    
    debug("Evaluate model...")
    // get some test data
    val Array(test, _) = banknoteAuth.getCsvReader.split(50, 50)
    val predicted = banknoteAuth.check(test)
    
    debug("Print predictions...")
    predicted.foreach { row => println("true value: " + row.get(4) + " predicted: " + row.get(8)) }
  }
}