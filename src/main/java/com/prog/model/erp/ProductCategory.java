package com.prog.model.erp;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "productcategory")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryuid;
    private String categoryname;
    private String categorydescription;

    @Lob
    private byte[] supportingimage;

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

	public String getProductcategorydescription() {
        return categorydescription;
    }

    public void setCategorydescription(String categorydescription) {
        this.categorydescription = categorydescription;
    }

    public byte[] getSupportingimage() {
        return supportingimage;
    }

    public void setSupportingimage(byte[] supportingimage) {
        this.supportingimage = supportingimage;
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
