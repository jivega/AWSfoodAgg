package com.jivega.awsfoodagg.driver

import org.apache.spark.sql.SparkSession

object Driver {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .appName("Spark FAO")
      .getOrCreate()           

    println(hello)
    
    spark.stop()

  }
  def hello(): String = {
    "Hello, world!"
  }

}