/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spark.rdd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author subhajitsidhanta
 */
public class SparkRDDAnalyze {

    /**
     * @param args the command line arguments
     */
    private static String filepath = "/Users/subhajitsidhanta/Downloads/spark-2.2.1/";///sparkRDD.log";
    private static String filepath1 = "/Users/subhajitsidhanta/Downloads/spark-2.2.1/sparkRDDDAG.aut";
    public static void main(String[] args) {
        // TODO code application logic here
        BufferedReader br = null;
        try {
            
            //args[0]="sparkRDD";
            //args[1]="sparkRDD";
            //br = new BufferedReader(new FileReader(filepath+"sparkRDD.log"));
            br = new BufferedReader(new FileReader(filepath+args[0]+".log"));
            String line, str = "", autStr = "", header =null, rdd = null, action = null, rddprev = null, actionprev = null,rddnext = null, actionnext = null;
            int statenum = 0, dest = 0, index = 0, index1 = 0;
            boolean flag = false;
            BufferedWriter writer = null;
            List<String> states = new ArrayList();
            while ((line = br.readLine()) != null){// &&  !"".equalsIgnoreCase(line.trim()) && line.trim().toUpperCase().contains("RDD")) {
                if(line.contains("RDD"))//"*****Subhajit Sidhanta todebugstring:"))
                {    
                    
                    if(!flag)
                    {
                        //str = str + line.split("\\*\\*\\*\\*\\*Subhajit Sidhanta todebugstring:")[0];
                        if(line.contains("*****Subhajit Sidhanta todebugstring:"))
                            states.add(line.split("Subhajit Sidhanta todebugstring:")[1]);
                        else
                            states.add(line);
                        flag = true; 
                    }
                    else// if (line.trim().toUpperCase().contains("RDD"))
                    {
                        //str = line.split("Subhajit Sidhanta todebugstring:")[1]  + "\n";
                        flag=false;
                        //break;
                    }    
                }
                else
                {        
                    if(flag)// && line.trim().toUpperCase().contains("RDD"))
                        states.add(line);//str = str + "\n";
                }
            }
           Collections.reverse(states);
           for (String state : states)
            System.out.println("****state:"+state+"\n");
           if(states.size()>0)
           {
           for (String state : states) {
               if(state.contains(" at "))
                    rdd = state.split(" at ")[0];
                if(state.contains(" at "))
                    action = state.split(" at ")[1].split(" at ")[0];
                if(rdd!=null && !"".equalsIgnoreCase(rdd) && rdd.contains("RDD[") && rdd.contains("]") && !"".equalsIgnoreCase(rdd.split("RDD\\[")[1].split("]")[0].trim()))
                {    
                    index = Integer.parseInt(rdd.split("RDD\\[")[1].split("]")[0].trim());
                    //System.out.println("***rdd vaue is: "+rdd +" index: "+ index);
                }
                
                if(statenum==0)
                {
                   
                    autStr = autStr + "\n(" + statenum + ", " + action  + ", " + String.valueOf(statenum+1) + ")";
                    statenum = statenum+1;
                    rddprev = rdd;
                    actionprev = action;
                }
                else
                {
                    //if(action.equalsIgnoreCase(actionprev))
                    //{
                        //dest=statenum;
                        //for (String state1 : states) {
                        for (String state1 : states) {
                            rddnext = state1.split(" at ")[0];
                            if(!"".equalsIgnoreCase(state1) && state1.contains(" at ") && state1.split(" at ")[1].contains(" at "))
                                actionnext = state1.split(" at ")[1].split(" at ")[0];
                            
                            if(rddnext!= null && !"".equalsIgnoreCase(rddnext) && rddnext.contains("RDD[") && rddnext.contains("]"))// && !"".equalsIgnoreCase(rdd.split("RDD\\[")[1].split("]")[0].trim()))
                                index1 = Integer.parseInt(rddnext.split("RDD\\[")[1].split("\\]")[0].trim());
                            
                            //System.out.println("***index1 vaue is: "+index1 +" index: "+ index);
                            if(index1>index && index1 > statenum)
                            {
                                //System.out.println("***rddnext vaue is: "+rddnext +" index1: "+ index1);
                                if(rddnext!= null && actionnext!= null && !rdd.equalsIgnoreCase(rddnext) && !action.equalsIgnoreCase(actionnext))
                                {
                                    dest=index1;
                                    break;
                                }
                            }
                        }
                    //}
                    autStr = autStr + "\n(" + statenum + ", " + action  + ", " + dest + ")";
                    statenum = statenum+1;
                    rddprev = rdd;
                    actionprev = action;
                }
           }
           }
//           String[] rddArr = null;
//           if(str.contains("at"))
//                rddArr = str.split("at");
//           
//           for(int i=0; i<rddArr.length; i++)
//           {
//               if(!rddArr[i].contains(".java") && !rddArr[i].contains("RDD"))
//                    autStr = autStr + "\n(" + i + ", " + rddArr[i].trim()  + ", " + i + ")";  
//               if(!rddArr[i].contains("RDD"))
//                   statenum++;
//           }
           
           header = "des (0, " + statenum + ", " + String.valueOf(statenum+1) + ")" ;
           //System.out.println(header + autStr);
           //writer = new BufferedWriter( new FileWriter(filepath+"sparkRDD.aut"));
           writer = new BufferedWriter( new FileWriter(filepath+args[0]+".aut"));
           writer.write(header + autStr);
           writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
