package org.tms.finalproject.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.tms.finalproject.dto.CommentDto;
import org.tms.finalproject.dto.order.FilterOrderDto;
import org.tms.finalproject.entity.User;
import org.tms.finalproject.entity.order.Order;
import org.tms.finalproject.service.database.OrderService;
import org.tms.finalproject.service.database.UserService;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/worker")
public class WorkerApplicationController {

    private OrderService orderService;
    private UserService userService;

    public WorkerApplicationController(OrderService orderService,
                                       UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping(path = "/get-all-orders")
    public ModelAndView getAllOrders(ModelAndView modelAndView) {
        List<Order> orders = orderService.getAllOrders();
        modelAndView.addObject("orders", orders);
        modelAndView.addObject("filterDto", new FilterOrderDto());
        modelAndView.setViewName("worker/list-orders");
        return modelAndView;
    }

    @PostMapping(path = "/get-orders-by-alias")
    public ModelAndView getOrdersByAlias(@RequestParam("alias") String alias,
                                         ModelAndView modelAndView) {
        List<Order> orders = orderService.getAllOrdersByPartOfTitle(alias);
        modelAndView.addObject("orders", orders);
        modelAndView.addObject("filterDto", new FilterOrderDto());
        modelAndView.setViewName("worker/list-orders");
        return modelAndView;
    }

    @PostMapping(path = "/get-orders-by-filters")
    public ModelAndView getOrdersByFilters(@ModelAttribute("filterDto") FilterOrderDto filter,
                                           ModelAndView modelAndView) {
        List<Order> orders = orderService.getAllOrdersByFilterParameters(filter);
        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("worker/list-orders");
        return modelAndView;
    }

    @GetMapping(path = "/get-taken-orders")
    public ModelAndView getTakenOrders(ModelAndView modelAndView) {
        SecurityContext context = SecurityContextHolder.getContext();
        String userName = ((org.springframework.security.core.userdetails.User) context.getAuthentication().getPrincipal()).getUsername();

        List<Order> orders = orderService.getAllTakenOrdersByUserName(userName);
        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("worker/progress");
        return modelAndView;
    }

    @GetMapping(path = "/get-order-by-id")
    public ModelAndView getOrderById(@RequestParam("order-id") long orderId,
                                     ModelAndView modelAndView) {
        Order order = orderService.getOrderById(orderId);
        modelAndView.addObject("order", order);
        modelAndView.addObject("commentDto", new CommentDto());
        modelAndView.setViewName("worker/order");
        return modelAndView;
    }

    @GetMapping(path = "/take-order")
    public ModelAndView takeOrder(@RequestParam("order-id") long orderId,
                                  ModelAndView modelAndView) {
        SecurityContext context = SecurityContextHolder.getContext();
        String userName = ((org.springframework.security.core.userdetails.User) context.getAuthentication().getPrincipal()).getUsername();
        User newApplicant = userService.getUserByLogin(userName);
        Order order = orderService.getOrderById(orderId);

        // First Step:
        if (!order.getStatus().equals("AWAITING_APPROVAL_STATUS")) {
            order.setStatus("AWAITING_APPROVAL_STATUS");
        }

        // Second Step:
        if (order.getApplicantsToOrder() == null) {
            List<User> applicants = new ArrayList<>();
            applicants.add(newApplicant);
            order.setApplicantsToOrder(applicants);
        } else {
            order.getApplicantsToOrder().add(newApplicant);
        }
        // Evokes within itself orderRepository.save(order), and will overwrite old order:
        orderService.createOrder(order);

        modelAndView.setViewName("redirect:/worker/get-taken-orders");
        return modelAndView;
    }

    @GetMapping(path = "/delete-order")
    public ModelAndView deleteOrder(@RequestParam("order-id") long orderId,
                                    ModelAndView modelAndView) {
        orderService.deleteOrderById(orderId);
        modelAndView.setViewName("redirect:/worker/get-taken-orders");
        return modelAndView;
    }
}
