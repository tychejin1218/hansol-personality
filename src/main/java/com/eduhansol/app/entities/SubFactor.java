package com.eduhansol.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subFactors")
public class SubFactor {

    @Id
    private int id;

    @ManyToOne
    @JoinColumn
    private NormPersonality normPersonality;

    @Column(nullable = false)
    private int subFactorId;

    @Column(nullable=false)
    private String name;
    
    @Column(nullable=false)
    private String description;

    @Column(nullable=false)
    private double nMean;

    @Column(nullable=false)
    private double nDev;

    @Column(nullable=false)
    private double iMean;

    @Column(nullable=false)
    private double iDev;

    @Column(nullable=false)
    private double mean;

    @Column(nullable=false)
    private double dev;

    @Column(nullable=false)
    private double nWeight;

    @Column(nullable=false)
    private double iWeight;

    @Column(nullable=false)
    private boolean isDummy;

    @Column(nullable = false)
    private boolean isReverse;

    public boolean getIsReverse() {
        return this.isReverse;
    }

    public void setIsReverse(boolean isReverse) {
        this.isReverse = isReverse;
    }

    public boolean getIsDummy() {
        return this.isDummy;
    }

    public void setIsDummy(boolean isDummy) {
        this.isDummy = isDummy;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NormPersonality getNormPersonality() {
        return this.normPersonality;
    }

    public void setNormPersonality(NormPersonality normPersonality) {
        this.normPersonality = normPersonality;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getNMean() {
        return this.nMean;
    }

    public void setNMean(double nMean) {
        this.nMean = nMean;
    }

    public double getNDev() {
        return this.nDev;
    }

    public void setNDev(double nDev) {
        this.nDev = nDev;
    }

    public double getIMean() {
        return this.iMean;
    }

    public void setIMean(double iMean) {
        this.iMean = iMean;
    }

    public double getIDev() {
        return this.iDev;
    }

    public void setIDev(double iDev) {
        this.iDev = iDev;
    }

    public double getMean() {
        return this.mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getDev() {
        return this.dev;
    }

    public void setDev(double dev) {
        this.dev = dev;
    }

    public double getNWeight() {
        return this.nWeight;
    }

    public void setNWeight(double nWeight) {
        this.nWeight = nWeight;
    }

    public double getIWeight() {
        return this.iWeight;
    }

    public void setIWeight(double iWeight) {
        this.iWeight = iWeight;
    }
 
    public int getSubFactorId() {
        return this.subFactorId;
    }

    public void setSubFactorId(int subFactorId) {
        this.subFactorId = subFactorId;
    }
}