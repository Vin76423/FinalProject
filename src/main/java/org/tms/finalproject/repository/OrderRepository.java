package org.tms.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tms.finalproject.entity.order.Order;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    void deleteOrderById(long id);

    @Query(nativeQuery = true, value = "SELECT * FROM orders WHERE order_type = ?")
    List<Order> findAllByOrderType(String orderType);

    List<Order> findAllByAuthor_UserName(String userName);
}
