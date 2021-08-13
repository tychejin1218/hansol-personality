package com.eduhansol.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="marks")
public class Mark {
    @Id
    private int testerId;

    @Column(nullable = false)
    private String mark1;
    
    @Column(nullable = false)
    private String mark2;

    @Column(nullable = false)
    private String convertMark2;

    public String getConvertMark2() {
        return this.convertMark2;
    }

    public void setConvertMark2(String convertMark2) {
        this.convertMark2 = convertMark2;
    }

    public int getTesterId() {
        return testerId;
    }

    public void setTesterId(int testerId) {
        this.testerId = testerId;
    }

    public String getMark1() {
        return mark1;
    }

    public void setMark1(String mark1) {
        this.mark1 = mark1;
    }

    public String getMark2() {
        return mark2;
    }

    public void setMark2(String mark2) {
        this.mark2 = mark2;
    }
}
