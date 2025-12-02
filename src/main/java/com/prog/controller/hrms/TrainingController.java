package com.prog.controller.hrms;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prog.Dao.hrms.EmployeeDAO;
import com.prog.Dao.hrms.EnrollmentDao;
import com.prog.model.erp.Appraisal;
import com.prog.model.erp.Employee;
import com.prog.model.erp.Enrollment;
import com.prog.model.erp.Onboarding;
import com.prog.model.erp.Training;
import com.prog.service.hrms.EmployeeService;
import com.prog.service.hrms.EnrollmentService;
import com.prog.service.hrms.TrainingService;
@Controller
public class TrainingController {
	
	
	@Autowired
	  private EmployeeService employeeService;
	    
	@Autowired
	private TrainingService trainingService;
	     	
   @Autowired
   private EnrollmentService enrollmentservice;
   
   @GetMapping("/hrms/TrainingGrid")
	public String viewgoalgrid() {
		return "HRMSgrid/Traininggrid";
	}
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
	
   
   //Training Schedule Controller
   
   @GetMapping("/viewtrainigform")
   public String showForm(Model model) {
       model.addAttribute("training", new Training());
       return "hrms/Trainingscheduleform"; // The name of the Thymeleaf form template
   }

   @PostMapping("/savetrainingform")
   public String saveTraining(@ModelAttribute Training training, Model model) {
           trainingService.saveTraining(training);
           model.addAttribute("Successmessage", "Training saved successfully!");
      return "redirect:/hrms/TrainingGrid"; // Show success or error message
   }
   
   @GetMapping("/viewAllTraining")
   @ResponseBody
   public  Map<String, Object> viewAllTrainings() {
   	
   	 List<String> headers = List.of("ID","Training Title", 
                "Trainer Name", "Department", "Internal/External", "Training Mode","Start Date","End Date","Start Time","End Time","Duration");
   	
   	 // Fetch data dynamically using the service layer
       List<Map<String,Object>> TrainingInfoList = trainingService.getAllTraining();
       
       // Prepare data to match the dynamic structure expected by the view
       List<Map<String, String>> trainingdata = new ArrayList<>();
       for(Map<String, Object> traininginfo :TrainingInfoList) {
           Map<String, String> row = new HashMap<>();
           row.put("ID", String.valueOf(traininginfo.get("id")));
           row.put("Training Title",String.valueOf(traininginfo.get("trainingtitle")));
           row.put("Trainer Name",String.valueOf( traininginfo.get("trainer_name")));
           row.put("Department",String.valueOf( traininginfo.get("department")));
           row.put("Internal/External", String.valueOf(traininginfo.get("internal_external")));
           row.put("Training Mode", String.valueOf(traininginfo.get("training_mode")));
           row.put("Start Date", String.valueOf(traininginfo.get("start_date")));
           row.put("End Date", String.valueOf( traininginfo.get("end_date")));
           row.put("Start Time", String.valueOf(traininginfo.get("start_time")));
           row.put("End Time", String.valueOf(traininginfo.get("end_time")));
           row.put("Duration", String.valueOf(traininginfo.get("duration")));

           trainingdata.add(row);
       	
       }
       // Prepare the response
       Map<String, Object> response = new HashMap<>();
       response.put("headers", headers);
       response.put("data", trainingdata);
       response.put("status", "success");

       return response;
   }
   
   
   @GetMapping("/updatetraining/{id}")
   public String getTraining(@PathVariable Long id,Model model) {
       Training training = trainingService.getTrainingById(id);
       model.addAttribute("training", training);
       return "hrms/Trainingscheduleform";  // Thymeleaf view name
   }
   
   
  @PostMapping("/updatetraining")
   public String saveUpdatedtTraining(@ModelAttribute Training training, RedirectAttributes redirectAttributes) {
	  trainingService.updateTraining(training);  // Save the updated appraisal
       redirectAttributes.addFlashAttribute("message", "Training updated successfully!");
       return "redirect:/hrms/TrainingGrid";  // Redirect back to the list page
   }
   
   @GetMapping("/deletetraininginfo")
   public String deleteTraining(@RequestParam("id") Long id) {
	   trainingService.deleteTrainingById(id);  // Calls the service method to delete the appraisal
       return "redirect:/hrms/TrainingGrid";  // Redirects to the list of appraisals after deletion
   }
   
   
   
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
	
   //Enrollment Training Controller
   
   @GetMapping("/viewenrollmentform")
    public String showEnrollmentForm(Model model) {
        model.addAttribute("enrollment", new Enrollment());
        List<String> trainingUid = trainingService.getAllTrainingUids();
		List<String> employeeuid = employeeService.getAlleDetails();
        model.addAttribute("employeeuid", employeeuid);
        model.addAttribute("trainingUid", trainingUid);
        return "hrms/Enrollmentform";  // your Thymeleaf template name
        
    }

