package com.prog.controller.inventory;

import org.springframework.stereotype.Controller;


import com.prog.model.erp.CostAdjustment;
import com.prog.model.erp.StockValuation;
import com.prog.service.inventory.CostAdjustmentService;
import com.prog.service.inventory.StockValuationService;
import com.prog.service.inventory.StockinfoService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


	



@Controller
public class StockValuationManagementController {
	
	@Autowired
    private StockValuationService stockvaluationservice;
    @Autowired
	private StockinfoService stockservice;
    @Autowired
	private CostAdjustmentService costadjustmentservice;
	


    
	
	
	@GetMapping("/inventory/stockvaluationGrid")
    public  String viewgrid() {
    	return "INVENTORYgrid/StockValuationManagementGrid";
    }
  
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	//Stock Valuation Controller
	
	@GetMapping("/stockvaluationform")
    public String stockValuationForm(Model model) {
//         List<String>Stockuid = stockservice.getAllStockuids();
        model.addAttribute("stockValuation", new StockValuation());
//	    model.addAttribute("Stockuid", Stockuid);

        return "inventory/StockValuation";
    }

    
    @PostMapping("/savestockvaluation")
    public String saveStockValuation(@ModelAttribute StockValuation stockValuation) {
       
        stockvaluationservice.saveStock(stockValuation);
        return "redirect:/inventory/stockvaluationGrid";
    }

    // Show Stock Valuation Info List
	@GetMapping("/stockvaluationlist")
    @ResponseBody
    public Map<String, Object> GetAllStocktransferInfo() {        
        List<String> headers = List.of("ID","Stock Name","Stock ID","Stock Valuation ID", "Valuation Method","Valuation Date");
        
		List <Map<String,Object>> stockvaluationinfolist = stockvaluationservice.getallStockValuation();
        
        List<Map<String, String>> stockvaluationinfoList = new ArrayList<>();
        
        for (Map<String, Object> stockvaluation : stockvaluationinfolist) {
            Map<String, String> row = new HashMap<>();
            row.put("ID", String.valueOf(stockvaluation.get("id")));
            row.put("Stock Name", String.valueOf(stockvaluation.get("stockname")));
            row.put("Stock ID", String.valueOf(stockvaluation.get("stockuid")));
            row.put("Stock Valuation ID", String.valueOf(stockvaluation.get("stockvaluationuid")));
            row.put("Valuation Method", String.valueOf(stockvaluation.get("valuationmethod")));
            row.put("Valuation Date", String.valueOf(stockvaluation.get("valuationdate")));
            stockvaluationinfoList.add(row);
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("headers", headers);
        response.put("data", stockvaluationinfoList);
        response.put("status", "success");
        
        return response;
    }
	

    @GetMapping("/deleteStockinfo")
    public String deleteStock(@RequestParam("id") int id) {
        stockvaluationservice.deleteById(id);
        return "redirect:/inventory/stockvaluationGrid";
    }
    

	 @GetMapping("/getValuationDetails")
	 @ResponseBody
	    public List<Map<String,Object>> getStockDetails(@RequestParam("stockuid") String stockuid) {
	        // Fetch training details from the service or database
		   
	        return stockvaluationservice.getStockDetailsByStockuid(stockuid);
	    } 
	 
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		
//COST ADJUSTMENT Controller
	 @GetMapping("/showcostadjustmentform")
	    public String showForm(Model model)
	    {
      List<String>Stockuid = stockservice.getAllStockuids();
		    model.addAttribute("Stockuid", Stockuid);
		    
	      List<String>productuid = costadjustmentservice.getApprovedGoodsProductUids();
           model.addAttribute("productuid", productuid);

	        model.addAttribute("costadjustment", new CostAdjustment());
	        return "inventory/CostAdjustmentForm";
	    }
	 
	 @PostMapping("/costadjustment/save")
	    public String  saveCost(@ModelAttribute CostAdjustment costadjustment)
	    {
		 costadjustmentservice.saveCost(costadjustment);
	    	return "redirect:/inventory/stockvaluationGrid";
	    }
	 
	 @GetMapping("/costadjustment/list")
	    public String getCostList(Model model) {
	        List<Map<String, Object>> costadjustment = costadjustmentservice.getCostAdjustment();
	        model.addAttribute("costadjustment", costadjustment);
	        return "costlist";
	    }
	 // Show Stock Valuation Info List
		@GetMapping("/viewcostadjustmentlist")
	    @ResponseBody
	    public Map<String, Object> GetAllCostAdjustmentInfo() {        
	        List<String> headers = List.of("ID","Stock Name","Stock ID","Stock Valuation ID", "Valuation Method","Valuation Date");
	        
			List <Map<String,Object>> stockvaluationinfolist = stockvaluationservice.getallStockValuation();
	        
	        List<Map<String, String>> stockvaluationinfoList = new ArrayList<>();
	        
	        for (Map<String, Object> stockvaluation : stockvaluationinfolist) {
	            Map<String, String> row = new HashMap<>();
	            row.put("ID", String.valueOf(stockvaluation.get("id")));
	            row.put("Stock Name", String.valueOf(stockvaluation.get("stockname")));
	            row.put("Stock ID", String.valueOf(stockvaluation.get("stockuid")));
	            row.put("Stock Valuation ID", String.valueOf(stockvaluation.get("stockvaluationuid")));
	            row.put("Valuation Method", String.valueOf(stockvaluation.get("valuationmethod")));
	            row.put("Valuation Date", String.valueOf(stockvaluation.get("valuationdate")));
	            stockvaluationinfoList.add(row);
	        }
	        
	        Map<String, Object> response = new HashMap<>();
	        response.put("headers", headers);
	        response.put("data", stockvaluationinfoList);
	        response.put("status", "success");
	        
	        return response;
	    }
		
		
	 @GetMapping("/deletecostadjustmentinfo")
	  public String deleteCost(@RequestParam("id") Long id) {
		 costadjustmentservice.deleteCost(id);  
	      return "redirect:/inventory/stockvaluationGrid";  
	  }
	 
	 @GetMapping("/costadjustment/update/{id}")
	   public String editCost(@PathVariable("id") Long id, Model model) {
		 CostAdjustment costadjustment = costadjustmentservice.getCostAdjustmentbyid(id);
	       model.addAttribute("costadjustment", costadjustment);
	       return "inventory/CostAdjustmentForm";
	   
	   }
	   
	   @PostMapping("/costadjustment/update")
	   public String updateCostAdjustment(@ModelAttribute CostAdjustment costadjustment,RedirectAttributes redirectAttributes)
	   {
		   costadjustmentservice.updateCostAdjustment(costadjustment);
		   return "redirect:/inventory/stockvaluationGrid";
	   }
	   

		 @GetMapping("/getStockDetails")
		 @ResponseBody
		    public List<Map<String,Object>> getStockdetails(@RequestParam("stockuid") String stockuid) {
		        // Fetch training details from the service or database
			   
		        return costadjustmentservice.getdataByStockuid(stockuid);
		    } 
		 
		 /**
			 * API to fetch product details (unit price + available stock quantity) by Product UID.
			 */
			@GetMapping("/getproductdetails")
			@ResponseBody
			public Map<String, Object> getProductDetails(@RequestParam("productuid") String productuid) {
				return costadjustmentservice.getProductDetails(productuid);
			}
			
			@GetMapping("/getstockdetails")
			@ResponseBody
			public Map<String, Object> getstockDetails(@RequestParam("productuid") String productuid) {
				return costadjustmentservice.getstockDetails(productuid);
			}
		 

	
}

