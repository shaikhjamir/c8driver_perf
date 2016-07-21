drop KEYSPACE c8_perf IF EXISTS ;

CREATE KEYSPACE c8_perf
  WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' :
 1 };

use c8_perf ;

drop table experiment IF EXISTS ;

CREATE TABLE experiment (
  id uuid,
  app_name text,
  created timestamp,
  creatorid text,
  description text,
  end_time timestamp,
  is_personalized boolean,
  is_rapid_experiment boolean,
  label text,
  model_name text,
  model_version text,
  modified timestamp,
  rule text,
  sample_percent double,
  start_time timestamp,
  state text,
  user_cap int,
  PRIMARY KEY ((id))
) ;

drop table event  IF EXISTS ; 

CREATE TABLE event (
eventId uuid,
eventType text,
eventTime timestamp,
eventDesc text,
eventData1 text,
eventData2 text,
eventData3 text,
eventData4 text,
eventData5 text,
eventData6 text,
eventData7 text,
eventData8 text,
eventData9 text,
eventData10 text,
eventData11 text,
eventData12 text,
eventData13 text,
eventData14 text,
eventData15 text,
eventData16 text,
eventData17 text,
eventData18 text,
eventData19 text,
eventData20 text,
PRIMARY KEY (eventId,eventType)
);
