package com.prog.controller.logistics;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.prog.model.erp.Deliveryorder;
import com.prog.service.inventory.CustomerDetailsService;
import com.prog.service.logistics.DeliveryOrderService;
import com.prog.service.sales.SalesOrderservice;

@Controller
public class DeliveryOrderController {

    @Autowired
    private DeliveryOrderService deliveryOrderService;

    @Autowired
    private CustomerDetailsService customerservice;

    @Autowired
    private SalesOrderservice salesorder;

    // Show Delivery Order Grid
    @GetMapping("/logistics/deliveryordergrid")
    public String showDeliveryOrderGrid() {
        return "LOGISTICSgrid/DeliveryOrderGrid";
    }

    // Show Delivery Order Form (New)
    @GetMapping("/showdeliveryorder")
    public String showDeliveryOrderForm(Model model) {
        model.addAttribute("deliveryOrder", new Deliveryorder());

        // Populate dropdowns
        List<String> customeruid = customerservice.getCustomeruIdInDeliveryOrder();
        model.addAttribute("customeruid", customeruid);

        List<String> salesorderuid = salesorder.getSalesorderUidIndeliveryorder()
                                               .stream()
                                               .distinct()
                                               .toList();
        model.addAttribute("salesorderuid", salesorderuid);

        return "logistics/DeliveryOrderForm"; 
    }

    // Save new Delivery Order
    @PostMapping("/deliveryorder/save")
    public String saveDeliveryOrder(@ModelAttribute Deliveryorder deliveryOrder, Model model) {
        deliveryOrderService.saveDeliveryOrder(deliveryOrder);
        model.addAttribute("successmsg", "Delivery Order details saved successfully");
        return "redirect:/logistics/deliveryordergrid"; 
    }

    // Update Delivery Order Form (Edit)
 // Update Delivery Order Form (Edit)
    @GetMapping("/deliveryorder/update/{id}")
    public String editDeliveryOrder(@PathVariable("id") Long id, Model model) {
        Deliveryorder deliveryOrder = deliveryOrderService.getDeliveryOrderById(id);
        if (deliveryOrder == null) {
            return "redirect:/logistics/deliveryordergrid";
        }

        // ======== Format delivery date for <input type="date"> ========
        if (deliveryOrder.getDeliverydate() != null && !deliveryOrder.getDeliverydate().isEmpty()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate deliveryDate = LocalDate.parse(deliveryOrder.getDeliverydate(), formatter);
                model.addAttribute("formattedDeliveryDate", deliveryDate.toString());
            } catch (Exception e) {
                // In case parsing fails, leave the field empty
                model.addAttribute("formattedDeliveryDate", "");
            }
        } else {
            model.addAttribute("formattedDeliveryDate", "");
        }

        // ======== Populate Customer Name if customeruid exists ========
        if (deliveryOrder.getCustomeruid() != null) {
            List<Map<String, Object>> customerDetails =
                customerservice.getcustomerDetailsbyidInDeliveryOrder(deliveryOrder.getCustomeruid());
            if (!customerDetails.isEmpty() && customerDetails.get(0).get("customername") != null) {
                deliveryOrder.setCustomername(customerDetails.get(0).get("customername").toString());
            }
        }

        model.addAttribute("deliveryOrder", deliveryOrder);

        // ======== Populate dropdowns for Sales Order UID ========
        List<String> salesorderuidList = salesorder.getSalesorderUidIndeliveryorder()
                                                   .stream()
                                                   .distinct()
                                                   .toList();
        model.addAttribute("salesorderuid", salesorderuidList);

