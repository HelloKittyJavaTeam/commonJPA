package it.hellokitty.gt.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class BaseObject {
	@Column(name = "CREATE_DATE", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	@Column(name = "USER_CREATE", nullable=false)
	private String userCreate;
	
	@Column(name = "UPDATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	
	@Column(name = "USER_UPDATE")
	private String userUpdate;
	
	@Column(name = "ACTIVE")
	private Boolean active;
	
	public BaseObject(){
		setCreateDate(new Date());
	}
	
	public BaseObject(String user){
		setUserCreate(user);
		setCreateDate(new Date());
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(String userCreate) {
		this.userCreate = userCreate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUserUpdate() {
		return userUpdate;
	}

	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}