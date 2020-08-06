package com.matellio.store.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.matellio.store.web.rest.TestUtil;

public class AboutUsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AboutUs.class);
        AboutUs aboutUs1 = new AboutUs();
        aboutUs1.setId("id1");
        AboutUs aboutUs2 = new AboutUs();
        aboutUs2.setId(aboutUs1.getId());
        assertThat(aboutUs1).isEqualTo(aboutUs2);
        aboutUs2.setId("id2");
        assertThat(aboutUs1).isNotEqualTo(aboutUs2);
        aboutUs1.setId(null);
        assertThat(aboutUs1).isNotEqualTo(aboutUs2);
    }
}
