package com.zepto.irctc.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@Data
public class CoacheToCreate {
    @NotNull @NotEmpty @NotBlank
    String name;
    @NotEmpty @NotNull @NotBlank
    String type;

    Integer id;
    List<SeatData> seatData ;
}
