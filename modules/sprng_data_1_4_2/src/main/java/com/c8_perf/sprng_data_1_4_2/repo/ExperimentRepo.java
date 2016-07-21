package com.c8_perf.sprng_data_1_4_2.repo;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.c8_perf.sprng_data_1_4_2.model.Experiment;

public interface ExperimentRepo extends CassandraRepository<Experiment> {

	@Query("SELECT * FROM experiment WHERE id=?0 ")
	Experiment findExperiment(UUID eventId);

}
