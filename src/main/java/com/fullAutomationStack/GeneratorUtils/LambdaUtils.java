package com.fullAutomationStack.GeneratorUtils;

import java.util.List;

public class LambdaUtils {

    public static String getContainsStringFromArray(List<String> arr, String valueExpected){
        return arr.stream()
                .filter( c -> c.contains(valueExpected))
                .findAny()
                        .map(v -> valueExpected)
                .orElse(null);
    }

}
