package it.hellokitty.gt.entity;

import java.io.Serializable;

import it.ferrari.gt.entity.BaseObject;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ATTACHMENT_HISTORY")
@NamedQueries({
    @NamedQuery(name=AttachmentHistory.ALL,
                query="SELECT a FROM AttachmentHistory a"),
    @NamedQuery(name=AttachmentHistory.IDENTITY,
                query="SELECT a FROM AttachmentHistory a WHERE a.id = :id")
    })
public class AttachmentHistory extends BaseObject implements Serializable{
	private static final long serialVersionUID = 4225191888884917603L;
	public static final String IDENTITY = "AttachmentHistory.IDENTITY";
	public static final String ALL = "AttachmentHistory.ALL";
	
	@Id 
	@GeneratedValue(generator = "SEQ_ATTACHMENTHISTORY_ID", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="SEQ_ATTACHMENTHISTORY_ID", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@Column(name="ID")
	private Long id;
	
	@Column(name="ATTACHMENT_ID",length=20)
	private Long attachmentId;
	
	@Column(name="BULLETIN_ID",length=20)
	private Long bulletinId;
	
	@Column(name="N_DOWNLOAD")
	private Long nDownload;

	public AttachmentHistory(){}
	
	public AttachmentHistory(String user){
		super(user);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBulletinId() {
		return bulletinId;
	}

	public void setBulletinId(Long bulletinId) {
		this.bulletinId = bulletinId;
	}

	public Long getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(Long attachmentId) {
		this.attachmentId = attachmentId;
	}

	public Long getnDownload() {
		return nDownload;
	}

	public void setnDownload(Long nDownload) {
		this.nDownload = nDownload;
	}
}
