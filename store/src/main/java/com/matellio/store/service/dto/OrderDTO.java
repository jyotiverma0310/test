package com.matellio.store.service.dto;

import java.time.Instant;
import java.io.Serializable;
import com.matellio.store.domain.enumeration.OrderStatus;
import com.matellio.store.domain.enumeration.OrderType;

/**
 * A DTO for the {@link com.matellio.store.domain.Order} entity.
 */
public class OrderDTO implements Serializable {
    
    private String id;

    private String orderNo;

    private String userId;

    private String deliveryEmployeeId;

    private OrderStatus status;

    private OrderType orderType;

    private Boolean isActive;

    private String shippingAddress;

    private Instant paidTime;

    private Instant completeTime;

    private Instant shippmentTime;

    private Instant deliveryTime;

    private Instant cancelTime;

    private Instant dispatchTime;

    private Instant rejectTime;

    private Instant acceptTime;

    private String note;

    private String rejectNote;

    private String cancelNote;

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeliveryEmployeeId() {
        return deliveryEmployeeId;
    }

    public void setDeliveryEmployeeId(String deliveryEmployeeId) {
        this.deliveryEmployeeId = deliveryEmployeeId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Instant getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(Instant paidTime) {
        this.paidTime = paidTime;
    }

    public Instant getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Instant completeTime) {
        this.completeTime = completeTime;
    }

    public Instant getShippmentTime() {
        return shippmentTime;
    }

    public void setShippmentTime(Instant shippmentTime) {
        this.shippmentTime = shippmentTime;
    }

    public Instant getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Instant deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Instant getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Instant cancelTime) {
        this.cancelTime = cancelTime;
    }

    public Instant getDispatchTime() {
        return dispatchTime;
    }

    public void setDispatchTime(Instant dispatchTime) {
        this.dispatchTime = dispatchTime;
    }

    public Instant getRejectTime() {
        return rejectTime;
    }

    public void setRejectTime(Instant rejectTime) {
        this.rejectTime = rejectTime;
    }

    public Instant getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Instant acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRejectNote() {
        return rejectNote;
    }

    public void setRejectNote(String rejectNote) {
        this.rejectNote = rejectNote;
    }

    public String getCancelNote() {
        return cancelNote;
    }

    public void setCancelNote(String cancelNote) {
        this.cancelNote = cancelNote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderDTO)) {
            return false;
        }

        return id != null && id.equals(((OrderDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderDTO{" +
            "id=" + getId() +
            ", orderNo='" + getOrderNo() + "'" +
            ", userId='" + getUserId() + "'" +
            ", deliveryEmployeeId='" + getDeliveryEmployeeId() + "'" +
            ", status='" + getStatus() + "'" +
            ", orderType='" + getOrderType() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", shippingAddress='" + getShippingAddress() + "'" +
            ", paidTime='" + getPaidTime() + "'" +
            ", completeTime='" + getCompleteTime() + "'" +
            ", shippmentTime='" + getShippmentTime() + "'" +
            ", deliveryTime='" + getDeliveryTime() + "'" +
            ", cancelTime='" + getCancelTime() + "'" +
            ", dispatchTime='" + getDispatchTime() + "'" +
            ", rejectTime='" + getRejectTime() + "'" +
            ", acceptTime='" + getAcceptTime() + "'" +
            ", note='" + getNote() + "'" +
            ", rejectNote='" + getRejectNote() + "'" +
            ", cancelNote='" + getCancelNote() + "'" +
            "}";
    }
}
