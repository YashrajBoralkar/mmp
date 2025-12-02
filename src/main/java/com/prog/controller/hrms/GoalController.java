package com.prog.controller.hrms;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prog.model.erp.Appraisal;
import com.prog.model.erp.Employee;
import com.prog.model.erp.Feedback;
import com.prog.model.erp.Goal;
import com.prog.service.hrms.AppraisalService;
import com.prog.service.hrms.EmployeeService;
import com.prog.service.hrms.FeedbackService;
import com.prog.service.hrms.GoalService;

@Controller
public class GoalController {
	
	 @Autowired
	  private EmployeeService employeeService;
	    
	
	@GetMapping("/hrms/GoalGrid")
	public String viewgoalgrid() {
		return "HRMSgrid/Goalgrid";
	}
	
	//Appraisal Controller
	
	@Autowired
    private AppraisalService appraisalService;
	
	@GetMapping("/appraisalform")
    public String viewAppraisalForm(Model model) {
		List<String> employeeuid = employeeService.getAlleDetails();
        model.addAttribute("employeeuid",employeeuid);
        
        return "hrms/AppraisalForm"; // Redirects to the appraisal form
    }
	
	@PostMapping("/submitAppraisal")
    public String submitAppraisal(@RequestParam("employeeuid") String employeeuid,@ModelAttribute Appraisal appraisal, Model model) {
        // Save the appraisal form data
		appraisal.setEmployeeUID(employeeuid);
		appraisalService.saveAppraisal(appraisal);
        // Add a success message to the model
        model.addAttribute("message", "Appraisal submitted successfully!");
        return "redirect:/hrms/GoalGrid"; // You can create a success view or redirect here
    }
	
	 // Show View Attendance
		@GetMapping("/viewAllappraisal")
	    @ResponseBody
	    public Map<String, Object> viewAllappraisal() {        
	        List<String> headers = List.of("ID", "Employee ID", "First Name", "Last Name", "Department", "Appraisal Period", "Start Date", "End Date");
	        
	        List<Map<String, Object>> appraisalInfoList = appraisalService.getAllAppraisal();
	        
	        List<Map<String, String>> appraisalList = new ArrayList<>();
	        
	        for (Map<String, Object> appraisalInfo : appraisalInfoList) {
	            Map<String, String> row = new HashMap<>();
	            row.put("ID", String.valueOf(appraisalInfo.get("id")));
	            row.put("Employee ID", String.valueOf(appraisalInfo.get("employeeuid")));
	            row.put("First Name", String.valueOf(appraisalInfo.get("first_name")));
	            row.put("Last Name", String.valueOf(appraisalInfo.get("last_name")));
	            row.put("Department", String.valueOf(appraisalInfo.get("departmentname")));
	            row.put("Appraisal Period", String.valueOf(appraisalInfo.get("appraisal_period")));
	            row.put("Start Date", String.valueOf(appraisalInfo.get("start_date")));
	            row.put("End Date", String.valueOf(appraisalInfo.get("end_date")));
	            
	            appraisalList.add(row);
	        }
	        
	        Map<String, Object> response = new HashMap<>();
	        response.put("headers", headers);
	        response.put("data", appraisalList);
	        response.put("status", "success");
	        
	        return response;
	    }
 
	 
	 @GetMapping("/updateappraisal/{id}")
	    public String getAppraisal(@PathVariable Long id,Model model) {
	        Appraisal appraisal = appraisalService.getAppraisalById(id);
	        model.addAttribute("appraisal", appraisal);
	        return "hrms/AppraisalForm";  // Thymeleaf view name
	    }
	    
	    
	   @PostMapping("/appraisal/update")
	    public String saveUpdatedAppraisal(@ModelAttribute Appraisal appraisal, RedirectAttributes redirectAttributes) {
	        appraisalService.updateAppraisal(appraisal);  // Save the updated appraisal
	        redirectAttributes.addFlashAttribute("message", "Appraisal updated successfully!");
	        return "redirect:/hrms/GoalGrid";  // Redirect back to the list page
	    }
	    
	    @GetMapping("/deleteappraisalinfo")
	    public String deleteAppraisal(@RequestParam("id") Long id) {
	        appraisalService.deleteAppraisalById(id);  // Calls the service method to delete the appraisal
	        return "redirect:/hrms/GoalGrid";  // Redirects to the list of appraisals after deletion
	    }
	    
	    //FETCHING
	    @GetMapping("/appraisal/details")
	    @ResponseBody
	    public Employee getADetails(@RequestParam("employeeuid") String employeeuid){
	    	return employeeService.getEdetailsByuid(employeeuid);
	    	
	    	
	    }
	    
	    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    
	 //Feedback Controller
	    
