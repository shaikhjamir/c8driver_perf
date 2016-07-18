package com.c8_perf.common;

import java.util.List;
import java.util.UUID;

public abstract class ExecutePerf {

	public abstract String getDriverType() ;
	
	public abstract List<UUID> inserts(int count) ;
	
	public abstract boolean read(List<UUID> uuids) ;
	
	public abstract boolean update(List<UUID> uuids) ;
	
	public abstract boolean delete(List<UUID> uuids) ;
	
	public abstract boolean lists(List<UUID> uuids) ;
	
	public abstract void cleanup() ;
	
}
