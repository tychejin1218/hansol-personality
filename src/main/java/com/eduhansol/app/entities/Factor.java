package com.eduhansol.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "factors")
public class Factor {

    @Id
    private int id;

    @ManyToOne
    @JoinColumn
    private NormPersonality normPersonality;

    @Column(nullable = false)
    private int factorId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double mean;

    @Column(nullable = false)
    private double dev;

    @Column(nullable = false)
    private double weight;

    public int getFactorId() {
        return factorId;
    }

    public void setfactorId(int factorId) {
        this.factorId = factorId;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getDev() {
        return dev;
    }

    public void setDev(double dev) {
        this.dev = dev;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NormPersonality getNormPersonality() {
        return normPersonality;
    }

    public void setNormPersonality(NormPersonality normPersonality) {
        this.normPersonality = normPersonality;
    }
}
