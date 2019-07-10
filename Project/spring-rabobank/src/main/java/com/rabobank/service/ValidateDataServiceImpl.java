package com.rabobank.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rabobank.model.RequestParameters;;

@Component
public class ValidateDataServiceImpl implements ValidateDataService{

	private final org.slf4j.Logger Logger =  LoggerFactory.getLogger(this.getClass());
	/**
	 * @return List<Records> to get duplicate records form given list.
	 */
	public List<RequestParameters> getDuplicateRecords(List<RequestParameters> records) {
		Map<Integer, RequestParameters> uniqeRecords = new HashMap<Integer, RequestParameters>();
		List<RequestParameters> duplicateRecords = new ArrayList<RequestParameters>();
		for (RequestParameters record : records) {
			if (uniqeRecords.containsKey(record.getTransactionRef())) {

				duplicateRecords.add(record);
			} else {
				uniqeRecords.put(record.getTransactionRef(), record);

			}
		}

		List<RequestParameters> finalDuplicateRecords = new ArrayList<RequestParameters>();
		finalDuplicateRecords.addAll(duplicateRecords);
		for (RequestParameters record : duplicateRecords) {
			if (null != uniqeRecords.get(record.getTransactionRef())) {

				finalDuplicateRecords.add(uniqeRecords.get(record.getTransactionRef()));
				uniqeRecords.remove(record.getTransactionRef());
			}
		}

		for(int i=0;i<finalDuplicateRecords.size();i++) {
			Logger.info("Final Duplicate Records:"+finalDuplicateRecords.get(i).getTransactionRef());
		}

	return finalDuplicateRecords;
	}

	/**
	 * @return List<Records> if startbalance + mutation != endbalance then
	 *         endbalance is wrong that list will be returned.
	 */
	public List<RequestParameters> getEndBalanceErrorRecords(List<RequestParameters> records) {
		List<RequestParameters> endBalanceErrorRecords = new ArrayList<RequestParameters>();
		for (RequestParameters record : records) {
			Logger.info("Started processing End balance error records");

			if (Math.round((record.getStartBalance() + record.getMutation()) - record.getEndBalance()) != 0) {
				endBalanceErrorRecords.add(record);
			}
		}
		for(int i=0;i<endBalanceErrorRecords.size();i++) {
			Logger.info("Final End Balance Records:"+endBalanceErrorRecords.get(i).getTransactionRef());
		}
		return endBalanceErrorRecords;
	}
}
