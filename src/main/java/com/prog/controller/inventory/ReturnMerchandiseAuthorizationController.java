package com.prog.controller.inventory;

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

import com.prog.model.erp.CustomerDetails;
import com.prog.model.erp.RMARequestForm;
import com.prog.model.erp.ReturnDetails;
import com.prog.model.erp.ReturnStockInspection;
import com.prog.service.inventory.CustomerDetailsService;
import com.prog.service.inventory.RMARequestService;
import com.prog.service.inventory.ReturnDetailsService;
import com.prog.service.inventory.ReturnStockInspectionService;




@Controller
public class ReturnMerchandiseAuthorizationController {
	

	@Autowired
	private RMARequestService rmaRequestService;
    @Autowired
	private CustomerDetailsService custDetailsService;
    @Autowired
	private ReturnDetailsService returnDetailsService;
    @Autowired
    private ReturnStockInspectionService inspectionService;
		
	
	
	 
	@GetMapping("/inventory/returnmerchandiseauthorizationgrid")
	public String showRMAgrid() {
		return "INVENTORYgrid/ReturnMerchandiseAuthorizationGrid";
		}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  //RMA REQUEST Controller
	
	@GetMapping("/returnmerchandiseauthorization")
	public String fetchData(Model model) {
		model.addAttribute("rma",new RMARequestForm());
		List<String> customeruid=custDetailsService.getAllCustDetails();
		model.addAttribute("customeruid",customeruid);
		
		List<String> batchuid =rmaRequestService.getBatchId();
		model.addAttribute("batchuid",batchuid);
		
		List<String> returnuid=returnDetailsService.getAllReturnsDetailsData();
		model.addAttribute("returnuid",returnuid);
		return "inventory/RMARequestForm";
	}
	
	@PostMapping("/returnmerchandiseauthorization/save")
	public String addRmaRequestData(RMARequestForm rma,Model model) {
		rmaRequestService.addRMARequestData(rma);
		model.addAttribute("Saved Successfully","Saved Successfully RMA Request Form");
		return "redirect:/inventory/returnmerchandiseauthorizationgrid";
	}

