package com.matellio.store.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.matellio.store.web.rest.TestUtil;

public class WholesalerDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WholesalerDTO.class);
        WholesalerDTO wholesalerDTO1 = new WholesalerDTO();
        wholesalerDTO1.setId("id1");
        WholesalerDTO wholesalerDTO2 = new WholesalerDTO();
        assertThat(wholesalerDTO1).isNotEqualTo(wholesalerDTO2);
        wholesalerDTO2.setId(wholesalerDTO1.getId());
        assertThat(wholesalerDTO1).isEqualTo(wholesalerDTO2);
        wholesalerDTO2.setId("id2");
        assertThat(wholesalerDTO1).isNotEqualTo(wholesalerDTO2);
        wholesalerDTO1.setId(null);
        assertThat(wholesalerDTO1).isNotEqualTo(wholesalerDTO2);
    }
}
