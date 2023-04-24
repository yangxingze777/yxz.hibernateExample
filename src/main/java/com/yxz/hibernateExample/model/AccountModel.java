package com.yxz.hibernateExample.model;


import java.math.BigInteger;
import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.yxz.hibernateExample.utils.Encryptor;




@Entity
@Table(name = "account")
public class AccountModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uid")
	private Integer uid;
	
	@Column(name = "first_name")
	@NotBlank(message = "first name is blank")
	@Size(min = 1, max = 20, message = "first name is wrong size")
	private String firstName;
	
	@Column(name = "last_name")
	@NotBlank(message = "last name is blank")
	@Size(min = 1, max = 20, message = "last name is wrong size")
	private String lastName;
	
	@Column(name = "email",unique = true)
	@Email(message = "email error")
	private String email;
	
	@Column(name = "password")
	@NotBlank(message = "password is blank")
	private String password;
	
	@Column(name = "mobile_number")
	private BigInteger mobileNumber;
	
	public AccountModel() {
		this.uid = 404;
		this.firstName = "error";
		this.lastName = "error";
		this.email = "error";
		this.password = "error";
		this.mobileNumber = new BigInteger("999999999");
	}

	public AccountModel(Integer uid, String firstName, String lastName, String email, String password,
			BigInteger mobileNumber) {
		super();
		this.uid = uid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.mobileNumber = mobileNumber;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = Encryptor.encrypt(password);
	}

	public BigInteger getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(BigInteger mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	@Override
	public String toString() {
		return String.format("uid %d \n first name %s \n last name %s \n email %s \n password %s \n mobile number %d",
				this.uid, this.firstName, this.lastName, this.email, this.password, this.mobileNumber);
	}
}
