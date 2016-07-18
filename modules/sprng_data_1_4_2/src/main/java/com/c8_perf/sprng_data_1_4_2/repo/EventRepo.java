package com.c8_perf.sprng_data_1_4_2.repo;


import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.c8_perf.sprng_data_1_4_2.model.Event;

public interface EventRepo extends CassandraRepository<Event> {
	
	@Query("SELECT * FROM event WHERE eventId=?0 and eventType=?1")
    Event findEvent(UUID eventId,String eventType);
}
