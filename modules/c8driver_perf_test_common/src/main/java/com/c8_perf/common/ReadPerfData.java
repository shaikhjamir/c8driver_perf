package com.c8_perf.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ReadPerfData {

	private static String SEP = ":::" ;
	
	// 02016-13-17 12-13-20:::__DATA__:::DSX_3_0_2:::INSERT       :::1000:::10:::2747
	// DATE 			   ::: TAG    ::: Driver  ::: Operation   ::: NoOfRecords ::: Thread ::: Time taken
	
	private static String[] keys = new String[] { 
			"10000",
			"20000",
			"30000",
			"40000",
			"50000",
			"60000",
			"70000",
			"80000",
			"90000",
			"100000",
			"110000",
			"120000",
			"130000",
			"140000",
			"150000",
			"160000",
			"170000",
			"180000",
			"190000",
			"200000"
			} ;
	public static void main(String[] args) {
	
		
		TreeMap<String, TreeMap<String, String>> recordsMap = new TreeMap<>() ;
		TreeMap<String, String> cumulutiveMap = new TreeMap<>() ;
		
		for(String key: keys){
		
			System.out.println(key);
		}
		
		String filePath = "/Users/jshaikh/Documents/repo/c8_performance_runs.dat.bk" ;
		File f = new File(filePath);
		FileReader fr;
		try {
			fr = new FileReader(f);
			BufferedReader reader = new BufferedReader(fr);
			String line = reader.readLine();
			while ( line != null ) {
			    
			    String[] arr = line.split(SEP) ;
			    
			    // Driver+Operation
			    String firstKey = arr[2] + "_" + arr[3];
			    TreeMap<String, String> innerMap = recordsMap.get(firstKey) ;
			    if (innerMap == null ){
			    	innerMap = new TreeMap<>() ;
			    	recordsMap.put(firstKey, innerMap) ;
			    }
			    
			    innerMap.put(arr[4], arr[6]) ;
			    
			    String cumulitiveKey = firstKey + "_" + arr[4] ;
			    cumulutiveMap.put(cumulitiveKey, arr[6]) ;
			    
			    averageDataInsert(arr) ;
			    
			    line = reader.readLine();
			}
			
			averageOutData() ;
			System.out.println("INSERT:::::");
			printResults("Spring_Data_1_4_2", "INSERT") ;
			printResults("DSX_3_0_2", "INSERT") ;
			getPercentDiff(allDataAveraged, "INSERT") ;
			
			System.out.println("READ:::::");
			printResults("Spring_Data_1_4_2", "READ") ;
			printResults("DSX_3_0_2", "READ") ;
			getPercentDiff(allDataAveraged, "READ") ;
			
			System.out.println("UPDATE:::::");
			printResults("Spring_Data_1_4_2", "UPDATE") ;
			printResults("DSX_3_0_2", "UPDATE") ;
			getPercentDiff(allDataAveraged, "UPDATE") ;
			
			System.out.println("DELETE:::::");
			printResults("Spring_Data_1_4_2", "DELETE") ;
			printResults("DSX_3_0_2", "DELETE") ;
			getPercentDiff(allDataAveraged, "DELETE") ;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void printResults(String driver, String operation) {
		// String key = obj.driver + obj.operation + obj.noOfRecords + "_" + obj.threads ;
		System.out.println(driver+":" + operation);
		for(String key: keys){
			
			String keyToFind = driver + operation + key + "_10" ; 
			System.out.println(allDataAveraged.get(keyToFind) );
		}
	}
	
	private static TreeMap<String, List<Data>> alldata = new TreeMap<>() ;
	private static TreeMap<String, Long> allDataAveraged = new TreeMap<>() ;
	
	public static String getAverageDataKey(Data obj) {
		String key = obj.driver + obj.operation + obj.noOfRecords + "_" + obj.threads ;
		return key ;
	}
	
	public static void averageDataInsert(String[] data) {
		Data obj = new Data() ;
		obj.setValues(data);
		String key = getAverageDataKey(obj) ;
		List<Data> dataList = alldata.get(key) ;
		if (dataList == null )
			dataList = new ArrayList<>() ;
		dataList.add(obj) ;
		alldata.put( key, dataList);
	}
	
	public static void averageOutData() {
		
		for (Map.Entry<String, List<Data>> v : alldata.entrySet()) {
			List<Data> dtValList = v.getValue() ;
			
			long[] dataArr = new long[dtValList.size()] ;
			
			int i = 0 ; 
			for (Data data : dtValList) {
				dataArr[i++] = Long.parseLong(data.timeTaken) ;
			}
			
			long median = median(dataArr) ;
			if (median == 0l )
				System.err.println("Median cannot be zero....") ;
			//System.out.println(v.getKey()+"=" + median);
			allDataAveraged.put(v.getKey(), median) ;
		}
	}
	

	public static long median(long[] data) {
		
		Arrays.sort(data);
	    int middle = data.length/2;
	    if (data.length%2 == 1) {
	        return data[middle];
	    } else {
	        return (data[middle-1] + data[middle]) / 2;
	    }
	}
	
	
	static class Data {
		
		// DATE 			   ::: TAG    ::: Driver  ::: Operation   ::: NoOfRecords ::: Thread ::: Time taken
		public String date ;
		
		public String tag ;
		public String driver ;
		public String operation ;
		public String noOfRecords ;
		public String threads ;
		public String timeTaken ;
		
		public void setValues(String[] args) {
			date = args[0] ;
			tag = args[1] ;
			driver = args[2] ;
			operation = args[3] ;
			noOfRecords = args[4] ;
			threads = args[5] ;
			timeTaken = args[6] ;
		}
		
		@Override
		public String toString() {
			return "Data [date=" + date + ", tag=" + tag + ", driver=" + driver + ", operation=" + operation
					+ ", noOfRecords=" + noOfRecords + ", threads=" + threads + ", timeTaken=" + timeTaken + "]";
		}

	}
	
	private static void getPercentDiff(TreeMap<String, Long> cumulutiveMap, String operation) {
		
		System.out.println( operation + "=getPercentDiff");
		for (String key : keys) {
			
			// String key = obj.driver + obj.operation + obj.noOfRecords + "_" + obj.threads ;
			Long dsxVal = cumulutiveMap.get("DSX_3_0_2" + operation + key + "_10") ;
			Long sdVal = cumulutiveMap.get("Spring_Data_1_4_2" + operation + key + "_10") ;
			
			if (dsxVal == null || sdVal == null )
				continue ;
			
			Long dsx = dsxVal ;
			Long sd = sdVal ;
			
			Long neg = 1l ;
			Long base = 0l ;
			Long c2 = 0l ;
			if (sd >=dsx ) {
				base = dsx ;
				c2 = sd ;
			} else {
				
				base = sd  ;
				c2 = dsx ;
				neg = -1l ;
			}
			
			long diff = c2 - base ;
			double div = ( (double)diff / base) ;
			double percentChange = div * 100 * neg ; 
			System.out.println( percentChange );
		}
	}
}
