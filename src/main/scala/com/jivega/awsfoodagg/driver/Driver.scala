package com.jivega.awsfoodagg.driver

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{count,lit}
import com.jivega.awsfoodagg.pipeline.FaoPipeline

object Driver {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .appName("Spark FAO")
      .getOrCreate()           

    println(hello)
    
    new FaoPipeline(spark).run()
    
    // FaoPipeline.qualityData(df,spark)


    spark.stop()

  }
  def hello(): String = {
    "Hello, world!"
  }

}