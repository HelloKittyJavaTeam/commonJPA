package it.hellokitty.gt.entity;

import it.ferrari.gt.entity.BaseObject;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Index;

@Entity
@Table(name = "BULLETIN")
@NamedQueries({
    @NamedQuery(name=Bulletin.ALL,
                query="SELECT a FROM Bulletin a"),
    @NamedQuery(name=Bulletin.IDENTITY,
                query="SELECT a FROM Bulletin a WHERE a.id = :id"),
    @NamedQuery(name=Bulletin.FIND_BY_TAG,
    			query="SELECT b FROM Bulletin b WHERE b.id IN (SELECT t.bulletin.id FROM Tag t WHERE t.word = :word)"),
    @NamedQuery(name=Bulletin.FIND_BY_TAG_ADMIN,
    			query="SELECT b FROM Bulletin b WHERE b.id IN (SELECT t.bulletin.id FROM Tag t WHERE t.word = :word) AND b.active = 1"),
    @NamedQuery(name=Bulletin.FIND_BY_TAG_LIKE,
    			query="SELECT b FROM Bulletin b WHERE b.id IN (SELECT t.bulletin.id FROM Tag t WHERE t.word LIKE :word)"),
    @NamedQuery(name=Bulletin.FIND_BY_TAG_LIKE_ADMIN,
    			query="SELECT b FROM Bulletin b WHERE b.id IN (SELECT t.bulletin.id FROM Tag t WHERE t.word LIKE :word) AND b.active = 1")
    })
public class Bulletin extends BaseObject implements Serializable{
	private static final long serialVersionUID = -1647039563610840864L;
	public static final String ALL = "Bulletin.ALL";
	public static final String IDENTITY = "Bulletin.IDENTITY";
	public static final String FIND_BY_TAG ="Bulletin.FIND_BY_TAG";
	public static final String FIND_BY_TAG_ADMIN ="Bulletin.FIND_BY_TAG_ADMIN";
	public static final String FIND_BY_TAG_LIKE ="Bulletin.FIND_BY_TAG_LIKE";
	public static final String FIND_BY_TAG_LIKE_ADMIN ="Bulletin.FIND_BY_TAG_LIKE_ADMIN";
	
	@Id 
	@GeneratedValue(generator = "SEQ_BULLETIN_ID", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="SEQ_BULLETIN_ID", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@Column(name="ID")
	private Long id;
	
	@Column(name="PRIORITY",length=20)
	private String priority;
	
	@Column(name="STATUS",length=20)
	private String status;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="TECHAREA_ID")
	private TechAreaDept techAreaDept;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="BULLETIN_ID", referencedColumnName="ID")
	private List<Tag> tags;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="BULLETIN_ID", referencedColumnName="ID")
	private List<BulletinUser> bulletinUsers;
	
