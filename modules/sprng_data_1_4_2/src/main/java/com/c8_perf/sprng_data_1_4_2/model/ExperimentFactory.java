package com.c8_perf.sprng_data_1_4_2.model;

import java.util.Date;
import java.util.UUID;

public class ExperimentFactory {

	public static Experiment getExperiment() {
	
		Experiment expr = new Experiment() ;
		UUID eventId = UUID.randomUUID() ;
		expr.setId(eventId);
		
		expr.setApplicationName("App-" + System.currentTimeMillis() );
		expr.setCreationTime(new Date());
		expr.setCreatorID("TEst");
		expr.setDescription("Tets descripion...");
		expr.setEndTime(new Date());
		expr.setIsPersonalizationEnabled(false);
		expr.setIsRapidExperiment(false);
		expr.setLabel("Nice label..");
		expr.setModelName("Tets Model");
		expr.setModelVersion("v2.2");
		expr.setModificationTime(new Date());
		expr.setRule("TEst rule..");
		expr.setRuleJson("JSOOOOOON RUle");
		expr.setSamplingPercent(2.0d);
		expr.setStartTime(new Date());
		expr.setState("RUNNING");
		expr.setUserCap(8);
		return expr ;
	}
}
