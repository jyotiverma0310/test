package com.matellio.store.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.Instant;

import com.matellio.store.domain.enumeration.OrderStatus;

import com.matellio.store.domain.enumeration.OrderType;

/**
 * A Order.
 */
@Document(collection = "order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("order_no")
    private String orderNo;

    @Field("user_id")
    private String userId;

    @Field("delivery_employee_id")
    private String deliveryEmployeeId;

    @Field("status")
    private OrderStatus status;

    @Field("order_type")
    private OrderType orderType;

    @Field("is_active")
    private Boolean isActive;

    @Field("shipping_address")
    private String shippingAddress;

    @Field("paid_time")
    private Instant paidTime;

    @Field("complete_time")
    private Instant completeTime;

    @Field("shippment_time")
    private Instant shippmentTime;

    @Field("delivery_time")
    private Instant deliveryTime;

    @Field("cancel_time")
    private Instant cancelTime;

    @Field("dispatch_time")
    private Instant dispatchTime;

    @Field("reject_time")
    private Instant rejectTime;

    @Field("accept_time")
    private Instant acceptTime;

    @Field("note")
    private String note;

    @Field("reject_note")
    private String rejectNote;

    @Field("cancel_note")
    private String cancelNote;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public Order orderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUserId() {
        return userId;
    }

    public Order userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeliveryEmployeeId() {
        return deliveryEmployeeId;
    }

    public Order deliveryEmployeeId(String deliveryEmployeeId) {
        this.deliveryEmployeeId = deliveryEmployeeId;
        return this;
    }

    public void setDeliveryEmployeeId(String deliveryEmployeeId) {
        this.deliveryEmployeeId = deliveryEmployeeId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Order status(OrderStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public Order orderType(OrderType orderType) {
        this.orderType = orderType;
        return this;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public Order isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public Order shippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
        return this;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Instant getPaidTime() {
        return paidTime;
    }

    public Order paidTime(Instant paidTime) {
        this.paidTime = paidTime;
        return this;
    }

    public void setPaidTime(Instant paidTime) {
        this.paidTime = paidTime;
    }

    public Instant getCompleteTime() {
        return completeTime;
    }

    public Order completeTime(Instant completeTime) {
        this.completeTime = completeTime;
        return this;
    }

    public void setCompleteTime(Instant completeTime) {
        this.completeTime = completeTime;
    }

    public Instant getShippmentTime() {
        return shippmentTime;
    }

    public Order shippmentTime(Instant shippmentTime) {
        this.shippmentTime = shippmentTime;
        return this;
    }

    public void setShippmentTime(Instant shippmentTime) {
        this.shippmentTime = shippmentTime;
    }

    public Instant getDeliveryTime() {
        return deliveryTime;
    }

    public Order deliveryTime(Instant deliveryTime) {
        this.deliveryTime = deliveryTime;
        return this;
    }

    public void setDeliveryTime(Instant deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Instant getCancelTime() {
        return cancelTime;
    }

    public Order cancelTime(Instant cancelTime) {
        this.cancelTime = cancelTime;
        return this;
    }

    public void setCancelTime(Instant cancelTime) {
        this.cancelTime = cancelTime;
    }

    public Instant getDispatchTime() {
        return dispatchTime;
    }

    public Order dispatchTime(Instant dispatchTime) {
        this.dispatchTime = dispatchTime;
        return this;
    }

    public void setDispatchTime(Instant dispatchTime) {
        this.dispatchTime = dispatchTime;
    }

    public Instant getRejectTime() {
        return rejectTime;
    }

    public Order rejectTime(Instant rejectTime) {
        this.rejectTime = rejectTime;
        return this;
    }

    public void setRejectTime(Instant rejectTime) {
        this.rejectTime = rejectTime;
    }

    public Instant getAcceptTime() {
        return acceptTime;
    }

    public Order acceptTime(Instant acceptTime) {
        this.acceptTime = acceptTime;
        return this;
    }

    public void setAcceptTime(Instant acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getNote() {
        return note;
    }

    public Order note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRejectNote() {
        return rejectNote;
    }

    public Order rejectNote(String rejectNote) {
        this.rejectNote = rejectNote;
        return this;
    }

    public void setRejectNote(String rejectNote) {
        this.rejectNote = rejectNote;
    }

    public String getCancelNote() {
        return cancelNote;
    }

    public Order cancelNote(String cancelNote) {
        this.cancelNote = cancelNote;
        return this;
    }

    public void setCancelNote(String cancelNote) {
        this.cancelNote = cancelNote;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }
        return id != null && id.equals(((Order) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Order{" +
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
