package com.prog.controller.hrms;

import java.io.IOException;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prog.Dao.hrms.EmployeeDAO;
import com.prog.model.erp.Department;
import com.prog.model.erp.Employee;
import com.prog.model.erp.EmploymentInformation;
import com.prog.model.erp.PayrollDetails;
import com.prog.service.admin.departmentservice;
import com.prog.service.hrms.EmployeeService;
import com.prog.service.hrms.EmploymentInformationService;
import com.prog.service.hrms.PayrollDetailsService;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private departmentservice departmentService;
   @Autowired
   private EmployeeDAO employeeDAO;
    
    @GetMapping("/hrms/EmpGrid")
    public  String viewgrid() {
    	return "HRMSgrid/Empgrid";
    }
    
    @GetMapping("/empregister")
  public String getEmpRegisterForm(Model model) {
      model.addAttribute("employee", new Employee());

      List<String> departments = departmentService.getAlldepartment(); // this is correct
      model.addAttribute("dname", departments);

      return "hrms/empregister";
 }
    


    @PostMapping("/employee_registration")
    public String saveEmployeeRegister(
            @RequestParam("firstName") String firstName,
            @RequestParam("middleName") String middleName,
            @RequestParam("lastName") String lastName,
            @RequestParam("dateOfBirth") String dateOfBirth,
            @RequestParam("gender") String gender,
            @RequestParam("maritalStatus") String maritalStatus,
            @RequestParam("nationality") String nationality,
            @RequestParam("departmentname") String departmentname, // ✅ Only this is needed
            // Removed departmentName from @RequestParam

            @RequestParam("permanentAddressLine1") String permanentAddressLine1,
            @RequestParam("permanentAddressLine2") String permanentAddressLine2,
            @RequestParam("permanentCity") String permanentCity,
            @RequestParam("permanentState") String permanentState,
            @RequestParam("permanentCountry") String permanentCountry,
            @RequestParam("permanentZipCode") String permanentZipCode,
            @RequestParam("currentAddressLine1") String currentAddressLine1,
            @RequestParam("currentAddressLine2") String currentAddressLine2,
            @RequestParam("currentCity") String currentCity,
            @RequestParam("currentState") String currentState,
            @RequestParam("currentCountry") String currentCountry,
            @RequestParam("currentZipCode") String currentZipCode,
            @RequestParam("primaryContactNumber") String primaryContactNumber,
            @RequestParam("alternateContactNumber") String alternateContactNumber,
            @RequestParam("personalEmail") String personalEmail,
            @RequestParam("bloodGroup") String bloodGroup,
            @RequestParam("emergencyContactName") String emergencyContactName,
            @RequestParam("emergencyRelationship") String emergencyRelationship,
            @RequestParam("emergencyContactNumber") String emergencyContactNumber,
            @RequestParam("emergencyEmail") String emergencyEmail,
            @RequestParam("passportNumber") String passportNumber,
            @RequestParam("visaNumber") String visaNumber,
            @RequestParam("visaExpiryDate") String visaExpiryDate,
            @RequestParam("aadharSsnFile") MultipartFile aadharSsnFile,
            @RequestParam("uploadProfilePhotoFile") MultipartFile uploadProfilePhotoFile,
            @RequestParam("panTaxIdFile") MultipartFile panTaxIdFile,
            @RequestParam("educationCertificatesFile") MultipartFile educationCertificatesFile,
            @RequestParam("professionalCertificatesFile") MultipartFile professionalCertificatesFile,
            @RequestParam("resumeFile") MultipartFile resumeFile) throws IOException {

        Employee employee = new Employee();

        // Set form fields
        employee.setFirstName(firstName);
        employee.setMiddleName(middleName);
        employee.setLastName(lastName);
        employee.setDateOfBirth(dateOfBirth);
        employee.setGender(gender);
        employee.setMaritalStatus(maritalStatus);
        employee.setNationality(nationality);
        employee.setDepartmentname(departmentname); // fetched from DB

        // Address
        employee.setPermanentAddressLine1(permanentAddressLine1);
        employee.setPermanentAddressLine2(permanentAddressLine2);
        employee.setPermanentCity(permanentCity);
        employee.setPermanentState(permanentState);
        employee.setPermanentCountry(permanentCountry);
        employee.setPermanentZipCode(permanentZipCode);
        employee.setCurrentAddressLine1(currentAddressLine1);
        employee.setCurrentAddressLine2(currentAddressLine2);
        employee.setCurrentCity(currentCity);
        employee.setCurrentState(currentState);
        employee.setCurrentCountry(currentCountry);
        employee.setCurrentZipCode(currentZipCode);

        // Contact
        employee.setPrimaryContactNumber(primaryContactNumber);
        employee.setAlternateContactNumber(alternateContactNumber);
        employee.setPersonalEmail(personalEmail);
        employee.setBloodGroup(bloodGroup);

        // Emergency
        employee.setEmergencyContactName(emergencyContactName);
        employee.setEmergencyRelationship(emergencyRelationship);
        employee.setEmergencyContactNumber(emergencyContactNumber);
        employee.setEmergencyEmail(emergencyEmail);

        // Passport/Visa
        employee.setPassportNumber(passportNumber);
        employee.setVisaNumber(visaNumber);
        employee.setVisaExpiryDate(visaExpiryDate);

     // File Uploads
        if (!aadharSsnFile.isEmpty()) {
            employee.setAadharSsnFile(aadharSsnFile.getBytes());
            employee.setAadharSsnFileName(aadharSsnFile.getOriginalFilename()); // ✅ Set file name
        }
        if (!uploadProfilePhotoFile.isEmpty()) {
            employee.setUploadProfilePhotoFile(uploadProfilePhotoFile.getBytes());
            employee.setUploadProfilePhotoFileName(uploadProfilePhotoFile.getOriginalFilename()); // ✅
        }
        if (!panTaxIdFile.isEmpty()) {
            employee.setPanTaxIdFile(panTaxIdFile.getBytes());
            employee.setPanTaxIdFileName(panTaxIdFile.getOriginalFilename()); // ✅
        }
        if (!educationCertificatesFile.isEmpty()) {
            employee.setEducationCertificatesFile(educationCertificatesFile.getBytes());
            employee.setEducationCertificatesFileName(educationCertificatesFile.getOriginalFilename()); // ✅
        }
        if (!professionalCertificatesFile.isEmpty()) {
            employee.setProfessionalCertificatesFile(professionalCertificatesFile.getBytes());
            employee.setProfessionalCertificatesFileName(professionalCertificatesFile.getOriginalFilename()); // ✅
        }
        if (!resumeFile.isEmpty()) {
            employee.setResumeFile(resumeFile.getBytes());
            employee.setResumeFileName(resumeFile.getOriginalFilename()); // ✅
        }

        
    


        // Save
        employeeService.saveEmployee(employee);

        return "redirect:/hrms/EmpGrid";
    }

    @GetMapping("/delete_employee")
    public String deleteEmployee(@RequestParam("id") Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/hrms/EmpGrid"; // Redirect to employee list after deletion
    }
    
    @GetMapping("/updateemployee/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "hrms/empregister"; // Reuse the same registration form for updating
    }

   
    
    @PostMapping("/update_employee")
    public String updateEmployee(
        @RequestParam("id") Long id,
        @RequestParam("firstName") String firstName,
        @RequestParam("middleName") String middleName,
        @RequestParam("lastName") String lastName,
        @RequestParam("departmentname") String departmentname, 
        @RequestParam("dateOfBirth") String dateOfBirth,
        @RequestParam("gender") String gender,
        @RequestParam("maritalStatus") String maritalStatus,
        @RequestParam("nationality") String nationality,
        @RequestParam("permanentAddressLine1") String permanentAddressLine1,
        @RequestParam("permanentAddressLine2") String permanentAddressLine2,
        @RequestParam("permanentCity") String permanentCity,
        @RequestParam("permanentState") String permanentState,
        @RequestParam("permanentCountry") String permanentCountry,
        @RequestParam("permanentZipCode") String permanentZipCode,
        @RequestParam("currentAddressLine1") String currentAddressLine1,
        @RequestParam("currentAddressLine2") String currentAddressLine2,
        @RequestParam("currentCity") String currentCity,
        @RequestParam("currentState") String currentState,
        @RequestParam("currentCountry") String currentCountry,
        @RequestParam("currentZipCode") String currentZipCode,
        @RequestParam("primaryContactNumber") String primaryContactNumber,
        @RequestParam("alternateContactNumber") String alternateContactNumber,
        @RequestParam("personalEmail") String personalEmail,
        @RequestParam("bloodGroup") String bloodGroup,
        @RequestParam("emergencyContactName") String emergencyContactName,
        @RequestParam("emergencyRelationship") String emergencyRelationship,
        @RequestParam("emergencyContactNumber") String emergencyContactNumber,
        @RequestParam("emergencyEmail") String emergencyEmail,
        @RequestParam("passportNumber") String passportNumber,
        @RequestParam("visaNumber") String visaNumber,
        @RequestParam("visaExpiryDate") String visaExpiryDate,
        @RequestParam("aadharSsnFile") MultipartFile aadharSsnFile,
        @RequestParam("uploadProfilePhotoFile") MultipartFile uploadProfilePhotoFile,
        @RequestParam("panTaxIdFile") MultipartFile panTaxIdFile,
        @RequestParam("educationCertificatesFile") MultipartFile educationCertificatesFile,
        @RequestParam("professionalCertificatesFile") MultipartFile professionalCertificatesFile,
        @RequestParam("resumeFile") MultipartFile resumeFile
    ) throws IOException {
        Employee employee = employeeService.getEmployeeById(id);

        // Update fields
        employee.setFirstName(firstName);
        employee.setMiddleName(middleName);
        employee.setLastName(lastName);
        employee.setDepartmentname(departmentname);
        employee.setDateOfBirth(dateOfBirth);
        employee.setGender(gender);
        employee.setMaritalStatus(maritalStatus);
        employee.setNationality(nationality);
        employee.setPermanentAddressLine1(permanentAddressLine1);
        employee.setPermanentAddressLine2(permanentAddressLine2);
        employee.setPermanentCity(permanentCity);
        employee.setPermanentState(permanentState);
        employee.setPermanentCountry(permanentCountry);
        employee.setPermanentZipCode(permanentZipCode);
        employee.setCurrentAddressLine1(currentAddressLine1);
        employee.setCurrentAddressLine2(currentAddressLine2);
        employee.setCurrentCity(currentCity);
        employee.setCurrentState(currentState);
        employee.setCurrentCountry(currentCountry);
        employee.setCurrentZipCode(currentZipCode);
        employee.setPrimaryContactNumber(primaryContactNumber);
        employee.setAlternateContactNumber(alternateContactNumber);
        employee.setPersonalEmail(personalEmail);
        employee.setBloodGroup(bloodGroup);
        employee.setEmergencyContactName(emergencyContactName);
        employee.setEmergencyRelationship(emergencyRelationship);
        employee.setEmergencyContactNumber(emergencyContactNumber);
        employee.setEmergencyEmail(emergencyEmail);
        employee.setPassportNumber(passportNumber);
        employee.setVisaNumber(visaNumber);
        employee.setVisaExpiryDate(visaExpiryDate);

        // Update files
        if (aadharSsnFile != null && !aadharSsnFile.isEmpty()) {
            employee.setAadharSsnFile(aadharSsnFile.getBytes());
            employee.setAadharSsnFileName(aadharSsnFile.getOriginalFilename()); // ✅ IMPORTANT
        }

        if (uploadProfilePhotoFile != null && !uploadProfilePhotoFile.isEmpty()) {
            employee.setUploadProfilePhotoFile(uploadProfilePhotoFile.getBytes());
            employee.setUploadProfilePhotoFileName(uploadProfilePhotoFile.getOriginalFilename()); // ✅
        }

        if (panTaxIdFile != null && !panTaxIdFile.isEmpty()) {
            employee.setPanTaxIdFile(panTaxIdFile.getBytes());
            employee.setPanTaxIdFileName(panTaxIdFile.getOriginalFilename()); // ✅
        }

        if (educationCertificatesFile != null && !educationCertificatesFile.isEmpty()) {
            employee.setEducationCertificatesFile(educationCertificatesFile.getBytes());
            employee.setEducationCertificatesFileName(educationCertificatesFile.getOriginalFilename()); // ✅
        }

        if (professionalCertificatesFile != null && !professionalCertificatesFile.isEmpty()) {
            employee.setProfessionalCertificatesFile(professionalCertificatesFile.getBytes());
            employee.setProfessionalCertificatesFileName(professionalCertificatesFile.getOriginalFilename()); // ✅
        }

        if (resumeFile != null && !resumeFile.isEmpty()) {
            employee.setResumeFile(resumeFile.getBytes());
            employee.setResumeFileName(resumeFile.getOriginalFilename()); // ✅
        }


        employeeService.updateEmployee(employee);

        return "redirect:/hrms/EmpGrid";

    }
    
    
    @GetMapping("/hrms/grid/employee/list")
    @ResponseBody 
    public Map<String, Object> getEmployeeList() {
        // Step 1: Define headers (column names for the grid)
        List<String> headers = List.of(
            "ID", "Employee ID","First Name", "Last Name", "Department Name", "Contact Number", "Email", "Current City" );
        // Step 2: Get employee data
        List<Employee> employeeList = employeeService.getAllEmployees();

        // Step 3: Convert to List<Map<String, String>>
        List<Map<String, String>> employeeData = new ArrayList<>();
        for (Employee emp : employeeList)
        {
        	if (emp == null) {
                System.out.println("Null employee found in list");
                continue; // skip this loop
            }
            Map<String, String> row = new HashMap<>();
            row.put("ID", String.valueOf(emp.getId()));
            row.put("Employee ID", emp.getEmployeeUID() != null ? emp.getEmployeeUID() : ""); // safe
            row.put("First Name", emp.getFirstName());
            row.put("Last Name", emp.getLastName());
            row.put("Department Name", emp.getDepartmentname());
            row.put("Contact Number", emp.getPrimaryContactNumber());
            row.put("Email", emp.getPersonalEmail());
            row.put("Current City", emp.getCurrentCity());

            employeeData.add(row);
        }

        // Step 4: Return response
        Map<String, Object> response = new HashMap<>();
        response.put("headers", headers);
        response.put("data", employeeData);


        return response;
    }
    
    
    
