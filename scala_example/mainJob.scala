package com.example

import org.apache.spark._
import org.apache.spark.SparkContext._
object Hello {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("hello").setMaster("yarn-cluster")
    val sc = new SparkContext(conf)
    val i = 0
    val textLinesRDD = sc.textFile("hdfs://192.168.100.123:9000/user/hadoop/input/1")
    try {
      for(i <- 0 to 3){
      var outRDD = textLinesRDD
                    .flatMap(_.split("""\s+"""))
                    .map((_, 1))
                    .reduceByKey(_ + _)
                    .map(tuple => tuple._1 + "," + tuple._2)

      outRDD.saveAsTextFile("hdfs://192.168.100.123:9000/user/hadoop/output/".concat(i.toString))
     }     
    } finally {
      sc.stop()
    }
  }
}


