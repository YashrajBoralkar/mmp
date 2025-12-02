package com.prog.controller.logistics;

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

import com.prog.model.erp.RetrunMaterialAuthorization;
import com.prog.service.logistics.RetrunMaterialAuthorizationService;

@Controller
public class RetrunMaterialAuthorizationController {

    @Autowired
    private RetrunMaterialAuthorizationService rmaserv;
    
    
    @GetMapping("/logistics/returnmaterialauthorizationgrid")
    public String ShowRMAGrid() {
    	return "LOGISTICSgrid/ReturnMaterialAuthorizationGrid";
    }

    
    // Display the form on home page
    @GetMapping("/showreturnmaterialauthorization")
    public String homerma(Model model) {
        model.addAttribute("returnmaterialauthorization", new RetrunMaterialAuthorization());
        
        // Get the batch IDs to populate in the dropdown or whatever field you need
        List<String> batchuid = rmaserv.getBatchId();
        model.addAttribute("batchuid", batchuid);
        
        return "logistics/ReturnMaterialAuthorizationForm"; // Return to index page with the form
    }

    // Save the return material authorization (RMA) data
    @PostMapping("/returnmaterialauthorization/save")
    public String saverma(@ModelAttribute("returnmaterialauthorization") RetrunMaterialAuthorization returnmaterialauthorization,RedirectAttributes redirectAttributes) {
    	
    	 rmaserv.save(returnmaterialauthorization); 
    	 redirectAttributes.addFlashAttribute("message", "Return Material Authorization record saved successfully");
    	 redirectAttributes.addFlashAttribute("successmsg", "Saved successfully"); 
        return "redirect:/logistics/returnmaterialauthorizationgrid";  // Redirect to the list page
    }

 

 // Display list of all RMA info
    @GetMapping("/viewallreturnmaterialauthorizationlistinfo")
	@ResponseBody
	public Map<String, Object> showlistofgoodsreceiptnote() {
	    List<String> headers = List.of(
	        "ID", "RMA ID","Return Date","Requester Name", "Product Name",
	        "Quantity Returned","Return Approved By", "Return Status");
	    
	     List<Map<String, Object>> ReturnMaterialAuthorizationInfoList = rmaserv.fetchAllData();
	    List<Map<String, String>> ReturnMaterialAuthorizationList = new ArrayList<>();
	    
	    for (Map<String, Object> ReturnMaterialAuthorizationInfo : ReturnMaterialAuthorizationInfoList) {
	        Map<String, String> row = new HashMap<>();
	        row.put("ID", String.valueOf(ReturnMaterialAuthorizationInfo.get("id")));
	        row.put("RMA ID", String.valueOf(ReturnMaterialAuthorizationInfo.get("returnmaterialauthorizationuid")));
	        row.put("Return Date", String.valueOf(ReturnMaterialAuthorizationInfo.get("returndate")));
	        row.put("Requester Name", String.valueOf(ReturnMaterialAuthorizationInfo.get("requestername")));
	        row.put("Product Name", String.valueOf(ReturnMaterialAuthorizationInfo.get("productname")));
	        row.put("Quantity Returned", String.valueOf(ReturnMaterialAuthorizationInfo.get("quantityreturned")));
	        row.put("Return Approved By", String.valueOf(ReturnMaterialAuthorizationInfo.get("returnapprovedby")));
	        row.put("Return Status", String.valueOf(ReturnMaterialAuthorizationInfo.get("returnstatus")));
	        ReturnMaterialAuthorizationList.add(row);
	    }
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("headers", headers);
	    response.put("data", ReturnMaterialAuthorizationList);
	    response.put("status", "success");
	    
	    return response;
	}
    

   

    // Fetch and show RMA details for updating (display on the index form)
    @GetMapping("/updatereturnmaterialauthorization/{id}")
    public String updatedata(@PathVariable("id") Long id, Model model) {
        RetrunMaterialAuthorization updaterma = rmaserv.GetById(id);  
        model.addAttribute("updaterma", updaterma); // Add RMA for updating
        return "logistics/ReturnMaterialAuthorizationForm";  // Return to the index form page for updating
    }

    // Post data to update the RMA entity
    @PostMapping("/returnmaterialauthorization/update")
    public String updaterma(@ModelAttribute("returnmaterialauthorization") RetrunMaterialAuthorization updaterma, RedirectAttributes redirectAttributes) {
       
    	rmaserv.update(updaterma);  // Update the entity in the database
        redirectAttributes.addFlashAttribute("successMessage", "Return Material Authorization record updated successfully!");
    	return "redirect:/logistics/returnmaterialauthorizationgrid";  // After updating, redirect to the list
    }
    
 // Delete an RMA entry
    @GetMapping("/deletereutrnmaterialauthorizationinfo")
    public String deleteRma(@RequestParam("id") Long id,RedirectAttributes redirectAttributes) {
        try {
        	rmaserv.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Return Material Authorization entry deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete record.");
        }
        return "redirect:/logistics/returnmaterialauthorizationgrid"; // After deletion, redirect to the list
    }
    
 // Get batch details via AJAX call
    @GetMapping("/returnmaterialauthorization/batchdetails")
    @ResponseBody
    public List<Map<String, Object>> getstockDeatils(@RequestParam("batchuid") String batchuid) {
        return rmaserv.getbatchDetailsbyid(batchuid);
    }

}
