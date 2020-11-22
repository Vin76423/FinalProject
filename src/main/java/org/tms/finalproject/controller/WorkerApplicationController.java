package org.tms.finalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.tms.finalproject.entity.order.Order;
import org.tms.finalproject.service.database.OrderService;
import java.util.List;

@Controller
@RequestMapping(path = "/worker")
public class WorkerApplicationController {

    @Autowired
    private OrderService orderService;

    @GetMapping(path = "get-all-orders")
    public ModelAndView getAllOrders(ModelAndView modelAndView) {
        List<Order> orders = orderService.getAllOrders();
        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("/worker/order-list");
        return modelAndView;
    }


}
