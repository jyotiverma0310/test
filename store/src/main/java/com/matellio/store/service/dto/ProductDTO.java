package com.matellio.store.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import com.matellio.store.domain.enumeration.ProductType;
import com.matellio.store.domain.enumeration.ProductSize;

/**
 * A DTO for the {@link com.matellio.store.domain.Product} entity.
 */
public class ProductDTO implements Serializable {
    
    private String id;

    private String name;

    private ProductType productType;

    private String productCategoryId;

    private BigDecimal price;

    private Boolean isActive;

    private ProductSize productSize;

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public ProductSize getProductSize() {
        return productSize;
    }

    public void setProductSize(ProductSize productSize) {
        this.productSize = productSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductDTO)) {
            return false;
        }

        return id != null && id.equals(((ProductDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductDTO{" +
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
