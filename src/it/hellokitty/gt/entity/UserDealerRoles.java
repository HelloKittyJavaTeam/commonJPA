package it.hellokitty.gt.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.persistence.annotations.Index;

@Entity
@Table(name = "USER_DEALER_ROLES")
public class UserDealerRoles implements Serializable {
	private static final long serialVersionUID = 787225444128375205L;

	@Id 
	@GeneratedValue(generator = "SEQ_USERDEALERROLES_ID", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="SEQ_USERDEALERROLES_ID", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@Column(name="ID")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="AD_USERS_ID", referencedColumnName="ID")
	private AdUsers adUser;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="DEALER_ROLES_ID", referencedColumnName="ID")
	private DealerRoles dealerRole;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="TECH_AREA_DEPT_ID", referencedColumnName="ID")
	private TechAreaDept techAreaDep;
	
	@Index
	@Column(name="USERNAME", nullable=false)
	private String username;
	
	@Column(name="LANGUAGE")
	private String language;
	
	@Column(name="DATE_INS")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateIns;

	@Column(name="DATE_MOD")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateMod;
	
	@Column(name="USER_INS")
	private String userIns;

	@Column(name="USER_MOD")
	private String userMod;
	
	private String active;
	
	public void setActive(String active) {
		this.active = active;
	}

	public UserDealerRoles(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AdUsers getAdUser() {
		return adUser;
	}

	public void setAdUser(AdUsers adUser) {
		this.adUser = adUser;
	}

	public DealerRoles getDealerRole() {
		return dealerRole;
	}

	public void setDealerRole(DealerRoles dealerRole) {
		this.dealerRole = dealerRole;
	}

	public TechAreaDept getTechAreaDep() {
		return techAreaDep;
	}

	public void setTechAreaDep(TechAreaDept techAreaDep) {
		this.techAreaDep = techAreaDep;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Date getDateIns() {
		return dateIns;
	}

	public void setDateIns(Date dateIns) {
		this.dateIns = dateIns;
	}

	public Date getDateMod() {
		return dateMod;
	}

	public void setDateMod(Date dateMod) {
		this.dateMod = dateMod;
	}

	public String getUserIns() {
		return userIns;
	}

	public void setUserIns(String userIns) {
		this.userIns = userIns;
	}

	public String getUserMod() {
		return userMod;
	}

	public void setUserMod(String userMod) {
		this.userMod = userMod;
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
}
