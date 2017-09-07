package com.geonational.tracker3;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the CONTRACTS database table.
 * 
 */
@Entity
@Table(name="CONTRACTS")

@NamedQuery(name="Contract.findAll", query="SELECT c FROM Contract c")
public class Contract implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CONTRACTS_CONTRACTID_GENERATOR", sequenceName="CONTRACT_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONTRACTS_CONTRACTID_GENERATOR")
	@Column(name="CONTRACT_ID")
	private Integer contractId;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	@Column(name="LABOUR_CATEGORY")
	private String labourCategory;

	@Column(name="PO_NUMBER")
	private String poNumber;

	private BigDecimal rate;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private Date startDate;

	@Column(name="TOTAL_DOLLARS")
	private BigDecimal totalDollars;

	@Column(name="TOTAL_HOURS")
	private BigDecimal totalHours;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;
	
		public Contract () {
					}

		public Contract(Integer contractId,Date endDate,Date startDate, 
				String labourCategory, String poNumber, BigDecimal rate,
				BigDecimal totalHours,BigDecimal totalDollars, User user) {

					this.contractId = contractId;
					this.endDate = endDate;
					this.startDate = startDate;					
					this.labourCategory = labourCategory;
					this.poNumber = poNumber;
					this.rate = rate;
					this.totalDollars = totalDollars;
					this.totalHours = totalHours;
					this.user = user;
			}

		
		public Contract(Date endDate,Date startDate, 
				String labourCategory, String poNumber, BigDecimal rate,
				BigDecimal totalHours,BigDecimal totalDollars, User user) {

			this.endDate = endDate;
			this.startDate = startDate;					
			this.labourCategory = labourCategory;
			this.poNumber = poNumber;
			this.rate = rate;
			this.totalDollars = totalDollars;
			this.totalHours = totalHours;
			this.user = user;
			}

		
		
		
	public Integer getContractId() {
		return this.contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getLabourCategory() {
		return this.labourCategory;
	}

	public void setLabourCategory(String labourCategory) {
		this.labourCategory = labourCategory;
	}

	public String getPoNumber() {
		return this.poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public BigDecimal getRate() {
		return this.rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	
}