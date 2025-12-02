package com.prog.controller.purchase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prog.Dao.purchase.RawmaterialpurchaseinvoiceDao;
import com.prog.model.erp.Employee;
import com.prog.model.erp.RawMaterialReceiptNote;
import com.prog.model.erp.Rawmaterialpurchaseinvoice;
import com.prog.service.hrms.EmployeeService;
import com.prog.service.purchase.RawmaterialpurchaseinvoiceService;



@Controller
public class RawmaterialpurchaseinvoiceController {
    
	@Autowired
	private RawmaterialpurchaseinvoiceService rawmaterialpurchaseinvoiceservice;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
    private RawmaterialpurchaseinvoiceDao rawmaterialpurchaseinvoicedao;
	
	
	
	
	
	@GetMapping("/purchase/rawmaterialpurchaseinvoicegrid")
	public String showRFQGrid() {
		return "PURCHASEGrid/RawMaterialPurchaseInvoiceGrid";
	}
	/*
	 * Display the invoice registration form.
	 * Loads all raw material purchase order UIDs and GRN UIDs for dropdowns.
	 */
	@GetMapping("/rawmaterialpurchaseinvoice")
	public String showInvoiceForm(Model model) {
	    model.addAttribute("invoice", new Rawmaterialpurchaseinvoice());

	    // Load Purchase Order UIDs
	    List<String> rawmaterialpurchaseorderuids = rawmaterialpurchaseinvoiceservice.getAllRawmaterialpurchaseorderUid();
	    model.addAttribute("rawmaterialpurchaseorderuid", rawmaterialpurchaseorderuids);

	    // Load GRN UIDs (list of only UIDs, not material details)
	   
	    List<String> grnUids = rawmaterialpurchaseinvoiceservice.getAllRawmaterialgrnUids();
	    model.addAttribute("grnUids", grnUids);
	    
	    // EMPUID and Name dropdown
        List<Employee> employeeList = employeeService.getBasicEmployeeDetails();
        Map<String, String> verifiedbyMap = new LinkedHashMap<>();
        for (Employee emp : employeeList) {
            String uid = emp.getEmployeeUID().trim();
            String firstName = emp.getFirstName() != null ? emp.getFirstName().trim() : "";
            String lastName = emp.getLastName() != null ? emp.getLastName().trim() : "";
            verifiedbyMap.put(uid, (firstName + " " + lastName).trim());
        }
        model.addAttribute("verifiedbyMap", verifiedbyMap);



	    return "purchase/RawMaterialPurchaseInvoice";
	}

	@GetMapping("/getMaterialsByGrnUid")
	@ResponseBody
	public List<Map<String, Object>> getMaterialsByGrnUid(@RequestParam("uid") String uid) {
	    return rawmaterialpurchaseinvoiceservice.getRawMaterialsByGrnUid(uid);
	}


	/*
     * Handle saving of new raw material purchase invoice.
     * Redirects to the invoice list page with a success message.
     */
	@PostMapping("/rawmaterialpurchaseinvoice/save")
	public String saveInvoice(@ModelAttribute("invoice") Rawmaterialpurchaseinvoice invoice,
	                          RedirectAttributes redirectAttributes) {
	    rawmaterialpurchaseinvoiceservice.saveInvoice(invoice);
	    redirectAttributes.addFlashAttribute("message", "Raw Material Purchase Invoice saved successfully!");
	    return "redirect:/purchase/rawmaterialpurchaseinvoicegrid"; // Redirects to the invoice list page
	}
	
//	 /*
//     * Display a list of all raw material purchase invoices.
//     */
//	@GetMapping("/rawmaterialpurchaseinvoice/list")
//	public String showInvoiceList(Model model) {
//	    List<Rawmaterialpurchaseinvoice> invoices = rawmaterialpurchaseinvoiceservice.findAll();
//	    model.addAttribute("invoices", invoices);
//	    return "PURCHASEgrid/rawmaterialpurchaseinvoicegrid";
//	}

	 
	 /*
	     * Handle deletion of a raw material invoice by ID.
	     * After deletion, redirects back to the list page.
	     */
	 @GetMapping("/deleterawmaterialpurchaseinvoiceinfo")
	 public String deleteInvoice(@RequestParam("id") Long id) {
	     rawmaterialpurchaseinvoiceservice.deleteById(id);
	     return "redirect:/purchase/rawmaterialpurchaseinvoicegrid";
	 }
	 
