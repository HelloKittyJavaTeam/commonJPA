package it.hellokitty.gt.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "GEO_REGIONS")
public class GeoRegions implements Serializable {
	private static final long serialVersionUID = -1396605811093405057L;

	@Id 
	@Basic(optional = false)
	@Column(name="ID")
	private Long id;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="BULLETIN_REGION",
			joinColumns=@JoinColumn(name="ID_REGION"),
			inverseJoinColumns=@JoinColumn(name="BULLETIN_ID"))
	private List<Bulletin> bulletin;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_REGION", referencedColumnName="ID")
	private List<GeoCountries> countries;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_AREA", referencedColumnName="ID")
	private GeoAreas geoArea;

	@Column(name="DESCRIPTION_IT")
	private String descriptionIT;
	
	@Column(name="DESCRIPTION_EN")
	private String descriptionEn;
	
	@Column(name="DESCRIPTION_FR")
	private String descriptionFr;
	
	@Column(name="DESCRIPTION_DE")
	private String descriptionDe;
	
	@Column(name="DESCRIPTION_ES")
	private String descriptionEs;
	
	@Column(name="DESCRIPTION_ZH")
	private String descriptionZh;
	
	@Column(name="DESCRIPTION_JP")
	private String descriptionJp;
	
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
	
	@Column(name="ACTIVE")
	private String active;
	
	public GeoRegions(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Bulletin> getBulletin() {
		return bulletin;
	}

	public void setBulletin(List<Bulletin> bulletin) {
		this.bulletin = bulletin;
	}

	public List<GeoCountries> getCountries() {
		return countries;
	}

	public void setCountries(List<GeoCountries> countries) {
		this.countries = countries;
	}

	public GeoAreas getGeoArea() {
		return geoArea;
	}

	public void setGeoArea(GeoAreas geoArea) {
		this.geoArea = geoArea;
	}

	public String getDescriptionIT() {
		return descriptionIT;
	}

	public void setDescriptionIT(String descriptionIT) {
		this.descriptionIT = descriptionIT;
	}

	public String getDescriptionEn() {
		return descriptionEn;
	}

	public void setDescriptionEn(String descriptionEn) {
		this.descriptionEn = descriptionEn;
	}

	public String getDescriptionFr() {
		return descriptionFr;
	}

	public void setDescriptionFr(String descriptionFr) {
		this.descriptionFr = descriptionFr;
	}

	public String getDescriptionDe() {
		return descriptionDe;
	}

	public void setDescriptionDe(String descriptionDe) {
		this.descriptionDe = descriptionDe;
	}

	public String getDescriptionEs() {
		return descriptionEs;
	}

	public void setDescriptionEs(String descriptionEs) {
		this.descriptionEs = descriptionEs;
	}

	public String getDescriptionZh() {
		return descriptionZh;
	}

	public void setDescriptionZh(String descriptionZh) {
		this.descriptionZh = descriptionZh;
	}

	public String getDescriptionJp() {
		return descriptionJp;
	}

	public void setDescriptionJp(String descriptionJp) {
		this.descriptionJp = descriptionJp;
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

	public Boolean isActive() {
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
}
