package com.matellio.store.web.rest;

import com.matellio.store.StoreApp;
import com.matellio.store.domain.DistributionStore;
import com.matellio.store.repository.DistributionStoreRepository;
import com.matellio.store.service.DistributionStoreService;
import com.matellio.store.service.dto.DistributionStoreDTO;
import com.matellio.store.service.mapper.DistributionStoreMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DistributionStoreResource} REST controller.
 */
@SpringBootTest(classes = StoreApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DistributionStoreResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    @Autowired
    private DistributionStoreRepository distributionStoreRepository;

    @Autowired
    private DistributionStoreMapper distributionStoreMapper;

    @Autowired
    private DistributionStoreService distributionStoreService;

    @Autowired
    private MockMvc restDistributionStoreMockMvc;

    private DistributionStore distributionStore;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DistributionStore createEntity() {
        DistributionStore distributionStore = new DistributionStore()
            .name(DEFAULT_NAME)
            .userId(DEFAULT_USER_ID);
        return distributionStore;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DistributionStore createUpdatedEntity() {
        DistributionStore distributionStore = new DistributionStore()
            .name(UPDATED_NAME)
            .userId(UPDATED_USER_ID);
        return distributionStore;
    }

    @BeforeEach
    public void initTest() {
        distributionStoreRepository.deleteAll();
        distributionStore = createEntity();
    }

    @Test
    public void createDistributionStore() throws Exception {
        int databaseSizeBeforeCreate = distributionStoreRepository.findAll().size();
        // Create the DistributionStore
        DistributionStoreDTO distributionStoreDTO = distributionStoreMapper.toDto(distributionStore);
        restDistributionStoreMockMvc.perform(post("/api/distribution-stores")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(distributionStoreDTO)))
            .andExpect(status().isCreated());

        // Validate the DistributionStore in the database
        List<DistributionStore> distributionStoreList = distributionStoreRepository.findAll();
        assertThat(distributionStoreList).hasSize(databaseSizeBeforeCreate + 1);
        DistributionStore testDistributionStore = distributionStoreList.get(distributionStoreList.size() - 1);
        assertThat(testDistributionStore.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDistributionStore.getUserId()).isEqualTo(DEFAULT_USER_ID);
    }

    @Test
    public void createDistributionStoreWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = distributionStoreRepository.findAll().size();

        // Create the DistributionStore with an existing ID
        distributionStore.setId("existing_id");
        DistributionStoreDTO distributionStoreDTO = distributionStoreMapper.toDto(distributionStore);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDistributionStoreMockMvc.perform(post("/api/distribution-stores")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(distributionStoreDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DistributionStore in the database
        List<DistributionStore> distributionStoreList = distributionStoreRepository.findAll();
        assertThat(distributionStoreList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllDistributionStores() throws Exception {
        // Initialize the database
        distributionStoreRepository.save(distributionStore);

        // Get all the distributionStoreList
        restDistributionStoreMockMvc.perform(get("/api/distribution-stores?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(distributionStore.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)));
    }
    
    @Test
    public void getDistributionStore() throws Exception {
        // Initialize the database
        distributionStoreRepository.save(distributionStore);

        // Get the distributionStore
        restDistributionStoreMockMvc.perform(get("/api/distribution-stores/{id}", distributionStore.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(distributionStore.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID));
    }
    @Test
    public void getNonExistingDistributionStore() throws Exception {
        // Get the distributionStore
        restDistributionStoreMockMvc.perform(get("/api/distribution-stores/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateDistributionStore() throws Exception {
        // Initialize the database
        distributionStoreRepository.save(distributionStore);

        int databaseSizeBeforeUpdate = distributionStoreRepository.findAll().size();

        // Update the distributionStore
        DistributionStore updatedDistributionStore = distributionStoreRepository.findById(distributionStore.getId()).get();
        updatedDistributionStore
            .name(UPDATED_NAME)
            .userId(UPDATED_USER_ID);
        DistributionStoreDTO distributionStoreDTO = distributionStoreMapper.toDto(updatedDistributionStore);

        restDistributionStoreMockMvc.perform(put("/api/distribution-stores")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(distributionStoreDTO)))
            .andExpect(status().isOk());

        // Validate the DistributionStore in the database
        List<DistributionStore> distributionStoreList = distributionStoreRepository.findAll();
        assertThat(distributionStoreList).hasSize(databaseSizeBeforeUpdate);
        DistributionStore testDistributionStore = distributionStoreList.get(distributionStoreList.size() - 1);
        assertThat(testDistributionStore.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDistributionStore.getUserId()).isEqualTo(UPDATED_USER_ID);
    }

    @Test
    public void updateNonExistingDistributionStore() throws Exception {
        int databaseSizeBeforeUpdate = distributionStoreRepository.findAll().size();

        // Create the DistributionStore
        DistributionStoreDTO distributionStoreDTO = distributionStoreMapper.toDto(distributionStore);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDistributionStoreMockMvc.perform(put("/api/distribution-stores")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(distributionStoreDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DistributionStore in the database
        List<DistributionStore> distributionStoreList = distributionStoreRepository.findAll();
        assertThat(distributionStoreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteDistributionStore() throws Exception {
        // Initialize the database
        distributionStoreRepository.save(distributionStore);

        int databaseSizeBeforeDelete = distributionStoreRepository.findAll().size();

        // Delete the distributionStore
        restDistributionStoreMockMvc.perform(delete("/api/distribution-stores/{id}", distributionStore.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DistributionStore> distributionStoreList = distributionStoreRepository.findAll();
        assertThat(distributionStoreList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
