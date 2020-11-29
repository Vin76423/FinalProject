package org.tms.finalproject.service.database;

import org.tms.finalproject.dto.order.FilterOrderDto;
import org.tms.finalproject.entity.order.Order;
import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    Order getOrderById(long id);
    void approveOrder(long orderId, long workerId);
    void closeOrderById(long orderId);
    void deleteOrderById(long orderId);

    List<Order> getAllOrders();
    List<Order> getAllOrdersByAuthorLogin(String authorLogin);
    List<Order> getAllOrdersByFilterParameters(FilterOrderDto filter);
    List<Order> getAllOrdersByPartOfTitle(String line);
    List<Order> getAllTakenOrdersByUserName(String userName);
}
