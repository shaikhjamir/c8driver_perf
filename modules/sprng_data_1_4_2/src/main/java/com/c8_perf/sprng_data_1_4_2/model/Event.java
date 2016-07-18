package com.c8_perf.sprng_data_1_4_2.model;

import java.util.Date;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

 
@Table(value = "Event")
public class Event {

	@PrimaryKey
	private EventPK pk ; 
	public EventPK getPk() {
		return pk;
	}
	public void setPk(EventPK pk) {
		this.pk = pk;
	}
	private Date eventTime ;
	private String eventDesc ;
	private String eventData1 ;
	private String eventData2 ;
	private String eventData3 ;
	private String eventData4 ;
	private String eventData5 ;
	private String eventData6 ;
	private String eventData7 ;
	private String eventData8 ;
	private String eventData9 ;
	private String eventData10 ;
	private String eventData11 ;
	private String eventData12 ;
	private String eventData13 ;
	private String eventData14 ;
	private String eventData15 ;
	private String eventData16 ;
	private String eventData17 ;
	private String eventData18 ;
	private String eventData19 ;
	private String eventData20 ;
	
	
	public Date getEventTime() {
		return eventTime;
	}
	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}
	public String getEventDesc() {
		return eventDesc;
	}
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
	public String getEventData1() {
		return eventData1;
	}
	public void setEventData1(String eventData1) {
		this.eventData1 = eventData1;
	}
	public String getEventData2() {
		return eventData2;
	}
	public void setEventData2(String eventData2) {
		this.eventData2 = eventData2;
	}
	public String getEventData3() {
		return eventData3;
	}
	public void setEventData3(String eventData3) {
		this.eventData3 = eventData3;
	}
	public String getEventData4() {
		return eventData4;
	}
	public void setEventData4(String eventData4) {
		this.eventData4 = eventData4;
	}
	public String getEventData5() {
		return eventData5;
	}
	public void setEventData5(String eventData5) {
		this.eventData5 = eventData5;
	}
	public String getEventData6() {
		return eventData6;
	}
	public void setEventData6(String eventData6) {
		this.eventData6 = eventData6;
	}
	public String getEventData7() {
		return eventData7;
	}
	public void setEventData7(String eventData7) {
		this.eventData7 = eventData7;
	}
	public String getEventData8() {
		return eventData8;
	}
	public void setEventData8(String eventData8) {
		this.eventData8 = eventData8;
	}
	public String getEventData9() {
		return eventData9;
	}
	public void setEventData9(String eventData9) {
		this.eventData9 = eventData9;
	}
	public String getEventData10() {
		return eventData10;
	}
	public void setEventData10(String eventData10) {
		this.eventData10 = eventData10;
	}
	public String getEventData11() {
		return eventData11;
	}
	public void setEventData11(String eventData11) {
		this.eventData11 = eventData11;
	}
	public String getEventData12() {
		return eventData12;
	}
	public void setEventData12(String eventData12) {
		this.eventData12 = eventData12;
	}
	public String getEventData13() {
		return eventData13;
	}
	public void setEventData13(String eventData13) {
		this.eventData13 = eventData13;
	}
	public String getEventData14() {
		return eventData14;
	}
	public void setEventData14(String eventData14) {
		this.eventData14 = eventData14;
	}
	public String getEventData15() {
		return eventData15;
	}
	public void setEventData15(String eventData15) {
		this.eventData15 = eventData15;
	}
	public String getEventData16() {
		return eventData16;
	}
	public void setEventData16(String eventData16) {
		this.eventData16 = eventData16;
	}
	public String getEventData17() {
		return eventData17;
	}
	public void setEventData17(String eventData17) {
		this.eventData17 = eventData17;
	}
	public String getEventData18() {
		return eventData18;
	}
	public void setEventData18(String eventData18) {
		this.eventData18 = eventData18;
	}
	public String getEventData19() {
		return eventData19;
	}
	public void setEventData19(String eventData19) {
		this.eventData19 = eventData19;
	}
	public String getEventData20() {
		return eventData20;
	}
	public void setEventData20(String eventData20) {
		this.eventData20 = eventData20;
	}
	@Override
	public String toString() {
		return "Event [pk=" + pk + ", eventTime=" + eventTime + ", eventDesc=" + eventDesc + ", eventData1="
				+ eventData1 + ", eventData2=" + eventData2 + ", eventData3=" + eventData3 + ", eventData4="
				+ eventData4 + ", eventData5=" + eventData5 + ", eventData6=" + eventData6 + ", eventData7="
				+ eventData7 + ", eventData8=" + eventData8 + ", eventData9=" + eventData9 + ", eventData10="
				+ eventData10 + ", eventData11=" + eventData11 + ", eventData12=" + eventData12 + ", eventData13="
				+ eventData13 + ", eventData14=" + eventData14 + ", eventData15=" + eventData15 + ", eventData16="
				+ eventData16 + ", eventData17=" + eventData17 + ", eventData18=" + eventData18 + ", eventData19="
				+ eventData19 + ", eventData20=" + eventData20 + "]";
	}

	
}
