package com.matellio.store.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.matellio.store.web.rest.TestUtil;

public class AboutUsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AboutUsDTO.class);
        AboutUsDTO aboutUsDTO1 = new AboutUsDTO();
        aboutUsDTO1.setId("id1");
        AboutUsDTO aboutUsDTO2 = new AboutUsDTO();
        assertThat(aboutUsDTO1).isNotEqualTo(aboutUsDTO2);
        aboutUsDTO2.setId(aboutUsDTO1.getId());
        assertThat(aboutUsDTO1).isEqualTo(aboutUsDTO2);
        aboutUsDTO2.setId("id2");
        assertThat(aboutUsDTO1).isNotEqualTo(aboutUsDTO2);
        aboutUsDTO1.setId(null);
        assertThat(aboutUsDTO1).isNotEqualTo(aboutUsDTO2);
    }
}
