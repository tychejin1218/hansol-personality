package com.eduhansol.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="questions")
public class Question {
    @Id
    private int id;

    @ManyToOne
    @JoinColumn
    private NormPersonality normPersonality;
    
    @Column(nullable = false)
    private int questionId;

    @Column(nullable = false)
    private int questionSetId;

    @Column(nullable = false)
    private String questionChar;

    @Column(nullable = false)
    private int pageNo;

    @ManyToOne
    @JoinColumn
    private SubFactor SubFactor;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private boolean isReverse;

    @Column(nullable = false)
    private boolean isLast;

    @Column(nullable = false)
    private boolean isDummy;

    public boolean getIsDummy() {
        return this.isDummy;
    }

    public void setIsDummy(boolean isDummy) {
        this.isDummy = isDummy;
    }

    public boolean getIsLast() {
        return this.isLast;
    }

    public void setIsLast(boolean isLast) {
        this.isLast = isLast;
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

    public SubFactor getSubFactor() {
        return this.SubFactor;
    }

    public void setSubFactor(SubFactor SubFactor) {
        this.SubFactor = SubFactor;
    }

    public int getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getQuestionSetId() {
        return this.questionSetId;
    }

    public void setQuestionSetId(int questionSetId) {
        this.questionSetId = questionSetId;
    }

    public String getQuestionChar() {
        return this.questionChar;
    }

    public void setQuestionChar(String questionChar) {
        this.questionChar = questionChar;
    }

    public int getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }



    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isIsReverse() {
        return this.isReverse;
    }

    public boolean getIsReverse() {
        return this.isReverse;
    }

    public void setIsReverse(boolean isReverse) {
        this.isReverse = isReverse;
    }
    
}
