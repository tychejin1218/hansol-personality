package com.eduhansol.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tempMark2s")
public class TempMark2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false)
    private int testerId;

    @Column(nullable = false)
    private int normPersonalityId;

    @Column(nullable = false)
    private int questionSetId;

    @Column(nullable = false)
    private int questionId;

    @Column(nullable = false)
    private int pageNo;

    @Column(nullable = false)
    private String mark2;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTesterId() {
        return this.testerId;
    }

    public void setTesterId(int testerId) {
        this.testerId = testerId;
    }

    public int getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getNormPersonalityId() {
        return this.normPersonalityId;
    }

    public void setNormPersonalityId(int normPersonalityId) {
        this.normPersonalityId = normPersonalityId;
    }

    public int getQuestionSetId() {
        return this.questionSetId;
    }

    public void setQuestionSetId(int questionSetId) {
        this.questionSetId = questionSetId;
    }

    public int getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getMark2() {
        return this.mark2;
    }

    public void setMark2(String mark2) {
        this.mark2 = mark2;
    }
    
}
