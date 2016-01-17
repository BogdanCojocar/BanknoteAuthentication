package org.banknoteauth.ml

import org.apache.spark.ml.param.ParamMap
import org.apache.spark.ml.tuning.ParamGridBuilder
import org.banknoteauth.utility.AppConfig
import org.apache.spark.SparkContext
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.tuning.CrossValidatorModel

protected class LogRegression extends Model {

  private val model = new LogisticRegression()
    .setFeaturesCol("features")
    .setPredictionCol("pred")
    .setLabelCol("label")

  def get = model

  def params: Array[ParamMap] = {
    val params = new ParamGridBuilder()
      .addGrid(model.regParam, Array(0.01, 0.1, 1))
      .addGrid(model.elasticNetParam, Array(0, 0.5, 1.0))
      .build()

    params
  }

  def getType:String = Model.LogRegClassifier 
}