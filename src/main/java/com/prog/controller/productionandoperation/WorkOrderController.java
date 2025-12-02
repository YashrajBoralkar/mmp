package com.prog.controller.productionandoperation;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.prog.model.erp.WorkOrder;
import com.prog.service.hrms.EmployeeService;
import com.prog.service.productionandoperation.WorkOrderService;

@Controller
public class WorkOrderController {
	
	@Autowired
	private WorkOrderService  workOrderService;
	@Autowired
	private EmployeeService employeeService;
	
	
	@GetMapping("/productionandoperationmanagement/workordergrid")
	public String viewGrid() {
		return "ProductionAndOperationManagementgrid/WorkOrderGrid";
	}
	@GetMapping("/workorderform")
	public String showform(Model model) {
	    // Fetch products list
	    List<Map<String, Object>> products = workOrderService.getAllProducts();

	   
	    List<String> productionplanninguid = workOrderService.getProductionPlanningUId();

	    model.addAttribute("updatewo", new WorkOrder());
	    model.addAttribute("products", products); //
	    model.addAttribute("productionplanninguid", productionplanninguid);
	    
	 // EMPUID and Name dropdown
        List<Employee> employeeList = employeeService.getBasicEmployeeDetails();
        Map<String, String> reviewedbyMap = new LinkedHashMap<>();
        for (Employee emp : employeeList) {
            String uid = emp.getEmployeeUID().trim();
            String firstName = emp.getFirstName() != null ? emp.getFirstName().trim() : "";
            String lastName = emp.getLastName() != null ? emp.getLastName().trim() : "";
            reviewedbyMap.put(uid, (firstName + " " + lastName).trim());
        }
        model.addAttribute("reviewedbyMap", reviewedbyMap);

	    return "ProductionAndOperationManagement/WorkOrderForm";
	}

	@PostMapping("/saveworkorderform")
	public String saveWorkOrder(@ModelAttribute WorkOrder workorder, Model model) {
	    
		workOrderService.save(workorder);  
    	model.addAttribute("message", "Work Order data saved successfully!");
        model.addAttribute("successMessage", "Work Order has been saved successfully!");
		
        return "redirect:/productionandoperationmanagement/workordergrid";  
    }
	
	
	
	
	@GetMapping("/viewworkorderlist")
	@ResponseBody
	public Map<String, Object> viewAllWorkOrder() {
	    List<String> headers = List.of("ID","Work Order ID","Production Plan ID","Product UID",
	        "Product Name","Work Order Type","Work Order Date","Priority Level","Work Center",
	        "Machine Required","Raw Material Consumption","Labour Assigned","Completion Status",
	        "Completion Date","Reviewed By");

	    List<Map<String, Object>> workorderinfolist = workOrderService.fetchAllData();

	    Map<String, Object> response = new HashMap<>();
	    response.put("headers", headers);
	    response.put("data", workorderinfolist); // no need for manual mapping
	    response.put("status", "success");
	    return response;
	}

	@GetMapping("/workorderdeleteinfo")
	public String deleteproductionplanning(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
		 try {
	            System.out.println("Deleting Work Order with ID: " + id); // DEBUG
	            workOrderService.deleteById(id);
	            redirectAttributes.addFlashAttribute("successMessage", "Work Order entry deleted successfully!");
	        } catch (Exception e) {
	            e.printStackTrace(); // DEBUG
	            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete Work Order.");
	        }
			return "redirect:/productionandoperationmanagement/workordergrid";
	}
	

	@GetMapping("/updateworkorder/{id}")
	public String editplan(@PathVariable("id") Long id, Model model) {
		WorkOrder workorder = workOrderService.getWorkOrderById(id);
		model.addAttribute("updatewo", workorder);
		return "ProductionAndOperationManagement/WorkOrderForm";

	}

	
	@PostMapping("/updateworkorderform")
	public String updateproduction(@ModelAttribute WorkOrder workorder) {
		try {
			workOrderService.updateworkorder(workorder);
		} catch(Exception e) {
			
		}
		return "redirect:/productionandoperationmanagement/workordergrid";
	}
	

	@GetMapping("/getproductionplanningdetailbyid")
	@ResponseBody
	public Map<String, Object> getProductDetalisByProductionPlanningUID(@RequestParam("productionplanninguid") String productionplanninguid) {
	    List<Map<String, Object>> list = workOrderService.getProductDetalisByProductionPlanningUID(productionplanninguid);
	    return list.isEmpty() ? new HashMap<>() : list.get(0);
	}
	
	
	// -------- //
	 @GetMapping("/getproductionplanningbyproduct")
	    @ResponseBody
	    public List<Map<String, Object>> getProductionPlanningByProduct(@RequestParam String productuid) {
	        return workOrderService.getProductionPlanningByProductUid(productuid);
	    }

	    @GetMapping("/getapprovedrawmaterials")
	    @ResponseBody
	    public List<Map<String, Object>> getApprovedRawMaterials(@RequestParam String productionplanninguid) {
	        return workOrderService.getApprovedRawMaterials(productionplanninguid);
	    }

	   
	
		
}




