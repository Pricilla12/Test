package com.rabobank.service;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rabobank.model.RequestParameters;

public interface RetrieveDataService {
	
	public List<RequestParameters> extractStatmentFromCSV(File csvFile) throws Exception;
	
	public List<RequestParameters> extractStatmentFromXML(File file) throws Exception;
}
