package com.prog.model.erp;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "realtimeupdate")
public class RealTimeUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String realtimeupdateuid;
    private String producttype;
    private String productuid;
    private String transactiontype;
    private String warehouseuid;
    private String innertransactiontype;

    private double quantity; 
    private double realtimequantity;
    private double globalrealtimequantity;

    private String transactiondate; 

    private String insertdate;
    private String updatedate;

    
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // ======= Getters and Setters =======
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRealtimeupdateuid() {
        return realtimeupdateuid;
    }

    public void setRealtimeupdateuid(String realtimeupdateuid) {
        this.realtimeupdateuid = realtimeupdateuid;
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    public String getProductuid() {
        return productuid;
    }

    public void setProductuid(String productuid) {
        this.productuid = productuid;
    }

    public String getTransactiontype() {
        return transactiontype;
    }

    public void setTransactiontype(String transactiontype) {
        this.transactiontype = transactiontype;
    }

    public String getWarehouseuid() {
        return warehouseuid;
    }

    public void setWarehouseuid(String warehouseuid) {
        this.warehouseuid = warehouseuid;
    }

    public String getInnertransactiontype() {
        return innertransactiontype;
    }

    public void setInnertransactiontype(String innertransactiontype) {
        this.innertransactiontype = innertransactiontype;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getRealtimequantity() {
        return realtimequantity;
    }

    public void setRealtimequantity(double realtimequantity) {
        this.realtimequantity = realtimequantity;
    }

    public double getGlobalrealtimequantity() {
        return globalrealtimequantity;
    }

    public void setGlobalrealtimequantity(double globalrealtimequantity) {
        this.globalrealtimequantity = globalrealtimequantity;
    }

    public String getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(String transactiondate) {
        this.transactiondate = transactiondate;
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
