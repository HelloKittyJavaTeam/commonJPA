package it.hellokitty.gt.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the VEHICLE_FAMILY database table.
 * 
 */
@Entity
@Table(name="VEHICLE_FAMILY")
@NamedQuery(name="VehicleFamily.findAll", query="SELECT x FROM VehicleFamily x")
public class VehicleFamily implements Serializable {
	private static final long serialVersionUID = 5347717609967685396L;

	@Id
	@Column(name="ID")
	private String id;
	
	private String active;

	@Column(name="DATE_INS")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateIns;

	@Column(name="DATE_MOD")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateMod;

	private String description;

	@Column(name="USER_INS")
	private String userIns;

	@Column(name="USER_MOD")
	private String userMod;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="FAMILY_CODE", referencedColumnName="ID")
	private List<VehicleGroup> vehicleGroup;

	public VehicleFamily() {
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
			this.active = "N";
		}
	}

	public Date getDateIns() {
		return this.dateIns;
	}

	public void setDateIns(Date dateIns) {
		this.dateIns = dateIns;
	}

	public Date getDateMod() {
		return this.dateMod;
	}

	public void setDateMod(Date dateMod) {
		this.dateMod = dateMod;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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
}