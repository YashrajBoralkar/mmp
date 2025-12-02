package com.prog.Dao.hrms;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.Goal;

import java.time.LocalDate;

@Repository
public class GoalSettingDAO {
	

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private static class GoalRowMapper implements RowMapper<Goal> {
        @Override
        public Goal mapRow(ResultSet rs, int rowNum) throws SQLException {
            Goal goal = new Goal();
            goal.setId(rs.getLong("id"));
            goal.setEmployeeUID(rs.getString("employeeuid"));
            goal.setGoaluid(rs.getString("goaluid"));
            goal.setTitle(rs.getString("title"));
            goal.setDescription(rs.getString("description"));
            //goal.setTargetdate(rs.get);
            goal.setPriority(rs.getString("priority"));
            goal.setStatus(rs.getString("status"));
            goal.setRegularUpdate(rs.getString("regular_update"));
            
            java.sql.Date targetDate = rs.getDate("target_date"); // Get the SQL date
            if (targetDate != null) {
                goal.setTargetDate(targetDate.toLocalDate()); // Convert to LocalDate
            }
            return goal;
        }
    }

    
  //Method to fetch the data from database
  		public List<Map<String, Object>> getAllGoals(){
  			String sql="select gs.id,gs.title, gs.target_date,gs.priority,gs.status,\r\n"
  					+ "emp.employeeuid, emp.first_name, emp.last_name, emp.departmentname from goal gs\r\n"
  					+ "join employee emp on gs.employeeuid = emp.employeeuid;\r\n"
  					+ "";
  			return jdbcTemplate.queryForList(sql);
  				};

    
    //Add new data for goal table
 public int addGoalSetting(Goal goal) {
	 String sql = "INSERT INTO Goal (employeeuid,title, goaluid, description, target_date, priority, status, regular_update) VALUES (?,?, ?, ?, ?, ?, ?,?)";
	 return jdbcTemplate.update(sql, 
	     goal.getEmployeeUID(),
	     goal.getTitle(),
	     goal.getGoaluid(),
	     goal.getDescription(), 
	     goal.getTargetDate(), 
	     goal.getPriority(), 
	     goal.getStatus(), 
	     goal.getRegularUpdate());
 }
//Delete an employee
 public int deleteGoal(Long id) {
	    String sql = "DELETE FROM Goal WHERE id = ?";
	    return jdbcTemplate.update(sql, id);
	}

 public Goal getGoalById(Long id) {
     String sql = "SELECT * FROM Goal WHERE id = ?";
     return jdbcTemplate.queryForObject(sql, new GoalRowMapper(), id);
 }
  
 public void updateGoal(Goal goal) {
	    String sql = "UPDATE goal SET title = ?, description = ?, target_date = ?, priority = ?, status = ?, regular_update = ? WHERE id = ?";
	    jdbcTemplate.update(sql,
	        goal.getTitle(),
	        goal.getDescription(),
	        goal.getTargetDate(),
	        goal.getPriority(),
	        goal.getStatus(),
	        goal.getRegularUpdate(),
	        goal.getId()); 
	    
	}


}

      