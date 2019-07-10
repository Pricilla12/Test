package com.rabobank.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.constant.Constants;
import com.rabobank.model.CustomResponse;
import com.rabobank.model.RequestParameters;

import com.rabobank.service.RetrieveDataService;
import com.rabobank.service.ValidateDataService;


@RestController
@RequestMapping("/rabobank")
public class ProcessorController {
	CustomResponse response = null;

	@Autowired
	private RetrieveDataService retrieveDataService;

	@Autowired
	private ValidateDataService validateDataService;

	private final org.slf4j.Logger Logger =  LoggerFactory.getLogger(this.getClass());


	@RequestMapping(value = "/statementprocess", method = RequestMethod.POST, consumes={"multipart/form-data"})
	public @ResponseBody CustomResponse handleFileUpload(@RequestParam("file") MultipartFile file) throws Exception {{
		response = new CustomResponse();
		List<RequestParameters> errorRecords = new ArrayList<RequestParameters>();
		Logger.debug("Verify if the input file is empty:",file.isEmpty());
		Logger.debug("Verify if the content type of input file:",file.getContentType());
		System.out.println("Verify if the input file is empty:"+file.isEmpty()+"::"+file.getSize());
		System.out.println("Verify if the content type of input file:"+file.getContentType());
		if (!file.isEmpty()) {
			if(file.getOriginalFilename().endsWith("csv") || file.getOriginalFilename().endsWith("CSV")) {
				File csvFile = new File(file.getOriginalFilename());
				Logger.info("Started processing csv file : "+csvFile);
				file.transferTo(csvFile);
				List<RequestParameters> extractedRecords = retrieveDataService.extractStatmentFromCSV(csvFile);
				System.out.println(extractedRecords.toString());
				for(int i=0;i<extractedRecords.size();i++) {
					Logger.info("Processing input records for reference number :"+extractedRecords.get(i).getTransactionRef());
				}
				Logger.info("Validating data for duplicate records");
				errorRecords.addAll(validateDataService.getDuplicateRecords(extractedRecords));
				Logger.info("Validating data for endbalance error records");
				errorRecords.addAll(validateDataService.getEndBalanceErrorRecords(extractedRecords));
				List<RequestParameters> listWithoutDuplicates = errorRecords.stream().distinct().collect(Collectors.toList());
				if (!errorRecords.isEmpty()) {
					response.setResponseCode(Constants.HTTP_CODE_SUCCESS);
					response.setResponseMessage(Constants.VALIDATION_ERROR);
					//response.setRecords(errorRecords);
					response.setRecords(listWithoutDuplicates);
				} else {
					response.setResponseCode(Constants.HTTP_CODE_SUCCESS);
					response.setResponseMessage(Constants.VALIDATION_SUCCESS);
				}}else if(file.getContentType().contains("xml") || file.getOriginalFilename().endsWith("XML") || file.getOriginalFilename().endsWith("xml")){

					File xmlFile = new File(file.getOriginalFilename());
					file.transferTo(xmlFile);
					Logger.info("Started processing xml file"+xmlFile);
					List<RequestParameters> extractedRecords = retrieveDataService.extractStatmentFromXML(xmlFile);
					System.out.println(extractedRecords.toString());
					for(int i=0;i<extractedRecords.size();i++) {
						Logger.info("Processing input records for reference number :"+extractedRecords.get(i).getTransactionRef());
					}
					Logger.info("Validating data for duplicate records");
					errorRecords.addAll(validateDataService.getDuplicateRecords(extractedRecords));
					Logger.info("Validating data for endbalance error records");
					errorRecords.addAll(validateDataService.getEndBalanceErrorRecords(extractedRecords));
					List<RequestParameters> listWithoutDuplicates = errorRecords.stream().distinct().collect(Collectors.toList());
					if (!errorRecords.isEmpty()) {
						response.setResponseCode(Constants.HTTP_CODE_SUCCESS);
						response.setResponseMessage(Constants.VALIDATION_ERROR);
						//response.setRecords(errorRecords);
						response.setRecords(listWithoutDuplicates); //Removed duplicate entries
					} else {
						response.setResponseCode(Constants.HTTP_CODE_SUCCESS);
						response.setResponseMessage(Constants.VALIDATION_SUCCESS);
					}
				} else {
					response.setResponseCode(Constants.HTTP_CODE_UNSUPPORTED_INPUT);
					response.setResponseMessage(Constants.UNSUPORTED_FILE_FORMAT);
				}
		}else{
			response.setResponseCode(Constants.HTTP_CODE_INVALID_INPUT);
			response.setResponseMessage(Constants.INVALID_INPUT);
		}
	}
	return response;
	}

	@ExceptionHandler(Exception.class)
	public @ResponseBody CustomResponse handleException(HttpServletRequest request, Exception ex) {
		response = new CustomResponse();
		response.setResponseCode(Constants.HTTP_CODE_ERROR);
		response.setResponseMessage(Constants.UNEXPECTED_SERVER_ERROR);
		
		return response;
	}

}
