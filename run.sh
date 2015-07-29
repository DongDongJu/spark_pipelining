#!/bin/bash
export HADOOP_CONF_DIR=/hadoop/etc/hadoop
/hadoop/spark/bin/spark-submit --class "com.example.Hello" --master yarn-cluster --queue default /hadoop/data/main_job/target/scala-2.11/main_job_2.11-1.0.jar &
/hadoop/spark/bin/spark-submit --class "com.example.Hello" --master yarn-cluster --queue one /hadoop/data/slave0Job/target/scala-2.11/main_job_2.11-1.0.jar &
/hadoop/spark/bin/spark-submit --class "com.example.Hello" --master yarn-cluster --queue two /hadoop/data/slave1Job/target/scala-2.11/main_job_2.11-1.0.jar &
/hadoop/spark/bin/spark-submit --class "com.example.Hello" --master yarn-cluster --queue three /hadoop/data/slave2Job/target/scala-2.11/main_job_2.11-1.0.jar &
/hadoop/spark/bin/spark-submit --class "com.example.Hello" --master yarn-cluster --queue four /hadoop/data/slave3Job/target/scala-2.11/main_job_2.11-1.0.jar

