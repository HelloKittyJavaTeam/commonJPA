package it.hellokitty.gt.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class BaseObjectDto {
	private Date createDate;
	
	private String userCreated;
	
	private Date update;
	
	private String userUpdate;
	
	private Boolean active;
	
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
