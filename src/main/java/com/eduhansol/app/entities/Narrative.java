package com.eduhansol.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "narratives")
public class Narrative{

    @Id
    private int id;

    @Column(nullable=false)
    private int subFactorId;

    @Column(nullable=false)
    private int minSten;

    @Column(nullable=false)
    private int maxSten;

    @Column(nullable=false)
    private String script;
    
    @Column(nullable=false)
    private String description;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubFactorId() {
        return this.subFactorId;
    }

    public void setSubFactorId(int subFactorId) {
        this.subFactorId = subFactorId;
    }

    public int getMinSten() {
        return this.minSten;
    }

    public void setMinSten(int minSten) {
        this.minSten = minSten;
    }

    public int getMaxSten() {
        return this.maxSten;
    }

    public void setMaxSten(int maxSten) {
        this.maxSten = maxSten;
    }

    public String getScript() {
        return this.script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}