package com.matellio.store.web.rest;

import com.matellio.store.StoreApp;
import com.matellio.store.domain.AboutUs;
import com.matellio.store.repository.AboutUsRepository;
import com.matellio.store.service.AboutUsService;
import com.matellio.store.service.dto.AboutUsDTO;
import com.matellio.store.service.mapper.AboutUsMapper;

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
 * Integration tests for the {@link AboutUsResource} REST controller.
 */
@SpringBootTest(classes = StoreApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AboutUsResourceIT {

    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_NOTE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    @Autowired
    private AboutUsRepository aboutUsRepository;

    @Autowired
    private AboutUsMapper aboutUsMapper;

    @Autowired
    private AboutUsService aboutUsService;

    @Autowired
    private MockMvc restAboutUsMockMvc;

    private AboutUs aboutUs;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AboutUs createEntity() {
        AboutUs aboutUs = new AboutUs()
            .note(DEFAULT_NOTE)
            .isActive(DEFAULT_IS_ACTIVE);
        return aboutUs;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AboutUs createUpdatedEntity() {
        AboutUs aboutUs = new AboutUs()
            .note(UPDATED_NOTE)
            .isActive(UPDATED_IS_ACTIVE);
        return aboutUs;
    }

    @BeforeEach
    public void initTest() {
        aboutUsRepository.deleteAll();
        aboutUs = createEntity();
    }

    @Test
    public void createAboutUs() throws Exception {
        int databaseSizeBeforeCreate = aboutUsRepository.findAll().size();
        // Create the AboutUs
        AboutUsDTO aboutUsDTO = aboutUsMapper.toDto(aboutUs);
        restAboutUsMockMvc.perform(post("/api/aboutuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(aboutUsDTO)))
            .andExpect(status().isCreated());

        // Validate the AboutUs in the database
        List<AboutUs> aboutUsList = aboutUsRepository.findAll();
        assertThat(aboutUsList).hasSize(databaseSizeBeforeCreate + 1);
        AboutUs testAboutUs = aboutUsList.get(aboutUsList.size() - 1);
        assertThat(testAboutUs.getNote()).isEqualTo(DEFAULT_NOTE);
        assertThat(testAboutUs.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
    }

    @Test
    public void createAboutUsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = aboutUsRepository.findAll().size();

        // Create the AboutUs with an existing ID
        aboutUs.setId("existing_id");
        AboutUsDTO aboutUsDTO = aboutUsMapper.toDto(aboutUs);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAboutUsMockMvc.perform(post("/api/aboutuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(aboutUsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AboutUs in the database
        List<AboutUs> aboutUsList = aboutUsRepository.findAll();
        assertThat(aboutUsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllAboutuses() throws Exception {
        // Initialize the database
        aboutUsRepository.save(aboutUs);

        // Get all the aboutUsList
        restAboutUsMockMvc.perform(get("/api/aboutuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(aboutUs.getId())))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())));
    }
    
    @Test
    public void getAboutUs() throws Exception {
        // Initialize the database
        aboutUsRepository.save(aboutUs);

        // Get the aboutUs
        restAboutUsMockMvc.perform(get("/api/aboutuses/{id}", aboutUs.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(aboutUs.getId()))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()));
    }
    @Test
    public void getNonExistingAboutUs() throws Exception {
        // Get the aboutUs
        restAboutUsMockMvc.perform(get("/api/aboutuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAboutUs() throws Exception {
        // Initialize the database
        aboutUsRepository.save(aboutUs);

        int databaseSizeBeforeUpdate = aboutUsRepository.findAll().size();

        // Update the aboutUs
        AboutUs updatedAboutUs = aboutUsRepository.findById(aboutUs.getId()).get();
        updatedAboutUs
            .note(UPDATED_NOTE)
            .isActive(UPDATED_IS_ACTIVE);
        AboutUsDTO aboutUsDTO = aboutUsMapper.toDto(updatedAboutUs);

        restAboutUsMockMvc.perform(put("/api/aboutuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(aboutUsDTO)))
            .andExpect(status().isOk());

        // Validate the AboutUs in the database
        List<AboutUs> aboutUsList = aboutUsRepository.findAll();
        assertThat(aboutUsList).hasSize(databaseSizeBeforeUpdate);
        AboutUs testAboutUs = aboutUsList.get(aboutUsList.size() - 1);
        assertThat(testAboutUs.getNote()).isEqualTo(UPDATED_NOTE);
        assertThat(testAboutUs.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
    }

    @Test
    public void updateNonExistingAboutUs() throws Exception {
        int databaseSizeBeforeUpdate = aboutUsRepository.findAll().size();

        // Create the AboutUs
        AboutUsDTO aboutUsDTO = aboutUsMapper.toDto(aboutUs);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAboutUsMockMvc.perform(put("/api/aboutuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(aboutUsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AboutUs in the database
        List<AboutUs> aboutUsList = aboutUsRepository.findAll();
        assertThat(aboutUsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteAboutUs() throws Exception {
        // Initialize the database
        aboutUsRepository.save(aboutUs);

        int databaseSizeBeforeDelete = aboutUsRepository.findAll().size();

        // Delete the aboutUs
        restAboutUsMockMvc.perform(delete("/api/aboutuses/{id}", aboutUs.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AboutUs> aboutUsList = aboutUsRepository.findAll();
        assertThat(aboutUsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
