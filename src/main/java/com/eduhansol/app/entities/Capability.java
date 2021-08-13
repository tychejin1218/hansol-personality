package com.eduhansol.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="capabilities")
public class Capability {
    @Id
    private int id;

    @ManyToOne
    @JoinColumn
    private NormPersonality normPersonality;

    @Column(nullable = false)
    private int no;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double mean;

    @Column(nullable = false)
    private double dev;

    @Column(nullable = false)
    private double weight;

    @Column(nullable = false)
    private boolean isTotal;

    public int getId() {
        return id;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public NormPersonality getNormPersonality() {
        return normPersonality;
    }

    public void setNormPersonality(NormPersonality normPersonality) {
        this.normPersonality = normPersonality;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getDev() {
        return dev;
    }

    public void setDev(double dev) {
        this.dev = dev;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isTotal() {
        return isTotal;
    }

    public void setTotal(boolean total) {
        isTotal = total;
    }
}
