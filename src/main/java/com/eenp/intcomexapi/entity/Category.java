package com.eenp.intcomexapi.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Categories")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="Category_ID", unique=true, nullable=false)
	private Integer categoryId;
	
	@Column(name="Category_Name")
	private String categoryName;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="Picture")
	private String picture;
	
	public Category() {
		
	}
	
	public Category(String name,String desc,String pic) {
		super();
		this.categoryName = name;
		this.description = desc;
		this.picture = pic;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "Categoria [categoryId=" + categoryId + ", categoryName=" + categoryName + ", description=" + description
				+ ", picture=" + picture + "]";
	}

}
