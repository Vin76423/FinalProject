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


    public PaidOrderDto(@NotEmpty(message = "It's required field!") @NotBlank(message = "It's required field!") String title,
                        String description,
                        @NotEmpty(message = "It's required field!") @NotBlank(message = "It's required field!") String locationCity,
                        @NotEmpty(message = "It's required field!") @NotBlank(message = "It's required field!") String locationStreet,
                        @NotEmpty(message = "It's required field!") @NotBlank(message = "It's required field!") int locationHome,
                        int locationFlat,
                        @NotEmpty(message = "It's required field!") @NotBlank(message = "It's required field!") double price) {
        super(title, description, locationCity, locationStreet, locationHome, locationFlat);
        this.price = price;
    }
}
