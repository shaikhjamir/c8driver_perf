package com.c8_perf.sprng_data_1_4_2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.convert.CassandraConverter;
import org.springframework.data.cassandra.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.c8_perf.sprng_data_1_4_2.Application;
 
 
@Configuration
@PropertySource(value = {"classpath:META-INF/cassandra.properties"})
@EnableCassandraRepositories(basePackages = {"com.c8_perf.sprng_data_1_4_2.repo", "com.c8_perf.sprng_data_1_4_2.test"})
public class CassandraConfig {
 
	private static CassandraSessionFactoryBean session = null ;
	private static CassandraClusterFactoryBean cluster = null ;
	
    @Autowired
    private Environment environment;
 
    @Bean
    public CassandraClusterFactoryBean cluster() {
 
        cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints(environment.getProperty("cassandra.contactpoints"));
        cluster.setPort(Integer.parseInt(environment.getProperty("cassandra.port")));
 
        return cluster;
    }
 
    @Bean
    public CassandraMappingContext mappingContext() {
        return new BasicCassandraMappingContext();
    }
 
    @Bean
    public CassandraConverter converter() {
        return new MappingCassandraConverter(mappingContext());
    }
 
    @Bean
    public CassandraSessionFactoryBean session() throws Exception {
 
        session = new CassandraSessionFactoryBean();
        session.setCluster(cluster().getObject());
        session.setKeyspaceName(environment.getProperty("cassandra.keyspace"));
        session.setConverter(converter());
        session.setSchemaAction(SchemaAction.NONE);
        return session;
    }
 
    
    public static void close() {
    
    	try {
    		session.getObject().close();
    		session.destroy();
    		cluster.destroy();
    		Application.close(); 

		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @Bean
    public CassandraOperations cassandraTemplate() throws Exception {
        return new CassandraTemplate(session().getObject());
    }
}
