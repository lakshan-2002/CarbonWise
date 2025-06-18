package com.lakshan.carbonwise.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor(force = true)
public class EmissionCategory {
    public final Map<String, Double> calculated;
    public final List<String> endpoint;
}