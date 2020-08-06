package com.matellio.store.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.matellio.store.web.rest.TestUtil;

public class TransactionsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Transactions.class);
        Transactions transactions1 = new Transactions();
        transactions1.setId("id1");
        Transactions transactions2 = new Transactions();
        transactions2.setId(transactions1.getId());
        assertThat(transactions1).isEqualTo(transactions2);
        transactions2.setId("id2");
        assertThat(transactions1).isNotEqualTo(transactions2);
        transactions1.setId(null);
        assertThat(transactions1).isNotEqualTo(transactions2);
    }
}
