package org.tms.finalproject.entity;

import lombok.*;
import org.tms.finalproject.entity.order.Order;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userName;
    private String password;
    private String role;
    private double rating;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "executor")
    private List<Order> executeOrders;

//    @ToString.Exclude
//    @ManyToMany
//    private List<Order> supposedOrders;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "owner")
    private List<Comment> comments;

    public User(long id) {
        this.id = id;
    }
}