	 /*
     * Load the existing invoice data into the form for editing.
     */
	 @GetMapping("/rawmaterialpurchaseinvoice/edit/{id}")
	 public String editRawMaterialPurchaseInvoice(@PathVariable("id") Long id, Model model) {
	     Rawmaterialpurchaseinvoice invoice = rawmaterialpurchaseinvoiceservice.getById(id);
	     model.addAttribute("invoice", invoice);
	     model.addAttribute("grnUids", rawmaterialpurchaseinvoiceservice.getAllRawmaterialgrnUids());
	     return "purchase/RawMaterialPurchaseInvoice"; // template must exist
	 }
	 /*
	     * Handle update of an existing invoice.
	     * Redirects to the list page after saving changes.
	     */

	 @PostMapping("/rawmaterialpurchaseinvoice/update")
	 @ResponseBody
	 public ResponseEntity<String> updateInvoice(Rawmaterialpurchaseinvoice invoice) {
	     rawmaterialpurchaseinvoiceservice.update(invoice);
	     return ResponseEntity.ok("Updated successfully");
	 }

	 /*
	     * AJAX endpoint: Fetch raw material purchase order details by UID.
	     * Returns a map of related values like supplier name, material, etc.
	     */
	 @GetMapping("/getRawMaterialPurchaseOrderDetails")
	 @ResponseBody
	 public Map<String, String> getPurchaseOrderDetails(@RequestParam String uid) {
	     Map<String, String> result = rawmaterialpurchaseinvoiceservice.findOrderDetailsByUid(uid);
	     if (result == null) {
	         throw new RuntimeException("No record found for UID: " + uid);
	     }
	     return result;
	 }
	    /*
	     * ✅ AJAX endpoint: Fetch list of raw materials (name and quantity)
	     * associated with a specific Raw Material Purchase Order UID.
	     * This is used to populate the invoice table dynamically.
	     */
	    @GetMapping("/getRawMaterialsByPurchaseOrderUid")
	    @ResponseBody
	    public List<Map<String,Object>> getRawMaterialsByPurchaseOrderUid(@RequestParam("uid") String uid) {
	        // Directly call the DAO method (since you're not using a service layer)
	        return rawmaterialpurchaseinvoicedao.fetchMaterialsByPurchaseOrderUid(uid);
	    }

	 // 🔹 API to fetch Purchase Order details (with parsed materials)
	    @GetMapping("/getReceiptNoteDetails")
	    @ResponseBody
	    public List<Map<String, Object>> getReceiptNoteDetails(@RequestParam("grnuid") String grnUid) {
	        return rawmaterialpurchaseinvoiceservice.getRawMaterialsByReceiptNoteUid(grnUid);
	    }

	    
	 // Display list 
	    @GetMapping("/viewrawmaterialpurchaseinvoice")
		@ResponseBody
		public Map<String, Object> showlistofviewallpurchaseinvoice() {
		    List<String> headers = List.of(
		        "ID","RM Purchase Invoice ID","RM Purchase Order ID","RM Supplier Name", "Supplier Name", "Raw Material Details","Invoice Date","Total Amount");
		    
	    	List<Map<String, Object>> purchaseinvoiceInfoList = rawmaterialpurchaseinvoiceservice.findAll();
		    List<Map<String, String>> purchaseinvoiceList = new ArrayList<>();
		    
		    for (Map<String, Object> purchaseinvoiceInfo : purchaseinvoiceInfoList) {
		        Map<String, String> row = new HashMap<>();
		        row.put("ID", String.valueOf(purchaseinvoiceInfo.get("id")));
		        row.put("RM Purchase Invoice ID", String.valueOf(purchaseinvoiceInfo.get("rawmaterialpurchaseinvoiceuid")));
		        row.put("RM Purchase Order ID", String.valueOf(purchaseinvoiceInfo.get("rawmaterialpurchaseorderuid")));
		        row.put("RM Supplier Name", String.valueOf(purchaseinvoiceInfo.get("suppliername")));
		        row.put("Raw Material Details", String.valueOf(purchaseinvoiceInfo.get("materialdetails")));
		        row.put("Invoice Date", String.valueOf(purchaseinvoiceInfo.get("invoicedate")));
		        row.put("Total Amount", String.valueOf(purchaseinvoiceInfo.get("totalamount")));
		        purchaseinvoiceList.add(row);
		    }
		    
		    Map<String, Object> response = new HashMap<>();
		    response.put("headers", headers);
		    response.put("data", purchaseinvoiceList);
		    response.put("status", "success");
		    
		    return response;
		}
	   
}
