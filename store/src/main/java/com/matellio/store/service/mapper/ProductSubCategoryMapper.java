package com.matellio.store.service.mapper;


import com.matellio.store.domain.*;
import com.matellio.store.service.dto.ProductSubCategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProductSubCategory} and its DTO {@link ProductSubCategoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProductSubCategoryMapper extends EntityMapper<ProductSubCategoryDTO, ProductSubCategory> {



    default ProductSubCategory fromId(String id) {
        if (id == null) {
            return null;
        }
        ProductSubCategory productSubCategory = new ProductSubCategory();
        productSubCategory.setId(id);
        return productSubCategory;
    }
}
