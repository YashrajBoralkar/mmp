package com.prog.controller.hrms;

import java.io.IOException;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

import com.prog.model.erp.Attendance;
import com.prog.model.erp.Employee;
import com.prog.model.erp.Leaveinfo;
import com.prog.model.erp.Shiftinfo;
import com.prog.service.admin.departmentservice;
import com.prog.service.hrms.AttendanceService;
import com.prog.service.hrms.EmployeeService;
import com.prog.service.hrms.LeaveFormService;
import com.prog.service.hrms.ShiftFormService;


@Controller
public class AttendanceController {
	@Autowired
	private AttendanceService attendanceService;
	 @Autowired
	 private departmentservice departmentService;
	 @Autowired
	  private EmployeeService employeeService;
	      
    
   @GetMapping("/hrms/AttendanceGrid")
   public  String viewgrid() {
   	return "HRMSgrid/Atdgrid";
   }

	@GetMapping("/attendance")
	public String getAttendanceForm(Model model) {
        List<String> employeeuid = employeeService.getAlleDetails();
        model.addAttribute("employeeuid",employeeuid);
        return "hrms/attendanceform";
	}
    //Add attendance in table
	@PostMapping("/saveattendance")
	public String saveAttendanceData(@RequestParam("employeeuid") String employeeuid,@ModelAttribute Attendance attendanceForm) {
		attendanceForm.setEmployeeuid(employeeuid);
		attendanceService.saveAttendance(attendanceForm);
	    return "redirect:/hrms/AttendanceGrid";
	}

