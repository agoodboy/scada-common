package com.ht.scada.common.user.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name="T_User_Ext_Info")
public class UserExtInfo extends AbstractPersistable<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7250774610026890461L;
	
	private String gender;
	private String department;
	private String email;
	private String address;
	@Digits(fraction = 0, integer = 12)
	private String telphone;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id") 
	private User user;
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
