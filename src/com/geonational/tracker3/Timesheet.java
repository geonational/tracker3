package com.geonational.tracker3;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TIMESHEETS database table.
 * 
 */
@Entity
@Table(name="TIMESHEETS")
@NamedQueries({
    @NamedQuery(name="Timesheet.findAll",
    query="SELECT t FROM Timesheet t"),
    @NamedQuery(name="Timesheet.findAllDate",
                query="SELECT t FROM Timesheet t ORDER BY t.weekStart DESC")
}) 

public class Timesheet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TIMESHEETS_TIMESHEETID_GENERATOR", sequenceName="TIMESHEET_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIMESHEETS_TIMESHEETID_GENERATOR")
	@Column(name="TIMESHEET_ID")
	private Integer timesheetId;

	private BigDecimal friday;

	private BigDecimal monday;

	private BigDecimal saturday;

	private BigDecimal sunday;

	private BigDecimal thursday;

	private BigDecimal tuesday;

	private BigDecimal wednesday;

	@Temporal(TemporalType.DATE)
	@Column(name="WEEK_START")
	private Date weekStart;

	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="PROJECT_ID")
	private Project project;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;

	public Timesheet() {
	}

	public Timesheet(Integer timesheetId,Date weekStart, 
			BigDecimal monday, BigDecimal tuesday, BigDecimal wednesday,
			BigDecimal thursday,BigDecimal friday,BigDecimal saturday,
			BigDecimal sunday,User user,Project project) {

				this.timesheetId = timesheetId;
				this.weekStart = weekStart;
				this.monday = monday;					
				this.tuesday = tuesday;
				this.wednesday = wednesday;
				this.thursday = thursday;
				this.friday = friday;
				this.saturday = saturday;
				this.sunday = sunday;
				this.user = user;
				this.project = project;
		}
	
	public Timesheet(Date weekStart, 
			BigDecimal monday, BigDecimal tuesday, BigDecimal wednesday,
			BigDecimal thursday,BigDecimal friday,BigDecimal saturday,
			BigDecimal sunday,User user,Project project) {

				
				this.weekStart = weekStart;
				this.monday = monday;					
				this.tuesday = tuesday;
				this.wednesday = wednesday;
				this.thursday = thursday;
				this.friday = friday;
				this.saturday = saturday;
				this.sunday = sunday;
				this.user = user;
				this.project = project;
		}
	
	
	
	public Integer getTimesheetId() {
		return this.timesheetId;
	}

	public void setTimesheetId(Integer timesheetId) {
		this.timesheetId = timesheetId;
	}

	public BigDecimal getFriday() {
		return this.friday;
	}

	public void setFriday(BigDecimal friday) {
		this.friday = friday;
	}

	public BigDecimal getMonday() {
		return this.monday;
	}

	public void setMonday(BigDecimal monday) {
		this.monday = monday;
	}

	public BigDecimal getSaturday() {
		return this.saturday;
	}

	public void setSaturday(BigDecimal saturday) {
		this.saturday = saturday;
	}

	public BigDecimal getSunday() {
		return this.sunday;
	}

	public void setSunday(BigDecimal sunday) {
		this.sunday = sunday;
	}

	public BigDecimal getThursday() {
		return this.thursday;
	}

	public void setThursday(BigDecimal thursday) {
		this.thursday = thursday;
	}

	public BigDecimal getTuesday() {
		return this.tuesday;
	}

	public void setTuesday(BigDecimal tuesday) {
		this.tuesday = tuesday;
	}

	public BigDecimal getWednesday() {
		return this.wednesday;
	}

	public void setWednesday(BigDecimal wednesday) {
		this.wednesday = wednesday;
	}

	public Date getWeekStart() {
		return this.weekStart;
	}

	public void setWeekStart(Date weekStart) {
		this.weekStart = weekStart;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}