package com.example.tobyspring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)     // UnrecognizedPropertyException 처리(필요한 것들만 받기 위함)
public record ExRateData(String result, Map<String, BigDecimal> rates) {
}
