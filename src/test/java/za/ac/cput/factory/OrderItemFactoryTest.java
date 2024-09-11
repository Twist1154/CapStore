package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.OrderItem;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemFactoryTest {

    @Test
    void buildOrderItem() {
        // Create test OrderItem with valid data
        OrderItem orderItem = OrderItemFactory.buildOrderItem(
                1L,
                12L,
                2,
                12.00,
                79L
        );

        // Ensure that the OrderItem was created successfully and is not null
        assertNotNull(orderItem);

        // Additional assertions to ensure the order item details are correct
        assertEquals(1L, orderItem.getOrderItemID());
        assertEquals(12L, orderItem.getProductID());
        assertEquals(2, orderItem.getQuantity());
        assertEquals(12.00, orderItem.getPrice());
    }

    @Test
    void buildOrderItemWithInvalidData() {
        // Expecting IllegalArgumentException for invalid data
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            OrderItemFactory.buildOrderItem(
                    null,
                    null,
                    -1,
                    -10.00,
                    79L
            );
        });

        assertEquals("Product ID must not be null or empty.", thrown.getMessage());
    }
}
