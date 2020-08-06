package com.matellio.store.service.mapper;


import com.matellio.store.domain.*;
import com.matellio.store.service.dto.AboutUsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AboutUs} and its DTO {@link AboutUsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AboutUsMapper extends EntityMapper<AboutUsDTO, AboutUs> {



    default AboutUs fromId(String id) {
        if (id == null) {
            return null;
        }
        AboutUs aboutUs = new AboutUs();
        aboutUs.setId(id);
        return aboutUs;
    }
}
