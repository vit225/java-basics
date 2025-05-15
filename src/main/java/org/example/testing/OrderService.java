package org.example.testing;

import java.util.Optional;

public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String processOrder(Order order) {
        int idOrder = orderRepository.saveOrder(order);
        if (idOrder > 0) {
            return "Order processed successfully";
        } else return "Order not processed";
    }

    public double calculateTotal(int id) {
        Optional<Order> order = orderRepository.getOrderById(id);
        return order.map(Order::getTotalPrice).orElse(0.0);
    }
}
