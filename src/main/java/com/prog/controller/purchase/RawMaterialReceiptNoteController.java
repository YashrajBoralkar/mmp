package com.prog.controller.purchase;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.prog.model.erp.Employee;
import com.prog.model.erp.RawMaterialReceiptNote;
import com.prog.service.hrms.EmployeeService;
import com.prog.service.purchase.RawMaterialReceiptNoteService;

import java.util.*;

@Controller
public class RawMaterialReceiptNoteController {

    @Autowired
    private RawMaterialReceiptNoteService service;
    @Autowired
    private EmployeeService employeeService;
    
    @GetMapping("/purchase/rawmaterialreceiptnotegrid")
    public String showrmrngrid() {
    	return "PURCHASEgrid/RawMaterialReceiptNoteGrid";
    }
    

   
    // ✅ Show Add Form
    @GetMapping("/rawmaterialrn/add")
    public String showForm(Model model) {
        model.addAttribute("rmrn", new RawMaterialReceiptNote());
        
        model.addAttribute("orders", service.getPurchaseOrdersWithPassedQuality()); // dropdown
        
        // EMPUID and Name dropdown
        List<Employee> employeeList = employeeService.getBasicEmployeeDetails();
        Map<String, String> receivernameMap = new LinkedHashMap<>();
        for (Employee emp : employeeList) {
            String uid = emp.getEmployeeUID().trim();
            String firstName = emp.getFirstName() != null ? emp.getFirstName().trim() : "";
            String lastName = emp.getLastName() != null ? emp.getLastName().trim() : "";
            receivernameMap.put(uid, (firstName + " " + lastName).trim());
        }
        model.addAttribute("receivernameMap", receivernameMap);

        return "purchase/RawMaterialReceiptNoteForm";
    }
    
    
 // Display list 
    @GetMapping("/viewallrmrninfo")
	@ResponseBody
	public Map<String, Object> showlistofviewallrmrn() {
	    List<String> headers = List.of( "ID","RMRN ID","RM Purchase Order ID", "Raw Material Details","Received By","Approval Status");
	    
    	List<Map<String, Object>> rmrnInfoList = service.getAllWithJoin();
	    List<Map<String, String>> rmrnList = new ArrayList<>();
	    
	    for (Map<String, Object> rmrnInfo : rmrnInfoList) {
	        Map<String, String> row = new HashMap<>();
	        row.put("ID", String.valueOf(rmrnInfo.get("id")));
	        row.put("RMRN ID", String.valueOf(rmrnInfo.get("rawmaterialreceiptnoteuid")));
	        row.put("RM Purchase Order ID", String.valueOf(rmrnInfo.get("rawmaterialpurchaseorderuid")));
	        row.put("Raw Material Details", String.valueOf(rmrnInfo.get("rawmaterialdetails")));
	        row.put("Received By", String.valueOf(rmrnInfo.get("receivername")));
	        row.put("Approval Status", String.valueOf(rmrnInfo.get("rawmaterialstatus")));
	        rmrnList.add(row);
	    }
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("headers", headers);
	    response.put("data", rmrnList);
	    response.put("status", "success");
	    
	    return response;
	}
   
 

    // ✅ Save
    @PostMapping("/rawmaterialreceiptnote/save")
    @ResponseBody
    public String saveRMRN(RawMaterialReceiptNote rmrn) {
        service.save(rmrn);
        return "success";
    }

 
    // ✅ Edit Form
    @GetMapping("/rawmaterialrn/edit/{id}")
    public String editrmrn(@PathVariable Long id, Model model) {
        model.addAttribute("rmrn", service.getById(id));
        model.addAttribute("orders", service.getPurchaseOrdersWithPassedQuality());
        return "purchase/RawMaterialReceiptNoteForm";
    }
    
 // ✅ Update
    @PostMapping("/rawmaterialreceiptnote/update")
    @ResponseBody
    public String updateRMRN(RawMaterialReceiptNote rmrn) {
        service.update(rmrn);
        return "success";
    }
    

    // ✅ Delete
    @GetMapping("/deleterawmaterialreceiptnoteinfo")
    public String delete(@RequestParam("id") Long id) {
        service.delete(id);
        return "redirect:/purchase/rawmaterialreceiptnotegrid";
    }
    
 // 🔹 API to fetch Purchase Order details (with parsed materials)
    @GetMapping("/getPoDetails/{poUid}")
    @ResponseBody
    public List<Map<String, Object>> getPoDetails(@PathVariable("poUid") String poUid) {
        return service.getRawMaterialsByPurchaseOrderUid(poUid);
    }
    
}
