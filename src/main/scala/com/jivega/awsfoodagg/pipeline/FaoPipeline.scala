package com.jivega.awsfoodagg.pipeline
import org.apache.spark.sql.DataFrame 
import org.apache.spark.sql.functions.{count,lit,col}
import org.apache.spark.sql.SparkSession

class FaoPipeline (spark: SparkSession) {
  val pathProduction = "s3://fao-awsfoodaggparam/data/Production_Crops_Livestock_E_All_Data_(Normalized).csv"
  val pathArea = "s3://fao-awsfoodaggparam/datamaster/FAOSTAT_data_1-5-2023.csv"
  def run():Unit = {
    println("Pipeline Run")
    val df = spark.read.option("header", "true").option("inferSchema","True").csv(pathProduction)
    df.printSchema()
    df.show(false)
    df.groupBy("Area").agg(count(lit(1)).alias("NumOfArea")).show(200,false)
    df.groupBy("Item").agg(count(lit(1)).alias("NumOfItem")).show(200,false)
    df.groupBy("Element").agg(count(lit(1)).alias("NumOfArea")).show(200,false)
    val dfArea = spark.read.option("header", "true").option("inferSchema","True").csv(pathArea)
    dfArea.printSchema()
    dfArea.show(300,false)
    dfArea.filter(df("Country") === "Afghanistan" ).sort(col("Country Group").desc).show(300,false) 
    qualityData(df,dfArea)
  }


  def qualityData(df: DataFrame,dfArea: DataFrame): Unit = {
    println("Quality")
    df.groupBy("Area Code","Area Code (M49)","Area").agg(count(lit(1)).alias("NumOfArea")).sort("Area","Area Code","Area Code (M49)").show(500,false)
    df.groupBy("Item Code","Item Code (CPC)","Item").agg(count(lit(1)).alias("NumOfItems")).sort("Item","Item Code","Item Code (CPC)").show(500,false)
    df.groupBy("Element Code","Element").agg(count(lit(1)).alias("NumOfElements")).sort("Element","Element Code").show(500,false)
    df.groupBy("Unit").agg(count(lit(1)).alias("NumOfUnits")).sort("Unit").show(300,false)
    // Top Olive Oil '2167
    df.filter(df("Item Code (CPC)") === "'2167"  && df("Year") === "2019" ).sort(col("Value").desc).show(300,false)
    df.filter(df("Item Code (CPC)") === "'2167"  && df("Year") === "2020" ).sort(col("Value").desc).show(300,false)
    df.filter(df("Item Code (CPC)") === "'2167"  && df("Year") === "2021" ).sort(col("Value").desc).show(300,false)
    
    // Sugar Cane '01802
    df.filter(df("Item Code (CPC)") === "'01802"  && df("Year") === "2019" ).sort(col("Value").desc).show(300,false)
    df.filter(df("Item Code (CPC)") === "'01802"  && df("Year") === "2020" ).sort(col("Value").desc).show(300,false)
    
    // Spain Area '724
    df.filter(df("Item Code (CPC)") === "'01802"  && df("Area Code (M49)") === "'724" ).sort(col("Year").desc,col("Element").desc).show(300,false)
    // Europe '150 
    df.filter(df("Item Code (CPC)") === "'01802"  && df("Area Code (M49)") === "'150" ).sort(col("Year").desc,col("Element").desc).show(300,false)
    // Europe '097
    df.filter(df("Item Code (CPC)") === "'01802"  && df("Area Code (M49)") === "'097" ).sort(col("Year").desc,col("Element").desc).show(300,false)
    
    
    df.filter(df("Year") === "2020" ).sort(col("Value").desc).show(200,false)
  }
}