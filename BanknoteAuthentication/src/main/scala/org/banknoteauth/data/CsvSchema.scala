package org.banknoteauth.data

import org.apache.spark.sql.types.DoubleType
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.StructField

object CsvSchema {
  val FirstFeature = "vwti"
  val SecondFeature = "swti"
  val ThirdFeature = "cwti"
  val FourthFeature = "eoi"
  val Label = "label"

  def create =
    StructType(Seq(
      StructField(FirstFeature, DoubleType, false),
      StructField(SecondFeature, DoubleType, false),
      StructField(ThirdFeature, DoubleType, false),
      StructField(FourthFeature, DoubleType, false),
      StructField(Label, DoubleType, false)))

  def getFeaturesNames = Array(FirstFeature, SecondFeature, ThirdFeature, FourthFeature)
}
  
