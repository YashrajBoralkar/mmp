package com.prog.Dao.sales;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.SalesCommission;


@Repository
public class SalesCommissionDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final RowMapper<SalesCommission> rowMapper = (rs, rowNum) -> {

		SalesCommission sales = new SalesCommission();
		sales.setId(rs.getLong("id"));
		sales.setSalescommissionuid(rs.getString("salescommissionuid"));
		sales.setSalesrepid(rs.getString("salesrepid"));
		sales.setSalesrepname(rs.getString("salesrepname"));
		sales.setCommissionperiod(rs.getString("commissionperiod"));
		sales.setTotalsales(rs.getDouble("totalsales"));
		sales.setCommissionrate(rs.getDouble("commissionrate"));
		sales.setCommissionearned(rs.getDouble("commissionearned"));
		sales.setApprovalstatus(rs.getString("approvalstatus"));
		return sales;

	};

	public int savesales(SalesCommission sales) {
		String sql = "INSERT INTO salescommission (id, salescommissionuid, salesrepid, salesrepname, commissionperiod, totalsales, commissionrate, commissionearned, approvalstatus, insertdate, updatedate  )"
				+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedTimestamp = LocalDateTime.now().format(formatter);	        
	        return jdbcTemplate.update(sql, 
	        		sales.getId(),
				sales.getSalescommissionuid(), 
				sales.getSalesrepid(), 
				sales.getSalesrepname(),
				sales.getCommissionperiod(), 
				sales.getTotalsales(), 
				sales.getCommissionrate(),
				sales.getCommissionearned(), 
				sales.getApprovalstatus(),
				formattedTimestamp,
                formattedTimestamp
                );
	}
		public int updatesales( SalesCommission sales) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedTimestamp = LocalDateTime.now().format(formatter);
			
			String sql ="UPDATE salescommission SET salesrepname=?, commissionperiod=?, totalsales=?, commissionrate=?, commissionearned=?, approvalstatus=?, updatedate = ? WHERE id=?";
			return jdbcTemplate.update(sql,
					sales.getSalesrepname(),
				sales.getCommissionperiod(), 
				sales.getTotalsales(),
				sales.getCommissionrate(),
				sales.getCommissionearned(), 
				sales.getApprovalstatus(),
				formattedTimestamp,
				sales.getId());
		}
		
		public List<Map<String, Object>> getAllSales(){
			String sql = "SELECT id, salescommissionuid, salesrepname, commissionperiod, totalsales, commissionearned, approvalstatus\r\n"
					+ "FROM salescommission;\r\n"
					+ " ";
	        return jdbcTemplate.queryForList(sql);
	      }
		
		
		public SalesCommission getSalesCommissionById(Long id) {
			String sql = "SELECT *FROM salescommission WHERE id=?";
			List<SalesCommission> sales = jdbcTemplate.query(sql, rowMapper, id);
		return sales.isEmpty() ? null : sales.get(0); 
		}
		
		public void deleteSalesId(Long id) {
			String sql =" DELETE FROM salescommission WHERE id = ?";
			jdbcTemplate.update(sql, id);
		}
		
}
