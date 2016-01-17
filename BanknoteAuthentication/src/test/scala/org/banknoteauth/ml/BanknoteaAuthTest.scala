package org.banknoteauth.ml

import org.scalatest.FunSuite

class BanknoteaAuthTest extends FunSuite {
  
  test("Check banknote authentication evaluation of a model") {
    val banknoteAuth = new BanknoteAuth
    
    val Array(test, _) = banknoteAuth.getCsvReader.split(50, 50)
    val predicted = banknoteAuth.check(test)
    
    val count = predicted.filter("label like pred").count()
    assert(count > 600)
  }
  
}