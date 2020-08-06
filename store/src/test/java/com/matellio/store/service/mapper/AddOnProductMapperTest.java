package com.matellio.store.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AddOnProductMapperTest {

    private AddOnProductMapper addOnProductMapper;

    @BeforeEach
    public void setUp() {
        addOnProductMapper = new AddOnProductMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(addOnProductMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(addOnProductMapper.fromId(null)).isNull();
    }
}
