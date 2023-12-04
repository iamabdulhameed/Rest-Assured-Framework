package com.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AssertionKeys {

    private String jsonPath;
    private Object actualValue;
    private Object expectedValue;
    private String result;
}
