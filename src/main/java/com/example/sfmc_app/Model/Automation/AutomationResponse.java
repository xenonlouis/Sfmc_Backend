package com.example.sfmc_app.Model.Automation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AutomationResponse {
    private int page;
    private int pageSize;
    private int count;
    private List<AutomationItem> items;


}
