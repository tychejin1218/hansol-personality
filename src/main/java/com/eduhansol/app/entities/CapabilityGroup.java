package com.eduhansol.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="capability_groups")
public class CapabilityGroup {
    @Id
    private int id;
    
    @Column(nullable = false)
    private int normPersonalityId;

    @Column(nullable = false)
    private int capabilityId;

    @Column(nullable = false)
    private int factorId;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getNormPersonalityId() {
        return normPersonalityId;
    }

    public void setNormPersonalityId(int normPersonalityId) {
        this.normPersonalityId = normPersonalityId;
    }

    public int getCapabilityId() {
        return capabilityId;
    }

    public void setCapabilityId(int capabilityId) {
        this.capabilityId = capabilityId;
    }

    public int getFactorId() {
        return factorId;
    }

    public void setFactorId(int factorId) {
        this.factorId = factorId;
    }
}
