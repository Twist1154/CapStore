package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.OrderItem;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemFactoryTest {

    @Test
    void buildOrderItem() {
        OrderItem orderItem1 = OrderItemFactory.buildOrderItem(
                1L,
                1L,
                12,
                12.00
        );

        assertNotNull(orderItem1);
        assertEquals(1L, orderItem1.getOrderItemId());
        assertEquals(1L, orderItem1.getProductID());
        assertEquals(12, orderItem1.getQuantity());
        assertEquals(12, orderItem1.getPrice());
    }
}