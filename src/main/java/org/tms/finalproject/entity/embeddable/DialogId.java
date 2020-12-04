package org.tms.finalproject.entity.embeddable;

import lombok.*;
import org.tms.finalproject.entity.User;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DialogId implements Serializable {
    @OneToOne
    private User firstMember;
    @OneToOne
    private User secondMember;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DialogId dialogId = (DialogId) o;
        return Objects.equals(firstMember, dialogId.firstMember) &&
                Objects.equals(secondMember, dialogId.secondMember);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstMember, secondMember);
    }
}
