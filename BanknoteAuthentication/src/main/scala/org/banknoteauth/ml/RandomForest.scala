package org.banknoteauth.ml

import org.apache.spark.ml.PipelineStage
import org.apache.spark.ml.param.ParamMap
import org.apache.spark.ml.classification.RandomForestClassifier
import org.apache.spark.ml.tuning.ParamGridBuilder
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.mllib.tree.model.RandomForestModel
import org.banknoteauth.utility.AppConfig
import org.apache.spark.SparkContext

class RandomForest extends Model {

  private val model = new RandomForestClassifier()
    .setFeaturesCol("features")
    .setPredictionCol("pred")
    .setLabelCol("indexedLabel")
    .setFeatureSubsetStrategy("auto")

  def get: PipelineStage = model

  def params: Array[ParamMap] = {
    val params = new ParamGridBuilder()
      .addGrid(model.numTrees, Array(10, 50, 100))
      .addGrid(model.impurity, Array("entropy", "gini"))
      .build()

    params
  }

  def getType = Model.RandForestClassifier
}