package com.prog.controller.productionandoperation;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
import com.prog.model.erp.ProductionShiftSchedule;
import com.prog.service.hrms.EmployeeService;
import com.prog.service.productionandoperation.ProductionShiftScheduleService;

@Controller
public class ProductionShiftScheduleController {
	@Autowired
	private ProductionShiftScheduleService productionShiftScheduleService;
	@Autowired 
	private EmployeeService  employeeService;
	
	
	@GetMapping("/productionandoperationmanagement/productionshiftschedulegrid")
	public String viewgrid() {
		return "ProductionAndOperationManagementgrid/ProductionShiftScheduleGrid";
	}
	
	@GetMapping("/productionshiftscheduling")
	public String showForm(Model model) {
	    model.addAttribute("pss", new ProductionShiftSchedule());

	    // Employee batch UIDs
	    List<String> empbatchuid = productionShiftScheduleService.fetchEmployeeBatchUIds();
	    model.addAttribute("empbatchuid", empbatchuid);

	    // Employee UIDs for dropdown
	    List<Map<String, Object>> employeeuid = productionShiftScheduleService.fetchEmployeeUIds();
	    model.addAttribute("employeeuid", employeeuid);

	    // Fetch supervisor UID + Full Name
	    // supervisor list
	    List<Employee> employeeList = employeeService.getBasicEmployeeDetails();
	    Map<String, String> supervisorMap = new LinkedHashMap<>();
	    for (Employee emp : employeeList) {
	        String firstName = emp.getFirstName() != null ? emp.getFirstName() : "";
	        String lastName = emp.getLastName() != null ? emp.getLastName() : "";
	        supervisorMap.put(emp.getEmployeeUID(), (firstName + " " + lastName).trim());
	    }

	    model.addAttribute("supervisorMap", supervisorMap);

	    // Production planning UID
	    List<String> productionplanninguid = productionShiftScheduleService.getProductionPlanningUID();
	    model.addAttribute("productionplanninguid", productionplanninguid);

	    return "ProductionAndOperationManagement/ProductionShiftSchedule";
	}


	@PostMapping("/saveproductionshiftscheduleform")
	public String submitShiftschedule(@ModelAttribute ProductionShiftSchedule shiftschedule) {
	    if (shiftschedule.getSupervisoruid() != null) {
	        Employee emp = employeeService.getEdetailsByuid(shiftschedule.getSupervisoruid());
	        if (emp != null) {
	            String firstName = emp.getFirstName() != null ? emp.getFirstName() : "";
	            String lastName = emp.getLastName() != null ? emp.getLastName() : "";
	            String fullName = (firstName + " " + lastName).trim();

	            // 👇 Save "UID - FullName"
	            shiftschedule.setSupervisorname(shiftschedule.getSupervisoruid() + " - " + fullName);
	        }
	    }
	    productionShiftScheduleService.saveShiftSchedule(shiftschedule);
	    return "redirect:/productionandoperationmanagement/productionshiftschedulegrid";
	}

	    
	@GetMapping("/viewproductionshiftschedulelist")
	@ResponseBody
	public Map<String, Object> getshipftViewList(Model model){
		List<String> headers = List.of("ID","Production Shift Scheduling ID","Production Department","Production Shift Date","Shift Start Time","Shift End Time","Total Shift Duration",
								"Production Planning uid","Product UID","Employee UID","Employee Names","Workstation","Break Type","Break Start Time",
								"Break End Time","Total Shift Count Per Day","Supervisor Name");

		List<Map<String, Object>> productionshiftplanninginfolist = productionShiftScheduleService.getAllShiftSchedules();

		List<Map<String, String>> productionshiftplanninglist = new ArrayList<>();
		
		for (Map<String, Object> productionshiftplanninginfo : productionshiftplanninginfolist) {
			Map<String, String> row = new HashMap<>();
			row.put("ID",String.valueOf(productionshiftplanninginfo.get("id")));
			row.put("Production Shift Scheduling ID",String.valueOf(productionshiftplanninginfo.get("productionscheduleuid")));
			row.put("Production Department",String.valueOf(productionshiftplanninginfo.get("productiondepartment")));
			row.put("Production Shift Date", String.valueOf(productionshiftplanninginfo.get("shiftdate")));
			row.put("Shift Start Time", String.valueOf(productionshiftplanninginfo.get("shiftstarttime")));
			row.put("Shift End Time",String.valueOf(productionshiftplanninginfo.get("shiftendtime")));
			row.put("Total Shift Duration", String.valueOf(productionshiftplanninginfo.get("totalshiftduration")));
			row.put("Production Planning uid", String.valueOf(productionshiftplanninginfo.get("productionplanninguid")));
			row.put("Product UID", String.valueOf(productionshiftplanninginfo.get("productuid")));
			row.put("empbatchuid", String.valueOf(productionshiftplanninginfo.get("empbatchuid")));
			row.put("batchname", String.valueOf(productionshiftplanninginfo.get("batchname")));
			row.put("employeeuid", String.valueOf(productionshiftplanninginfo.get("employeeuid")));

			row.put("emplyeenames", String.valueOf(productionshiftplanninginfo.get("emplyeenames")));
			row.put("Workstation", String.valueOf(productionshiftplanninginfo.get("workstation")));
			row.put("Break Type", String.valueOf(productionshiftplanninginfo.get("breaktiming")));
			row.put("Break Start Time", String.valueOf(productionshiftplanninginfo.get("breakstarttime")));
			row.put("Break End Time", String.valueOf(productionshiftplanninginfo.get("breakendtime")));
			row.put("Total Shift Count Per Day", String.valueOf(productionshiftplanninginfo.get("totalshiftcountperday")));
			row.put("Supervisor Name", String.valueOf(productionshiftplanninginfo.get("supervisorname")));
			productionshiftplanninglist.add(row);
		}

		Map<String, Object> response = new HashMap<>();
		response.put("headers", headers);
		response.put("data", productionshiftplanninglist);
		response.put("status", "success");
		
		return response;
	}
	@GetMapping("/productionshiftscheduledeleteinfo")
	public String deleteShiftSchedule(@RequestParam("id") Long id,RedirectAttributes redirectAttributes) {
		 try {
	            System.out.println("Deleting Work Order with ID: " + id); // DEBUG
	            productionShiftScheduleService.deleteShiftSchedule(id);
	            redirectAttributes.addFlashAttribute("successMessage", "Shift Schedule Planning entry deleted successfully!");
	        } catch (Exception e) {
	            e.printStackTrace(); // DEBUG
	            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete Shift Schedule Planning.");
	        }
		return "redirect:/productionandoperationmanagement/productionshiftschedulegrid";
	}
	