	// Show View Attendance
	@GetMapping("/viewAllattendance")
    @ResponseBody
    public Map<String, Object> viewAllAttendance() {        
        List<String> headers = List.of("ID", "Employee ID", "First Name", "Last Name", "Department", "Attendance Date", "Check In Time", "Check Out Time", "Total Working Time");
        
        List<Map<String, Object>> attendanceInfoList = attendanceService.getAllAttendance();
        
        List<Map<String, String>> attendanceList = new ArrayList<>();
        
        for (Map<String, Object> attendanceInfo : attendanceInfoList) {
            Map<String, String> row = new HashMap<>();
            row.put("ID", String.valueOf(attendanceInfo.get("id")));
            row.put("Employee ID", String.valueOf(attendanceInfo.get("employeeuid")));
            row.put("First Name", String.valueOf(attendanceInfo.get("first_name")));
            row.put("Last Name", String.valueOf(attendanceInfo.get("last_name")));
            row.put("Department", String.valueOf(attendanceInfo.get("departmentname")));
            row.put("Attendance Date", String.valueOf(attendanceInfo.get("attendance_date")));
            row.put("Check In Time", String.valueOf(attendanceInfo.get("check_in_time")));
            row.put("Check Out Time", String.valueOf(attendanceInfo.get("check_out_time")));
            row.put("Total Working Time", String.valueOf(attendanceInfo.get("total_working_time")));
            
            attendanceList.add(row);
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("headers", headers);
        response.put("data", attendanceList);
        response.put("status", "success");
        
        return response;
    }

	@GetMapping("/updateAttendance/{id}")
	public String showUpdatedForm(@PathVariable("id") Long id, Model model) {
		Attendance attendanceForm = attendanceService.getAttendanceById(id);
		model.addAttribute("attendance", attendanceForm);
		return "hrms/attendanceform"; // Name of the update attendance page (HTML file)
	}

	// Show View Attendance
	@PostMapping("/updateExistingAttendance")
	public String updateAttendance(@ModelAttribute Attendance attendanceForm) {
		attendanceService.updateAttendanceById(attendanceForm); // Save or update attendance in the database
		return "redirect:/hrms/AttendanceGrid"; // Redirect to the viewattendance.html list page
	}

	@GetMapping("/delete_atdinfo")
	public String deleteAttendance(@RequestParam("id") Long id) {
	    attendanceService.deleteById(id);
	    return "redirect:/hrms/AttendanceGrid"; // Redirects after deletion
	}

	
	@GetMapping("/attendance/details")
    @ResponseBody
    public Employee getEDetails(@RequestParam("employeeuid") String employeeuid){
    	return employeeService.getEdetailsByuid(employeeuid);
    	
    	
    }
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	
	
	
	/* Leave Controller **/
	@Autowired
	private LeaveFormService leaveService;

	@GetMapping("/leave")
	public String getLeaveForm(Model model) {
		 List<String> employeeuid = employeeService.getAlleDetails();
	        model.addAttribute("employeeuid", employeeuid);
	        // Employee map
	        List<Employee> employeeList = employeeService.getBasicEmployeeDetails();
	        Map<String, String> approverMap = new LinkedHashMap<>();
	        for (Employee emp : employeeList) {
	            String firstName = emp.getFirstName() != null ? emp.getFirstName() : "";
	            String lastName = emp.getLastName() != null ? emp.getLastName() : "";
	            approverMap.put(emp.getEmployeeUID(), (firstName + " " + lastName).trim());
	        }
	        model.addAttribute("approverMap", approverMap);

	        return "hrms/leaveapplication";
	}

	@PostMapping("/saveleave")
	public String saveLeaveData(@RequestParam("employeeuid") String employeeuid,@RequestParam("file") MultipartFile file, 
            @ModelAttribute Leaveinfo leaveForm) throws IOException {
		if (!file.isEmpty()) {
			leaveForm.setSupportingDocument(file.getBytes());
			leaveForm.setDocName(file.getOriginalFilename());
		}
		// Save the leaveForm details
		leaveForm.setEmployeeuid(employeeuid);
		leaveService.saveLeave(leaveForm);
	    return "redirect:/hrms/AttendanceGrid"; // Return the name of the view to render
	}


	
	// Show LeaveInfo List
		@GetMapping("/viewleavelist")
	    @ResponseBody
	    public Map<String, Object> viewAllleave() {        
	        List<String> headers = List.of("ID", "Employee ID", "First Name", "Last Name", "Department", "Leave Application Date", "Total Days", "Reason");
	        
	        List<Map<String, Object>> leaveInfoList = leaveService.getAllLeave();
	        
	        List<Map<String, String>> leaveList = new ArrayList<>();
	        
	        for (Map<String, Object> leavelist : leaveInfoList) {
	            Map<String, String> row = new HashMap<>();
	            row.put("ID", String.valueOf(leavelist.get("id")));
	            row.put("Employee ID", String.valueOf(leavelist.get("employeeuid")));
	            row.put("First Name", String.valueOf(leavelist.get("first_name")));
	            row.put("Last Name", String.valueOf(leavelist.get("last_name")));
	            row.put("Department", String.valueOf(leavelist.get("departmentname")));
	            row.put("Leave App Date", String.valueOf(leavelist.get("leaveapplicationdate")));
	            row.put("Total Days", String.valueOf(leavelist.get("total_days")));
	            row.put("Reason", String.valueOf(leavelist.get("reason")));
	            
	            leaveList.add(row);
	        }
	        
	        Map<String, Object> response = new HashMap<>();
	        response.put("headers", headers);
	        response.put("data", leaveList);
	        response.put("status", "success");
	        
	        return response;
	    }

	
	
	@GetMapping("/updateLeave/{id}")
	public String showUpdatedLeaveForm(@PathVariable("id") Long id, Model model) {
		Leaveinfo leaveForm = leaveService.getLeaveById(id);
		model.addAttribute("leave", leaveForm);
		 // Employee map
        List<Employee> employeeList = employeeService.getBasicEmployeeDetails();
        Map<String, String> approverMap = new LinkedHashMap<>();
        for (Employee emp : employeeList) {
            String firstName = emp.getFirstName() != null ? emp.getFirstName() : "";
            String lastName = emp.getLastName() != null ? emp.getLastName() : "";
            approverMap.put(emp.getEmployeeUID(), (firstName + " " + lastName).trim());
        }
        model.addAttribute("approverMap", approverMap);

		return "hrms/leaveapplication"; // Name of the update page (HTML file)
	}
	
	// Show View leave
		@PostMapping("/updateExistingLeave")
		public String updateLeave(
				@ModelAttribute Leaveinfo leaveForm,
				@RequestParam(value="file", required= false) MultipartFile file, 
	            RedirectAttributes redirectAttributes) throws IOException {
			
			try {
				
				
				if (file != null && !file.isEmpty()) {
					leaveForm.setSupportingDocument(file.getBytes());//update
					leaveForm.setDocName(file.getOriginalFilename());//update file name
				}
				leaveService.updateLeaveById(leaveForm);
				
				redirectAttributes.addFlashAttribute("Successfully Updated", " Supporting Document updated successfully!");
			}catch(Exception e) {
				e.printStackTrace();
	            redirectAttributes.addFlashAttribute("error Message", "An error occurred while updating the Supporting Document.");
			}
			
			leaveService.updateLeaveById(leaveForm); // Save or update Shiftinfo in the database
			return "redirect:/hrms/AttendanceGrid"; // Redirect to the viewshift.html list page
		}


		/*
		 * @GetMapping("/fetch-file/{id}") public ResponseEntity<byte[]>
		 * fetchFile(@PathVariable("id") Long id) { // Retrieve the candidate by ID
		 * LeaveForm leaveForm = leaveService.getLeaveById(id);
		 * 
		 * if (leaveForm == null || leaveForm.getSupportingDocument() == null) { return
		 * new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if candidate or
		 * file is missing } // Return the file as an attachment
		 * 
		 * HttpHeaders headers = new HttpHeaders();
		 * headers.setContentType(MediaType.APPLICATION_PDF);
		 * headers.setContentDisposition(ContentDisposition.inline().filename(
		 * "document.pdf,jpg,png").build()); // Generic file name // Set generic binary
		 * content type
		 * 
		 * return new
		 * ResponseEntity<>(leaveForm.getSupportingDocument(),headers,HttpStatus.OK); }
		 */

		
	@GetMapping("/deleteLeave")
	public String deleteLeave(@RequestParam("id") Long id) {
		leaveService.deleteById(id);
		return "redirect:/hrms/AttendanceGrid"; // Redirect to the viewleave.html list page
	}
	
	@GetMapping("/leave/Edetails")
    @ResponseBody
    public Employee getdetails(@RequestParam("employeeuid") String employeeuid){
    	return employeeService.getEdetailsByuid(employeeuid);
    	
    	
    }
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/* Shiftinfo Controller */
	@Autowired
	private ShiftFormService shiftService;

	@GetMapping("/shift")
	public String getShiftForm(Model model) {
		List<String> employeeuid = employeeService.getAlleDetails();
        model.addAttribute("employeeuid", employeeuid);
      
		return "hrms/shiftinfo";
	}

	@PostMapping("/saveshift")
	public String saveShiftData(@RequestParam("employeeuid") String employeeuid,Shiftinfo shiftForm) {
		shiftForm.setEmployeeuid(employeeuid);
		shiftService.saveShift(shiftForm);
		return "redirect:/hrms/AttendanceGrid";
	}

		// Show ShiftInfo List
			@GetMapping("/viewshiftlist")
		    @ResponseBody
		    public Map<String, Object> viewAllShift() {        
		        List<String> headers = List.of("ID", "Employee ID", "First Name", "Last Name", "Department", "Shift Date", "Shift Duration", "Shift Status");
		        
		        List<Map<String, Object>> shiftInfoList = shiftService.getAllShift();
		        
		        List<Map<String, String>> shiftList = new ArrayList<>();
		        
		        for (Map<String, Object> shiftlist : shiftInfoList) {
		            Map<String, String> row = new HashMap<>();
		            row.put("ID", String.valueOf(shiftlist.get("id")));
		            row.put("Employee ID", String.valueOf(shiftlist.get("employeeuid")));
		            row.put("First Name", String.valueOf(shiftlist.get("first_name")));
		            row.put("Last Name", String.valueOf(shiftlist.get("last_name")));
		            row.put("Department", String.valueOf(shiftlist.get("departmentname")));
		            row.put("Shift Date", String.valueOf(shiftlist.get("shift_date")));
		            row.put("Shift Duration", String.valueOf(shiftlist.get("shift_duration")));
		            row.put("Shift Status", String.valueOf(shiftlist.get("shift_status")));
		            
		            shiftList.add(row);
		        }
		        
		        Map<String, Object> response = new HashMap<>();
		        response.put("headers", headers);
		        response.put("data", shiftList);
		        response.put("status", "success");
		        
		        return response;
		    }
	
	  @GetMapping("/updateShift/{id}") 
	 public String showUpdatedShiftForm(@PathVariable("id") Long id, Model model) {
	  Shiftinfo shiftForm = shiftService.getShiftById(id);
	  model.addAttribute("shift", shiftForm); 
	  return "hrms/shiftinfo"; // Name of the update page (HTML file) 
	  }
	 

	// Show View Shiftinfo
	@PostMapping("/updateExistingShift")
	public String updateShift( Shiftinfo shiftForm) {
		shiftService.updateShiftById(shiftForm); // Save or update Shiftinfo in the database
		return "redirect:/hrms/AttendanceGrid"; // Redirect to the viewshift.html list page
	}

	@GetMapping("/deleteShift")
	public String deleteShift(@RequestParam("id") Long id) {
		shiftService.deleteById(id);
		return "redirect:/hrms/AttendanceGrid"; // Redirect to the viewshift.html list page
	}
	
	@GetMapping("/shift/Details")
    @ResponseBody
    public Employee getDetails(@RequestParam("employeeuid") String employeeuid){
    	return employeeService.getEdetailsByuid(employeeuid);
    	
    	
    }

	

}