//	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn(name="BULLETIN_ID", referencedColumnname="ID")
//	private List<>
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="BULLETIN_ID", referencedColumnName="ID")
	private List<Attachment> bulletinAttachments;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="BULLETIN_DEALER",
			joinColumns=@JoinColumn(name="BULLETIN_ID"),
			inverseJoinColumns=@JoinColumn(name="ID_DEALER"))
	private List<Dealers> dealers;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="BULLETIN_CONTACT",
			joinColumns=@JoinColumn(name="BULLETIN_ID"),
			inverseJoinColumns=@JoinColumn(name="EMAILCONTACT_ID"))
	private List<EmailContact> emailContacts;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="BULLETIN_MAILINGLIST",
			joinColumns=@JoinColumn(name="BULLETIN_ID"),
			inverseJoinColumns=@JoinColumn(name="MAILINGLIST_ID"))
	private List<MailingList> mailingLists;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="BULLETIN_GEOAREA",
			joinColumns=@JoinColumn(name="BULLETIN_ID"),
			inverseJoinColumns=@JoinColumn(name="ID_AREA"))
	private List<GeoAreas> geoAreas;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="BULLETIN_REGION",
			joinColumns=@JoinColumn(name="BULLETIN_ID"),
			inverseJoinColumns=@JoinColumn(name="ID_REGION"))
	private List<GeoRegions> regions;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="BULLETIN_COUNTRY",
			joinColumns=@JoinColumn(name="BULLETIN_ID"),
			inverseJoinColumns=@JoinColumn(name="ID_COUNTRY"))
	private List<GeoCountries> countries;

	@Index
	@Column(name="IT_TITLE")
	private String itTitle;

	@Index
	@Column(name="IT_DESCRIPTION")
	private String itDescription;

	@Index
	@Column(name="IT_CONTENT")
	private String itContent;

	@Index
	@Column(name="EN_TITLE")
	private String enTitle;

	@Index
	@Column(name="EN_DESCRIPTION")
	private String enDescription;

	@Index
	@Column(name="EN_CONTENT")
	private String enContent;

	@Index
	@Column(name="FR_TITLE")
	private String frTitle;

	@Index
	@Column(name="FR_DESCRIPTION")
	private String frDescription;

	@Index
	@Column(name="FR_CONTENT")
	private String frContent;

	@Index
	@Column(name="ES_TITLE")
	private String esTitle;

	@Index
	@Column(name="ES_DESCRIPTION")
	private String esDescription;

	@Index
	@Column(name="ES_CONTENT")
	private String esContent;

	@Index
	@Column(name="JP_TITLE")
	private String jpTitle;

	@Index
	@Column(name="JP_DESCRIPTION")
	private String jpDescription;

	@Index
	@Column(name="JP_CONTENT")
	private String jpContent;

	@Index
	@Column(name="ZH_TITLE")
	private String zhTitle;

	@Index
	@Column(name="ZH_DESCRIPTION")
	private String zhDescription;

	@Index
	@Column(name="ZH_CONTENT")
	private String zhContent;

	@Index
	@Column(name="DE_TITLE")
	private String deTitle;

	@Index
	@Column(name="DE_DESCRIPTION")
	private String deDescription;

	@Index
	@Column(name="DE_CONTENT")
	private String deContent;
	
	public Bulletin(){}
	
	public Bulletin(String user){
		super(user);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public TechAreaDept getTechAreaDept() {
		return techAreaDept;
	}

	public void setTechAreaDept(TechAreaDept techAreaDept) {
		this.techAreaDept = techAreaDept;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<Attachment> getBulletinAttachments() {
		return bulletinAttachments;
	}

	public void setBulletinAttachments(List<Attachment> bulletinAttachments) {
		this.bulletinAttachments = bulletinAttachments;
	}

	public List<Dealers> getDealers() {
		return dealers;
	}

	public void setDealers(List<Dealers> dealers) {
		this.dealers = dealers;
	}

	public List<EmailContact> getEmailContacts() {
		return emailContacts;
	}

	public void setEmailContacts(List<EmailContact> emailContacts) {
		this.emailContacts = emailContacts;
	}

	public List<MailingList> getMailingLists() {
		return mailingLists;
	}

	public void setMailingLists(List<MailingList> mailingLists) {
		this.mailingLists = mailingLists;
	}

	public List<GeoAreas> getGeoAreas() {
		return geoAreas;
	}

	public void setGeoAreas(List<GeoAreas> geoAreas) {
		this.geoAreas = geoAreas;
	}

	public List<GeoRegions> getRegions() {
		return regions;
	}

	public void setRegions(List<GeoRegions> regions) {
		this.regions = regions;
	}

	public List<GeoCountries> getCountries() {
		return countries;
	}

	public void setCountries(List<GeoCountries> countries) {
		this.countries = countries;
	}

	public String getItTitle() {
		return itTitle;
	}

	public void setItTitle(String itTitle) {
		this.itTitle = itTitle;
	}

	public String getItDescription() {
		return itDescription;
	}

	public void setItDescription(String itDescription) {
		this.itDescription = itDescription;
	}

	public String getItContent() {
		return itContent;
	}

	public void setItContent(String itContent) {
		this.itContent = itContent;
	}

	public String getEnTitle() {
		return enTitle;
	}

	public void setEnTitle(String enTitle) {
		this.enTitle = enTitle;
	}

	public String getEnDescription() {
		return enDescription;
	}

	public void setEnDescription(String enDescription) {
		this.enDescription = enDescription;
	}

	public String getEnContent() {
		return enContent;
	}

	public void setEnContent(String enContent) {
		this.enContent = enContent;
	}

	public String getFrTitle() {
		return frTitle;
	}

	public void setFrTitle(String frTitle) {
		this.frTitle = frTitle;
	}

	public String getFrDescription() {
		return frDescription;
	}

	public void setFrDescription(String frDescription) {
		this.frDescription = frDescription;
	}

	public String getFrContent() {
		return frContent;
	}

	public void setFrContent(String frContent) {
		this.frContent = frContent;
	}

	public String getEsTitle() {
		return esTitle;
	}

	public void setEsTitle(String esTitle) {
		this.esTitle = esTitle;
	}

	public String getEsDescription() {
		return esDescription;
	}

	public void setEsDescription(String esDescription) {
		this.esDescription = esDescription;
	}

	public String getEsContent() {
		return esContent;
	}

	public void setEsContent(String esContent) {
		this.esContent = esContent;
	}

	public String getJpTitle() {
		return jpTitle;
	}

	public void setJpTitle(String jpTitle) {
		this.jpTitle = jpTitle;
	}

	public String getJpDescription() {
		return jpDescription;
	}

	public void setJpDescription(String jpDescription) {
		this.jpDescription = jpDescription;
	}

	public String getJpContent() {
		return jpContent;
	}

	public void setJpContent(String jpContent) {
		this.jpContent = jpContent;
	}

	public String getZhTitle() {
		return zhTitle;
	}

	public void setZhTitle(String zhTitle) {
		this.zhTitle = zhTitle;
	}

	public String getZhDescription() {
		return zhDescription;
	}

	public void setZhDescription(String zhDescription) {
		this.zhDescription = zhDescription;
	}

	public String getZhContent() {
		return zhContent;
	}

	public void setZhContent(String zhContent) {
		this.zhContent = zhContent;
	}

	public String getDeTitle() {
		return deTitle;
	}

	public void setDeTitle(String deTitle) {
		this.deTitle = deTitle;
	}

	public String getDeDescription() {
		return deDescription;
	}

	public void setDeDescription(String deDescription) {
		this.deDescription = deDescription;
	}

	public String getDeContent() {
		return deContent;
	}

	public void setDeContent(String deContent) {
		this.deContent = deContent;
	}

	public List<BulletinUser> getBulletinUsers() {
		return bulletinUsers;
	}

	public void setBulletinUsers(List<BulletinUser> bulletinUsers) {
		this.bulletinUsers = bulletinUsers;
	}
}
