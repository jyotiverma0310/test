package com.matellio.store.service.mapper;


import com.matellio.store.domain.*;
import com.matellio.store.service.dto.TermsAndConditionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TermsAndCondition} and its DTO {@link TermsAndConditionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TermsAndConditionMapper extends EntityMapper<TermsAndConditionDTO, TermsAndCondition> {



    default TermsAndCondition fromId(String id) {
        if (id == null) {
            return null;
        }
        TermsAndCondition termsAndCondition = new TermsAndCondition();
        termsAndCondition.setId(id);
        return termsAndCondition;
    }
}
