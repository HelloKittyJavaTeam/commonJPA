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
	private String userCreated;
	
	@Column(name = "UPDATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date update;
	
	@Column(name = "USER_UPDATE")
	private String userUpdate;
	
	@Column(name = "ACTIVE")
	private Boolean active;
	
	public BaseObject(){
		setCreateDate(new Date());
	}
	
	public BaseObject(String user){
		setUserCreated(user);
		setCreateDate(new Date());
    	
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUserCreated() {
		return userCreated;
	}

	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}

	public Date getUpdate() {
		return update;
	}

	public void setUpdate(Date update) {
		this.update = update;
	}

	public String getUserUpdate() {
		return userUpdate;
	}

	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}

	/**
	 * This implementation will return Boolean. True if active value is != 0, false otherwise.
	 * 
	 * @return Boolean
	 */
	public Boolean isActive() {
		return active;
	}

	/**
	 * This implementation will set "1" if input parameter value is Boolean.TRUE, "0" otherwise.
	 * 
	 * @param active Boolean
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}
	
}
