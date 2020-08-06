package com.matellio.store.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

import com.matellio.store.domain.enumeration.FaqType;

/**
 * A Faq.
 */
@Document(collection = "faq")
public class Faq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("type")
    private FaqType type;

    @Field("question")
    private String question;

    @Field("answer")
    private String answer;

    @Field("is_active")
    private Boolean isActive;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FaqType getType() {
        return type;
    }

    public Faq type(FaqType type) {
        this.type = type;
        return this;
    }

    public void setType(FaqType type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public Faq question(String question) {
        this.question = question;
        return this;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public Faq answer(String answer) {
        this.answer = answer;
        return this;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public Faq isActive(Boolean isActive) {
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
        if (!(o instanceof Faq)) {
            return false;
        }
        return id != null && id.equals(((Faq) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Faq{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", question='" + getQuestion() + "'" +
            ", answer='" + getAnswer() + "'" +
            ", isActive='" + isIsActive() + "'" +
            "}";
    }
}
