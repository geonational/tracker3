package com.geonational.tracker3;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the USERS database table.
 * 
 */
@Entity
@Table(name="USERS")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USERS_USERID_GENERATOR", sequenceName="USER_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USERS_USERID_GENERATOR")
	@Column(name="USER_ID")
	private Integer userId;

	private String city;

	@Column(name="COMPANY_NAME")
	private String companyName;

	private String street;

	private String username;

	private String zip;

	//bi-directional many-to-one association to Contract
	@OneToMany(mappedBy="user")
	private List<Contract> contracts;

	//bi-directional many-to-one association to Timesheet
	@OneToMany(mappedBy="user")
	private List<Timesheet> timesheets;

	public User() {
	}

	
	public User(Integer userId,String username,
        		String street,String city,String zip,String companyName) {
		this.userId = userId;
		this.username = username;
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.companyName = companyName;
	}
	
	public User(String username,
    		String street,String city,String zip,String companyName) {
	
    this.username = username;
	this.street = street;
	this.city = city;
	this.zip = zip;
	this.companyName = companyName;
}
	
	
	
	
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public List<Contract> getContracts() {
		return this.contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}

	public Contract addContract(Contract contract) {
		getContracts().add(contract);
		contract.setUser(this);

		return contract;
	}

	public Contract removeContract(Contract contract) {
		getContracts().remove(contract);
		contract.setUser(null);

		return contract;
	}

	public List<Timesheet> getTimesheets() {
		return this.timesheets;
	}

	public void setTimesheets(List<Timesheet> timesheets) {
		this.timesheets = timesheets;
	}

	public Timesheet addTimesheet(Timesheet timesheet) {
		getTimesheets().add(timesheet);
		timesheet.setUser(this);

		return timesheet;
	}

	public Timesheet removeTimesheet(Timesheet timesheet) {
		getTimesheets().remove(timesheet);
		timesheet.setUser(null);

		return timesheet;
	}

}