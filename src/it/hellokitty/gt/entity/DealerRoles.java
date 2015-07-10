package it.hellokitty.gt.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Index;

@Entity
@Table(name = "DEALER_ROLES")
public class DealerRoles implements Serializable {
	private static final long serialVersionUID = 7238755395980215355L;

	@Id 
	@Basic(optional = false)
	@Column(name="ID")
	private Long id;

	@Index
	@Column(name="ROLE", nullable=false)
	private String role;

	public DealerRoles(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}