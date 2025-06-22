package com.lakshan.carbonwise.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "emission_entries")
public class EmissionEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type")
    private String type;

    @Column(name = "data")
    private String data;

    @Column(name = "method")
    private String method;

    @Column(name = "iata_airport_from")
    private String iataAirportFrom;

    @Column(name = "iata_airport_to")
    private String iataAirportTo;

    @Column(name = "number_of_passengers")
    private int numberOfPassengers;

    @Column(name = "amount")
    private double amount;

    @Column(name = "unit")
    private String unit;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "co2_emission")
    private double co2Emission;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getIataAirportFrom() {
        return iataAirportFrom;
    }

    public void setIataAirportFrom(String iataAirportFrom) {
        this.iataAirportFrom = iataAirportFrom;
    }

    public String getIataAirportTo() {
        return iataAirportTo;
    }

    public void setIataAirportTo(String iataAirportTo) {
        this.iataAirportTo = iataAirportTo;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getCo2Emission() {
        return co2Emission;
    }

    public void setCo2Emission(double co2Emission) {
        this.co2Emission = co2Emission;
    }
}
