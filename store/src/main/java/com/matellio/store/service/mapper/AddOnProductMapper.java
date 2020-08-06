package com.matellio.store.service.mapper;


import com.matellio.store.domain.*;
import com.matellio.store.service.dto.AddOnProductDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AddOnProduct} and its DTO {@link AddOnProductDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AddOnProductMapper extends EntityMapper<AddOnProductDTO, AddOnProduct> {



    default AddOnProduct fromId(String id) {
        if (id == null) {
            return null;
        }
        AddOnProduct addOnProduct = new AddOnProduct();
        addOnProduct.setId(id);
        return addOnProduct;
    }
}
