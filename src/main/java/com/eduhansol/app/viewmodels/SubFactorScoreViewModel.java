package com.eduhansol.app.viewmodels;

public class SubFactorScoreViewModel{
    private int subFactorId;
    private double tScore;

    public SubFactorScoreViewModel(int subFactorId, double tScore) {
        this.subFactorId = subFactorId;
        this.tScore = tScore;
    }
    public int getSubFactorId() {
        return this.subFactorId;
    }

    public void setSubFactorId(int subFactorId) {
        this.subFactorId = subFactorId;
    }

    public double getTScore() {
        return this.tScore;
    }

    public void setTScore(double tScore) {
        this.tScore = tScore;
    }
}