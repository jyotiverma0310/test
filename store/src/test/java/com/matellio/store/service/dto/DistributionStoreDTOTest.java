package com.matellio.store.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.matellio.store.web.rest.TestUtil;

public class DistributionStoreDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DistributionStoreDTO.class);
        DistributionStoreDTO distributionStoreDTO1 = new DistributionStoreDTO();
        distributionStoreDTO1.setId("id1");
        DistributionStoreDTO distributionStoreDTO2 = new DistributionStoreDTO();
        assertThat(distributionStoreDTO1).isNotEqualTo(distributionStoreDTO2);
        distributionStoreDTO2.setId(distributionStoreDTO1.getId());
        assertThat(distributionStoreDTO1).isEqualTo(distributionStoreDTO2);
        distributionStoreDTO2.setId("id2");
        assertThat(distributionStoreDTO1).isNotEqualTo(distributionStoreDTO2);
        distributionStoreDTO1.setId(null);
        assertThat(distributionStoreDTO1).isNotEqualTo(distributionStoreDTO2);
    }
}
