package org.tms.finalproject.entity.order;

import lombok.*;
import org.tms.finalproject.entity.Location;
import org.tms.finalproject.entity.User;
import javax.persistence.*;

@Entity
@Table(name = "orders")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "order_type", discriminatorType = DiscriminatorType.STRING)

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String description;

    @OneToOne
    private User author;

    @OneToOne(cascade = CascadeType.ALL)
    private Location location;

    private boolean isActive = true;
//    private LocalDateTime orderCreatedDateTime = LocalDateTime.now();
}
