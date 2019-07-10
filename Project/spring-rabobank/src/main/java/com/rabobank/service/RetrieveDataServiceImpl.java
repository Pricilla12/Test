package com.rabobank.service;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.rabobank.model.Records;
import com.rabobank.model.RequestParameters;


@Component
public class RetrieveDataServiceImpl implements RetrieveDataService{

	
	/**
	 * @return List<RequestParameters>
	 */
	public List<RequestParameters> extractStatmentFromCSV(File file) throws Exception {
		HeaderColumnNameTranslateMappingStrategy<RequestParameters> beanStrategy = new HeaderColumnNameTranslateMappingStrategy<RequestParameters>();
		beanStrategy.setType(RequestParameters.class);

		Map<String, String> columnMapping = new HashMap<String, String>();
		columnMapping.put("Reference", "transactionRef");
		columnMapping.put("Account Number", "accountNumber");
		columnMapping.put("Description", "description");
		columnMapping.put("Start Balance", "startBalance");
		columnMapping.put("Mutation", "mutation");
		columnMapping.put("End Balance", "endBalance");

		beanStrategy.setColumnMapping(columnMapping);

		CsvToBean<RequestParameters> csvToBean = new CsvToBean<RequestParameters>();
		CSVReader reader = new CSVReader(new FileReader(file));
		
		
		@SuppressWarnings("deprecation")
		List<RequestParameters> records = csvToBean.parse(beanStrategy, reader);
		return records;
	}

	/**
	 * @return List<RequestParameters>
	 */
	public List<RequestParameters> extractStatmentFromXML(File file) throws Exception {
		
       
		
			JAXBContext jaxbContext = JAXBContext.newInstance(Records.class);  
   
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
			Records rootRecord = (Records) jaxbUnmarshaller.unmarshal(file);
		

		return rootRecord.getRecord();
	}
}
