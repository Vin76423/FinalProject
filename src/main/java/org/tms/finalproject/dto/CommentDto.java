package org.tms.finalproject.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class CommentDto {
    private long orderOwnerId; // hiden field in form
    private String rating; // select field in form, have a defult value
    @NotEmpty(message = "It's required field!")
    @NotBlank(message = "It's required field!")
    private String title;
    private String description;
}