	    @Autowired
	    private FeedbackService feedbackService;

	    @GetMapping("/feedbackform")
	    public String showFeedbackForm(Model model) {
	    	List<String> employeeuid = employeeService.getAlleDetails();
	        model.addAttribute("employeeuid",employeeuid);
	        model.addAttribute("feedback", new Feedback());
	        return "hrms/feedbackform";  // Refers to feedback-form.html in src/main/resources/templates
	    }
	    
	    @PostMapping("/submitFeedback")
	    public String submitFeedback(@RequestParam("employeeuid") String employeeuid,@ModelAttribute Feedback feedback, 
	                                 @RequestParam("file") MultipartFile file) throws IOException {
	    	// Check if the file is not empty
	        if (!file.isEmpty()) {
	            // Convert MultipartFile to byte array and set it in the feedback object
	            feedback.setAttachment(file.getBytes());
	        }
	        // Save feedback with attachment
	        feedback.setEmployeeUID(employeeuid);
	        feedbackService.saveFeedback(feedback);
	        return "redirect:/hrms/GoalGrid";  // Redirect to a success page or feedback form again
	    }
	    
	   
	    
		 // Show View Attendance
			@GetMapping("/viewAllfeedback")
		    @ResponseBody
		    public Map<String, Object> viewAllFeedback() {        
		        List<String> headers = List.of("ID", "Employee ID", "First Name", "Last Name", "Department", "Feedbck Type", "Feedback Date");
		        
		        List<Map<String, Object>> feedbackInfoList = feedbackService.getAllFeedback();
		        
		        List<Map<String, String>> feedbackList = new ArrayList<>();
		        
		        for (Map<String, Object> feedbackInfo : feedbackInfoList) {
		            Map<String, String> row = new HashMap<>();
		            row.put("ID", String.valueOf(feedbackInfo.get("id")));
		            row.put("Employee ID", String.valueOf(feedbackInfo.get("employeeuid")));
		            row.put("First Name", String.valueOf(feedbackInfo.get("first_name")));
		            row.put("Last Name", String.valueOf(feedbackInfo.get("last_name")));
		            row.put("Department", String.valueOf(feedbackInfo.get("departmentname")));
		            row.put("Feedback Type", String.valueOf(feedbackInfo.get("feedback_type")));
		            row.put("Feedback Date", String.valueOf(feedbackInfo.get("feedback_date")));
		           
		            feedbackList.add(row);
		        }
		        
		        Map<String, Object> response = new HashMap<>();
		        response.put("headers", headers);
		        response.put("data", feedbackList);
		        response.put("status", "success");
		        
		        return response;
		    }
	    
	    
	    @GetMapping("/updatefeedback/{id}")
	    public String updateFeedback(@PathVariable Long id, Model model) {
	        Feedback feedback = feedbackService.getFeedbackById(id);  // Fetch feedback by ID
	        model.addAttribute("feedback", feedback);  // Pass feedback to the view
	        return "hrms/feedbackform";  // Refers to updateFeedback.html
	    }

	    
	    @PostMapping("/updatefeedback")
	    public String saveUpdatedFeedback(
	            @ModelAttribute Feedback feedback,
	            @RequestParam("file") MultipartFile file,  // Accept file as a request parameter
	            RedirectAttributes redirectAttributes) {

	        try {
	            // Ensure the ID remains unchanged
	           
	            // Process the file if it's not empty
	            if (!file.isEmpty()) {
	                feedback.setAttachment(file.getBytes());
	            }

	            // Update feedback through the service layer
	            feedbackService.updateFeedback(feedback);

	            // Add a success message
	            redirectAttributes.addFlashAttribute("message", "Feedback updated successfully!");
	        } catch (Exception e) {
	            // Add an error message in case of failure
	            redirectAttributes.addFlashAttribute("error", "Failed to update feedback: " + e.getMessage());
	        }

	        // Redirect back to the list page
	        return "redirect:/hrms/GoalGrid";
	    }
	    
	    
	    @GetMapping("/fetch-pdf/{id}")
	    public ResponseEntity<byte[]> fetchPdf(@PathVariable Long id) {
	        Feedback feedback = feedbackService.getFeedbackById(id);

	        if (feedback == null || feedback.getAttachment() == null) {
	            return ResponseEntity.notFound().build(); // Handle missing feedback or file
	        }

	        byte[] attachmentData = feedback.getAttachment();

	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=attachment.pdf") // Adjust filename as needed
	                .contentType(MediaType.APPLICATION_PDF) // Default to application/pdf
	                .body(attachmentData);
	    }

	    
	    @GetMapping("/deletefeedbackinfo")
	    public String deleteFeedback(@RequestParam("id") Long id) {
	        feedbackService.deleteFeedbackById(id);  // Call service to delete feedback
	        return "redirect:/hrms/GoalGrid";       // Redirect to feedback list page
	    }
	    
	    
	  //FETCHING
	    @GetMapping("/feedback/details")
	    @ResponseBody
	    public Employee getFDetails(@RequestParam("employeeuid") String employeeuid){
	    	return employeeService.getEdetailsByuid(employeeuid);
	    	
	    	
	    }
	    
	    
	    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    
	    //GoalSetting Controller
	    
