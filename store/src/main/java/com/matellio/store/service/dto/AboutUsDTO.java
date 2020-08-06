package com.matellio.store.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.matellio.store.domain.AboutUs} entity.
 */
public class AboutUsDTO implements Serializable {
    
    private String id;

    private String note;

    private Boolean isActive;

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
        if (!(o instanceof AboutUsDTO)) {
            return false;
        }

        return id != null && id.equals(((AboutUsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AboutUsDTO{" +
            "id=" + getId() +
            ", note='" + getNote() + "'" +
            ", isActive='" + isIsActive() + "'" +
            "}";
    }
}
