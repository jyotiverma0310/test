package com.matellio.store.service.mapper;


import com.matellio.store.domain.*;
import com.matellio.store.service.dto.WholesalerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Wholesaler} and its DTO {@link WholesalerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WholesalerMapper extends EntityMapper<WholesalerDTO, Wholesaler> {



    default Wholesaler fromId(String id) {
        if (id == null) {
            return null;
        }
        Wholesaler wholesaler = new Wholesaler();
        wholesaler.setId(id);
        return wholesaler;
    }
}
