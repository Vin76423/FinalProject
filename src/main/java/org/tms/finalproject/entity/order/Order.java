package org.tms.finalproject.entity.order;

import lombok.*;
import org.tms.finalproject.entity.Location;
import org.tms.finalproject.entity.User;
import javax.persistence.*;
import java.util.List;

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

    @ManyToOne
    private User author;

    @ManyToOne
    private User executor;

    @ManyToMany(fetch = FetchType.EAGER/*, mappedBy = "supposedOrders"*/)
    @JoinTable(
            name = "applicants_orders",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> applicantsToOrder;

    @OneToOne(cascade = CascadeType.ALL)
    private Location location;

    private String status = "ACTIVE_STATUS";
//    ACTIVE_STATUS
//    AWAITING_APPROVAL_STATUS
//    IN_WORK_STATUS
//    CLOSED_STATUS

//    private LocalDateTime orderCreatedDateTime = LocalDateTime.now();
}
