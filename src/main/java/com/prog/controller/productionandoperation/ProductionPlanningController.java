package com.prog.controller.productionandoperation;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prog.model.erp.productionplanning;
import com.prog.service.inventory.ProductInfoService;
import com.prog.service.productionandoperation.ProductionPlanningService;


@Controller
public class ProductionPlanningController {

    @Autowired
    private ProductionPlanningService productionplanningservice;
    @Autowired
    private ProductInfoService productinfoservice;

    
    @GetMapping("/productionandoperationmanagement/productionplanninggrid")
	public String viewgrid() {
	    return "ProductionAndOperationManagementgrid/ProductionPlanninggrid";
	}
    
 // ✅ Show the production planning form
    @GetMapping("/productionplanningform")
    public String showForm(Model model) {
		List<String> productuid = productinfoservice.getAllProductuids();
        model.addAttribute("productuid", productuid);// send to view
        model.addAttribute("productionplanning", new productionplanning());// empty form model
        return "ProductionAndOperationManagement/productionplanning";
    }
    
    // ✅ Save production plan (list of materials for one plan)
    @PostMapping("/productionplanning/save")
    public ResponseEntity<String> savePlan(@RequestBody List<productionplanning> plans) {
        for (productionplanning plan : plans) {
            productionplanningservice.saveplan(plan);
        }
        return ResponseEntity.ok("Saved");
    }

  
    @GetMapping("/viewproductionplanninglist")
	@ResponseBody
	public Map<String, Object> viewAllProductionPlanning(Model model) {
		List<String> headers = List.of("Production Planning ID", "Product ID", "Product Name", "Production Type",
				"Start Date", "End Date", "Raw Materials Details", "Cost Estimation");

		List<Map<String, Object>> productionplanninginfolist = productionplanningservice.getplanlist();

		List<Map<String, String>> productionplanninglist = new ArrayList<>();

		for (Map<String, Object> productionplanninginfo : productionplanninginfolist) {
			Map<String, String> row = new HashMap<>();
			row.put("Production Planning ID", String.valueOf(productionplanninginfo.get("productionplanninguid")));
			row.put("Product ID", String.valueOf(productionplanninginfo.get("productuid")));
			row.put("Product Name", String.valueOf(productionplanninginfo.get("productname")));
			row.put("Production Type", String.valueOf(	productionplanninginfo.get("productiontype")));
			row.put("Start Date", String.valueOf(productionplanninginfo.get("productionstartdate")));
			row.put("End Date", String.valueOf(productionplanninginfo.get("productionenddate")));
			row.put("Raw Materials Details", String.valueOf(productionplanninginfo.get("materialDetails")));
			row.put("Cost Estimation", String.valueOf(productionplanninginfo.get("productioncostestimation")));
			productionplanninglist.add(row);
		}
		Map<String, Object> response = new HashMap<>();
		response.put("headers", headers);
		response.put("data", productionplanninglist);
		response.put("status", "success");
		return response;
	}

 // ✅ Delete a production plan by UID
    @GetMapping("/deleteproductionplanninginfo")
    public String deleteplan(@RequestParam("productionplanninguid") String productionplanninguid) {
        productionplanningservice.deleteplan(productionplanninguid);
        return "redirect:/productionandoperationmanagement/productionplanninggrid";
    }

// // 🔁 (Legacy - not used by JS) Open form for update with plan data
//    @GetMapping("/productionplanning/updatePlan/{productionplanninguid}")
//    public String editplan(@PathVariable("productionplanninguid") String productionplanninguid, Model model) {
//        List<Map<String, Object>> productionplanning = productionplanningservice.getProductionPlanningbyuid(productionplanninguid);
//        model.addAttribute("productionplanning", productionplanning);
//        return "ProductionAndOperationManagement/productionplanning";
//    }
//    
    @GetMapping("/productionplanning")
    public String handleProductionPlanning(@RequestParam(required = false) String mode,
                                           @RequestParam(required = false) String uid,
                                           Model model) {
        if ("update".equals(mode) && uid != null) {
            List<Map<String, Object>> plan = productionplanningservice.getProductionPlanningbyuid(uid);
            model.addAttribute("productionplanning", plan);
        }
        return "ProductionAndOperationManagement/ProductionPlanning";
    }

    // ✅ Update single production plan (form values only, not materials)
    @PostMapping("/productionplanning/update")
    public String updateproduction(@ModelAttribute productionplanning productionplanning, RedirectAttributes redirectAttributes) {
        productionplanningservice.updateProductionPlanning(productionplanning);
        return "redirect:/productionandoperationmanagement/productionplanninggrid";
    }

 // ✅ Get all product UIDs for dropdown
    @GetMapping("/getProductUIds")
    @ResponseBody
    public List<String> getProductUIds() {
        return productionplanningservice.getProductUId();// fetch UID list
    }

    // ✅ Get product name based on selected product UID
    @GetMapping("/getProductNames")
    @ResponseBody
    public List<String> getProductNamesByUid(@RequestParam String productuid) {
        return productionplanningservice.getProductNamesByProductUid(productuid);// fetch product name
    }

 // ✅ Get raw materials required for selected product
    @GetMapping("/getMaterialsByProductName")
    @ResponseBody
    public List<Map<String, Object>> getMaterialsByProductName(@RequestParam String productname) {
        return productionplanningservice.getMaterialNamesByProductName(productname);// fetch material list
    }

 // ✅ Get available quantity of specific material (for actual field)
    @GetMapping("/getAvailableQuantity")
    public ResponseEntity<Double> getAvailableQuantity(@RequestParam String rawmaterialuid) {
        double qty = productionplanningservice.getAvailableQuantity(rawmaterialuid);
        return ResponseEntity.ok(qty);
    }

    
 // ✅ Get list of employees for dropdowns (responsible manager / approved by)
    @GetMapping("/getEmployeeNameList")
    @ResponseBody
    public List<Map<String, String>> getEmployeeNameList() {
        return productionplanningservice.getEmployeeNames();// return list of employee names
    }
    
 // ✅ Get plan data by UID — used by JS to load form in update mode
    @GetMapping("/productionplanning/getPlanByUid")
    @ResponseBody
    public List<Map<String, Object>> getPlanByUid(@RequestParam String productionplanninguid) {
        return productionplanningservice.getProductionPlanningbyuid(productionplanninguid);// fetch full plan by UID
    }
    
}
