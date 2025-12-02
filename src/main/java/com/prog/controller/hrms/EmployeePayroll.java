package com.prog.controller.hrms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeePayroll {

	@GetMapping("/EmpoyeePayrollGrid")
	public String showgrid(){
		return "EmployeePayroll";
		
	}
	
	@GetMapping("/leaveattendceform")
	public String showform(){
		return "EmployeePayroll/EmployeeLeaveAndAttendanceForm";
		
	}
	@GetMapping("/empsalary")
	public String showsalary() {
		return "EmployeePayroll/employeesalaryform";
	}
	
	@GetMapping("/empbonus")
	public String showbonusform() {
		return "EmployeePayroll/employee_bonus_incentive_form";
	}
	
	@GetMapping("/emppayroll")
	public String showpayroll() {
		return "EmployeePayroll/employeepayroll1";
	}
	
}
