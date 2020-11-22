package org.tms.finalproject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.tms.finalproject.dto.order.OrderDto;
import org.tms.finalproject.dto.order.PaidOrderDto;
import org.tms.finalproject.dto.order.UnpaidOrderDto;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "org.tms.finalproject")
public class WebConfiguration {

    @Bean
    public Map<String, OrderDto> orderDtoManager() {
        Map<String, OrderDto> manager = new HashMap<>();
        manager.put("PAID_ORDER", new PaidOrderDto());
        manager.put("UNPAID_ORDER", new UnpaidOrderDto());
        return manager;
    }
}
