package it.hellokitty.gt.entity;

import java.io.Serializable;

import it.ferrari.gt.entity.BaseObject;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "ATTACHMENT")
@NamedQueries({
    @NamedQuery(name=Attachment.ALL,
                query="SELECT a FROM Attachment a"),
    @NamedQuery(name=Attachment.IDENTITY,
                query="SELECT a FROM Attachment a WHERE a.id = :id")
    })
public class Attachment extends BaseObject implements Serializable{
	private static final long serialVersionUID = -5560878479204719670L;

	public static final String IDENTITY = "Attachment.IDENTITY";

	public static final String ALL = "Attachment.ALL";
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="ID")
	private Long id;

	@Lob
	@Column(name="ATTACHMENT",length = 2147483647)
	private String attachment;
	
	@Column(name="FILENAME",length = 2048)
	private String fileName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="BULLETIN_ID", referencedColumnName="id")
	private Bulletin bulletin;

	public Attachment(){}
	
	public Attachment(String user){
		super(user);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Bulletin getBulletin() {
		return bulletin;
	}

	public void setBulletin(Bulletin bulletin) {
		this.bulletin = bulletin;
	}
}
