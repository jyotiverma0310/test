package com.matellio.store.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.matellio.store.web.rest.TestUtil;

public class ProductSubCategoryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductSubCategory.class);
        ProductSubCategory productSubCategory1 = new ProductSubCategory();
        productSubCategory1.setId("id1");
        ProductSubCategory productSubCategory2 = new ProductSubCategory();
        productSubCategory2.setId(productSubCategory1.getId());
        assertThat(productSubCategory1).isEqualTo(productSubCategory2);
        productSubCategory2.setId("id2");
        assertThat(productSubCategory1).isNotEqualTo(productSubCategory2);
        productSubCategory1.setId(null);
        assertThat(productSubCategory1).isNotEqualTo(productSubCategory2);
    }
}
