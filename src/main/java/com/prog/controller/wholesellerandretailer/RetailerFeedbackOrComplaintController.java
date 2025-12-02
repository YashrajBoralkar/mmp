//package com.prog.controller.wholesellerandretailer;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.prog.model.erp.SellerRegistration;
//import com.prog.model.erp.retailerfeedbackorcomplaint;
//import com.prog.service.wholesellerandretailer.RetailerFeedbackOrComplaintService;
//import com.prog.service.wholesellerandretailer.SellerRegistrationService;
//
//
//@Controller
//public class RetailerFeedbackOrComplaintController {
//	
//	@Autowired
//	private RetailerFeedbackOrComplaintService service;
//	@Autowired
//	private SellerRegistrationService sellerservice;
//	
//	 @GetMapping("/wholesellerandretailer/retailerfeedbackorcomplaintgrid")
//		public String showgrid(){
//			return "WHOLESELLERANDRETAILERgrid/RetailerFeedbackOrComplaintGrid";
//		}
//	
//	//insert 
//	@GetMapping("/showretailerfeedbackorcomplaint")
//	public String showform(Model model) {
//		 	model.addAttribute("updaterf", new retailerfeedbackorcomplaint()); 
//		    List<String> selleruid= sellerservice.getAllRetailerUids();
//			model.addAttribute("selleruid",selleruid);
//		    return "wholesellerandretailer/RetailerFeedbackOrComplaintForm";
//	}
//	
//	// Save
//    @PostMapping("/retailerfeedbackorcomplaint/save")
//    public String savesp(@ModelAttribute("retailerfeedback") retailerfeedbackorcomplaint retailerfeedback) {
//        service.save(retailerfeedback);  
//        return "redirect:/wholesellerandretailer/retailerfeedbackorcomplaintgrid";  
//    }
//    
// // Display list 
//    @GetMapping("/viewallretailerfeedbackorcomplaintinfo")
//	@ResponseBody
//	public Map<String, Object> showlistofretailerfeedbackorcomplaint() {
//	    List<String> headers = List.of(
//	        "ID","Retailer ID","Company Name","Date","Type", "Stauts");
//	    
//        List<Map<String,Object>> retailerfeedbackorcomplaintInfoList  = service.getAlldata(); 
//	    List<Map<String, String>> retailerfeedbackorcomplaintList = new ArrayList<>();
//	    
//	    for (Map<String, Object> retailerfeedbackorcomplaintInfo : retailerfeedbackorcomplaintInfoList) {
//	        Map<String, String> row = new HashMap<>();
//	        row.put("ID", String.valueOf(retailerfeedbackorcomplaintInfo.get("id")));
//	        row.put("Retailer ID", String.valueOf(retailerfeedbackorcomplaintInfo.get("selleruid")));
//	        row.put("Company Name", String.valueOf(retailerfeedbackorcomplaintInfo.get("companyname")));
//	        row.put("Date", String.valueOf(retailerfeedbackorcomplaintInfo.get("date")));
//	        row.put("Type", String.valueOf(retailerfeedbackorcomplaintInfo.get("type")));
//	        row.put("Status", String.valueOf(retailerfeedbackorcomplaintInfo.get("status")));
//	        retailerfeedbackorcomplaintList.add(row);
//	    }
//	    
//	    Map<String, Object> response = new HashMap<>();
//	    response.put("headers", headers);
//	    response.put("data", retailerfeedbackorcomplaintList);
//	    response.put("status", "success");
//	    
//	    return response;
//	}
//    
//    
//    
//    //update the data 
//    @GetMapping("/retailerfeedbackorcomplaint/edit/{id}")
//    public String updatedata(@PathVariable("id") Long id, Model model) {
//    retailerfeedbackorcomplaint proposals = service.GetById(id);  
//        model.addAttribute("updaterf", proposals); // Add RMA for updating
//        return "wholesellerandretailer/RetailerFeedbackOrComplaintForm";  // Return to the index form page for updating
//    }
//    //update and show to list 
//    @PostMapping("/retailerfeedbackorcomplaint/update")
//    public String update(@ModelAttribute retailerfeedbackorcomplaint rf) {
//      service.update(rf);
//      return "redirect:/wholesellerandretailer/retailerfeedbackorcomplaintgrid";
//  }
//    
//  //delete the data
//    @GetMapping("/deleteretailerfeedbackorcomplaintinfo")
//    public String deleterf(@RequestParam("id") Long id) {
//    	service.deleteById(id);
//        return "redirect:/wholesellerandretailer/retailerfeedbackorcomplaintgrid"; 
//    }
//    
//    //fething the data 
//    @GetMapping("/retailerfeedbackorcomplaint/getretaileruid")
//    @ResponseBody
//    public SellerRegistration getretailerDeatils(@RequestParam("selleruid") String selleruid) {
//        return sellerservice.getDetailsByRetailerUid(selleruid);
//    }
//   
//
//}
//
//
//
