package com.c8_perf.sprng_data_1_4_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.c8_perf.common.ExecuteTest;
import com.c8_perf.sprng_data_1_4_2.config.CassandraConfig;
import com.c8_perf.sprng_data_1_4_2.test.ExecutePerfImpl;

@ComponentScan(basePackages = { "com.c8_perf.sprng_data_1_4_2",  "com.c8_perf.sprng_data_1_4_2.test"})
@SpringBootApplication
@EnableAutoConfiguration
public class Application {

	private static AnnotationConfigApplicationContext ctx = null;

	public static void main(String[] args) {

		int noofThreads = 10;
		int noOfRecords = 1000;
		if (args.length == 2) {
			noOfRecords = Integer.parseInt(args[0]);
			noofThreads = Integer.parseInt(args[1]);
		}
		System.out.println("Going for " + noOfRecords + " with Threads:" + noofThreads);
		ctx = new AnnotationConfigApplicationContext(Application.class);
		ctx.register(CassandraConfig.class);
		
		ExecutePerfImpl perf = ctx.getBean(ExecutePerfImpl.class) ;
		ExecuteTest.runTests(noOfRecords, noofThreads, perf);
	}

	public static void close() {
		SpringApplication.exit(ctx);
	}
}
