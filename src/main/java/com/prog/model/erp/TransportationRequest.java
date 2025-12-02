package com.prog.model.erp;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import jakarta.persistence.*;

@Entity
@Table(name = "transportationrequest")
public class TransportationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    private String transportationrequestuid;

    private String requestdate;
	private String requestedby;
    private String departmentname;
    
    private String sourcelocation;
    private String destinationlocation;
    private String shipmenttype;
    private String weightvolume;
    private String modeoftransport;
    private String expecteddeliverydate;

    private String transportprovidername;
    private String vehiclenumber;
    private String drivername;
    private String drivercontact;
    private String approvalstatus;

    private String sourcewarehouseuid;
    private String destinationwarehouseuid;
    
    private String insertdate;
    private String updatedate;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
   
    // Getters and Setters

   

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


    public String getDestinationwarehouseuid() {
		return destinationwarehouseuid;
	}

	public void setDestinationwarehouseuid(String destinationwarehouseuid) {
		this.destinationwarehouseuid = destinationwarehouseuid;
	}

	public String getSourcewarehouseuid() {
		return sourcewarehouseuid;
	}

	public void setSourcewarehouseuid(String sourcewarehouseuid) {
		this.sourcewarehouseuid = sourcewarehouseuid;
	}

	public String getTransportationrequestuid() {
		return transportationrequestuid;
	}

	public void setTransportationrequestuid(String transportationrequestuid) {
		this.transportationrequestuid = transportationrequestuid;
	}

	public String getRequestdate() {
        return requestdate;
    }

    public void setRequestdate(String requestdate) {
        this.requestdate = requestdate;
    }

    public String getRequestedby() {
        return requestedby;
    }

    public void setRequestedby(String requestedby) {
        this.requestedby = requestedby;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    
    public String getSourcelocation() {
		return sourcelocation;
	}

	public void setSourcelocation(String sourcelocation) {
		this.sourcelocation = sourcelocation;
	}

	public String getDestinationlocation() {
		return destinationlocation;
	}

	public void setDestinationlocation(String destinationlocation) {
		this.destinationlocation = destinationlocation;
	}

	public String getShipmenttype() {
        return shipmenttype;
    }

    public void setShipmenttype(String shipmenttype) {
        this.shipmenttype = shipmenttype;
    }

    public String getWeightvolume() {
        return weightvolume;
    }

    public void setWeightvolume(String weightvolume) {
        this.weightvolume = weightvolume;
    }

    public String getModeoftransport() {
        return modeoftransport;
    }

    public void setModeoftransport(String modeoftransport) {
        this.modeoftransport = modeoftransport;
    }

    public String getExpecteddeliverydate() {
        return expecteddeliverydate;
    }

    public void setExpecteddeliverydate(String expecteddeliverydate) {
        this.expecteddeliverydate = expecteddeliverydate;
    }

    public String getTransportprovidername() {
        return transportprovidername;
    }

    public void setTransportprovidername(String transportprovidername) {
        this.transportprovidername = transportprovidername;
    }

    public String getVehiclenumber() {
        return vehiclenumber;
    }

    public void setVehiclenumber(String vehiclenumber) {
        this.vehiclenumber = vehiclenumber;
    }

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername;
    }

    public String getDrivercontact() {
        return drivercontact;
    }

    public void setDrivercontact(String drivercontact) {
        this.drivercontact = drivercontact;
    }

    public String getApprovalstatus() {
        return approvalstatus;
    }

    public void setApprovalstatus(String approvalstatus) {
        this.approvalstatus = approvalstatus;
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
