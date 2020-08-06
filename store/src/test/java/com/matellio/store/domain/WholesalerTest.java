package com.matellio.store.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.matellio.store.web.rest.TestUtil;

public class WholesalerTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Wholesaler.class);
        Wholesaler wholesaler1 = new Wholesaler();
        wholesaler1.setId("id1");
        Wholesaler wholesaler2 = new Wholesaler();
        wholesaler2.setId(wholesaler1.getId());
        assertThat(wholesaler1).isEqualTo(wholesaler2);
        wholesaler2.setId("id2");
        assertThat(wholesaler1).isNotEqualTo(wholesaler2);
        wholesaler1.setId(null);
        assertThat(wholesaler1).isNotEqualTo(wholesaler2);
    }
}
