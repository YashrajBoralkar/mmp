package com.prog.controller.hrms;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.prog.model.erp.Employee;
import com.prog.model.erp.InterviewSchedule;
import com.prog.model.erp.Job;
import com.prog.model.erp.Onboarding;
import com.prog.service.hrms.EmployeeService;

@Controller
public class MainDashboardController 
{
	 @GetMapping("/dashboard")
	    public String dashboardPage() {
	        return "Maindashboard"; // This will load dashboard.html from templates folder
	    }    
    @GetMapping("/hrms")
    public String hrms() {
    	 return "HRMS" ;
    }
    
    @GetMapping("/inventory")
    public String inventory() {
    	 return "INVENTORY" ;
    }
    
    
    @GetMapping("/qualitycontrol")
    public String qualitycontrol() {
    	 return "QUALITYCONTROL" ;
    }
    
    @GetMapping("/suppliermanagement")
    public String suppliermanagement() {
    	 return "SUPPLIER" ;
    }
    
    @GetMapping("/logisticsmanagement")
    public String logisticsmanagement() {
    	 return "LOGISTICS" ;
    }
    
    @GetMapping("/salesmanagement")
    public String salesmanagement() {
    	 return "SALES" ;
    }
    
    @GetMapping("/purchasemanagement")
    public String purchasemanagement() {
    	 return "PURCHASE" ;
    }
   
    @GetMapping("/wholesellerandretailermanagement")
    public String wholesellerandretailer() {
    	 return "WHOLESELLERANDRETAILER" ;
    }
    
    @GetMapping("/productionandoperationmanagement")
    public String productionandoperationmanagement() {
    	 return "PRODUCTIONANDOPERATION" ;
    }
    
    @GetMapping("/MaintenanceManagementDepartment")
    public String MaintenanceManagementDepartment() {
    	 return "MAINTENANCEMANAGEMENTDEPERTMENT" ;
    }
    
    @GetMapping("/AdvertiseGrid")
    public String Advertise() {
    	return "ADVERTISE";
    }

   
}
