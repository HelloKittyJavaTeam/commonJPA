package it.hellokitty.gt.entity;

import java.io.Serializable;

import it.ferrari.gt.entity.BaseObject;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BULLETIN_USER")
@NamedQueries({
	@NamedQuery(name=BulletinUser.ALL,
                query="SELECT a FROM BulletinUser a"),
    @NamedQuery(name=BulletinUser.IDENTITY,
                query="SELECT a FROM BulletinUser a WHERE a.id = :id")
})
public class BulletinUser extends BaseObject implements Serializable{
	private static final long serialVersionUID = 443126599290066820L;
	
	public static final String IDENTITY = "BulletinUser.IDENTITY";
	public static final String ALL = "BulletinUser.ALL";
	
	@Id 
	@GeneratedValue(generator = "SEQ_BULLETINUSER_ID", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="SEQ_BULLETINUSER_ID", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@Column(name="ID")
	private Long id;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="BULLETIN_ID")
	private Bulletin bulletinId;
	
	@Column(name="USER_NAME")
	private String userName;

	@Column(name="DOWNLOAD")
	private Boolean download;
	
	@Column(name="N_READ")
	private Long nRead;

	public BulletinUser(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getDownload() {
		return download;
	}

	public void setDownload(Boolean download) {
		this.download = download;
	}

	public Bulletin getBulletinId() {
		return bulletinId;
	}

	public void setBulletinId(Bulletin bulletinId) {
		this.bulletinId = bulletinId;
	}

	public Long getnRead() {
		return nRead;
	}

	public void setnRead(Long nRead) {
		this.nRead = nRead;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

//	public static final String IDENTITY = "BulletinUser.IDENTITY";
//	public static final String ALL = "BulletinUser.ALL";
//
//	@Id 
//	@GeneratedValue(generator = "SEQ_BULLETINUSER_ID", strategy = GenerationType.SEQUENCE)
//	@SequenceGenerator(name="SEQ_BULLETINUSER_ID", allocationSize = 1, initialValue = 0)
//	@Basic(optional = false)
//	@Column(name="ID")
//	private Long id;
//	
//	@Column(name="BULLETIN_ID")
//	private Long bulletin;
//	
//	@Column(name="USER")
//	private String user;
//	
//	@Column(name="READ")
//	private Boolean read;
//	
//	@Column(name="DOWNLOAD")
//	private Boolean download;
//	
//	public BulletinUser(){}
//	
//	public BulletinUser(String user){
//		super(user);
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public Long getBulletin() {
//		return bulletin;
//	}
//
//	public void setBulletin(Long bulletin) {
//		this.bulletin = bulletin;
//	}
//
//	public String getUser() {
//		return user;
//	}
//
//	public void setUser(String user) {
//		this.user = user;
//	}
//
//	public Boolean getRead() {
//		return read;
//	}
//
//	public void setRead(Boolean read) {
//		this.read = read;
//	}
//
//	public Boolean getDownload() {
//		return download;
//	}
//
//	public void setDownload(Boolean download) {
//		this.download = download;
//	}
}