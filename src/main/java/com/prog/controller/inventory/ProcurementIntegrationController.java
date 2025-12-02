package com.prog.controller.inventory;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prog.model.erp.CustomerDetails;
import com.prog.model.erp.Productinfo;
import com.prog.model.erp.PurchaseOrder;
import com.prog.model.erp.PurchaseRequisition;
import com.prog.model.erp.SalesOrder;
import com.prog.model.erp.Supplier;
import com.prog.service.inventory.CustomerDetailsService;
import com.prog.service.inventory.ProductInfoService;
import com.prog.service.inventory.PurchaseOrderService;
import com.prog.service.inventory.PurchaseRequistionService;
import com.prog.service.inventory.SupplierService;


@Controller
public class ProcurementIntegrationController 

{
	
	@Autowired
    private PurchaseRequistionService prform;
	@Autowired
    private ProductInfoService productservice;
	@Autowired
	private PurchaseOrderService purchaseorderservice;
	@Autowired
	private SupplierService supplierservice;
//	@Autowired
//	private SalesOrderService soservice;
//	@Autowired
//	private CustomerDetailsService cuservice;
//	

		
   
	@GetMapping("/inventory/procurementintegrationGrid")
    public  String viewgrid() {
    	return "INVENTORYgrid/procurementintegrationGrid";
    }
  
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	//Purchase Requisition Controller
 

	@GetMapping("/showprform")
	public String showprform( Model model ) 
	{
		model.addAttribute("pr", new PurchaseRequisition());
		List<String> productuid=productservice.soallproductuid();
		model.addAttribute("productuid",productuid);
		return "inventory/PurchaseRequisitionForm";
	}
//========================================================================================================================
	
	@PostMapping("/saveprform")
	public String saveprform(@ModelAttribute PurchaseRequisition pr, Model model ) 
	{
		prform.savepurchaserequisiton(pr);
		model.addAttribute("message", "pr saved successfully!");
        model.addAttribute("successMessage", "Purchase Info has been saved successfully!");
		return "redirect:/inventory/procurementintegrationGrid";
	}
//========================================================================================================================
	
	
	// Show Product Info List
		@GetMapping("/viewpurchasedlist")
	    @ResponseBody
	    public Map<String, Object> GetAllpurchasedlistInfo() {        
	        List<String> headers = List.of("ID","Purchase Requisition Id", "Product Id", "Product Name", "Requested By","Request Date","Quantity", "Unit of Measure", "Priority Level", "Approval Status","Approval Date");
	        
			List <Map<String,Object>> purchaserequisitonlist = prform.getAllPurchasedLevels();
	        
	        List<Map<String, String>> purchaserequisitionList = new ArrayList<>();
	        
	        for (Map<String, Object> purchaserequisition : purchaserequisitonlist) {
	            Map<String, String> row = new HashMap<>();
	            row.put("ID", String.valueOf(purchaserequisition.get("id")));
	            row.put("Purchase Requisition Id", String.valueOf(purchaserequisition.get("requisitionuid")));
	            row.put("Product Id", String.valueOf(purchaserequisition.get("productuid")));
	            row.put("Product Name", String.valueOf(purchaserequisition.get("productname")));
	            row.put("Requested By", String.valueOf(purchaserequisition.get("requestedby")));
	            row.put("Request Date", String.valueOf(purchaserequisition.get("requestdate")));
	            row.put("Quantity", String.valueOf(purchaserequisition.get("quantity")));
	            row.put("Unit of Measure", String.valueOf(purchaserequisition.get("unitofmeasure")));
	            row.put("Priority Level", String.valueOf(purchaserequisition.get("prioritylevel")));
	            row.put("Approval Status", String.valueOf(purchaserequisition.get("approvalstatus")));
	            row.put("Approval Date", String.valueOf(purchaserequisition.get("approvaldate")));
	           
	            purchaserequisitionList.add(row);
	        }
	        
	        Map<String, Object> response = new HashMap<>();
	        response.put("headers", headers);
	        response.put("data", purchaserequisitionList);
	        response.put("status", "success");
	        
	        return response;
	    }
//========================================================================================================================

