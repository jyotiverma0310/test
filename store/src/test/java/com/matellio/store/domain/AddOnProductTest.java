package com.matellio.store.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.matellio.store.web.rest.TestUtil;

public class AddOnProductTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AddOnProduct.class);
        AddOnProduct addOnProduct1 = new AddOnProduct();
        addOnProduct1.setId("id1");
        AddOnProduct addOnProduct2 = new AddOnProduct();
        addOnProduct2.setId(addOnProduct1.getId());
        assertThat(addOnProduct1).isEqualTo(addOnProduct2);
        addOnProduct2.setId("id2");
        assertThat(addOnProduct1).isNotEqualTo(addOnProduct2);
        addOnProduct1.setId(null);
        assertThat(addOnProduct1).isNotEqualTo(addOnProduct2);
    }
}
