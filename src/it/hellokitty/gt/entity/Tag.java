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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Index;

@Entity
@Table(name="TAG")
@NamedQueries({
	@NamedQuery(name=Tag.ALL,
				query="SELECT t FROM Tag t"),
	@NamedQuery(name=Tag.IDENTITY,
				query="SELECT t FROM Tag t WHERE t.id = :id")
})
public class Tag extends BaseObject implements Serializable{
	private static final long serialVersionUID = 6337889489065403705L;
	public static final String ALL 		= "Tag.ALL";
	public static final String IDENTITY = "Tag.IDENTITY";
	
	@Id 
	@GeneratedValue(generator = "SEQ_TAG_ID", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="SEQ_TAG_ID", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@Column(name="ID")
	private Long id;
	
	@Index
	@Column(name = "WORD")
	private String word;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="BULLETIN_ID", referencedColumnName="id")
	private Bulletin bulletin;

	public Tag(){}
	
	public Tag(String word){
		this.word = word;
	}
	
	public Tag(String user, String word){
		super(user);
		this.word = word;
	}
	
	public Tag(String user, String word, Bulletin bulletin){
		super(user);
		this.word = word;
		this.bulletin = bulletin;
	}
	
	public Bulletin getBulletin() {
		return bulletin;
	}

	public void setBulletin(Bulletin bulletin) {
		this.bulletin = bulletin;
	}
	
	public String getWord(){
		return this.word;
	}
	
	public void setWord(String word){
		this.word = word;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}






