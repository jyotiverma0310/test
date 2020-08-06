package com.matellio.store.web.rest;

import com.matellio.store.StoreApp;
import com.matellio.store.domain.Wallet;
import com.matellio.store.repository.WalletRepository;
import com.matellio.store.service.WalletService;
import com.matellio.store.service.dto.WalletDTO;
import com.matellio.store.service.mapper.WalletMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.matellio.store.domain.enumeration.WalletOperationType;
/**
 * Integration tests for the {@link WalletResource} REST controller.
 */
@SpringBootTest(classes = StoreApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class WalletResourceIT {

    private static final String DEFAULT_ORDER_ID = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_ID = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_CURRENT_TOTAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_CURRENT_TOTAL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CURRENT_REEDEM = new BigDecimal(1);
    private static final BigDecimal UPDATED_CURRENT_REEDEM = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    private static final WalletOperationType DEFAULT_OPERATION = WalletOperationType.Add;
    private static final WalletOperationType UPDATED_OPERATION = WalletOperationType.Subtract;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private WalletMapper walletMapper;

    @Autowired
    private WalletService walletService;

    @Autowired
    private MockMvc restWalletMockMvc;

    private Wallet wallet;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Wallet createEntity() {
        Wallet wallet = new Wallet()
            .orderId(DEFAULT_ORDER_ID)
            .currentTotal(DEFAULT_CURRENT_TOTAL)
            .currentReedem(DEFAULT_CURRENT_REEDEM)
            .amount(DEFAULT_AMOUNT)
            .operation(DEFAULT_OPERATION);
        return wallet;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Wallet createUpdatedEntity() {
        Wallet wallet = new Wallet()
            .orderId(UPDATED_ORDER_ID)
            .currentTotal(UPDATED_CURRENT_TOTAL)
            .currentReedem(UPDATED_CURRENT_REEDEM)
            .amount(UPDATED_AMOUNT)
            .operation(UPDATED_OPERATION);
        return wallet;
    }

    @BeforeEach
    public void initTest() {
        walletRepository.deleteAll();
        wallet = createEntity();
    }

    @Test
    public void createWallet() throws Exception {
        int databaseSizeBeforeCreate = walletRepository.findAll().size();
        // Create the Wallet
        WalletDTO walletDTO = walletMapper.toDto(wallet);
        restWalletMockMvc.perform(post("/api/wallets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(walletDTO)))
            .andExpect(status().isCreated());

        // Validate the Wallet in the database
        List<Wallet> walletList = walletRepository.findAll();
        assertThat(walletList).hasSize(databaseSizeBeforeCreate + 1);
        Wallet testWallet = walletList.get(walletList.size() - 1);
        assertThat(testWallet.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testWallet.getCurrentTotal()).isEqualTo(DEFAULT_CURRENT_TOTAL);
        assertThat(testWallet.getCurrentReedem()).isEqualTo(DEFAULT_CURRENT_REEDEM);
        assertThat(testWallet.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testWallet.getOperation()).isEqualTo(DEFAULT_OPERATION);
    }

    @Test
    public void createWalletWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = walletRepository.findAll().size();

        // Create the Wallet with an existing ID
        wallet.setId("existing_id");
        WalletDTO walletDTO = walletMapper.toDto(wallet);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWalletMockMvc.perform(post("/api/wallets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(walletDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Wallet in the database
        List<Wallet> walletList = walletRepository.findAll();
        assertThat(walletList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllWallets() throws Exception {
        // Initialize the database
        walletRepository.save(wallet);

        // Get all the walletList
        restWalletMockMvc.perform(get("/api/wallets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(wallet.getId())))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID)))
            .andExpect(jsonPath("$.[*].currentTotal").value(hasItem(DEFAULT_CURRENT_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].currentReedem").value(hasItem(DEFAULT_CURRENT_REEDEM.intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].operation").value(hasItem(DEFAULT_OPERATION.toString())));
    }
    
    @Test
    public void getWallet() throws Exception {
        // Initialize the database
        walletRepository.save(wallet);

        // Get the wallet
        restWalletMockMvc.perform(get("/api/wallets/{id}", wallet.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(wallet.getId()))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID))
            .andExpect(jsonPath("$.currentTotal").value(DEFAULT_CURRENT_TOTAL.intValue()))
            .andExpect(jsonPath("$.currentReedem").value(DEFAULT_CURRENT_REEDEM.intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.operation").value(DEFAULT_OPERATION.toString()));
    }
    @Test
    public void getNonExistingWallet() throws Exception {
        // Get the wallet
        restWalletMockMvc.perform(get("/api/wallets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateWallet() throws Exception {
        // Initialize the database
        walletRepository.save(wallet);

        int databaseSizeBeforeUpdate = walletRepository.findAll().size();

        // Update the wallet
        Wallet updatedWallet = walletRepository.findById(wallet.getId()).get();
        updatedWallet
            .orderId(UPDATED_ORDER_ID)
            .currentTotal(UPDATED_CURRENT_TOTAL)
            .currentReedem(UPDATED_CURRENT_REEDEM)
            .amount(UPDATED_AMOUNT)
            .operation(UPDATED_OPERATION);
        WalletDTO walletDTO = walletMapper.toDto(updatedWallet);

        restWalletMockMvc.perform(put("/api/wallets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(walletDTO)))
            .andExpect(status().isOk());

        // Validate the Wallet in the database
        List<Wallet> walletList = walletRepository.findAll();
        assertThat(walletList).hasSize(databaseSizeBeforeUpdate);
        Wallet testWallet = walletList.get(walletList.size() - 1);
        assertThat(testWallet.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testWallet.getCurrentTotal()).isEqualTo(UPDATED_CURRENT_TOTAL);
        assertThat(testWallet.getCurrentReedem()).isEqualTo(UPDATED_CURRENT_REEDEM);
        assertThat(testWallet.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testWallet.getOperation()).isEqualTo(UPDATED_OPERATION);
    }

    @Test
    public void updateNonExistingWallet() throws Exception {
        int databaseSizeBeforeUpdate = walletRepository.findAll().size();

        // Create the Wallet
        WalletDTO walletDTO = walletMapper.toDto(wallet);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWalletMockMvc.perform(put("/api/wallets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(walletDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Wallet in the database
        List<Wallet> walletList = walletRepository.findAll();
        assertThat(walletList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteWallet() throws Exception {
        // Initialize the database
        walletRepository.save(wallet);

        int databaseSizeBeforeDelete = walletRepository.findAll().size();

        // Delete the wallet
        restWalletMockMvc.perform(delete("/api/wallets/{id}", wallet.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Wallet> walletList = walletRepository.findAll();
        assertThat(walletList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
