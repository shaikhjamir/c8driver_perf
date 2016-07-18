package com.c8_perf.sprng_data_1_4_2.model;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class EventPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKeyColumn(name = "eventId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private UUID eventId ;
	
	@PrimaryKeyColumn(name = "eventType", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
	private String eventType ;
	public UUID getEventId() {
		return eventId;
	}
	public void setEventId(UUID eventId) {
		this.eventId = eventId;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	@Override
	public String toString() {
		return "EventPK [eventId=" + eventId + ", eventType=" + eventType + "]";
	}
	
}
