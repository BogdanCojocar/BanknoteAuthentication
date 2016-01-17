package org.banknoteauth.ml

import Model._

object ModelFactory {
  def apply(cls: String): Model = {
    cls match {
      case LogRegClassifier => new LogRegression
      case RandForestClassifier => new RandomForest
    }
  }
}