    @PostMapping("/enrollment/save")
    public String saveEnrollment(@RequestParam(value = "employeeuid", required = false) String employeeuid,
            @ModelAttribute Enrollment enrollment,Model model) {

    	enrollment.setEmployeeuid(employeeuid);
    	enrollmentservice.saveEnrollment(enrollment);
		//model.addAttribute("message", "Enrollment saved successfully!");
        model.addAttribute("successMessage", "Enrollment Info has been saved successfully!");

		return "redirect:/hrms/TrainingGrid"; // Replace with your actual success page template
    }
    
    
    @GetMapping("/viewAllEnrollment")
    @ResponseBody
    public  Map<String, Object> viewAllEnrollments() {
    	
    	 List<String> headers = List.of("ID","Employee ID","Employee First Name","Employee Last Name", "Department", "Training Title", 
                 "Trainer Name", "Duration", "Registration Date", "Completion Date","Enrollment Status");
    	
    	 // Fetch data dynamically using the service layer
        List<Map<String,Object>> EnrollmentInfoList = enrollmentservice.getAllEnrollment();
        
        // Prepare data to match the dynamic structure expected by the view
        List<Map<String, String>> enrollmentdata = new ArrayList<>();
        for(Map<String, Object> enrollmentinfo :EnrollmentInfoList) {
            Map<String, String> row = new HashMap<>();
            row.put("ID", String.valueOf(enrollmentinfo.get("id")));
            row.put("Employee ID",String.valueOf(enrollmentinfo.get("employeeuid")));
            row.put("Employee First Name",String.valueOf( enrollmentinfo.get("first_name")));
            row.put("Employee Last Name",String.valueOf( enrollmentinfo.get("last_name")));
            row.put("Department", String.valueOf(enrollmentinfo.get("departmentname")));
            row.put("Training Title", String.valueOf(enrollmentinfo.get("trainingtitle")));
            row.put("Trainer Name", String.valueOf(enrollmentinfo.get("trainer_name")));
            row.put("Duration", String.valueOf( enrollmentinfo.get("duration")));
            row.put("Registration Date", String.valueOf(enrollmentinfo.get("registration_date")));
            row.put("Completion Date", String.valueOf(enrollmentinfo.get("completion_date")));
            row.put("Enrollment Status", String.valueOf(enrollmentinfo.get("enrollment_status")));

            enrollmentdata.add(row);
        	
        }
        // Prepare the response
        Map<String, Object> response = new HashMap<>();
        response.put("headers", headers);
        response.put("data", enrollmentdata);
        response.put("status", "success");

        return response;
    }
    
    
   
    @GetMapping("/deleteenrollmentinfo")
    public String deleteEnrollment(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
    	enrollmentservice.deleteEnrollment(id);
           return "redirect:/hrms/TrainingGrid"; // Corrected path
    }
    
  
    
   
 @GetMapping("/updateenrollment/{id}")
 public String ViewEnrollment(@PathVariable("id") Long id, Model model) {
	 Enrollment enrollment = enrollmentservice.getEnrollmentById(id);
     
	 model.addAttribute("enrollment", enrollment);

	 return "hrms/Enrollmentform";
	 
 }
 @PostMapping("/enrollmentupdate")
 public String updateEnrollment(@ModelAttribute Enrollment enrollment, RedirectAttributes redirectAttributes) {
     // Validate the incoming enrollment data
   try {
	   enrollmentservice.updateEnrollment(enrollment);
         redirectAttributes.addFlashAttribute("message", "Enrollment updated successfully!");
     } catch (Exception e) {
         redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while updating the enrollment. Please try again.");
     }
     
     return "redirect:/hrms/TrainingGrid"; // Redirect to the enrollment list page
 }
 
 
 @GetMapping("/enrollment/getTrainingDetails")
 @ResponseBody
 public Training getTrainingDetails(@RequestParam("trainingUid") String trainingUid) {
     // Fetch training details from the service or database
     return trainingService.getTrainingDetailsByUid(trainingUid);
 } 
 
 
 @GetMapping("/enrollment/edetails")
 @ResponseBody
 public Employee getEmployeeDetails(@RequestParam("employeeuid") String employeeuid) {
     // Fetch training details from the service or database
     return employeeService.getEdetailsByuid(employeeuid);
 } 

 @GetMapping("/enrollment/ddetails")
 @ResponseBody
 public Onboarding getDesignationDetails(@RequestParam("employeeuid") String employeeuid) {
     // Fetch training details from the service or database
     return enrollmentservice.getDesignationdetailsByuid(employeeuid);
 } 



}
