package com.prog.Dao.hrms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.Training;

@Repository
public class TrainingDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int saveTraining(Training training) {
        String sql = "INSERT INTO training (training_uid, employeeuid, trainingtitle, trainer_name, description, department, internal_external, trainer_email, phone_number, training_mode, location, venue_address, start_date, end_date, start_time, end_time, duration) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                training.getTrainingUid(),
                training.getEmployeeUID(),
                training.getTrainingtitle(),
                training.getTrainerName(),
                training.getDescription(),
                training.getDepartment(),
                training.getInternalExternal(),
                training.getTrainerEmail(),
                training.getPhoneNumber(),
                training.getTrainingMode(),
                training.getLocation(),
                training.getVenueAddress(),
                training.getStartDate(),
                training.getEndDate(),
                training.getStartTime(),
                training.getEndTime(),
                training.getDuration()
        );
    }

    public List<String> getAllTrainingUids() {
        String sql = "SELECT training_uid FROM training";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    public Training getTrainingDetailsByUid(String trainingUid) {
        String sql = "SELECT training_uid, trainingtitle, trainer_name, duration FROM training WHERE training_uid = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{trainingUid}, (rs, rowNum) -> {
                Training details = new Training();
                details.setTrainingUid(rs.getString("training_uid"));  // 🔹 Include this
                details.setTrainingtitle(rs.getString("trainingtitle"));
                details.setTrainerName(rs.getString("trainer_name"));
                details.setDuration(rs.getString("duration"));
                return details;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    public List<Map<String, Object>> getAllTraining() {
        String sql = "SELECT id,trainer_name,trainingtitle,department,internal_external,training_mode,start_date,end_date,start_time,end_time,duration FROM training;\r\n"
        		+ ""; // Fixed table name
        return jdbcTemplate.queryForList(sql);
    }

    // RowMapper for Training
    private static class TrainingRowMapper implements RowMapper<Training> {
        @Override
        public Training mapRow(ResultSet rs, int rowNum) throws SQLException {
            Training training = new Training();
            training.setId(rs.getLong("id"));
            training.setTrainingUid(rs.getString("training_uid"));
            training.setEmployeeUID(rs.getString("employeeuid"));
            training.setTrainingtitle(rs.getString("trainingtitle"));
            training.setTrainerName(rs.getString("trainer_name"));
            training.setDescription(rs.getString("description"));
            training.setDepartment(rs.getString("department"));
            training.setInternalExternal(rs.getString("internal_external"));
            training.setTrainerEmail(rs.getString("trainer_email"));
            training.setPhoneNumber(rs.getString("phone_number"));
            training.setTrainingMode(rs.getString("training_mode"));
            training.setLocation(rs.getString("location"));
            training.setVenueAddress(rs.getString("venue_address"));
            training.setStartDate(rs.getString("start_date"));
            training.setEndDate(rs.getString("end_date"));
            training.setStartTime(rs.getString("start_time"));
            training.setEndTime(rs.getString("end_time"));
            training.setDuration(rs.getString("duration"));

            return training;
        }
    }
    
    public Training gettrainingbyid(Long id) 
	{
		String sql="select * from training where id=?";
		return jdbcTemplate.queryForObject(sql,new TrainingRowMapper(),id);
	}
    
 // Delete a job by its ID
    public int deleteTrainingById(Long id) {
        String sql = "DELETE FROM Training WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
    
    public int updateTraining(Training training) {
        String sql = "UPDATE training SET trainingtitle=?, trainer_name=?, description=?, department=?, internal_external=?, trainer_email=?, phone_number=?, training_mode=?, location=?, venue_address=?, start_date=?, end_date=?, start_time=?, end_time=?, duration=? WHERE id=?";
        return jdbcTemplate.update(sql,
                training.getTrainingtitle(),
                training.getTrainerName(),
                training.getDescription(),
                training.getDepartment(),  // 🔹 Previously missing in the query
                training.getInternalExternal(),
                training.getTrainerEmail(),
                training.getPhoneNumber(),
                training.getTrainingMode(),
                training.getLocation(),
                training.getVenueAddress(),
                training.getStartDate(),
                training.getEndDate(),
                training.getStartTime(),
                training.getEndTime(),
                training.getDuration(),
                training.getId() // 🔹 Ensure ID is included at the end
        );
    }

}