	 // Show RMA REQUEST Info List
	@GetMapping("/returnmerchandiseauthorizationlist")
    @ResponseBody
    public Map<String, Object> GetAllRMARequestInfo() {        
        List<String> headers = List.of("ID"," RMA Request ID","Date Of Return Request", "Customer Name","Product Name","Return Quantity", "Return Tracking Number","Approval Status");
        
        List<Map<String,Object>> RMARequestinfolist= rmaRequestService.showAllRMARequsetData();
        
        List<Map<String, String>> RMARequestinfoList = new ArrayList<>();
        
        for (Map<String, Object> RMARequest : RMARequestinfolist) {
            Map<String, String> row = new HashMap<>();
            row.put("ID", String.valueOf(RMARequest.get("id")));
            row.put("RMA Request ID", String.valueOf(RMARequest.get("returnmerchandiseauthorizationuid")));
            row.put("Date Of Return Request", String.valueOf(RMARequest.get("dateofreturnrequest")));
            row.put("Customer Name", String.valueOf(RMARequest.get("customername")));
            row.put("Product Name", String.valueOf(RMARequest.get("productname")));
            row.put("Return Quantity", String.valueOf(RMARequest.get("returnquantity")));
            row.put("Return Tracking Number", String.valueOf(RMARequest.get("returntrakingnumber")));
            row.put("Approval Status", String.valueOf(RMARequest.get("approvalstatus")));
           
            RMARequestinfoList.add(row);
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("headers", headers);
        response.put("data", RMARequestinfoList);
        response.put("status", "success");
        
        return response;
    }
	

	@PostMapping("/returnmerchandiseauthorization/update")
	public String updateRmaRequestData(@ModelAttribute RMARequestForm rma) {
		rmaRequestService.updateRMARequestData(rma);
		return "redirect:/inventory/returnmerchandiseauthorizationgrid";
	}
	@GetMapping("/returnmerchandiseauthorization/update/{id}")
	public String updatedFormById(@PathVariable("id") Long id, Model model) {
		RMARequestForm rma=rmaRequestService.getRequestById(id);
		model.addAttribute("rma",rma);
		return "inventory/RMARequestForm";
	}
	@GetMapping("/deletereturnmerchandiseauthorizationinfo")
	public String deleteRMARequestById(@RequestParam("id") Long id) {
		rmaRequestService.deleteRMARequestById(id);
		return "redirect:/inventory/returnmerchandiseauthorizationgrid";
	}
	
	@GetMapping("/returnmerchandiseauthorization/custdetails")
	@ResponseBody
	public CustomerDetails getCustDetailsById(@RequestParam("customeruid") String customeruid) {
		return custDetailsService.getCustDetailsById(customeruid);
	}

	
	@GetMapping("/returnmerchandiseauthorization/returndetails")
	@ResponseBody
	public ReturnDetails getReturnDetailsById(@RequestParam("returnuid") String returnuid) {
		return returnDetailsService.getReturnDetailsById(returnuid);
	}
	@GetMapping("/returnmerchandiseauthorization/rmabatchdetails")
	@ResponseBody
	public List<Map<String, Object>> getstockDeatils(@RequestParam("batchuid")String batchuid){
		return rmaRequestService.getbatchDetailsbyid(batchuid);
	}


	
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  //RETURN STOCK INSPECTION Controller
	
	 @GetMapping("/returnstockinspectionform")
	    public String showForm(Model model) {
	        model.addAttribute("inspection", new ReturnStockInspection());
	        List<String>productuid = inspectionService.fetchproductUid();
			
			model.addAttribute("productuid", productuid);

	        return "inventory/returnstockinspectionform";
	    }
	    
	    @PostMapping("/returnstockinspection/save")
	    public String submitForm(ReturnStockInspection inspection, @RequestParam("file")MultipartFile file) throws IOException{
	        
	        	if(!file.isEmpty()) {
	        		inspection.setDocument(file.getBytes());
	        	}
	            // If ID is present, update the existing inspection
	            inspectionService.saveInspection(inspection);
	    
	        return "redirect:/inventory/returnmerchandiseauthorizationgrid"; // Redirect to the inspections list
	    }
	    
	    @GetMapping("/returnstockinspection/edit/{id}")
	    public String showEditForm(@PathVariable("id") Long id, Model model) {
	        ReturnStockInspection inspection = inspectionService.getInspectionById(id);
	        model.addAttribute("inspection", inspection);
	        return "inventory/returnstockinspectionform"; // Reuse the same form for editing    
	    }
	    

	    @PostMapping("/returnstockinspection/update")
	    public String update(@ModelAttribute ReturnStockInspection inspection, @RequestParam("file")MultipartFile file, 
	    		RedirectAttributes redirectattribute) throws IOException{
	    	
	    	if(file != null && !file.isEmpty()) {
	    		inspection.setDocument(file.getBytes());
	    	}
	    	inspectionService.updateInspection(inspection);
	    	
	    	return "redirect:/inventory/returnmerchandiseauthorizationgrid";
	    }
	    
	    
	 // Show RETURN STOCK INSPECTION Info List
		@GetMapping("/returnstockinspectionlist")
	    @ResponseBody
	    public Map<String, Object> GetAllReturnStockInfo() {        
	        List<String> headers = List.of("ID"," Return Stock ID","Product Name", "Return Quantity","Stock Condition", "Inspection Date","Inspected By");
	        
	        List<Map<String,Object>> returnstockinspectioninfolist= inspectionService.getAllInspections();
	        
	        List<Map<String, String>> returnstockinspectioninfoList = new ArrayList<>();
	        
	        for (Map<String, Object> returnstockinspection : returnstockinspectioninfolist) {
	            Map<String, String> row = new HashMap<>();
	            row.put("ID", String.valueOf(returnstockinspection.get("id")));
	            row.put("Return Stock ID", String.valueOf(returnstockinspection.get("returnstockuid")));
	            row.put("Product Name", String.valueOf(returnstockinspection.get("productname")));
	            row.put("Return Quantity", String.valueOf(returnstockinspection.get("quantityreturned")));
	            row.put("Stock Condition", String.valueOf(returnstockinspection.get("stockcondition")));
	            row.put("Inspection Date", String.valueOf(returnstockinspection.get("inspectiondate")));
	            row.put("Inspected By", String.valueOf(returnstockinspection.get("inspectedby")));
	           
	            returnstockinspectioninfoList.add(row);
	        }
	        
	        Map<String, Object> response = new HashMap<>();
	        response.put("headers", headers);
	        response.put("data", returnstockinspectioninfoList);
	        response.put("status", "success");
	        
	        return response;
	    }
	    
	    
	    @GetMapping("/deletereturnstockinspectioninfo")
	    public String deleteInspection(@RequestParam("id") Long id) {
	        inspectionService.deleteInspection(id);
	        return "redirect:/inventory/returnmerchandiseauthorizationgrid"; // Redirect to the inspections list after deletion
	    }
	    
	    @GetMapping("/getproductuids")
		@ResponseBody 
		public List<Map<String, Object>> getproductuidinfo(@RequestParam("productuid") String productuid ) {
			
			if(productuid == null || productuid.isEmpty()) {
		        throw new IllegalArgumentException("productuid  must not be null or empty.");

			}
			return inspectionService.getDataByproductuid(productuid);
			
		}

	

	
	
}
