package com.eenp.intcomexapi.entity;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="Product_Id", unique=true, nullable=false)
	private Integer productId;
	
	@Column(name="Product_Name")
	private String productName;
	
	@Column(name="Supplier_ID", insertable=false, updatable=false)
	private Integer supplierId;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Supplier.class)
	@JoinColumn(name="Supplier_ID")
	private Supplier supplier;
	
	@Column(name="Category_ID", insertable=false, updatable=false)
	private Integer categoryId;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Category.class)
	@JoinColumn(name="Category_ID")
	private Category category;

	@Column(name="Quantity_Per_Unit")
	private Integer quantityPerUnit;
	
	@Column(name="Unit_Price")
	private Double unitPrice;
	
	@Column(name="Units_In_Stock")
	private Integer unitsInStock;
	
	@Column(name="Units_On_Order")
	private Integer unitsOnOrders;
	
	@Column(name="Reorder_Level")
	private Integer reorderLevel;
	
	@Column(name="Discontinued")
	private boolean discontinued;
	
	public Product() {
		
	}

	public Product(String productName, Integer supplierId, Integer categoryId,
			Integer quantityPerUnit, Double unitPrice, Integer unitsInStock, Integer unitsOnOrders,
			Integer reorderLevel, boolean discontinued) {
		super();
		this.productName = productName;
		this.supplierId = supplierId;
		this.categoryId = categoryId;
		this.quantityPerUnit = quantityPerUnit;
		this.unitPrice = unitPrice;
		this.unitsInStock = unitsInStock;
		this.unitsOnOrders = unitsOnOrders;
		this.reorderLevel = reorderLevel;
		this.discontinued = discontinued;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getQuantityPerUnit() {
		return quantityPerUnit;
	}

	public void setQuantityPerUnit(Integer quantityPerUnit) {
		this.quantityPerUnit = quantityPerUnit;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(Integer unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public Integer getUnitsOnOrders() {
		return unitsOnOrders;
	}

	public void setUnitsOnOrders(Integer unitsOnOrders) {
		this.unitsOnOrders = unitsOnOrders;
	}

	public Integer getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(Integer reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	public boolean isDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(boolean discontinued) {
		this.discontinued = discontinued;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", supplierId=" + supplierId
				+ ", categoryId=" + categoryId + ", category=" + category + ", quantityPerUnit=" + quantityPerUnit
				+ ", unitPrice=" + unitPrice + ", unitsInStock=" + unitsInStock + ", unitsOnOrders=" + unitsOnOrders
				+ ", reorderLevel=" + reorderLevel + ", discontinued=" + discontinued + "]";
	}

}
