package com.geonational.tracker3;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the PROJECTS database table.
 * 
 */
@Entity
@Table(name="PROJECTS")
@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PROJECTS_PROJECTID_GENERATOR", sequenceName="PROJECT_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROJECTS_PROJECTID_GENERATOR")
	@Column(name="PROJECT_ID")
	private Integer projectId;

	@Column(name="PROJECT_NAME")
	private String projectName;

	@Column(name="TOTAL_DOLLARS")
	private BigDecimal totalDollars;

	@Column(name="TOTAL_HOURS")
	private BigDecimal totalHours;

	//bi-directional many-to-one association to Timesheet
	@OneToMany(mappedBy="project")
	private List<Timesheet> timesheets;

	public Project() {
	}

	 public Project( Integer projectId, String projectName, BigDecimal totalHours, BigDecimal totalDollars){
		 this.projectId = projectId;
		 this.projectName = projectName;
		 this.totalHours = totalHours;
		 this.totalDollars = totalDollars;
	 }
	
	 public Project(String projectName, BigDecimal totalHours, BigDecimal totalDollars){
		 this.projectName = projectName;
		 this.totalHours = totalHours;
		 this.totalDollars = totalDollars;
	 }
	
	
	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public BigDecimal getTotalDollars() {
		return this.totalDollars;
	}

	public void setTotalDollars(BigDecimal totalDollars) {
		this.totalDollars = totalDollars;
	}

	public BigDecimal getTotalHours() {
		return this.totalHours;
	}

	public void setTotalHours(BigDecimal totalHours) {
		this.totalHours = totalHours;
	}

	public List<Timesheet> getTimesheets() {
		return this.timesheets;
	}

	public void setTimesheets(List<Timesheet> timesheets) {
		this.timesheets = timesheets;
	}

	public Timesheet addTimesheet(Timesheet timesheet) {
		getTimesheets().add(timesheet);
		timesheet.setProject(this);

		return timesheet;
	}

	public Timesheet removeTimesheet(Timesheet timesheet) {
		getTimesheets().remove(timesheet);
		timesheet.setProject(null);

		return timesheet;
	}

}