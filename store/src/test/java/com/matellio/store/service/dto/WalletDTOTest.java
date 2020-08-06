package com.matellio.store.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.matellio.store.web.rest.TestUtil;

public class WalletDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WalletDTO.class);
        WalletDTO walletDTO1 = new WalletDTO();
        walletDTO1.setId("id1");
        WalletDTO walletDTO2 = new WalletDTO();
        assertThat(walletDTO1).isNotEqualTo(walletDTO2);
        walletDTO2.setId(walletDTO1.getId());
        assertThat(walletDTO1).isEqualTo(walletDTO2);
        walletDTO2.setId("id2");
        assertThat(walletDTO1).isNotEqualTo(walletDTO2);
        walletDTO1.setId(null);
        assertThat(walletDTO1).isNotEqualTo(walletDTO2);
    }
}
