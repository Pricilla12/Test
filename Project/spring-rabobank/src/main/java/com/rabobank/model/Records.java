package com.rabobank.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Records {
	
	public Records() {
	}

	public Records(List<RequestParameters> record) {
		super();
		this.record = record;
	}

	private List<RequestParameters> record;

	@XmlElement(name="record")
	public List<RequestParameters> getRecord() {
		return record;
	}

	public void setRecord(List<RequestParameters> record) {
		this.record = record;
	}


}
