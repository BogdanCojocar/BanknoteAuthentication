package org.banknoteauth.ml

import java.io.File
import org.apache.commons.io.FileUtils
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.evaluation.BinaryClassificationEvaluator
import org.apache.spark.ml.tuning.CrossValidator
import org.apache.spark.ml.tuning.CrossValidatorModel
import org.banknoteauth.data.CsvReader
import ModelCreator._
import org.apache.spark.ml.PipelineStage
import org.banknoteauth.utility.Logging

object ModelCreator {
  val K = 5
  val ModelPath = "src/main/resources/model"
}

class ModelCreator(val csvReader: CsvReader, val model: Model) extends Logging {

  val Array(trainData, testData) = csvReader.split(80, 20)
  var trainedModel: CrossValidatorModel = _
  val evaluator = new BinaryClassificationEvaluator
  var isTrained = false

  def train {
    debug("train started...")
    val stages= AlgorithmStepCreator.get(model.getType)
    val completeStages = stages :+ model.get

    val pipeline = new Pipeline()
      .setStages(completeStages)

    val crossval = new CrossValidator()
      .setEstimator(pipeline)
      .setEvaluator(evaluator)

    crossval.setEstimatorParamMaps(model.params)
    crossval.setNumFolds(K)

    trainedModel = crossval.fit(trainData)
    isTrained = true
    debug("train finished")
  }

  def evaluate: Double = {
    assert(isTrained)
    val predicted = trainedModel.transform(testData)
    evaluator.evaluate(predicted)
  }

  def save {
    assert(isTrained)
    FileUtils.deleteQuietly(new File(ModelPath))
    trainedModel.save(ModelPath)
    debug("new model saved...")
  }
}