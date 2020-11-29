package org.tms.finalproject.dto.order;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FilterOrderDto {

    private String locationCity;
    private String locationStreet;

    private String orderType;

    private double fromPrice;
    private double toPrice;

}
