package com.matellio.store.web.rest;

import com.matellio.store.StoreApp;
import com.matellio.store.domain.ProductSubCategory;
import com.matellio.store.repository.ProductSubCategoryRepository;
import com.matellio.store.service.ProductSubCategoryService;
import com.matellio.store.service.dto.ProductSubCategoryDTO;
import com.matellio.store.service.mapper.ProductSubCategoryMapper;

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
 * Integration tests for the {@link ProductSubCategoryResource} REST controller.
 */
@SpringBootTest(classes = StoreApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProductSubCategoryResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    @Autowired
    private ProductSubCategoryRepository productSubCategoryRepository;

    @Autowired
    private ProductSubCategoryMapper productSubCategoryMapper;

    @Autowired
    private ProductSubCategoryService productSubCategoryService;

    @Autowired
    private MockMvc restProductSubCategoryMockMvc;

    private ProductSubCategory productSubCategory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductSubCategory createEntity() {
        ProductSubCategory productSubCategory = new ProductSubCategory()
            .name(DEFAULT_NAME)
            .isActive(DEFAULT_IS_ACTIVE);
        return productSubCategory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductSubCategory createUpdatedEntity() {
        ProductSubCategory productSubCategory = new ProductSubCategory()
            .name(UPDATED_NAME)
            .isActive(UPDATED_IS_ACTIVE);
        return productSubCategory;
    }

    @BeforeEach
    public void initTest() {
        productSubCategoryRepository.deleteAll();
        productSubCategory = createEntity();
    }

    @Test
    public void createProductSubCategory() throws Exception {
        int databaseSizeBeforeCreate = productSubCategoryRepository.findAll().size();
        // Create the ProductSubCategory
        ProductSubCategoryDTO productSubCategoryDTO = productSubCategoryMapper.toDto(productSubCategory);
        restProductSubCategoryMockMvc.perform(post("/api/product-sub-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSubCategoryDTO)))
            .andExpect(status().isCreated());

        // Validate the ProductSubCategory in the database
        List<ProductSubCategory> productSubCategoryList = productSubCategoryRepository.findAll();
        assertThat(productSubCategoryList).hasSize(databaseSizeBeforeCreate + 1);
        ProductSubCategory testProductSubCategory = productSubCategoryList.get(productSubCategoryList.size() - 1);
        assertThat(testProductSubCategory.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testProductSubCategory.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
    }

    @Test
    public void createProductSubCategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productSubCategoryRepository.findAll().size();

        // Create the ProductSubCategory with an existing ID
        productSubCategory.setId("existing_id");
        ProductSubCategoryDTO productSubCategoryDTO = productSubCategoryMapper.toDto(productSubCategory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductSubCategoryMockMvc.perform(post("/api/product-sub-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSubCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProductSubCategory in the database
        List<ProductSubCategory> productSubCategoryList = productSubCategoryRepository.findAll();
        assertThat(productSubCategoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllProductSubCategories() throws Exception {
        // Initialize the database
        productSubCategoryRepository.save(productSubCategory);

        // Get all the productSubCategoryList
        restProductSubCategoryMockMvc.perform(get("/api/product-sub-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productSubCategory.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())));
    }
    
    @Test
    public void getProductSubCategory() throws Exception {
        // Initialize the database
        productSubCategoryRepository.save(productSubCategory);

        // Get the productSubCategory
        restProductSubCategoryMockMvc.perform(get("/api/product-sub-categories/{id}", productSubCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(productSubCategory.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()));
    }
    @Test
    public void getNonExistingProductSubCategory() throws Exception {
        // Get the productSubCategory
        restProductSubCategoryMockMvc.perform(get("/api/product-sub-categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProductSubCategory() throws Exception {
        // Initialize the database
        productSubCategoryRepository.save(productSubCategory);

        int databaseSizeBeforeUpdate = productSubCategoryRepository.findAll().size();

        // Update the productSubCategory
        ProductSubCategory updatedProductSubCategory = productSubCategoryRepository.findById(productSubCategory.getId()).get();
        updatedProductSubCategory
            .name(UPDATED_NAME)
            .isActive(UPDATED_IS_ACTIVE);
        ProductSubCategoryDTO productSubCategoryDTO = productSubCategoryMapper.toDto(updatedProductSubCategory);

        restProductSubCategoryMockMvc.perform(put("/api/product-sub-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSubCategoryDTO)))
            .andExpect(status().isOk());

        // Validate the ProductSubCategory in the database
        List<ProductSubCategory> productSubCategoryList = productSubCategoryRepository.findAll();
        assertThat(productSubCategoryList).hasSize(databaseSizeBeforeUpdate);
        ProductSubCategory testProductSubCategory = productSubCategoryList.get(productSubCategoryList.size() - 1);
        assertThat(testProductSubCategory.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testProductSubCategory.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
    }

    @Test
    public void updateNonExistingProductSubCategory() throws Exception {
        int databaseSizeBeforeUpdate = productSubCategoryRepository.findAll().size();

        // Create the ProductSubCategory
        ProductSubCategoryDTO productSubCategoryDTO = productSubCategoryMapper.toDto(productSubCategory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductSubCategoryMockMvc.perform(put("/api/product-sub-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSubCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProductSubCategory in the database
        List<ProductSubCategory> productSubCategoryList = productSubCategoryRepository.findAll();
        assertThat(productSubCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteProductSubCategory() throws Exception {
        // Initialize the database
        productSubCategoryRepository.save(productSubCategory);

        int databaseSizeBeforeDelete = productSubCategoryRepository.findAll().size();

        // Delete the productSubCategory
        restProductSubCategoryMockMvc.perform(delete("/api/product-sub-categories/{id}", productSubCategory.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductSubCategory> productSubCategoryList = productSubCategoryRepository.findAll();
        assertThat(productSubCategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
