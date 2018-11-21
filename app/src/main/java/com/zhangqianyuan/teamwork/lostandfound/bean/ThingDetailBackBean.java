package com.zhangqianyuan.teamwork.lostandfound.bean;

/**
 * Description
 * 接受评论发送bean
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class ThingDetailBackBean {

    /**
     * lostid : 1
     * start : 0
     * end : 15
     */

    private int lostid;
    private int start;
    private int end;

    public int getLostid() {
        return lostid;
    }

    public void setLostid(int lostid) {
        this.lostid = lostid;
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