	    @Autowired
	    private GoalService goalService;
	   
	    // Show goal creation form
	    @GetMapping("/goalform")
	    public String showGoalForm(Model model) {
	    	List<String> employeeuid = employeeService.getAlleDetails();
	        model.addAttribute("employeeuid",employeeuid);
	       model.addAttribute("goal", new Goal());
	        return "hrms/Goalsetting"; // Refers to the Thymeleaf template for creating/updating goals
	    }

	     // Save a new goal
	    @PostMapping("/savegoalform")
	    public String saveGoal1(@RequestParam("employeeuid") String employeeuid,@ModelAttribute Goal goal) {
	    	goal.setEmployeeUID(employeeuid);
	    	goalService.saveGoal(goal);
	        return "redirect:/hrms/GoalGrid"; // Redirect to the list page after saving
	    }
	    
	   
	    
	 // Show View Attendance
		@GetMapping("/viewAllgoal")
	    @ResponseBody
	    public Map<String, Object> viewAllGoal() {        
	        List<String> headers = List.of("ID", "Employee ID", "First Name", "Last Name", "Department", "Goal Title", "Target Date", "Priority", "Status");
	        
	        List<Map<String, Object>> goalInfoList = goalService.getAllGoals();
	        
	        List<Map<String, String>> goalList = new ArrayList<>();
	        
	        for (Map<String, Object> goalInfo : goalInfoList) {
	            Map<String, String> row = new HashMap<>();
	            row.put("ID", String.valueOf(goalInfo.get("id")));
	            row.put("Employee ID", String.valueOf(goalInfo.get("employeeuid")));
	            row.put("First Name", String.valueOf(goalInfo.get("first_name")));
	            row.put("Last Name", String.valueOf(goalInfo.get("last_name")));
	            row.put("Department", String.valueOf(goalInfo.get("departmentname")));
	            row.put("Goal Title", String.valueOf(goalInfo.get("title")));
	            row.put("Target Date", String.valueOf(goalInfo.get("target_date")));
	            row.put("Priority", String.valueOf(goalInfo.get("priority")));
	            row.put("Status", String.valueOf(goalInfo.get("status")));
	            
	            goalList.add(row);
	        }
	        
	        Map<String, Object> response = new HashMap<>();
	        response.put("headers", headers);
	        response.put("data", goalList);
	        response.put("status", "success");
	        
	        return response;
	    }
	 
	    
	    @GetMapping("/updategoal/{id}")
	    public String updateGoal(@PathVariable Long id, Model model) {
	        Goal goal = goalService.getGoalById(id);  
	        model.addAttribute("goal", goal);  
	        return "hrms/Goalsetting";  
	    }



	    @PostMapping("/updategoal")
	    public String  updateGoal(@ModelAttribute Goal goal,RedirectAttributes redirectAttributes) {
	    	try {
	    		goalService.updateGoal(goal);
	    	
	    	redirectAttributes.addFlashAttribute("message","goal updated succssfully");
	    	} catch (Exception e) {
	        	redirectAttributes.addFlashAttribute("errorMessage","eroor occur");
			}
			return "redirect:/hrms/GoalGrid";
	    	
	    }
	    
	
	    @GetMapping("/deletegoalinfo")
	    public String deleteGoal(@RequestParam("id") Long id) {
	        goalService.deleteGoalById(id);  // Call service to delete feedback
	        return "redirect:/hrms/GoalGrid";       
	    }
	    
	    
	  //FETCHING
	    @GetMapping("/goal/details")
	    @ResponseBody
	    public Employee getGDetails(@RequestParam("employeeuid") String employeeuid){
	    	return employeeService.getEdetailsByuid(employeeuid);
	    	
	    	
	    }
	    
}
