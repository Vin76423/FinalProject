package org.tms.finalproject.entity.order;

import lombok.*;
import org.tms.finalproject.entity.Location;
import org.tms.finalproject.entity.User;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue(value = "PAID")

@Data
@NoArgsConstructor
public class PaidOrder extends Order {
    private double price;
//    private LocalDateTime orderDeadlineDateTime;

    public PaidOrder(long id,
                     String title,
                     String description,
                     User author,
                     User executor,
                     List<User> applicantsToOrder,
                     Location location,
                     String status,
                     double price) {
        super(id, title, description, author, executor, applicantsToOrder, location, status);
        this.price = price;
    }
}
