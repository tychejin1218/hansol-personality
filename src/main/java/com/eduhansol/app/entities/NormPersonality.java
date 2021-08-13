package com.eduhansol.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="norm_personalities")
public class NormPersonality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double mean;

    @Column(nullable = false)
    private double dev;

    @Column(nullable = false)
    private double consistencyMean;

    @Column(nullable = false)
    private double consistencyDev;

    @Column(nullable = false)
    private int timeout;

    @Column(nullable = false)
    private int failConsistencySten;

    @Column(nullable = false)
    private double failDev;

    @Column(nullable = false)
    private int failMark1Count;

    @Column(nullable = false)
    private int failExtremeMark1Count;

    @Column(nullable = false)
    private boolean isDelete;

    public int getId() {
        return id;
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

    public double getConsistencyMean() {
        return consistencyMean;
    }

    public void setConsistencyMean(double consistencyMean) {
        this.consistencyMean = consistencyMean;
    }

    public double getConsistencyDev() {
        return consistencyDev;
    }

    public void setConsistencyDev(double consistencyDev) {
        this.consistencyDev = consistencyDev;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getFailConsistencySten() {
        return failConsistencySten;
    }

    public void setFailConsistencySten(int failConsistencySten) {
        this.failConsistencySten = failConsistencySten;
    }

    public double getFailDev() {
        return failDev;
    }

    public void setFailDev(double failDev) {
        this.failDev = failDev;
    }

    public int getFailMark1Count() {
        return failMark1Count;
    }

    public void setFailMark1Count(int failMark1Count) {
        this.failMark1Count = failMark1Count;
    }

    public int getFailExtremeMark1Count() {
        return failExtremeMark1Count;
    }

    public void setFailExtremeMark1Count(int failExtremeMark1Count) {
        this.failExtremeMark1Count = failExtremeMark1Count;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}
