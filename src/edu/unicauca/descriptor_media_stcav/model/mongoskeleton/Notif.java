package edu.unicauca.descriptor_media_stcav.model.mongoskeleton;

public class Notif {
	private String name;
	private String desc;
	private String type;
	
	
	public Notif() {
		super();
	}
	public Notif(String name, String desc, String type) {
		super();
		this.name = name;
		this.desc = desc;
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
