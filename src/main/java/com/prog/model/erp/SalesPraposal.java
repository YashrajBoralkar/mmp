package com.prog.model.erp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;


@Table(name="salesproposal")
@Entity
public class SalesPraposal {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		//general info
		private Long id;
		
		private String salesproposaluid;
		private String proposaldate;
		private String clientuid;
		private String productdescription;		
		private String pricequotation;
		private String insertdate;  
	    private String updatedate;
	    
	    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}		

		public String getSalesproposaluid() {
			return salesproposaluid;
		}

		public void setSalesproposaluid(String salesproposaluid) {
			this.salesproposaluid = salesproposaluid;
		}


		public String getProposaldate() {
			return proposaldate;
		}

		public void setProposaldate(String proposaldate) {
			this.proposaldate = proposaldate;
		}

		public String getProductdescription() {
			return productdescription;
		}

		public void setProductdescription(String productdescription) {
			this.productdescription = productdescription;
		}

		public String getPricequotation() {
			return pricequotation;
		}

		public void setPricequotation(String pricequotation) {
			this.pricequotation = pricequotation;
		}

		public String getClientuid() {
			return clientuid;
		}

		public void setClientuid(String clientuid) {
			this.clientuid = clientuid;
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
