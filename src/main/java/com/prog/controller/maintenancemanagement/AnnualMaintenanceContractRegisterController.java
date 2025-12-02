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

import com.prog.model.erp.AnnualMaintenanceContractRegister;
import com.prog.model.erp.EquipmentMaster;
import com.prog.service.maintenancemanagement.AnnualMaintenanceContractRegisterService;

@Controller
public class AnnualMaintenanceContractRegisterController {
	@Autowired
	private AnnualMaintenanceContractRegisterService annualMaintenanceContractRegisterService;
	
	
	@GetMapping("/maintenancemanagementdepartment/annualmaintenancecontractregistergrid")
	public String viewAnnualMaintenanceContractRegister() {
		return "MAINTENANCEMANAGEMENTDEPERTMENTgrid/AnnualMaintenanceContractRegisterGrid";
	}
	
	@GetMapping("/annualmaintenancecontractregisterform")
	public String showAnnualMaintenanceContractRegisterGrid(Model model) {
		model.addAttribute("mhj",new AnnualMaintenanceContractRegister());
		List<String> equipmentmasteruid=annualMaintenanceContractRegisterService.getEquipmentDetailsById();
		model.addAttribute("equipmentmasteruid",equipmentmasteruid);
		
		return "MaintenanceManagementDepartment/AnnualMaintenanceContractRegister";
	}
	
	@PostMapping("SaveAnnualMaintenanceContractRegister")
	public String addAnnualMaintenanceContractRegisterData(@ModelAttribute AnnualMaintenanceContractRegister amc) {
		annualMaintenanceContractRegisterService.addAnnualMaintenanceContractRegisterData(amc);
		return "redirect:/maintenancemanagementdepartment/annualmaintenancecontractregistergrid";
	}
	
	@GetMapping("/viewannualmaintenancecontractregister")
    @ResponseBody
    public Map<String,Object> getAnnualMaintenanceContractRegisterList(Model modedl){
    	List<String> headers = List.of("ID","Annual Maintenance Contract Form UID", "Vendor Name",
				 "Start Date", "End Date","Equipment Master UID", "Equipment Name","Terms and Conditions","Contact Person");
		
		List<Map<String, Object>> annualmaintenancecontractregisterinfolist = annualMaintenanceContractRegisterService.getAnnualMaintenanceContractRegisterData();

		List<Map<String, String>> annualmaintenancecontractregisterlist = new ArrayList<>();
		
		for (Map<String, Object> annualmaintenancecontractregisterinfo : annualmaintenancecontractregisterinfolist) {
			Map<String, String> row = new HashMap<>();
			row.put("ID",String.valueOf(annualmaintenancecontractregisterinfo.get("id")));
			row.put("Annual Maintenance Contract Form UID", String.valueOf(annualmaintenancecontractregisterinfo.get("annualmaintenancecontractuid")));
			row.put("Vendor Name", String.valueOf(annualmaintenancecontractregisterinfo.get("vendorname")));
			row.put("Start Date", String.valueOf(annualmaintenancecontractregisterinfo.get("amcstartdate")));
			row.put("End Date", String.valueOf(annualmaintenancecontractregisterinfo.get("amcenddate")));
			row.put("Equipment Master UID",String.valueOf(annualmaintenancecontractregisterinfo.get("equipmentmasteruid")));
			row.put("Equipment Name", String.valueOf(annualmaintenancecontractregisterinfo.get("equipmentname")));
			row.put("Terms and Conditions", String.valueOf(annualmaintenancecontractregisterinfo.get("termsandcondition")));
			row.put("Contact Person", String.valueOf(annualmaintenancecontractregisterinfo.get("contactperson")));

			
			annualmaintenancecontractregisterlist.add(row);

		}

		Map<String, Object> response = new HashMap<>();
		response.put("headers", headers);
		response.put("data", annualmaintenancecontractregisterlist);
		response.put("status", "success");
		
		return response;
 
    }

	 @GetMapping("/annualmaintenancecontractregisterdeleteinfo")
		public String deleteAnnualMaintenanceContractRegisterById(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
		 try {
	            System.out.println("Deleting Spare Parts Inventory with ID: " + id); // DEBUG
	            annualMaintenanceContractRegisterService.deleteAnnualMaintenanceContractRegisterById(id);
	            redirectAttributes.addFlashAttribute("successMessage", "Maintenance History Log Form entry deleted successfully!");
	        } catch (Exception e) {
	            e.printStackTrace(); 
	            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete Maintenance History Log Form.");
	        }
			return "redirect:/maintenancemanagementdepartment/annualmaintenancecontractregistergrid";
		}
	
	 @GetMapping("/updateannualmaintenancecontractregister/{id}")
		public String getAnnualMaintenanceContractRegisterById(@PathVariable("id") Long id, Model model) {
		 AnnualMaintenanceContractRegister amc=annualMaintenanceContractRegisterService.getAnnualMaintenanceContractRegisterById(id);
			model.addAttribute("updateamc", amc);
			return "MaintenanceManagementDepartment/AnnualMaintenanceContractRegister";
		} 
	 
	@PostMapping("/updateannualmaintenancecontractregister")
	public String updategetAnnualMaintenanceContractRegister(@ModelAttribute AnnualMaintenanceContractRegister amc) {
		annualMaintenanceContractRegisterService.updategetAnnualMaintenanceContractRegister(amc);
		return "redirect:/maintenancemanagementdepartment/annualmaintenancecontractregistergrid";
	}
 
	@GetMapping("/fetchEquipmentDetailsInAnnualMaintenanceContractRegister")
	@ResponseBody
	public EquipmentMaster getEquipmentDetailsById(String equipmentmasteruid) {
		return annualMaintenanceContractRegisterService.getEquipmentDetails(equipmentmasteruid);
	}
}
