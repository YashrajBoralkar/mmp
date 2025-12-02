package com.prog.Dao.qualitycontrol;

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

import com.prog.model.erp.QualityControlAudit;


@Repository
public class QualityControlAuditDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public static class AuditFormRowMapper implements RowMapper<QualityControlAudit>{

		@Override
		public QualityControlAudit mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			QualityControlAudit af=new QualityControlAudit();
			af.setId(rs.getLong("id"));
			af.setQualitycontrolaudituid(rs.getString("qualitycontrolaudituid"));
			af.setAuditdate(rs.getString("auditdate"));
			af.setAuditorname(rs.getString("auditorname"));
			af.setPcscore(rs.getString("pcscore"));
			af.setPqscore(rs.getString("pqscore"));
			af.setCorrectiveaction(rs.getString("correctiveaction"));
			af.setAuditstatus(rs.getString("auditstatus"));
			af.setSupervisorreview(rs.getString("supervisorreview"));
			return af;
		}
	}
	
	public int addAuditData(QualityControlAudit af) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
		String sql="insert into qualitycontrolauditform(qualitycontrolaudituid,auditdate,auditorname,pcscore,pqscore,"
				+ "correctiveaction,auditstatus,supervisorreview,insertdate,updatedate)values(?,?,?,?,?,?,?,?,?,?) ";
		return jdbcTemplate.update(sql,
			
				af.getQualitycontrolaudituid(),
				af.getAuditdate(),
				af.getAuditorname(),
				af.getPcscore(),
				af.getPqscore(),
				af.getCorrectiveaction(),
				af.getAuditstatus(),
				af.getSupervisorreview(),
				formattedTimestamp,
                formattedTimestamp
				);
	}
	
	public QualityControlAudit getAuditFormDataById(Long id) {
		String sql="select * from qualitycontrolauditform where id=?";
		try {
	    	return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(QualityControlAudit.class));
	    } catch (EmptyResultDataAccessException e) {
	        return null; // Or throw a custom exception if you prefer
	    }
	}
	
	public List<Map<String, Object>> getAllAuditFormData(){
		String sql="select id, qualitycontrolaudituid,auditdate,auditorname,pcscore,pqscore,correctiveaction,auditstatus,supervisorreview from qualitycontrolauditform ";
		return jdbcTemplate.queryForList(sql);
	}
	
	public int updateAuditForm(QualityControlAudit af) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
		String sql="update qualitycontrolauditform set auditdate=?,auditorname=?,pcscore=?,pqscore=?,correctiveaction=?,auditstatus=?,supervisorreview=?,updatedate=? where id=?";
		return jdbcTemplate.update(sql,
			af.getAuditdate(),
			af.getAuditorname(),
			af.getPcscore(),
			af.getPqscore(),
			af.getCorrectiveaction(),
			af.getAuditstatus(),
			af.getSupervisorreview(),
			formattedTimestamp,
			af.getId()
				);
	}
	
	public void deleteAuditFormDataById(Long id) {
		String sql="Delete from qualitycontrolauditform where id=?";
		jdbcTemplate.update(sql,id);
	}
}
