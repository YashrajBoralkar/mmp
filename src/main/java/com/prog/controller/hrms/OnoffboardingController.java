package com.prog.controller.hrms;

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

import com.prog.model.erp.Employee;
import com.prog.model.erp.Offboarding;
import com.prog.model.erp.Onboarding;
import com.prog.service.hrms.EmployeeService;
import com.prog.service.hrms.OffboardingService;
import com.prog.service.hrms.OnboardingService;
@Controller
public class OnoffboardingController {
	
	 @Autowired
	    private EmployeeService employeeService;
	    
	
	@GetMapping("/hrms/Onoffboarding")
	public String viewgrid() {
		return "HRMSgrid/Onoffboardinggrid";
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//ONBoarding Controller
	
	 @Autowired
	  private OnboardingService onboardingservice;
	
	  @GetMapping("/viewform")
	   public String showonboarding(Model model) 
	   {
		   model.addAttribute("onboarding", new Onboarding());
		   List<String> employeeuid = employeeService.getAlleDetails();
	        model.addAttribute("employeeuid",employeeuid);
	        return "hrms/onboardingform";
		   
	   }
	   
	   @PostMapping("/saveform")
	   public  String saveonboarding(@RequestParam("employeeuid") String employeeuid,@ModelAttribute Onboarding onboarding ,Model model) 
	   {
		   onboarding.setEmployeeuid(employeeuid);
		   onboardingservice.saveonboarding(onboarding);
		   return "redirect:/hrms/Onoffboarding";
		   
	   }
	   
	   @GetMapping("/viewonboardinglist")
	    @ResponseBody
	    public Map<String, Object> getAllOnboardinglist() {        
	        List<String> headers = List.of("ID", "Employee ID","Employee First Name", "Employee Last Name", "Department", "Designation","Date of Joining", "Manager Name", "Approved By");
	        
	        List<Map<String, Object>> onboardingList = onboardingservice.getAllonboarding();
	        
	        List<Map<String, String>> OnboardingList = new ArrayList<>();
	        
	        for (Map<String, Object> onboardingInfo : onboardingList) {
	            Map<String, String> row = new HashMap<>();
	            row.put("ID", String.valueOf(onboardingInfo.get("id")));
	            row.put("Employee ID", String.valueOf(onboardingInfo.get("employeeuid")));
	            row.put("Employee First Name", String.valueOf(onboardingInfo.get("first_name")));
	            row.put("Employee Last Name", String.valueOf(onboardingInfo.get("last_name")));
	            row.put("Department", String.valueOf(onboardingInfo.get("departmentname")));
	            row.put("Designation", String.valueOf(onboardingInfo.get("designation")));
	            row.put("Date of Joining", String.valueOf(onboardingInfo.get("dateofjoining")));
	            row.put("Manager Name", String.valueOf(onboardingInfo.get("managername")));
	            row.put("Approved By", String.valueOf(onboardingInfo.get("approvedby")));

	            OnboardingList.add(row);
	        }
	        
	        Map<String, Object> response = new HashMap<>();
	        response.put("headers", headers);
	        response.put("data", OnboardingList);
	        response.put("status", "success");
	        
	        return response;
	    }
	   
	   
	   @GetMapping("/updateonoffboarding/{id}")
	   public String editOnboarding(@PathVariable("id") Long id, Model model) {
	       Onboarding onboarding = onboardingservice.getonboardingbyid(id);
	       model.addAttribute("onboarding", onboarding);
	       return "hrms/onboardingform";
	   
	   }
	   
	   @PostMapping("/updateonboarding")
	   public String updateonboarding(@ModelAttribute Onboarding onboarding,RedirectAttributes redirectAttributes)
	   {
		   onboardingservice.updateonboarding(onboarding);
		   return "redirect:/hrms/Onoffboarding";
	   }
	   
	   @GetMapping("/deleteonboardinginfo")
	    public String deleteonboarding(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) 
	    {
	        onboardingservice.deleteonboarding(id);
	        redirectAttributes.addFlashAttribute("successMessage", "Stock Level entry deleted successfully!");
	        return "redirect:/hrms/Onoffboarding";
	    }
	   
	   //FETCHING
	   @GetMapping("/onboarding/edetails")
	    @ResponseBody
	    public Employee getEdetails(@RequestParam("employeeuid") String employeeuid){
	    	return employeeService.getEdetailsByuid(employeeuid);
	    }
	   
	   
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	   
	   //OFFBoarding Controller
	   
	   @Autowired
		private OffboardingService offboardingservice;
		
	   @GetMapping("/viewoffboarding")
	   public String showoffboarding(Model model) 
	   {
		   List<String> employeeuid = employeeService.getAlleDetails();
	       model.addAttribute("employeeuid",employeeuid);
	       model.addAttribute("offboarding", new Offboarding());
		   return "hrms/offboardingform";
		   
	   }
	   
	@PostMapping("/saveoffboarding")
	public String saveOffboarding(@RequestParam("employeeuid") String employeeuid,@ModelAttribute Offboarding offboarding, Model model) {
		offboarding.setEmployeeuid(employeeuid);
	    offboardingservice.saveOffboarding(offboarding);
	   return "redirect:/hrms/Onoffboarding";
	}

	   
	   @GetMapping("/offboardinglist")
	    @ResponseBody
	    public Map<String, Object> getAllOffboardinglist() {        
	        List<String> headers = List.of("ID", "Employee ID","Employee First Name", "Employee Last Name", "Department", "Last Working Day", "Notice Period Details", "Start Date","End Date","Served Notice Period","Final Settlement Status");
	        
	        List<Map<String, Object>> offboardingList =  offboardingservice.getAllBoarding();
	        
	        List<Map<String, String>> OffboardingList = new ArrayList<>();
	        
	        for (Map<String, Object> offboardingInfo : offboardingList) {
	            Map<String, String> row = new HashMap<>();
	            row.put("ID", String.valueOf(offboardingInfo.get("id")));
	            row.put("Employee ID", String.valueOf(offboardingInfo.get("employeeuid")));
	            row.put("Employee First Name", String.valueOf(offboardingInfo.get("first_name")));
	            row.put("Employee Last Name", String.valueOf(offboardingInfo.get("last_name")));
	            row.put("Department", String.valueOf(offboardingInfo.get("departmentname")));
	            row.put("Last Working Day", String.valueOf(offboardingInfo.get("last_working_day")));
	            row.put("Notice Period Details", String.valueOf(offboardingInfo.get("notice_period_details")));
	            row.put("Start Date", String.valueOf(offboardingInfo.get("start_date")));
	            row.put("End Date", String.valueOf(offboardingInfo.get("end_date")));
	            row.put("Served Notice Period", String.valueOf(offboardingInfo.get("served_notice_period")));
	            row.put("Final Settlement Status", String.valueOf(offboardingInfo.get("final_settlement_status")));

	            OffboardingList.add(row);
	        }
	        
	        Map<String, Object> response = new HashMap<>();
	        response.put("headers", headers);
	        response.put("data", OffboardingList);
	        response.put("status", "success");
	        
	        return response;
	    }

	   @GetMapping("/updateoffboarding/{id}")
	   public String editOffboarding(@PathVariable("id") Long id, Model model) {
		   Offboarding offboarding = offboardingservice.getoffboardingbyid(id);
	       model.addAttribute("offboarding", offboarding);
	       return "hrms/offboardingform";
	   
	   }
	   
	   @PostMapping("/updateoffboarding")
	   public String updateoffnboarding(@ModelAttribute Offboarding offboarding,RedirectAttributes redirectAttributes)
	   {
		   offboardingservice.updateoffboarding(offboarding);
		   return "redirect:/hrms/Onoffboarding";
	   }
	   
	   @GetMapping("/deleteoffboardinginfo")
	    public String deleteOffboarding(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) 
	    {
	        offboardingservice.deleteonboarding(id);
	        return "redirect:/hrms/Onoffboarding";
	    }
	   
	 //FETCHING
	   @GetMapping("/offboarding/edetails")
	    @ResponseBody
	    public Employee getempdetails(@RequestParam("employeeuid") String employeeuid){
	    	return employeeService.getEdetailsByuid(employeeuid);
	    }
	   
	   
	   
}
