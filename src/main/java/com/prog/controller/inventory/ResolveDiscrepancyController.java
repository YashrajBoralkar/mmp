package com.prog.controller.inventory;



import java.io.IOException;
import java.sql.SQLException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.prog.Dao.inventory.WarehouseInfoDao;
import com.prog.model.erp.CycleCountPhysical;
import com.prog.model.erp.Employee;
import com.prog.model.erp.RealTimeUpdate;
import com.prog.model.erp.Warehouseinfo;
import com.prog.model.erp.physicalcount;
import com.prog.service.hrms.EmployeeService;
import com.prog.service.inventory.CycleCountPhysicalService;
import com.prog.service.inventory.InventoryEntryService;
import com.prog.service.inventory.PhysicalCountService;
import com.prog.service.inventory.RealTimeUpdateService;



@Controller
public class ResolveDiscrepancyController {
	
	
	 @Autowired
	 private PhysicalCountService physicalCountService;
	 @Autowired
	 private CycleCountPhysicalService cyclecountphysicalservice;
	 
	 @Autowired
	    private EmployeeService employeeService;
	 
	 @Autowired
	 private RealTimeUpdateService realtimeupdateservice;
	 
	 @Autowired
	 private InventoryEntryService inventoryEntryService;
	 
	 @Autowired
	 private WarehouseInfoDao warehouseInfoDao;



	
	
	
	
