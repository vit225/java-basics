import org.example.testing.Order;
import org.example.testing.OrderRepository;
import org.example.testing.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class OrderServiceTest {

    private OrderService orderService;
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        orderRepository = mock(OrderRepository.class);
        orderService = new OrderService(orderRepository);
    }

    @Test
    public void shouldReturnSuccessMessageWhenSaveTrue() {
        Order order = new Order(1, "Phone", 3, 20000.0);
        when(orderRepository.saveOrder(order)).thenReturn(order.getId());

        String message = orderService.processOrder(order);

        assertEquals("Order processed successfully", message);
        verify(orderRepository, Mockito.times(1)).saveOrder(order);
    }

    @Test
    public void shouldReturnFailureMessageWhenSaveFalse() {
        Order order = new Order(2, "Phone", 3, 20000.0);
        when(orderRepository.saveOrder(order)).thenReturn(-1);

        String message = orderService.processOrder(order);

        assertEquals("Order not processed", message);
        verify(orderRepository, Mockito.times(1)).saveOrder(order);
    }

    @Test
    public void calculateTotalPriceSuccessfully() {
        Order order = new Order(3, "Phone", 3, 20000.00);
        when(orderRepository.getOrderById(1)).thenReturn(Optional.of(order));

        double totalPrice = orderService.calculateTotal(1);

        assertEquals(60000.0, totalPrice, 0.001);
        verify(orderRepository, Mockito.times(1)).getOrderById(1);
    }

    @Test
    public void calculateTotalPriceNotFound() {
        when(orderRepository.getOrderById(50)).thenReturn(Optional.empty());

        double totalPrice = orderService.calculateTotal(50);

        assertEquals(0.0, totalPrice, 0.0001);
        verify(orderRepository, Mockito.times(1)).getOrderById(50);
    }

    @Test
    public void calculateTotalZeroQuantityShouldSuccessfully() {
        Order order = new Order(4, "Phone", 0, 20000.54);
        when(orderRepository.getOrderById(1)).thenReturn(Optional.of(order));

        double totalPrice = orderService.calculateTotal(4);

        assertEquals(0.0, totalPrice, 0.0001);
        verify(orderRepository, Mockito.times(1)).getOrderById(4);
    }

    @Test
    public void calculateTotalZeroUnitPriceShouldSuccessfully() {
        Order order = new Order(5, "Phone", 3, 0.0);
        when(orderRepository.getOrderById(1)).thenReturn(Optional.of(order));

        double totalPrice = orderService.calculateTotal(5);

        assertEquals(0.0, totalPrice, 0.0001);
        verify(orderRepository, Mockito.times(1)).getOrderById(5);
    }
}
