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
@Table(name="departments")
public class Department{

    public Department() {
        super();
    }

    public Department(String name, int regionId) {
        this.name = name;
        Region region = new Region();
        region.setId(regionId);
        this.setRegion(region);
        this.isDelete = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // @ManyToOne(targetEntity = Region.class, fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn
    private Region region;

    @Column(nullable = false)
    @NotBlank(message = "부서 이름을 입력하세요.")
    private String name;

    @Column(nullable = false)
    private boolean isDelete;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Region getRegion() {
        return this.region;
    }

    public void setRegion(Region region) {
        this.region = region;
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