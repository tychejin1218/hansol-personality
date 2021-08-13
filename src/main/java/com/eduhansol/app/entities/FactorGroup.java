package com.eduhansol.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "factor_groups")
public class FactorGroup {
    @Id
    private int id;

    @Column(nullable = false)
    private int normPersonalityId;

    @ManyToOne
    @JoinColumn
    private Factor factor;
    
    @ManyToOne
    @JoinColumn
    private SubFactor subFactor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNormPersonalityId() {
        return normPersonalityId;
    }

    public void setNormPersonalityId(int normPersonalityId) {
        this.normPersonalityId = normPersonalityId;
    }

    public Factor getFactor() {
        return factor;
    }

    public void setFactor(Factor factor) {
        this.factor = factor;
    }

    public SubFactor getSubFactor() {
        return subFactor;
    }

    public void setSubFactor(SubFactor subFactor) {
        this.subFactor = subFactor;
    }
}
