import { NotificationType } from 'app/shared/model/enumerations/notification-type.model';

export interface INotification {
  id?: string;
  name?: string;
  message?: string;
  notificationType?: NotificationType;
  notificationImage?: string;
}

export class Notification implements INotification {
  constructor(
    public id?: string,
    public name?: string,
    public message?: string,
    public notificationType?: NotificationType,
    public notificationImage?: string
  ) {}
}
