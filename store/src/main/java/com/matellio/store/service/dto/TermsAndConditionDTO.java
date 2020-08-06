package com.matellio.store.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.matellio.store.domain.TermsAndCondition} entity.
 */
public class TermsAndConditionDTO implements Serializable {
    
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
        if (!(o instanceof TermsAndConditionDTO)) {
            return false;
        }

        return id != null && id.equals(((TermsAndConditionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TermsAndConditionDTO{" +
            "id=" + getId() +
            ", note='" + getNote() + "'" +
            ", isActive='" + isIsActive() + "'" +
            "}";
    }
}