        return "logistics/DeliveryOrderForm";
    }

    // Update Delivery Order (Submit)
    @PostMapping("/deliveryorder/update")
    public String updateDeliveryOrder(@ModelAttribute Deliveryorder deliveryOrder, RedirectAttributes redirectAttributes) {
        deliveryOrderService.updateDeliveryOrder(deliveryOrder);
        redirectAttributes.addFlashAttribute("successMessage", "Delivery Order details updated successfully!");
        return "redirect:/logistics/deliveryordergrid";
    }

    // Delete Delivery Order
    @GetMapping("/deletedeliveryorderinfo")
    public String deleteDeliveryOrder(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            deliveryOrderService.deleteDeliveryOrder(id);
            redirectAttributes.addFlashAttribute("successMessage", "Delivery Order details deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete record.");
        }
        return "redirect:/logistics/deliveryordergrid";
    }

    // Fetch Customer Details by UID (AJAX)
    @GetMapping("/deliveryOrder/fetchcustomerdata")
    @ResponseBody
    public List<Map<String, Object>> getCustomerDetails(@RequestParam("customeruid") String customeruid){
        return customerservice.getcustomerDetailsbyidInDeliveryOrder(customeruid);
    }

    // Fetch Products + Sales Order Details by Sales Order UID (AJAX)
    @GetMapping("/deliveryOrder/fetchsalesorderdata")
    @ResponseBody
    public List<Map<String,Object>> fetchSalesOrderData(@RequestParam String salesorderuid) throws JsonProcessingException {
        List<Map<String,Object>> salesOrderDetails = deliveryOrderService.getSalesOrderDetails(salesorderuid);
        List<Map<String,Object>> products = deliveryOrderService.getProductsBySalesOrder(salesorderuid);

        Map<String, Object> responseMap = new HashMap<>();
        if(!salesOrderDetails.isEmpty()) {
            responseMap.putAll(salesOrderDetails.get(0));
            responseMap.put("products", products); // Add product list
        }

        return Collections.singletonList(responseMap);
    }

    // Fetch Quantity Dispatched for a Product under a Sales Order
    @GetMapping("/deliveryOrder/fetchquantitydispatched")
    @ResponseBody
    public Map<String,Object> fetchQuantityDispatched(@RequestParam String salesorderuid, @RequestParam String productuid) {
        return deliveryOrderService.getQuantityDispatched(salesorderuid, productuid);
    }

    // Display All Delivery Orders (Grid)
    @GetMapping("/viewalldeliveryorderlistinfo")
    @ResponseBody
    public Map<String, Object> showAllDeliveryOrders() {
        List<String> headers = List.of(
            "ID", "Delivery Order ID", "Sales Order ID", "Customer Name", "Delivery Date",
            "Quantity Dispatched", "Delivery Status");

        List<Map<String, Object>> deliveryOrdersInfoList = deliveryOrderService.getAllDeliveryOrders();
        List<Map<String, String>> deliveryOrdersList = new ArrayList<>();

        for (Map<String, Object> deliveryOrdersInfo : deliveryOrdersInfoList) {
            Map<String, String> row = new HashMap<>();
            row.put("ID", String.valueOf(deliveryOrdersInfo.get("id")));
            row.put("Delivery Order ID", String.valueOf(deliveryOrdersInfo.get("deliveryorderuid")));
            row.put("Sales Order ID", String.valueOf(deliveryOrdersInfo.get("salesorderuid")));
            row.put("Customer Name", String.valueOf(deliveryOrdersInfo.get("customername")));
            row.put("Delivery Date", String.valueOf(deliveryOrdersInfo.get("deliverydate")));
            row.put("Product Name", String.valueOf(deliveryOrdersInfo.get("productname")));
            row.put("Quantity Dispatched", String.valueOf(deliveryOrdersInfo.get("quantitydispatched")));
            row.put("Delivery Status", String.valueOf(deliveryOrdersInfo.get("deliverystatus")));
            deliveryOrdersList.add(row);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("headers", headers);
        response.put("data", deliveryOrdersList);
        response.put("status", "success");

        return response;
    }

    // Fetch Delivery Date by Product (AJAX)
    @GetMapping("/getDeliveryDateByProduct")
    @ResponseBody
    public Map<String, Object> getDeliveryDateByProduct( @RequestParam String salesorderuid, @RequestParam String productuid) {
        return deliveryOrderService.getDeliveryDateByProduct(salesorderuid, productuid);
    }

}
