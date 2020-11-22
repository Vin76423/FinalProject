package org.tms.finalproject.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.tms.finalproject.dto.order.OrderDto;
import org.tms.finalproject.entity.order.Order;
import org.tms.finalproject.service.database.OrderService;
import org.tms.finalproject.service.mapper.OrderDtoDoMapperService;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(path = "/customer")
public class CustomerApplicationController {

    private OrderService orderService;
    private OrderDtoDoMapperService orderDtoDoMapperService;
    private Map<String, OrderDto> orderDtoManager;

    public CustomerApplicationController(OrderService orderService,
                                         OrderDtoDoMapperService orderDtoDoMapperService,
                                         Map<String, OrderDto> orderDtoManager) {
        this.orderService = orderService;
        this.orderDtoDoMapperService = orderDtoDoMapperService;
        this.orderDtoManager = orderDtoManager;
    }

    @GetMapping(path = "/create-order")
    public ModelAndView getOrderForm(@RequestParam("order-type") String orderType,
                                     ModelAndView modelAndView) {
        OrderDto orderDto = orderDtoManager.get(orderType);
        modelAndView.addObject("dto", orderDto);
        modelAndView.setViewName("customer/order-form");
        return modelAndView;
    }

    @PostMapping(path = "/create-order")
    public ModelAndView createOrder(@ModelAttribute("dto") OrderDto orderDto,
                                    BindingResult bindingResult,
                                    ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("customer/order-form");
        } else {
            Order order = orderDtoDoMapperService.buildDo(orderDto);
            orderService.createOrder(order);
            modelAndView.setViewName("redirect:/home");
        }
        return modelAndView;
    }

    @GetMapping(path = "/close-order")
    public ModelAndView closeOrder(ModelAndView modelAndView,
                                   @RequestParam("order-id") long orderId) {
        orderService.deleteOrderById(orderId);
        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }

    @GetMapping(path = "/show-my-orders")
    public ModelAndView showCustomerOrders(ModelAndView modelAndView,
                                           Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        List<Order> orders = orderService.getAllOrdersByAuthorLogin(principal.getUsername());
        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }
}
