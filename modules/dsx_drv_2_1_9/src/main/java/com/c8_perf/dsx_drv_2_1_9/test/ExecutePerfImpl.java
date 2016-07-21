package com.c8_perf.dsx_drv_2_1_9.test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.c8_perf.common.ExecutePerf;
import com.c8_perf.dsx_drv_2_1_9.config.CassandraConfig;
import com.c8_perf.dsx_drv_2_1_9.model.Experiment;
import com.c8_perf.dsx_drv_2_1_9.model.ExperimentFactory;
import com.datastax.driver.mapping.Mapper;

public class ExecutePerfImpl extends ExecutePerf {

	@Override
	public String getDriverType() {
		return "DSX_3_0_2";
	}

	@Override
	public List<UUID> inserts(int count) {
		
		/*Mapper<Event> mapper = CassandraConfig.getMapper() ; 
		List<UUID> uuids = new ArrayList<>() ;
		for (int i = 0 ; i < count ; i++) {
			List<Event> events = EventFactory.getNewEvents() ;
			for (Event eve : events) {
				mapper.save(eve);
			}
			uuids.add(events.get(0).getEventId()) ;
		}*/
		
		Mapper<Experiment> mapper = CassandraConfig.getExperimentMapper() ;
		List<UUID> uuids = new ArrayList<>() ;
		for (int i = 0 ; i < count ; i++) {
			Experiment ex = ExperimentFactory.getExperiment() ;
			mapper.save(ex);
			uuids.add(ex.getId()) ;
		}
		return uuids;
	}
	

	@Override
	public boolean read(List<UUID> uuids) {
		
		/*Mapper<Event> mapper = CassandraConfig.getMapper() ;
		for (UUID uuid : uuids) {
			
			for (String eventType : EventTypes.TYPES) {
				Event evt = mapper.get(uuid, eventType) ;
				if (evt == null || evt.getEventData1().equals(uuid.toString() + "_data1") == false )
					return false ;
			}
		}*/
		
		Mapper<Experiment> mapper = CassandraConfig.getExperimentMapper() ;
		for (UUID uuid : uuids) {
		
			Experiment expr = mapper.get(uuid) ;
			if (expr == null || expr.getApplicationName() == null )
				return false ;
		}
		return true;
	}

	@Override
	public boolean update(List<UUID> uuids) {
		
		/*Mapper<Event> mapper = CassandraConfig.getMapper() ;
		for (UUID uuid : uuids) {
			
			for (String eventType : EventTypes.TYPES) {
				Event evt = mapper.get(uuid, eventType) ;
				if (evt == null || evt.getEventData1().equals(uuid.toString() + "_data1") == false )
					return false ;
				
				evt.setEventData20(evt.getEventData20() + "--updated");
				mapper.save(evt);
			}
		}*/
		return true;
	}

	@Override
	public boolean delete(List<UUID> uuids) {
		/*Mapper<Event> mapper = CassandraConfig.getMapper() ;
		for (UUID uuid : uuids) {
			
			for (String eventType : EventTypes.TYPES) {
				Event evt = new Event() ;
				evt.setEventId(uuid);
				evt.setEventType(eventType);
				mapper.delete(evt);
			}
		}*/
		return true;
	}

	@Override
	public boolean lists(List<UUID> uuids) {
		return false;
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		CassandraConfig.client.close();
	}

}
