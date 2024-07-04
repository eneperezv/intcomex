package com.eenp.intcomexapi.entity;

import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Suppliers")
public class Supplier {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="Supplier_ID", unique=true, nullable=false)
	private Integer SupplierID;
	
	@Column(name="Company_Name")
	private String companyName;
	
	@Column(name="Contact_Name")
	private String contactName;
	
	@Column(name="Contact_Title")
	private String contactTitle;
	
	@Column(name="Address")
	private String address;
	
	@Column(name="City")
	private String city;
	
	@Column(name="Region")
	private String region;
	
	@Column(name="Postal_Code")
	private String postalCode;
	
	@Column(name="Country")
	private String country;
	
	@Column(name="Phone")
	private String phone;
	
	@Column(name="Fax")
	private String fax;
	
	@Column(name="Home_Page")
	private String homePage;

	public Integer getSupplierID() {
		return SupplierID;
	}

	public void setSupplierID(Integer supplierID) {
		SupplierID = supplierID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactTitle() {
		return contactTitle;
	}

	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	@Override
	public String toString() {
		return "Supplier [SupplierID=" + SupplierID + ", companyName=" + companyName + ", contactName=" + contactName
				+ ", contactTitle=" + contactTitle + ", address=" + address + ", city=" + city + ", region=" + region
				+ ", postalCode=" + postalCode + ", country=" + country + ", phone=" + phone + ", fax=" + fax
				+ ", homePage=" + homePage + "]";
	}

}