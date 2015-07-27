package com.example

 
import java.io.OutputStream
import java.io.IOException
import java.io.Writer
import org.apache.spark._

import org.apache.spark.SparkContext._

object Hello {
    val conf = new SparkConf().setAppName("hello").setMaster("yarn-cluster")
    val sc = new SparkContext(conf)
  def Check(): Boolean = {
    val hadoopConf = new org.apache.hadoop.conf.Configuration()
    val hdfs = org.apache.hadoop.fs.FileSystem.get(new java.net.URI("hdfs://192.168.100.123:9000"), hadoopConf)
    var exists = hdfs.exists(new org.apache.hadoop.fs.Path("/user/hadoop/scala_output/part-00000"))
    if(exists)
	true
    else
        false	
  }
  def Reducing():Unit ={
    val textLinesRDD = sc.textFile("hdfs://192.168.100.123:9000/user/hadoop/scala_output/part-00000")
    val outRDD = textLinesRDD
                    .flatMap(_.split("""\s+"""))
                    .map((_, 1))
                    .reduceByKey(_ + _)
                    .map(tuple => tuple._1 + "," + tuple._2)

      outRDD.saveAsTextFile("hdfs://192.168.100.123:9000/user/hadoop/final_result")
  }
  def main(args: Array[String]): Unit = {

    try {
	var FileCheck = false	
	do{
		FileCheck = Check()
		println("check")
	}while(!FileCheck)
	
        Reducing()

    } finally {

      sc.stop()

    }

  }

}
