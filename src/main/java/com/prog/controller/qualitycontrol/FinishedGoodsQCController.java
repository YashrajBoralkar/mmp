package com.prog.controller.qualitycontrol;

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

import com.prog.model.erp.finishedgoodsqc;
import com.prog.service.qualitycontrol.FinishedGoodsQCService;

@Controller
public class FinishedGoodsQCController {
	@Autowired
	private FinishedGoodsQCService finishedGoodsQCService;

	@GetMapping("/qualitycontrol/finishgoodsqcgrid")
	public String showfinishgoodsqcgrid() { 
		return "QUALITYCONTROLgrid/FinishedGoodsQualityControlGrid";
	}

	// Show Physical Count form
	@GetMapping("/showfinishgoodsqcform")
	public String showFinishedGoodsQC(Model model) {
		List<String> productuid = finishedGoodsQCService.fetchProductUIds();
		model.addAttribute("productuid", productuid);
		
		List<Map<String, Object>> employeeuid = finishedGoodsQCService.getEmpuid();
		model.addAttribute("employeeuid", employeeuid);
		return "qualitycontrol/FinishedGoodsQualityControlForm"; // Return the view name for the form
	}

	@PostMapping("/finishgoodsqc/save")
	public String submitFinishedGoodsQC(@ModelAttribute finishedgoodsqc finishedgoodsqc) throws IOException {
		finishedGoodsQCService.saveFinishedGoodsQC(finishedgoodsqc);
		return "redirect:/qualitycontrol/finishgoodsqcgrid";
	}

	// Display list
	@GetMapping("/viewallfinishedgoodsqcinfo")
	@ResponseBody
	public Map<String, Object> showlistoffirstarticleinspection() {
		List<String> headers = List.of("ID", "Finish Goods QC ID", "Production Order ID", "Product Name", "Inspection Date",
				"Final Approval Status", "Action Taken");

		List<Map<String, Object>> FinishedGoodsQCInfoList = finishedGoodsQCService.getAllFinishedGoodsQC();
		List<Map<String, String>> FinishedGoodsQCList = new ArrayList<>();

		for (Map<String, Object> FinishedGoodsQCInfo : FinishedGoodsQCInfoList) {
			Map<String, String> row = new HashMap<>();
			row.put("ID", String.valueOf(FinishedGoodsQCInfo.get("id")));
			row.put("Finish Goods QC ID", String.valueOf(FinishedGoodsQCInfo.get("finishgoodsqcuid")));
			row.put("Production Order ID", String.valueOf(FinishedGoodsQCInfo.get("productionorderuid")));
			row.put("Product Name", String.valueOf(FinishedGoodsQCInfo.get("productname")));
			row.put("Inspection Date", String.valueOf(FinishedGoodsQCInfo.get("inspectiondate")));
			row.put("Final Approval Status", String.valueOf(FinishedGoodsQCInfo.get("finalapprovalstatus")));
			row.put("Action Taken", String.valueOf(FinishedGoodsQCInfo.get("actiontaken")));
			FinishedGoodsQCList.add(row);
		}

		Map<String, Object> response = new HashMap<>();
		response.put("headers", headers);
		response.put("data", FinishedGoodsQCList);
		response.put("status", "success");

		return response;
	}

	// Edit Physical Count
	@GetMapping("/finishgoodsqc/update/{id}")
	public String editFinishedGoodsQC(@PathVariable Long id, Model model) {
		List<Map<String, Object>> finishedgoodsqc = finishedGoodsQCService.getFinishedGoodsQCById(id);
		model.addAttribute("finishedGoodsQC", finishedgoodsqc.get(0));
		return "qualitycontrol/FinishedGoodsQualityControlForm"; // Return the form view for editing
	}

	// Update Physical Count
	@PostMapping("/finishgoodsqc/update")
	public String updatePhysicalCount(@ModelAttribute("finishedGoodsQC") finishedgoodsqc finishedgoodsqc) {

		finishedGoodsQCService.updateFinishedGoodsQC(finishedgoodsqc);

		return "redirect:/qualitycontrol/finishgoodsqcgrid"; // Redirect after update
	}

	// Delete Physical Count
	@GetMapping("/deletefinishgoodsqcinfo")
	public String deleteFinishedGoodsQC(@RequestParam("id") Long id) {
		finishedGoodsQCService.deleteFinishedGoodsQC(id);
		return "redirect:/qualitycontrol/finishgoodsqcgrid"; // Redirect to the list view
	}

	// FETCHING

	// Get product details based on product UID
	@GetMapping("/finishgoodsqc/getproductdetails")
	@ResponseBody
	public List<Map<String, Object>> getProductInfo(@RequestParam("productuid") String productuid) {
		if (productuid == null || productuid.isEmpty()) {
			throw new IllegalArgumentException("Product UID must not be null or empty.");
		}
		return finishedGoodsQCService.getDataByProductUid(productuid);
	}

	@GetMapping("/finishgoodsqc/getDataByBatchuid")
	@ResponseBody
	public List<Map<String, Object>> getStockDetails(@RequestParam("batchuid") String batchuid) {
		return finishedGoodsQCService.getdataByBatchuid(batchuid);

	}
	
	@GetMapping("/finishgoodsqc/getempdetails")
	@ResponseBody
	public List<Map<String, Object>> getEmpName(@RequestParam("employeeuid") String employeeuid) {
		return finishedGoodsQCService.getdataByEmpuid(employeeuid);
		
	}
}
