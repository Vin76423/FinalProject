package org.tms.finalproject.service.database;

import org.tms.finalproject.entity.order.Order;
import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    void deleteOrderById(long id);

    List<Order> getAllOrders();
    List<Order> getAllOrdersByOrderType(String orderType);
    List<Order> getAllOrdersByAuthorLogin(String authorLogin);
}
