package com.matellio.store.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import com.matellio.store.domain.enumeration.WalletOperationType;

/**
 * A DTO for the {@link com.matellio.store.domain.Wallet} entity.
 */
public class WalletDTO implements Serializable {
    
    private String id;

    private String orderId;

    private BigDecimal currentTotal;

    private BigDecimal currentReedem;

    private BigDecimal amount;

    private WalletOperationType operation;

    
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

    public BigDecimal getCurrentTotal() {
        return currentTotal;
    }

    public void setCurrentTotal(BigDecimal currentTotal) {
        this.currentTotal = currentTotal;
    }

    public BigDecimal getCurrentReedem() {
        return currentReedem;
    }

    public void setCurrentReedem(BigDecimal currentReedem) {
        this.currentReedem = currentReedem;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public WalletOperationType getOperation() {
        return operation;
    }

    public void setOperation(WalletOperationType operation) {
        this.operation = operation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WalletDTO)) {
            return false;
        }

        return id != null && id.equals(((WalletDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WalletDTO{" +
            "id=" + getId() +
            ", orderId='" + getOrderId() + "'" +
            ", currentTotal=" + getCurrentTotal() +
            ", currentReedem=" + getCurrentReedem() +
            ", amount=" + getAmount() +
            ", operation='" + getOperation() + "'" +
            "}";
    }
}
