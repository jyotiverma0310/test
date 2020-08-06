package com.matellio.store.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WalletMapperTest {

    private WalletMapper walletMapper;

    @BeforeEach
    public void setUp() {
        walletMapper = new WalletMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(walletMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(walletMapper.fromId(null)).isNull();
    }
}
