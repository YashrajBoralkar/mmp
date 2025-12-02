package com.prog.controller.wholesellerandretailer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prog.model.erp.Rawmaterialdeliverychallan;
import com.prog.service.wholesellerandretailer.DeliveryChallanService;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class DeliveryChallanController {

	@Autowired
	DeliveryChallanService dcService;

//	@Autowired
//	RetailerService rService;

	
	 @GetMapping("/wholesellerandretailer/deliverychallangrid")
		public String showgrid(){
			return "WHOLESELLERANDRETAILERgrid/DeliveryChallanGrid";
		}
	 
	@GetMapping("/deliverychallanForm")
	public String getChallanForm(Model model) {
//		List<Map<String, Object>> retailOrder = rService.getRids();
//		model.addAttribute("ret", retailOrder);
		return "wholesellerandretailer/DeliveryChallanForm";
	}



//	@PostMapping("/deliverychallanForm/addChallan")
//	@ResponseBody
//	public Map<String, Object> postChallan(@ModelAttribute DeliveryChallan challan)
//		dcService.postChallan(challan);
//
//		Map<String, Object> response = new HashMap<>();
//		response.put("status", "Delivery Challan saved successfully. ");
//
//		return response;
//	}
//
//	@GetMapping("/deliverychallanForm/allChallans")
//	public String viewChallan(Model model) {
//		List<Map<String, Object>> challan = dcService.getAllChallan();
//		model.addAttribute("challan", challan);
//		return "deliverychallan/alldeliverychallan.html";
//	}
//
//	@GetMapping("/deliverychallanForm/deleteChallan/{dcid}")
//	public String deleteChallan(@PathVariable Long dcid) {
//		dcService.deleteChallan(dcid);
//		return "redirect:/deliverychallanForm/allChallans";
//	}
//
//	@GetMapping("/orduids")
//	@ResponseBody
//	public RetailOrder getOrderUid(Model model, String orderid) {
//		RetailOrder retailOrder = rService.getRetailOrderById(orderid);
//		return retailOrder;   // this goes to form as response and fetch data within it like response.name.
//	}
//	
//	@GetMapping("/deliverychallanForm/getChallanById/{crid}")
//	public String getChallanById(@PathVariable Long crid, Model model) {
//		Map<String, Object> challan = dcService.getChallanById(crid).get(0);
//
//		model.addAttribute("challan", challan);
//		List<Map<String, Object>> retailOrder = rService.getRids();
//		model.addAttribute("ret", retailOrder);
//		
//		// Add the Product details to the model for the form
//		return "deliverychallan/deliverychallanForm.html";
//	}
//
//	@PostMapping("/deliverychallanForm/updatechallan/{dcid}")
//	@ResponseBody
//	public Map<String, Object> updateChallan(@PathVariable Long dcid, @ModelAttribute DeliveryChallan dcData, Model model) {
//	     dcService.updateChallan(dcData);
//		Map<String, Object> response = new HashMap<>();
//		response.put("status", "Updated");
//
//		return response;
//	}

}

