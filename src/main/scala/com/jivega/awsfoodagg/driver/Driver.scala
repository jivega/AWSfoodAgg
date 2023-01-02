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
    val df = spark.read.option("header", "true").csv("s3://fao-awsfoodaggparam/data/Production_Crops_Livestock_E_All_Data_(Normalized).csv")
    df.printSchema()
    df.show(false)
    df.groupBy("Area").agg(count(lit(1)).alias("NumOfArea")).show(200,false)
    df.groupBy("Item").agg(count(lit(1)).alias("NumOfItem")).show(200,false)
    df.groupBy("Element").agg(count(lit(1)).alias("NumOfArea")).show(200,false)
    FaoPipeline.qualityData(df,spark)


    spark.stop()

  }
  def hello(): String = {
    "Hello, world!"
  }

}