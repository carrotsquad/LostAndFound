package com.yf107.teamwork.lostandfound.bean;

public class DynamicsRequestBean {


    public DynamicsRequestBean(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /**
     * start : 0
     * end : 15
     */

    private int start;
    private int end;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
