package com.matellio.store.service.impl;

import com.matellio.store.service.FaqService;
import com.matellio.store.domain.Faq;
import com.matellio.store.repository.FaqRepository;
import com.matellio.store.service.dto.FaqDTO;
import com.matellio.store.service.mapper.FaqMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Faq}.
 */
@Service
public class FaqServiceImpl implements FaqService {

    private final Logger log = LoggerFactory.getLogger(FaqServiceImpl.class);

    private final FaqRepository faqRepository;

    private final FaqMapper faqMapper;

    public FaqServiceImpl(FaqRepository faqRepository, FaqMapper faqMapper) {
        this.faqRepository = faqRepository;
        this.faqMapper = faqMapper;
    }

    @Override
    public FaqDTO save(FaqDTO faqDTO) {
        log.debug("Request to save Faq : {}", faqDTO);
        Faq faq = faqMapper.toEntity(faqDTO);
        faq = faqRepository.save(faq);
        return faqMapper.toDto(faq);
    }

    @Override
    public Page<FaqDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Faqs");
        return faqRepository.findAll(pageable)
            .map(faqMapper::toDto);
    }


    @Override
    public Optional<FaqDTO> findOne(String id) {
        log.debug("Request to get Faq : {}", id);
        return faqRepository.findById(id)
            .map(faqMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Faq : {}", id);
        faqRepository.deleteById(id);
    }
}