	@GetMapping("/deletepurchaseinfo")
	public String deleteprformbyid(@RequestParam("id") Long id,RedirectAttributes redirectAttributes) 
	{
		try {
            System.out.println("Deleting enrollment with ID: " + id);
		prform.deleteprform(id);
		 redirectAttributes.addFlashAttribute("successMessage", "Stock Level entry deleted successfully!");
		  } catch (Exception e) {
			  
			  e.printStackTrace(); // DEBUG
	            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete enrollment.");
	        }
		return "redirect:/inventory/procurementintegrationGrid";
		
	}

//========================================================================================================================
	
	
	
	@GetMapping("/editpr/{id}")
	public String viewpurchasedform(@PathVariable("id") Long id, Model model) {
	    PurchaseRequisition pr = prform.getprformbyruid(id);
	    model.addAttribute("pr", pr);
	    return "inventory/PurchaseRequisitionForm";
	}

	// Example Spring Boot controller
	
//=========================================================================================================================

	
	
	@PostMapping("/updateprform")
	public String updateprform(@ModelAttribute PurchaseRequisition pr, RedirectAttributes redirectAttributes) {
	    try {
	        prform.updatepurchaseform(pr);
	        redirectAttributes.addFlashAttribute("message", "Purchase Requisition updated successfully!");
	    } catch (Exception e) {
	        e.printStackTrace();
	        redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while updating.");
	    }
	    return "redirect:/inventory/procurementintegrationGrid";
	}

//========================================================================================================================
	 @GetMapping("/product/getproductDetails")
	 @ResponseBody
	 public Productinfo getproductDetails(@RequestParam("productuid") String productuid) {
	       return productservice.sogetproductdetailsbyuid(productuid) ;
	    } 
	  
//========================================================================================================================

	 
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		
//Purchase Order(PO) Controller
	
		 @GetMapping("/purchaseorderform")
		    public String showForm(Model model)
		    {
		        List<String>supplieruid = supplierservice.getAllSupplierUids();
				List<String> productuid=productservice.soallproductuid();
			    model.addAttribute("purchaseorder", new PurchaseOrder());
			    model.addAttribute("supplieruid", supplieruid);
			    model.addAttribute("productuid", productuid);
		        return "inventory/PurchaseOrderForm";
		    }
		 
		 @PostMapping("/submitPurchaseOrder")
		    public String  savepurchaseorder(@ModelAttribute PurchaseOrder purchaseorder )
		    {
			 purchaseorderservice.savePurchaseOrder(purchaseorder);
		    	return "redirect:/inventory/procurementintegrationGrid";
		    }
		
		 
		// Show Stock List
					@GetMapping("/viewAllPurchaseOrderlist")
				    @ResponseBody
				    public Map<String, Object> viewAllpurchaseorder() {        
				        List<String> headers = List.of("ID", "PO ID", "Product Name", "Product ID", "PO Date", "Delivery Date", "Delivery Location", "Supplier Id","Supplier Name", "Total Price", "Unit Of Measure","Unit Price","Authorized By","Approval Status");
				        
				        List<Map<String, Object>> purchaseorderInfoList = purchaseorderservice.getPurchaseOrder();
				        
				        List<Map<String, String>>purchaseorderList = new ArrayList<>();
				        
				        for (Map<String, Object> purchaseorderInfo : purchaseorderInfoList) {
				            Map<String, String> row = new HashMap<>();
				            row.put("ID", String.valueOf(purchaseorderInfo.get("id")));
				            row.put("PO ID", String.valueOf(purchaseorderInfo.get("pouid")));
				            row.put("Product Name", String.valueOf(purchaseorderInfo.get("productname")));
				            row.put("Product ID", String.valueOf(purchaseorderInfo.get("productuid")));
				            row.put("PO Date", String.valueOf(purchaseorderInfo.get("podate")));
				            row.put("Delivery Date", String.valueOf(purchaseorderInfo.get("deliverydate")));
				            row.put("Delivery Location", String.valueOf(purchaseorderInfo.get("deliverylocation")));
				            row.put("Supplier Id", String.valueOf(purchaseorderInfo.get("supplieruid")));
				            row.put("Supplier Name", String.valueOf(purchaseorderInfo.get("suppliername")));
				            row.put("Total Price", String.valueOf(purchaseorderInfo.get("totalprice")));
				            row.put("Unit Of Measure", String.valueOf(purchaseorderInfo.get("unitofmeasure")));
				            row.put("Unit Price", String.valueOf(purchaseorderInfo.get("unitprice")));
				            row.put("Authorized By", String.valueOf(purchaseorderInfo.get("authorizedby")));
				            row.put("Approval Status", String.valueOf(purchaseorderInfo.get("approvalstatus")));

				            purchaseorderList.add(row);
				        }
				        
				        Map<String, Object> response = new HashMap<>();
				        response.put("headers", headers);
				        response.put("data", purchaseorderList);
				        response.put("status", "success");
				        
				        return response;
				    }

		 
		 

