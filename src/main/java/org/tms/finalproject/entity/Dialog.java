package org.tms.finalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tms.finalproject.entity.embeddable.DialogId;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dialogs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dialog {
    @EmbeddedId
    private DialogId id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "dialog")
    private List<Massage> massages;
}
