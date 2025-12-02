package com.prog.Dao.inventory;



import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.StockValuation;




@Repository
public class StockValuationDAO {

	 @Autowired
	    private JdbcTemplate jdbcTemplate;

	  public int saveStock(StockValuation stockValuation) {
		  String sql = "INSERT INTO stock_valuation (stockvaluationuid,stockuid, totalcost, unitcost, unitquantity, valuationdate, valuationmethod) VALUES (?,?,?,?,?,?,?)";
				  return jdbcTemplate.update(sql,
						  stockValuation.getStockvaluationuid(),
						  stockValuation.getStockuid(),
						  stockValuation.getTotalcost(),
						  stockValuation.getUnitcost(),
						  stockValuation.getUnitquantity(),
						  stockValuation.getValuationdate(),
						  stockValuation.getValuationmethod());
		  
	  }
	  public List<StockValuation> getAllStock() {
	        String sql = "SELECT * FROM stock_valuation";
	        return jdbcTemplate.query(sql, new StockValuationRowMapper());
	    }
	 private static class StockValuationRowMapper implements RowMapper<StockValuation> {
	        @Override
	        public StockValuation mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	StockValuation sv = new StockValuation();
	            sv.setId(rs.getLong("id"));
				sv.setStockuid(rs.getString("stockuid"));
	            sv.setTotalcost(rs.getString("totalcost"));
	            sv.setUnitcost(rs.getString("unitcost"));
	            sv.setUnitquantity(rs.getString("unitquantity"));
	            sv.setValuationdate(rs.getString("valuationdate"));
	            sv.setValuationmethod(rs.getString("valuationmethod"));
	            		           
	            return sv;
	        }
	    }
	public StockValuation getStockById(Long id) {
		 String sql = "SELECT * FROM stock_valuation WHERE id = ?";
		 try {
		        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(StockValuation.class));
		    } catch (EmptyResultDataAccessException e) {
		         return null; // Or throw a custom exception if you prefer
		    }
	}
//	 public int updateStock(StockValuation stockvaluation) {
//		    // SQL query to update the salary
//		    String sql = "UPDATE stock_valuation SET  totalcost = ?, unitcost = ?, unitquantity = ?, valuationdate = ?, valuationmethod = ? WHERE id = ?";
//
//		    // Execute the update query with the salary data
//		    return jdbcTemplate.update(
//		        sql,
//					/*
//					 * stockvaluation.getStockCategory(), stockvaluation.getStockName(),
//					 */ stockvaluation.getTotalcost(),
//		        stockvaluation.getUnitcost(),
//		        stockvaluation.getUnitquantity(),
//		        stockvaluation.getValuationdate(),
//		        stockvaluation.getValuationmethod(),
//		        stockvaluation.getId()  // Use the ID to update the existing salary
//		    );
//		}
	public int deleteStock(Long id) {
		 String sql = "DELETE FROM stock_valuation WHERE id = ?";
	        return jdbcTemplate.update(sql, id);
	}
	
	public List <Map<String,Object>> getStockDetailsByStockuid(String stockuid) {
		  String sql="select stockname,category from stockinfo where stockuid=?";
		  return jdbcTemplate.queryForList(sql, stockuid);
				}
	  
	  public List<Map<String,Object>>showthefindall() {
	    	String sql = "SELECT sv.id,sv.stockvaluationuid, sv.stockuid,sv.valuationmethod, \r\n"
	    			+ "sv.valuationdate FROM stock_valuation sv "
	    			+ "JOIN stockinfo s ON sv.stockuid = s.stockuid \r\n"
	    			+ "";
			return jdbcTemplate.queryForList(sql);
	       
	    } 
	  
	  
	  
	   
	  public StockValuation getStockByUId(String stockuid) {
		    String sql = "SELECT * FROM stock_valuation WHERE stockuid = ?";
		    return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(StockValuation.class), stockuid);
		}

	  public int updateUnitCostByUid(StockValuation stockvaluation) {
		    String sql = "UPDATE stock_valuation SET unitcost = ?,totalcost=? WHERE stockuid = ?";
		    return jdbcTemplate.update(sql, stockvaluation.getUnitcost(),stockvaluation.getTotalcost(), stockvaluation.getStockuid());
		}


}
