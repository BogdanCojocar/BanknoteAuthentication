package org.banknoteauth.ml

import org.apache.spark.sql.SQLContext
import org.banknoteauth.data.CsvReader
import org.scalatest.BeforeAndAfter
import org.scalatest.Finders
import org.scalatest.FunSuite
import org.banknoteauth.driver.SparkSetup

object ModelCreatorTest {
  val MinThreshold = 0.95
}
import ModelCreatorTest._
class ModelCreatorTest extends FunSuite with BeforeAndAfter {
  val sparkSetup = new SparkSetup
  var csvReader: CsvReader = _

  before {
    csvReader = new CsvReader(sparkSetup.sqlCt)
  }

  test("LogisticRegression classification has a very good recognition rate") {
    val modelCreator = initModel(Model.LogRegClassifier)
    modelCreator.train
    val eval = modelCreator.evaluate

    assert(eval > MinThreshold)
  }
  
  test("RandomForest classification has a very good recognition rate") {
    val modelCreator = initModel(Model.RandForestClassifier)
    modelCreator.train
    val eval = modelCreator.evaluate

    assert(eval > MinThreshold)
  }

  test("ModelCreater is unable to evaluate a model before the training step") {
    val modelCreator = initModel(Model.LogRegClassifier)
    
    intercept[AssertionError] {
      modelCreator.evaluate
    }
  }
  
  test("ModelCreater is unable to save a model before the training step") {
    val modelCreator = initModel(Model.LogRegClassifier)
    
    intercept[AssertionError] {
      modelCreator.save
    }
  }

  after {
    sparkSetup.stop
  }

  def initModel(modelType: String): ModelCreator = {
    val model = ModelFactory(modelType)
    new ModelCreator(csvReader, model)
  }
}