package com.prog.controller.wholesellerandretailer;

import java.util.ArrayList;
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

import com.prog.service.inventory.ProductInfoService;
import com.prog.service.wholesellerandretailer.CustomerOrderService;
import com.prog.service.wholesellerandretailer.CustomerRegistrationService;
import com.prog.model.erp.CustomerOrder;
import com.prog.model.erp.CustomerRegistration;


@Controller
public class CustomerOrderController {
	
	  @Autowired
	  private CustomerOrderService customerOrderService;
	  @Autowired
	  private CustomerRegistrationService sellerservice;
	  @Autowired
	  private ProductInfoService productservice;
	  
	  @GetMapping("/wholesellerandretailer/customerordergrid")
		public String showwholesaleordergrid(){
			return "WHOLESELLERANDRETAILERgrid/CustomerOrderGrid";
		}
	  
	// Display the Wholesale Order form page
	    @GetMapping("/showwholesaleorderform")
	    public String showWholesaleOrderForm(Model model) {
	        // Fetch all product UIDs to populate the product selection dropdown
	        List<String> productuid = productservice.getAllProductuids();
	        model.addAttribute("productuid", productuid);
	        model.addAttribute("selleruid", sellerservice.getAllWholesellerUids());
	        // Return the view name for the wholesale order form
	        return "wholesellerandretailer/CustomerOrderForm"; 
	    }
	    
	 // Handle form submission for saving a Wholesale Order
	    //Submit Customer Quotation form
	    @PostMapping("/wholesaleorder/save")
	    public String submitWholesaleOrder(@ModelAttribute CustomerOrder customerorder) {
	        // Save the wholesale order using the service layer
	    	customerOrderService.saveWholesaleOrder(customerorder);
	        return "redirect:/wholesellerandretailer/customerordergrid";  // Redirect to the wholesale order listing page after successful save

	    }
	    
	 // Display list 
	    @GetMapping("/viewallwholesaleorderinfo")
		@ResponseBody
		public Map<String, Object> showlistofwholesaleorder() {
		    List<String> headers = List.of(
		        "ID","wholesaler ID","Company Name","Contact Person", "Order Date", "Product Name","Total Amount","Payment Status");
		    
	    	List<Map<String, Object>> wholesaleorderInfoList = customerOrderService.getAllWholesaleOrder();

			List<Map<String, String>> wholesaleorderList = new ArrayList<>();
		    
		    for (Map<String, Object> wholesaleorderInfo : wholesaleorderInfoList) {
		        Map<String, String> row = new HashMap<>();
		        row.put("ID", String.valueOf(wholesaleorderInfo.get("id")));
		        row.put("CustomerOrder UID", String.valueOf(wholesaleorderInfo.get("customerorderuid")));
		        row.put("Customer UID", String.valueOf(wholesaleorderInfo.get("customeruid")));          // ✅ ADD if needed
		        row.put("Company Name", String.valueOf(wholesaleorderInfo.get("companyname")));
		        row.put("Contact Person", String.valueOf(wholesaleorderInfo.get("contactperson")));
		        row.put("Order Date", String.valueOf(wholesaleorderInfo.get("orderdate")));
		        row.put("Product Name", String.valueOf(wholesaleorderInfo.get("productname")));
		        row.put("Total Amount", String.valueOf(wholesaleorderInfo.get("totalvalue")));
		        row.put("Payment Status", String.valueOf(wholesaleorderInfo.get("paymentstatus")));

		        wholesaleorderList.add(row);
		    }
		    
		    Map<String, Object> response = new HashMap<>();
		    response.put("headers", headers);
		    response.put("data", wholesaleorderList);
		    response.put("status", "success");
		    
		    return response;
		}

	 // Handle form submission for updating an existing Wholesale Order
	    @PostMapping("/wholesaleorder/update")
	    public String updateWholesaleOrder(@ModelAttribute("order") CustomerOrder order) {
	        // Call the service method to update the wholesale order in the database
	    	customerOrderService.updateWholesaleOrder(order);
	        return "redirect:/wholesellerandretailer/customerordergrid";    // Redirect the user to the list view after successful update

	    }
	    
	 // Handle deletion of a Wholesale Order by its ID
	    @GetMapping("/deletewholesaleorderinfo")
	    public String deleteWholesaleOrder(@RequestParam("id") Long id) {
	        // Call service to delete the wholesale order record with the given ID
	    	customerOrderService.deleteWholesaleOrder(id);
	        return "redirect:/wholesellerandretailer/customerordergrid";  // Redirect to the wholesale order listing page after deletion

	    }
	    
	    
//FETCHJING
	    @GetMapping("/wholesaleorder/fetchProductDetails")
		@ResponseBody
		 public List<Map<String, Object>>getProductDetails(@RequestParam("productuid") String productuid) { 	   
		          return productservice.getproductDetailsByProductuid(productuid);
		      } 
	   
	    @GetMapping("/wholesaleorder/fetchWholesalerDetails")
	    @ResponseBody
	    public CustomerRegistration getsellerdetailsDetailsByRetaileruid(@RequestParam("selleruid") String selleruid) { 	   
	        return sellerservice.getDetailsByRetailerUid(selleruid);
	    } 
	    
	    
	 // 👉 Fetch UIDs by customer type
	    @GetMapping("/customerorder/fetchCustomerUIDs")
	    @ResponseBody
	    public List<String> fetchCustomerUIDs(@RequestParam("type") String type) {
	        List<String> uids = customerOrderService.getCustomerUidsByType(type);
	        return (uids == null || uids.isEmpty()) ? List.of() : uids;
	    }

	    // 👉 Fetch customer details by UID
	    @GetMapping("/customerorder/fetchCustomerDetails")
	    @ResponseBody
	    public CustomerRegistration fetchCustomerDetails(@RequestParam("uid") String uid) {
	        return customerOrderService.getCustomerDetailsByUid(uid);
	    }
	    
	    @GetMapping("/wholesaleorder/edit/{id}")
	    public String editOrder(@PathVariable Long id, Model model) {
	        CustomerOrder order = customerOrderService.findById(id);
	        model.addAttribute("order", order);

	        if(order.getCustomeruid() != null) {
	            // Fetch CustomerRegistration using customer UID
	            CustomerRegistration customer = customerOrderService.findByCustomerUid(order.getCustomeruid());
	            model.addAttribute("customer", customer); // Add to model
	        }

	        return "wholesellerandretailer/CustomerOrderForm";
	    }


	}
	
