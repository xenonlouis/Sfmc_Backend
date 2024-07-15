package com.example.sfmc_app.Model.Automation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.springframework.boot.jackson.JsonComponent;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonComponent
@JsonSerialize
public class AutomationItem {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("status")
    private String status;
    @JsonProperty("schedule")
    private Schedule schedule;

}
