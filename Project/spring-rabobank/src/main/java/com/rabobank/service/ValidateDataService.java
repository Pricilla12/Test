package com.rabobank.service;

import java.io.File;
import java.util.List;

import com.rabobank.model.RequestParameters;

public interface ValidateDataService {

	public List<RequestParameters> getDuplicateRecords(List<RequestParameters> records);
	
	public List<RequestParameters> getEndBalanceErrorRecords(List<RequestParameters> records);
}
