package com.prog.controller.logistics;

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

import com.prog.Dao.inventory.CustomerDetailsDao;
import com.prog.model.erp.CustomerDetails;
import com.prog.model.erp.CustomerRegistration;
import com.prog.model.erp.GoodsReceiptNote;
import com.prog.model.erp.Productinfo;
import com.prog.model.erp.SupplierRegistrationForm;
import com.prog.model.erp.Warehouseinfo;
import com.prog.service.inventory.CustomerDetailsService;
import com.prog.service.inventory.ProductInfoService;
import com.prog.service.inventory.PurchaseOrderService;
import com.prog.service.inventory.WarehouseInfoService;
import com.prog.service.logistics.GoodsReceiptNoteService;
import com.prog.service.sales.SalesOrderservice;
import com.prog.service.supplier.RawMaterialSupplierRegistrationService;
import com.prog.service.wholesellerandretailer.CustomerRegistrationService;

@Controller
public class GoodsReceiptController {

	@Autowired
	private GoodsReceiptNoteService goodsreceiptnoteservice;

	@Autowired
	private WarehouseInfoService warehouseservice;

	@Autowired
	private ProductInfoService productinfoservice;

	@Autowired
	private RawMaterialSupplierRegistrationService supplierservice;

	@Autowired
	private PurchaseOrderService purchaseservice;
	
	@Autowired
	private SalesOrderservice soservice;

	@Autowired
	private CustomerRegistrationService customerservice;

	@GetMapping("/logistics/goodsreceiptnotegrid")
	public String showauditgrid() {
		return "LOGISTICSgrid/GoodsReceiptNoteGrid";
	}

	@GetMapping("/showgoodsreceiptnote")
	public String showGoods(Model model) {
		List<String> Productuid = productinfoservice.getAllProductuids();
		List<String> Warehouseuid = warehouseservice.getAllWarehouseuidsIngoodsreceiptnote();
		List<String> rawmaterialsupplieruid = supplierservice.getallsupplieruids();
		List<String> pouid = purchaseservice.getAllPurchaseUidsIngoodsReceiptnote();
		model.addAttribute("goodsreceiptnote", new GoodsReceiptNote());
		model.addAttribute("Productuid", Productuid);
		model.addAttribute("Warehouseuid", Warehouseuid);
		model.addAttribute("supplieruid", rawmaterialsupplieruid);
		model.addAttribute("pouid", pouid);

		return "logistics/GoodsReceiptNoteForm";
	}

	@PostMapping("/goodsreceiptnote/save")
	public String saveGoods(@ModelAttribute GoodsReceiptNote goodsreceiptnote, Model model) {

		model.addAttribute("message", "Goods Receipt Note record saved successfully");
		model.addAttribute("successmsg", "Saved successfully");
		goodsreceiptnoteservice.saveGoods(goodsreceiptnote);
		return "redirect:/logistics/goodsreceiptnotegrid";
	}

	// Display list of all goods receipt note info
	@GetMapping("/viewalllistgoodsreceiptnoteinfo")
	@ResponseBody
	public Map<String, Object> showlistofgoodsreceiptnote() {
		List<String> headers = List.of("ID", "Goods Receipt ID", "Receipt Date", "Supplier Name", "Warehouse Address", "Product Name", "Inspection Status", "Verified By", "Approval Status");

		List<Map<String, Object>> goodsreceiptnoteInfoList = goodsreceiptnoteservice.getGoodsList();
		List<Map<String, String>> goodsreceiptnoteList = new ArrayList<>();

		for (Map<String, Object> goodsreceiptnoteInfo : goodsreceiptnoteInfoList) {
			Map<String, String> row = new HashMap<>();
			row.put("ID", String.valueOf(goodsreceiptnoteInfo.get("id")));
			row.put("Goods Receipt ID", String.valueOf(goodsreceiptnoteInfo.get("goodsreceiptuid")));
			row.put("Receipt Date", String.valueOf(goodsreceiptnoteInfo.get("receiptdate")));
			row.put("Supplier Name", String.valueOf(goodsreceiptnoteInfo.get("suppliername")));
			row.put("Warehouse Address", String.valueOf(goodsreceiptnoteInfo.get("address")));
			row.put("Product Name", String.valueOf(goodsreceiptnoteInfo.get("productname")));
			row.put("Verified By", String.valueOf(goodsreceiptnoteInfo.get("verifiedby")));
			row.put("Approval Status", String.valueOf(goodsreceiptnoteInfo.get("approvalstatus")));
			goodsreceiptnoteList.add(row);
		}

		Map<String, Object> response = new HashMap<>();
		response.put("headers", headers);
		response.put("data", goodsreceiptnoteList);
		response.put("status", "success");

		return response;
	}

