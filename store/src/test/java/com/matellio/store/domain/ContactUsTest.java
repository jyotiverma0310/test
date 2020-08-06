package com.matellio.store.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.matellio.store.web.rest.TestUtil;

public class ContactUsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContactUs.class);
        ContactUs contactUs1 = new ContactUs();
        contactUs1.setId("id1");
        ContactUs contactUs2 = new ContactUs();
        contactUs2.setId(contactUs1.getId());
        assertThat(contactUs1).isEqualTo(contactUs2);
        contactUs2.setId("id2");
        assertThat(contactUs1).isNotEqualTo(contactUs2);
        contactUs1.setId(null);
        assertThat(contactUs1).isNotEqualTo(contactUs2);
    }
}
