package com.example

 

import org.apache.spark._

import org.apache.spark.SparkContext._

object Hello {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("hello").setMaster("local")

    val sc = new SparkContext(conf)

    val textLinesRDD = sc.textFile("2")   
    
    try {
      val outRDD = textLinesRDD

                    .flatMap(_.split("""\s+"""))

                    .map((_, 1))

                    .reduceByKey(_ + _)

                    .map(tuple => tuple._1 + "," + tuple._2)

 

      outRDD.saveAsTextFile("out.txt")    	
    
    } finally {

      sc.stop()

    }

  }

}
