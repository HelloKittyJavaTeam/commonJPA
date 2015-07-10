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

import org.eclipse.persistence.annotations.Index;

@Entity
@Table(name = "EMAIL_CONTACT")
@NamedQueries({
    @NamedQuery(name=EmailContact.ALL,
                query="SELECT a FROM EmailContact a"),
    @NamedQuery(name=EmailContact.IDENTITY,
                query="SELECT a FROM EmailContact a WHERE a.id = :id")
    })
public class EmailContact extends BaseObject implements Serializable{
	private static final long serialVersionUID = 385238460327065486L;
	
	public static final String IDENTITY = "EmailContact.IDENTITY";
	public static final String ALL = "EmailContact.ALL";
	
	@Id 
	@GeneratedValue(generator = "SEQ_EMAILCONTACT_ID", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="SEQ_EMAILCONTACT_ID", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@Column(name="ID")
	private Long id;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="BULLETIN_CONTACT",
			joinColumns=@JoinColumn(name="EMAILCONTACT_ID"),
			inverseJoinColumns=@JoinColumn(name="BULLETIN_ID"))
	private List<Bulletin> bulletins;
    
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="MAILING_EMAIL",
			joinColumns=@JoinColumn(name="EMAILCONTACT_ID"),
			inverseJoinColumns=@JoinColumn(name="MAILINGLIST_ID"))
	private List<MailingList> mailingLists;

	@Index
	@Column(name="EMAIL")
    private String email;

	@Index
	@Column(name="NAME")
	private String name;

	@Index
	@Column(name="SURNAME")
    private String surname;

	@Index
	@Column(name="ROLE")
    private String role;

	@Index
	@Column(name="COUNTRY")
    private String country;

    public EmailContact(){}
    
    public EmailContact(String user){
    	super(user);
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
