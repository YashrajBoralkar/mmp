package com.prog.model.erp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "inventoryentry")
public class InventoryEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String inventoryentryuid;
    private String producttype;     // Will be saved in lowercase + no spaces
    private int actualquantity;
    private String warehouseuid;
    private String entrydate;
    private String productuid;
    private String referenceuid;
    private String productname;
    private BigDecimal approvedquantity;

    @Transient
    private String warehousename;

    private String employeeuid;
    private String insertdate;
    private String updatedate;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // ---- ALL GETTERS & SETTERS ---- //

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInventoryentryuid() {
        return inventoryentryuid;
    }

    public void setInventoryentryuid(String inventoryentryuid) {
        this.inventoryentryuid = inventoryentryuid;
    }

    public String getProducttype() {
        return producttype;
    }

    // FIXED SETTER: lowercase + no spaces always
    public void setProducttype(String type) {
        if (type != null) {
            this.producttype = type.toLowerCase().replace(" ", "");
        } else {
            this.producttype = null;
        }
    }

    public int getActualquantity() {
        return actualquantity;
    }

    public void setActualquantity(int actualquantity) {
        this.actualquantity = actualquantity;
    }

    public String getWarehouseuid() {
        return warehouseuid;
    }

    public void setWarehouseuid(String warehouseuid) {
        this.warehouseuid = warehouseuid;
    }

    public String getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(String entrydate) {
        this.entrydate = entrydate;
    }

    public String getProductuid() {
        return productuid;
    }

    public void setProductuid(String productuid) {
        this.productuid = productuid;
    }

    public String getReferenceuid() {
        return referenceuid;
    }

    public void setReferenceuid(String referenceuid) {
        this.referenceuid = referenceuid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public BigDecimal getApprovedquantity() {
        return approvedquantity;
    }

    public void setApprovedquantity(BigDecimal approvedquantity) {
        this.approvedquantity = approvedquantity;
    }

    public String getWarehousename() {
        return warehousename;
    }

    public void setWarehousename(String warehousename) {
        this.warehousename = warehousename;
    }

    public String getEmployeeuid() {
        return employeeuid;
    }

    public void setEmployeeuid(String employeeuid) {
        this.employeeuid = employeeuid;
    }

    // ---- AUTO TIMESTAMP ---- //

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