//    @GetMapping("/api/departments")
//    @ResponseBody
//    public List<Department> getAllDepartments() {
//        return departmentService.getAllDepartments();
//    }
//
//    @GetMapping("/getDepartmentName/{uid}")
//    @ResponseBody
//    public String getDepartmentName(@PathVariable String uid) {
//        String departmentName = departmentDao.getDepartmentNameByUid(uid);
//        return departmentName != null ? departmentName : "";
//    }
//    @GetMapping("/empregister")
//    public String getEmpRegisterForm(Model model) {
//        model.addAttribute("employee", new Employee());
//
//        List<Department> departments = departmentService.getAllDepartments(); // this is correct
//        model.addAttribute("departments", departments);
//
//        return "hrms/empregister";
//    }
//   
    @GetMapping("/check-duplicate")
    @ResponseBody
    public boolean checkDuplicate(
            @RequestParam(required = false) String primaryContactNumber,
            @RequestParam(required = false) String personalEmail,
            @RequestParam(required = false) String passportNumber,
            @RequestParam(required = false) String visaNumber,
            @RequestParam(required = false) Long id // 👈 currently editing employee ID
    ) {

        if (primaryContactNumber != null && !primaryContactNumber.isEmpty()) {
            boolean existingId = employeeDAO.isDuplicatePrimaryContact(primaryContactNumber, id);
            return existingId ;
        }

        

        if (personalEmail != null && !personalEmail.isEmpty()) {
            boolean existingId = employeeDAO.isPersonalEmailDuplicate(personalEmail, id);
            return existingId ;
        }
        
        if (passportNumber != null && !passportNumber.isEmpty()) {
            boolean existingId= employeeDAO.isPassportNumberDuplicate(passportNumber, id);
            return existingId;
            
        }

        if (visaNumber != null && !visaNumber.isEmpty()) {
            boolean existingId = employeeDAO.isVisaNumberDuplicate(visaNumber, id);
            return existingId;
        }

        return false;
    }

    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    //Employee Information
    
    @Autowired
    private EmploymentInformationService employmentInformationService;


    @GetMapping("/employementForm")
    public String showEmploymentForm(Model model) {
        model.addAttribute("employmentInfo", new EmploymentInformation());
        List<String> employeeuid = employeeService.getAlleDetails();
        model.addAttribute("employeeuid",employeeuid);
        return "hrms/empinfo";
    }
    
    @PostMapping("/saveemployementForm")
    public String saveEmploymentInformation(@RequestParam("employeeuid") String employeeuid,@ModelAttribute EmploymentInformation employmentInformation, Model model) {
    	employmentInformation.setEmployeeuid(employeeuid);
    	employmentInformationService.saveEmploymentInfo(employmentInformation);
        model.addAttribute("successMessage", "Employment information added successfully!");
        return "redirect:/hrms/EmpGrid";
    }

    
    @GetMapping("/viewAllDetails")
    @ResponseBody
    public  Map<String, Object> viewAllDetails() {
    	
    	 List<String> headers = List.of("ID","Employee ID", "Department", "Designation", "Manager", 
                 "Office Address", "Date of Joining", "Shift Info Details", "Work Hours");
    	
    	 // Fetch data dynamically using the service layer
        List<Map<String,Object>> EmploymentInfoList = employmentInformationService.getAllEmploymentInfo();
        
        // Prepare data to match the dynamic structure expected by the view
        List<Map<String, String>> employmentdata = new ArrayList<>();
        for(Map<String, Object> employmentinfo :EmploymentInfoList) {
            Map<String, String> row = new HashMap<>();
            row.put("ID", String.valueOf(employmentinfo.get("id")));
            row.put("Employee ID",String.valueOf(employmentinfo.get("employeeuid")));
            row.put("Department", String.valueOf(employmentinfo.get("departmentname")));
            row.put("Designation",String.valueOf( employmentinfo.get("designation")));
            row.put("Manager", String.valueOf(employmentinfo.get("reporting_manager")));
            row.put("Office Address", String.valueOf(employmentinfo.get("office_address")));
            row.put("Date of Joining", String.valueOf( employmentinfo.get("date_of_joining")));
            row.put("Shift Info Details", String.valueOf(employmentinfo.get("shift_details")));
            row.put("Work Hours", String.valueOf(employmentinfo.get("work_hours")));
            employmentdata.add(row);
        	
        }
        // Prepare the response
        Map<String, Object> response = new HashMap<>();
        response.put("headers", headers);
        response.put("data", employmentdata);
        response.put("status", "success");

        return response;
    }
    
    @GetMapping("/delete_empinfo")
    public String deleteEmploymentInformation(@RequestParam("id") Long id) {
        employmentInformationService.deleteEmploymentInfo(id);
        return "redirect:/hrms/EmpGrid";
    }
    
   
    
    @GetMapping("/update/{id}")
    public String showEmployeeInfoUpdateForm(@PathVariable Long id, Model model) {
    	
        EmploymentInformation employmentInformation = employmentInformationService.getEmploymentInfoById(id);
        model.addAttribute("employmentInfo", employmentInformation);
        return "hrms/empinfo"; // Reuse the same form for updating
    }

    @PostMapping("/update")
    public String updateEmploymentInformation(@ModelAttribute EmploymentInformation employmentInformation) {
        employmentInformationService.updateEmploymentInfo(employmentInformation);
        return "redirect:/hrms/EmpGrid";
    }
    
    
    @GetMapping("/employment/edetails")
    @ResponseBody
    public Employee getEdetails(@RequestParam("employeeuid") String employeeuid){
    	return employeeService.getEdetailsByuid(employeeuid);
    	
    	
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
  
    //Payroll INFO
    
    @Autowired
    private PayrollDetailsService payrollDetailsService;
       

    // Show form to create new payroll details
    @GetMapping("/addpayroll")
    public String showPayrollForm(Model model) {
        model.addAttribute("payrollDetails", new PayrollDetails());
        List<String> employeeuid = employeeService.getAlleDetails();
        model.addAttribute("employeeuid",employeeuid);
        
        return "hrms/payrollForm";
    }

    
    @PostMapping("/addpayroll")
    public String savePayrollDetails(@RequestParam("employeeuid") String employeeuid, @ModelAttribute PayrollDetails payrollDetails) {
    	payrollDetails.setEmployeeuid(employeeuid);
    	payrollDetailsService.savePayrollDetails(payrollDetails);
        return "redirect:/hrms/EmpGrid"; // Add the query parameter
    }


    
    // View all payroll details
    @GetMapping("/payrollList")
    @ResponseBody
    public  Map<String, Object>  viewAllPayrollDetails(Model model) {
    	List<String> headers = List.of("ID","Employee ID", "Bank Name", "Branch Name", "Tax Residency");
   	
   	 // Fetch data dynamically using the service layer
       List<Map<String, Object>> payrolldetailsList = payrollDetailsService.getAllPayrollDetails();
       
       // Prepare data to match the dynamic structure expected by the view
       List<Map<String, String>> payrolldetailsdata = new ArrayList<>();
       
       for(Map<String, Object> payrolldetails : payrolldetailsList) {
           Map<String, String> row = new HashMap<>();
           row.put("ID", String.valueOf(payrolldetails.get("id")));
           row.put("Employee ID",String.valueOf(payrolldetails.get("employeeuid")));
           row.put("First Name",String.valueOf(payrolldetails.get("first_name")));
           row.put("Last Name", String.valueOf(payrolldetails.get("last_name")));
           row.put("Department", String.valueOf(payrolldetails.get("departmentname")));
           row.put("Bank Name", String.valueOf(payrolldetails.get("bank_name")));
           row.put("Branch Name", String.valueOf(payrolldetails.get("branch_name")));
           row.put("Tax Residency", String.valueOf(payrolldetails.get("tax_residency_status")));
           
           payrolldetailsdata.add(row);
       	
       }
       // Prepare the response
       Map<String, Object> response = new HashMap<>();
       response.put("headers", headers);
       response.put("data", payrolldetailsdata);
       response.put("status", "success");
       
       return response;
    	
    	
     
    }
  

    // Delete payroll details by ID
    @GetMapping("/deletePayroll")
    public String deletePayroll(@RequestParam Long id) {
        payrollDetailsService.deletePayrollDetails(id);
        return "redirect:/hrms/EmpGrid";
    }
    
 // Show form to update payroll details
    @GetMapping("/update/Payroll/{id}")
    public String showUpdatePayrollForm(@PathVariable Long id, Model model) {
    	
        PayrollDetails payrollDetails = payrollDetailsService.getPayrollDetailsById(id);
        model.addAttribute("payrollDetails", payrollDetails);
        return "hrms/payrollForm"; // Reuse the existing payrollForm
    }
    


    @PostMapping("/updatePayroll")
    public String updatePayrollDetails(@ModelAttribute PayrollDetails payrollDetails, Model model) {
        if (payrollDetails.getId() != null) {
            payrollDetailsService.savePayrollDetails(payrollDetails); // This should update
        } else {
            model.addAttribute("error", "Payroll ID is missing.");
        }
        return "redirect:/hrms/EmpGrid";
    }
    
    @GetMapping("/payroll/Edetails")
    @ResponseBody
    public Employee getdetails(@RequestParam("employeeuid") String employeeuid){
    	return employeeService.getEdetailsByuid(employeeuid);   	
    }
    
   
}