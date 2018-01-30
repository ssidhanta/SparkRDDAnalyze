# SparkRDDAnalyze

This tool is used to run two Spark jobs and check if they are BISIMILAR or not.
First, the tool runs the Spark jobs, and  collects the log files. 
Then it extracts the RDD lineage DAGs from the log files and stores them as AUT files.
Then it calls the bcg_io method of the CADP tool to convert the AUT files to BCG format.
Finally it runs the bcg_cmp method of CADP to check if the two bcg files are strongly BISIMILAR or not.

This tool can be run as follows.


$TOOL_PATH/runbisim.sh <logfilename1> <logfilename2> <sparkinputfile1> <sparkinputfile2>

The parameters <logfilename1> <logfilename2> <sparkinputfile1> <sparkinputfile2> are filenames without extensions for the spark log files and the spark input files, respectively.

The Spark home directory path needs to be set in the project first in the file SparkRDDAnalyze.java.

