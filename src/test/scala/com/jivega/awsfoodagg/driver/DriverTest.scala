package com.jivega.awsfoodagg.driver

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.funsuite.AnyFunSuite

import org.scalatest.matchers.should.Matchers

// class DriverTest extends AnyFlatSpec with Matchers {
//   "The Hello object" should "say hello" in {
//     "Hello, worldd!" shouldEqual "Hello, world!"
//   }
// }
class DriverTest extends AnyFunSuite {
  test("Hello should start with H") {
    // Hello, as opposed to hello
    assert("Hello".startsWith("H"))
  }
}