package com.matellio.store.service.impl;

import com.matellio.store.service.UserLocationService;
import com.matellio.store.domain.UserLocation;
import com.matellio.store.repository.UserLocationRepository;
import com.matellio.store.service.dto.UserLocationDTO;
import com.matellio.store.service.mapper.UserLocationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link UserLocation}.
 */
@Service
public class UserLocationServiceImpl implements UserLocationService {

    private final Logger log = LoggerFactory.getLogger(UserLocationServiceImpl.class);

    private final UserLocationRepository userLocationRepository;

    private final UserLocationMapper userLocationMapper;

    public UserLocationServiceImpl(UserLocationRepository userLocationRepository, UserLocationMapper userLocationMapper) {
        this.userLocationRepository = userLocationRepository;
        this.userLocationMapper = userLocationMapper;
    }

    @Override
    public UserLocationDTO save(UserLocationDTO userLocationDTO) {
        log.debug("Request to save UserLocation : {}", userLocationDTO);
        UserLocation userLocation = userLocationMapper.toEntity(userLocationDTO);
        userLocation = userLocationRepository.save(userLocation);
        return userLocationMapper.toDto(userLocation);
    }

    @Override
    public Page<UserLocationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserLocations");
        return userLocationRepository.findAll(pageable)
            .map(userLocationMapper::toDto);
    }


    @Override
    public Optional<UserLocationDTO> findOne(String id) {
        log.debug("Request to get UserLocation : {}", id);
        return userLocationRepository.findById(id)
            .map(userLocationMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete UserLocation : {}", id);
        userLocationRepository.deleteById(id);
    }
}
