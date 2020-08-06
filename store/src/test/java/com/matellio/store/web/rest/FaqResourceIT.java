package com.matellio.store.web.rest;

import com.matellio.store.StoreApp;
import com.matellio.store.domain.Faq;
import com.matellio.store.repository.FaqRepository;
import com.matellio.store.service.FaqService;
import com.matellio.store.service.dto.FaqDTO;
import com.matellio.store.service.mapper.FaqMapper;

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

import com.matellio.store.domain.enumeration.FaqType;
/**
 * Integration tests for the {@link FaqResource} REST controller.
 */
@SpringBootTest(classes = StoreApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FaqResourceIT {

    private static final FaqType DEFAULT_TYPE = FaqType.Customer;
    private static final FaqType UPDATED_TYPE = FaqType.Delivey;

    private static final String DEFAULT_QUESTION = "AAAAAAAAAA";
    private static final String UPDATED_QUESTION = "BBBBBBBBBB";

    private static final String DEFAULT_ANSWER = "AAAAAAAAAA";
    private static final String UPDATED_ANSWER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    @Autowired
    private FaqRepository faqRepository;

    @Autowired
    private FaqMapper faqMapper;

    @Autowired
    private FaqService faqService;

    @Autowired
    private MockMvc restFaqMockMvc;

    private Faq faq;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Faq createEntity() {
        Faq faq = new Faq()
            .type(DEFAULT_TYPE)
            .question(DEFAULT_QUESTION)
            .answer(DEFAULT_ANSWER)
            .isActive(DEFAULT_IS_ACTIVE);
        return faq;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Faq createUpdatedEntity() {
        Faq faq = new Faq()
            .type(UPDATED_TYPE)
            .question(UPDATED_QUESTION)
            .answer(UPDATED_ANSWER)
            .isActive(UPDATED_IS_ACTIVE);
        return faq;
    }

    @BeforeEach
    public void initTest() {
        faqRepository.deleteAll();
        faq = createEntity();
    }

    @Test
    public void createFaq() throws Exception {
        int databaseSizeBeforeCreate = faqRepository.findAll().size();
        // Create the Faq
        FaqDTO faqDTO = faqMapper.toDto(faq);
        restFaqMockMvc.perform(post("/api/faqs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(faqDTO)))
            .andExpect(status().isCreated());

        // Validate the Faq in the database
        List<Faq> faqList = faqRepository.findAll();
        assertThat(faqList).hasSize(databaseSizeBeforeCreate + 1);
        Faq testFaq = faqList.get(faqList.size() - 1);
        assertThat(testFaq.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testFaq.getQuestion()).isEqualTo(DEFAULT_QUESTION);
        assertThat(testFaq.getAnswer()).isEqualTo(DEFAULT_ANSWER);
        assertThat(testFaq.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
    }

    @Test
    public void createFaqWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = faqRepository.findAll().size();

        // Create the Faq with an existing ID
        faq.setId("existing_id");
        FaqDTO faqDTO = faqMapper.toDto(faq);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFaqMockMvc.perform(post("/api/faqs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(faqDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Faq in the database
        List<Faq> faqList = faqRepository.findAll();
        assertThat(faqList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllFaqs() throws Exception {
        // Initialize the database
        faqRepository.save(faq);

        // Get all the faqList
        restFaqMockMvc.perform(get("/api/faqs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(faq.getId())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].question").value(hasItem(DEFAULT_QUESTION)))
            .andExpect(jsonPath("$.[*].answer").value(hasItem(DEFAULT_ANSWER)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())));
    }
    
    @Test
    public void getFaq() throws Exception {
        // Initialize the database
        faqRepository.save(faq);

        // Get the faq
        restFaqMockMvc.perform(get("/api/faqs/{id}", faq.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(faq.getId()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.question").value(DEFAULT_QUESTION))
            .andExpect(jsonPath("$.answer").value(DEFAULT_ANSWER))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()));
    }
    @Test
    public void getNonExistingFaq() throws Exception {
        // Get the faq
        restFaqMockMvc.perform(get("/api/faqs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateFaq() throws Exception {
        // Initialize the database
        faqRepository.save(faq);

        int databaseSizeBeforeUpdate = faqRepository.findAll().size();

        // Update the faq
        Faq updatedFaq = faqRepository.findById(faq.getId()).get();
        updatedFaq
            .type(UPDATED_TYPE)
            .question(UPDATED_QUESTION)
            .answer(UPDATED_ANSWER)
            .isActive(UPDATED_IS_ACTIVE);
        FaqDTO faqDTO = faqMapper.toDto(updatedFaq);

        restFaqMockMvc.perform(put("/api/faqs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(faqDTO)))
            .andExpect(status().isOk());

        // Validate the Faq in the database
        List<Faq> faqList = faqRepository.findAll();
        assertThat(faqList).hasSize(databaseSizeBeforeUpdate);
        Faq testFaq = faqList.get(faqList.size() - 1);
        assertThat(testFaq.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testFaq.getQuestion()).isEqualTo(UPDATED_QUESTION);
        assertThat(testFaq.getAnswer()).isEqualTo(UPDATED_ANSWER);
        assertThat(testFaq.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
    }

    @Test
    public void updateNonExistingFaq() throws Exception {
        int databaseSizeBeforeUpdate = faqRepository.findAll().size();

        // Create the Faq
        FaqDTO faqDTO = faqMapper.toDto(faq);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFaqMockMvc.perform(put("/api/faqs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(faqDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Faq in the database
        List<Faq> faqList = faqRepository.findAll();
        assertThat(faqList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteFaq() throws Exception {
        // Initialize the database
        faqRepository.save(faq);

        int databaseSizeBeforeDelete = faqRepository.findAll().size();

        // Delete the faq
        restFaqMockMvc.perform(delete("/api/faqs/{id}", faq.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Faq> faqList = faqRepository.findAll();
        assertThat(faqList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
