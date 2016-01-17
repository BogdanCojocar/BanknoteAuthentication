package org.banknoteauth.ml

import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.feature.VectorAssembler
import org.banknoteauth.data.CsvSchema
import org.apache.spark.ml.PipelineStage

object AlgorithmStepCreator {

  private def assembler = new VectorAssembler()
    .setInputCols(CsvSchema.getFeaturesNames)
    .setOutputCol("features")

  private def indexer = new StringIndexer()
    .setInputCol("label")
    .setOutputCol("indexedLabel")

  def get(cls: String) = {
    val stages = cls match {
      case Model.LogRegClassifier => Array(assembler)
      case Model.RandForestClassifier => Array(assembler, indexer)
    }
    stages
  }
}