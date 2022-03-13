package com.zepto.irctc.dto;

import jdk.dynalink.linker.LinkerServices;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Validated
@Data
public class TrainWithAddedCoahesIdsData {
    Integer trainId;
    Integer trainName;
    List<NestedCoacheData> list;

    @Data
    public static class NestedCoacheData{
        Integer id;
        String name;
        String type;
    }
}
