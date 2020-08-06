package com.matellio.store.web.rest;

import com.matellio.store.StoreApp;
import com.matellio.store.domain.TermsAndCondition;
import com.matellio.store.repository.TermsAndConditionRepository;
import com.matellio.store.service.TermsAndConditionService;
import com.matellio.store.service.dto.TermsAndConditionDTO;
import com.matellio.store.service.mapper.TermsAndConditionMapper;

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
 * Integration tests for the {@link TermsAndConditionResource} REST controller.
 */
@SpringBootTest(classes = StoreApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TermsAndConditionResourceIT {

    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_NOTE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    @Autowired
    private TermsAndConditionRepository termsAndConditionRepository;

    @Autowired
    private TermsAndConditionMapper termsAndConditionMapper;

    @Autowired
    private TermsAndConditionService termsAndConditionService;

    @Autowired
    private MockMvc restTermsAndConditionMockMvc;

    private TermsAndCondition termsAndCondition;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TermsAndCondition createEntity() {
        TermsAndCondition termsAndCondition = new TermsAndCondition()
            .note(DEFAULT_NOTE)
            .isActive(DEFAULT_IS_ACTIVE);
        return termsAndCondition;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TermsAndCondition createUpdatedEntity() {
        TermsAndCondition termsAndCondition = new TermsAndCondition()
            .note(UPDATED_NOTE)
            .isActive(UPDATED_IS_ACTIVE);
        return termsAndCondition;
    }

    @BeforeEach
    public void initTest() {
        termsAndConditionRepository.deleteAll();
        termsAndCondition = createEntity();
    }

    @Test
    public void createTermsAndCondition() throws Exception {
        int databaseSizeBeforeCreate = termsAndConditionRepository.findAll().size();
        // Create the TermsAndCondition
        TermsAndConditionDTO termsAndConditionDTO = termsAndConditionMapper.toDto(termsAndCondition);
        restTermsAndConditionMockMvc.perform(post("/api/terms-and-conditions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(termsAndConditionDTO)))
            .andExpect(status().isCreated());

        // Validate the TermsAndCondition in the database
        List<TermsAndCondition> termsAndConditionList = termsAndConditionRepository.findAll();
        assertThat(termsAndConditionList).hasSize(databaseSizeBeforeCreate + 1);
        TermsAndCondition testTermsAndCondition = termsAndConditionList.get(termsAndConditionList.size() - 1);
        assertThat(testTermsAndCondition.getNote()).isEqualTo(DEFAULT_NOTE);
        assertThat(testTermsAndCondition.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
    }

    @Test
    public void createTermsAndConditionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = termsAndConditionRepository.findAll().size();

        // Create the TermsAndCondition with an existing ID
        termsAndCondition.setId("existing_id");
        TermsAndConditionDTO termsAndConditionDTO = termsAndConditionMapper.toDto(termsAndCondition);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTermsAndConditionMockMvc.perform(post("/api/terms-and-conditions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(termsAndConditionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TermsAndCondition in the database
        List<TermsAndCondition> termsAndConditionList = termsAndConditionRepository.findAll();
        assertThat(termsAndConditionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllTermsAndConditions() throws Exception {
        // Initialize the database
        termsAndConditionRepository.save(termsAndCondition);

        // Get all the termsAndConditionList
        restTermsAndConditionMockMvc.perform(get("/api/terms-and-conditions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(termsAndCondition.getId())))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())));
    }
    
    @Test
    public void getTermsAndCondition() throws Exception {
        // Initialize the database
        termsAndConditionRepository.save(termsAndCondition);

        // Get the termsAndCondition
        restTermsAndConditionMockMvc.perform(get("/api/terms-and-conditions/{id}", termsAndCondition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(termsAndCondition.getId()))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()));
    }
    @Test
    public void getNonExistingTermsAndCondition() throws Exception {
        // Get the termsAndCondition
        restTermsAndConditionMockMvc.perform(get("/api/terms-and-conditions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateTermsAndCondition() throws Exception {
        // Initialize the database
        termsAndConditionRepository.save(termsAndCondition);

        int databaseSizeBeforeUpdate = termsAndConditionRepository.findAll().size();

        // Update the termsAndCondition
        TermsAndCondition updatedTermsAndCondition = termsAndConditionRepository.findById(termsAndCondition.getId()).get();
        updatedTermsAndCondition
            .note(UPDATED_NOTE)
            .isActive(UPDATED_IS_ACTIVE);
        TermsAndConditionDTO termsAndConditionDTO = termsAndConditionMapper.toDto(updatedTermsAndCondition);

        restTermsAndConditionMockMvc.perform(put("/api/terms-and-conditions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(termsAndConditionDTO)))
            .andExpect(status().isOk());

        // Validate the TermsAndCondition in the database
        List<TermsAndCondition> termsAndConditionList = termsAndConditionRepository.findAll();
        assertThat(termsAndConditionList).hasSize(databaseSizeBeforeUpdate);
        TermsAndCondition testTermsAndCondition = termsAndConditionList.get(termsAndConditionList.size() - 1);
        assertThat(testTermsAndCondition.getNote()).isEqualTo(UPDATED_NOTE);
        assertThat(testTermsAndCondition.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
    }

    @Test
    public void updateNonExistingTermsAndCondition() throws Exception {
        int databaseSizeBeforeUpdate = termsAndConditionRepository.findAll().size();

        // Create the TermsAndCondition
        TermsAndConditionDTO termsAndConditionDTO = termsAndConditionMapper.toDto(termsAndCondition);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTermsAndConditionMockMvc.perform(put("/api/terms-and-conditions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(termsAndConditionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TermsAndCondition in the database
        List<TermsAndCondition> termsAndConditionList = termsAndConditionRepository.findAll();
        assertThat(termsAndConditionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteTermsAndCondition() throws Exception {
        // Initialize the database
        termsAndConditionRepository.save(termsAndCondition);

        int databaseSizeBeforeDelete = termsAndConditionRepository.findAll().size();

        // Delete the termsAndCondition
        restTermsAndConditionMockMvc.perform(delete("/api/terms-and-conditions/{id}", termsAndCondition.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TermsAndCondition> termsAndConditionList = termsAndConditionRepository.findAll();
        assertThat(termsAndConditionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
