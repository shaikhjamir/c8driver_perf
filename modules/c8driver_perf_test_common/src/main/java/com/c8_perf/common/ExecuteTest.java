package com.c8_perf.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecuteTest {

	private static SimpleDateFormat df = new SimpleDateFormat("yyyyy-mm-dd hh-mm-ss");
	
	private static String SP = ":::" ;
	
	/**
	 * This is used to output logs which can be used for analysis
	 * @param driver
	 * @param operation
	 * @param totalRecordsExpectedCount
	 * @param noOfThreads
	 * @param timeTaken
	 */
	private static void log(String driver,
			String operation,
			long totalRecordsExpectedCount,
			int noOfThreads,
			long timeTaken
			) {
		System.out.println(df.format(new Date()) + SP + "__DATA__"+ SP + driver + SP + operation + SP + totalRecordsExpectedCount + SP + noOfThreads + SP + timeTaken);
	}
	
	
	public static void runTests(int size, int noOfThreads, ExecutePerf executor) {
		int perThread = size/( noOfThreads ) ;
		
		// Note here we are diving by noOfThreads so mod will be ignored
		int totalRecordsExpectedCount = perThread * noOfThreads  ;
		ArrayList<ExecuteInsert> taskList = new ArrayList<>() ;
		for (int i = 0 ; i < noOfThreads ; i ++ ) {
			taskList.add(new ExecuteInsert(executor, perThread)) ;
		}

		ExecutorService executorSer = Executors.newFixedThreadPool(noOfThreads);
		try {
			ArrayList<UUID> uuids = new ArrayList<>() ;
			long startInsert = System.currentTimeMillis() ;
			// This will return only when all tasks are done
			List<Future<List<UUID>>> futures = null ;
			try {
				futures = executorSer.invokeAll(taskList, 20, TimeUnit.MINUTES) ;
				for (Future<List<UUID>> future : futures) {
					if (future.isCancelled()) {
						System.err.println("Unable to finish the INSERT properly...");
						return ;
					}
					
					uuids.addAll(future.get()) ;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Unable to finish the INSERT properly...");
			}
			
			if (totalRecordsExpectedCount != uuids.size()) {
				
				System.err.println("Unable to finish the INSERT properly...");
				return ;
			}
			
			long endInsert = System.currentTimeMillis() ;
			long totalInsertTime = endInsert - startInsert ;
			System.out.println("Total time for insert:" + totalInsertTime + " for records: " + (totalRecordsExpectedCount) + " with Threads:" + noOfThreads);
			log(executor.getDriverType(), "INSERT", totalRecordsExpectedCount, noOfThreads, totalInsertTime) ;
			
			long startRead = System.currentTimeMillis() ;
			try {
				ArrayList<ExecuteRead> taskReadList = new ArrayList<>() ;
				for (int i = 0 ; i < noOfThreads ; i ++ ) {
					taskReadList.add(new ExecuteRead(executor, futures.get(i).get())) ;
				}
				List<Future<Boolean>> futuresRead = executorSer.invokeAll(taskReadList, 20, TimeUnit.MINUTES) ;
				for (Future<Boolean> future : futuresRead) {
					if (future.isCancelled()) {
						System.err.println("Unable to finish the READ properly...");
						return ;
					}
					
					if (future.get() == false ) {
						System.err.println("Unable to finish the READ properly...");
						return ;
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Unable to finish the READ properly...");
			}
			long endRead = System.currentTimeMillis() ;
			long totalReadTime = endRead - startRead ;
			System.out.println("Total time for read:" + totalReadTime + " for records: " + (totalRecordsExpectedCount) + " with Threads:" + noOfThreads);
			log(executor.getDriverType(), "READ", totalRecordsExpectedCount, noOfThreads, totalReadTime) ;
			
			long startUpdate = System.currentTimeMillis() ;
			try {
				ArrayList<ExecuteUpdate> taskUpdateList = new ArrayList<>() ;
				for (int i = 0 ; i < noOfThreads ; i ++ ) {
					taskUpdateList.add(new ExecuteUpdate(executor, futures.get(i).get())) ;
				}
				List<Future<Boolean>> futuresUpdate = executorSer.invokeAll(taskUpdateList, 20, TimeUnit.MINUTES) ;
				for (Future<Boolean> future : futuresUpdate) {
					if (future.isCancelled()) {
						System.err.println("Unable to finish the UPDATE properly-1");
						return ;
					}
					
					if (future.get() == false ) {
						System.err.println("Unable to finish the UPDATE properly-2");
						return ;
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Unable to finish the UPDATE properly-3");
			}
			long endUpdate = System.currentTimeMillis() ;
			long totalUpdate = endUpdate - startUpdate ;
			System.out.println("Total time for update:" + totalUpdate + " for records: " + (totalRecordsExpectedCount) + " with Threads:" + noOfThreads);
			log(executor.getDriverType(), "UPDATE", totalRecordsExpectedCount, noOfThreads, totalUpdate) ;
			
			
			long startDelete = System.currentTimeMillis() ;
			try {
				ArrayList<ExecuteDelete> taskDeleteList = new ArrayList<>() ;
				for (int i = 0 ; i < noOfThreads ; i ++ ) {
					taskDeleteList.add(new ExecuteDelete(executor, futures.get(i).get())) ;
				}
				List<Future<Boolean>> futuresDelete = executorSer.invokeAll(taskDeleteList, 20, TimeUnit.MINUTES) ;
				for (Future<Boolean> future : futuresDelete) {
					if (future.isCancelled()) {
						System.err.println("Unable to finish the DELETE properly...");
						return ;
					}
					
					if (future.get() == false ) {
						System.err.println("Unable to finish the DELETE properly...");
						return ;
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Unable to finish the DELETE properly...");
			}
			long endDelete = System.currentTimeMillis() ;
			long totalDelete = endDelete - startDelete ;
			System.out.println("Total time for Delete:" + totalDelete + " for records: " + (totalRecordsExpectedCount) + " with Threads:" + noOfThreads);
			
			log(executor.getDriverType(), "DELETE", totalRecordsExpectedCount, noOfThreads, totalDelete) ;
		} finally {
		
			executor.cleanup();
			executorSer.shutdown();
		}
	}
	
	static class ExecuteInsert implements Callable<List<UUID>> {
		
		private ExecutePerf executor ;
		private int count ;
		public ExecuteInsert(ExecutePerf executor, int count) {
			this.executor = executor ;
			this.count = count ;
		}
		
		@Override
		public List<UUID> call() throws Exception {
			
			List<UUID> uuids = executor.inserts(count) ;
			return uuids;
		}
	}
	
	static class ExecuteRead implements Callable<Boolean> {
		
		private ExecutePerf executor ;
		private List<UUID> uuidList ; 
		
		public ExecuteRead(ExecutePerf executor, List<UUID> uuidList) {
			this.executor = executor ;
			this.uuidList = uuidList ;
		}
		
		@Override
		public Boolean call() throws Exception {
			return executor.read(uuidList) ;
		}
	}
	
	static class ExecuteUpdate implements Callable<Boolean> {
		
		private ExecutePerf executor ;
		private List<UUID> uuidList ; 
		
		public ExecuteUpdate(ExecutePerf executor, List<UUID> uuidList) {
			this.executor = executor ;
			this.uuidList = uuidList ;
		}
		
		@Override
		public Boolean call() throws Exception {
			return executor.update(uuidList) ;
		}
	}

	static class ExecuteDelete implements Callable<Boolean> {
		
		private ExecutePerf executor ;
		private List<UUID> uuidList ; 
		
		public ExecuteDelete(ExecutePerf executor, List<UUID> uuidList) {
			this.executor = executor ;
			this.uuidList = uuidList ;
		}
		
		@Override
		public Boolean call() throws Exception {
			return executor.delete(uuidList) ;
		}
	}
}
