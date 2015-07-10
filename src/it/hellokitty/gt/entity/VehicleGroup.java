package it.hellokitty.gt.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the VEHICLE_GROUPS database table.
 * 
 */
@Entity
@Table(name="VEHICLE_GROUP")
@NamedQuery(name="VehicleGroupsIn.findAll", query="SELECT x FROM VehicleGroup x")
public class VehicleGroup implements Serializable {
	private static final long serialVersionUID = 3599752950805908921L;

	@Id
	@Column(name="ID")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="FAMILY_CODE", referencedColumnName="ID")
	private VehicleFamily vehicleFamily;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="VEHICLE_GROUP_ID", referencedColumnName="ID")
	private List<VehicleMaster> vehicleMasters;

	private String active;

	@Column(name="DATE_INS")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateIns;

	@Column(name="DATE_MOD")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateMod;

	@Column(name="DCS_CATALOG_RELEASE")
	private BigDecimal dcsCatalogRelease;

	@Column(name="DCS_CATALOG_VERSION")
	private BigDecimal dcsCatalogVersion;

	@Column(name="DCS_MODEL")
	private String dcsModel;

	@Column(name="DCS_VEHICLECLASS")
	private String dcsVehicleclass;

	private String description;

	private String model;

	@Column(name="PROJECT_ID")
	private BigDecimal projectId;

	private String suspended;

	@Column(name="USER_INS")
	private String userIns;

	@Column(name="USER_MOD")
	private String userMod;

	public VehicleGroup() {
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

	public BigDecimal getDcsCatalogRelease() {
		return this.dcsCatalogRelease;
	}

	public void setDcsCatalogRelease(BigDecimal dcsCatalogRelease) {
		this.dcsCatalogRelease = dcsCatalogRelease;
	}

	public BigDecimal getDcsCatalogVersion() {
		return this.dcsCatalogVersion;
	}

	public void setDcsCatalogVersion(BigDecimal dcsCatalogVersion) {
		this.dcsCatalogVersion = dcsCatalogVersion;
	}

	public String getDcsModel() {
		return this.dcsModel;
	}

	public void setDcsModel(String dcsModel) {
		this.dcsModel = dcsModel;
	}

	public String getDcsVehicleclass() {
		return this.dcsVehicleclass;
	}

	public void setDcsVehicleclass(String dcsVehicleclass) {
		this.dcsVehicleclass = dcsVehicleclass;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public VehicleFamily getFamilyCode() {
		return this.vehicleFamily;
	}

	public void setVehicleFamily(VehicleFamily vehicleFamily) {
		this.vehicleFamily = vehicleFamily;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public BigDecimal getProjectId() {
		return this.projectId;
	}

	public void setProjectId(BigDecimal projectId) {
		this.projectId = projectId;
	}

	public String getSuspended() {
		return this.suspended;
	}

	public void setSuspended(String suspended) {
		this.suspended = suspended;
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