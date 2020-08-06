package com.matellio.store.service.mapper;


import com.matellio.store.domain.*;
import com.matellio.store.service.dto.ContactUsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ContactUs} and its DTO {@link ContactUsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ContactUsMapper extends EntityMapper<ContactUsDTO, ContactUs> {



    default ContactUs fromId(String id) {
        if (id == null) {
            return null;
        }
        ContactUs contactUs = new ContactUs();
        contactUs.setId(id);
        return contactUs;
    }
}
