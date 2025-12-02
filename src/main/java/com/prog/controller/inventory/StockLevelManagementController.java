 package com.prog.controller.inventory;

import java.sql.SQLException;


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

import com.prog.model.erp.Batchinfo;
import com.prog.model.erp.StockLevelManagement;
import com.prog.model.erp.Warehouseinfo;
import com.prog.service.inventory.BatchInfoService;
import com.prog.service.inventory.StockLevelManagementService;
import com.prog.service.inventory.StockinfoService;
import com.prog.service.inventory.WarehouseInfoService;


@Controller
public class StockLevelManagementController 
{
	@Autowired
	private StockLevelManagementService stocklevelmanagementservice;
	
	@Autowired
	private WarehouseInfoService whservice;
	
	@Autowired
	private BatchInfoService batchservice;
	
	@Autowired
	private StockinfoService sInfoservice;
	
	  @GetMapping("/inventory/stocklevelGrid")
	    public  String viewgrid() {
	    	return "INVENTORYgrid/stocklevelmanagementGrid";
	    }
	  
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	  //Stock Level Management Controller
	 

	
	@GetMapping("/stocklevelform")
	public String showform(Model model) {
	    model.addAttribute("stocklevelmanagement", new StockLevelManagement());
        List<String> warehouseuid = whservice.getAllwarehouseUids();
        List<String> batchuid=batchservice.getAllbatchUids();
//        List<String>stockuid=sInfoservice.getallstockml();
        model.addAttribute("warehouseuid", warehouseuid);
        model.addAttribute("batchuid", batchuid);
//        model.addAttribute("stockuid", stockuid);
        // Removed the extra parenthesis
	    return "inventory/stocklevelmanagementform";
	}

	@PostMapping("/savestocklevelform")
	public String savestocklevel(@ModelAttribute StockLevelManagement levelmanagement,Model model) 
	{ 
		stocklevelmanagementservice.saveStockelevelmanagement(levelmanagement);
		 model.addAttribute("message", "stock saved successfully!");
        model.addAttribute("successMessage", "stock schedule has been saved successfully!");
		return "redirect:/inventory/stocklevelGrid";
		
	}

	
	// Show Stock List
			@GetMapping("/viewAllstocklevellist")
		    @ResponseBody
		    public Map<String, Object> viewAllstocklevel() {        
		        List<String> headers = List.of("ID", "Stock Level ID", "Stock Name", "Warehouseinfo ID", "City Name", "Minimum Stock Quantity", "Maximum Stock Quantity", "Current Stock Quantity","Reorder Stock");
		        
		        List<Map<String, Object>> stocklevelmanagementInfoList = stocklevelmanagementservice.getAllStockLevels();
		        
		        List<Map<String, String>> stocklevelList = new ArrayList<>();
		        
		        for (Map<String, Object> stocklevelInfo : stocklevelmanagementInfoList) {
		            Map<String, String> row = new HashMap<>();
		            row.put("ID", String.valueOf(stocklevelInfo.get("id")));
		            row.put("Stock Level ID", String.valueOf(stocklevelInfo.get("stockuid")));
		            row.put("Stock Name", String.valueOf(stocklevelInfo.get("stockname")));
		            row.put("Warehouseinfo ID", String.valueOf(stocklevelInfo.get("warehouseuid")));
		            row.put("City Name", String.valueOf(stocklevelInfo.get("cityname")));
		            row.put("Minimum Stock Quantity", String.valueOf(stocklevelInfo.get("minstockquantity")));
		            row.put("Maximum Stock Quantity", String.valueOf(stocklevelInfo.get("maxstockquantity")));
		            row.put("Current Stock Quantity", String.valueOf(stocklevelInfo.get("currentstockquantity")));
		            row.put("Reorder Stock", String.valueOf(stocklevelInfo.get("reorederstock")));

		            stocklevelList.add(row);
		        }
		        
		        Map<String, Object> response = new HashMap<>();
		        response.put("headers", headers);
		        response.put("data", stocklevelList);
		        response.put("status", "success");
		        
		        return response;
		    }

	 
	  @GetMapping("/stockleveldeleteinfo")
	    public String deleteStocklevel(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
	        try {
	            System.out.println("Deleting enrollment with ID: " + id); // DEBUG
	            stocklevelmanagementservice.deletestockelevel(id);
	            redirectAttributes.addFlashAttribute("successMessage", "Stock Level entry deleted successfully!");
	        } catch (Exception e) {
	            e.printStackTrace(); // DEBUG
	            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete enrollment.");
	        }
	        return "redirect:/inventory/stocklevelGrid"; // Corrected path
	    }
	  
	  @GetMapping("/updatestocklevel/{id}")
	  public String ViewEnrollment(@PathVariable("id") Long id, Model model) {
	 	 StockLevelManagement stocklevel = stocklevelmanagementservice.getstockById(id);
	     model.addAttribute("stocklevel", stocklevel);
	     return "inventory/stocklevelmanagementform";
	 	 
	  }
	  @PostMapping("/stocklevelupdate")
	  public String updatestocklevel(@ModelAttribute StockLevelManagement stocklevel) {
	      // Validate the incoming enrollment data
	    
	      try {
	    	  stocklevelmanagementservice.updateStocklevel(stocklevel);
	      } catch (Exception e) {
	      }
	      
	      return "redirect:/inventory/stocklevelGrid"; // Redirect to the enrollment list page
	  }
	  
	  @GetMapping("/warehouse/getwarehouseDetails")
	    @ResponseBody
	    public Warehouseinfo getWarehouseDetails(@RequestParam("warehouseuid") String warehouseuid) {
	        
	        return whservice.getwarehouseDetailsByUid(warehouseuid);
	    } 
	  
	  @GetMapping("/batch/getbatchDetails")
	    @ResponseBody
	    public Batchinfo getbatchDetails(@RequestParam("batchuid") String batchuid) {
	       
	        return batchservice.getbatchDetailsByUid(batchuid);
	    } 
	  
//	  @GetMapping("/stock/getstockmanagementDetails")
//	    @ResponseBody
//	    public StockInfo getstockmlDetails(@RequestParam("stockuid") String stockuid) {
//	       
//	        return sInfoservice.getinfobysmluids(stockuid) ;
//	    } 
	  

	  
	  
	  
	  
}
