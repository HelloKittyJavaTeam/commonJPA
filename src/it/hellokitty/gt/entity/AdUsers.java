package it.hellokitty.gt.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the AD_USERS database table.
 * 
 */
@Entity
@Table(name="AD_USERS")
@NamedQuery(name="AdUsers.findAll", query="SELECT x FROM AdUsers x")
public class AdUsers implements Serializable {
	private static final long serialVersionUID = 2927991163881025898L;

	@Id
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="DEALER_CODE", referencedColumnName="ID")
	private Dealers dealerCode;
	
	private String active;

	private String company;

	@Column(name="COST_CENTER")
	private String costCenter;

	@Column(name="DATE_INS")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateIns;

	@Column(name="DATE_LAST_UPDATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateLastUpdate;

	@Column(name="DATE_MOD")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateMod;

	private String email;

	private String fax;

	@Column(name="FE_GROUP")
	private String feGroup;

	@Column(name="FE_ROLE")
	private String feRole;

	@Column(name="FIRST_NAME")
	private String firstName;

	@Column(name="\"LANGUAGE\"")
	private String language;

	@Column(name="LAST_NAME")
	private String lastName;

	private String password;

	private String phone;

	private String source;

	private String status;

	@Column(name="USER_INS")
	private String userIns;

	@Column(name="USER_MOD")
	private String userMod;

	private String username;

	public AdUsers() {
	}

	public boolean isActive() {
		if(active.equals("Y")){
			return true;
		} else {
			return false;
		}
	}

	public void setActive(boolean active) {
		if(active){
			this.active = "Y";
		} else {
			this.active ="N";
		}
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCostCenter() {
		return this.costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public Date getDateIns() {
		return this.dateIns;
	}

	public void setDateIns(Date dateIns) {
		this.dateIns = dateIns;
	}

	public Date getDateLastUpdate() {
		return this.dateLastUpdate;
	}

	public void setDateLastUpdate(Date dateLastUpdate) {
		this.dateLastUpdate = dateLastUpdate;
	}

	public Date getDateMod() {
		return this.dateMod;
	}

	public void setDateMod(Date dateMod) {
		this.dateMod = dateMod;
	}

	public Dealers getDealerCode() {
		return this.dealerCode;
	}

	public void setDealerCode(Dealers dealerCode) {
		this.dealerCode = dealerCode;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFeGroup() {
		return this.feGroup;
	}

	public void setFeGroup(String feGroup) {
		this.feGroup = feGroup;
	}

	public String getFeRole() {
		return this.feRole;
	}

	public void setFeRole(String feRole) {
		this.feRole = feRole;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserIns() {
		return this.userIns;
	}

	public void setUserIns(String userIns) {
		this.userIns = userIns;
	}

	public String getUserMod() {
		return this.userMod;
	}

	public void setUserMod(String userMod) {
		this.userMod = userMod;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}