		 @GetMapping("/deletepurchaseordersinfo")
		  public String deleteorders(@RequestParam("id") Long id) {
			 purchaseorderservice.deleteorders(id);  
		      return "redirect:/inventory/procurementintegrationGrid";  
		  }
		 
		 
		 
		 @GetMapping("/updatepurchaseorders/{id}")
		   public String editOrders(@PathVariable("id") Long id, Model model) {
			 PurchaseOrder purchaseorder = purchaseorderservice.getPurchaseOrderbyid(id);
		       model.addAttribute("purchaseorder", purchaseorder);
		       return "inventory/PurchaseOrderForm";
		   
		   }
		   
		   @PostMapping("/updateorders")
		   public String updateOrders(@ModelAttribute PurchaseOrder purchaseorder,RedirectAttributes redirectAttributes)
		   {
			   purchaseorderservice.updatePurchaseOrder(purchaseorder);
			   return "redirect:/inventory/procurementintegrationGrid";
		   }

		   
		   @GetMapping("/supplier/getSupplierDetails")
		    @ResponseBody
		    public Supplier getEmployeeDetails(@RequestParam("supplieruid") String supplieruid) {
		        // Fetch training details from the service or database
			   
		        return supplierservice.getSupplierDetailsBySupuid(supplieruid);
		    } 
		   

		 
		   @GetMapping("/product/getproductDetail")
			 @ResponseBody
			 public Productinfo getproductDetail(@RequestParam("productuid") String productuid) {
			       return productservice.sogetproductdetailsbyuid(productuid) ;
			    }
		   
		   
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			
//    //Sales Order Controller
//		   
//		   
//		   @GetMapping("/salesform")
//			public String salesorder(Model model ) 
//			{
//				model.addAttribute("so",new SalesOrder());
//				
//				List<String>customeruid=cuservice.showcustomerdeatils();// for the customer data fetching.
//				model.addAttribute("customeruid", customeruid);
//				
//				List<String> productuid=productservice.soallproductuid();// for the customer data fetching.
//				model.addAttribute("productuid", productuid);
//				
//				return"inventory/SalesOrderForm";
//				
//			}
//		//===============================================================================================================================	
//			@PostMapping("/savedsales")
//			public String Salesordersaving(SalesOrder so ,Model model) 
//			{
//				soservice.savesales(so);
//				model.addAttribute("savedsuccessful", "saved successfully sales order");
//				return "redirect:/inventory/procurementintegrationGrid";
//			}
//		//===================================================================================================================	
//
//			
//			
//			@GetMapping("/viewAllSalesOrderlist")
//			@ResponseBody
//			public Map<String, Object> showlistofsales() {
//			    List<String> headers = List.of(
//			        "ID", "Sales Order ID", "Customer UID", "Customer Name", "Email Address", "Phone Number",
//			        "Billing Address", "Shipping Address", "Product Name", "Product ID", "Quantity", "Total Amount",
//			        "Order Date", "Order Status", "Delivery Address", "Delivery Date", "Delivery Method", "Delivery Status"
//			    );
//			    
//			    List<Map<String, Object>> salesOrderInfoList = soservice.showjoinedsales();
//			    List<Map<String, String>> salesOrderList = new ArrayList<>();
//			    
//			    for (Map<String, Object> salesOrderInfo : salesOrderInfoList) {
//			        Map<String, String> row = new HashMap<>();
//			        row.put("ID", String.valueOf(salesOrderInfo.get("id")));
//			        row.put("Sales Order ID", String.valueOf(salesOrderInfo.get("salesorderuid")));
//			        row.put("Customer UID", String.valueOf(salesOrderInfo.get("customeruid")));
//			        row.put("Customer Name", String.valueOf(salesOrderInfo.get("customername")));
//			        row.put("Email Address", String.valueOf(salesOrderInfo.get("emailaddress")));
//			        row.put("Phone Number", String.valueOf(salesOrderInfo.get("contactnumber")));
//			        row.put("Billing Address", String.valueOf(salesOrderInfo.get("billingaddress")));
//			        row.put("Shipping Address", String.valueOf(salesOrderInfo.get("shippingaddress")));
//			        row.put("Product Name", String.valueOf(salesOrderInfo.get("productname")));
//			        row.put("Product ID", String.valueOf(salesOrderInfo.get("productuid")));
//			        row.put("Quantity", String.valueOf(salesOrderInfo.get("quantity")));
//			        row.put("Total Amount", String.valueOf(salesOrderInfo.get("totalamount")));
//			        row.put("Order Date", String.valueOf(salesOrderInfo.get("orderdate")));
//			        row.put("Order Status", String.valueOf(salesOrderInfo.get("orderstatus")));
//			        row.put("Delivery Address", String.valueOf(salesOrderInfo.get("deliveryaddress")));
//			        row.put("Delivery Date", String.valueOf(salesOrderInfo.get("deliverydate")));
//			        row.put("Delivery Method", String.valueOf(salesOrderInfo.get("deliverymethod")));
//			        row.put("Delivery Status", String.valueOf(salesOrderInfo.get("deliverystatus")));
//			        
//			        salesOrderList.add(row);
//			    }
//			    
//			    Map<String, Object> response = new HashMap<>();
//			    response.put("headers", headers);
//			    response.put("data", salesOrderList);
//			    response.put("status", "success");
//			    
//			    return response;
//			}
//		//===================================================================================================================	
//			
//			@GetMapping("/deletesalesinfo")
//			public String deletefromsales(@RequestParam("id") Long id,Model model) 
//			{
//				soservice.deletsalesfromlist(id);
//				return "redirect:/inventory/procurementintegrationGrid";
//				
//			}
//		//================================================================================================================================	
//
//			@GetMapping("/updatesales/{id}")
//			public String updatesales(@PathVariable Long id,Model model) 
//			{
//				SalesOrder so=soservice.getsalesbyid(id);
//				model.addAttribute("so",so);
//				return "inventory/SalesOrderForm";
//				
//			}
//		//===============================================================================================================================	
//			
//			@PostMapping("/sales/update")
//			public String updatesalespost(@ModelAttribute SalesOrder so, RedirectAttributes redirectAttributes) {
//			    try {
//			        soservice.updatesalesorderform(so);
//			        redirectAttributes.addFlashAttribute("message", "Updated successfully!");
//			    } catch (Exception e) {
//			        e.printStackTrace();
//			        redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while updating.");
//			    }
//			    return "redirect:/inventory/procurementintegrationGrid";  // Ensure proper redirection
//			}
//
//		//===============================================================================================================================	
//
//			@GetMapping("/customer/getdetails")
//			@ResponseBody
//			public CustomerDetails showlistofcustomer(@RequestParam("customeruid") String customeruid )
//			{
//				return cuservice.getcustomerbyuid(customeruid);
//				
//			}
//		//===============================================================================================================================	
//
//			@GetMapping("/product/getproducts")
//			@ResponseBody
//			public Productinfo showallproductuids(@RequestParam("productuid") String productuid) 
//			{
//				return productservice.sogetproductdetailsbyuid(productuid);
//			}
//		//===============================================================================================================================	
//
//		
//
//
//

		   
		   
		   
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			
     //Back Order Controller

		   
		  



}
