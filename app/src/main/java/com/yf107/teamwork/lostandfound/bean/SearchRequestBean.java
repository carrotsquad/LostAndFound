package com.yf107.teamwork.lostandfound.bean;

public class SearchRequestBean {
    /**
     * keyword : 你的
     * losttype : 0
     * typeid : 1
     * placeid : 1
     * start : 0
     * end : 4
     */

    private String keyword;
    private int losttype;
    private int typeid;
    private int placeid;
    private int start;
    private int end;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getLosttype() {
        return losttype;
    }

    public void setLosttype(int losttype) {
        this.losttype = losttype;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public int getPlaceid() {
        return placeid;
    }

    public void setPlaceid(int placeid) {
        this.placeid = placeid;
    }

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
