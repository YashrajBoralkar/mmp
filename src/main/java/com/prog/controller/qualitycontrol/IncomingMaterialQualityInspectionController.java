package com.prog.controller.qualitycontrol;

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

import com.prog.model.erp.IncomingMaterialQualityInspection;
import com.prog.service.qualitycontrol.IncomingMaterialQualityInspectionService;


@Controller
public class IncomingMaterialQualityInspectionController {
	@Autowired
	private IncomingMaterialQualityInspectionService imqInspectorFormService;
	
	@GetMapping("/qualitycontrol/incomingmaterialqualityinspectiongrid")
    public String qualitycontrolauditgrid() {
    	return "QUALITYCONTROLgrid/IncomingMaterialQualityInspectionGrid";
    }
		
	
	@GetMapping("/showincomingmaterialqualityinspection")
	public String fetchImqInspection(Model  model) {
		model.addAttribute("imq",new IncomingMaterialQualityInspection());
		
		List<String> batchuid =imqInspectorFormService.getBatchId();
		model.addAttribute("batchuid",batchuid);
		return "qualitycontrol/IncomingMaterialQualityInspectionForm";
	}
	
	@PostMapping("/incomingmaterialqualityinspection/save")
	public String addImqInspectorFormData(IncomingMaterialQualityInspection imq,Model model) {
		 imqInspectorFormService.addImqInspectorFormData(imq);
		 model.addAttribute("Saved Successfully","Saved Successfully Imq Inspection Form");
		 return "redirect:/qualitycontrol/incomingmaterialqualityinspectiongrid";
	}
	
	
	 // Display list
    @GetMapping("/viewallincomingmaterialqualityinspectionlistinfo")
	@ResponseBody
	public Map<String, Object> showlistofgoodsreceiptnote() {
	    List<String> headers = List.of(
	        "ID", "IMQ Inspection ID","PO ID","Product Name", "Inspection Date",
	        "Inspector Name","Inspection Status","Action Taken");
	    
		List<Map<String,Object>> IncomingMaterialQualityInspectionInfoList=imqInspectorFormService.getAllImqInspectorData();
	    List<Map<String, String>> IncomingMaterialQualityInspectionList = new ArrayList<>();
	    
	    for (Map<String, Object> IncomingMaterialQualityInspectionInfo : IncomingMaterialQualityInspectionInfoList) {
	        Map<String, String> row = new HashMap<>();
	        row.put("ID", String.valueOf(IncomingMaterialQualityInspectionInfo.get("id")));
	        row.put("IMQ Inspection ID", String.valueOf(IncomingMaterialQualityInspectionInfo.get("incomingmaterialqualityinspectionuid")));
	        row.put("PO ID", String.valueOf(IncomingMaterialQualityInspectionInfo.get("rawmaterialpurchaseorderuid")));
	        row.put("Product Name", String.valueOf(IncomingMaterialQualityInspectionInfo.get("productname")));
	        row.put("Inspection Date", String.valueOf(IncomingMaterialQualityInspectionInfo.get("inspectiondate")));
	        row.put("Inspector Name", String.valueOf(IncomingMaterialQualityInspectionInfo.get("inspectorname")));
	        row.put("Inspection Status", String.valueOf(IncomingMaterialQualityInspectionInfo.get("inspectionstatus")));
	        row.put("Action Taken", String.valueOf(IncomingMaterialQualityInspectionInfo.get("actiontaken")));
	        IncomingMaterialQualityInspectionList.add(row);
	    }
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("headers", headers);
	    response.put("data", IncomingMaterialQualityInspectionList);
	    response.put("status", "success");
	    
	    return response;
	}
	
	@GetMapping("/incomingmaterialqualityinspection/update/{id}")
	public String updateImqInspectorDataById(@PathVariable("id") Long id,Model model) {
		IncomingMaterialQualityInspection imq=imqInspectorFormService.getImqInspectorDataById(id);
		model.addAttribute("imq",imq);
		return "qualitycontrol/IncomingMaterialQualityInspectionForm";
	}
	
	@PostMapping("/incomingmaterialqualityinspection/update")
	public String updateimqinspectionData(@ModelAttribute IncomingMaterialQualityInspection imq) {
		imqInspectorFormService.updateImqInspectorFormData(imq);
		return "redirect:/qualitycontrol/incomingmaterialqualityinspectiongrid";
	}	
	
	@GetMapping("/deleteincomingmaterialqualityinspectioninfo")
	public String deleteImqInspectionDataById(@RequestParam("id") Long id) {
		imqInspectorFormService.deleteImqInspectorDataById(id);
		return "redirect:/qualitycontrol/incomingmaterialqualityinspectiongrid";
	}
	

	
//FETCHING
	@GetMapping("/incomingmaterialqualityinspection/imqbatchdetails")
	@ResponseBody
	public List<Map<String, Object>> getstockDeatils(@RequestParam("batchuid")String batchuid){
		return imqInspectorFormService.getbatchDetailsbyid(batchuid);
	}
}
