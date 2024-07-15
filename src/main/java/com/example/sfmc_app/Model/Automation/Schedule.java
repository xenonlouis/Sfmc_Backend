package com.example.sfmc_app.Model.Automation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.boot.jackson.JsonComponent;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonComponent
@ToString
public class Schedule {
    @JsonProperty("type")
    private String type;
    @JsonProperty("startDate")
    private String startDate;
    @JsonProperty("endDate")
    private String endDate;
    @JsonProperty("runFrequency")
    private String runFrequency;
}
