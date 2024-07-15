package com.example.sfmc_app.Model.DataExtention;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataExtensionField {
    @JsonProperty("name")
    private String name;

    @JsonProperty("fieldType")
    private String fieldType;

    @JsonProperty("maxLength")
    private int maxLength;

    @JsonProperty("isPrimaryKey")
    private boolean isPrimaryKey;

    @JsonProperty("isRequired")
    private boolean isRequired;
}
