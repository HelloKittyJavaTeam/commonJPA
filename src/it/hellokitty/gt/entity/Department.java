package it.hellokitty.gt.entity;

import it.ferrari.gt.entity.BaseObject;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Index;

@Entity
@Table(name="DEPARTMENT")
public class Department extends BaseObject implements Serializable{
	private static final long serialVersionUID = 4445488796944267489L;

	@Id 
	@Basic(optional = false)
	@Column(name="ID")
	private Long id;
	
	@Index
	@Column(name = "NAME")
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="DEPARTMENT_ID", referencedColumnName="ID")
	private List<TechAreaDept> tecAreaDep;
	
	public Department(){}
	
	public Department(String name){
		this.name = name;
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

	public List<TechAreaDept> getTecAreaDep() {
		return tecAreaDep;
	}

	public void setTecAreaDep(List<TechAreaDept> tecAreaDep) {
		this.tecAreaDep = tecAreaDep;
	}
}