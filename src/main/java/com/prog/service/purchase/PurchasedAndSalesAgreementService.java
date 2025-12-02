package com.prog.service.purchase;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.purchase.PurchasedAndSalesAgreementDao;
import com.prog.model.erp.PurchasedAndSalesAgreement;


@Service
public class PurchasedAndSalesAgreementService 
{
	@Autowired
	private PurchasedAndSalesAgreementDao psadao;
	
	/**
	 * Generates a unique PSA UID with prefix "PSA" followed by 3 random digits.
	 */
	private String generatepsaUID() {
		int length = 4;
		String characters = "1234567890";
		Random random = new Random();
		StringBuilder StId = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			StId.append(characters.charAt(random.nextInt(characters.length())));
		}

		return "PASA" + StId.toString();
	}
	
//================================================================================================================================	

	/**
	 * Saves a new PurchasedAndSalesAgreement entry after assigning a generated UID.
	 */
	public int saveinsertpsa(PurchasedAndSalesAgreement pa) {
		String psauid = generatepsaUID();
		pa.setPurchasesalesagreementuid(psauid);
		return psadao.savepsaentry(pa);
	}
	
//================================================================================================================================	

	/**
	 * Returns a list of all PSA entries for display in the PSA list view.
	 */
	public List<Map<String, Object>> show_psaform_in_list() {
		return psadao.showpsalist();
	}
	
//================================================================================================================================	

	/**
	 * Retrieves a specific PSA record based on its ID.
	 */
	public PurchasedAndSalesAgreement get_psa_details_by_id(Long id) {
		return psadao.select_psa_by_id(id);
	}
	
//================================================================================================================================	

	/**
	 * Deletes a PSA record from the database using its ID.
	 */
	public void delete_psa_service(Long id) {
		psadao.delete_psa_from_list(id);
	}
	
//================================================================================================================================	

	/**
	 * Updates an existing PSA record with new data.
	 */
	public void update_psa_from_the_list(PurchasedAndSalesAgreement pa) {
		psadao.update_psa_by_id(pa);
	}
}
