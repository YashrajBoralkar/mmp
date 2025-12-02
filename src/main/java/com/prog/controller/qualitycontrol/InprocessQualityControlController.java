package com.prog.controller.qualitycontrol;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prog.model.erp.InprocessQualityControl;
import com.prog.service.qualitycontrol.InprocessQualityControlService;

@Controller
public class InprocessQualityControlController {

    @Autowired
    private InprocessQualityControlService inprocessQualityControlService;

    /**
     * Displays the grid page for In-Process Quality Control records.
     *
     * @return The Thymeleaf view name for the grid page.
     */
    @GetMapping("/qualitycontrol/inprocessqualitycontrolgrid")
    public String inprocessqualitycontrolgrid() {
        return "QUALITYCONTROLgrid/InprocessQualityControlGrid";
    }

    /**
     * Displays the In-Process Quality Control form for creating a new record.
     *
     * @param model Model object to pass data to the view.
     * @return The form view.
     */
    @GetMapping("/showinprogressqualitycontrol")
    public String showQualityControlForm(Model model) {
        model.addAttribute("processQualityControl", new InprocessQualityControl());
        List<String> productuid = inprocessQualityControlService.getItemid();
        model.addAttribute("productuid", productuid);
        return "qualitycontrol/InprocessQualityControlForm";
    }

    /**
     * Saves a new In-Process Quality Control record.
     *
     * @param control The form data submitted.
     * @param model   The model object to store messages.
     * @return Redirects to the grid view after saving.
     */
    @PostMapping("/inprogressqualitycontrol/save")
    public String saveQualityControl(@ModelAttribute InprocessQualityControl control, Model model) {
        inprocessQualityControlService.saveProcessQualityControl(control);
        model.addAttribute("message", "In-Process Quality Control record saved successfully");
        model.addAttribute("successmsg", "Saved successfully");
        return "redirect:/qualitycontrol/inprocessqualitycontrolgrid";
    }

    /**
     * Provides a JSON response containing all In-Process Quality Control records
     * for display in a grid.
     *
     * @return A structured map with headers, data, and status.
     */
    @GetMapping("/viewallinprogressqualitycontrollistinfo")
    @ResponseBody
    public Map<String, Object> showlistofgoodsreceiptnote() {
        List<String> headers = List.of(
            "ID", "In-Process QC ID", "Production Order Number", "Department Name", "Product Name",
            "Inspection Date & Time", "Inspector Name", "Inspection Status", "Supervisor Approval"
        );

        List<Map<String, Object>> InprogressQualityControlInfoList = inprocessQualityControlService.getAllProcessQualityControls();
        List<Map<String, String>> InprogressQualityControlList = new ArrayList<>();

        for (Map<String, Object> info : InprogressQualityControlInfoList) {
            Map<String, String> row = new HashMap<>();
            row.put("ID", String.valueOf(info.get("id")));
            row.put("In-Process QC ID", String.valueOf(info.get("inprocessqualitycontroluid")));
            row.put("Production Order Number", String.valueOf(info.get("productionorderuid")));
            row.put("Department Name", String.valueOf(info.get("departmentname")));
            row.put("Product Name", String.valueOf(info.get("productname")));
            row.put("Inspection Date & Time", String.valueOf(info.get("inspectiondatetime")));
            row.put("Inspector Name", String.valueOf(info.get("inspectorname")));
            row.put("Inspection Status", String.valueOf(info.get("inspectionstatus")));
            row.put("Supervisor Approval", String.valueOf(info.get("supervisorapproval")));
            InprogressQualityControlList.add(row);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("headers", headers);
        response.put("data", InprogressQualityControlList);
        response.put("status", "success");

        return response;
    }

    /**
     * Loads the form for editing an existing In-Process Quality Control record.
     *
     * @param id    The ID of the record to edit.
     * @param model The model to pass the record to the view.
     * @return The form view populated with existing data.
     */
    @GetMapping("/inprogressqualitycontrol/update/{id}")
    public String editQualityControl(@PathVariable("id") Long id, Model model) {
        InprocessQualityControl control = inprocessQualityControlService.getProcessQualityControlById(id);
        model.addAttribute("processQualityControl", control);
        return "qualitycontrol/InprocessQualityControlForm";
    }

    /**
     * Updates an existing In-Process Quality Control record.
     *
     * @param control             The updated data.
     * @param redirectAttributes  Flash attributes for success messages.
     * @return Redirects to the grid view after updating.
     */
    @PostMapping("/inprogressqualitycontrol/update")
    public String updateQualityControl(@ModelAttribute InprocessQualityControl control, RedirectAttributes redirectAttributes) {
        inprocessQualityControlService.updateProcessQualityControl(control);
        redirectAttributes.addFlashAttribute("successMessage", "In-Process Quality Control record updated successfully!");
        return "redirect:/qualitycontrol/inprocessqualitycontrolgrid";
    }

    /**
     * Deletes an In-Process Quality Control record by ID.
     *
     * @param id                  The ID of the record to delete.
     * @param redirectAttributes  Flash attributes for messages.
     * @return Redirects to the grid view.
     */
    @GetMapping("/deleteinprocessqualitycontrolinfo")
    public String deleteQualityControl(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            inprocessQualityControlService.deleteProcessQualityControl(id);
            redirectAttributes.addFlashAttribute("successMessage", "In-Process Quality Control entry deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete record.");
        }
        return "redirect:/qualitycontrol/inprocessqualitycontrolgrid";
    }

    // ---------------------- FETCHING METHODS ----------------------

    /**
     * Retrieves all product UIDs for use in dropdowns or selectors.
     *
     * @return A list of product UIDs.
     */
    @GetMapping("/product-uids")
    public ResponseEntity<List<String>> getAllProductUids() {
        return ResponseEntity.ok(inprocessQualityControlService.getItemid());
    }

    /**
     * Fetches the product name by a given product UID.
     *
     * @param productuid The UID of the product.
     * @return The name of the product.
     */
    @GetMapping("/product-name/{productuid}")
    public ResponseEntity<String> getProductNameByUid(@PathVariable String productuid) {
        String productName = inprocessQualityControlService.getProductNameByUid(productuid);
        return ResponseEntity.ok(productName);
    }

    /**
     * Retrieves a list of approved FAI (First Article Inspection) UIDs
     * for a given product UID.
     *
     * @param productuid The product UID.
     * @return A list of approved FAI UIDs.
     */
    @GetMapping("/fai-uids/{productuid}")
    public ResponseEntity<List<String>> getApprovedFaiUids(@PathVariable String productuid) {
        return ResponseEntity.ok(inprocessQualityControlService.getApprovedFAIUIDsByProductUid(productuid));
    }

    /**
     * Fetches production order details (e.g., work order, planned quantity)
     * by a given FAI UID.
     *
     * @param faiuid The FAI UID.
     * @return A map of order details.
     */
    @GetMapping("/order-details/{faiuid}")
    public ResponseEntity<Map<String, String>> getOrderDetailsByFaiUid(@PathVariable String faiuid) {
        return ResponseEntity.ok(inprocessQualityControlService.getOrderDetailsByFAIUid(faiuid));
    }
}
