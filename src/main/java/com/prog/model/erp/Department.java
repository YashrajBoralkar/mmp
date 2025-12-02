package com.prog.model.erp;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    private String departmentuid;    
    private String departmentname;   
    private String departmentcode;   
    private String departmenttype;   
    private String description;      
    private String status;           
    private String insertdate;       
    private String updatedate;       


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDepartmentcode() {
        return departmentcode;
    }

    public void setDepartmentcode(String departmentcode) {
        this.departmentcode = departmentcode;
    }

    public String getDepartmenttype() {
        return departmenttype;
    }

    public void setDepartmenttype(String departmenttype) {
        this.departmenttype = departmenttype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInsertdate() {
        return insertdate;
    }

    public void setInsertdate(String insertdate) {
        this.insertdate = insertdate;
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }
}
