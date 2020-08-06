package com.matellio.store.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.matellio.store.web.rest.TestUtil;

public class AddOnProductDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AddOnProductDTO.class);
        AddOnProductDTO addOnProductDTO1 = new AddOnProductDTO();
        addOnProductDTO1.setId("id1");
        AddOnProductDTO addOnProductDTO2 = new AddOnProductDTO();
        assertThat(addOnProductDTO1).isNotEqualTo(addOnProductDTO2);
        addOnProductDTO2.setId(addOnProductDTO1.getId());
        assertThat(addOnProductDTO1).isEqualTo(addOnProductDTO2);
        addOnProductDTO2.setId("id2");
        assertThat(addOnProductDTO1).isNotEqualTo(addOnProductDTO2);
        addOnProductDTO1.setId(null);
        assertThat(addOnProductDTO1).isNotEqualTo(addOnProductDTO2);
    }
}
