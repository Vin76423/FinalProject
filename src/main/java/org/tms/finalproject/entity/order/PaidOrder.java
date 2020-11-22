package org.tms.finalproject.entity.order;

import lombok.*;
import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "PAID")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaidOrder extends Order {
    private double price;
//    private LocalDateTime orderDeadlineDateTime;
}
