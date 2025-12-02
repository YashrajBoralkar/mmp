package com.prog.service.qualitycontrol;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.qualitycontrol.QualityControlAuditDao;
import com.prog.model.erp.QualityControlAudit;


@Service
public class QualityControlAuditService {
	@Autowired
	private QualityControlAuditDao qualityControlAuditDao;
	
	public int addAuditData(QualityControlAudit af) {
		String afUID=generateAuditUID();
		af.setQualitycontrolaudituid(afUID);
		return qualityControlAuditDao.addAuditData(af);
	}
	public QualityControlAudit getAuditFormDataById(Long id) {
		return qualityControlAuditDao.getAuditFormDataById(id);
	}
	public List<Map<String, Object>> getAllAuditFormData(){
		return qualityControlAuditDao.getAllAuditFormData();
	}
	
	public int updateAuditFormData(QualityControlAudit af) {
		return qualityControlAuditDao.updateAuditForm(af);
	}
	
	public void deleteAuditFormDataById(Long id) {
		qualityControlAuditDao.deleteAuditFormDataById(id);
	}
	
	private String generateAuditUID() {
		int length=4;
		String characters="1234567890";
		Random random=new Random();
		StringBuilder afUID=new StringBuilder(length);
		//Generate random characters for the afUId
				for(int i=0;i<length;i++) {
					afUID.append(characters.charAt(random.nextInt(characters.length())));
				}
				return "QCAF"+afUID.toString();
	}
}