	@GetMapping("/updateproductionshiftschedule/{id}")
	public String getShiftScheduleById(@PathVariable("id") Long id, Model model) {
	    ProductionShiftSchedule pss = productionShiftScheduleService.getShiftScheduleById(id);
	    model.addAttribute("shift", pss);

	    // Add the same dropdown data as in showForm()
	    List<String> empbatchuid = productionShiftScheduleService.fetchEmployeeBatchUIds();
	    model.addAttribute("empbatchuid", empbatchuid);

	    List<Map<String, Object>> employeeuid = productionShiftScheduleService.fetchEmployeeUIds();
	    model.addAttribute("employeeuid", employeeuid);

	    // supervisor list
	    List<Employee> employeeList = employeeService.getBasicEmployeeDetails();
	    Map<String, String> supervisorMap = new LinkedHashMap<>();
	    for (Employee emp : employeeList) {
	        String firstName = emp.getFirstName() != null ? emp.getFirstName() : "";
	        String lastName = emp.getLastName() != null ? emp.getLastName() : "";
	        supervisorMap.put(emp.getEmployeeUID(), (firstName + " " + lastName).trim());
	    }

	    model.addAttribute("supervisorMap", supervisorMap);
	    List<String> productionplanninguid = productionShiftScheduleService.getProductionPlanningUID();
	    model.addAttribute("productionplanninguid", productionplanninguid);

	    return "ProductionAndOperationManagement/ProductionShiftSchedule";
	}

	
	@PostMapping("/updateproductionshiftschedule")
	public String updateShiftSchedule(@ModelAttribute ProductionShiftSchedule pss) {
	    try {
	        if (pss.getSupervisoruid() != null) {
		        Employee emp = employeeService.getEdetailsByuid(pss.getSupervisoruid());
	            if (emp != null) {
	                String firstName = emp.getFirstName() != null ? emp.getFirstName() : "";
	                String lastName = emp.getLastName() != null ? emp.getLastName() : "";
	                String fullName = (firstName + " " + lastName).trim();

	                // 👇 Save "UID - FullName"
	                pss.setSupervisorname(pss.getSupervisoruid() + " - " + fullName);
	            }
	        }
	        productionShiftScheduleService.updateShiftSchedule(pss);
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    return "redirect:/productionandoperationmanagement/productionshiftschedulegrid";
	}

	//Data fetch from employee details form by using multiple employeeuid
	@GetMapping("/getemployeedetailsbymultipleids")
	@ResponseBody
	public List<Map<String, Object>> getDataByMultipleEmployeeUids(@RequestParam("empbatchuid") List<String> empbatchuid) {
	    return productionShiftScheduleService.getDataByMultipleEmployeeUids(empbatchuid);
	}

	//Data fetch from production planning form by using productionplanninguid
	@GetMapping("/fetchproductionplanningdetailsbyid")
	@ResponseBody
	public List<Map<String, Object>> getProductDetalisByProductionPlanningUID(@RequestParam("productionplanninguid") String productionplanninguid) {
	    
		return productionShiftScheduleService.getProductDetalisByProductionPlanningUID(productionplanninguid);
	}

	@GetMapping("/getbatchnamebyempbatchuid")
	@ResponseBody
	public List<String> getBatchNameByEmpBatchUid(@RequestParam("empbatchuid") String empbatchuid) {
	    return productionShiftScheduleService.getBatchNameByEmpBatchUid(empbatchuid);
	}


//	@GetMapping("/getfullnamebyemployeeuid")
//	@ResponseBody
//	public List<String> getfullnameByEmpUid(@RequestParam("employeeuid") String employeeuid) {
//	    return productionShiftScheduleService.fetchEmployeeUIds(employeeuid);
//	}
//	@GetMapping("/getallemployeenames")
//	@ResponseBody
//	public List<Map<String, String>> getAllEmployeeNames() {
//	    return productionShiftScheduleService.getEmployeeNames(); // call the service layer
//	}
	
//	
	// ✅ In ProductionShiftScheduleController.java
	@GetMapping("/getfullnamebyemployeeuid")
	@ResponseBody
	public List<Map<String, Object>> getFullNameByEmployeeUid(@RequestParam("employeeuid") String employeeuid) {
	    return productionShiftScheduleService.getFullNameByEmployeeUid(employeeuid);
	}
	
}




