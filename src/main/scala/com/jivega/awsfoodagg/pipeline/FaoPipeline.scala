package com.jivega.awsfoodagg.pipeline
import org.apache.spark.sql.DataFrame 
import org.apache.spark.sql.functions.{count,lit}
import org.apache.spark.sql.SparkSession

object FaoPipeline {

  def qualityData(df: DataFrame, spark: SparkSession ): Unit = {
    println("Quality")
    df.groupBy("Area Code","Area Code (M49)","Area").agg(count(lit(1)).alias("NumOfArea")).sort("Area","Area Code","Area Code (M49)").show(300,false)
    
  }
}