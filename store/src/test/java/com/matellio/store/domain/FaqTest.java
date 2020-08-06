package com.matellio.store.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.matellio.store.web.rest.TestUtil;

public class FaqTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Faq.class);
        Faq faq1 = new Faq();
        faq1.setId("id1");
        Faq faq2 = new Faq();
        faq2.setId(faq1.getId());
        assertThat(faq1).isEqualTo(faq2);
        faq2.setId("id2");
        assertThat(faq1).isNotEqualTo(faq2);
        faq1.setId(null);
        assertThat(faq1).isNotEqualTo(faq2);
    }
}
