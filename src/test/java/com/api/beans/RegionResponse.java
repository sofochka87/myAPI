package com.api.beans;

import java.util.List;
import java.util.Map;

public class RegionResponse {

	private int region_id;
	private String region_name;
	private List<Map<String,String>> links;

	public void setRegion_id(int region_id) {
	this.region_id = region_id;
	}

	public void setRegion_name(String region_name) {
	this.region_name = region_name;
	}

	public int getRegion_id(){
	return region_id;
	}
	public String getRegion_name() {
	return region_name;
	}

	public List<Map<String, String>> getLinks() {
		return links;
	}

	public void setList(List<Map<String, String>> links) {
		this.links = links;
	}
	

}
