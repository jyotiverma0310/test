package com.matellio.store.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.matellio.store.web.rest.TestUtil;

public class ContactUsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContactUsDTO.class);
        ContactUsDTO contactUsDTO1 = new ContactUsDTO();
        contactUsDTO1.setId("id1");
        ContactUsDTO contactUsDTO2 = new ContactUsDTO();
        assertThat(contactUsDTO1).isNotEqualTo(contactUsDTO2);
        contactUsDTO2.setId(contactUsDTO1.getId());
        assertThat(contactUsDTO1).isEqualTo(contactUsDTO2);
        contactUsDTO2.setId("id2");
        assertThat(contactUsDTO1).isNotEqualTo(contactUsDTO2);
        contactUsDTO1.setId(null);
        assertThat(contactUsDTO1).isNotEqualTo(contactUsDTO2);
    }
}
