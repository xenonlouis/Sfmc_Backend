package com.example.sfmc_app.Model.Journey;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class JourneyItem {
    @JsonProperty("id")
    private String id;

    @JsonProperty("key")
    private String key;

    @JsonProperty("name")
    private String name;

    @JsonProperty("status")
    private String status;

    @JsonProperty("createdDate")
    private Date createdDate;

    @JsonProperty("modifiedDate")
    private Date modifiedDate;

    @JsonProperty("activities")
    private List<Activity> activities;
}
