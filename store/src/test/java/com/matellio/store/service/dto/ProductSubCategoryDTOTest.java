package com.matellio.store.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.matellio.store.web.rest.TestUtil;

public class ProductSubCategoryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductSubCategoryDTO.class);
        ProductSubCategoryDTO productSubCategoryDTO1 = new ProductSubCategoryDTO();
        productSubCategoryDTO1.setId("id1");
        ProductSubCategoryDTO productSubCategoryDTO2 = new ProductSubCategoryDTO();
        assertThat(productSubCategoryDTO1).isNotEqualTo(productSubCategoryDTO2);
        productSubCategoryDTO2.setId(productSubCategoryDTO1.getId());
        assertThat(productSubCategoryDTO1).isEqualTo(productSubCategoryDTO2);
        productSubCategoryDTO2.setId("id2");
        assertThat(productSubCategoryDTO1).isNotEqualTo(productSubCategoryDTO2);
        productSubCategoryDTO1.setId(null);
        assertThat(productSubCategoryDTO1).isNotEqualTo(productSubCategoryDTO2);
    }
}
