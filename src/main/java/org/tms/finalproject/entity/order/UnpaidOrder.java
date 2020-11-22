package org.tms.finalproject.entity.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "UN_PAID")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnpaidOrder extends Order {
    private String priority;
}
