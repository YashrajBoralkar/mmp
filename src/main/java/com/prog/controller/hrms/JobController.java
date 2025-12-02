package com.prog.controller.hrms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

import com.prog.model.erp.Candidate;
import com.prog.model.erp.Employee;
import com.prog.model.erp.InterviewSchedule;
import com.prog.model.erp.Job;
import com.prog.service.hrms.CandidateService;
import com.prog.service.hrms.InterviewScheduleService;
import com.prog.service.hrms.JobService;
@Controller
public class JobController {
	
	@Autowired
	 private  CandidateService candidateService;
	 @Autowired
	   private InterviewScheduleService interviewScheduleService;
	 @Autowired
	 	private JobService jobService;

	
	
	 
	@GetMapping("/hrms/JobGrid")
    public  String viewgrid() {
    	return "HRMSgrid/Jobgrid";
    }
		
	//CANDIDATE Controller
	
	 
	@GetMapping("/candidateapplication")
    public String showCandidateApplicationForm(Model model) {
        List<String> jobuid = jobService.getAlljobDetails();
        model.addAttribute("jobuid",jobuid);
        model.addAttribute("candidate", new Candidate()); // Prepare an empty candidate object
        return "hrms/candidateapplicationform"; // Return the form view
    }
    
    @PostMapping("/candidateapplication")
    public String submitCandidateApplication(@RequestParam("jobuid")String jobuid,
            @ModelAttribute Candidate candidate,
            @RequestParam("file") MultipartFile file) throws IOException {
    	candidate.setJobuid(jobuid);
    	// Check if a file is uploaded
        if (!file.isEmpty()) {
            candidate.setResume(file.getBytes()); // Save the binary file
            candidate.setResumeName(file.getOriginalFilename()); // Save the file name
        }

        // Save the candidate details
        candidateService.saveCandidate(candidate);

        return "redirect:/hrms/JobGrid"; // Return to the form or success page
    }

    // List all candidates
    
