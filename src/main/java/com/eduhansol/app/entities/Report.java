package com.eduhansol.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    private int id;
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String personTemplatePath;

    @Column(nullable = false)
    private String HrTemplatePath;

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

    public String getPersonTemplatePath() {
        return personTemplatePath;
    }

    public void setPersonTemplatePath(String personTemplatePath) {
        this.personTemplatePath = personTemplatePath;
    }

    public String getHrTemplatePath() {
        return HrTemplatePath;
    }

    public void setHrTemplatePath(String hrTemplatePath) {
        HrTemplatePath = hrTemplatePath;
    }
}
