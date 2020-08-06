package com.matellio.store.service.dto;

import java.io.Serializable;
import com.matellio.store.domain.enumeration.FaqType;

/**
 * A DTO for the {@link com.matellio.store.domain.Faq} entity.
 */
public class FaqDTO implements Serializable {
    
    private String id;

    private FaqType type;

    private String question;

    private String answer;

    private Boolean isActive;

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FaqType getType() {
        return type;
    }

    public void setType(FaqType type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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
        if (!(o instanceof FaqDTO)) {
            return false;
        }

        return id != null && id.equals(((FaqDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FaqDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", question='" + getQuestion() + "'" +
            ", answer='" + getAnswer() + "'" +
            ", isActive='" + isIsActive() + "'" +
            "}";
    }
}
