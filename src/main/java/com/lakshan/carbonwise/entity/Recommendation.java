package com.lakshan.carbonwise.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "recommendations")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "category")
    private String category;

    @Column(name = "carbon_impact")
    private Double carbonImpact;

    @Column(name = "financial_impact")
    private Double financialImpact;

    @Column(name = "implementation_cost")
    private Double implementationCost;

    @Column(name = "payback_period")
    private String paybackPeriod;

    @Column(name = "implementation_difficulty")
    private String implementationDifficulty;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "emission_id")
    private EmissionEntry emissionEntry;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getCarbonImpact() {
        return carbonImpact;
    }

    public void setCarbonImpact(Double carbonImpact) {
        this.carbonImpact = carbonImpact;
    }

    public Double getFinancialImpact() {
        return financialImpact;
    }

    public void setFinancialImpact(Double financialImpact) {
        this.financialImpact = financialImpact;
    }

    public Double getImplementationCost() {
        return implementationCost;
    }

    public void setImplementationCost(Double implementationCost) {
        this.implementationCost = implementationCost;
    }

    public String getPaybackPeriod() {
        return paybackPeriod;
    }

    public void setPaybackPeriod(String paybackPeriod) {
        this.paybackPeriod = paybackPeriod;
    }

    public String getImplementationDifficulty() {
        return implementationDifficulty;
    }

    public void setImplementationDifficulty(String implementationDifficulty) {
        this.implementationDifficulty = implementationDifficulty;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public EmissionEntry getEmissionEntry() {
        return emissionEntry;
    }

    public void setEmissionEntry(EmissionEntry emissionEntry) {
        this.emissionEntry = emissionEntry;
    }
}
