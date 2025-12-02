package com.prog.model.erp;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Appraisal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String employeeUID; // Auto-incremented ID for the employee
    private String appraisaluid;
    private String appraisalPeriod;

    private LocalDate startDate;
    private LocalDate endDate;

    @Column(length = 1000)
    private String keyAchievements;

    @Column(length = 1000)
    private String areasForImprovement;

    @Column(length = 1000)
    private String goalAchieved;

    private String rating;

    @Column(length = 1000)
    private String reviewerComments;

    @Column(length = 1000)
    private String managerComments;

  

    // Getters and Setters
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public String getEmployeeUID() {
		return employeeUID;
	}

	public void setEmployeeUID(String employeeUID) {
		this.employeeUID = employeeUID;
	}

	public String getAppraisaluid() {
		return appraisaluid;
	}

	public void setAppraisaluid(String appraisaluid) {
		this.appraisaluid = appraisaluid;
	}

	public String getAppraisalPeriod() {
        return appraisalPeriod;
    }

    public void setAppraisalPeriod(String appraisalPeriod) {
        this.appraisalPeriod = appraisalPeriod;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getKeyAchievements() {
        return keyAchievements;
    }

    public void setKeyAchievements(String keyAchievements) {
        this.keyAchievements = keyAchievements;
    }

    public String getGoalAchieved() {
        return goalAchieved;
    }

    public void setGoalAchieved(String goalAchieved) {
        this.goalAchieved = goalAchieved;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReviewerComments() {
        return reviewerComments;
    }

    public void setReviewerComments(String reviewerComments) {
        this.reviewerComments = reviewerComments;
    }

    public String getManagerComments() {
        return managerComments;
    }

    public void setManagerComments(String managerComments) {
        this.managerComments = managerComments;
    }

    public String getAreasForImprovement() {
        return areasForImprovement;
    }

    public void setAreasForImprovement(String areasForImprovement) {
        this.areasForImprovement = areasForImprovement;
    }
}
