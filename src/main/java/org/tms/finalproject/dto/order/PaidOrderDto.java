package org.tms.finalproject.dto.order;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class PaidOrderDto extends OrderDto {
    @NotEmpty(message = "It's required field!")
    @NotBlank(message = "It's required field!")
    // Create a constraint "Not negative" :
    private double price;
//    private LocalDateTime orderDeadlineDateTime;
}
