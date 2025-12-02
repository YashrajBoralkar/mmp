package com.prog.controller.inventory;

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

import com.prog.service.inventory.WarehouseInfoService;
import com.prog.model.erp.Warehouseinfo;
import com.prog.service.inventory.ProductInfoService;

@Controller
public class ExpiryDateManagementController {
	
	
//			@Autowired
//			private ExpiryService expiryservice;
			
			@Autowired
			private WarehouseInfoService warehouseservice;
			
			@Autowired
			private ProductInfoService ProductInfoService;


	
	
	
	@GetMapping("/inventory/expirydatemanagementgrid")
	public String showexpiry() {
		return "INVENTORYgrid/ExpiryDateManagementGrid";
	}
}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//	
//   //Expiry Alert(EXPIRY TRACKING)Controller
//	
//	 @GetMapping("/expirytracking")
//	    public String showForm(Model model)
//	    {
//	         List<String>Productuid = ProductInfoService.getAllProductuids();
//	         List<String>Warehouseuid= warehouseservice.getAllWarehouseuids();		   
//	        model.addAttribute("expirytracking", new ExpiryTracking());
//		    model.addAttribute("Productuid", Productuid);
//		    model.addAttribute("Warehouseuid", Warehouseuid);
//	        return "inventory/ExpiryTrackingForm";
//	    }
//	 
//	 @PostMapping("/expirytracking/save")
//	    public String  savetrack(@ModelAttribute ExpiryTracking expirytracking )
//	    {
//		 expiryservice.saveExpiry(expirytracking);
//	    	return "redirect:/inventory/expirydatemanagementgrid";
//	    }
//	 
//	 @GetMapping("/expirytracking/list")
//	    public String viewlist(Model model) 
//	   {
//	        
//			List<Map<String,Object>>expirytracking = expiryservice.getExpiryTracking();
//	        model.addAttribute("expirytracking",expirytracking);
//	        return "expirylist";
//	        
//	   }
//	 
//	 @GetMapping("/getProductDetails")
//	 @ResponseBody
//	    public List<Map<String,Object>> getProductDetails(@RequestParam("productuid") String productuid) {
//	        // Fetch training details from the service or database
//		   
//	        return expiryservice.getproductdetailsbyProductuid(productuid);
//	    } 
//	   
//	 @GetMapping("/warehouse/getWarehouseDetails")
//	    @ResponseBody
//	    public Warehouseinfo getWarehouseDetails(@RequestParam("warehouseuid") String warehouseuid) {
//	        // Fetch training details from the service or database
//		   
//	        return warehouseservice.getwarehouseDetailsByWarehouseuid(warehouseuid);
//	    } 
//	   
//
//	 @GetMapping("/expirytracking/delete/{id}")
//	  public String deleteexpiry(@RequestParam("id") Long id) {
//		 expiryservice.deleteexpiry(id);  
//	      return "redirect:/inventory/expirydatemanagementgrid";  
//	  }
//	 
//	 @GetMapping("/expirytracking/update/{id}")
//	   public String editexpiry(@PathVariable("id") Long id, Model model) {
//		 ExpiryTracking expirytracking = expiryservice.getExpiryTrackingbyid(id);
//	       model.addAttribute("expirytracking", expirytracking);
//	       return "inventory/ExpiryTrackingForm";
//	   
//	   }
//	   
//	   @PostMapping("/expirytracking/update")
//	   public String updateExpiry(@ModelAttribute ExpiryTracking expirytracking,RedirectAttributes redirectAttributes)
//	   {
//		   expiryservice.updateExpiryTracking(expirytracking);
//		   return "redirect:/inventory/expirydatemanagementgrid";
//	   }
//
//}
//	
//	
//	
//	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//	
//  //First Expiry First Out(FEFO)Controller
//	
//
//}
