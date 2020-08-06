package com.matellio.store.service.mapper;


import com.matellio.store.domain.*;
import com.matellio.store.service.dto.TransactionsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Transactions} and its DTO {@link TransactionsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TransactionsMapper extends EntityMapper<TransactionsDTO, Transactions> {



    default Transactions fromId(String id) {
        if (id == null) {
            return null;
        }
        Transactions transactions = new Transactions();
        transactions.setId(id);
        return transactions;
    }
}
