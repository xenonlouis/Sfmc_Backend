package com.example.sfmc_app.Model.DataExtention;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class DataExtensionItem {
    @JsonProperty("keys")
    private Map<String, String> keys;

    @JsonProperty("values")
    private Map<String, Object> values;
}
