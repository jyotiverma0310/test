package com.matellio.store.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.math.BigDecimal;

import com.matellio.store.domain.enumeration.WalletOperationType;

/**
 * A Wallet.
 */
@Document(collection = "wallet")
public class Wallet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("order_id")
    private String orderId;

    @Field("current_total")
    private BigDecimal currentTotal;

    @Field("current_reedem")
    private BigDecimal currentReedem;

    @Field("amount")
    private BigDecimal amount;

    @Field("operation")
    private WalletOperationType operation;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public Wallet orderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getCurrentTotal() {
        return currentTotal;
    }

    public Wallet currentTotal(BigDecimal currentTotal) {
        this.currentTotal = currentTotal;
        return this;
    }

    public void setCurrentTotal(BigDecimal currentTotal) {
        this.currentTotal = currentTotal;
    }

    public BigDecimal getCurrentReedem() {
        return currentReedem;
    }

    public Wallet currentReedem(BigDecimal currentReedem) {
        this.currentReedem = currentReedem;
        return this;
    }

    public void setCurrentReedem(BigDecimal currentReedem) {
        this.currentReedem = currentReedem;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Wallet amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public WalletOperationType getOperation() {
        return operation;
    }

    public Wallet operation(WalletOperationType operation) {
        this.operation = operation;
        return this;
    }

    public void setOperation(WalletOperationType operation) {
        this.operation = operation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Wallet)) {
            return false;
        }
        return id != null && id.equals(((Wallet) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Wallet{" +
            "id=" + getId() +
            ", orderId='" + getOrderId() + "'" +
            ", currentTotal=" + getCurrentTotal() +
            ", currentReedem=" + getCurrentReedem() +
            ", amount=" + getAmount() +
            ", operation='" + getOperation() + "'" +
            "}";
    }
}
