package com.lakshan.carbonwise.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "emission_factors")
public class EmissionFactor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type")
    private String type;

    @Column(name = "unit")
    private String unit;

    @Column(name = "co2e_factor")
    private double co2eFactor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getCo2eFactor() {
        return co2eFactor;
    }

    public void setCo2eFactor(double co2eFactor) {
        this.co2eFactor = co2eFactor;
    }
}
