package com.prog.controller.productionandoperation;

import com.prog.model.erp.Employee;
import com.prog.model.erp.ProductionOrder;
import com.prog.service.hrms.EmployeeService;
import com.prog.service.productionandoperation.ProductionOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
public class ProductionOrderController {

    @Autowired
    private ProductionOrderService productionOrderService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/productionandoperationmanagement/productionordergrid")
    public String showproductionordergrid() {
        return "ProductionAndOperationManagementgrid/ProductionOrderGrid";
    }

    // Show Create Form
    @GetMapping("/productionorderform")
    public String showForm(Model model) {
        model.addAttribute("productionorder", new ProductionOrder());
        model.addAttribute("productuid", productionOrderService.getAllProductbyPP());

        // Supervisor Map
        List<Employee> employeeList = employeeService.getBasicEmployeeDetails();
        Map<String, String> supervisornameMap = new LinkedHashMap<>();
        for (Employee emp : employeeList) {
            String uid = emp.getEmployeeUID() != null ? emp.getEmployeeUID().trim() : "";
            String firstName = emp.getFirstName() != null ? emp.getFirstName().trim() : "";
            String lastName = emp.getLastName() != null ? emp.getLastName().trim() : "";
            supervisornameMap.put(uid, (firstName + " " + lastName).trim());
        }
        model.addAttribute("supervisornameMap", supervisornameMap);

        return "ProductionAndOperationManagement/ProductionOrderform";
    }

    // Save New Production Order
    @PostMapping("/saveproductionorderform")
    public String saveOrder(@ModelAttribute ProductionOrder productionOrder,
                            RedirectAttributes redirectAttributes,
                            @RequestParam(value = "productionplanninguid", required = false) String planningUids) {

        if (planningUids != null && !planningUids.isEmpty()) {
            productionOrder.setProductionplanninguid(planningUids); // already CSV string
        }

        productionOrderService.saveOrder(productionOrder);
        redirectAttributes.addFlashAttribute("successMessage", "Production Order saved successfully!");
        return "redirect:/productionandoperationmanagement/productionordergrid";
    }
  
    // Display list
    @GetMapping("/viewproductionorderlist")
	@ResponseBody
	public Map<String, Object> showlistoffproductionorder() {
	    List<String> headers = List.of(
	        "ID", "Product ID", "Product Name", "Production Order ID","Production Planning ID","Work Order ID", "Work Order Quantity","Production Order Quantity","Production Status");
	    
        List<Map<String, Object>> productionordersInfoList = productionOrderService.getAllOrders();
	    List<Map<String, String>> productionordersList = new ArrayList<>();
	    
	    for (Map<String, Object> productionordersInfo : productionordersInfoList) {
	        Map<String, String> row = new HashMap<>();
	        row.put("ID", String.valueOf(productionordersInfo.get("id")));
	        row.put("Product ID", String.valueOf(productionordersInfo.get("productuid")));
	        row.put("Product Name", String.valueOf(productionordersInfo.get("productname")));
	        row.put("Production Order ID", String.valueOf(productionordersInfo.get("productionorderuid")));
	        row.put("Production Planning ID", String.valueOf(productionordersInfo.get("productionplanninguid")));
	        row.put("Work Order ID", String.valueOf(productionordersInfo.get("workorderuid")));
	        row.put("Work Order Quantity", String.valueOf(productionordersInfo.get("workorderquantity")));
	        row.put("Production Order Quantity", String.valueOf(productionordersInfo.get("productionorderquantity")));
	        row.put("Production Status", String.valueOf(productionordersInfo.get("productionstatus")));
	        productionordersList.add(row);
	    }
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("headers", headers);
	    response.put("data", productionordersList);
	    response.put("status", "success");
	    
	    return response;
	}
  
    
    
    // Show Edit Form
    @GetMapping("/productionorder/edit/{id}")
    public String editOrder(@PathVariable("id") Long id, Model model) {
        ProductionOrder po = productionOrderService.getOrderById(id);
         model.addAttribute("po", po);
        return "ProductionAndOperationManagement/ProductionOrderform";
    }

    // Update Production Order
    @PostMapping("/productionorderupdate")
    public String updateOrder(@ModelAttribute ProductionOrder po,
                              @RequestParam(value = "productionplanninguid", required = false) String[] planningUids) {
        if (planningUids != null && planningUids.length > 0) {
            po.setProductionplanninguid(String.join(",", planningUids));
        }
        productionOrderService.updateProductionOrder(po);
        return "redirect:/productionandoperationmanagement/productionordergrid";
    }
    
 // Delete Order
    @GetMapping("/deleteproductionorderdeleteinfo")
    public String deleteOrder(@RequestParam("id") Long id) {
        productionOrderService.deleteOrder(id);
        return "redirect:/productionandoperationmanagement/productionordergrid";
    }
 

   
    
    // Get Product Data by Product UID
    @GetMapping("/productionplanning/getproductdata")
    @ResponseBody
    public List<Map<String, Object>> getProductData(@RequestParam("productuid") String productUid) {
        return productionOrderService.getProductDataByUid(productUid);
    }

    
 // ✅ Fetch Planning UIDs by Product UID
    @GetMapping("/getPlanningUIDs/{productuid}")
    @ResponseBody
    public List<String> getPlanningUIDs(@PathVariable String productuid) {
        return productionOrderService.getPlanningUIDsByProduct(productuid);
    }

    // ✅ Fetch WorkOrders by Planning UID
    @GetMapping("/getWorkOrders/{productionplanninguid}")
    @ResponseBody
    public List<String> getWorkOrders(@PathVariable String productionplanninguid) {
        return productionOrderService.getWorkOrdersByPlanningUID(productionplanninguid);
    }

    // ✅ Fetch Planned Quantity by WorkOrder UID
    @GetMapping("/getPlannedQuantity/{workorderuid}")
    @ResponseBody
    public Integer getPlannedQuantity(@PathVariable String workorderuid) {
        return productionOrderService.getPlannedQuantityByWorkOrder(workorderuid);
    }
    
    @GetMapping("/getPlannedDates/{productionplanninguid}")
    @ResponseBody
    public Map<String, Object> getPlannedDates(@PathVariable String productionplanninguid) {
        return productionOrderService.getPlannedDatesByPlanningUID(productionplanninguid);
    }

}
