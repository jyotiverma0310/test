package com.matellio.store.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import com.matellio.store.domain.enumeration.TransactionType;
import com.matellio.store.domain.enumeration.TransactionUser;

/**
 * A DTO for the {@link com.matellio.store.domain.Transactions} entity.
 */
public class TransactionsDTO implements Serializable {
    
    private String id;

    private String orderId;

    private BigDecimal amount;

    private TransactionType transactionType;

    private TransactionUser transactionUser;

    private String userId;

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public TransactionUser getTransactionUser() {
        return transactionUser;
    }

    public void setTransactionUser(TransactionUser transactionUser) {
        this.transactionUser = transactionUser;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransactionsDTO)) {
            return false;
        }

        return id != null && id.equals(((TransactionsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TransactionsDTO{" +
            "id=" + getId() +
            ", orderId='" + getOrderId() + "'" +
            ", amount=" + getAmount() +
            ", transactionType='" + getTransactionType() + "'" +
            ", transactionUser='" + getTransactionUser() + "'" +
            ", userId='" + getUserId() + "'" +
            "}";
    }
}
