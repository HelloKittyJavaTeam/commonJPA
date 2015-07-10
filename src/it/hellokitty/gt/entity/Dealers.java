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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the DEALERS database table.
 * 
 */
@Entity
@Table(name="DEALERS")
@NamedQuery(name="Dealers.findAll", query="SELECT d FROM Dealers d")
public class Dealers implements Serializable {
	private static final long serialVersionUID = -3548589032187394603L;

	@Id
	private String id;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="BULLETIN_DEALER",
			joinColumns=@JoinColumn(name="DEALER_ID"),
			inverseJoinColumns=@JoinColumn(name="BULLETIN_ID"))
	private List<Bulletin> bulletins;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="COUNTRY_ID", referencedColumnName="id")
	private GeoCountries country;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="DEALER_CODE", referencedColumnName="ID")
	private List<AdUsers> AdUsers;

	private String active;

	private String address;

	private String city;

	private String complement;

	@Column(name="COUNTRY_CODE")
	private String countryCode;

	@Column(name="DATE_INS")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateIns;

	@Column(name="DATE_MOD")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateMod;

	@Column(name="DEALER_TYPE")
	private String dealerType;

	private String email;

	private String fax;

	@Column(name="FLAG_AFTERMARKET")
	private String flagAftermarket;

	@Column(name="FLAG_ALLUMINIUM")
	private String flagAlluminium;

	@Column(name="FLAG_ASSISTANCE")
	private String flagAssistance;

	@Column(name="FLAG_BODYSHOP")
	private String flagBodyshop;

	@Column(name="FLAG_DEALER")
	private String flagDealer;

	@Column(name="FLAG_DEALERSELECTION")
	private String flagDealerselection;

	@Column(name="FLAG_EUROPEASS")
	private String flagEuropeass;

	@Column(name="FLAG_FLAGSHIP")
	private String flagFlagship;

	@Column(name="FLAG_HVR")
	private String flagHvr;

	@Column(name="FLAG_IMPORTER")
	private String flagImporter;

	@Column(name="FLAG_PDICENTER")
	private String flagPdicenter;

	@Column(name="FLAG_POS")
	private String flagPos;

	@Column(name="FLAG_REGIONALOFFICE")
	private String flagRegionaloffice;

	@Column(name="FLAG_REGIONALOFFICE_SUBSIDIARY")
	private String flagRegionalofficeSubsidiary;

	@Column(name="FLAG_SHOWROOM")
	private String flagShowroom;

	@Column(name="FLAG_SUBDEALER")
	private String flagSubdealer;

	@Column(name="FLAG_SUBSIDIARY")
	private String flagSubsidiary;

	@Column(name="GL_LATITUDE")
	private BigDecimal glLatitude;

	@Column(name="GL_LONGITUDE")
	private BigDecimal glLongitude;

	@Column(name="HOUSE_NUMBER")
	private String houseNumber;

	private String name;

	private String phone;

	private String province;

	@Column(name="PROVINCE_CODE")
	private String provinceCode;

	@Column(name="SW_ENABLED")
	private String swEnabled;

	@Column(name="SW_HEADING")
	private BigDecimal swHeading;

	@Column(name="SW_LATITUDE")
	private BigDecimal swLatitude;

	@Column(name="SW_LONGITUDE")
	private BigDecimal swLongitude;

	@Column(name="SW_PITCH")
	private BigDecimal swPitch;

	@Column(name="SW_ZOOM")
	private BigDecimal swZoom;

	@Column(name="USER_INS")
	private String userIns;

	@Column(name="USER_MOD")
	private String userMod;

	private String village;

	private String website;

	private String zipcode;

	public Dealers() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getComplement() {
		return this.complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
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

	public String getDealerType() {
		return this.dealerType;
	}

	public void setDealerType(String dealerType) {
		this.dealerType = dealerType;
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

	public String getFlagAftermarket() {
		return this.flagAftermarket;
	}

	public void setFlagAftermarket(String flagAftermarket) {
		this.flagAftermarket = flagAftermarket;
	}

	public String getFlagAlluminium() {
		return this.flagAlluminium;
	}

	public void setFlagAlluminium(String flagAlluminium) {
		this.flagAlluminium = flagAlluminium;
	}

	public String getFlagAssistance() {
		return this.flagAssistance;
	}

	public void setFlagAssistance(String flagAssistance) {
		this.flagAssistance = flagAssistance;
	}

	public String getFlagBodyshop() {
		return this.flagBodyshop;
	}

	public void setFlagBodyshop(String flagBodyshop) {
		this.flagBodyshop = flagBodyshop;
	}

	public String getFlagDealer() {
		return this.flagDealer;
	}

	public void setFlagDealer(String flagDealer) {
		this.flagDealer = flagDealer;
	}

	public String getFlagDealerselection() {
		return this.flagDealerselection;
	}

	public void setFlagDealerselection(String flagDealerselection) {
		this.flagDealerselection = flagDealerselection;
	}

	public String getFlagEuropeass() {
		return this.flagEuropeass;
	}

	public void setFlagEuropeass(String flagEuropeass) {
		this.flagEuropeass = flagEuropeass;
	}

	public String getFlagFlagship() {
		return this.flagFlagship;
	}

	public void setFlagFlagship(String flagFlagship) {
		this.flagFlagship = flagFlagship;
	}

	public String getFlagHvr() {
		return this.flagHvr;
	}

	public void setFlagHvr(String flagHvr) {
		this.flagHvr = flagHvr;
	}

	public String getFlagImporter() {
		return this.flagImporter;
	}

	public void setFlagImporter(String flagImporter) {
		this.flagImporter = flagImporter;
	}

	public String getFlagPdicenter() {
		return this.flagPdicenter;
	}

	public void setFlagPdicenter(String flagPdicenter) {
		this.flagPdicenter = flagPdicenter;
	}

	public String getFlagPos() {
		return this.flagPos;
	}

	public void setFlagPos(String flagPos) {
		this.flagPos = flagPos;
	}

	public String getFlagRegionaloffice() {
		return this.flagRegionaloffice;
	}

	public void setFlagRegionaloffice(String flagRegionaloffice) {
		this.flagRegionaloffice = flagRegionaloffice;
	}

	public String getFlagRegionalofficeSubsidiary() {
		return this.flagRegionalofficeSubsidiary;
	}

	public void setFlagRegionalofficeSubsidiary(String flagRegionalofficeSubsidiary) {
		this.flagRegionalofficeSubsidiary = flagRegionalofficeSubsidiary;
	}

	public String getFlagShowroom() {
		return this.flagShowroom;
	}

	public void setFlagShowroom(String flagShowroom) {
		this.flagShowroom = flagShowroom;
	}

	public String getFlagSubdealer() {
		return this.flagSubdealer;
	}

	public void setFlagSubdealer(String flagSubdealer) {
		this.flagSubdealer = flagSubdealer;
	}

	public String getFlagSubsidiary() {
		return this.flagSubsidiary;
	}

	public void setFlagSubsidiary(String flagSubsidiary) {
		this.flagSubsidiary = flagSubsidiary;
	}

	public BigDecimal getGlLatitude() {
		return this.glLatitude;
	}

	public void setGlLatitude(BigDecimal glLatitude) {
		this.glLatitude = glLatitude;
	}

	public BigDecimal getGlLongitude() {
		return this.glLongitude;
	}

	public void setGlLongitude(BigDecimal glLongitude) {
		this.glLongitude = glLongitude;
	}

	public String getHouseNumber() {
		return this.houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvinceCode() {
		return this.provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getSwEnabled() {
		return this.swEnabled;
	}

	public void setSwEnabled(String swEnabled) {
		this.swEnabled = swEnabled;
	}

	public BigDecimal getSwHeading() {
		return this.swHeading;
	}

	public void setSwHeading(BigDecimal swHeading) {
		this.swHeading = swHeading;
	}

	public BigDecimal getSwLatitude() {
		return this.swLatitude;
	}

	public void setSwLatitude(BigDecimal swLatitude) {
		this.swLatitude = swLatitude;
	}

	public BigDecimal getSwLongitude() {
		return this.swLongitude;
	}

	public void setSwLongitude(BigDecimal swLongitude) {
		this.swLongitude = swLongitude;
	}

	public BigDecimal getSwPitch() {
		return this.swPitch;
	}

	public void setSwPitch(BigDecimal swPitch) {
		this.swPitch = swPitch;
	}

	public BigDecimal getSwZoom() {
		return this.swZoom;
	}

	public void setSwZoom(BigDecimal swZoom) {
		this.swZoom = swZoom;
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

	public String getVillage() {
		return this.village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

}
