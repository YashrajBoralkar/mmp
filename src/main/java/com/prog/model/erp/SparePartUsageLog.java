package com.prog.model.erp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="sparepartusagelog")
public class SparePartUsageLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

private Long id;
	
	private String  sparepartusageuid;
	private String sparepartsinventoryuid;	
	private String dateofusage;	
	private String usedforequipment;	
	private String quantityused;	
	private String usedby;	
	private String reasonforuse;	
	private String insertdate;	
	private String updatedate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSparepartusageuid() {
		return sparepartusageuid;
	}
	public void setSparepartusageuid(String sparepartusageuid) {
		this.sparepartusageuid = sparepartusageuid;
	}
	public String getSparepartsinventoryuid() {
		return sparepartsinventoryuid;
	}
	public void setSparepartsinventoryuid(String sparepartsinventoryuid) {
		this.sparepartsinventoryuid = sparepartsinventoryuid;
	}
	public String getDateofusage() {
		return dateofusage;
	}
	public void setDateofusage(String dateofusage) {
		this.dateofusage = dateofusage;
	}
	public String getUsedforequipment() {
		return usedforequipment;
	}
	public void setUsedforequipment(String usedforequipment) {
		this.usedforequipment = usedforequipment;
	}
	public String getQuantityused() {
		return quantityused;
	}
	public void setQuantityused(String quantityused) {
		this.quantityused = quantityused;
	}
	public String getUsedby() {
		return usedby;
	}
	public void setUsedby(String usedby) {
		this.usedby = usedby;
	}
	public String getReasonforuse() {
		return reasonforuse;
	}
	public void setReasonforuse(String reasonforuse) {
		this.reasonforuse = reasonforuse;
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
