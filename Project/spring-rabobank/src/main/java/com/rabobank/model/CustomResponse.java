package com.rabobank.model;

import java.util.List;

public class CustomResponse {
	private String responseMessage;
	private int responseCode;
	private List<RequestParameters> Records;

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	@Override
	public String toString() {
		return "Error [responseMessage=" + responseMessage + ", responseCode=" + responseCode + "]";
	}

	public List<RequestParameters> getRecords() {
		return Records;
	}

	public void setRecords(List<RequestParameters> records) {
		Records = records;
	}
}
