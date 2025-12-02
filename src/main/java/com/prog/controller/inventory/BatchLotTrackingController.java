package com.prog.controller.inventory;

import java.util.ArrayList;

import java.util.Arrays;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prog.model.erp.Batchinfo;
import com.prog.model.erp.LotInfo;
import com.prog.model.erp.Productinfo;
import com.prog.model.erp.RawMaterialInventoryEntry;
import com.prog.model.erp.RawMaterialPurchaseRequest;
import com.prog.model.erp.RawmaterialInfo;
import com.prog.model.erp.Stockinfo;
import com.prog.service.inventory.BatchInfoService;
import com.prog.service.inventory.BatchTrackingService;
import com.prog.service.inventory.LotInfoService;
import com.prog.service.inventory.LotTrackingService;
import com.prog.service.inventory.ProductInfoService;
import com.prog.service.inventory.RawMaterialInventoryEntryService;
import com.prog.service.inventory.RawMaterialPurchaseRequestService;
import com.prog.service.inventory.RawmaterialInfoService;
import com.prog.service.inventory.StockinfoService;


@Controller
public class BatchLotTrackingController {
	
	@Autowired
	private StockinfoService stockdetailsService;
	@Autowired
	private ProductInfoService productinfoservice;
	@Autowired
	private RawmaterialInfoService rawmaterialRegistrationservice; 
	@Autowired
    private RawMaterialInventoryEntryService service;
	@Autowired
	private RawMaterialPurchaseRequestService rawmaterialpurchaserequestservice;

	

	
	@GetMapping("/inventory/batchlotgrid")
	public String showbatchlotgrid() {
		return "INVENTORYgrid/BatchLotGrid";
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 	
	
	// Stock Controller

			
				@GetMapping("/stockinfoform")
				public String getStockInfoForm(Model model) {
					 List<String> productuid = stockdetailsService.fetchProductUIds();
				        model.addAttribute("productuid", productuid);
				       
				    return "inventory/StockInformationForm";
				}
			
				@PostMapping("/stockdetails")
				public String saveStockDetails(@ModelAttribute Stockinfo stockdetails) {
				    stockdetailsService.SaveBatchInfo(stockdetails); // ✅ Correct
				   
				    return "redirect:/inventory/batchlotgrid";
				}
			
			//	@PostMapping("/stockdetails")
			//	public String saveStockDetails(@ModelAttribute Stockdetails stockdetails) {
			//	    stockdetailsService.SaveBatchInfo(stockdetails); // ✅ Correct
			//	    return "redirect:/inventory/batchlotgrid";
			//	}
			
			
				// Show Stock Info List
				@GetMapping("/viewstockdetails")
				@ResponseBody
				public Map<String, Object> getAllStockDetailsList() {
				    List<String> headers = List.of("ID", "Product ID", "Product Name", "Unit Price", "Total Stock Count","Stock Price");
			
				    List<Map<String, Object>> stockDetailsRawList = stockdetailsService.getAllStockdetails();
			
				    List<Map<String, String>> stockdetailsList = new ArrayList<>();
			
				    for (Map<String, Object> stockRow : stockDetailsRawList) {
				        Map<String, String> row = new HashMap<>();
				        row.put("ID", String.valueOf(stockRow.get("id")));
				        row.put("Stock ID", String.valueOf(stockRow.get("stockuid")));
				        row.put("Product ID", String.valueOf(stockRow.get("productuid")));
				        row.put("Product Name", String.valueOf(stockRow.get("productname")));
				        row.put("Unit Price", String.valueOf(stockRow.get("sellingprice")));
				        row.put("Total Stock Count", String.valueOf(stockRow.get("totalstockquantity")));
				        row.put("Stock Price", String.valueOf(stockRow.get("stockprice")));
			
				        stockdetailsList.add(row);
				    }
			
				    Map<String, Object> response = new HashMap<>();
				    response.put("headers", headers);
				    response.put("data", stockdetailsList);
				    response.put("status", "success");
			
				    return response;
				}
			
				 @GetMapping("/deletestockdetails")
				    public String deleteStockInfo(@RequestParam("id") Long id) {
				        stockdetailsService.DeleteStockdetailsByid(id);
				        return "redirect:/inventory/batchlotgrid";
				    }
				 @GetMapping("/stockdetails/update/{id}")
				 public String showUpdateForm(@PathVariable Long id, Model model) {
				     Stockinfo stock = stockdetailsService.FetchStockdetailsByid(id);
				     model.addAttribute("stockdetails", stock);
				     return "inventory/StockInformationForm"; // your Thymeleaf HTML page
				 }
			
			
				 @PostMapping("/stockdetails/update")
				 public String updateStockDetails(@ModelAttribute Stockinfo stockdetails, RedirectAttributes redirectAttributes) {
				     stockdetailsService.UpdateStockdetails(stockdetails);
				     redirectAttributes.addFlashAttribute("message", "Stock Info updated successfully!");
				     return "redirect:/inventory/batchlotgrid";
				 }
						
				
			
				 // Handles AJAX request to fetch product details based on the given Product UID
				    @GetMapping("/getStockproductdetails")
				    @ResponseBody
				    public List<Map<String, Object>> getStockProductInfo(@RequestParam("productuid") String productuid) {
				        // Validate that the product UID is not null or empty
				        if (productuid == null || productuid.isEmpty()) {
				            throw new IllegalArgumentException("Product UID must not be null or empty.");
				        }
				        return stockdetailsService.getDataByProductUid(productuid); // Call service to fetch product details associated with the given UID
			
				    }

	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Product Info Controller

				  // Load Product Info Form
				@GetMapping("/productinfo")
				public String getproductInfo(Model model) {
				    List<String> rawmaterialuid = productinfoservice.getAllRawMaterialUIDs();
				    model.addAttribute("rawmaterialuid", rawmaterialuid);
			
				    List<String> categoryList = productinfoservice.getAllProductCategoryNames();
				    model.addAttribute("categoryList", categoryList);
			
				    // ❌ Do NOT call getSubcategoriesByCategory() without category
				    // Keep empty list for now
				    List<String> subcategoryList = new ArrayList<>();
				    model.addAttribute("subcategoryList", subcategoryList);
			
				    return "inventory/Productinformationform";
				}
			
			
				  @PostMapping("/saveproductinfo")
				  public String saveProductInfo(
				      Productinfo productinfo,
				      @RequestParam(value = "rawmaterialuid", required = false) List<String> rawmaterialuid,
				      @RequestParam(value = "rawmaterialname", required = false) String rawmaterialname
				  ) {
				      // Handle create mode
				      if (productinfo.getId() == null) {
				          if (rawmaterialuid != null && !rawmaterialuid.isEmpty()) {
				              String joinedRawMaterials = String.join(",", rawmaterialuid);
				              productinfo.setRawmaterialuid(joinedRawMaterials);
				          }
				      }
				
				      // Always set rawmaterialnames
				      productinfo.setRawmaterialname(rawmaterialname);
				
				      // Save or update
				      productinfoservice.SaveProductInfo(productinfo);
				
				      return "redirect:/inventory/batchlotgrid";
				  }
			 
			
			
			  // Get All Product Info (For listing)
			  @GetMapping("/viewproductinfolist")
			  @ResponseBody
			  public Map<String, Object> GetAllProductInfo() {
			      List<String> headers = List.of("ID", "Product ID", "Product Name", "Product Category",
			          "Unit of Measure", "Selling Price (MRP)","Product Status");
			
			      List<Map<String, Object>> productInfolist = productinfoservice.getAllProductInfo();
			      List<Map<String, String>> productinfoList = new ArrayList<>();
			
			      for (Map<String, Object> productInfo : productInfolist) {
			          Map<String, String> row = new HashMap<>();
			          row.put("ID", String.valueOf(productInfo.get("id")));
			          row.put("Product ID", String.valueOf(productInfo.get("productuid")));
			          row.put("Product Name", String.valueOf(productInfo.get("productname")));
			          row.put("Product Category", String.valueOf(productInfo.get("productcategory")));
			          row.put("Unit of Measure", String.valueOf(productInfo.get("unitofmeasure")));
			          row.put("Selling Price (MRP)", String.valueOf(productInfo.get("sellingprice")));
			          row.put("Product Status", String.valueOf(productInfo.get("itemstatus")));
			
			          productinfoList.add(row);
			      }
			
			      Map<String, Object> response = new HashMap<>();
			      response.put("headers", headers);
			      response.put("data", productinfoList);
			      response.put("status", "success");
			      return response;
			  }
			
			  // Delete
			  @GetMapping("/deleteproductinfo")
			  public String DeleteProductInfo(@RequestParam("id") Long id) {
			      productinfoservice.DeleteProductInfoByid(id);
			      return "redirect:/inventory/batchlotgrid";
			  }
			
			  // Fetch Product Info for Edit
			  @GetMapping("/getproductinfo/{id}")
			  public String GetProductInfo(@PathVariable Long id, Model model) {
			      Productinfo product = productinfoservice.FetchProductInfoByid(id);
			      model.addAttribute("product", product);
			
			      // ✅ Split comma-separated UIDs
			      String uidString = product.getRawmaterialuid();
			      List<String> uidList = Arrays.asList(uidString.split(","));
			
			      // ✅ Fetch raw material names
			      List<String> rawmaterialname = productinfoservice.getRawMaterialNamesByUIDs(uidList);
			      model.addAttribute("rawmaterialname", rawmaterialname);
			
			      // ✅ If needed in view
			      model.addAttribute("rawmaterialuid", productinfoservice.getAllRawMaterialUIDs());
			
			      return "inventory/Productinformationform";
			  }
			  
			  @PostMapping("/updateproductinfo")
			  public String updateProductInfo(
			          @ModelAttribute Productinfo productinfo,
			          @RequestParam(value = "rawmaterialuid", required = false) List<String> rawmaterialuid,
			          @RequestParam(value = "rawmaterialname", required = false) String rawmaterialname
			  ) {
			      if (rawmaterialuid != null && !rawmaterialuid.isEmpty()) {
			          productinfo.setRawmaterialuid(String.join(",", rawmaterialuid));
			      }
			      productinfo.setRawmaterialname(rawmaterialname);
			
			      productinfoservice.updateProductInfo(productinfo);
			      return "redirect:/inventory/batchlotgrid";
			  }
			
			  
			
			
			  @PostMapping("/getRawMaterialNamesByUIDs")
			  @ResponseBody
			  public  List<String> getRawMaterialNamesByUIDs(@RequestBody List<String> rawMaterialUIDs) {
			      return productinfoservice.getRawMaterialNamesByUIDs(rawMaterialUIDs); // Now returns comma-separated String
			  }
			
			
			  @GetMapping("/inventory/getAllProductCategories")
			  @ResponseBody
			  public List<String> getAllProductCategories() {
			      return productinfoservice.getAllProductCategoryNames();
			  }
			  
			  @GetMapping("/inventory/getSubcategoriesByCategory")
			  @ResponseBody
			  public List<String> getSubcategoriesByCategory(@RequestParam String category) {
			      return productinfoservice.getSubcategoriesByCategory(category);
			  }
			
			
			
			  @GetMapping("/getSubcategories")
			  @ResponseBody
			  public List<String> getSubcategories(@PathVariable("category") String category) {
			      return productinfoservice.getSubcategoriesByCategory(category);
			  }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		    //RAW MATERIAL REGISTRATION

			@GetMapping("/showrawmaterialregistrationform")
			public String showRawmaterialRegistrationForm(Model model) {
			    model.addAttribute("rawmaterialregistration", new RawmaterialInfo());
			    return "inventory/RawMaterialRegistrationForm";
			}

			
			@PostMapping("/rawmaterialregistration/save")
			public String submitRawMaterial(@ModelAttribute("rawmaterialregistration") RawmaterialInfo rawMaterial, RedirectAttributes redirectAttributes) {
			    rawmaterialRegistrationservice.saveRawMaterial(rawMaterial);
			    redirectAttributes.addFlashAttribute("successmessage", "Raw Material saved successfully!");
			    return "redirect:/inventory/batchlotgrid";  
			}

			 
			 // Show List
				@GetMapping("/viewrawmaterialregistrationinfolist")
				@ResponseBody
				public Map<String, Object> GetRawMaterialInfo() {        
				List<String> headers = List.of("ID", "Raw Material ID", "Raw Material Name", "Category", "Standard Purchase Price", "Active Status");
				
				 List<Map<String, Object>> rawmaterialInfolist = rawmaterialRegistrationservice.getAllRawMaterials();
				
				List<Map<String, String>> rawmaterialinfoList = new ArrayList<>();
				
				for (Map<String, Object> rawmaterialInfo : rawmaterialInfolist) {
				Map<String, String> row = new HashMap<>();
				row.put("ID", String.valueOf(rawmaterialInfo.get("id")));
				row.put("Raw Material ID", String.valueOf(rawmaterialInfo.get("rawmaterialuid")));
				row.put("Raw Material Name", String.valueOf(rawmaterialInfo.get("rawmaterialname")));
				row.put("Category", String.valueOf(rawmaterialInfo.get("category")));
				row.put("Standard Purchase Price", String.valueOf(rawmaterialInfo.get("standardpurchaseprice")));
				row.put("Active Status", String.valueOf(rawmaterialInfo.get("activestatus")));
				rawmaterialinfoList.add(row);
				}
				
				Map<String, Object> response = new HashMap<>();
				response.put("headers", headers);
				response.put("data", rawmaterialinfoList);
				response.put("status", "success");
				
				return response;
				}

			
		   
			
			@GetMapping("/rawmaterialmasterregistration/edit/{id}")
			public String editRawMaterial(@PathVariable("id") Long id, Model model) {
			    RawmaterialInfo raw = rawmaterialRegistrationservice.getRawMaterialById(id);
			    model.addAttribute("rawmaterialregistration", raw);
			    return "inventory/RawMaterialRegistrationForm"; // View name (rawmaterialregistration.html)
			}

			
			@PostMapping("/rawmaterialregistration/update")
			public String updateRawMaterial(@ModelAttribute("rawmaterialregistration") RawmaterialInfo rawMaterial, RedirectAttributes redirectAttributes) {
			    rawmaterialRegistrationservice.updateRawMaterial(rawMaterial); // Call the correct service method
			    redirectAttributes.addFlashAttribute("successmessage", "Raw material updated successfully!");
			    return "redirect:/inventory/batchlotgrid";
			}
			
			@GetMapping("/deleterawmaterialmasterregistrationinfo")
			public String deleteRawMaterial(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
			    rawmaterialRegistrationservice.deleteById(id);
			    redirectAttributes.addFlashAttribute("successmessage", "Raw material deleted successfully!");
			    return "redirect:/inventory/batchlotgrid"; 
			}
			
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			//RAW MATERIAL PURCHASE REQUEST
	
			    @GetMapping("/showrawmaterialpurchaserequestform")
			    public String getMaterialRequestForm(Model model) {
			        List<Map<String, Object>> plannings = rawmaterialpurchaserequestservice.getAllProductionPlanningUIDs();  // Planning UIDs

			        model.addAttribute("plannings", plannings);
			       return "inventory/RawMaterialPurchaseRequestForm";
			    }

			    @PostMapping("/rawmaterialpurchaserequest/save")
			    public ResponseEntity<String> saveRawMaterialRequest(@RequestBody List<RawMaterialPurchaseRequest> requests) {
			    	for (RawMaterialPurchaseRequest request : requests ) {
			    		rawmaterialpurchaserequestservice.saveMaterialRequest(request);
			    	}
			        return ResponseEntity.ok("Saved successfully");
			    }

			   
			    @GetMapping("/rawmaterialpurchaserequestupdate")
			    public String editMaterialRequest(@RequestParam(required = false) String rawmaterialpurchaserequestuid, Model model) {
			        List<Map<String, Object>> plannings = rawmaterialpurchaserequestservice.getAllProductionPlanningUIDs();
			        model.addAttribute("plannings", plannings);
			        if (rawmaterialpurchaserequestuid != null && !rawmaterialpurchaserequestuid.isEmpty()) {
			            List<Map<String, Object>> requestData = rawmaterialpurchaserequestservice.getRequestsByRequestId(rawmaterialpurchaserequestuid);
			            model.addAttribute("requestData", requestData);
			        }

			        return "inventory/RawMaterialPurchaseRequestForm";
			    }
			    
			    @PostMapping("/rawmaterialpurchaserequest/update")
			    public String updateMaterialRequest(@ModelAttribute RawMaterialPurchaseRequest updatedRequest) {
			    	rawmaterialpurchaserequestservice.updateMaterialRequest(updatedRequest);
			        return "redirect:/inventory/batchlotgrid";
			    }


			   
			    // Show List
				@GetMapping("/viewrawmaterialpurchaserequestinfolist")
				@ResponseBody
				public Map<String, Object> GetRawMaterialpurchaserequestInfo() {        
				List<String> headers = List.of("Raw Material Purchase Request ID", "Production Planning ID", "Product Name","Raw Material Details", "Request Date", "Required By", "Priority");
				
		        List<Map<String, Object>> rawmaterialpurchaserequestInfolist = rawmaterialpurchaserequestservice.getAllMaterialRequests();
				
				List<Map<String, String>> rawmaterialpurchaserequestinfoList = new ArrayList<>();
				
				for (Map<String, Object> rawmaterialpurchaserequestInfo : rawmaterialpurchaserequestInfolist) {
				Map<String, String> row = new HashMap<>();
				row.put("Raw Material Purchase Request ID", String.valueOf(rawmaterialpurchaserequestInfo.get("rawmaterialpurchaserequestuid")));
				row.put("Production Planning ID", String.valueOf(rawmaterialpurchaserequestInfo.get("productionplanninguid")));
				row.put("Product Name", String.valueOf(rawmaterialpurchaserequestInfo.get("productname")));
				row.put("Raw Material Details", String.valueOf(rawmaterialpurchaserequestInfo.get("rawmaterialDetails")));
				row.put("Request Date", String.valueOf(rawmaterialpurchaserequestInfo.get("requestdate")));
				row.put("Required By", String.valueOf(rawmaterialpurchaserequestInfo.get("requiredbydate")));
				row.put("Priority", String.valueOf(rawmaterialpurchaserequestInfo.get("priority")));
				rawmaterialpurchaserequestinfoList.add(row);
				}
				
				Map<String, Object> response = new HashMap<>();
				response.put("headers", headers);
				response.put("data", rawmaterialpurchaserequestinfoList);
				response.put("status", "success");
				
				return response;
				}

			
			    /**
			     * Deletes a material request by ID.
			     */
			    @GetMapping("/deleterawmaterialpurchaserequestinfo")
			    public String deleteRequest(@RequestParam("rawmaterialpurchaserequestuid") String rawmaterialpurchaserequestuid) {
			    	rawmaterialpurchaserequestservice.deleteRequest(rawmaterialpurchaserequestuid);
			        return "redirect:/inventory/batchlotgrid";
			    }

			    /**
			     * Loads a material request for editing.
			     */
//			    @GetMapping("/materialRequestForm/getRequestById/{rid}")
//			    public String getRequestById(@PathVariable Long rid, Model model) {
//			        List<Map<String, Object>> materialCodes = rawMaterialService.getAllRawMaterials();
//			        List<Map<String, Object>> products = productService.getAllProducts();
		//
//			        model.addAttribute("codes", materialCodes);
//			        model.addAttribute("products", products);
//			        return "Raw Material Purchase Request/RawMaterialPurchaseRequestForm.html";
//			    }

			    /**
			     * AJAX: Gets material summary (material name + required quantity) based on planning UID.
			     */
			    @GetMapping("/getMaterialsByPlanningUID")
			    @ResponseBody
			    public List<Map<String, Object>> getMaterialsByPlanningUID(@RequestParam String productionplanninguid) {
			        return rawmaterialpurchaserequestservice.getMaterialSummaryByPlanningUID(productionplanninguid);
			    }

			    /**
			     * AJAX: Fetches product name for a given planning UID.
			     */
			    @GetMapping("/getProductNameByPlanningUID")
			    @ResponseBody
			    public Map<String, Object> getProductNameByPlanningUID(@RequestParam String productionplanninguid) {
			        return rawmaterialpurchaserequestservice.getProductNameByPlanningUID(productionplanninguid);
			    }
			 // ✅ Get raw material request data by Request ID — used in update mode
			    @GetMapping("/rawmaterialpurchaserequest/getByRawmaterialpurchaserequestuid")
			    @ResponseBody
			    public List<Map<String, Object>> getRequestsByRequestId(@RequestParam String rawmaterialpurchaserequestuid) {
			        return rawmaterialpurchaserequestservice.getRequestsByRequestId(rawmaterialpurchaserequestuid);
			    }
			    
			 // ✅ Get list of employees for dropdowns (responsible manager / approved by)
			    @GetMapping("/getEmployeeName")
			    @ResponseBody
			    public List<Map<String, String>> getEmployeeNameList() {
			        return rawmaterialpurchaserequestservice.getEmployeeNames();// return list of employee names
			    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			//RAW MATERIAL INVENTORY ENTRY

			 
			    @GetMapping("/rawmaterialinventoryentry")
			    public String viewList(Model model) {
			        model.addAttribute("list", service.getAllEntriesForList());
			        model.addAttribute("grnList", service.getAllAcceptedGrns());
			        return "inventory/RawMaterialInventoryEntryForm";
			    }

			    
			    @GetMapping("/rawmaterialinventoryentry/form")
			    public String showForm(Model model) {
			        model.addAttribute("entry", new RawMaterialInventoryEntry());
			        model.addAttribute("grnList", service.getAllAcceptedGrns());
			        model.addAttribute("warehouseList", service.getAllWarehouses());
			        model.addAttribute("empuid", service.getAllEmployeeUid());

			        return "inventory/RawMaterialInventoryEntryForm";
			    }

			    @PostMapping("/rawmaterialinventoryentry/save")
			    public String save(@ModelAttribute("entry") RawMaterialInventoryEntry entry) {
			        service.saveEntry(entry);
			        return "redirect:/inventory/batchlotgrid";
			    }

			 // Show List
				@GetMapping("/viewrawmaterialinventoryentryinfolist")
				@ResponseBody
				public Map<String, Object> GetRawMAterialinventoryentryInfo() {        
				List<String> headers = List.of("ID", "RM Inventory Entry ID", "RMRN ID", "Raw Material ID", "Raw Material Name", "Actual Quantity", "Actual Quantity","Entered By");
				
				 List<Map<String, Object>> rawmaterialinventoryentryInfolist = service.getAllEntries();
				
				List<Map<String, String>> rawmaterialinventoryentryinfoList = new ArrayList<>();
				
				for (Map<String, Object> rawmaterialinventoryentryInfo : rawmaterialinventoryentryInfolist) {
				Map<String, String> row = new HashMap<>();
				row.put("ID", String.valueOf(rawmaterialinventoryentryInfo.get("id")));
				row.put("RM Inventory Entry ID", String.valueOf(rawmaterialinventoryentryInfo.get("rawmaterialinventoryentryuid")));
				row.put("RMRN ID", String.valueOf(rawmaterialinventoryentryInfo.get("rawmaterialreceiptnoteuid")));
				row.put("Raw Material ID", String.valueOf(rawmaterialinventoryentryInfo.get("rawmaterialuid")));
				row.put("Raw Material Name", String.valueOf(rawmaterialinventoryentryInfo.get("rawmaterialname")));
				row.put("Actual Quantity", String.valueOf(rawmaterialinventoryentryInfo.get("actualquantity")));
				row.put("Entered By", String.valueOf(rawmaterialinventoryentryInfo.get("fullName")));
				rawmaterialinventoryentryinfoList.add(row);
				}
				
				Map<String, Object> response = new HashMap<>();
				response.put("headers", headers);
				response.put("data", rawmaterialinventoryentryinfoList);
				response.put("status", "success");
				
				return response;
				}
				
			    @GetMapping("/rawmaterialinventoryentry/edit/{id}")
			    public String edit(@PathVariable int id, Model model) {
			        model.addAttribute("entry", service.getEntryById(id));
			        model.addAttribute("grnList", service.getAllAcceptedGrns());
			        model.addAttribute("warehouseList", service.getAllWarehouses());
			        model.addAttribute("empuid", service.getAllEmployeeUid());
			        return "inventory/RawMaterialInventoryEntryForm";
			    }
			    
			    @PostMapping("/rawmaterialinventoryentry/updateEntry")
			    public String updateEntry(@ModelAttribute RawMaterialInventoryEntry updated,RedirectAttributes redirectAttributes) {
			       service.updateEntry(updated);
				    redirectAttributes.addFlashAttribute("successmessage", "Raw material Inventory Entry updated successfully!");
			       return "redirect:/inventory/batchlotgrid";
			    }

			    @GetMapping("/deleterawmaterialinventoryentryinfo")
			    public String delete(@RequestParam("id") int id) {
			        service.deleteEntry(id);
			        return "redirect:/inventory/batchlotgrid";
			    }
			    
			    //fetching materialcode, materialname, orderedquantity only with qualitystatus pass
			    @GetMapping("/rawmaterialinventoryentry/materialdetails")
			    @ResponseBody
			    public Map<String, Object> getMaterialDetails(String rawmaterialreceiptnoteuid) {
			    	List<Map<String, Object>> materialdetails = service.getmaterialDetails(rawmaterialreceiptnoteuid);
			        return materialdetails.get(0);
			    }
			    

		


	}