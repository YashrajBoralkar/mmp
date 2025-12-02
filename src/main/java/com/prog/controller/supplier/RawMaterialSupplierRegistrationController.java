package com.prog.controller.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.prog.model.erp.RawMaterialSupplierRegistration;
import com.prog.model.erp.RawmaterialInfo;
import com.prog.service.supplier.RawMaterialSupplierRegistrationService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RawMaterialSupplierRegistrationController {

    @Autowired
    private RawMaterialSupplierRegistrationService supplierService;

    
    @GetMapping("/supplier/rawmaterialsupplierregistrationgrid")
    public String showrawmaterialsupplierregistrationgrid() {
    	return "SUPPLIERgrid/RawMaterialSupplierRegistrationGrid";
    }
    
    
    // Show registration form with material list
    @GetMapping("/rawmaterialsupplierregistration/form")
    public String showSupplierForm(Model model) {
        model.addAttribute("supplier", new RawMaterialSupplierRegistration());
        model.addAttribute("materialList", supplierService.getAllRawMaterials());
        return "supplier/RawMaterialSupplierRegistrationForm";
    }

 // Save or update supplier with document upload
    @PostMapping("/rawmaterialsupplierregistration/save")
    public String saveOrUpdateSupplier(
            @ModelAttribute("supplier") RawMaterialSupplierRegistration supplier,
            @RequestParam("document") MultipartFile file
    ) throws IOException {

        if (!file.isEmpty()) {
            supplier.setSupplierdoc(file.getBytes()); // Save the uploaded document
        } else if (supplier.getId() != null) {
            // Retain existing document if not re-uploaded during update
            byte[] existingDoc = supplierService.getSupplierByid(supplier.getId()).getSupplierdoc();
            supplier.setSupplierdoc(existingDoc);
        }

        if (supplier.getId() == null) {
            supplierService.saveSupplier(supplier);
        } else {
            supplierService.updateSupplier(supplier);
        }

        return "redirect:/supplier/rawmaterialsupplierregistrationgrid";
    }
    
    @GetMapping("/rawmaterialsupplierregistration/viewdoc/{id}")
    public ResponseEntity<byte[]> viewSupplierDoc(@PathVariable Long id) {
    	RawMaterialSupplierRegistration supplier = supplierService.getSupplierByid(id);
        byte[] doc = supplier.getSupplierdoc();

        if (doc == null) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.attachment()
            .filename("supplier_document_" + id + ".pdf")  
            .build());

        return new ResponseEntity<>(doc, headers, HttpStatus.OK);
    }

    @PostMapping("/rawmaterialsupplierregistration/update")
    public ResponseEntity<String> updateSupplier(
            @ModelAttribute("supplier") RawMaterialSupplierRegistration supplier,
            @RequestParam(value = "document", required = false) MultipartFile file
    ) throws IOException {

        if (file != null && !file.isEmpty()) {
            supplier.setSupplierdoc(file.getBytes());
        } else if (supplier.getId() != null) {
            byte[] existingDoc = supplierService.getSupplierByid(supplier.getId()).getSupplierdoc();
            supplier.setSupplierdoc(existingDoc);
        }

        supplierService.updateSupplier(supplier);

        return ResponseEntity.ok("Supplier Registration updated successfully!");
    }


    // Display all suppliers
//    @GetMapping("/rawmaterialsupplierregistration/list")
//    public String listSuppliers(Model model) {
//        model.addAttribute("suppliers", supplierService.getAllSuppliers());
//        return "supplierlist";
//    }
    

    // Display list 
       @GetMapping("/viewallrmsupplierinfo")
   	@ResponseBody
   	public Map<String, Object> showlistofviewallsupplier() {
   	    List<String> headers = List.of(
   	        "ID","Supplier ID", "Supplier Name", "Contact Person", "Mobile Number","Supplier Type","Raw Material Names","Registration Date");
   	    
       	List<Map<String, Object>> rmsupplierregistrationInfoList = supplierService.getAllSuppliers();
   	    List<Map<String, String>> rmsupplierregistrationList = new ArrayList<>();
   	    
   	    for (Map<String, Object> rmsupplierregistrationInfo : rmsupplierregistrationInfoList) {
   	        Map<String, String> row = new HashMap<>();
   	        row.put("ID", String.valueOf(rmsupplierregistrationInfo.get("id")));
   	        row.put("Supplier ID", String.valueOf(rmsupplierregistrationInfo.get("rawmaterialsupplieruid")));
   	        row.put("Supplier Name", String.valueOf(rmsupplierregistrationInfo.get("suppliername")));
   	        row.put("Contact Person", String.valueOf(rmsupplierregistrationInfo.get("contactperson")));
   	        row.put("Mobile Number", String.valueOf(rmsupplierregistrationInfo.get("mobilenumber")));
   	        row.put("Supplier Type", String.valueOf(rmsupplierregistrationInfo.get("suppliertype")));
   	        row.put("Raw Material Names", String.valueOf(rmsupplierregistrationInfo.get("rawmaterialname")));
   	        row.put("Registration Date", String.valueOf(rmsupplierregistrationInfo.get("registrationdate")));
   	        rmsupplierregistrationList.add(row);
   	    }
   	    
   	    Map<String, Object> response = new HashMap<>();
   	    response.put("headers", headers);
   	    response.put("data", rmsupplierregistrationList);
   	    response.put("status", "success");
   	    
   	    return response;
   	}

    // Load supplier for editing
    @GetMapping("/rawmaterialsupplierregistration/edit/{id}")
    public String editSupplier(@PathVariable("id") Long id, Model model) {
        model.addAttribute("supplier", supplierService.getSupplierByid(id));
        model.addAttribute("materialList", supplierService.getAllRawMaterials());
        return "supplier/RawMaterialSupplierRegistrationForm";
    }

    // Delete supplier by ID
    @GetMapping("/deleterawmaterialsupplierregistrationinfo")
    public String deleteSupplier(@RequestParam("id") Long id) {
        supplierService.deleteSupplierByid(id);
        return "redirect:/supplier/rawmaterialsupplierregistrationgrid";
    }

    // Fetch material names from selected UIDs (AJAX)
    @PostMapping("/rawmaterial/fetchMultipleByUids")
    @ResponseBody
    public List<Map<String, String>> fetchMaterialNamesByUids(@RequestBody List<String> uids) {
        List<RawmaterialInfo> materials = supplierService.getRawMaterialsByUids(uids);
        List<Map<String, String>> response = new ArrayList<>();
        for (RawmaterialInfo mat : materials) {
            Map<String, String> entry = new HashMap<>();
            entry.put("rawmaterialuid", mat.getRawmaterialuid());
            entry.put("rawmaterialname", mat.getRawmaterialname());
            response.add(entry);
        }
        return response;
    }
}

