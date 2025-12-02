package com.prog.controller.qualitycontrol;

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

import com.prog.model.erp.QualityControlAudit;
import com.prog.service.qualitycontrol.QualityControlAuditService;




@Controller
public class QualityControlAuditController {
	@Autowired
	private QualityControlAuditService auditService;
	
	@GetMapping("/qualitycontrol/qualitycontrolauditgrid")
    public String qualitycontrolauditgrid() {
    	return "QUALITYCONTROLgrid/QualityControlAuditGrid";
    }
		
	@GetMapping("/showqualitycontrolaudit")
	public String getAuditform(Model model) {
		model.addAttribute("af",new QualityControlAudit());
		return "qualitycontrol/QualityControlAuditForm";
	}
	
	@PostMapping("/qualitycontrolaudit/save")
	public String addAuditform( QualityControlAudit af, Model model) {
		auditService.addAuditData(af);
		model.addAttribute("Saved Successfully","Saved Successfully Quality Controller Audit Form");		
		return "redirect:/qualitycontrol/qualitycontrolauditgrid";
	}
	
	   
	 // Display list
	    @GetMapping("/viewallqualitycontrolauditlistinfo")
		@ResponseBody
		public Map<String, Object> showlistofgoodsreceiptnote() {
		    List<String> headers = List.of(
		        "ID", "QC Audit ID","Audit Date","Auditor Name", "Supervisior Review",
		        "Audit Status");
		    
			List<Map<String, Object>> qualitycontrolauditInfoList=auditService.getAllAuditFormData();
		    List<Map<String, String>> qualitycontrolauditList = new ArrayList<>();
		    
		    for (Map<String, Object> qualitycontrolauditInfo : qualitycontrolauditInfoList) {
		        Map<String, String> row = new HashMap<>();
		        row.put("ID", String.valueOf(qualitycontrolauditInfo.get("id")));
		        row.put("QC Audit ID", String.valueOf(qualitycontrolauditInfo.get("qualitycontrolaudituid")));
		        row.put("Audit Date", String.valueOf(qualitycontrolauditInfo.get("auditdate")));
		        row.put("Auditor Name", String.valueOf(qualitycontrolauditInfo.get("auditorname")));
		        row.put("Supervisior Review", String.valueOf(qualitycontrolauditInfo.get("supervisorreview")));
		        row.put("Audit Status", String.valueOf(qualitycontrolauditInfo.get("auditstatus")));
		        qualitycontrolauditList.add(row);
		    }
		    
		    Map<String, Object> response = new HashMap<>();
		    response.put("headers", headers);
		    response.put("data", qualitycontrolauditList);
		    response.put("status", "success");
		    
		    return response;
		}
	    
	
	@GetMapping("/qualitycontrolaudit/update/{id}")
	public String updateAuditFormDataById(@PathVariable("id") Long id, Model model) {
		QualityControlAudit af=auditService.getAuditFormDataById(id);
		model.addAttribute("auditlist",af);
		return "qualitycontrol/QualityControlAuditForm";
	}
	
	@PostMapping("/qualitycontrolaudit/update")
	public String updateAuditFormData(@ModelAttribute QualityControlAudit af) {
		auditService.updateAuditFormData(af);
		return "redirect:/qualitycontrol/qualitycontrolauditgrid";
	}
		
	
	@GetMapping("/deletequalitycontrolauditinfo")
	public String deleteAuditDataById(@RequestParam("id") Long id) {
		auditService.deleteAuditFormDataById(id);
		return "redirect:/qualitycontrol/qualitycontrolauditgrid";
	}
}
