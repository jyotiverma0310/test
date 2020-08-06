package com.matellio.store.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.matellio.store.domain.UserLocation} entity.
 */
public class UserLocationDTO implements Serializable {
    
    private String id;

    private String userId;

    private String latitude;

    private String longitude;

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserLocationDTO)) {
            return false;
        }

        return id != null && id.equals(((UserLocationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserLocationDTO{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", latitude='" + getLatitude() + "'" +
            ", longitude='" + getLongitude() + "'" +
            "}";
    }
}
