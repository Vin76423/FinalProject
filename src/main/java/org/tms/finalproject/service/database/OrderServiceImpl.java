package org.tms.finalproject.service.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tms.finalproject.entity.order.Order;
import org.tms.finalproject.repository.OrderRepository;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order is null!");
        }
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrderById(long id) {
        if (id < 1) {
            throw new IllegalArgumentException("Id is not correct!");
        }
        orderRepository.deleteOrderById(id);
    }

    @Override
    public List<Order> getAllOrders() { return orderRepository.findAll(); }

    @Override
    public List<Order> getAllOrdersByOrderType(String orderType) {
        if (orderType == null) {
            throw new IllegalArgumentException("Orders type is null!");
        }
        return orderRepository.findAllByOrderType(orderType);
    }

    @Override
    public List<Order> getAllOrdersByAuthorLogin(String authorLogin) {
        if (authorLogin == null) {
            throw new IllegalArgumentException("Author's login is null!");
        }
        return orderRepository.findAllByAuthor_UserName(authorLogin);
    }
}
