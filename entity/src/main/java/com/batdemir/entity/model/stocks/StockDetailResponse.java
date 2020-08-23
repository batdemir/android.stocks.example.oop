package com.batdemir.entity.model.stocks;

import com.batdemir.entity.model.core.GraphicData;
import com.batdemir.entity.model.core.Status;

import java.util.List;

public class StockDetailResponse extends Stock {
    private float channge;
    private int count;
    private float highest;
    private float lowest;
    private float maximum;
    private float minimum;
    private float value;
    private List<GraphicData> graphicData;
    private Status status;

    public float getChannge() {
        return channge;
    }

    public void setChannge(float channge) {
        this.channge = channge;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getHighest() {
        return highest;
    }

    public void setHighest(float highest) {
        this.highest = highest;
    }

    public float getLowest() {
        return lowest;
    }

    public void setLowest(float lowest) {
        this.lowest = lowest;
    }

    public float getMaximum() {
        return maximum;
    }

    public void setMaximum(float maximum) {
        this.maximum = maximum;
    }

    public float getMinimum() {
        return minimum;
    }

    public void setMinimum(float minimum) {
        this.minimum = minimum;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public List<GraphicData> getGraphicData() {
        return graphicData;
    }

    public void setGraphicData(List<GraphicData> graphicData) {
        this.graphicData = graphicData;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
