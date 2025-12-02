package com.prog.controller.maintenancemanagement;

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

import com.prog.model.erp.CalibrationRecord;
import com.prog.model.erp.EquipmentMaster;
import com.prog.service.maintenancemanagement.CalibrationRecordService;

@Controller
public class CalibrationRecordController {
	@Autowired
	private CalibrationRecordService calibrationRecordService;
	
	@GetMapping("/maintenancemanagementdepartment/calibrationrecordgrid")
	public String showCalibrationRecordGrid() {
		return "MAINTENANCEMANAGEMENTDEPERTMENTgrid/CalibrationRecordGrid";
	}
	
	@GetMapping("/calibrationrecordform")
	public String viewCalibrationRecordForm(Model model) {
		model.addAttribute("cr",new CalibrationRecord());
		List<String> equipmentmasteruid=calibrationRecordService.getEquipmentDetailsById();
		model.addAttribute("equipmentmasteruid",equipmentmasteruid);
		return "MaintenanceManagementDepartment/CalibrationRecordForm";		
	}
	
	@PostMapping("/savecalibrationrecord")
	public String addCalibrationRecordData(@ModelAttribute CalibrationRecord cr) {
		calibrationRecordService.addCalibarationRecord(cr);
		return "redirect:/maintenancemanagementdepartment/calibrationrecordgrid";
	}
	
	@GetMapping("/viewcalibrationrecord")
    @ResponseBody
    public Map<String,Object> getAllCalibrationRecord(Model modedl){
    	List<String> headers = List.of("ID","Calibration Record UID", "Equipment Master UID", "Equipment Name", "Serial Number",
				"Calibration Date", "Next Due Date", "Performed By", "Status");
		
		List<Map<String, Object>> maintenancehistoryloginfolist = calibrationRecordService.getAllCalibrationRecord();

		List<Map<String, String>> maintenancehistoryloglist = new ArrayList<>();
		
		for (Map<String, Object> maintenancehistoryloginfo : maintenancehistoryloginfolist) {
			Map<String, String> row = new HashMap<>();
			row.put("ID",String.valueOf(maintenancehistoryloginfo.get("id")));
			row.put("Calibration Record UID", String.valueOf(maintenancehistoryloginfo.get("calibrationrecorduid")));
			row.put("Equipment Master UID",String.valueOf(maintenancehistoryloginfo.get("equipmentmasteruid")));
			row.put("Equipment Name", String.valueOf(maintenancehistoryloginfo.get("equipmentname")));
			row.put("Serial Number", String.valueOf(maintenancehistoryloginfo.get("serialnumber")));
			row.put("Calibration Date", String.valueOf(maintenancehistoryloginfo.get("calibrationdate")));
			row.put("Next Due Date", String.valueOf(maintenancehistoryloginfo.get("nextduedate")));
			row.put("Performed By", String.valueOf(maintenancehistoryloginfo.get("performedby")));
			row.put("Status", String.valueOf(maintenancehistoryloginfo.get("status")));
			maintenancehistoryloglist.add(row);

		}

		Map<String, Object> response = new HashMap<>();
		response.put("headers", headers);
		response.put("data", maintenancehistoryloglist);
		response.put("status", "success");
		
		return response;
 
    }

	 @GetMapping("/calibrationrecorddeleteinfo")
		public String deleteCalibrationRecord(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
		 try {
	            System.out.println("Deleting Spare Parts Inventory with ID: " + id); // DEBUG
	            calibrationRecordService.deleteCalibrationRecord(id);
	            redirectAttributes.addFlashAttribute("successMessage", "Maintenance History Log Form entry deleted successfully!");
	        } catch (Exception e) {
	            e.printStackTrace(); 
	            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete Maintenance History Log Form.");
	        }
			return "redirect:/maintenancemanagementdepartment/calibrationrecordgrid";
		}
	
	 @GetMapping("/updatecalibrationrecord/{id}")
		public String getCalibrationRecordById(@PathVariable("id") Long id, Model model) {
		 CalibrationRecord cr=calibrationRecordService.getCalibrationRecordById(id);
			model.addAttribute("records", cr);
			return "MaintenanceManagementDepartment/CalibrationRecordForm";
		} 
	 
	@PostMapping("/updatecalibrationrecord")
	public String updateCalibrationRecord(@ModelAttribute CalibrationRecord cr) {
		calibrationRecordService.updateCalibrationRecord(cr);
		return "redirect:/maintenancemanagementdepartment/calibrationrecordgrid";
	}
	
	@GetMapping("/fetchEquipmentDetailsInCalibrationRecord")
	@ResponseBody
	public EquipmentMaster getEquipmentDetailsById(String equipmentmasteruid) {
		return calibrationRecordService.getEquipmentDetails(equipmentmasteruid);
	}
}
