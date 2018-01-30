export CADP=/Users/subhajitsidhanta/cadp
cd /Users/subhajitsidhanta/Downloads/spark-2.2.1
./bin/spark-submit --class org.apache.spark.examples.JavaWordCount --conf spark.logLineage=true --master local[2] examples/target/original-spark-examples_2.11-2.2.1.jar $3 >> $1.log 
./bin/spark-submit --class org.apache.spark.examples.JavaWordCount --conf spark.logLineage=true --master local[2] examples/target/original-spark-examples_2.11-2.2.1.jar $4 >> $2.log 
#./bin/spark-submit --class org.apache.spark.examples.JavaSparkPi --conf spark.logLineage=true --master local[2] examples/target/original-spark-examples_2.11-2.2.1.jar 10  >> sparkRDD.log
#./bin/spark-submit --class org.apache.spark.examples.JavaSparkPi --conf spark.logLineage=true --master local[2] examples/target/original-spark-examples_2.11-2.2.1.jar 10  >> sparkRDD1.log
#cd /Users/subhajitsidhanta/Dropbox/SparkRDDAnalyze/build/
#cd /Users/subhajitsidhanta/Dropbox/SparkRDDAnalyze/build/classes
#java -cp . com.spark.rdd.SparkRDDAnalyze $1
#java -cp . com.spark.rdd.SparkRDDAnalyze $2
java -jar "/Users/subhajitsidhanta/Dropbox/SparkRDDAnalyze/dist/SparkRDDAnalyze.jar" $1
java -jar "/Users/subhajitsidhanta/Dropbox/SparkRDDAnalyze/dist/SparkRDDAnalyze.jar" $2
export CADP=/Users/subhajitsidhanta/cadp
/Users/subhajitsidhanta/cadp/bin.mac86/bcg_io $1.aut $2.bcg
/Users/subhajitsidhanta/cadp/bin.mac86/bcg_io $2.aut $2.bcg
/Users/subhajitsidhanta/cadp/bin.mac86/bcg_cmp $1.bcg $2.bcg

