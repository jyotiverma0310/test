package com.matellio.store.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WholesalerMapperTest {

    private WholesalerMapper wholesalerMapper;

    @BeforeEach
    public void setUp() {
        wholesalerMapper = new WholesalerMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(wholesalerMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(wholesalerMapper.fromId(null)).isNull();
    }
}
