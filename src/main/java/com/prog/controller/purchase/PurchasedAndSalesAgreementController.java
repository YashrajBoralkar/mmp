package com.prog.controller.purchase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prog.model.erp.PurchasedAndSalesAgreement;
import com.prog.service.purchase.PurchasedAndSalesAgreementService;
import com.prog.service.supplier.RawMaterialSupplierRegistrationService;


@Controller
public class PurchasedAndSalesAgreementController 
{
	@Autowired
	private PurchasedAndSalesAgreementService psaservice;
	 @Autowired
	 private RawMaterialSupplierRegistrationService supplierservice;
	

	@GetMapping("/purchase/purchasedandsalesagreementgrid")
    public String showpasagrid() {
    	return "PURCHASEgrid/PurchasedAndSalesAgreementGrid";
    }
	
	// Displays the PSA form for creating a new Purchased and Sales Agreement.
	@GetMapping("/showpurchasedandsalesagreementform")
	public String showpsaform(Model model) 
	{
		model.addAttribute("pa", new PurchasedAndSalesAgreement());
		List<String> rawmaterialsupplieruid = supplierservice.getallsupplieruids();
        model.addAttribute("supplieruid", rawmaterialsupplieruid);
   		return "purchase/PurchasedAndSalesAgreementForm";
	}
	
	//Saves a new PSA entry to the database and reloads the PSA form.
	@PostMapping("/purchasedandsalesagreement/save")
	public String savepsaform(@ModelAttribute PurchasedAndSalesAgreement pa, Model model) 
	{
		psaservice.saveinsertpsa(pa);
		model.addAttribute("message", "PSA saved successfully");
		model.addAttribute("successMessage", "PSA Info has been saved successfully!");
		return "redirect:/purchase/purchasedandsalesagreementgrid";
	}

	// Display list 
    @GetMapping("/viewallpurchasedandsalesagreementinfo")
	@ResponseBody
	public Map<String, Object> showlistofpurchasedandsalesagreement() {
	    List<String> headers = List.of(
	        "ID","PASA ID","Agreement Date","Buyer Name","Supplier Name", "Contract Value");
	    
		List<Map<String, Object>> purchasedandsalesagreementInfoList = psaservice.show_psaform_in_list();
	    List<Map<String, String>> purchasedandsalesagreementList = new ArrayList<>();
	    
	    for (Map<String, Object> purchasedandsalesagreementInfo : purchasedandsalesagreementInfoList) {
	        Map<String, String> row = new HashMap<>();
	        row.put("ID", String.valueOf(purchasedandsalesagreementInfo.get("id")));
	        row.put("PASA ID", String.valueOf(purchasedandsalesagreementInfo.get("purchasesalesagreementuid")));
	        row.put("Agreement Date", String.valueOf(purchasedandsalesagreementInfo.get("agreementdate")));
	        row.put("Buyer Name", String.valueOf(purchasedandsalesagreementInfo.get("buyername")));
	        row.put("Supplier Name", String.valueOf(purchasedandsalesagreementInfo.get("suppliername")));
	        row.put("Contract Value", String.valueOf(purchasedandsalesagreementInfo.get("contractvalue")));
	        purchasedandsalesagreementList.add(row);
	    }
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("headers", headers);
	    response.put("data", purchasedandsalesagreementList);
	    response.put("status", "success");
	    
	    return response;
	}
	

	 //Loads a specific PSA entry into the form for updating.
	@GetMapping("/purchasedandsalesagreement/update/{id}")
	public String updatepsaentrybyid(@PathVariable Long id, Model model) 
	{
		PurchasedAndSalesAgreement pa = psaservice.get_psa_details_by_id(id);
		model.addAttribute("pa", pa);
		return "purchase/PurchasedAndSalesAgreementForm";
	}
	

	// Updates the PSA entry and redirects back to the PSA list with a success or error message.
	@PostMapping("/purchasedandsalesagreement/update")
	public String update_psa(@ModelAttribute PurchasedAndSalesAgreement pa, RedirectAttributes redirectAttributes) {
		try {
			psaservice.update_psa_from_the_list(pa);
			redirectAttributes.addFlashAttribute("message", "PSA updated successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while updating.");
		}
		return "redirect:/purchase/purchasedandsalesagreementgrid";
	}
	

	//Deletes a specific PSA entry based on the provided ID.
	 @GetMapping("/deletepurchasedandsalesagreementinfo")
	public String deletepsaentrybyid(@RequestParam("id") Long id, Model model) {
		psaservice.delete_psa_service(id);
		return "redirect:/purchase/purchasedandsalesagreementgrid";
	}
	 
	 //FETCHING
	    @GetMapping("/purchasedandsalesagreement/getsupplieruidinfo")
	    @ResponseBody 
	    public List<Map<String, Object>> getSupplierUidInfo(@RequestParam("supplieruid") String rawmaterialsupplieruid) {
	        if (rawmaterialsupplieruid == null || rawmaterialsupplieruid.isEmpty()) {
	            throw new IllegalArgumentException("supplieruid must not be null or empty.");
	        }
	        return supplierservice.getSupplierDetailsByUids(rawmaterialsupplieruid);
	    }
}
