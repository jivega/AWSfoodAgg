package com.jivega.awsfoodagg.pipeline
import org.apache.spark.sql.DataFrame 
import org.apache.spark.sql.functions.{count,lit}
import org.apache.spark.sql.SparkSession

object FaoPipeline {

  def qualityData(df: DataFrame, spark: SparkSession ): Unit = {
    df.groupBy("Area Code","Area Code (M49)","Area").agg(count(lit(1)).alias("NumOfArea")).sort("Area Code","Area Code (M49)","Area").show(300,false)
    
    
  }
}