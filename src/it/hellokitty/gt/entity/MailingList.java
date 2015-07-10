package it.hellokitty.gt.entity;

import it.ferrari.gt.entity.BaseObject;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MAILING_LIST")
@NamedQueries({
    @NamedQuery(name=MailingList.ALL,
                query="SELECT a FROM MailingList a"),
    @NamedQuery(name=MailingList.IDENTITY,
                query="SELECT a FROM MailingList a WHERE a.id = :id")
    })
public class MailingList extends BaseObject implements Serializable{
	private static final long serialVersionUID = 7405433036186630520L;

	public static final String IDENTITY = "MailingList.IDENTITY";

	public static final String ALL = "MailingList.ALL";
	
	@Id 
	@GeneratedValue(generator = "SEQ_MAILINGLIST_ID", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="SEQ_MAILINGLIST_ID", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@Column(name="ID")
	private Long id;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="MAILING_EMAIL",
			joinColumns=@JoinColumn(name="MAILINGLIST_ID"),
			inverseJoinColumns=@JoinColumn(name="EMAILCONTACT_ID"))
	private List<EmailContact> emailContacts;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="BULLETIN_MAILINGLIST",
			joinColumns=@JoinColumn(name="MAILINGLIST_ID"),
			inverseJoinColumns=@JoinColumn(name="BULLETIN_ID"))
	private List<Bulletin> bulletins;
	
	@Column(name="NAME")
	private String name;
	
	public MailingList(){}
	
	public MailingList(String user){
		super(user);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Bulletin> getBulletins() {
		return bulletins;
	}

	public void setBulletins(List<Bulletin> bulletins) {
		this.bulletins = bulletins;
	}

	public List<EmailContact> getEmailContacts() {
		return emailContacts;
	}

	public void setEmailContacts(List<EmailContact> emailContacts) {
		this.emailContacts = emailContacts;
	}
}
