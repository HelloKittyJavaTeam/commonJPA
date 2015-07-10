package it.hellokitty.gt.entity;

import java.io.Serializable;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Index;

@Entity
@Table(name="TECH_AREA_DEPT")
public class TechAreaDept extends BaseObject implements Serializable{
	private static final long serialVersionUID = 4347432721688931991L;

	@Id 
	@GeneratedValue(generator = "SEQ_TECHAREADEPT_ID", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="SEQ_TECHAREADEPT_ID", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@Column(name="ID")
	private Long id;
	
	@Index
	@Column(name = "NAME")
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="TECH_AREA_DEPT_ID", referencedColumnName="ID")
	private List<UserDealerRoles> userDealerRoles;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="DEPARTMENT_ID", referencedColumnName="id")
	private Department department;
	
	public TechAreaDept(){}
	
	public TechAreaDept(String name){
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

	public List<UserDealerRoles> getUserDealerRoles() {
		return userDealerRoles;
	}

	public void setUserDealerRoles(List<UserDealerRoles> userDealerRoles) {
		this.userDealerRoles = userDealerRoles;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}
