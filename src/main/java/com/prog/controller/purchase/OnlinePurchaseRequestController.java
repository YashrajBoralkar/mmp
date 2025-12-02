package com.prog.controller.purchase;

import java.io.IOException;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prog.model.erp.OnlinePurchaseRequest;
import com.prog.service.admin.departmentservice;
import com.prog.service.inventory.ProductInfoService;
import com.prog.service.purchase.OnlinePurchaseRequestService;


@Controller
public class OnlinePurchaseRequestController {
	@Autowired
	private OnlinePurchaseRequestService onlinePurchaseRequestService;
	@Autowired
	private ProductInfoService productservice;
	@Autowired
	private departmentservice departmentService;
	 
	 @GetMapping("/purchase/onlinepurchaserequestgrid")
	    public String showOPRGrid() {
	        return "PURCHASEGrid/OnlinePurchaseRequestGrid";
	    }
	 	 
	@GetMapping("/showonlinepurchserequestform")
	public String getPurchaseForm(Model model) {
		model.addAttribute("oprform",new OnlinePurchaseRequest());
		List<String> deprtmentname = departmentService.getAlldepartment();
		model.addAttribute("dname",deprtmentname);
        List<String> productuid = productservice.getAllProductuids(); // Fetch all product UIDs
		model.addAttribute("productuid", productuid);
		return "purchase/OnlinePurchaseRequestForm";
	}
	
	@PostMapping("/onlinepurchserequest/save")
	public String addOnlinePurchaseRequest(@RequestParam("file") MultipartFile file, 
            @ModelAttribute OnlinePurchaseRequest opr,Model model) throws IOException {
		if (!file.isEmpty()) {
			opr.setInvoiceupload(file.getBytes());
			opr.setDocName(file.getOriginalFilename());
		}
		onlinePurchaseRequestService.addOnlinePurchaseRequest(opr);
		 model.addAttribute("message", "OPR Form saved successfully");
	     model.addAttribute("successmsg", "Saved successfully");
	       
		return "redirect:/purchase/onlinepurchaserequestgrid";
	}
	
	
	 // Display list 
    @GetMapping("/viewallonlinepurchaserequestinfo")
	@ResponseBody
	public Map<String, Object> showlistofgoodsreceiptnote() {
	    List<String> headers = List.of(
	        "ID","OPR ID","Request Date", "Online Vendor Name", "Website Link","Product Name","Total Amount","Expected Delivery Date","Finance Dept Approval Status", "Procurement Dept Approval Status");
	    
		List<Map<String,Object>> OnlinePurchaseRequestInfoList  = onlinePurchaseRequestService.getAllOnlinePurchaseRequest();
	    List<Map<String, String>> OnlinePurchaseRequestList = new ArrayList<>();
	    
	    for (Map<String, Object> OnlinePurchaseRequestInfo : OnlinePurchaseRequestInfoList) {
	        Map<String, String> row = new HashMap<>();
	        row.put("ID", String.valueOf(OnlinePurchaseRequestInfo.get("id")));
	        row.put("OPR ID", String.valueOf(OnlinePurchaseRequestInfo.get("onlinepurchaserequestuid")));
	        row.put("Request Date", String.valueOf(OnlinePurchaseRequestInfo.get("requestdate")));
	        row.put("Online Vendor Name", String.valueOf(OnlinePurchaseRequestInfo.get("onlinevendorname")));
	        row.put("Website Link", String.valueOf(OnlinePurchaseRequestInfo.get("websitelink")));
	        row.put("Product Name", String.valueOf(OnlinePurchaseRequestInfo.get("productname")));
	        row.put("Total Amount", String.valueOf(OnlinePurchaseRequestInfo.get("finaltotalamount")));
	        row.put("Expected Delivery Date", String.valueOf(OnlinePurchaseRequestInfo.get("expecteddeliverydate")));
	        row.put("Finance Dept Approval Status", String.valueOf(OnlinePurchaseRequestInfo.get("financedepartmentapprovalstatus")));
	        row.put("Procurement Dept Approval Status", String.valueOf(OnlinePurchaseRequestInfo.get("procurementdepartmentapprovalstatus")));
	        OnlinePurchaseRequestList.add(row);
	    }
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("headers", headers);
	    response.put("data", OnlinePurchaseRequestList);
	    response.put("status", "success");
	    
	    return response;
	}
    
	@GetMapping("/onlinepurchserequest/update/{id}")
	public String getOnlinePurchaseRequestById(@PathVariable("id")  Long id, Model model) {
		OnlinePurchaseRequest opr=onlinePurchaseRequestService.getOnlinePurchaseRequestById(id);
		
		model.addAttribute("oprform",opr);
		model.addAttribute("dname", departmentService.getAlldepartment());
		return "purchase/OnlinePurchaseRequestForm";
	}
	
	@PostMapping("/onlinepurchserequest/update")
	public String updateOnlinePurchaseRequestModel(@ModelAttribute OnlinePurchaseRequest opr,RedirectAttributes redirectAttributes) {
		onlinePurchaseRequestService.updateOnlinePurchaseRequestData(opr);
        redirectAttributes.addFlashAttribute("successMessage", "OPR Form updated successfully!");
		return "redirect:/purchase/onlinepurchaserequestgrid";
	}
	
	@GetMapping("/deleteonlinepurchserequestinfo")
	public String deleteOnlinePurchaseRequest(@RequestParam("id") Long id) {
		onlinePurchaseRequestService.deleteOnlinePurchaseRequestById(id);
		return "redirect:/purchase/onlinepurchaserequestgrid";
	}
	
	//FETCHING PRODUCT DETAILS
	@GetMapping("/onlinepurchaserequest/getproductdetails")
	@ResponseBody
	 public  List<Map<String, Object>>getProductDetails(@RequestParam("productuid") String productuid) { 	   
	          return productservice.getproductDetailsByProductuid(productuid);
	      } 
	
}
