package com.prog.model.erp;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;


@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String employeeUID;
    
	private String firstName;
    private String middleName;
    private String lastName;

    private String dateOfBirth;

    private String gender;
    private String maritalStatus;
    private String nationality;
    private String departmentuid; // instead of Long departmentId
	
	private String departmentname;
	private String permanentAddressLine1;
    private String permanentAddressLine2;
    private String permanentCity;
    private String permanentState;
    private String permanentCountry;
    private String permanentZipCode;

    private String currentAddressLine1;
    private String currentAddressLine2;
    private String currentCity;
    private String currentState;
    private String currentCountry;
    private String currentZipCode;

    private String primaryContactNumber;
    private String alternateContactNumber;
    private String personalEmail;
    private String bloodGroup;

    private String emergencyContactName;
    private String emergencyRelationship;
    private String emergencyContactNumber;
    private String emergencyEmail;

    private String passportNumber;
    private String visaNumber;

    private String visaExpiryDate;

    @Lob
    private byte[] aadharSsnFile;
    @Lob
	private byte[] uploadProfilePhotoFile;
    @Lob
	private byte[] panTaxIdFile;
    @Lob
	private byte[] resumeFile;

    @Lob
    private byte[] educationCertificatesFile;

    @Lob
    private byte[] professionalCertificatesFile;
    private String aadharSsnFileName;
    private String uploadProfilePhotoFileName;
    private String panTaxIdFileName;
    private String resumeFileName;
    private String educationCertificatesFileName;
    private String professionalCertificatesFileName;
    
    private String insertdate;
    private String updatedate;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    
    public String getAadharSsnFileName() {
        return aadharSsnFileName;
    }

    public void setAadharSsnFileName(String aadharSsnFileName) {
        this.aadharSsnFileName = aadharSsnFileName;
    }

    public String getUploadProfilePhotoFileName() {
        return uploadProfilePhotoFileName;
    }

    public void setUploadProfilePhotoFileName(String uploadProfilePhotoFileName) {
        this.uploadProfilePhotoFileName = uploadProfilePhotoFileName;
    }

    public String getPanTaxIdFileName() {
        return panTaxIdFileName;
    }

    public void setPanTaxIdFileName(String panTaxIdFileName) {
        this.panTaxIdFileName = panTaxIdFileName;
    }

    public String getResumeFileName() {
        return resumeFileName;
    }

    public void setResumeFileName(String resumeFileName) {
        this.resumeFileName = resumeFileName;
    }

    public String getEducationCertificatesFileName() {
        return educationCertificatesFileName;
    }

    public void setEducationCertificatesFileName(String educationCertificatesFileName) {
        this.educationCertificatesFileName = educationCertificatesFileName;
    }

    public String getProfessionalCertificatesFileName() {
        return professionalCertificatesFileName;
    }

    public void setProfessionalCertificatesFileName(String professionalCertificatesFileName) {
        this.professionalCertificatesFileName = professionalCertificatesFileName;
    }


    
    public String getEmployeeUID() {
		return employeeUID;
	}
	public void setEmployeeUID(String employeeUID) {
		this.employeeUID = employeeUID;
	}
   
    
   
	public byte[] getUploadProfilePhotoFile() {
		return uploadProfilePhotoFile;
	}
	public void setUploadProfilePhotoFile(byte[] uploadProfilePhotoFile) {
		this.uploadProfilePhotoFile = uploadProfilePhotoFile;
	}
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	
	public String getPermanentAddressLine1() {
		return permanentAddressLine1;
	}
	public void setPermanentAddressLine1(String permanentAddressLine1) {
		this.permanentAddressLine1 = permanentAddressLine1;
	}
	public String getPermanentAddressLine2() {
		return permanentAddressLine2;
	}
	public void setPermanentAddressLine2(String permanentAddressLine2) {
		this.permanentAddressLine2 = permanentAddressLine2;
	}
	public String getPermanentCity() {
		return permanentCity;
	}
	public void setPermanentCity(String permanentCity) {
		this.permanentCity = permanentCity;
	}
	public String getPermanentState() {
		return permanentState;
	}
	public void setPermanentState(String permanentState) {
		this.permanentState = permanentState;
	}
	public String getPermanentCountry() {
		return permanentCountry;
	}
	public void setPermanentCountry(String permanentCountry) {
		this.permanentCountry = permanentCountry;
	}
	public String getPermanentZipCode() {
		return permanentZipCode;
	}
	public void setPermanentZipCode(String permanentZipCode) {
		this.permanentZipCode = permanentZipCode;
	}
	public String getCurrentAddressLine1() {
		return currentAddressLine1;
	}
	public void setCurrentAddressLine1(String currentAddressLine1) {
		this.currentAddressLine1 = currentAddressLine1;
	}
	public String getCurrentAddressLine2() {
		return currentAddressLine2;
	}
	public void setCurrentAddressLine2(String currentAddressLine2) {
		this.currentAddressLine2 = currentAddressLine2;
	}
	public String getCurrentCity() {
		return currentCity;
	}
	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}
	public String getCurrentState() {
		return currentState;
	}
	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}
	public String getCurrentCountry() {
		return currentCountry;
	}
	public void setCurrentCountry(String currentCountry) {
		this.currentCountry = currentCountry;
	}
	public String getCurrentZipCode() {
		return currentZipCode;
	}
	public void setCurrentZipCode(String currentZipCode) {
		this.currentZipCode = currentZipCode;
	}
	public String getPrimaryContactNumber() {
		return primaryContactNumber;
	}
	public void setPrimaryContactNumber(String primaryContactNumber) {
		this.primaryContactNumber = primaryContactNumber;
	}
	public String getAlternateContactNumber() {
		return alternateContactNumber;
	}
	public void setAlternateContactNumber(String alternateContactNumber) {
		this.alternateContactNumber = alternateContactNumber;
	}
	public String getPersonalEmail() {
		return personalEmail;
	}
	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getEmergencyContactName() {
		return emergencyContactName;
	}
	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}
	public String getEmergencyRelationship() {
		return emergencyRelationship;
	}
	public void setEmergencyRelationship(String emergencyRelationship) {
		this.emergencyRelationship = emergencyRelationship;
	}
	public String getEmergencyContactNumber() {
		return emergencyContactNumber;
	}
	public void setEmergencyContactNumber(String emergencyContactNumber) {
		this.emergencyContactNumber = emergencyContactNumber;
	}
	public String getEmergencyEmail() {
		return emergencyEmail;
	}
	public void setEmergencyEmail(String emergencyEmail) {
		this.emergencyEmail = emergencyEmail;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	public String getVisaNumber() {
		return visaNumber;
	}
	public void setVisaNumber(String visaNumber) {
		this.visaNumber = visaNumber;
	}
	public String getVisaExpiryDate() {
		return visaExpiryDate;
	}
	public void setVisaExpiryDate(String visaExpiryDate) {
		this.visaExpiryDate = visaExpiryDate;
	}
	
	public byte[] getAadharSsnFile() {
		return aadharSsnFile;
	}
	public void setAadharSsnFile(byte[] aadharSsnFile) {
		this.aadharSsnFile = aadharSsnFile;
	}
	public byte[] getPanTaxIdFile() {
		return panTaxIdFile;
	}
	public void setPanTaxIdFile(byte[] panTaxIdFile) {
		this.panTaxIdFile = panTaxIdFile;
	}
	public byte[] getEducationCertificatesFile() {
		return educationCertificatesFile;
	}
	public void setEducationCertificatesFile(byte[] educationCertificatesFile) {
		this.educationCertificatesFile = educationCertificatesFile;
	}
	public byte[] getProfessionalCertificatesFile() {
		return professionalCertificatesFile;
	}
	public void setProfessionalCertificatesFile(byte[] professionalCertificatesFile) {
		this.professionalCertificatesFile = professionalCertificatesFile;
	}
	public byte[] getResumeFile() {
		return resumeFile;
	}
	public void setResumeFile(byte[] resumeFile) {
		this.resumeFile = resumeFile;
	}

	public String getDepartmentuid() {
		return departmentuid;
	}

	public void setDepartmentuid(String departmentuid) {
		this.departmentuid = departmentuid;
	}

	public String getDepartmentname() {
		return departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	
	@PrePersist
    protected void onCreate() {
        this.insertdate = LocalDateTime.now().format(formatter);
        this.updatedate = LocalDateTime.now().format(formatter);
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedate = LocalDateTime.now().format(formatter);
    }
	
    
}