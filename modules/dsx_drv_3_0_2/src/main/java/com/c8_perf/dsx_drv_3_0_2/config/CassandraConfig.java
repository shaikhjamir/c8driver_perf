package com.c8_perf.dsx_drv_3_0_2.config;

import com.c8_perf.dsx_drv_3_0_2.model.Event;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;

public class CassandraConfig {

	private Cluster cluster;
	   private Session session;

	   public Session getSession() {
	      return this.session;
	   }

	   public void connect(String node) {
	      cluster = Cluster.builder()
	            .addContactPoint(node)
	            .build();
	      Metadata metadata = cluster.getMetadata();
	      System.out.printf("Connected to cluster: %s\n", 
	            metadata.getClusterName());
	      for ( Host host : metadata.getAllHosts() ) {
	         System.out.printf("Datatacenter: %s; Host: %s; Rack: %s\n",
	               host.getDatacenter(), host.getAddress(), host.getRack());
	      }
	      session = cluster.connect("c8_perf");
	   }

	  
	   public void close() {
	      session.close();
	      cluster.close();
	   }

	   
	   public static CassandraConfig client = null ;
	   private static Mapper<Event> mapper = null ;
	   public static synchronized Mapper<Event> getMapper() {
		  
		  if (mapper != null )
			  return mapper ;
		  client = new CassandraConfig();
	      client.connect("127.0.0.1");
	      
	      MappingManager manager = new MappingManager(client.getSession());
	      mapper =  manager.mapper(Event.class);
	      return mapper ;
	   }
}
