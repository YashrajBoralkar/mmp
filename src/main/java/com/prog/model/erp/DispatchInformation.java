package com.prog.model.erp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="dispatchinformation")
public class DispatchInformation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String carriername;
	private String transportdocumentnumber;
	private String modeoftransport;
	private String dispatchcost;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCarrierName() {
		return carriername;
	}
	public void setCarrierName(String carriername) {
		this.carriername = carriername;
	}
	public String getTransportDocumentNumber() {
		return transportdocumentnumber;
	}
	public void setTransportDocumentNumber(String transportdocumentnumber) {
		this.transportdocumentnumber = transportdocumentnumber;
	}
	public String getModeOfTransport() {
		return modeoftransport;
	}
	public void setModeOfTransport(String modeoftransport) {
		this.modeoftransport = modeoftransport;
	}
	public String getDispatchost() {
		return dispatchcost;
	}
	public void setDispatchCost(String dispatchcost) {
		this.dispatchcost = dispatchcost;
	}
	
	
}
