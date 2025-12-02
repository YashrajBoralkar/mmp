package com.prog.controller.supplier;


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

import com.prog.model.erp.SupplierContractAgreement;
import com.prog.service.supplier.RawMaterialSupplierRegistrationService;
import com.prog.service.supplier.SupplierContractAgreementService;


@Controller
public class SupplierContractAgreementController {

    @Autowired
    private SupplierContractAgreementService supplierContractAgreementService;
    @Autowired
    private RawMaterialSupplierRegistrationService supplierregistrationservice;
    
    @GetMapping("/supplier/suppliercontractagreementgrid")
    public String showcontractagreementgrid(){
    	return "SUPPLIERgrid/SupplierContractAgreementGrid";
    }  
    
    
    
    @GetMapping("/showsuppliercontractagreement")
    public String showSupplierContractForm(Model model) {
        model.addAttribute("supplierContract", new SupplierContractAgreement());
        List<String> rawmaterialsupplieruid = supplierregistrationservice.getallsupplieruids();
        model.addAttribute("rawmaterialsupplieruid", rawmaterialsupplieruid);
        
              return "supplier/SupplierContractAgreementForm";
    }
    

    @PostMapping("/suppliercontractagreement/save")
    public String saveSupplierContract(@ModelAttribute SupplierContractAgreement contract, Model model) {
        supplierContractAgreementService.saveSupplierContract(contract);
        model.addAttribute("message", "Supplier Contract record saved successfully");
        model.addAttribute("successmsg", "Saved successfully");
        return "redirect:/supplier/suppliercontractagreementgrid";
    }
    

 // Display list of Supplier Invoice Submission Form
    @GetMapping("/viewallsuppliercontractagreementlist")
	@ResponseBody
	public Map<String, Object> showlistofcustomerreturn() {
	    List<String> headers = List.of(
	        "ID","Contract Agreement ID", "RM Supplier ID", "Supplier Name",
	        "Contract Start Date","Contract End Date","Termination Clause","Supplier Representative Name","Company Representative Name","Date of Approval");
	    
        List<Map<String, Object>> suppliercontractAgreementInfoList = supplierContractAgreementService.getAllSupplierContractAgreement();
	    List<Map<String, String>> suppliercontractAgreementList = new ArrayList<>();
	    
	    for (Map<String, Object> suppliercontractAgreementInfo : suppliercontractAgreementInfoList) {
	        Map<String, String> row = new HashMap<>();
	        row.put("ID", String.valueOf(suppliercontractAgreementInfo.get("id")));
	        row.put("Contract Agreement ID", String.valueOf(suppliercontractAgreementInfo.get("suppliercontractagreementuid")));
	        row.put("RM Supplier ID", String.valueOf(suppliercontractAgreementInfo.get("rawmaterialsupplieruid")));
	        row.put("Supplier Name", String.valueOf(suppliercontractAgreementInfo.get("suppliername")));
	        row.put("Contract Start Date", String.valueOf(suppliercontractAgreementInfo.get("contractstartdate")));
	        row.put("Contract End Date", String.valueOf(suppliercontractAgreementInfo.get("contractenddate")));
	        row.put("Termination Clause", String.valueOf(suppliercontractAgreementInfo.get("terminationclause")));
	        row.put("Supplier Representative Name", String.valueOf(suppliercontractAgreementInfo.get("supplierrepresentativename")));
	        row.put("Company Representative Name", String.valueOf(suppliercontractAgreementInfo.get("companyrepresentativename")));
	        row.put("Date of Approval", String.valueOf(suppliercontractAgreementInfo.get("dateofapproval")));

	        suppliercontractAgreementList.add(row);
	    }
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("headers", headers);
	    response.put("data", suppliercontractAgreementList);
	    response.put("status", "success");
	    
	    return response;
	}
   

    @GetMapping("/updatesuppliercontractagreement/{id}")
    public String editSupplierContract(@PathVariable("id") Long id, Model model) {
        SupplierContractAgreement contract = supplierContractAgreementService.getSupplierContractById(id);
        model.addAttribute("supplierContract", contract);
        return "supplier/SupplierContractAgreementForm";
    }

    @PostMapping("/suppliercontractagreement/update")
    public String updateSupplierContract(@ModelAttribute SupplierContractAgreement contract, RedirectAttributes redirectAttributes) {
        supplierContractAgreementService.updateSupplierContract(contract);
        redirectAttributes.addFlashAttribute("successMessage", "Supplier Contract record updated successfully!");
        return "redirect:/supplier/suppliercontractagreementgrid";
    }
    
    @GetMapping("/deletesuppliercontractagreement")
    public String deleteSupplierContract(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            supplierContractAgreementService.deleteSupplierContract(id);
            redirectAttributes.addFlashAttribute("successMessage", "Supplier Contract entry deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete record.");
        }
        return "redirect:/supplier/suppliercontractagreementgrid";
    }
    

    @GetMapping("/supplier/getsupplieruidinfo")
    @ResponseBody 
    public List<Map<String, Object>> getSupplierUidInfo(@RequestParam("rawmaterialsupplieruid") String rawmaterialsupplieruid) {
        if (rawmaterialsupplieruid == null || rawmaterialsupplieruid.isEmpty()) {
            throw new IllegalArgumentException("supplieruid must not be null or empty.");
        }
        return supplierregistrationservice.getSupplierDetailsByUids(rawmaterialsupplieruid);
    }
    

    
    
    
    
}