package com.prog.Dao.purchase;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.OnlinePurchaseRequest;


@Repository
public class OnlinePurchaseRequestDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public static class onlinepurchaserequestRoeMapper implements RowMapper<OnlinePurchaseRequest> {

		@Override
		public OnlinePurchaseRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			OnlinePurchaseRequest opr=new OnlinePurchaseRequest();
			opr.setId(rs.getLong("id"));
			opr.setOnlinepurchaserequestuid(rs.getString("onlinepurchaserequestuid"));
			opr.setRequestdate(rs.getString("requestdate"));
			opr.setRequestingdepartment(rs.getString("requestingdepartment"));
			opr.setRequestedby(rs.getString("requestedby"));
			opr.setWebsitelink(rs.getString("websitelink"));
			opr.setOnlinevendorname(rs.getString("onlinevendorname"));
			opr.setOnlinevendoremail(rs.getString("onlinevendoremail"));
			opr.setOnlinevendorcontactnumber(rs.getString("onlinevendorcontactnumber"));
			opr.setPaymentmethod(rs.getString("paymentmethod"));
			opr.setOrderconfirmation(rs.getString("orderconfirmation"));
			opr.setProductuid(rs.getString("productuid"));
			opr.setDeliveryaddress(rs.getString("deliveryaddress"));
			opr.setExpecteddeliverydate(rs.getString("expecteddeliverydate"));
			opr.setTrackingnumber(rs.getString("trackingnumber"));
			opr.setCourierservicename(rs.getString("courierservicename"));
			opr.setBudgetcode(rs.getString("budgetcode"));
			opr.setFinaltotalamount(rs.getString("finaltotalamount"));
			opr.setAvailablebudget(rs.getString("availablebudget"));
			opr.setFundingsource(rs.getString("fundingsource"));
			opr.setFinancedepartmentapprovername(rs.getString("financedepartmentapprovername"));
			opr.setFinancedepartmentapprovalstatus(rs.getString("financedepartmentapprovalstatus"));
			opr.setFinancedepartmentapprovaldate(rs.getString("financedepartmentapprovaldate"));
			opr.setProcurementapprovername(rs.getString("procurementapprovername"));
			opr.setProcurementdepartmentapprovaldate(rs.getString("procurementdepartmentapprovaldate"));
			opr.setProcurementdepartmentapprovalstatus(rs.getString("procurementdepartmentapprovalstatus"));
			opr.setInvoiceupload(rs.getBytes("invoiceupload"));
			opr.setComments(rs.getString("comments"));
			opr.setDocName(rs.getString("doc_name"));
			opr.setOnlinepurchasequantity(rs.getString("onlinepurchasequantity"));
			opr.setTotalprice(rs.getString("totalprice"));
			opr.setShippingcost(rs.getString("shippingcost"));
			opr.setTaxamount(rs.getString("taxamount"));
			return opr;
		}		
	}
	
	public int addOnlinePurchaseRequest(OnlinePurchaseRequest opr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
        String sql="insert into onlinepurchaserequest(id,onlinepurchaserequestuid,requestdate,requestingdepartment,requestedby,websitelink,onlinevendorname,onlinevendoremail,onlinevendorcontactnumber,paymentmethod,"
        		+ "orderconfirmation,productuid,deliveryaddress,expecteddeliverydate,trackingnumber,courierservicename,budgetcode,finaltotalamount,availablebudget,fundingsource,"
        		+ "financedepartmentapprovername,financedepartmentapprovalstatus,financedepartmentapprovaldate,procurementapprovername,procurementdepartmentapprovaldate,procurementdepartmentapprovalstatus,invoiceupload,"
        		+ "comments,doc_name,onlinepurchasequantity,totalprice,shippingcost,taxamount,insertdate,updatedate"
        		+ ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql,
				opr.getId(),
				opr.getOnlinepurchaserequestuid(),
				opr.getRequestdate(),
				opr.getRequestingdepartment(),
				opr.getRequestedby(),
				opr.getWebsitelink(),
				opr.getOnlinevendorname(),
				opr.getOnlinevendoremail(),
				opr.getOnlinevendorcontactnumber(),
				opr.getPaymentmethod(),
				opr.getOrderconfirmation(),
				opr.getProductuid(),
				opr.getDeliveryaddress(),
				opr.getExpecteddeliverydate(),
				opr.getTrackingnumber(),
				opr.getCourierservicename(),
				opr.getBudgetcode(),
				opr.getFinaltotalamount(),
				opr.getAvailablebudget(),
				opr.getFundingsource(),
				opr.getFinancedepartmentapprovername(),
				opr.getFinancedepartmentapprovalstatus(),
				opr.getFinancedepartmentapprovaldate(),
				opr.getProcurementapprovername(),
				opr.getProcurementdepartmentapprovaldate(),
				opr.getProcurementdepartmentapprovalstatus(),
				opr.getInvoiceupload(),
				opr.getComments(),
				opr.getDocName(),
				opr.getOnlinepurchasequantity(),
				opr.getTotalprice(),
				opr.getShippingcost(),
				opr.getTaxamount(),
				formattedTimestamp,
                formattedTimestamp
				);
	}
	
	public OnlinePurchaseRequest getOnlinePurchaseRequestById(Long id) {
		String sql="select * from onlinepurchaserequest where id=?";
		try {
	    	return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(OnlinePurchaseRequest.class));
	    } catch (EmptyResultDataAccessException e) {
	        return null; // Or throw a custom exception if you prefer
	    }
	}
	public List<Map<String, Object>> getAllOnlinePurchaseRequest(){
		String sql="Select opr.id,opr.onlinepurchaserequestuid,opr.requestdate,opr.requestingdepartment,opr.requestedby,opr.websitelink,opr.onlinevendorname,opr.onlinevendoremail,opr.onlinevendorcontactnumber,opr.paymentmethod,\r\n"
				+ "				opr.orderconfirmation,pd.productname,pd.sellingprice,opr.onlinepurchasequantity ,opr.deliveryaddress,opr.expecteddeliverydate,\r\n"
				+ "				opr.trackingnumber,opr.courierservicename,opr.budgetcode,opr.finaltotalamount,opr.availablebudget,opr.fundingsource,\r\n"
				+ "				opr.financedepartmentapprovername,opr.financedepartmentapprovalstatus,opr.financedepartmentapprovaldate,opr.procurementapprovername,opr.procurementdepartmentapprovaldate,opr.procurementdepartmentapprovalstatus,opr.invoiceupload,opr.comments,opr.taxamount,opr.shippingcost,opr.totalprice\r\n"
				+ "				from onlinepurchaserequest opr\r\n"
				+ "				JOIN productinfo pd ON pd.productuid=opr.productuid;";
		return jdbcTemplate.queryForList(sql);
	}
	
	public int updateOnlinePurchaseRequestData(OnlinePurchaseRequest opr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
		 String sql="update onlinepurchaserequest set requestdate=?,requestingdepartment=?,requestedby=?,websitelink=?,onlinevendorname=?,onlinevendoremail=?,onlinevendorcontactnumber=?,"
		 		+ "paymentmethod=?,orderconfirmation=?,deliveryaddress=?,expecteddeliverydate=?,trackingnumber=?,courierservicename=?,budgetcode=?,finaltotalamount = ?,"
		 		+ "availablebudget=?,fundingsource=?,financedepartmentapprovername=?,financedepartmentapprovalstatus=?,financedepartmentapprovaldate=?,procurementapprovername=?,procurementdepartmentapprovaldate=?,"
		 		+ "procurementdepartmentapprovalstatus=?,invoiceupload=?,comments=?,onlinepurchasequantity = ?,totalprice = ?,shippingcost = ?,taxamount = ?, updatedate=? where id=?";
		 		
		return jdbcTemplate.update(sql,
				opr.getRequestdate(),
				opr.getRequestingdepartment(),
				opr.getRequestedby(),
				opr.getWebsitelink(),
				opr.getOnlinevendorname(),
				opr.getOnlinevendoremail(),
				opr.getOnlinevendorcontactnumber(),
				opr.getPaymentmethod(),
				opr.getOrderconfirmation(),
				opr.getDeliveryaddress(),
				opr.getExpecteddeliverydate(),
				opr.getTrackingnumber(),
				opr.getCourierservicename(),
				opr.getBudgetcode(),
				opr.getFinaltotalamount(),
				opr.getAvailablebudget(),
				opr.getFundingsource(),
				opr.getFinancedepartmentapprovername(),
				opr.getFinancedepartmentapprovalstatus(),
				opr.getFinancedepartmentapprovaldate(),
				opr.getProcurementapprovername(),
				opr.getProcurementdepartmentapprovaldate(),
				opr.getProcurementdepartmentapprovalstatus(),
				opr.getInvoiceupload(),
				opr.getComments(),
				opr.getOnlinepurchasequantity(),
				opr.getTotalprice(),
				opr.getShippingcost(),
				opr.getTaxamount(),
				formattedTimestamp,
				
				opr.getId()
				);
	}
	
	public void deleteOPRFormDataById(Long id) {
		String sql="delete from onlinepurchaserequest where id=?";
		jdbcTemplate.update(sql,id);		
	}

	
	
//	//Data fetch from Sales info table by using customer details and prosuctuid  details in sales invoice form
//		public List<String>getProductDetailsByuid(){
//	    	String sql="select productuid From productdetails";
//	    	return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("productuid"));
//	    }
//	    
//	  public ProductDetails getProductDetails(String productuid){
//	   String sql= "SELECT productname, productdescription,quantity,unitprice,totalprice,shippingcost,\r\n"
//	   		+ "taxamount,finalcost FROM productdetails where productuid=?";
//   	return jdbcTemplate.queryForObject(sql, new Object[] {productuid}, (rs, rowNum) -> {
//   		ProductDetails pd=new ProductDetails();
//   		pd.setProductname(rs.getString("productname"));
//   		pd.setProductdescription(rs.getString("productdescription"));
//   		pd.setQuantity(rs.getString("quantity"));
//   		pd.setUnitprice(rs.getString("unitprice"));
//   		pd.setTotalprice(rs.getString("totalprice"));
//   		pd.setShippingcost(rs.getString("shippingcost"));
//   		pd.setTaxamount(rs.getString("taxamount"));
//   		pd.setFinalcost(rs.getString("finalcost"));
//   		return pd;
//   	});
//	}
//
//	
} 
