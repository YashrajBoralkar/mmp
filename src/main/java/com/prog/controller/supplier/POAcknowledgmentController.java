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

import com.prog.model.erp.POAcknowledgement;
import com.prog.service.purchase.RawMaterialPurchaseOrderService;
import com.prog.service.supplier.POAcknowledgmentService;
import com.prog.service.supplier.RawMaterialSupplierRegistrationService;


@Controller
public class POAcknowledgmentController {

    @Autowired
    private POAcknowledgmentService poAcknowledgmentService;
    @Autowired
    private RawMaterialPurchaseOrderService rpOrder;
    @Autowired
    private RawMaterialSupplierRegistrationService supplierregistrationservice;
    
    

    @GetMapping("/supplier/poacknowledgementgrid")
	public String showsupplierregistrationgrid(){
		return ("SUPPLIERgrid/POAcknowledgementGrid");
	}
	
    
    @GetMapping("/showpoacknowledgmentform")
    public String showPOAckForm(Model model) {
    	 List<String> rpuid = rpOrder.fetchRMPOUID(); // Fetch all product UIDs
	     model.addAttribute("rpuid", rpuid);
         return "supplier/POAcknowledgmentForm"; // Should match the Thymeleaf file name
    }


    // Submit PO Acknowledgment form
    @PostMapping("/poacknowledgmentform/save")
    public String submitPOAcknowledgment(@ModelAttribute POAcknowledgement poacknowledgment) {
        poAcknowledgmentService.savePurchaseOrderAcknowledgment(poacknowledgment);
        return "redirect:/supplier/poacknowledgementgrid"; // Redirect after submission
    }
    
    
 
 // Display list of all PO Acknowledgment
    @GetMapping("/viewallPoAcknowledgmenlist")
	@ResponseBody
	public Map<String, Object> showlistofcustomerreturn() {
	    List<String> headers = List.of(
	        "ID", "PO Acknowledgment ID","RM Purchase Order ID", "Supplier Name", "PO Date", "Acceptance Status");
	    
        List<Map<String, Object>> PoAcknowledgmentInfoList = poAcknowledgmentService.getAllPOAcknowledgments();
	    List<Map<String, String>> PoAcknowledgmenList = new ArrayList<>();
	    
	    for (Map<String, Object> PoAcknowledgmenInfo : PoAcknowledgmentInfoList) {
	        Map<String, String> row = new HashMap<>();
	        row.put("ID", String.valueOf(PoAcknowledgmenInfo.get("id")));
	        row.put("PO Acknowledgment ID", String.valueOf(PoAcknowledgmenInfo.get("poacknowledgmentuid")));
	        row.put("RM Purchase Order ID", String.valueOf(PoAcknowledgmenInfo.get("rawmaterialpurchaseorderuid")));
	        row.put("Supplier Name", String.valueOf(PoAcknowledgmenInfo.get("suppliername")));
	        row.put("PO Date", String.valueOf(PoAcknowledgmenInfo.get("podate")));
	        row.put("Acceptance Status", String.valueOf(PoAcknowledgmenInfo.get("acceptancestatus")));
	        PoAcknowledgmenList.add(row);
	    }
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("headers", headers);
	    response.put("data", PoAcknowledgmenList);
	    response.put("status", "success");
	    
	    return response;
	}
	
   
    
    
    // Edit PO Acknowledgment
    @GetMapping("/editpoacknowledgment/{id}")
    public String editPOAcknowledgment(@PathVariable Long id, Model model) {
        POAcknowledgement poacknowledgment = poAcknowledgmentService.getPOAcknowledgmentById(id);
        model.addAttribute("poAcknowledgment", poacknowledgment);
        return "supplier/POAcknowledgmentForm"; // Return the form view for editing
    }

    // Update PO Acknowledgment
    @PostMapping("/poacknowledgment/update")
    public String updateAcknowledgment(@ModelAttribute("poAcknowledgment") POAcknowledgement poacknowledgment) {
        poAcknowledgmentService.updateAcknowledgment(poacknowledgment);
        return "redirect:/supplier/poacknowledgementgrid"; // Redirect after update
    }

    // Delete PO Acknowledgment
    @GetMapping("/deletepoacknowledgmentinfo")
    public String deleteAcknowledgment(@RequestParam("id") Long id) {
        poAcknowledgmentService.deleteAcknowledgment(id);
        return "redirect:/supplier/poacknowledgementgrid"; // Redirect after deletion
    }
  
    
  //FETCHING
//    @GetMapping("/poacknowledgement/getSupplierdetails")
//    @ResponseBody
//    public List<Map<String, Object>> getSupplierInfo(@RequestParam("supplieruid") String supplieruid) {
//        if (supplieruid == null || supplieruid.isEmpty()) {
//            throw new IllegalArgumentException("Supplier UID must not be null or empty.");
//        }
//        return supplierregistrationservice.getDataBySupplieruid(supplieruid);
//    }
    
//    @GetMapping("/poacknowledgement/getRpdetails")
//    @ResponseBody
//    public List<Map<String, Object>> getpurchaseInfo(@RequestParam("rawmaterialpurchaseorderuid") String rawmaterialpurchaseorderuid) {
//        return rpOrder.getDataByRpUid(rawmaterialpurchaseorderuid);
//    }
    
 // 🔹 API to fetch Purchase Order details (with parsed materials)
    @GetMapping("/getPoDetailsinpoack/{poUid}")
    @ResponseBody
    public List<Map<String, Object>> getPoDetails(@PathVariable("poUid") String poUid) {
        return poAcknowledgmentService.getRawMaterialsByPurchaseOrderUID(poUid);
    }

}

