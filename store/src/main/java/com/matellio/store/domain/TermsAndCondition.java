package com.matellio.store.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * A TermsAndCondition.
 */
@Document(collection = "terms_and_condition")
public class TermsAndCondition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("note")
    private String note;

    @Field("is_active")
    private Boolean isActive;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public TermsAndCondition note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public TermsAndCondition isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TermsAndCondition)) {
            return false;
        }
        return id != null && id.equals(((TermsAndCondition) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TermsAndCondition{" +
            "id=" + getId() +
            ", note='" + getNote() + "'" +
            ", isActive='" + isIsActive() + "'" +
            "}";
    }
}
