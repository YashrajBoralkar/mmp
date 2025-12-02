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

import com.prog.model.erp.OnlinePurchaseRequest;
import com.prog.model.erp.noncompetitivepurchaserequest;
import com.prog.service.admin.departmentservice;
import com.prog.service.inventory.ProductInfoService;
import com.prog.service.purchase.NonCompetitivePurchaseRequestService;
import com.prog.service.supplier.RawMaterialSupplierRegistrationService;


@Controller
public class NonCompetitivePurchaseRequestController {

	 @Autowired
	  private NonCompetitivePurchaseRequestService nonCompetitivePurchaseRequestService;
	 @Autowired
	 private RawMaterialSupplierRegistrationService supplierservice;
	 @Autowired
	 private ProductInfoService productservice;
	 @Autowired
	 private departmentservice departmentService;
	 
	 
	    @GetMapping("/purchase/noncompetitivepurchaserequestgrid")
	    public String showrfigrid() {
	    	return "PURCHASEgrid/NonCompetitivePurchaseRequestGrid";
	    }
	    
	 
	    // Show Customer Quotation form
	    @GetMapping("/showpurchaserequestform")
	    public String showPurchaseRequestForm(Model model) {
			model.addAttribute("PurchaseRequest",new noncompetitivepurchaserequest());

	    	List<String> rawmaterialsupplieruid = supplierservice.getallsupplieruids();
	        model.addAttribute("supplieruid", rawmaterialsupplieruid);
	         
	        List<String> productuid = productservice.getAllProductuids(); // Fetch all product UIDs
	        model.addAttribute("productuid", productuid);
	       
	        List<String> deprtmentname = departmentService.getAlldepartment();
			model.addAttribute("dname",deprtmentname);
	        	        
	        return "purchase/NonCompetitivePurchaseRequestForm"; 
	    }

	    
	     //Submit Customer Quotation form
	    @PostMapping("/noncompetitivepurchaserequest/save")
	    public String submitPurchaseRequest(@ModelAttribute noncompetitivepurchaserequest PurchaseRequest) {
	    	nonCompetitivePurchaseRequestService.savePurchaseRequest(PurchaseRequest);
	        return "redirect:/purchase/noncompetitivepurchaserequestgrid";
	    }

	    
	 // Display list 
	    @GetMapping("/viewallnoncompetitivepurchaserequestinfo")
		@ResponseBody
		public Map<String, Object> showlistofgoodsreceiptnote() {
		    List<String> headers = List.of(
		        "ID","NCPR ID","Request Date","Requesting Department","Supplier Name", "Supplier Contact Number","Product Name","Expected Delivery Date","Finance Approval Status","Procurement Approval Status");
		    
	        List<Map<String, Object>> noncompetitivepurchasereuestInfoList = nonCompetitivePurchaseRequestService.getAllPurchaseRequest();
		    List<Map<String, String>> requestforproposalList = new ArrayList<>();
		    
		    for (Map<String, Object> requestforproposalInfo : noncompetitivepurchasereuestInfoList) {
		        Map<String, String> row = new HashMap<>();
		        row.put("ID", String.valueOf(requestforproposalInfo.get("id")));
		        row.put("NCPR ID", String.valueOf(requestforproposalInfo.get("noncompetitivepurchaserequestuid")));
		        row.put("Request Date", String.valueOf(requestforproposalInfo.get("requestdate")));
		        row.put("Requesting Department", String.valueOf(requestforproposalInfo.get("requestingdepartment")));
		        row.put("Supplier Name", String.valueOf(requestforproposalInfo.get("suppliername")));
		        row.put("Supplier Contact Number", String.valueOf(requestforproposalInfo.get("contactnumber")));
		        row.put("Product Name", String.valueOf(requestforproposalInfo.get("productname")));
		        row.put("Expected Delivery Date", String.valueOf(requestforproposalInfo.get("expecteddeliverydate")));
		        row.put("Finance Approval Status", String.valueOf(requestforproposalInfo.get("financeapprovalstatus")));
		        row.put("Procurement Approval Status", String.valueOf(requestforproposalInfo.get("procurementapprovalstatus")));

		        requestforproposalList.add(row);
		    }
		    
		    Map<String, Object> response = new HashMap<>();
		    response.put("headers", headers);
		    response.put("data", requestforproposalList);
		    response.put("status", "success");
		    
		    return response;
		}
	    
	    // Edit Customer Quotation
	    @GetMapping("/noncompetitivepurchaserequest/update/{id}")
	    public String editPurchaseRequest(@PathVariable Long id, Model model) {
	    	noncompetitivepurchaserequest PurchaseRequest = nonCompetitivePurchaseRequestService.getPurchaseRequestById(id);
	        model.addAttribute("PurchaseRequest", PurchaseRequest);
			model.addAttribute("dname", departmentService.getAlldepartment());
	        return "purchase/NonCompetitivePurchaseRequestForm";
	    }

	    // Update Customer Quotation
	    @PostMapping("/noncompetitivepurchaserequest/update")
	    public String updatePurchaseRequest(@ModelAttribute("PurchaseRequest") noncompetitivepurchaserequest PurchaseRequest) {
	    	nonCompetitivePurchaseRequestService.updatePurchaseRequest(PurchaseRequest);
	        return "redirect:/purchase/noncompetitivepurchaserequestgrid";
	    }
	    	    
	    
	    // Delete Customer Quotation
	    @GetMapping("/deletepurchaserequestinfo")
	    public String deletePurchaseRequest(@RequestParam("id") Long id) {
	    	nonCompetitivePurchaseRequestService.deletePurchaseRequest(id);
	        return "redirect:/purchase/noncompetitivepurchaserequestgrid";
	    }
	    
	    
	  //FETCHING
	    @GetMapping("/noncompetitivepurchasereuest/getsupplieruidinfo")
	    @ResponseBody 
	    public List<Map<String, Object>> getSupplierUidInfo(@RequestParam("supplieruid") String rawmaterialsupplieruid) {
	        if (rawmaterialsupplieruid == null || rawmaterialsupplieruid.isEmpty()) {
	            throw new IllegalArgumentException("supplieruid must not be null or empty.");
	        }
	        return supplierservice.getSupplierDetailsByUids(rawmaterialsupplieruid);
	    }
	
	    @GetMapping("/noncompetitivepurchasereuest/getproductdetails")
		   @ResponseBody
		      public  List<Map<String, Object>>getProductDetails(@RequestParam("productuid") String productuid) {
		  	   
		          return productservice.getproductDetailsByProductuid(productuid);
		      } 
	
}
