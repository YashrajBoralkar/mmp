package com.prog.model.erp;

import jakarta.persistence.*;

@Entity

public class StockDispatch {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String dispatchuid;

    private String dispatchtype;
    private String customeruid;
    private String batchuid;
    private String productuid;
    private String carriername;
    private String dispatchedby;
    private String approvedby;

    private String approvaldate; // Changed to LocalDate
    private String status;
    private String remarks;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDispatchuid() {
        return dispatchuid;
    }
    
    

    public String getBatchuid() {
		return batchuid;
	}

	public void setBatchuid(String batchuid) {
		this.batchuid = batchuid;
	}

	public void setDispatchuid(String dispatchuid) {
        this.dispatchuid = dispatchuid;
    }

    public String getDispatchtype() {
        return dispatchtype;
    }

    public void setDispatchtype(String dispatchtype) {
        this.dispatchtype = dispatchtype;
    }

    


    public String getCustomeruid() {
		return customeruid;
	}

	public void setCustomeruid(String customeruid) {
		this.customeruid = customeruid;
	}

	public String getProductuid() {
		return productuid;
	}

	public void setProductuid(String productuid) {
		this.productuid = productuid;
	}

	public String getCarriername() {
        return carriername;
    }

    public void setCarriername(String carriername) {
        this.carriername = carriername;
    }

    public String getDispatchedby() {
        return dispatchedby;
    }

    public void setDispatchedby(String dispatchedby) {
        this.dispatchedby = dispatchedby;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
