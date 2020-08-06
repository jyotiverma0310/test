package com.matellio.store.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.matellio.store.domain.Wholesaler} entity.
 */
public class WholesalerDTO implements Serializable {
    
    private String id;

    private String name;

    private String userId;

    
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WholesalerDTO)) {
            return false;
        }

        return id != null && id.equals(((WholesalerDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WholesalerDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", userId='" + getUserId() + "'" +
            "}";
    }
}
