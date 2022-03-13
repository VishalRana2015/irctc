package com.zepto.irctc.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Validated
@Data
public class AddCoachesToTrainData {
    @NotNull
    Integer trainId;
    @NotNull
    List<Integer> coacheIDs;
}