    @GetMapping("/viewcandidatelist")
    @ResponseBody
    public Map<String, Object> getAllCandidate() {        
        List<String> headers = List.of("ID", "Job Id","Candidate Name", "Email", "Highest Qualification","Total Work Experience", "Application Status", "Final Decision");
        
        List<Map<String, Object>> candidateList = candidateService.getAllCandidate();
        
        List<Map<String, String>> CandidateList = new ArrayList<>();
        
        for (Map<String, Object> candidateInfo : candidateList) {
            Map<String, String> row = new HashMap<>();
            row.put("ID", String.valueOf(candidateInfo.get("id")));
            row.put("Job Id", String.valueOf(candidateInfo.get("jobuid")));
            row.put("Candidate Name", String.valueOf(candidateInfo.get("name")));
            row.put("Email", String.valueOf(candidateInfo.get("email")));
            row.put("Contact Number", String.valueOf(candidateInfo.get("contact")));
            row.put("Highest Qualification", String.valueOf(candidateInfo.get("highest_qualification")));
            row.put("Total Work Experience", String.valueOf(candidateInfo.get("total_experience")));
            row.put("Application Status", String.valueOf(candidateInfo.get("status")));
            
            CandidateList.add(row);
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("headers", headers);
        response.put("data", CandidateList);
        response.put("status", "success");
        
        return response;
    }
    // Fetch candidate details for updating
    @GetMapping("/candidate/updatecandidate/{id}")
    public String updateCandidateById(@PathVariable Long id, Model model) {
        try {
            Candidate candidate = candidateService.getCandidateById(id);

            if (candidate == null) {
                throw new IllegalArgumentException("Candidate not found with id: " + id);
            }

            // Add candidate details to the model
            model.addAttribute("candidate", candidate);

            // Add resume name to the model if it exists
            if (candidate.getResumeName() != null && !candidate.getResumeName().isEmpty()) {
                model.addAttribute("resumeName", candidate.getResumeName());
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "An error occurred while fetching the candidate details.");
        }

        return "hrms/candidateapplicationform"; // Return the Thymeleaf template
    }

    @PostMapping("/candidate/update")
    public String updateCandidate(
            @PathVariable Long id,
            @ModelAttribute Candidate candidate,
            @RequestParam(value = "file", required = false) MultipartFile file,
            RedirectAttributes redirectAttributes) {

        try {
            // Set the candidate ID
            candidate.setId(id);

            // Check if a new file is uploaded
            if (file != null && !file.isEmpty()) {
                candidate.setResume(file.getBytes()); // Update the binary file
                candidate.setResumeName(file.getOriginalFilename()); // Update the file name
            }

            // Update candidate details
            candidateService.updatecandidate(candidate);

            // Add success message
            redirectAttributes.addFlashAttribute("successMessage", "Candidate updated successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while updating the candidate.");
        }

        return "redirect:/hrms/JobGrid"; // Redirect to the candidate list page
    }

    @GetMapping("/fetch-file/{id}")
    public ResponseEntity<byte[]> fetchFile(@PathVariable("id") Long id) {
        // Retrieve the candidate by ID
        Candidate candidate = candidateService.getCandidateById(id);

        if (candidate == null || candidate.getResume() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if candidate or file is missing
        }
        		// Return the file as an attachment
       
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDisposition(ContentDisposition.inline().filename("certificate.pdf,jpg,png").build()); // Generic file name // Set generic binary content type
        	
        return new ResponseEntity<>(candidate.getResume(),headers, HttpStatus.OK);
    }

    
    @GetMapping("/candidate/delete")
    public String deleteCandidate(@RequestParam("id") Long id) {
        candidateService.deleteCandidateById(id);
        return "redirect:/hrms/JobGrid";
    }
    
    
    //FETCHING
    @GetMapping("/candidate/jobdetails")
    @ResponseBody
    public Job getjobdetail(@RequestParam("jobuid") String jobuid){
    	return jobService.getjobdetailsByuid(jobuid);
    	
    	
    }
    
    
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    //INterview Schedule Controller
   
    @GetMapping("/Interviewscheduleform")
    public String showForm(Model model) {
        model.addAttribute("interviewSchedule", new InterviewSchedule()); // Prepare an empty form object
        List<String> jobuid = jobService.getAlljobDetails();
        List<String> candidateuid = candidateService.getAllcandidateDetails();
        model.addAttribute("jobuid",jobuid);
        model.addAttribute("candidateuid",candidateuid);
       return "hrms/interviewscheduleform";
    }
    @PostMapping("/save-interview-schedule")
    public String saveInterviewSchedule(@RequestParam("jobuid")String jobuid,@RequestParam("candidateuid")String candidateuid,@ModelAttribute InterviewSchedule interviewSchedule) {
    	interviewSchedule.setJobuid(jobuid);
    	interviewSchedule.setCandidateuid(candidateuid);
    	interviewScheduleService.saveInterviewSchedule(interviewSchedule); // Save using the service layer
        return "redirect:/hrms/JobGrid"; // Redirect to avoid duplicate submissions
    }
   
    @GetMapping("/viewinterviewlist")
    @ResponseBody
    public Map<String, Object> getAllInterviewSchedule() {        
        List<String> headers = List.of("ID", "Candidate Name", "Job Title", "Interview Date","Interview Time", "Mode Of Interview", "Final Decision");
        
        List<Map<String, Object>> scheduldedinterviewList = interviewScheduleService.getAllInterviewSchedule();
        
        List<Map<String, String>> interviewschduleList = new ArrayList<>();
        
        for (Map<String, Object> interviewInfo : scheduldedinterviewList) {
            Map<String, String> row = new HashMap<>();
            row.put("ID", String.valueOf(interviewInfo.get("id")));
            row.put("Candidate Name", String.valueOf(interviewInfo.get("name")));
            row.put("Job Title", String.valueOf(interviewInfo.get("title")));
            row.put("Interview Date", String.valueOf(interviewInfo.get("interview_date")));
            row.put("Interview Time", String.valueOf(interviewInfo.get("interview_time")));
            row.put("Mode Of Interview", String.valueOf(interviewInfo.get("mode_of_interview")));
            row.put("Final Decision", String.valueOf(interviewInfo.get("final_decision")));
            
            interviewschduleList.add(row);
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("headers", headers);
        response.put("data", interviewschduleList);
        response.put("status", "success");
        
        return response;
    }
   
    @GetMapping("/interview/update/{id}")
    public String editInterview(@PathVariable Long id, Model model) {
        InterviewSchedule interviewSchedule = interviewScheduleService.getInterviewById(id);
        model.addAttribute("interviewSchedule", interviewSchedule);
        return "hrms/interviewscheduleform";
    }
    @PostMapping("/interview/update")
    public String updateInterview(@ModelAttribute("interviewSchedule") InterviewSchedule interviewSchedule,RedirectAttributes redirectAttributes) {

    	interviewScheduleService.update(interviewSchedule); // Save updated data
    	 redirectAttributes.addFlashAttribute("successMessage", "Interview updated successfully!");
    	 return "redirect:/hrms/JobGrid"; // Redirect to list page
        
    }
    @GetMapping("/interview/deleteinfo")
    public String deleteCandidateById( @RequestParam("id") Long id) {
        interviewScheduleService.deleteCandidateById(id);
        return "redirect:/hrms/JobGrid";
    }  
    
    
   //FETCHING
    @GetMapping("/interview/jobdetails")
    @ResponseBody
    public Job getjobdetails(@RequestParam("jobuid") String jobuid){
    	return jobService.getjobdetailsByuid(jobuid);  	
    }
    //FETCHING
    @GetMapping("/interview/candidatedetails")
    @ResponseBody
    public Candidate getcandidatedetails(@RequestParam("candidateuid") String candidateuid){
    	return candidateService.getcandidatedetailsByuid(candidateuid);
    	
    	
    }
    
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 

	
	//Job Posting Controller
	
	
    @GetMapping("/jobposting")
    public String showJobPostingForm(Model model) {
        model.addAttribute("job", new Job()); // Prepare an empty job object
        return "hrms/job"; // Return the view name for the form
    }

    @PostMapping("/jobpost")
    public String submitJobPosting(@ModelAttribute Job job,Model model) {
		
    	    jobService.saveJob(job);  // Save the job to the database
            model.addAttribute("successMessage", "Job posted successfully!");
            return "redirect:/hrms/JobGrid";  // Redirect to job list page after success
        
    
    }
   
 // Show All Posted Jobs
 	@GetMapping("/viewallpostedjobs")
     @ResponseBody
     public Map<String, Object> viewAllPostedJobs() {        
         List<String> headers = List.of("ID", "Job ID", "Title", "Department","Posted Date", "Requirement", "Application Deadline");
         
         List<Map<String, Object>> postedJobList = jobService.getAllJob();
         
         List<Map<String, String>> jobList = new ArrayList<>();
         
         for (Map<String, Object> jobsInfo : postedJobList) {
             Map<String, String> row = new HashMap<>();
             row.put("ID", String.valueOf(jobsInfo.get("id")));
             row.put("Job ID", String.valueOf(jobsInfo.get("jobuid")));
             row.put("Title", String.valueOf(jobsInfo.get("title")));
             row.put("Department", String.valueOf(jobsInfo.get("department")));
             row.put("Posted Date", String.valueOf(jobsInfo.get("posted_date")));
             row.put("Requirement", String.valueOf(jobsInfo.get("requirement")));
             row.put("Application Deadline", String.valueOf(jobsInfo.get("application_deadline")));
             
             jobList.add(row);
         }
         
         Map<String, Object> response = new HashMap<>();
         response.put("headers", headers);
         response.put("data", jobList);
         response.put("status", "success");
         
         return response;
     }
    
   
    @GetMapping("/edit/{id}")
    public String editJob(@PathVariable Long id, Model model) {
        Job job = jobService.getJobById(id); // Fetch the job by ID
        model.addAttribute("job", job); // Add the job to the model for Thymeleaf
        return "hrms/job"; // Return the edit page view
    }



    @PostMapping("/updatejob")
    public String updateJob(@ModelAttribute("job") Job job) {
        System.out.println("Job received for update: " + job); // Log the job object
        jobService.updateJob(job); // Service method to update job
        return "redirect:/hrms/JobGrid"; // Redirect after update
    }
    @GetMapping("/deletepostedjob")
    public String deleteJob(@RequestParam("id") Long id) {
         jobService.deleteJob(id);
        
        return "redirect:/hrms/JobGrid"; // Redirect to the job list page
    }

	

}
