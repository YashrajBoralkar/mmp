package com.prog.service.maintenancemanagement;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.maintenancemanagement.CalibrationRecordDao;
import com.prog.model.erp.CalibrationRecord;
import com.prog.model.erp.EquipmentMaster;

@Service
public class CalibrationRecordService {
	@Autowired
	private CalibrationRecordDao calibrationRecordDao;
	
	public int addCalibarationRecord(CalibrationRecord record) {
		String calibrationrecorduid = generateClbRuid();
		record.setCalibrationrecorduid(calibrationrecorduid);
		return calibrationRecordDao.addCalibrationRecord(record);
	}
	
	private String generateClbRuid() {
	    int length = 4;  // Length of the UID
	    String characters = "1234567890";
	    Random random = new Random();
	    StringBuilder prouid = new StringBuilder(length);

	    // Generate random characters for the UID
	    for (int i = 0; i < length; i++) {
	        prouid.append(characters.charAt(random.nextInt(characters.length())));
	    }

	    return "CR" + prouid.toString();
	}

	public void deleteCalibrationRecord(Long id) {
		calibrationRecordDao.deleteCalibrationRecord(id);
	}

	public List<Map<String, Object>> getAllCalibrationRecord() {
		return calibrationRecordDao.getAllCalibrationRecord();
	}

	public CalibrationRecord getCalibrationRecordById(Long id) {
		return calibrationRecordDao.getCalibrationRecordById(id);
	}
//
	public int updateCalibrationRecord(CalibrationRecord record) {
		return calibrationRecordDao.updateCalibrationRecord(record);
	}


	public List<String> getEquipmentDetailsById(){
		return calibrationRecordDao.getEquipmentDetailsById();
	}
	
	public EquipmentMaster getEquipmentDetails(String equipmentuid) {
		return calibrationRecordDao.getEquipmentDetails(equipmentuid);
	}
}
