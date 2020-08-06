package com.matellio.store.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.matellio.store.web.rest.TestUtil;

public class TermsAndConditionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TermsAndConditionDTO.class);
        TermsAndConditionDTO termsAndConditionDTO1 = new TermsAndConditionDTO();
        termsAndConditionDTO1.setId("id1");
        TermsAndConditionDTO termsAndConditionDTO2 = new TermsAndConditionDTO();
        assertThat(termsAndConditionDTO1).isNotEqualTo(termsAndConditionDTO2);
        termsAndConditionDTO2.setId(termsAndConditionDTO1.getId());
        assertThat(termsAndConditionDTO1).isEqualTo(termsAndConditionDTO2);
        termsAndConditionDTO2.setId("id2");
        assertThat(termsAndConditionDTO1).isNotEqualTo(termsAndConditionDTO2);
        termsAndConditionDTO1.setId(null);
        assertThat(termsAndConditionDTO1).isNotEqualTo(termsAndConditionDTO2);
    }
}
