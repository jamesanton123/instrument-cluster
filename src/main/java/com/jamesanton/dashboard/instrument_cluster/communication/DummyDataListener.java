package com.jamesanton.dashboard.instrument_cluster.communication;

public interface DummyDataListener extends Runnable{
	public void pumpData() throws Exception;
}
