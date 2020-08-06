package com.matellio.store.service.impl;

import com.matellio.store.service.ContactUsService;
import com.matellio.store.domain.ContactUs;
import com.matellio.store.repository.ContactUsRepository;
import com.matellio.store.service.dto.ContactUsDTO;
import com.matellio.store.service.mapper.ContactUsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ContactUs}.
 */
@Service
public class ContactUsServiceImpl implements ContactUsService {

    private final Logger log = LoggerFactory.getLogger(ContactUsServiceImpl.class);

    private final ContactUsRepository contactUsRepository;

    private final ContactUsMapper contactUsMapper;

    public ContactUsServiceImpl(ContactUsRepository contactUsRepository, ContactUsMapper contactUsMapper) {
        this.contactUsRepository = contactUsRepository;
        this.contactUsMapper = contactUsMapper;
    }

    @Override
    public ContactUsDTO save(ContactUsDTO contactUsDTO) {
        log.debug("Request to save ContactUs : {}", contactUsDTO);
        ContactUs contactUs = contactUsMapper.toEntity(contactUsDTO);
        contactUs = contactUsRepository.save(contactUs);
        return contactUsMapper.toDto(contactUs);
    }

    @Override
    public Page<ContactUsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Contactuses");
        return contactUsRepository.findAll(pageable)
            .map(contactUsMapper::toDto);
    }


    @Override
    public Optional<ContactUsDTO> findOne(String id) {
        log.debug("Request to get ContactUs : {}", id);
        return contactUsRepository.findById(id)
            .map(contactUsMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete ContactUs : {}", id);
        contactUsRepository.deleteById(id);
    }
}
