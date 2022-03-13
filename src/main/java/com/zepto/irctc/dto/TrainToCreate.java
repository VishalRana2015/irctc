package com.zepto.irctc.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Validated
@Data
public class TrainToCreate {
    @NotEmpty
    @NotBlank
    @NotNull
    String trainName;
}
