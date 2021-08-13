package com.eduhansol.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orientations")
public class Orientation {
    @Id
    private int id;

    @Column(nullable = false)
    private int normPersonalityId;

    @Column(nullable = false)
    private int orderNo;

    @Column(nullable = false)
    private int pageNo;

    @Column(nullable = false)
    private String imagePath;

    @Column(nullable = false)
    private boolean isLast;

    public boolean getIsLast() {
        return this.isLast;
    }

    public void setIsLast(boolean isLast) {
        this.isLast = isLast;
    }

    public int getNormPersonalityId() {
        return normPersonalityId;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public void setNormPersonalityId(int normPersonalityId) {
        this.normPersonalityId = normPersonalityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
