package com.matellio.store.service.mapper;


import com.matellio.store.domain.*;
import com.matellio.store.service.dto.DistributionStoreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DistributionStore} and its DTO {@link DistributionStoreDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DistributionStoreMapper extends EntityMapper<DistributionStoreDTO, DistributionStore> {



    default DistributionStore fromId(String id) {
        if (id == null) {
            return null;
        }
        DistributionStore distributionStore = new DistributionStore();
        distributionStore.setId(id);
        return distributionStore;
    }
}
