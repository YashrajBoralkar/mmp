package com.prog.Dao.inventory;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.ReturnDetails;


@Repository
public class ReturnDetailsDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<String> getAllReturnDetailsData(){
		String sql="select returnuid from returndetails";
		return jdbcTemplate.queryForList(sql,String.class);
	}
	public ReturnDetails getReturnDetailsById(String returnuid) {
		String sql= "select reasonforreturn,dateofreturnrequest, supportingevidence from returndetails where returnuid=? ";
		return jdbcTemplate.queryForObject(sql, new Object[] {returnuid}, (rs, rowNum) -> {
			ReturnDetails rd=new ReturnDetails();
			rd.setReasonforreturn(rs.getString("reasonforreturn"));
			rd.setDateofreturnrequest(rs.getString("dateofreturnrequest"));
			rd.setSupportingevidence(rs.getString("supportingevidence"));
			return rd;
		});
	}
}
