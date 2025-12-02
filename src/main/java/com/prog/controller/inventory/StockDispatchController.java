package com.prog.controller.inventory;

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

import com.prog.model.erp.CustomerDetails;
import com.prog.model.erp.DispatchInformation;
import com.prog.model.erp.StockDispatch;
import com.prog.service.inventory.CustomerDetailsService;
import com.prog.service.inventory.DispatchInformationService;
import com.prog.service.inventory.StockDispatchService;

@Controller
public class StockDispatchController {
	@Autowired
	private StockDispatchService stockDispatchService;
	
	@Autowired
	private CustomerDetailsService customerDetailsService;
	@Autowired
	private DispatchInformationService dispatchInformationService;
	 

	@GetMapping("/inventory/stockdispatched")
    public  String viewgrid() {
    	return "INVENTORYgrid/stockdispatchedGrid";
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//Stock Dispatched Controller
	
	@GetMapping("/stockdispatchform")
	public String getStockDispatchForm(Model model) {
		
		model.addAttribute("sd", new StockDispatch());
		List<String> customeruid= customerDetailsService.getAllCustomerDetails();
		model.addAttribute("customeruid" , customeruid);

		List<String> batchuid =stockDispatchService.getBatchId();
		model.addAttribute("batchuid",batchuid);
		
		List<String> carriername = dispatchInformationService.getAllDispatchInformation();
		model.addAttribute("carriername", carriername);
		
		return "inventory/StockDispatchFormstock";
	}
	//Add data 
	@PostMapping("/addstockdispatch")
	public String addStockDispatchData(StockDispatch sd,Model model) {
		stockDispatchService.addStockDispatch(sd);
		model.addAttribute("Saved Succcesfully", "saved successfully Stock Dispatch");
		return "redirect:/inventory/stockdispatched";
	}

	
	// Show Stock List
				@GetMapping("/viewAllstockdispatchedlist")
			    @ResponseBody
			    public Map<String, Object> viewAllstockdispatched() {        
			        List<String> headers = List.of("ID", "Dispatch Id", "Dispatch Type", "Delivery Address", "Contact Person", "Contact Number", "Unit Of Measure", "Transport Document Number","Mode of Transport","Dispatch Cost","Dispatched By","Approved By","Approval Date","Status");
			        
			        List<Map<String, Object>> stockdispatchedInfoList = stockDispatchService.FetchAllStockDispatchdata();
			        
			        List<Map<String, String>> stockdispatchedList = new ArrayList<>();
			        
			        for (Map<String, Object> stockdispatchedInfo : stockdispatchedInfoList) {
			            Map<String, String> row = new HashMap<>();
			            row.put("ID", String.valueOf(stockdispatchedInfo.get("id")));
			            row.put("Dispatch Id", String.valueOf(stockdispatchedInfo.get("dispatchuid")));
			            row.put("Dispatch Type", String.valueOf(stockdispatchedInfo.get("dispatchtype")));
			            row.put("Delivery Address", String.valueOf(stockdispatchedInfo.get("deliveryaddress")));
			            row.put("Contact Person", String.valueOf(stockdispatchedInfo.get("contactperson")));
			            row.put("Contact Number", String.valueOf(stockdispatchedInfo.get("contactnumber")));
			            row.put("Unit Of Measure", String.valueOf(stockdispatchedInfo.get("unitofmeasure")));
			            row.put("Transport Document Number", String.valueOf(stockdispatchedInfo.get("transportdocumentnumber")));
			            row.put("Mode of Transport", String.valueOf(stockdispatchedInfo.get("modeoftransport")));
			            row.put("Dispatch Cost", String.valueOf(stockdispatchedInfo.get("dispatchcost")));
			            row.put("Dispatched By", String.valueOf(stockdispatchedInfo.get("dispatchedby")));
			            row.put("Approved By", String.valueOf(stockdispatchedInfo.get("approvedby")));
			            row.put("Approval Date", String.valueOf(stockdispatchedInfo.get("approvaldate")));
			            row.put("Status", String.valueOf(stockdispatchedInfo.get("status")));

			            stockdispatchedList.add(row);
			        }
			        
			        Map<String, Object> response = new HashMap<>();
			        response.put("headers", headers);
			        response.put("data", stockdispatchedList);
			        response.put("status", "success");
			        
			        return response;
			    }


	
	@GetMapping("/updateStockDispatch/{id}")
	public String showUpdatedForm(@PathVariable Long  id, Model model) {
		StockDispatch sd = stockDispatchService.getStockDispatchById(id);
		model.addAttribute("dispatch", sd);
		return "inventory/StockDispatchFormstock"; // Name of the update attendance page (HTML file)
	}

	
	@PostMapping("/updateExistingStock")
	public String updateStockDispatch(@ModelAttribute StockDispatch sd) {
		stockDispatchService.updateStockDispatch(sd);
		return "redirect:/inventory/stockdispatched";
	}
	
	
	 @GetMapping("/deleteStockDispatchinfo")
	 public String deleteStockDispatchById(@RequestParam("id") Long  id) {
		 stockDispatchService.deleteStockDispatchById(id);
		 return "redirect:/inventory/stockdispatched";
	 }
	
	 
	 //CustomerDetailsService Controller 
	
	@GetMapping("/customerdetails")
	@ResponseBody
	public CustomerDetails getCustomerDetails(@RequestParam("customeruid") String customeruid){
		return customerDetailsService.getCustomerDetailsById(customeruid);	
	}

	

	//Dispatch Information Controller
	
	@GetMapping("/dispatchinformation")
	@ResponseBody
	public DispatchInformation getDispatchInformation(@RequestParam("carriername") String carriername){	
		return dispatchInformationService.getDispatchInformation(carriername);
	}


  @GetMapping("/batch/getbatchdetailsbyid")
  @ResponseBody
   public List<Map<String, Object>> getbatchDeatils(@RequestParam("batchuid")String batchuid){
	 return stockDispatchService.getbatchDetailsbyid(batchuid);
    }

 }
