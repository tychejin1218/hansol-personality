package com.eduhansol.app.viewmodels;

import com.eduhansol.app.entities.Orientation;

public class OrientationViewModel{
       private int normPersonalityId;
       private Orientation orientation;
    //    private int count;
       private int page;

    public int getNormPersonalityId() {
        return this.normPersonalityId;
    }

    public void setNormPersonalityId(int normPersonalityId) {
        this.normPersonalityId = normPersonalityId;
    }

    public Orientation getOrientation() {
        return this.orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    // public int getCount() {
    //     return this.count;
    // }

    // public void setCount(int count) {
    //     this.count = count;
    // }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}