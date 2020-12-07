package org.tms.finalproject.entity.order;

import lombok.*;
import org.thymeleaf.expression.Dates;
import org.tms.finalproject.entity.Location;
import org.tms.finalproject.entity.User;
import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "order_type", discriminatorType = DiscriminatorType.STRING)

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Order {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String description;

    @ManyToOne
    private User author;
    @ManyToOne
    private User executor;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "applicants_orders",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> applicantsToOrder = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Location location;

    private String status = "ACTIVE_STATUS";
    private LocalDateTime orderCreatedDateTime = LocalDateTime.now();



    public Order(long id,
                 String title,
                 String description,
                 User author,
                 User executor,
                 List<User> applicantsToOrder,
                 Location location,
                 String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.executor = executor;
        this.applicantsToOrder = applicantsToOrder;
        this.location = location;
        this.status = status;
    }
}
