package com.matellio.store.service.mapper;


import com.matellio.store.domain.*;
import com.matellio.store.service.dto.WalletDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Wallet} and its DTO {@link WalletDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WalletMapper extends EntityMapper<WalletDTO, Wallet> {



    default Wallet fromId(String id) {
        if (id == null) {
            return null;
        }
        Wallet wallet = new Wallet();
        wallet.setId(id);
        return wallet;
    }
}