	@GetMapping("/inventory/resolvediscrepancygrid")
	public String showbatchlotgrid() {
		return "INVENTORYgrid/ResolveDiscrepancyGrid";
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	 //Cycle Count Physical Controller
	
		@GetMapping("/physicalcyclecountform")
		public String getform(Model model) {
		   
		    List<String> productuids = physicalCountService.fetchProductUIds();

		    
		    List<Employee> employees = employeeService.getAllEmployees();
		    List<Map<String, Object>> warehouseuids = inventoryEntryService.getAllWarehouses();



		    model.addAttribute("productuids", productuids);
		    model.addAttribute("employees", employees);
		    model.addAttribute("warehouseuids", warehouseuids); // ✅ add warehouse UIDs only

		    model.addAttribute("physicalcyclecount", new CycleCountPhysical());

		    return "inventory/CycleCountPhysicalForm";
		}
		
//		@PostMapping("/physicalcyclecount")
//		public String SavePhysicalCycleCount(CycleCountPhysical physicalcyclecount) {
//			if (physicalcyclecount.getFinalapproval() == null || physicalcyclecount.getFinalapproval().isEmpty()) {
//		        physicalcyclecount.setFinalapproval("Pending");
//		    }
//			cyclecountphysicalservice.SavePhysicalCycleCount(physicalcyclecount);
//			return "redirect:/inventory/resolvediscrepancygrid";
//		}
		@PostMapping("/physicalcyclecount")
		public String savePhysicalCycleCount(@ModelAttribute CycleCountPhysical physicalcyclecount, Model model) {

		    cyclecountphysicalservice.SavePhysicalCycleCount(physicalcyclecount);

		    List<CycleCountPhysical> physicalcyclecountlist = cyclecountphysicalservice.getAllPhysicalCycleCountEntities();
		    model.addAttribute("physicalcyclecountlist", physicalcyclecountlist);

		 
		    return "INVENTORYgrid/CycleCountPhysicalGrid";
		}


		@GetMapping("/physicalcyclecount")
		public String showPhysicalCycleCountGrid(Model model) {
		   
		    List<CycleCountPhysical> physicalcyclecountlist = cyclecountphysicalservice.getAllPhysicalCycleCountEntities();
		    model.addAttribute("physicalcyclecountlist", physicalcyclecountlist);
		    return "INVENTORYgrid/CycleCountPhysicalGrid"; // Thymeleaf template path
		}



		
		
		
		
		// Show List
		@GetMapping("/viewCyclePhysicalCountlist")
		@ResponseBody
		public Map<String, Object> GetAllcyclePhysicalCycleCount() {        
		List<String> headers = List.of("ID", "Product ID", "Product Name","Warehouse ID","Warehouse Name", "Current System Stock", "Physical Count", "Difference", "Physical Count Date","Approval Status");
		
		List <Map<String,Object>> cyclecountphysicallist = cyclecountphysicalservice.getAllPhysicalCycleCount();
		
		List<Map<String, String>>  cyclecountphysicalList = new ArrayList<>();
		
		for (Map<String, Object> cyclecountphysicalinfo : cyclecountphysicallist) {
		Map<String, String> row = new HashMap<>();
		row.put("ID", String.valueOf(cyclecountphysicalinfo.get("id")));
		row.put("Product ID", String.valueOf(cyclecountphysicalinfo.get("productuid")));
		row.put("Product Name", String.valueOf(cyclecountphysicalinfo.get("productname")));
		row.put("Warehouse ID", String.valueOf(cyclecountphysicalinfo.get("warehouseuid")));
		row.put("Warehouse Name", String.valueOf(cyclecountphysicalinfo.get("warehousename")));
		row.put("Current System Stock", String.valueOf(cyclecountphysicalinfo.get("currentSystemStock")));
		row.put("Current System Stock", String.valueOf(cyclecountphysicalinfo.get("systemstock")));
		row.put("Physical Count", String.valueOf(cyclecountphysicalinfo.get("physicalstock")));
		row.put("Difference", String.valueOf(cyclecountphysicalinfo.get("stockdifference")));

		row.put("Cycle Count Scheduled Date", String.valueOf(cyclecountphysicalinfo.get("scheduleddate")));
		row.put("Approval Status", String.valueOf(cyclecountphysicalinfo.get("approvalstatus")));

		cyclecountphysicalList.add(row);
		}
		
		Map<String, Object> response = new HashMap<>();
		response.put("headers", headers);
		response.put("data", cyclecountphysicalList);
		response.put("status", "success");
		
		return response;
		}

	  
		
		
		@GetMapping("/getphysicalcyclecount/{id}")
		public String GetPhysicalCycleCount(@PathVariable Long id, Model model) {
		    CycleCountPhysical physicalcyclecount = cyclecountphysicalservice.FetchPhysicalCycleCountByid(id);

		    List<Employee> employees = employeeService.getAllEmployees();  
		    List<String> productuids = physicalCountService.fetchProductUIds(); 
		    List<Map<String, Object>> warehouseuids = inventoryEntryService.getAllWarehouses();

		    // ✅ Fetch warehouse name using warehouse UID
		    String warehousename = cyclecountphysicalservice.getWarehouseNameByUid(physicalcyclecount.getWarehouseuid());

		    model.addAttribute("employees", employees);
		    model.addAttribute("productuids", productuids);
		    model.addAttribute("physicalcyclecount", physicalcyclecount);
		    model.addAttribute("warehouseuids", warehouseuids);
		    model.addAttribute("warehousename", warehousename);
		    System.out.println("Warehouse UID: " + physicalcyclecount.getWarehouseuid());
		    System.out.println("Warehouse Name: " + warehousename);


		    return "inventory/CycleCountPhysicalForm";
		}


		
		
		@PostMapping("/updatephysicalcyclecount")
		public String UpdatePhysicalCycleCount(@ModelAttribute CycleCountPhysical formPCC) {
		    // Fetch existing entity
		    CycleCountPhysical existingPCC = cyclecountphysicalservice.FetchPhysicalCycleCountByid(formPCC.getId());

		    // Update only the editable fields
		    existingPCC.setCategory(formPCC.getCategory());
		    existingPCC.setScheduleddate(formPCC.getScheduleddate());
		    existingPCC.setCyclecounttype(formPCC.getCyclecounttype());
		    existingPCC.setCountinitiatedby(formPCC.getCountinitiatedby());
		    existingPCC.setProductuid(formPCC.getProductuid());
		    existingPCC.setWarehouseuid(formPCC.getWarehouseuid());
		    existingPCC.setPhysicalstock(formPCC.getPhysicalstock());
		    existingPCC.setStockdifference(formPCC.getStockdifference());
		    existingPCC.setReasonofdiscrepancy(formPCC.getReasonofdiscrepancy());
		    existingPCC.setRemark(formPCC.getRemark());
		    existingPCC.setCorrectiveactiontaken(formPCC.getCorrectiveactiontaken());
		    existingPCC.setResolvedby(formPCC.getResolvedby());
		    existingPCC.setResolutiondate(formPCC.getResolutiondate());
		    existingPCC.setApprovalstatus(formPCC.getApprovalstatus());
		    existingPCC.setVerifiedby(formPCC.getVerifiedby());
		    existingPCC.setReviewedby(formPCC.getReviewedby());
		    existingPCC.setFinalapproval(formPCC.getFinalapproval());

		    // ❌ Do NOT overwrite cyclecountUID
		    // existingPCC.setCyclecountUID(existingPCC.getCyclecountUID());

		    // Save updated entity
		    cyclecountphysicalservice.UpdatePhysicalCycleCount(existingPCC);

		    return "redirect:/inventory/resolvediscrepancygrid";
		}


		
		@GetMapping("/deletephysicalcyclecountinfo")
		public String DeletePhysicalCycleCount(@RequestParam("id") Long id) {
			cyclecountphysicalservice.DeletePhysicalCycleCountByid(id);
			return "redirect:/inventory/resolvediscrepancygrid";
			
		}
		
		// ✅ Fetch Global Quantity from Realtimeupdate by Product UID
		@GetMapping("/getGlobalQuantityByUid")
		@ResponseBody
		public Double getGlobalQuantityByUid(@RequestParam("productuid") String productuid) {
		    if (productuid == null || productuid.isEmpty()) {
		        throw new IllegalArgumentException("Product UID must not be null or empty.");
		    }

		    Double globalQty = cyclecountphysicalservice.getGlobalQuantityByProductUid(productuid);
		    return globalQty != null ? globalQty : 0.0;
		}

		
//		@GetMapping("/getbatchdetails")
//		@ResponseBody  // Make sure to return JSON, not a view
//		public List<Map<String, Object>> getbatchinfo(@RequestParam("batchuid") String batchuid ) {
//			
//			if(batchuid == null || batchuid.isEmpty()) {
//		        throw new IllegalArgumentException("Batch UID must not be null or empty.");
//
//			}
//			return physicalcyclecountservice.GetDataByBatchuid(batchuid);
//			
//		}
		
		

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//Physical Count Controller
	
//  @GetMapping("/physicalcountform")
//  public String showPhysicalCountForm(Model model) {
//       List<String> productuids = physicalCountService.fetchProductUIds(); // Fetch all product UIDs
//       model.addAttribute("productuids", productuids);
//       
//       List<String> stockmanageruids = physicalCountService.fetchStockUIds(); // Fetch all product UIDs
//       model.addAttribute("stockmanageruids", stockmanageruids);
//       return "inventory/PhysicalCountForm"; // Return the view name for the form
//   }
	
	@GetMapping("/physicalcountform")
	public String showPhysicalCountForm(Model model) {
	    List<String> productuids = physicalCountService.fetchProductUIds();
	    List<Employee> employees = employeeService.getAllEmployees();
	    List<RealTimeUpdate> globalQuantities = realtimeupdateservice.getAllGlobalQuantities();
	    model.addAttribute("productuids", productuids);
	    model.addAttribute("employees", employees);
	    model.addAttribute("currentsystemstock", globalQuantities);
	    return "inventory/PhysicalCountForm";
	}


   // Submit Physical Count form
//   @PostMapping("/physicalcountform/save")
//   public String submitPhysicalCount(@ModelAttribute physicalcount physicalcount,
//                                     @RequestParam("file") MultipartFile file) throws IOException {
//       if (!file.isEmpty()) {
//           physicalcount.setSupportingDocument(file.getBytes());
//       }
//       
//       physicalCountService.savePhysicalCount(physicalcount);
//       return "redirect:/inventory/resolvediscrepancygrid"; // Redirect to the list view after submission
//   }
	
	@PostMapping("/physicalcountform/save")
	public String submitPhysicalCount(@ModelAttribute physicalcount physicalcount,
	                                  @RequestParam("file") MultipartFile file) throws IOException {
	    if (!file.isEmpty()) {
	        physicalcount.setSupportingDocument(file.getBytes());
	    }
	    physicalCountService.savePhysicalCount(physicalcount);
	    
	    return "redirect:/inventory/resolvediscrepancygrid";
	}


   // Display list of Physical Counts
//   @GetMapping("/physicalcount/List")
//   public String listPhysicalCount(@ModelAttribute physicalcount physicalcount, Model model) {
//       List<Map<String, Object>> physicalCounts = physicalCountService.getAllPhysicalCounts();
//       model.addAttribute("physicalCounts", physicalCounts);
//       return "physicalcountList"; // Thymeleaf template name for displaying the list
//   }
   
// Display list of Physical Counts
//   @GetMapping("/physicalcount/List")
//   public String listPhysicalCount(Model model) {
//       List<Map<String, Object>> physicalCounts = physicalCountService.getAllPhysicalCounts();
//       model.addAttribute("physicalCounts", physicalCounts);
//       
//       return "INVENTORYgrid/PhysicalCountGrid";
//   }
//   
   // Show Product Info List
	@GetMapping("/viewPhysicalCountlist")
	@ResponseBody
	public Map<String, Object> GetAllPhysicalCountInfo() {        
	List<String> headers = List.of("ID", "Product ID", "Product Name", "Current System Stock", "Physical Count", "Difference", "Physical Count Date","Approval Status");
	
	List <Map<String,Object>> physicalcountlist = physicalCountService.getAllPhysicalCounts();
	
	List<Map<String, String>>  physicalcountList = new ArrayList<>();
	
	for (Map<String, Object> physicalcountinfo : physicalcountlist) {
	Map<String, String> row = new HashMap<>();
	row.put("ID", String.valueOf(physicalcountinfo.get("id")));
	row.put("Product ID", String.valueOf(physicalcountinfo.get("productuid")));
	row.put("Product Name", String.valueOf(physicalcountinfo.get("productname")));
	row.put("Current System Stock", String.valueOf(physicalcountinfo.get("currentSystemStock")));
	row.put("Physical Count", String.valueOf(physicalcountinfo.get("physicalcount")));
	row.put("Difference", String.valueOf(physicalcountinfo.get("difference")));
	row.put("Physical Count Date", String.valueOf(physicalcountinfo.get("countdate")));
	row.put("Approval Status", String.valueOf(physicalcountinfo.get("approvalstatus")));

	physicalcountList.add(row);
	}
	
	Map<String, Object> response = new HashMap<>();
	response.put("headers", headers);
	response.put("data", physicalcountList);
	response.put("status", "success");
	
	return response;
	}


//   // Edit Physical Count
   @GetMapping("/editphysicalcount/{id}")
   public String editPhysicalCount(@PathVariable Long id, Model model) {
       physicalcount physicalcount = physicalCountService.getPhysicalCountById(id);

       // ✅ Dropdown lists पुन्हा load करायला हवेत
       List<String> productuids = physicalCountService.fetchProductUIds();
       List<Employee> employees = employeeService.getAllEmployees();
       List<RealTimeUpdate> globalQuantities = realtimeupdateservice.getAllGlobalQuantities();

       model.addAttribute("physicalCount", physicalcount);
       model.addAttribute("productuids", productuids);
       model.addAttribute("employees", employees);
       model.addAttribute("currentsystemstock", globalQuantities);

       return "inventory/PhysicalCountForm"; // Form reload होईल filled values सहित
   }

//
//   // Update Physical Count
//   @PostMapping("/physicalcount/update")
//   public String updatePhysicalCount(@ModelAttribute("physicalCount") physicalcount physicalcount) {
//       
//   	physicalCountService.updatePhysicalCount(physicalcount);
//       
//   	return "redirect:/inventory/resolvediscrepancygrid"; // Redirect after update
//   }
   
   @PostMapping("/physicalcount/update")
   public String updatePhysicalCount(@ModelAttribute("physicalCount") physicalcount physicalcount) {
       physicalCountService.updatePhysicalCount(physicalcount);
       return "redirect:/physicalcount/List"; // ✅ आता list ला redirect होईल
   }

   @GetMapping("/deletephysicalcountinfo")
   public String deletePhysicalCount(@RequestParam("id") Long id) {
       physicalCountService.deletePhysicalCount(id);
       return "redirect:/physicalcount/List"; // ✅
   }

   // Delete Physical Count
//   @GetMapping("/deletephysicalcountinfo")
//   public String deletePhysicalCount(@RequestParam("id") Long id) {
//       physicalCountService.deletePhysicalCount(id);
//       return "redirect:/inventory/resolvediscrepancygrid"; // Redirect to the list view
//   }

   // Get product details based on product UID
   @GetMapping("/getphysicalproductdetails")
   @ResponseBody
   public List<Map<String, Object>> getProductInfo(@RequestParam("productuid") String productuid) {
       if (productuid == null || productuid.isEmpty()) {
           throw new IllegalArgumentException("Product UID must not be null or empty.");
       }
       return physicalCountService.getDataByProductUid(productuid);
   }
   
   
//   @GetMapping("/getstockmanagerdetails")
//   @ResponseBody
//   public List<Map<String, Object>> getStockmanagerInfo(@RequestParam("stockmanageruid") String stockmanageruid) {
//       if (stockmanageruid == null || stockmanageruid.isEmpty()) {
//           throw new IllegalArgumentException("stock UID must not be null or empty.");
//       }
//       return physicalCountService.getDataByStockUid(stockmanageruid);
//   }
// Return JSON list of all Physical Counts
   @GetMapping("/viewAllPhysicalCount")
   @ResponseBody
   public List<Map<String, Object>> getAllPhysicalCount() {
       return physicalCountService.getAllPhysicalCounts();
   }

// Fetch Product Name + All Linked Warehouses for Dropdown

   @GetMapping("/getProductInfoByUid")
   @ResponseBody
   public Map<String, Object> getProductInfoByUid(@RequestParam("productuid") String productuid) throws SQLException {
       Map<String, Object> response = new HashMap<>();

       if(productuid == null || productuid.isEmpty()) {
           response.put("error", "Product UID is required");
           return response;
       }

       // Fetch product details from PhysicalCountService
       Map<String, Object> productInfo = physicalCountService.getDataByProductUid(productuid).stream().findFirst().orElse(null);

       if(productInfo != null) {
           response.put("productname", productInfo.get("productname"));
           response.put("warehouseuid", productInfo.get("warehouseuid"));
           // Fetch global quantity from realtimeupdateservice
           List<Double> globalQty = realtimeupdateservice.getAllGlobalQuantitiesByProductUid(productuid);
           response.put("globalquantity", globalQty != null ? globalQty : 0);
       } else {
           response.put("error", "No data found for Product UID: " + productuid);
       }

       return response;
   }
      
		 
		// ✅ Fetch latest realtime quantity by warehouseuid
		@GetMapping("/getRealtimeQuantityByWarehouse")
		@ResponseBody
		public Double getRealtimeQuantityByWarehouse(@RequestParam("warehouseuid") String warehouseuid) {
		    if (warehouseuid == null || warehouseuid.isEmpty()) {
		        throw new IllegalArgumentException("Warehouse UID must not be null or empty.");
		    }
		    Double latestQty = cyclecountphysicalservice.getLatestRealtimeQuantityByWarehouse(warehouseuid);
		    return latestQty != null ? latestQty : 0.0;
		}		
		
		
		//Fetch all warehouse UIDs linked to a specific productUID
		@GetMapping("/getWarehousesByProductUid")
		@ResponseBody
		public List<String> getWarehousesByProductUid(@RequestParam("productUid") String productuid) {
		 if (productuid == null || productuid.isEmpty()) {
		     throw new IllegalArgumentException("Product UID must not be null or empty.");
		 }
		 // InventoryEntryService मधून fetch करा
		 return inventoryEntryService.getWarehouseUidsByProductUid(productuid);
		}
		
		
		//✅ Fetch Warehouse Name by UID (for Cycle Count Physical Form)
		@GetMapping("/getWarehousenameByWarehouseUid")
		@ResponseBody
		public Map<String, Object> getWarehousenameByWarehouseUid(@RequestParam("warehouseuid") String warehouseUid) {
		 Map<String, Object> response = new HashMap<>();
		
		 if (warehouseUid == null || warehouseUid.isEmpty()) {
		     response.put("error", "Warehouse UID is required");
		     return response;
		 }
		
		 // Fetch warehouse name from DAO
		 List<Map<String, Object>> warehousename = warehouseInfoDao.getDataBywarehouseuid(warehouseUid);
		
		 if (warehousename != null) {
		     response.put("warehousename", warehousename);
		 } else {
		     response.put("error", "Warehouse name not found for UID: " + warehouseUid);
		 }
		
		 return response;
		}
		
		
		
	}
