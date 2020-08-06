package com.matellio.store.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import com.matellio.store.domain.enumeration.NotificationType;

/**
 * A DTO for the {@link com.matellio.store.domain.Notification} entity.
 */
public class NotificationDTO implements Serializable {
    
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String message;

    @NotNull
    private NotificationType notificationType;

    private String notificationImage;

    
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public String getNotificationImage() {
        return notificationImage;
    }

    public void setNotificationImage(String notificationImage) {
        this.notificationImage = notificationImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NotificationDTO)) {
            return false;
        }

        return id != null && id.equals(((NotificationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NotificationDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", message='" + getMessage() + "'" +
            ", notificationType='" + getNotificationType() + "'" +
            ", notificationImage='" + getNotificationImage() + "'" +
            "}";
    }
}
