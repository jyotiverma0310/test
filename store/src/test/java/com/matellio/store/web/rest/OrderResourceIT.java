package com.matellio.store.web.rest;

import com.matellio.store.StoreApp;
import com.matellio.store.domain.Order;
import com.matellio.store.repository.OrderRepository;
import com.matellio.store.service.OrderService;
import com.matellio.store.service.dto.OrderDTO;
import com.matellio.store.service.mapper.OrderMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.matellio.store.domain.enumeration.OrderStatus;
import com.matellio.store.domain.enumeration.OrderType;
/**
 * Integration tests for the {@link OrderResource} REST controller.
 */
@SpringBootTest(classes = StoreApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class OrderResourceIT {

    private static final String DEFAULT_ORDER_NO = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_NO = "BBBBBBBBBB";

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_EMPLOYEE_ID = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_EMPLOYEE_ID = "BBBBBBBBBB";

    private static final OrderStatus DEFAULT_STATUS = OrderStatus.Shipped;
    private static final OrderStatus UPDATED_STATUS = OrderStatus.Delivered;

    private static final OrderType DEFAULT_ORDER_TYPE = OrderType.PickUp;
    private static final OrderType UPDATED_ORDER_TYPE = OrderType.Delivery;

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final String DEFAULT_SHIPPING_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_ADDRESS = "BBBBBBBBBB";

    private static final Instant DEFAULT_PAID_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PAID_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_COMPLETE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_COMPLETE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_SHIPPMENT_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SHIPPMENT_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DELIVERY_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DELIVERY_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_CANCEL_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CANCEL_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DISPATCH_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DISPATCH_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_REJECT_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_REJECT_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_ACCEPT_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ACCEPT_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_NOTE = "BBBBBBBBBB";

    private static final String DEFAULT_REJECT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_REJECT_NOTE = "BBBBBBBBBB";

    private static final String DEFAULT_CANCEL_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_CANCEL_NOTE = "BBBBBBBBBB";

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MockMvc restOrderMockMvc;

    private Order order;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Order createEntity() {
        Order order = new Order()
            .orderNo(DEFAULT_ORDER_NO)
            .userId(DEFAULT_USER_ID)
            .deliveryEmployeeId(DEFAULT_DELIVERY_EMPLOYEE_ID)
            .status(DEFAULT_STATUS)
            .orderType(DEFAULT_ORDER_TYPE)
            .isActive(DEFAULT_IS_ACTIVE)
            .shippingAddress(DEFAULT_SHIPPING_ADDRESS)
            .paidTime(DEFAULT_PAID_TIME)
            .completeTime(DEFAULT_COMPLETE_TIME)
            .shippmentTime(DEFAULT_SHIPPMENT_TIME)
            .deliveryTime(DEFAULT_DELIVERY_TIME)
            .cancelTime(DEFAULT_CANCEL_TIME)
            .dispatchTime(DEFAULT_DISPATCH_TIME)
            .rejectTime(DEFAULT_REJECT_TIME)
            .acceptTime(DEFAULT_ACCEPT_TIME)
            .note(DEFAULT_NOTE)
            .rejectNote(DEFAULT_REJECT_NOTE)
            .cancelNote(DEFAULT_CANCEL_NOTE);
        return order;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Order createUpdatedEntity() {
        Order order = new Order()
            .orderNo(UPDATED_ORDER_NO)
            .userId(UPDATED_USER_ID)
            .deliveryEmployeeId(UPDATED_DELIVERY_EMPLOYEE_ID)
            .status(UPDATED_STATUS)
            .orderType(UPDATED_ORDER_TYPE)
            .isActive(UPDATED_IS_ACTIVE)
            .shippingAddress(UPDATED_SHIPPING_ADDRESS)
            .paidTime(UPDATED_PAID_TIME)
            .completeTime(UPDATED_COMPLETE_TIME)
            .shippmentTime(UPDATED_SHIPPMENT_TIME)
            .deliveryTime(UPDATED_DELIVERY_TIME)
            .cancelTime(UPDATED_CANCEL_TIME)
            .dispatchTime(UPDATED_DISPATCH_TIME)
            .rejectTime(UPDATED_REJECT_TIME)
            .acceptTime(UPDATED_ACCEPT_TIME)
            .note(UPDATED_NOTE)
            .rejectNote(UPDATED_REJECT_NOTE)
            .cancelNote(UPDATED_CANCEL_NOTE);
        return order;
    }

    @BeforeEach
    public void initTest() {
        orderRepository.deleteAll();
        order = createEntity();
    }

    @Test
    public void createOrder() throws Exception {
        int databaseSizeBeforeCreate = orderRepository.findAll().size();
        // Create the Order
        OrderDTO orderDTO = orderMapper.toDto(order);
        restOrderMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isCreated());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeCreate + 1);
        Order testOrder = orderList.get(orderList.size() - 1);
        assertThat(testOrder.getOrderNo()).isEqualTo(DEFAULT_ORDER_NO);
        assertThat(testOrder.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testOrder.getDeliveryEmployeeId()).isEqualTo(DEFAULT_DELIVERY_EMPLOYEE_ID);
        assertThat(testOrder.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testOrder.getOrderType()).isEqualTo(DEFAULT_ORDER_TYPE);
        assertThat(testOrder.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testOrder.getShippingAddress()).isEqualTo(DEFAULT_SHIPPING_ADDRESS);
        assertThat(testOrder.getPaidTime()).isEqualTo(DEFAULT_PAID_TIME);
        assertThat(testOrder.getCompleteTime()).isEqualTo(DEFAULT_COMPLETE_TIME);
        assertThat(testOrder.getShippmentTime()).isEqualTo(DEFAULT_SHIPPMENT_TIME);
        assertThat(testOrder.getDeliveryTime()).isEqualTo(DEFAULT_DELIVERY_TIME);
        assertThat(testOrder.getCancelTime()).isEqualTo(DEFAULT_CANCEL_TIME);
        assertThat(testOrder.getDispatchTime()).isEqualTo(DEFAULT_DISPATCH_TIME);
        assertThat(testOrder.getRejectTime()).isEqualTo(DEFAULT_REJECT_TIME);
        assertThat(testOrder.getAcceptTime()).isEqualTo(DEFAULT_ACCEPT_TIME);
        assertThat(testOrder.getNote()).isEqualTo(DEFAULT_NOTE);
        assertThat(testOrder.getRejectNote()).isEqualTo(DEFAULT_REJECT_NOTE);
        assertThat(testOrder.getCancelNote()).isEqualTo(DEFAULT_CANCEL_NOTE);
    }

    @Test
    public void createOrderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderRepository.findAll().size();

        // Create the Order with an existing ID
        order.setId("existing_id");
        OrderDTO orderDTO = orderMapper.toDto(order);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllOrders() throws Exception {
        // Initialize the database
        orderRepository.save(order);

        // Get all the orderList
        restOrderMockMvc.perform(get("/api/orders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(order.getId())))
            .andExpect(jsonPath("$.[*].orderNo").value(hasItem(DEFAULT_ORDER_NO)))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].deliveryEmployeeId").value(hasItem(DEFAULT_DELIVERY_EMPLOYEE_ID)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].orderType").value(hasItem(DEFAULT_ORDER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].shippingAddress").value(hasItem(DEFAULT_SHIPPING_ADDRESS)))
            .andExpect(jsonPath("$.[*].paidTime").value(hasItem(DEFAULT_PAID_TIME.toString())))
            .andExpect(jsonPath("$.[*].completeTime").value(hasItem(DEFAULT_COMPLETE_TIME.toString())))
            .andExpect(jsonPath("$.[*].shippmentTime").value(hasItem(DEFAULT_SHIPPMENT_TIME.toString())))
            .andExpect(jsonPath("$.[*].deliveryTime").value(hasItem(DEFAULT_DELIVERY_TIME.toString())))
            .andExpect(jsonPath("$.[*].cancelTime").value(hasItem(DEFAULT_CANCEL_TIME.toString())))
            .andExpect(jsonPath("$.[*].dispatchTime").value(hasItem(DEFAULT_DISPATCH_TIME.toString())))
            .andExpect(jsonPath("$.[*].rejectTime").value(hasItem(DEFAULT_REJECT_TIME.toString())))
            .andExpect(jsonPath("$.[*].acceptTime").value(hasItem(DEFAULT_ACCEPT_TIME.toString())))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE)))
            .andExpect(jsonPath("$.[*].rejectNote").value(hasItem(DEFAULT_REJECT_NOTE)))
            .andExpect(jsonPath("$.[*].cancelNote").value(hasItem(DEFAULT_CANCEL_NOTE)));
    }
    
    @Test
    public void getOrder() throws Exception {
        // Initialize the database
        orderRepository.save(order);

        // Get the order
        restOrderMockMvc.perform(get("/api/orders/{id}", order.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(order.getId()))
            .andExpect(jsonPath("$.orderNo").value(DEFAULT_ORDER_NO))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.deliveryEmployeeId").value(DEFAULT_DELIVERY_EMPLOYEE_ID))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.orderType").value(DEFAULT_ORDER_TYPE.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.shippingAddress").value(DEFAULT_SHIPPING_ADDRESS))
            .andExpect(jsonPath("$.paidTime").value(DEFAULT_PAID_TIME.toString()))
            .andExpect(jsonPath("$.completeTime").value(DEFAULT_COMPLETE_TIME.toString()))
            .andExpect(jsonPath("$.shippmentTime").value(DEFAULT_SHIPPMENT_TIME.toString()))
            .andExpect(jsonPath("$.deliveryTime").value(DEFAULT_DELIVERY_TIME.toString()))
            .andExpect(jsonPath("$.cancelTime").value(DEFAULT_CANCEL_TIME.toString()))
            .andExpect(jsonPath("$.dispatchTime").value(DEFAULT_DISPATCH_TIME.toString()))
            .andExpect(jsonPath("$.rejectTime").value(DEFAULT_REJECT_TIME.toString()))
            .andExpect(jsonPath("$.acceptTime").value(DEFAULT_ACCEPT_TIME.toString()))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE))
            .andExpect(jsonPath("$.rejectNote").value(DEFAULT_REJECT_NOTE))
            .andExpect(jsonPath("$.cancelNote").value(DEFAULT_CANCEL_NOTE));
    }
    @Test
    public void getNonExistingOrder() throws Exception {
        // Get the order
        restOrderMockMvc.perform(get("/api/orders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateOrder() throws Exception {
        // Initialize the database
        orderRepository.save(order);

        int databaseSizeBeforeUpdate = orderRepository.findAll().size();

        // Update the order
        Order updatedOrder = orderRepository.findById(order.getId()).get();
        updatedOrder
            .orderNo(UPDATED_ORDER_NO)
            .userId(UPDATED_USER_ID)
            .deliveryEmployeeId(UPDATED_DELIVERY_EMPLOYEE_ID)
            .status(UPDATED_STATUS)
            .orderType(UPDATED_ORDER_TYPE)
            .isActive(UPDATED_IS_ACTIVE)
            .shippingAddress(UPDATED_SHIPPING_ADDRESS)
            .paidTime(UPDATED_PAID_TIME)
            .completeTime(UPDATED_COMPLETE_TIME)
            .shippmentTime(UPDATED_SHIPPMENT_TIME)
            .deliveryTime(UPDATED_DELIVERY_TIME)
            .cancelTime(UPDATED_CANCEL_TIME)
            .dispatchTime(UPDATED_DISPATCH_TIME)
            .rejectTime(UPDATED_REJECT_TIME)
            .acceptTime(UPDATED_ACCEPT_TIME)
            .note(UPDATED_NOTE)
            .rejectNote(UPDATED_REJECT_NOTE)
            .cancelNote(UPDATED_CANCEL_NOTE);
        OrderDTO orderDTO = orderMapper.toDto(updatedOrder);

        restOrderMockMvc.perform(put("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isOk());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeUpdate);
        Order testOrder = orderList.get(orderList.size() - 1);
        assertThat(testOrder.getOrderNo()).isEqualTo(UPDATED_ORDER_NO);
        assertThat(testOrder.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testOrder.getDeliveryEmployeeId()).isEqualTo(UPDATED_DELIVERY_EMPLOYEE_ID);
        assertThat(testOrder.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testOrder.getOrderType()).isEqualTo(UPDATED_ORDER_TYPE);
        assertThat(testOrder.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testOrder.getShippingAddress()).isEqualTo(UPDATED_SHIPPING_ADDRESS);
        assertThat(testOrder.getPaidTime()).isEqualTo(UPDATED_PAID_TIME);
        assertThat(testOrder.getCompleteTime()).isEqualTo(UPDATED_COMPLETE_TIME);
        assertThat(testOrder.getShippmentTime()).isEqualTo(UPDATED_SHIPPMENT_TIME);
        assertThat(testOrder.getDeliveryTime()).isEqualTo(UPDATED_DELIVERY_TIME);
        assertThat(testOrder.getCancelTime()).isEqualTo(UPDATED_CANCEL_TIME);
        assertThat(testOrder.getDispatchTime()).isEqualTo(UPDATED_DISPATCH_TIME);
        assertThat(testOrder.getRejectTime()).isEqualTo(UPDATED_REJECT_TIME);
        assertThat(testOrder.getAcceptTime()).isEqualTo(UPDATED_ACCEPT_TIME);
        assertThat(testOrder.getNote()).isEqualTo(UPDATED_NOTE);
        assertThat(testOrder.getRejectNote()).isEqualTo(UPDATED_REJECT_NOTE);
        assertThat(testOrder.getCancelNote()).isEqualTo(UPDATED_CANCEL_NOTE);
    }

    @Test
    public void updateNonExistingOrder() throws Exception {
        int databaseSizeBeforeUpdate = orderRepository.findAll().size();

        // Create the Order
        OrderDTO orderDTO = orderMapper.toDto(order);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderMockMvc.perform(put("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteOrder() throws Exception {
        // Initialize the database
        orderRepository.save(order);

        int databaseSizeBeforeDelete = orderRepository.findAll().size();

        // Delete the order
        restOrderMockMvc.perform(delete("/api/orders/{id}", order.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
