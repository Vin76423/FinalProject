package org.tms.finalproject.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.tms.finalproject.entity.Location;
import org.tms.finalproject.entity.User;
import org.tms.finalproject.entity.order.Order;
import org.tms.finalproject.entity.order.PaidOrder;
import org.tms.finalproject.entity.order.UnpaidOrder;
import org.tms.finalproject.service.database.OrderService;
import org.tms.finalproject.service.database.OrderServiceImpl;
import org.tms.finalproject.service.database.UserService;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "org.tms.finalproject")
public class WebConfiguration {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @EventListener(classes = ContextRefreshedEvent.class)
    public void contextOnStartUp() {
        User customer = new User(0,"Oleg", "123", "CUSTOMER", 0.0, null, null);
        User worker = new User(0,"user", "user", "WORKER", 0.0, null, null);
        userService.createUser(customer);
        userService.createUser(worker);

        // First Order Mock:
        Location location1 = new Location(0, "Phoenix", "Cross", 25, 45);
        String title1 = "Subjugate a water pipe";
        String description1 = "The water pipe in my house is leaking. It is necessary to take measurements from the damaged area, buy the necessary parts, and replace the emergency network section.";
        Order order1 = new PaidOrder(0, title1, description1, customer, null, null, location1, "ACTIVE_STATUS", 55);

        // Second Order Mock:
        Location location2 = new Location(0, "San Antonio", "Rousvud", 12, 0);
        String title2 = "Harvest tomatoes from the greenhouse";
        String description2 = "I am an elderly farmer. There was an accident and I broke my leg, so I can't harvest on my own. Help is very much needed as the harvest time is running out and the fruits may deteriorate.";
        Order order2 = new UnpaidOrder(0, title2, description2, customer, null, null, location2, "ACTIVE_STATUS", "Wery");

        // Third Order Mock:
        Location location3 = new Location(0, "Dallas", "Cross", 34, 13);
        String title3 = "Driving a car";
        String description3 = "I am a very busy person. I agreed to buy a used car, but, unfortunately, I cannot come to the deal myself, I need a person who will do it for me.";
        Order order3 = new PaidOrder(0, title3, description3, customer, worker, null, location3, "IN_WORK_STATUS", 115);

        orderService.createOrder(order1);
        orderService.createOrder(order2);
        orderService.createOrder(order3);
    }
}
