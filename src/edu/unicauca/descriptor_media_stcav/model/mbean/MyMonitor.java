package edu.unicauca.descriptor_media_stcav.model.mbean;

import javax.management.monitor.Monitor;

public interface MyMonitor {
	public String getName();
	public void setName(String name);
	public Monitor getMonitor();
}
