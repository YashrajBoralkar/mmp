package com.prog.Dao.logistics;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.hibernate.tool.schema.spi.TargetDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.GoodsReceiptNote;

@Repository
public class GoodsReceiptNoteDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public int addGoods(GoodsReceiptNote goodsreceiptnote) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTimestamp = LocalDateTime.now().format(formatter);

		String sql = "INSERT INTO goodsreceiptnote (customertype,customeruid,customername,salesorderuid,deliveredquantity,deliveryby,goodsreceiptuid,receiptdate, warehouseuid, productuid,quantityreceived,remarks,receivedby,verifiedby,approvalstatus,insertdate,updatedate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		return jdbcTemplate.update(sql, 
				goodsreceiptnote.getCustomertype(),
				goodsreceiptnote.getCustomeruid(),
				goodsreceiptnote.getCustomername(), 
				goodsreceiptnote.getSalesorderuid(),
				goodsreceiptnote.getDeliveredquantity(), 
				goodsreceiptnote.getDeliveryby(),
				goodsreceiptnote.getGoodsreceiptuid(), 
				goodsreceiptnote.getReceiptdate(),
//				goodsreceiptnote.getRawmaterialsupplieruid(), 
				goodsreceiptnote.getWarehouseuid(), 
				goodsreceiptnote.getProductuid(),
				goodsreceiptnote.getQuantityreceived(),
				goodsreceiptnote.getRemarks(), 
				goodsreceiptnote.getReceivedby(),
				goodsreceiptnote.getVerifiedby(), 
				goodsreceiptnote.getApprovalstatus(), 
				formattedTimestamp,
				formattedTimestamp);
	}

	private class GoodsReceiptNoteRowMapper implements RowMapper<GoodsReceiptNote> {
		@Override
		public GoodsReceiptNote mapRow(ResultSet rs, int rowNum) throws SQLException {
			GoodsReceiptNote god = new GoodsReceiptNote();

			god.setId(rs.getLong("id"));
			god.setCustomertype(rs.getString("customertype"));
			god.setCustomeruid(rs.getString("customeruid"));
			god.setCustomername(rs.getString("customername"));
			god.setSalesorderuid(rs.getString("salesorderuid"));
			god.setDeliveredquantity(rs.getString("deliveredquantity"));
			god.setDeliveryby(rs.getString("deliveryby"));
			god.setGoodsreceiptuid(rs.getString("goodsreceiptuid"));
			god.setReceiptdate(rs.getString("receiptdate"));
//			god.setRawmaterialsupplieruid(rs.getString("rawmaterialsupplieruid"));
			god.setWarehouseuid(rs.getString("warehouseuid"));
			god.setProductuid(rs.getString("productuid"));
			god.setQuantityreceived(rs.getInt("quantityreceived"));
			god.setRemarks(rs.getString("remarks"));
			god.setReceivedby(rs.getString("receivedby"));
			god.setVerifiedby(rs.getString("verifiedby"));
			god.setApprovalstatus(rs.getString("approvalstatus"));
			return god;
		}
	}

	public List<GoodsReceiptNote> getGoodsList() {
		String sql = "SELECT * FROM goodsreceiptnote";
		return jdbcTemplate.query(sql, new GoodsReceiptNoteRowMapper());
	}

	public int deleteGoods(Long id) {
		String sql = "DELETE FROM goodsreceiptnote WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}

	public GoodsReceiptNote getGoodsReceiptbyid(Long id) {
		String sql = "select * from goodsreceiptnote where id=?";
		return jdbcTemplate.queryForObject(sql, new GoodsReceiptNoteRowMapper(), id);
	}

	public void updateGoodsReceiptNote(GoodsReceiptNote goodsreceiptnote) {
		String sql = "UPDATE goodsreceiptnote SET deliveryby=?, receiptdate=?, quantityreceived=?, remarks=?, receivedby=?,verifiedby=?, approvalstatus=?, updatedate=?  WHERE id =?";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTimestamp = LocalDateTime.now().format(formatter);

		jdbcTemplate.update(sql,goodsreceiptnote.getDeliveryby() , goodsreceiptnote.getReceiptdate(), goodsreceiptnote.getQuantityreceived(),
				goodsreceiptnote.getRemarks(), goodsreceiptnote.getReceivedby(), goodsreceiptnote.getVerifiedby(),
				goodsreceiptnote.getApprovalstatus(), formattedTimestamp, goodsreceiptnote.getId());
	}

	public List<Map<String, Object>> findall() {
		String sql = "SELECT gn.id, p.productname, w.address, gn.productuid, gn.warehouseuid, "
				+ " gn.goodsreceiptuid, gn.receiptdate, gn.quantityreceived, "
				+ " gn.remarks, gn.receivedby, gn.verifiedby, gn.approvalstatus "
				+ "FROM goodsreceiptnote gn " 
				+ " JOIN productinfo p ON gn.productuid = p.productuid "
				+ " JOIN warehouseinfo w ON gn.warehouseuid = w.warehouseuid "
				+ " ORDER BY gn.id ASC;";

		return jdbcTemplate.queryForList(sql);
	}

	public List<String> getSalesOrderUIDS(String customeruid) {
		String sql = "Select salesorderuid from salesorder where customeruid = ?";
		return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("salesorderuid"), customeruid);
	}

	public List<String> getQuantity(String salesorderuid) {
		String sql = "Select deliveredquantity from salesorder where salesorderuid = ?";
		return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("deliveredquantity"), salesorderuid);
	}

	public List<String> getProductUIDsBySalesOrder(String salesorderuid) {
	    String sql = "SELECT productuid FROM salesorder WHERE salesorderuid = ?";
	    return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("productuid"), salesorderuid);
	}

	
	public String getProductNameByUID(String productuid) {
	    String sql = "SELECT productname FROM productinfo WHERE productuid = ?";
	    try {
	        return jdbcTemplate.queryForObject(sql, String.class, productuid);
	    } catch (EmptyResultDataAccessException e) {
	        return "";
	    }
	}
}
