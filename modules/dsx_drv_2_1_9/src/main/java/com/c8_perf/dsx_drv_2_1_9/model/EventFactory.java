package com.c8_perf.dsx_drv_2_1_9.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import com.c8_perf.common.EventTypes;

public class EventFactory {

	public static List<Event> getNewEvents() {
	
		ArrayList<Event> eventList = new ArrayList<>() ;
		
		UUID eventId = UUID.randomUUID() ;
		for (String eventType : EventTypes.TYPES) {
			eventList.add(getNewEvent(eventId, eventType)) ;
		}
		return eventList ;
	}
	
	public static Event getNewEvent(UUID eventId, String eventType) {
		
	  Event eve = new Event() ;
	  String eventIdStr = eventId.toString() ;
      eve.setEventId(eventId);
      eve.setEventType(eventType);
      eve.setEventTime(Calendar.getInstance().getTime()) ;
      eve.setEventData1(eventIdStr + "_data1");
      eve.setEventData2(eventIdStr + "_data2");
      eve.setEventData3(eventIdStr + "_data3");
      eve.setEventData4(eventIdStr + "_data4");
      eve.setEventData5(eventIdStr + "_data5");
      eve.setEventData6(eventIdStr + "_data6");
      eve.setEventData7(eventIdStr + "_data7");
      eve.setEventData8(eventIdStr + "_data8");
      eve.setEventData9(eventIdStr + "_data9");
      eve.setEventData10(eventIdStr + "_data10");
      eve.setEventData11(eventIdStr + "_data11");
      eve.setEventData12(eventIdStr + "_data12");
      eve.setEventData13(eventIdStr + "_data13");
      eve.setEventData14(eventIdStr + "_data14");
      eve.setEventData15(eventIdStr + "_data15");
      eve.setEventData16(eventIdStr + "_data16");
      eve.setEventData17(eventIdStr + "_data17");
      eve.setEventData18(eventIdStr + "_data18");
      eve.setEventData19(eventIdStr + "_data19");
      eve.setEventData20(eventIdStr + "_data20");
      return eve ;
	}
}
