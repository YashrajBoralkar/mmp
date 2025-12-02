package com.prog.controller.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prog.model.erp.Employee;
import com.prog.model.erp.Rawmaterialdeliverychallan;
import com.prog.model.erp.Rawmaterialpurchaseorder;
import com.prog.service.hrms.EmployeeService;
import com.prog.service.inventory.RawmaterialDeliveryChallanService;

@Controller
public class RawmaterialDeliveryChallanController {

    @Autowired
    private RawmaterialDeliveryChallanService rawmaterialDeliveryChallanService;
    @Autowired
    private EmployeeService employeeService;
    
    @GetMapping("/inventory/rawmaterialdeliverychallangrid")
	public String showdeliverychallangrid() {
		return "INVENTORYgrid/RawMaterialDeliveryChallanGrid";
	}
	

    // Show the form for creating a new delivery challan
    @GetMapping("/rawmaterialdeliverychallan/form")
    public String showChallanForm(Model model) {
        model.addAttribute("challan", new Rawmaterialdeliverychallan());
        model.addAttribute("purchaseorderuidList", rawmaterialDeliveryChallanService.getAllPurchaseOrderUIDs());
        
        // EMPUID and Name dropdown
        List<Employee> employeeList = employeeService.getBasicEmployeeDetails();
        Map<String, String> receivedMap = new LinkedHashMap<>();
        for (Employee emp : employeeList) {
            String uid = emp.getEmployeeUID().trim();
            String firstName = emp.getFirstName() != null ? emp.getFirstName().trim() : "";
            String lastName = emp.getLastName() != null ? emp.getLastName().trim() : "";
            receivedMap.put(uid, (firstName + " " + lastName).trim());
        }
        model.addAttribute("receivedMap", receivedMap);


        return "inventory/RawMaterialDeliveryChallanForm";
    }

    // Save a new delivery challan (POST)
    @PostMapping("/rawmaterialdeliverychallan/save")
    public String saveChallan(@ModelAttribute("challan") Rawmaterialdeliverychallan challan, RedirectAttributes redirectAttributes) {
        rawmaterialDeliveryChallanService.saveDeliveryChallan(challan);
        redirectAttributes.addFlashAttribute("message", "Delivery Challan saved successfully.");
        return "redirect:/inventory/rawmaterialdeliverychallangrid";
    }    

    // Display list 
       @GetMapping("/viewallrmdeliverychallaninfo")
   	@ResponseBody
   	public Map<String, Object> showlistofviewallrmdeliverychallan() {
   	    List<String> headers = List.of(
   	        "ID","RM Delivery Challan ID","RM Purchase Order ID","Supplier ID", "Supplier Name", "Raw Materials Name","Delivery Date","Received By");
   	    
       	List<Map<String, Object>> rmdeliverychallanInfoList = rawmaterialDeliveryChallanService.getAllChallans();
   	    List<Map<String, String>> rmdeliverychallanList = new ArrayList<>();
   	    
   	    for (Map<String, Object> rmdeliverychallanInfo : rmdeliverychallanInfoList) {
   	        Map<String, String> row = new HashMap<>();
   	        row.put("ID", String.valueOf(rmdeliverychallanInfo.get("id")));
   	        row.put("RM Delivery Challan ID", String.valueOf(rmdeliverychallanInfo.get("rawmaterialdeliverychallanuid")));
   	        row.put("RM Purchase Order ID", String.valueOf(rmdeliverychallanInfo.get("rawmaterialpurchaseorderuid")));
   	        row.put("Supplier ID", String.valueOf(rmdeliverychallanInfo.get("rawmaterialsupplieruid")));
   	        row.put("Supplier Name", String.valueOf(rmdeliverychallanInfo.get("suppliername")));
   	        row.put("Raw Materials Name", String.valueOf(rmdeliverychallanInfo.get("rawmaterialname")));
   	        row.put("Delivery Date", String.valueOf(rmdeliverychallanInfo.get("deliverydate")));
   	        row.put("Received By", String.valueOf(rmdeliverychallanInfo.get("receivedby")));
   	     rmdeliverychallanList.add(row);
   	    }
   	    
   	    Map<String, Object> response = new HashMap<>();
   	    response.put("headers", headers);
   	    response.put("data", rmdeliverychallanList);
   	    response.put("status", "success");
   	    
   	    return response;
   	}
      
    
 // Show the form with pre-filled data for editing an existing challan
    @GetMapping("/rawmaterialdeliverychallan/edit/{id}")
    public String editChallan(@PathVariable("id") Long id, Model model) {
    	Rawmaterialdeliverychallan challan = rawmaterialDeliveryChallanService.getChallanById(id);
        model.addAttribute("challan", challan);
        model.addAttribute("purchaseorderuidList", rawmaterialDeliveryChallanService.getAllPurchaseOrderUIDs());
       // model.addAttribute("employeeList", rawmaterialDeliveryChallanService.getAllEmployees());
        model.addAttribute("employeeList", rawmaterialDeliveryChallanService.getAllEmployees());
        return "inventory/RawMaterialDeliveryChallanForm";
    }

    // Update an existing challan (POST)
    @PostMapping("/rawmaterialdeliverychallan/update")
    public String updateChallan(@ModelAttribute("challan") Rawmaterialdeliverychallan challan, RedirectAttributes redirectAttributes) {
        rawmaterialDeliveryChallanService.updateDeliveryChallan(challan);
        redirectAttributes.addFlashAttribute("message", "Delivery Challan updated successfully.");
        return "redirect:/inventory/rawmaterialdeliverychallangrid";
    }

    // Delete a challan by ID
    @GetMapping("/deleterawmaterialdeliverychallaninfo")
    public String deleteChallan(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        rawmaterialDeliveryChallanService.deleteDeliveryChallan(id);
        redirectAttributes.addFlashAttribute("message", "Delivery Challan deleted successfully.");
        return "redirect:/inventory/rawmaterialdeliverychallangrid";
    }

    // AJAX endpoint to get PO details by UID
    @GetMapping("/rawmaterialdeliverychallan/fetchPurchaseOrderDetails")
    @ResponseBody
    public Rawmaterialpurchaseorder fetchPurchaseOrderDetails(@RequestParam String rawmaterialpurchaseorderuid) {
        return rawmaterialDeliveryChallanService.getPurchaseOrderDetails(rawmaterialpurchaseorderuid);
    }

    @GetMapping("/rawmaterialdeliverychallan/getAllEmployees")
    @ResponseBody
    public List<Map<String, String>> getAllEmployees() {
        return rawmaterialDeliveryChallanService.getAllEmployees();
    }

}
