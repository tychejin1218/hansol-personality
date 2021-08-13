package com.eduhansol.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="regions")
public class Region{

    public Region() {
        super();
    }

    public Region(String name, int hqId) {
        this.name = name;
        Hq hq =  new Hq();
        hq.setId(hqId);
        this.setHq(hq);
        this.isDelete = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // @ManyToOne(targetEntity = Hq.class, fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn
    private Hq hq;

    @Column(nullable = false)
    @NotBlank(message = "지역국 이름을 입력하세요.")
    private String name;

    @Column(nullable = false)
    private boolean isDelete;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Hq getHq() {
        return this.hq;
    }

    public void setHq(Hq hq) {
        this.hq = hq;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public boolean getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }
}