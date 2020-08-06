package com.matellio.store.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.matellio.store.domain.ProductSubCategory} entity.
 */
public class ProductSubCategoryDTO implements Serializable {
    
    private String id;

    private String name;

    private Boolean isActive;

    
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

    public Boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductSubCategoryDTO)) {
            return false;
        }

        return id != null && id.equals(((ProductSubCategoryDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductSubCategoryDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", isActive='" + isIsActive() + "'" +
            "}";
    }
}
