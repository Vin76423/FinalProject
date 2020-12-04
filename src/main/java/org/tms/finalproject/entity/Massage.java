package org.tms.finalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;

@Entity
@Table(name = "massages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Massage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({@JoinColumn(name = "dialog_id_first_member"),
                    @JoinColumn(name = "dialog_id_second_member")})
    private Dialog dialog;

    @OneToOne
    private User author;

    private String text;
    //    private LocalDateTime orderCreatedDateTime = LocalDateTime.now();
}
