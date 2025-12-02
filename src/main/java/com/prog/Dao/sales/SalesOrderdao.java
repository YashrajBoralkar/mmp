package com.prog.Dao.sales;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.Enrollment;
import com.prog.model.erp.SalesOrder;

@Repository
public class SalesOrderdao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

//=========================================================================================================================================
	private static final String sql = "INSERT INTO salesorder (" +
	        "customertype, customeruid, salesorderuid, orderdate, orderstatus, productuid, " +
	        "deliverydate, deliverymethod, deliveryaddress, deliverystatus, " +
	        "orderquantity, deliveredquantity, remainingquantity, sellingprice, " +
	        "shippingaddress, billingaddress, totalamount, insertdate, updatedate) " +
	        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	public int savesalesorder(SalesOrder so) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String now = LocalDateTime.now().format(formatter);

	    return jdbcTemplate.update(sql,
	            so.getCustomertype(),
	            so.getCustomeruid(),
	            so.getSalesorderuid(),
	            so.getOrderdate(),
	            so.getOrderstatus(),
	            so.getProductuid(),
	            so.getDeliverydate(),
	            so.getDeliverymethod(),
	            so.getDeliveryaddress(),
	            so.getDeliverystatus(),
	            so.getOrderquantity(),
	            so.getDeliveredquantity(),
	            so.getRemainingquantity(),
	            so.getSellingprice(),
	            so.getShippingaddress(),
	            so.getBillingaddress(),
	            so.getTotalamount(),
	            now,
	            now
	    );
	}

//=========================================================================================================================================

	public List<SalesOrder> showsaleslist() {
		String sql = "select * from salesorder";
		return jdbcTemplate.query(sql, new SalesorderRowMapper());

	}

//=========================================================================================================================================
	public List<Map<String, Object>> showjoindata() {
		String sql = "SELECT pr.productname, \r\n" + " cd.companyname,cd.phonenumber, \r\n"
				+ "so.id,so.deliverydate, \r\n" + "so.orderdate, so.orderquantity,so.salesorderuid,so.totalamount \r\n"
				+ "FROM salesorder so \r\n" + "JOIN productinfo pr ON so.productuid = pr.productuid \r\n"
				+ "JOIN customerregistration cd ON so.customeruid = cd.customeruid;\r\n" + "" + "";
		return jdbcTemplate.queryForList(sql);
	}
//=========================================================================================================================================

	public static class SalesorderRowMapper implements RowMapper<SalesOrder> {
		@Override
		public SalesOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
			SalesOrder so = new SalesOrder();

			so.setId(rs.getLong("id"));
			so.setCustomertype(rs.getString("customertype"));
			so.setCustomeruid(rs.getString("customeruid"));
			so.setSalesorderuid(rs.getString("salesorderuid"));
			so.setOrderdate(rs.getString("orderdate"));
			so.setOrderstatus(rs.getString("orderstatus"));
			so.setProductuid(rs.getString("productuid"));
			so.setDeliverydate(rs.getString("deliverydate"));
			so.setDeliverymethod(rs.getString("deliverymethod"));
			so.setDeliveryaddress(rs.getString("deliveryaddress"));
			so.setDeliverystatus(rs.getString("deliverystatus"));
			so.setOrderquantity(rs.getString("orderquantity"));
			so.setDeliveredquantity(rs.getString("deliveredquantity"));
			so.setRemainingquantity(rs.getString("remainingquantity"));
			so.setSellingprice(rs.getString("sellingprice"));
			so.setShippingaddress(rs.getString("shippingaddress"));
			so.setBillingaddress(rs.getString("billingaddress"));
			so.setTotalamount(rs.getString("totalamount"));

			return so;
		}
	}

//=========================================================================================================================================

	public SalesOrder selectorderbyid(Long id) {
		String sql = "select * from salesorder where id=?";
		return jdbcTemplate.queryForObject(sql, new SalesorderRowMapper(), id);
	}

//=========================================================================================================================================
	public void Deletsalesorderbyid(Long id) {
		String sql = "delete from salesorder where id=?";
		jdbcTemplate.update(sql, id);

	}
//=========================================================================================================================================

	public int updatesaleorder(SalesOrder so) {
		String sql = "UPDATE salesorder SET orderdate = ?, remainingquantity = ?, deliveredquantity = ?, orderstatus = ?, deliverydate = ?, deliverymethod = ?, deliveryaddress = ?, deliverystatus = ?, totalamount=?, updatedate = ? WHERE id = ?";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTimestamp = LocalDateTime.now().format(formatter);

		return jdbcTemplate.update(sql, so.getOrderdate(),so.getRemainingquantity(), so.getDeliveredquantity(), so.getOrderstatus(), so.getDeliverydate(),
				so.getDeliverymethod(), so.getDeliveryaddress(), so.getDeliverystatus(),
				so.getTotalamount(), formattedTimestamp,

				so.getId());
	}
//=========================================================================================================================================

	// Fetching IN Delivery Order

	public List<String> getCustomerUidInDeliveryOrder() {
		String sql = "select customeruid From customerregistration";
		return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("customeruid"));
	}

	public List<String> getSalesOrderUIDInDeliveryOrder() {
		String sql = "select salesorderuid From salesorder";
		return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("salesorderuid"));
	}

	public List<Map<String, Object>> getSalesOrderByuidInDeliveryOrder(String salesorderuid) {
		String sql = "SELECT cd.customeruid,cd.companyname,so.deliverydate, so.deliveryaddress FROM salesorder so JOIN customerregistration cd ON so.customeruid = cd.customeruid WHERE salesorderuid=?";
		return jdbcTemplate.queryForList(sql, salesorderuid);
	}

	public List<String> getcustomerUIDbytype(String customertype) {
		String sql = "SELECT customeruid FROM customerregistration WHERE customertype = ?";
		return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("customeruid"),customertype);
	}

//  //Fetching IN BACKORDER
//  
//  public List<String>getallsalerorderuid(){
//		String sql = "SELECT salesorderuid from salesorder";
//		return jdbcTemplate.queryForList(sql, String.class);
//	}
//  public Salesorder getOrderDetailsByuid(String salesorderuid) {
//		String sql = "SELECT orderdate, customername FROM salesorder WHERE salesorderuid = ? ";
//		return jdbcTemplate.queryForObject(sql,new Object[] {salesorderuid},(rs,rowNum) -> {
//			Salesorder salesorder = new Salesorder();
//			salesorder.setCustomerName(rs.getString("customername"));
//			salesorder.setOrderDate(rs.getString("orderdate"));
//			return salesorder;
//
//		});
//	}

}
