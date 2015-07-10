package it.hellokitty.gt.entity;

import it.ferrari.gt.entity.BaseObject;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the ROLES_ACTION database table.
 * 
 */
@Entity
@Table(name="ROLES_ACTION")
public class RolesAction extends BaseObject implements Serializable{

	private static final long serialVersionUID = -8468891907382420650L;

	@Id
	private Long id;
	
	@Column(name="ROLES")
	private String roles;
	
	@Column(name="ACTION")
	private String action;
	
	public RolesAction(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
