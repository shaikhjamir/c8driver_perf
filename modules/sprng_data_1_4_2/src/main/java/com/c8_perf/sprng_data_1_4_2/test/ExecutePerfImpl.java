package com.c8_perf.sprng_data_1_4_2.test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.c8_perf.common.ExecutePerf;
import com.c8_perf.sprng_data_1_4_2.config.CassandraConfig;
import com.c8_perf.sprng_data_1_4_2.model.Experiment;
import com.c8_perf.sprng_data_1_4_2.model.ExperimentFactory;
import com.c8_perf.sprng_data_1_4_2.repo.EventRepo;
import com.c8_perf.sprng_data_1_4_2.repo.ExperimentRepo;


@Service
public class ExecutePerfImpl extends ExecutePerf {

	@Autowired
	EventRepo repo ;
	
	@Autowired
	ExperimentRepo repoEx ;
	
	@Override
	public String getDriverType() {
		return "Spring_Data_1_4_2";
	}

	@Override
	public List<UUID> inserts(int count) {
		 
		/*List<UUID> uuids = new ArrayList<>() ;
		for (int i = 0 ; i < count ; i++) {
			List<Event> events = EventFactory.getNewEvents() ;
			for (Event eve : events) {
				repo.save(eve);
			}
			uuids.add(events.get(0).getPk().getEventId()) ;
		}
		return uuids;
		*/
		
		List<UUID> uuids = new ArrayList<>() ;
		for (int i = 0 ; i < count ; i++) {
			Experiment ex = ExperimentFactory.getExperiment() ;
			repoEx.save(ex);
			uuids.add(ex.getId()) ;
		}
		return uuids;
	}

	@Override
	public boolean read(List<UUID> uuids) {
		
		/*for (UUID uuid : uuids) {
			
			for (String eventType : EventTypes.TYPES) {
				Event evt = repo.findEvent(uuid, eventType) ;
				if (evt == null || evt.getEventData1().equals(uuid.toString() + "_data1") == false )
					return false ;
			}
		}
		return true;
		*/
		
		for (UUID uuid : uuids) {
			
			Experiment evt = repoEx.findExperiment(uuid) ;
			if (evt == null || evt.getApplicationName() == null || evt.getApplicationName().length() == 0 )
				return false ;
		}
		return true;
	}

	@Override
	public boolean update(List<UUID> uuids) {
		
		/*for (UUID uuid : uuids) {
			
			for (String eventType : EventTypes.TYPES) {
				Event evt = repo.findEvent(uuid, eventType) ;
				if (evt == null || evt.getEventData1().equals(uuid.toString() + "_data1") == false )
					return false ;
				
				evt.setEventData20(evt.getEventData20() + "--updated");
				repo.save(evt);
			}
		}*/
		return true;
	}

	@Override
	public boolean delete(List<UUID> uuids) {
		/*for (UUID uuid : uuids) {
			
			for (String eventType : EventTypes.TYPES) {
				EventPK evtPK = new EventPK() ;
				evtPK.setEventId(uuid);
				evtPK.setEventType(eventType);
				Event eve = new Event() ;
				eve.setPk(evtPK) ;
				repo.delete(eve);
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
		CassandraConfig.close();
	}

}
