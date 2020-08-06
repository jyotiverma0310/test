package com.matellio.store.service.impl;

import com.matellio.store.service.TransactionsService;
import com.matellio.store.domain.Transactions;
import com.matellio.store.repository.TransactionsRepository;
import com.matellio.store.service.dto.TransactionsDTO;
import com.matellio.store.service.mapper.TransactionsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Transactions}.
 */
@Service
public class TransactionsServiceImpl implements TransactionsService {

    private final Logger log = LoggerFactory.getLogger(TransactionsServiceImpl.class);

    private final TransactionsRepository transactionsRepository;

    private final TransactionsMapper transactionsMapper;

    public TransactionsServiceImpl(TransactionsRepository transactionsRepository, TransactionsMapper transactionsMapper) {
        this.transactionsRepository = transactionsRepository;
        this.transactionsMapper = transactionsMapper;
    }

    @Override
    public TransactionsDTO save(TransactionsDTO transactionsDTO) {
        log.debug("Request to save Transactions : {}", transactionsDTO);
        Transactions transactions = transactionsMapper.toEntity(transactionsDTO);
        transactions = transactionsRepository.save(transactions);
        return transactionsMapper.toDto(transactions);
    }

    @Override
    public Page<TransactionsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Transactions");
        return transactionsRepository.findAll(pageable)
            .map(transactionsMapper::toDto);
    }


    @Override
    public Optional<TransactionsDTO> findOne(String id) {
        log.debug("Request to get Transactions : {}", id);
        return transactionsRepository.findById(id)
            .map(transactionsMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Transactions : {}", id);
        transactionsRepository.deleteById(id);
    }
}
