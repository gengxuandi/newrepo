package com.bean;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	private String teanname;
	private String name;
	private String info;
	public String getTeanname() {
		return teanname;
	}
	public void setTeanname(String teanname) {
		this.teanname = teanname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Override
	public String toString() {
		return "User [teanname=" + teanname + ", name=" + name + ", info=" + info + "]";
	}
	
	
}
