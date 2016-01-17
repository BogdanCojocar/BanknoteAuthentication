package org.banknoteauth.ml

import org.apache.spark.ml.param.ParamMap
import org.apache.spark.ml.classification.Classifier
import org.apache.spark.ml.PipelineStage
import org.apache.spark.SparkContext
import org.apache.spark.ml.tuning.CrossValidatorModel

object Model {
  val LogRegClassifier = "logisticReg"
  val RandForestClassifier = "randForest"
}

trait Model {
  def get: PipelineStage
  def params: Array[ParamMap]
  def getType: String
  def load: CrossValidatorModel = {
    CrossValidatorModel.load(ModelCreator.ModelPath)
  }
}