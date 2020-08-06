package com.matellio.store.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.math.BigDecimal;

import com.matellio.store.domain.enumeration.TransactionType;

import com.matellio.store.domain.enumeration.TransactionUser;

/**
 * A Transactions.
 */
@Document(collection = "transactions")
public class Transactions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("order_id")
    private String orderId;

    @Field("amount")
    private BigDecimal amount;

    @Field("transaction_type")
    private TransactionType transactionType;

    @Field("transaction_user")
    private TransactionUser transactionUser;

    @Field("user_id")
    private String userId;

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

    public Transactions orderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Transactions amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public Transactions transactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public TransactionUser getTransactionUser() {
        return transactionUser;
    }

    public Transactions transactionUser(TransactionUser transactionUser) {
        this.transactionUser = transactionUser;
        return this;
    }

    public void setTransactionUser(TransactionUser transactionUser) {
        this.transactionUser = transactionUser;
    }

    public String getUserId() {
        return userId;
    }

    public Transactions userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transactions)) {
            return false;
        }
        return id != null && id.equals(((Transactions) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Transactions{" +
            "id=" + getId() +
            ", orderId='" + getOrderId() + "'" +
            ", amount=" + getAmount() +
            ", transactionType='" + getTransactionType() + "'" +
            ", transactionUser='" + getTransactionUser() + "'" +
            ", userId='" + getUserId() + "'" +
            "}";
    }
}
