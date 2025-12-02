package com.prog.model.erp;



	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	@Entity
	public class StockValuation {
		
		    @Id
		    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the Stock ID
		    private  Long id;
		    
		    private String stockuid; 
			private String stockvaluationuid;// Auto-generated unique ID for the stock
			
			/*
			 * private String stockName; private String stockCategory;
			 */  
		    private String valuationmethod; // Valuation method (LIFO, FIFO) - can be a dropdown
		    private String unitcost; // Cost per unit
		    private String unitquantity; // Quantity of units
		    private String totalcost; // Total cost (unitCost * unitQuantity)
		    private String valuationdate; // Date of valuation
		    
			public Long getId() {
				return id;
			}
			public void setId(Long id) {
				this.id = id;
			}
			public String getStockuid() {
				return stockuid;
			}
			public void setStockuid(String stockuid) {
				this.stockuid = stockuid;
			}
			public String getStockvaluationuid() {
				return stockvaluationuid;
			}
			public void setStockvaluationuid(String stockvaluationuid) {
				this.stockvaluationuid = stockvaluationuid;
			}
			public String getValuationmethod() {
				return valuationmethod;
			}
			public void setValuationmethod(String valuationmethod) {
				this.valuationmethod = valuationmethod;
			}
			public String getUnitcost() {
				return unitcost;
			}
			public void setUnitcost(String unitcost) {
				this.unitcost = unitcost;
			}
			public String getUnitquantity() {
				return unitquantity;
			}
			public void setUnitquantity(String unitquantity) {
				this.unitquantity = unitquantity;
			}
			public String getTotalcost() {
				return totalcost;
			}
			public void setTotalcost(String totalcost) {
				this.totalcost = totalcost;
			}
			public String getValuationdate() {
				return valuationdate;
			}
			public void setValuationdate(String valuationdate) {
				this.valuationdate = valuationdate;
			}
		
		   
		    
	}


