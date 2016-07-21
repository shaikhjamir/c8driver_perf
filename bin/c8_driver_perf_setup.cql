drop KEYSPACE  IF EXISTS c8_perf ;

CREATE KEYSPACE c8_perf
  WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' :
 1 };

use c8_perf ;

drop table IF EXISTS experiment;

CREATE TABLE experiment (
  id uuid,
  ApplicationName text,
  CreationTime timestamp,
  CreatorID text,
  Description text,
  EndTime timestamp,
  IsPersonalizationEnabled boolean,
  IsRapidExperiment boolean,
  label text,
  ModelName text,
  ModelVersion text,
  ModificationTime timestamp,
  rule text,
  RuleJson text,
  SamplingPercent double,
  startTime timestamp,
  state text,
  userCap int,
  PRIMARY KEY ((id))
) ;

drop table IF EXISTS event ;

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
