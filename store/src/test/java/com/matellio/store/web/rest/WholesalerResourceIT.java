package com.matellio.store.web.rest;

import com.matellio.store.StoreApp;
import com.matellio.store.domain.Wholesaler;
import com.matellio.store.repository.WholesalerRepository;
import com.matellio.store.service.WholesalerService;
import com.matellio.store.service.dto.WholesalerDTO;
import com.matellio.store.service.mapper.WholesalerMapper;

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
 * Integration tests for the {@link WholesalerResource} REST controller.
 */
@SpringBootTest(classes = StoreApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class WholesalerResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    @Autowired
    private WholesalerRepository wholesalerRepository;

    @Autowired
    private WholesalerMapper wholesalerMapper;

    @Autowired
    private WholesalerService wholesalerService;

    @Autowired
    private MockMvc restWholesalerMockMvc;

    private Wholesaler wholesaler;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Wholesaler createEntity() {
        Wholesaler wholesaler = new Wholesaler()
            .name(DEFAULT_NAME)
            .userId(DEFAULT_USER_ID);
        return wholesaler;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Wholesaler createUpdatedEntity() {
        Wholesaler wholesaler = new Wholesaler()
            .name(UPDATED_NAME)
            .userId(UPDATED_USER_ID);
        return wholesaler;
    }

    @BeforeEach
    public void initTest() {
        wholesalerRepository.deleteAll();
        wholesaler = createEntity();
    }

    @Test
    public void createWholesaler() throws Exception {
        int databaseSizeBeforeCreate = wholesalerRepository.findAll().size();
        // Create the Wholesaler
        WholesalerDTO wholesalerDTO = wholesalerMapper.toDto(wholesaler);
        restWholesalerMockMvc.perform(post("/api/wholesalers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(wholesalerDTO)))
            .andExpect(status().isCreated());

        // Validate the Wholesaler in the database
        List<Wholesaler> wholesalerList = wholesalerRepository.findAll();
        assertThat(wholesalerList).hasSize(databaseSizeBeforeCreate + 1);
        Wholesaler testWholesaler = wholesalerList.get(wholesalerList.size() - 1);
        assertThat(testWholesaler.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testWholesaler.getUserId()).isEqualTo(DEFAULT_USER_ID);
    }

    @Test
    public void createWholesalerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = wholesalerRepository.findAll().size();

        // Create the Wholesaler with an existing ID
        wholesaler.setId("existing_id");
        WholesalerDTO wholesalerDTO = wholesalerMapper.toDto(wholesaler);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWholesalerMockMvc.perform(post("/api/wholesalers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(wholesalerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Wholesaler in the database
        List<Wholesaler> wholesalerList = wholesalerRepository.findAll();
        assertThat(wholesalerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllWholesalers() throws Exception {
        // Initialize the database
        wholesalerRepository.save(wholesaler);

        // Get all the wholesalerList
        restWholesalerMockMvc.perform(get("/api/wholesalers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(wholesaler.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)));
    }
    
    @Test
    public void getWholesaler() throws Exception {
        // Initialize the database
        wholesalerRepository.save(wholesaler);

        // Get the wholesaler
        restWholesalerMockMvc.perform(get("/api/wholesalers/{id}", wholesaler.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(wholesaler.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID));
    }
    @Test
    public void getNonExistingWholesaler() throws Exception {
        // Get the wholesaler
        restWholesalerMockMvc.perform(get("/api/wholesalers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateWholesaler() throws Exception {
        // Initialize the database
        wholesalerRepository.save(wholesaler);

        int databaseSizeBeforeUpdate = wholesalerRepository.findAll().size();

        // Update the wholesaler
        Wholesaler updatedWholesaler = wholesalerRepository.findById(wholesaler.getId()).get();
        updatedWholesaler
            .name(UPDATED_NAME)
            .userId(UPDATED_USER_ID);
        WholesalerDTO wholesalerDTO = wholesalerMapper.toDto(updatedWholesaler);

        restWholesalerMockMvc.perform(put("/api/wholesalers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(wholesalerDTO)))
            .andExpect(status().isOk());

        // Validate the Wholesaler in the database
        List<Wholesaler> wholesalerList = wholesalerRepository.findAll();
        assertThat(wholesalerList).hasSize(databaseSizeBeforeUpdate);
        Wholesaler testWholesaler = wholesalerList.get(wholesalerList.size() - 1);
        assertThat(testWholesaler.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testWholesaler.getUserId()).isEqualTo(UPDATED_USER_ID);
    }

    @Test
    public void updateNonExistingWholesaler() throws Exception {
        int databaseSizeBeforeUpdate = wholesalerRepository.findAll().size();

        // Create the Wholesaler
        WholesalerDTO wholesalerDTO = wholesalerMapper.toDto(wholesaler);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWholesalerMockMvc.perform(put("/api/wholesalers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(wholesalerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Wholesaler in the database
        List<Wholesaler> wholesalerList = wholesalerRepository.findAll();
        assertThat(wholesalerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteWholesaler() throws Exception {
        // Initialize the database
        wholesalerRepository.save(wholesaler);

        int databaseSizeBeforeDelete = wholesalerRepository.findAll().size();

        // Delete the wholesaler
        restWholesalerMockMvc.perform(delete("/api/wholesalers/{id}", wholesaler.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Wholesaler> wholesalerList = wholesalerRepository.findAll();
        assertThat(wholesalerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
