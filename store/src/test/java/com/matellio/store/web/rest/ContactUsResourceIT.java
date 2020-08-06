package com.matellio.store.web.rest;

import com.matellio.store.StoreApp;
import com.matellio.store.domain.ContactUs;
import com.matellio.store.repository.ContactUsRepository;
import com.matellio.store.service.ContactUsService;
import com.matellio.store.service.dto.ContactUsDTO;
import com.matellio.store.service.mapper.ContactUsMapper;

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
 * Integration tests for the {@link ContactUsResource} REST controller.
 */
@SpringBootTest(classes = StoreApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ContactUsResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    @Autowired
    private ContactUsRepository contactUsRepository;

    @Autowired
    private ContactUsMapper contactUsMapper;

    @Autowired
    private ContactUsService contactUsService;

    @Autowired
    private MockMvc restContactUsMockMvc;

    private ContactUs contactUs;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContactUs createEntity() {
        ContactUs contactUs = new ContactUs()
            .name(DEFAULT_NAME)
            .email(DEFAULT_EMAIL)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .message(DEFAULT_MESSAGE);
        return contactUs;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContactUs createUpdatedEntity() {
        ContactUs contactUs = new ContactUs()
            .name(UPDATED_NAME)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .message(UPDATED_MESSAGE);
        return contactUs;
    }

    @BeforeEach
    public void initTest() {
        contactUsRepository.deleteAll();
        contactUs = createEntity();
    }

    @Test
    public void createContactUs() throws Exception {
        int databaseSizeBeforeCreate = contactUsRepository.findAll().size();
        // Create the ContactUs
        ContactUsDTO contactUsDTO = contactUsMapper.toDto(contactUs);
        restContactUsMockMvc.perform(post("/api/contactuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contactUsDTO)))
            .andExpect(status().isCreated());

        // Validate the ContactUs in the database
        List<ContactUs> contactUsList = contactUsRepository.findAll();
        assertThat(contactUsList).hasSize(databaseSizeBeforeCreate + 1);
        ContactUs testContactUs = contactUsList.get(contactUsList.size() - 1);
        assertThat(testContactUs.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testContactUs.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testContactUs.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testContactUs.getMessage()).isEqualTo(DEFAULT_MESSAGE);
    }

    @Test
    public void createContactUsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contactUsRepository.findAll().size();

        // Create the ContactUs with an existing ID
        contactUs.setId("existing_id");
        ContactUsDTO contactUsDTO = contactUsMapper.toDto(contactUs);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContactUsMockMvc.perform(post("/api/contactuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contactUsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ContactUs in the database
        List<ContactUs> contactUsList = contactUsRepository.findAll();
        assertThat(contactUsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllContactuses() throws Exception {
        // Initialize the database
        contactUsRepository.save(contactUs);

        // Get all the contactUsList
        restContactUsMockMvc.perform(get("/api/contactuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contactUs.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE)));
    }
    
    @Test
    public void getContactUs() throws Exception {
        // Initialize the database
        contactUsRepository.save(contactUs);

        // Get the contactUs
        restContactUsMockMvc.perform(get("/api/contactuses/{id}", contactUs.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(contactUs.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE));
    }
    @Test
    public void getNonExistingContactUs() throws Exception {
        // Get the contactUs
        restContactUsMockMvc.perform(get("/api/contactuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateContactUs() throws Exception {
        // Initialize the database
        contactUsRepository.save(contactUs);

        int databaseSizeBeforeUpdate = contactUsRepository.findAll().size();

        // Update the contactUs
        ContactUs updatedContactUs = contactUsRepository.findById(contactUs.getId()).get();
        updatedContactUs
            .name(UPDATED_NAME)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .message(UPDATED_MESSAGE);
        ContactUsDTO contactUsDTO = contactUsMapper.toDto(updatedContactUs);

        restContactUsMockMvc.perform(put("/api/contactuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contactUsDTO)))
            .andExpect(status().isOk());

        // Validate the ContactUs in the database
        List<ContactUs> contactUsList = contactUsRepository.findAll();
        assertThat(contactUsList).hasSize(databaseSizeBeforeUpdate);
        ContactUs testContactUs = contactUsList.get(contactUsList.size() - 1);
        assertThat(testContactUs.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testContactUs.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testContactUs.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testContactUs.getMessage()).isEqualTo(UPDATED_MESSAGE);
    }

    @Test
    public void updateNonExistingContactUs() throws Exception {
        int databaseSizeBeforeUpdate = contactUsRepository.findAll().size();

        // Create the ContactUs
        ContactUsDTO contactUsDTO = contactUsMapper.toDto(contactUs);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContactUsMockMvc.perform(put("/api/contactuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contactUsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ContactUs in the database
        List<ContactUs> contactUsList = contactUsRepository.findAll();
        assertThat(contactUsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteContactUs() throws Exception {
        // Initialize the database
        contactUsRepository.save(contactUs);

        int databaseSizeBeforeDelete = contactUsRepository.findAll().size();

        // Delete the contactUs
        restContactUsMockMvc.perform(delete("/api/contactuses/{id}", contactUs.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ContactUs> contactUsList = contactUsRepository.findAll();
        assertThat(contactUsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
