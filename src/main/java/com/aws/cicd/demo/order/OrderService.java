package com.aws.cicd.demo.order;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    //RDS DB
    private final List<Order> Orders = new ArrayList<>();

    // Create a new Order
    public void addOrder(Order Order) {
        Orders.add(Order);
    }

    // Retrieve all Orders
    public List<Order> getAllOrders() {
        return Orders;
    }

    // Retrieve a Order by id
    public Optional<Order> getOrderById(int id) {
        return Orders.stream()
                .filter(Order -> Order.getId() == id)
                .findFirst();
    }

    // Update a Order
    public boolean updateOrder(int id, Order newOrder) {
        return getOrderById(id).map(existingOrder -> {
            Orders.remove(existingOrder);
            Orders.add(newOrder);
            return true;
        }).orElse(false);
    }

    // Delete a Order by id
    public boolean deleteOrder(int id) {
        return Orders
                .removeIf(Order -> Order.getId() == id);
    }
}
