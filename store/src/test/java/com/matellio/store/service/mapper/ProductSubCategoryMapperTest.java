package com.matellio.store.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductSubCategoryMapperTest {

    private ProductSubCategoryMapper productSubCategoryMapper;

    @BeforeEach
    public void setUp() {
        productSubCategoryMapper = new ProductSubCategoryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(productSubCategoryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(productSubCategoryMapper.fromId(null)).isNull();
    }
}
