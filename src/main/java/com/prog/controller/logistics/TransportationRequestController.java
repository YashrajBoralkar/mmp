package com.prog.controller.logistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.prog.model.erp.TransportationRequest;
import com.prog.service.inventory.WarehouseInfoService;
import com.prog.service.logistics.TransportationRequestService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TransportationRequestController {

    @Autowired
    private TransportationRequestService transportationRequestService;
    @Autowired
    private WarehouseInfoService warehouseservice;

   
    
    @GetMapping("/logistics/transportationrequestgrid")
    public String ShowTransportationRequestGrid() {
    	return "LOGISTICSgrid/TransportationRequestGrid";
    }
    
    
    @GetMapping("/showtransportationrequestform")
    public String showForm(@RequestParam(value = "id", required = false) Long id, Model model) {
    	
     	List<String>warehouseuid = warehouseservice.fetchwarehouseuid();
		model.addAttribute("warehouseuid", warehouseuid);

        if (id != null) {
            TransportationRequest request = transportationRequestService.getRequestById(id);
            
            model.addAttribute("request", request);
        } else {
            model.addAttribute("request", new TransportationRequest());
        }
        return "logistics/TransportationRequestForm";
    }

    @PostMapping("/transportationrequest/save")
    public String saveLogistics(@ModelAttribute TransportationRequest request, Model model) {
        transportationRequestService.saveLogistics(request); // Use instance method
        model.addAttribute("SuccessMsg", "Saved successfully");
        return "redirect:/logistics/transportationrequestgrid";
    }
    
    
  
 // Display list
    @GetMapping("/viewalltransportationrequestlistinfo")
	@ResponseBody
	public Map<String, Object> showlistoftransportationrequest() {
	    List<String> headers = List.of(
	        "ID", "Transportation Request ID","Source ID","Source Location","Destination ID",
	        "Destination Location","Requested By", "Transport Provider","Driver Name","Driver Contact Number","Approval Status");
	    
        List<Map<String, Object>> transportationrequestInfoList = transportationRequestService.getAllRequest();
	    List<Map<String, String>> transportationrequestList = new ArrayList<>();
	    
	    for (Map<String, Object> transportationrequestInfo : transportationrequestInfoList) {
	        Map<String, String> row = new HashMap<>();
	        row.put("ID", String.valueOf(transportationrequestInfo.get("id")));
	        row.put("Transportation Request ID", String.valueOf(transportationrequestInfo.get("transportationrequestuid")));
	        row.put("Source ID", String.valueOf(transportationrequestInfo.get("sourcewarehouseuid")));
	        row.put("Source Location", String.valueOf(transportationrequestInfo.get("sourcelocation")));
	        row.put("Destination ID", String.valueOf(transportationrequestInfo.get("destinationwarehouseuid")));
	        row.put("Destination Location", String.valueOf(transportationrequestInfo.get("destinationlocation")));
	        row.put("Requested By", String.valueOf(transportationrequestInfo.get("requestedby")));
	        row.put("Transport Provider", String.valueOf(transportationrequestInfo.get("transportprovidername")));
	        row.put("Driver Name", String.valueOf(transportationrequestInfo.get("drivername")));
	        row.put("Driver Contact Number", String.valueOf(transportationrequestInfo.get("drivercontact")));
	        row.put("Approval Status", String.valueOf(transportationrequestInfo.get("approvalstatus")));

	        transportationrequestList.add(row);
	    }
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("headers", headers);
	    response.put("data", transportationrequestList);
	    response.put("status", "success");
	    
	    return response;
	}
    


    @GetMapping("/transportationrequest/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        TransportationRequest request = transportationRequestService.getRequestById(id);
        List<String> warehouseuid = warehouseservice.fetchwarehouseuid();
        
        model.addAttribute("request", request);
        model.addAttribute("warehouseuid", warehouseuid); // Ensure warehouse UIDs are available in form
        return "logistics/TransportationRequestForm";
    }
    
    @PostMapping("/transportationrequest/update")
    public String updateLogistics(@ModelAttribute TransportationRequest request) {
        System.out.println("Updating logistics data: " + request);
        System.out.println("Received ID: " + request.getId()); // Should not be null
        transportationRequestService.updateLogistics(request);
        return "redirect:/logistics/transportationrequestgrid";
    } 

    
    @GetMapping("/deletetransportationrequestinfo")
    public String deleteRequest(@RequestParam("id") Long id) {
        transportationRequestService.deleteRequestById(id);
        return "redirect:/logistics/transportationrequestgrid";
    }
    
    
    //FETCHING
    
    @GetMapping("/logistics/getwarehousedatabyuid")
 	@ResponseBody  // Make sure to return JSON, not a view
 	public List<Map<String, Object>> getproductinfo(@RequestParam("warehouseuid") String warehouseuid ) {
 		
 		if(warehouseuid == null || warehouseuid.isEmpty()) {
 	        throw new IllegalArgumentException("warehouse ID must not be null or empty.");

 		}
 		return warehouseservice.GetDataByWarehouseUId(warehouseuid);
 		
 	}

}
