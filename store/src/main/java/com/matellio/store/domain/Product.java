package com.matellio.store.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.math.BigDecimal;

import com.matellio.store.domain.enumeration.ProductType;

import com.matellio.store.domain.enumeration.ProductSize;

/**
 * A Product.
 */
@Document(collection = "product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("product_type")
    private ProductType productType;

    @Field("product_category_id")
    private String productCategoryId;

    @Field("price")
    private BigDecimal price;

    @Field("is_active")
    private Boolean isActive;

    @Field("product_size")
    private ProductSize productSize;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Product name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductType getProductType() {
        return productType;
    }

    public Product productType(ProductType productType) {
        this.productType = productType;
        return this;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getProductCategoryId() {
        return productCategoryId;
    }

    public Product productCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
        return this;
    }

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Product price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public Product isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public ProductSize getProductSize() {
        return productSize;
    }

    public Product productSize(ProductSize productSize) {
        this.productSize = productSize;
        return this;
    }

    public void setProductSize(ProductSize productSize) {
        this.productSize = productSize;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        return id != null && id.equals(((Product) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", productType='" + getProductType() + "'" +
            ", productCategoryId='" + getProductCategoryId() + "'" +
            ", price=" + getPrice() +
            ", isActive='" + isIsActive() + "'" +
            ", productSize='" + getProductSize() + "'" +
            "}";
    }
}
