package com.matellio.store.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.matellio.store.web.rest.TestUtil;

public class DistributionStoreTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DistributionStore.class);
        DistributionStore distributionStore1 = new DistributionStore();
        distributionStore1.setId("id1");
        DistributionStore distributionStore2 = new DistributionStore();
        distributionStore2.setId(distributionStore1.getId());
        assertThat(distributionStore1).isEqualTo(distributionStore2);
        distributionStore2.setId("id2");
        assertThat(distributionStore1).isNotEqualTo(distributionStore2);
        distributionStore1.setId(null);
        assertThat(distributionStore1).isNotEqualTo(distributionStore2);
    }
}
