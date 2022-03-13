package com.zepto.irctc.dto;

import javax.validation.constraints.NotNull;

public class TrainData {
    @NotNull
    String name;
    @NotNull
    Integer id;
}
