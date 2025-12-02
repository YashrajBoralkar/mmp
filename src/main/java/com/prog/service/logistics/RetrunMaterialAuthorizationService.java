package com.prog.service.logistics;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.logistics.RetrunMaterialAuthorizationDao;
import com.prog.model.erp.RetrunMaterialAuthorization;


@Service
public class RetrunMaterialAuthorizationService {

	@Autowired
	private RetrunMaterialAuthorizationDao rmadao;
	
		public int save(RetrunMaterialAuthorization returnmaterialauthorization) {
			String rmaUID=generateInvoiceUID();
			returnmaterialauthorization.setReturnmaterialauthorizationuid(rmaUID);

			return rmadao.add(returnmaterialauthorization);
	}

		public List<String> getBatchId(){
	 		return rmadao.getBatchId();
	 	}
		public List<Map<String,Object>> fetchAllData(){
			return rmadao.fetchAllData();
		}
		
		public List<Map<String, Object>> getProductDetailsByuid(String productuid){
			return rmadao.getProductDetailsByuid(productuid);
		}
		public List<Map<String, Object>> getbatchDetailsbyid(String batchuid){
			return rmadao.getbatchDetailsByuid(batchuid);
		}
		public void deleteById(Long id) {
	        rmadao.deleteById(id);
			
		}
		//update
		public RetrunMaterialAuthorization GetById(long id) {
			return rmadao.getById(id);
		}
		public void update(RetrunMaterialAuthorization updaterma) {
			        rmadao.updaterma(updaterma);
			    }
			
		private String generateInvoiceUID() {
			int length=4;
			String characters="1234567890";
			Random random=new Random();
			StringBuilder rmaUID=new StringBuilder(length);
			//Generate random characters for the sisUId
					for(int i=0;i<length;i++) {
						rmaUID.append(characters.charAt(random.nextInt(characters.length())));
					}
					return "RMAZ"+rmaUID.toString();
		}
		}
