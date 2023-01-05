package com.jivega.awsfoodagg.pipeline
import org.apache.spark.sql.DataFrame 
import org.apache.spark.sql.functions.{count,lit}
import org.apache.spark.sql.SparkSession

object FaoPipeline {

  def qualityData(df: DataFrame, spark: SparkSession ): Unit = {
    println("Quality")
    df.groupBy("Area Code","Area Code (M49)","Area").agg(count(lit(1)).alias("NumOfArea")).sort("Area","Area Code","Area Code (M49)").show(500,false)
    df.groupBy("Item Code","Item Code (CPC)","Item").agg(count(lit(1)).alias("NumOfItems")).sort("Item","Item Code","Item Code (CPC)").show(500,false)
    df.groupBy("Element Code","Element").agg(count(lit(1)).alias("NumOfElements")).sort("Element","Element Code").show(500,false)
    df.groupBy("Unit").agg(count(lit(1)).alias("NumOfUnits")).sort("Unit").show(300,false)
    
  }
}