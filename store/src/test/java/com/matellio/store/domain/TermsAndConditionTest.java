package com.matellio.store.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.matellio.store.web.rest.TestUtil;

public class TermsAndConditionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TermsAndCondition.class);
        TermsAndCondition termsAndCondition1 = new TermsAndCondition();
        termsAndCondition1.setId("id1");
        TermsAndCondition termsAndCondition2 = new TermsAndCondition();
        termsAndCondition2.setId(termsAndCondition1.getId());
        assertThat(termsAndCondition1).isEqualTo(termsAndCondition2);
        termsAndCondition2.setId("id2");
        assertThat(termsAndCondition1).isNotEqualTo(termsAndCondition2);
        termsAndCondition1.setId(null);
        assertThat(termsAndCondition1).isNotEqualTo(termsAndCondition2);
    }
}
