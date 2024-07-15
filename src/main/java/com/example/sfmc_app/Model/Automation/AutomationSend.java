package com.example.sfmc_app.Model.Automation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AutomationSend {

    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("status")
    private String status;
    @JsonProperty("schedule")
    private Schedule schedule;

    public AutomationSend(AutomationItem item){
        this.name = item.getName();
        this.description = item.getDescription();
        this.status = item.getStatus();
        this.schedule = item.getSchedule();
    }
}
