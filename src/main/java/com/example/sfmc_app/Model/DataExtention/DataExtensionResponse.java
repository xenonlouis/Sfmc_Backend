package com.example.sfmc_app.Model.DataExtention;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DataExtensionResponse {
    @JsonProperty("items")
    private List<DataExtension> items;
}
