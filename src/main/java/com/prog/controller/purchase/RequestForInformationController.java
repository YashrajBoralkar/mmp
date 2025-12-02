package com.prog.controller.purchase;

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

import com.prog.model.erp.RequestForInformation;
import com.prog.service.purchase.RequestForInformationService;
import com.prog.service.supplier.RawMaterialSupplierRegistrationService;

@Controller
public class RequestForInformationController {

    @Autowired
    private RequestForInformationService RequestForInformationService;
    @Autowired
    private RawMaterialSupplierRegistrationService supplierservice;
    
    
    @GetMapping("/purchase/requestforinformationgrid")
    public String showrfigrid() {
    	return "PURCHASEgrid/RequestForInformationGrid";
    }
    
    @GetMapping("/showrequestforinformation")
    public String showRFIForm(Model model) {
        model.addAttribute("rfiForm", new RequestForInformation());
        List<String> rawmaterialsupplieruid = supplierservice.getallsupplieruids();
        model.addAttribute("supplieruid", rawmaterialsupplieruid);
        return "purchase/RequestForInformationForm";
    }

    @PostMapping("/requestforinformation/save")
    public String saveRFIForm(@ModelAttribute RequestForInformation rfiForm, Model model) {
    	RequestForInformationService.saveRFIForm(rfiForm);
        model.addAttribute("message", "RFI Form saved successfully");
        model.addAttribute("successmsg", "Saved successfully");
        return "redirect:/purchase/requestforinformationgrid";
    }

   
 // Display list 
    @GetMapping("/viewallrequestforinformationinfo")
	@ResponseBody
	public Map<String, Object> showlistofgoodsreceiptnote() {
	    List<String> headers = List.of(
	        "ID","RFI ID","Request for Information Issue Date", "Supplier Name", "Response Deadline");
	    
    	List<Map<String, Object>> requestforinformationInfoList = RequestForInformationService.getAllRFIForms();
	    List<Map<String, String>> requestforinformationList = new ArrayList<>();
	    
	    for (Map<String, Object> requestforinformationInfo : requestforinformationInfoList) {
	        Map<String, String> row = new HashMap<>();
	        row.put("ID", String.valueOf(requestforinformationInfo.get("id")));
	        row.put("RFI ID", String.valueOf(requestforinformationInfo.get("requestforinformationuid")));
	        row.put("Request for Information Issue Date", String.valueOf(requestforinformationInfo.get("rfiissuedate")));
	        row.put("Supplier Name", String.valueOf(requestforinformationInfo.get("suppliername")));
	        row.put("Response Deadline", String.valueOf(requestforinformationInfo.get("responsedeadline")));
	       requestforinformationList.add(row);
	    }
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("headers", headers);
	    response.put("data", requestforinformationList);
	    response.put("status", "success");
	    
	    return response;
	}


    @GetMapping("/requestforinformation/update/{id}")
    public String editRFIForm(@PathVariable("id") Long id, Model model) {
    	RequestForInformation rfiForm = RequestForInformationService.getRFIFormById(id);
        model.addAttribute("rfiForm", rfiForm);
        return "purchase/RequestForInformationForm";
    }

    @PostMapping("/requestforinformation/update")
    public String updateRFIForm(@ModelAttribute RequestForInformation rfiForm, RedirectAttributes redirectAttributes) {
    	RequestForInformationService.updateRFIForm(rfiForm);
        redirectAttributes.addFlashAttribute("successMessage", "RFI Form updated successfully!");
        return "redirect:/purchase/requestforinformationgrid";
    }
    
    @GetMapping("/deleterequestforinformationinfo")
    public String deleteRFIForm(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        try {
        	RequestForInformationService.deleteRFIForm(id);
            redirectAttributes.addFlashAttribute("successMessage", "RFI Form entry deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete record.");
        }
        return "redirect:/purchase/requestforinformationgrid";
    }
    
    
    //FETCHING
    @GetMapping("/requestforinformation/getsupplieruidinfo")
    @ResponseBody 
    public List<Map<String, Object>> getSupplierUidInfo(@RequestParam("supplieruid") String rawmaterialsupplieruid) {
        if (rawmaterialsupplieruid == null || rawmaterialsupplieruid.isEmpty()) {
            throw new IllegalArgumentException("supplieruid must not be null or empty.");
        }
        return supplierservice.getSupplierDetailsByUids(rawmaterialsupplieruid);
    }
}

