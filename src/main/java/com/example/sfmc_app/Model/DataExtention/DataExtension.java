package com.example.sfmc_app.Model.DataExtention;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataExtension {
    @JsonProperty("customerKey")
    private String customerKey;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("isSendable")
    private Boolean isSendable;

    @JsonProperty("createdDate")
    private String createdDate;

    @JsonProperty("modifiedDate")
    private String modifiedDate;
}
