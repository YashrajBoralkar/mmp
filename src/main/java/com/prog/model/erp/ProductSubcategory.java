package com.prog.model.erp;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "productsubcategory")
public class ProductSubcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subcategoryuid;
    private String categoryuid;
    private String categoryname;
    private String subcategoryname;
    private String subcategorydescription;
    private String isactive;
    private LocalDateTime insertdate;
    private LocalDateTime updatedate;

    @PrePersist
    protected void onCreate() {
        this.insertdate = LocalDateTime.now();
        this.updatedate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubcategoryuid() {
        return subcategoryuid;
    }

    public void setSubcategoryuid(String subcategoryuid) {
        this.subcategoryuid = subcategoryuid;
    }

    public String getCategoryuid() {
        return categoryuid;
    }

    public void setCategoryuid(String categoryuid) {
        this.categoryuid = categoryuid;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

   

    public String getSubcategoryname() {
		return subcategoryname;
	}

	public void setSubcategoryname(String subcategoryname) {
		this.subcategoryname = subcategoryname;
	}

	public String getSubcategorydescription() {
        return subcategorydescription;
    }

    public void setSubcategorydescription(String subcategorydescription) {
        this.subcategorydescription = subcategorydescription;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public LocalDateTime getInsertdate() {
        return insertdate;
    }

    public void setInsertdate(LocalDateTime insertdate) {
        this.insertdate = insertdate;
    }

    public LocalDateTime getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(LocalDateTime updatedate) {
        this.updatedate = updatedate;
    }
}