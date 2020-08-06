package com.matellio.store.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TermsAndConditionMapperTest {

    private TermsAndConditionMapper termsAndConditionMapper;

    @BeforeEach
    public void setUp() {
        termsAndConditionMapper = new TermsAndConditionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(termsAndConditionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(termsAndConditionMapper.fromId(null)).isNull();
    }
}
