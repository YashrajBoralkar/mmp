package com.prog.model.erp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "stocktransfer")
public class Stocktransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stocktransferuid;

    private String sourceWarehouseUid;
    private String destinationWarehouseUid;

    private String transferdate;
    private String transfertype;
    private String sourcesection;
    private String destinationsection;

    private String quantitytotransfer;
    private String reasonfortransfer;
    private String transfermode;
    private String carriername;
    private String transfercost;

    private String requestedby;
    private String approvedby;
    private String approvaldate;

    private String remarks;
    private String status;
     
    private String productuid;       
    private String productname;  
  
    private String address;  
    private String toaddress;
    private Integer actualquantity;
    private String producttype;

    private String insertdate;
    private String updatedate;
    
    
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStocktransferuid() {
        return stocktransferuid;
    }

    public void setStocktransferuid(String stocktransferuid) {
        this.stocktransferuid = stocktransferuid;
    }

    public String getSourceWarehouseUid() {
        return sourceWarehouseUid;
    }

    public void setSourceWarehouseUid(String sourceWarehouseUid) {
        this.sourceWarehouseUid = sourceWarehouseUid;
    }

    public String getDestinationWarehouseUid() {
        return destinationWarehouseUid;
    }

    public void setDestinationWarehouseUid(String destinationWarehouseUid) {
        this.destinationWarehouseUid = destinationWarehouseUid;
    }

    public String getTransferdate() {
        return transferdate;
    }

    public void setTransferdate(String transferdate) {
        this.transferdate = transferdate;
    }

    public String getTransfertype() {
        return transfertype;
    }

    public void setTransfertype(String transfertype) {
        this.transfertype = transfertype;
    }

    public String getSourcesection() {
        return sourcesection;
    }

    public void setSourcesection(String sourcesection) {
        this.sourcesection = sourcesection;
    }

    public String getDestinationsection() {
        return destinationsection;
    }

    public void setDestinationsection(String destinationsection) {
        this.destinationsection = destinationsection;
    }

    public String getQuantitytotransfer() {
        return quantitytotransfer;
    }

    public void setQuantitytotransfer(String quantitytotransfer) {
        this.quantitytotransfer = quantitytotransfer;
    }

    public String getReasonfortransfer() {
        return reasonfortransfer;
    }

    public void setReasonfortransfer(String reasonfortransfer) {
        this.reasonfortransfer = reasonfortransfer;
    }

    public String getTransfermode() {
        return transfermode;
    }

    public void setTransfermode(String transfermode) {
        this.transfermode = transfermode;
    }

    public String getCarriername() {
        return carriername;
    }

    public void setCarriername(String carriername) {
        this.carriername = carriername;
    }

    public String getTransfercost() {
        return transfercost;
    }

    public void setTransfercost(String transfercost) {
        this.transfercost = transfercost;
    }

    public String getRequestedby() {
        return requestedby;
    }

    public void setRequestedby(String requestedby) {
        this.requestedby = requestedby;
    }

    public String getApprovedby() {
        return approvedby;
    }

    public void setApprovedby(String approvedby) {
        this.approvedby = approvedby;
    }

    public String getApprovaldate() {
        return approvaldate;
    }

    public void setApprovaldate(String approvaldate) {
        this.approvaldate = approvaldate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }    

    public String getProductuid() {
		return productuid;
	}

	public void setProductuid(String productuid) {
		this.productuid = productuid;
	}

	
	public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getToaddress() {
		return toaddress;
	}

	public void setToaddress(String toaddress) {
		this.toaddress = toaddress;
	}

	public Integer getActualquantity() {
		return actualquantity;
	}

	public void setActualquantity(Integer actualquantity) {
		this.actualquantity = actualquantity;
	}

	public String getProducttype() {
		return producttype;
	}

	public void setProducttype(String producttype) {
		this.producttype = producttype;
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
