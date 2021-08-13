package com.eduhansol.app.viewmodels;

import java.util.List;

import com.eduhansol.app.entities.Question;
import com.eduhansol.app.entities.TempMark1;
import com.eduhansol.app.entities.TempMark2;

public class QuestionViewModel {
    private int normPersonalityId;

    private int testerId;

    private int prevPageNo;

    private int pageNo;

    private int nextPageNo;

    private int time;

    private boolean isLast;

    private List<Question> questions;

    private List<TempMark1> tempMark1s;

    private List<TempMark2> tempMark2s;

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getNormPersonalityId() {
        return this.normPersonalityId;
    }

    public void setNormPersonalityId(int normPersonalityId) {
        this.normPersonalityId = normPersonalityId;
    }

    public int getTesterId() {
        return this.testerId;
    }

    public void setTesterId(int testerId) {
        this.testerId = testerId;
    }

    public int getPrevPageNo() {
        return this.prevPageNo;
    }

    public void setPrevPageNo(int prevPageNo) {
        this.prevPageNo = prevPageNo;
    }

    public int getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getNextPageNo() {
        return this.nextPageNo;
    }

    public void setNextPageNo(int nextPageNo) {
        this.nextPageNo = nextPageNo;
    }

    public boolean getIsLast() {
        return this.isLast;
    }

    public void setIsLast(boolean isLast) {
        this.isLast = isLast;
    }

    public List<Question> getQuestions() {
        return this.questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<TempMark1> getTempMark1s() {
        return this.tempMark1s;
    }

    public void setTempMark1s(List<TempMark1> tempMark1s) {
        this.tempMark1s = tempMark1s;
    }

    public List<TempMark2> getTempMark2s() {
        return this.tempMark2s;
    }

    public void setTempMark2s(List<TempMark2> tempMark2s) {
        this.tempMark2s = tempMark2s;
    }
}