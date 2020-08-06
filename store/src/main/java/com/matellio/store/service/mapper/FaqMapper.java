package com.matellio.store.service.mapper;


import com.matellio.store.domain.*;
import com.matellio.store.service.dto.FaqDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Faq} and its DTO {@link FaqDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FaqMapper extends EntityMapper<FaqDTO, Faq> {



    default Faq fromId(String id) {
        if (id == null) {
            return null;
        }
        Faq faq = new Faq();
        faq.setId(id);
        return faq;
    }
}
