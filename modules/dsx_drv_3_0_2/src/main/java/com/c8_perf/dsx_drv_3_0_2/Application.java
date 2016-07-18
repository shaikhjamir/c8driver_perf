package com.c8_perf.dsx_drv_3_0_2;

import com.c8_perf.common.ExecuteTest;
import com.c8_perf.dsx_drv_3_0_2.test.ExecutePerfImpl;

public class Application {
   
	public static void main(String[] args) {
	   ExecutePerfImpl perf = new ExecutePerfImpl() ;
	   
	   int noofThreads = 10 ;
	   int noOfRecords = 1000 ;
	   if (args.length == 2 ) {
		   noOfRecords = Integer.parseInt(args[0]) ;
		   noofThreads = Integer.parseInt(args[1]) ;
	   }
	   System.out.println("Going for " + noOfRecords + " with Threads:" + noofThreads );
	   ExecuteTest.runTests(noOfRecords, noofThreads, perf);
   }
}

