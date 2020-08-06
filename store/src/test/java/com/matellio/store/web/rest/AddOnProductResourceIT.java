package com.matellio.store.web.rest;

import com.matellio.store.StoreApp;
import com.matellio.store.domain.AddOnProduct;
import com.matellio.store.repository.AddOnProductRepository;
import com.matellio.store.service.AddOnProductService;
import com.matellio.store.service.dto.AddOnProductDTO;
import com.matellio.store.service.mapper.AddOnProductMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AddOnProductResource} REST controller.
 */
@SpringBootTest(classes = StoreApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AddOnProductResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final BigDecimal DEFAULT_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRICE = new BigDecimal(2);

    private static final String DEFAULT_PRODUCT_CATEGORY_ID = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_CATEGORY_ID = "BBBBBBBBBB";

    @Autowired
    private AddOnProductRepository addOnProductRepository;

    @Autowired
    private AddOnProductMapper addOnProductMapper;

    @Autowired
    private AddOnProductService addOnProductService;

    @Autowired
    private MockMvc restAddOnProductMockMvc;

    private AddOnProduct addOnProduct;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AddOnProduct createEntity() {
        AddOnProduct addOnProduct = new AddOnProduct()
            .name(DEFAULT_NAME)
            .isActive(DEFAULT_IS_ACTIVE)
            .price(DEFAULT_PRICE)
            .productCategoryId(DEFAULT_PRODUCT_CATEGORY_ID);
        return addOnProduct;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AddOnProduct createUpdatedEntity() {
        AddOnProduct addOnProduct = new AddOnProduct()
            .name(UPDATED_NAME)
            .isActive(UPDATED_IS_ACTIVE)
            .price(UPDATED_PRICE)
            .productCategoryId(UPDATED_PRODUCT_CATEGORY_ID);
        return addOnProduct;
    }

    @BeforeEach
    public void initTest() {
        addOnProductRepository.deleteAll();
        addOnProduct = createEntity();
    }

    @Test
    public void createAddOnProduct() throws Exception {
        int databaseSizeBeforeCreate = addOnProductRepository.findAll().size();
        // Create the AddOnProduct
        AddOnProductDTO addOnProductDTO = addOnProductMapper.toDto(addOnProduct);
        restAddOnProductMockMvc.perform(post("/api/add-on-products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(addOnProductDTO)))
            .andExpect(status().isCreated());

        // Validate the AddOnProduct in the database
        List<AddOnProduct> addOnProductList = addOnProductRepository.findAll();
        assertThat(addOnProductList).hasSize(databaseSizeBeforeCreate + 1);
        AddOnProduct testAddOnProduct = addOnProductList.get(addOnProductList.size() - 1);
        assertThat(testAddOnProduct.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAddOnProduct.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testAddOnProduct.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testAddOnProduct.getProductCategoryId()).isEqualTo(DEFAULT_PRODUCT_CATEGORY_ID);
    }

    @Test
    public void createAddOnProductWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = addOnProductRepository.findAll().size();

        // Create the AddOnProduct with an existing ID
        addOnProduct.setId("existing_id");
        AddOnProductDTO addOnProductDTO = addOnProductMapper.toDto(addOnProduct);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAddOnProductMockMvc.perform(post("/api/add-on-products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(addOnProductDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AddOnProduct in the database
        List<AddOnProduct> addOnProductList = addOnProductRepository.findAll();
        assertThat(addOnProductList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllAddOnProducts() throws Exception {
        // Initialize the database
        addOnProductRepository.save(addOnProduct);

        // Get all the addOnProductList
        restAddOnProductMockMvc.perform(get("/api/add-on-products?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(addOnProduct.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].productCategoryId").value(hasItem(DEFAULT_PRODUCT_CATEGORY_ID)));
    }
    
    @Test
    public void getAddOnProduct() throws Exception {
        // Initialize the database
        addOnProductRepository.save(addOnProduct);

        // Get the addOnProduct
        restAddOnProductMockMvc.perform(get("/api/add-on-products/{id}", addOnProduct.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(addOnProduct.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.intValue()))
            .andExpect(jsonPath("$.productCategoryId").value(DEFAULT_PRODUCT_CATEGORY_ID));
    }
    @Test
    public void getNonExistingAddOnProduct() throws Exception {
        // Get the addOnProduct
        restAddOnProductMockMvc.perform(get("/api/add-on-products/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAddOnProduct() throws Exception {
        // Initialize the database
        addOnProductRepository.save(addOnProduct);

        int databaseSizeBeforeUpdate = addOnProductRepository.findAll().size();

        // Update the addOnProduct
        AddOnProduct updatedAddOnProduct = addOnProductRepository.findById(addOnProduct.getId()).get();
        updatedAddOnProduct
            .name(UPDATED_NAME)
            .isActive(UPDATED_IS_ACTIVE)
            .price(UPDATED_PRICE)
            .productCategoryId(UPDATED_PRODUCT_CATEGORY_ID);
        AddOnProductDTO addOnProductDTO = addOnProductMapper.toDto(updatedAddOnProduct);

        restAddOnProductMockMvc.perform(put("/api/add-on-products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(addOnProductDTO)))
            .andExpect(status().isOk());

        // Validate the AddOnProduct in the database
        List<AddOnProduct> addOnProductList = addOnProductRepository.findAll();
        assertThat(addOnProductList).hasSize(databaseSizeBeforeUpdate);
        AddOnProduct testAddOnProduct = addOnProductList.get(addOnProductList.size() - 1);
        assertThat(testAddOnProduct.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAddOnProduct.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testAddOnProduct.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testAddOnProduct.getProductCategoryId()).isEqualTo(UPDATED_PRODUCT_CATEGORY_ID);
    }

    @Test
    public void updateNonExistingAddOnProduct() throws Exception {
        int databaseSizeBeforeUpdate = addOnProductRepository.findAll().size();

        // Create the AddOnProduct
        AddOnProductDTO addOnProductDTO = addOnProductMapper.toDto(addOnProduct);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAddOnProductMockMvc.perform(put("/api/add-on-products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(addOnProductDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AddOnProduct in the database
        List<AddOnProduct> addOnProductList = addOnProductRepository.findAll();
        assertThat(addOnProductList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteAddOnProduct() throws Exception {
        // Initialize the database
        addOnProductRepository.save(addOnProduct);

        int databaseSizeBeforeDelete = addOnProductRepository.findAll().size();

        // Delete the addOnProduct
        restAddOnProductMockMvc.perform(delete("/api/add-on-products/{id}", addOnProduct.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AddOnProduct> addOnProductList = addOnProductRepository.findAll();
        assertThat(addOnProductList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