	@GetMapping("/goodsreceiptnote/update/{id}")
	public String editGoods(@PathVariable("id") Long id, Model model) {
		GoodsReceiptNote goodsreceiptnote = goodsreceiptnoteservice.getGoodsReceiptbyid(id);
		model.addAttribute("goodsreceiptnote", goodsreceiptnote);
		return "logistics/GoodsReceiptNoteForm";

	}

	@PostMapping("/goodsreceiptnote/update")
	public String updateGoodsNote(@ModelAttribute GoodsReceiptNote goodsreceiptnote,
			RedirectAttributes redirectAttributes) {
		goodsreceiptnoteservice.updateGoodsReceiptNote(goodsreceiptnote);
		redirectAttributes.addFlashAttribute("successMessage", "Goods Receipt Note record updated successfully!");
		return "redirect:/logistics/goodsreceiptnotegrid";
	}

	@GetMapping("/deletegoodsreceiptnoteinfo")
	public String deleteGoods(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
		goodsreceiptnoteservice.deleteGoods(id);
		try {
			goodsreceiptnoteservice.deleteGoods(id);
			redirectAttributes.addFlashAttribute("successMessage", "Goods Receipt Note entry deleted successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete record.");
		}
		return "redirect:/logistics/goodsreceiptnotegrid";
	}

//FETCHING 

	@GetMapping("/logistics/getProductdataingoodsreceiptnote")
	@ResponseBody
	public List<Map<String, Object>> getProductDetails(@RequestParam("productuid") String productuid) {

		return productinfoservice.getproductDetailsByProductuid(productuid);
	}

	@GetMapping("/logistics/getWarehouseInfoingoodsreceiptnote")
	@ResponseBody
	public Warehouseinfo getWarehouseDetails(@RequestParam("warehouseuid") String warehouseuid) {

		return warehouseservice.getwarehouseDetailsByWarehouseuidIngoodsreceiptnote(warehouseuid);
	}

	@GetMapping("/logistics/getSupplierDetailsingoodsreceiptnote")
	@ResponseBody
	public List<Map<String, Object>> getSupplierDetails(@RequestParam("supplieruid") String supplieruid) {

		return supplierservice.getSupplierDetailsByUids(supplieruid);
	}
//	   @GetMapping("/logistics/getpurchaseorderingoodsreceiptnote")
//	    @ResponseBody
//	    public SupplierRegistrationForm getpurchaseOrder(@RequestParam("pouid") String supplieruid) {
//		   
//	        return supplierservice.getSupplierDetailsBySupuidInGoodsReceiptNote(supplieruid);
//	    } 

	@GetMapping("/grn/customeruid")
	@ResponseBody
	public List<String> getcustomerUIDbytype(@RequestParam("customertype") String customertype) {
		return soservice.getcustomerUIDbytype(customertype);
	}
	
	@GetMapping("/grn/customer/GETdetails")
	@ResponseBody
	public CustomerRegistration showlistofcustomer(@RequestParam("customeruid") String customeruid) {
		return customerservice.getcustomerbyuid(customeruid);

	}
	
	@GetMapping("/grn/salesorderuids")
	@ResponseBody
	public List<String> getSalesOrderUIDS(@RequestParam("customeruid") String customeruid) {
		return goodsreceiptnoteservice.getSalesOrderUIDS(customeruid);
		
	}
	
	@GetMapping("/grn/deliveredquantity")
	@ResponseBody
	public List<String> getQuantity(@RequestParam("salesorderuid") String salesorderuid) {
		return goodsreceiptnoteservice.getQuantity(salesorderuid);
	}
	
	@GetMapping("/grn/productuids")
	@ResponseBody
	public List<String> getProductUIDsBySalesOrder(@RequestParam("salesorderuid") String salesorderuid) {
	    return goodsreceiptnoteservice.getProductUIDsBySalesOrder(salesorderuid);
	}

	@GetMapping("/grn/productname")
	@ResponseBody
	public String getProductName(@RequestParam("productuid") String productuid) {
	    return goodsreceiptnoteservice.getProductNameByUID(productuid);
	}
	
}
