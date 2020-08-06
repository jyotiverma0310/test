package com.matellio.store.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DistributionStoreMapperTest {

    private DistributionStoreMapper distributionStoreMapper;

    @BeforeEach
    public void setUp() {
        distributionStoreMapper = new DistributionStoreMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(distributionStoreMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(distributionStoreMapper.fromId(null)).isNull();
    }
}
