package com.prog.controller.hrms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.prog.model.erp.EmployeeBatchCreation;
import com.prog.service.hrms.EmployeeBatchCreationService;

import java.util.*;

@Controller
public class EmployeeBatchCreationController {

    @Autowired
    private EmployeeBatchCreationService batchService;

    // Grid page
    @GetMapping("/hrms/employeebatchcreationgrid")
    public String viewGrid() {
        return "HRMSgrid/EmployeeBatchCreationGrid";
    }

    // Form page
    @GetMapping("/empbatch/form")
    public String createBatchPage(Model model) {
        return "hrms/EmployeeBatchCreation";
    }

    // Save new batch
    @PostMapping("/empbatch/save")
    @ResponseBody
    public ResponseEntity<String> saveBatch(@ModelAttribute EmployeeBatchCreation empbatch) {
        batchService.saveBatch(empbatch);
        return ResponseEntity.ok("Saved successfully");
    }

    // Update existing batch
    @PostMapping("/empbatch/update")
    @ResponseBody
    public ResponseEntity<String> updateBatch(@ModelAttribute EmployeeBatchCreation empbatch) {
        batchService.updateBatch(empbatch);
        return ResponseEntity.ok("Updated successfully");
    }

    // Get batch by ID (for edit mode AJAX)
    @GetMapping("/batch/get/{id}")
    @ResponseBody
    public EmployeeBatchCreation getBatchById(@PathVariable Long id) {
        return batchService.getBatchById(id);
    }

    @GetMapping("/empbatchupdate/{id}")
    public String editBatch(@PathVariable Long id, Model model) {
        EmployeeBatchCreation empbatch = batchService.getBatchById(id);
        model.addAttribute("empbatch", empbatch);
        return "hrms/EmployeeBatchCreation"; // reuse the same form
    }

    // Delete batch
    @GetMapping("/deleteempbatchinfo")
    public String deleteBatch(@RequestParam("id") Long id) {
        batchService.deleteBatchById(id);
        return "redirect:/hrms/employeebatchcreationgrid";
    }

    // Fetch all batches for grid
    @GetMapping("/viewallempbatchinfo")
    @ResponseBody
    public Map<String, Object> showAllBatches() {
        List<String> headers = List.of(
            "ID", "EMP Batch ID", "Batch Name", "Department Name",
            "Selected Employee IDs", "Selected Employee Names", "Batch Status"
        );

        List<Map<String, Object>> batchList = batchService.getAllBatches();
        List<Map<String, String>> batchData = new ArrayList<>();

        for (Map<String, Object> info : batchList) {
            Map<String, String> row = new HashMap<>();
            row.put("ID", String.valueOf(info.get("id")));
            row.put("EMP Batch ID", String.valueOf(info.get("empbatchuid")));
            row.put("Batch Name", String.valueOf(info.get("batchname")));
            row.put("Department Name", String.valueOf(info.get("departmentname")));
            row.put("Selected Employee IDs", String.valueOf(info.get("employeeuid")));
            row.put("Selected Employee Names", String.valueOf(info.get("employeename")));
            row.put("Batch Status", String.valueOf(info.get("status")));
            batchData.add(row);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("headers", headers);
        response.put("data", batchData);
        response.put("status", "success");
        return response;
    }

    // Get all departments
    @GetMapping("/batch/departments")
    @ResponseBody
    public List<Map<String, Object>> getDepartments() {
        return batchService.getAllDepartments();
    }

    // Get employees by department
    @GetMapping("/batch/employees-by-dept")
    @ResponseBody
    public List<Map<String, Object>> getEmployeesByDepartment(@RequestParam String departmentuid) {
        return batchService.getEmployeesByDepartment(departmentuid);
    }

    // Sync department names (utility)
    @PostMapping("/sync-department-names")
    public ResponseEntity<String> syncDepartmentNames() {
        int updatedRows = batchService.syncEmployeeDepartmentNames();
        return ResponseEntity.ok("Updated department names for " + updatedRows + " employees");
    }
}
