package com.zhangqianyuan.teamwork.lostandfound.bean;

import java.util.List;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class MyHistoryItem {

    /**
     * status : 200
     * data : [{"id":8,"typeid":4,"losttype":0,"title":"失物发布测试6","description":"的JFK但是JFK速度加快分解速度快","placeid":10,"publishtime":"20181110221944","losttime":"2018111013","photo":"8.jpg","ishandled":1}]
     */

    private int status;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 8
         * typeid : 4
         * losttype : 0
         * title : 失物发布测试6
         * description : 的JFK但是JFK速度加快分解速度快
         * placeid : 10
         * publishtime : 20181110221944
         * losttime : 2018111013
         * photo : 8.jpg
         * ishandled : 1
         */

        private int id;
        private int typeid;
        private int losttype;
        private String title;
        private String description;
        private int placeid;
        private String publishtime;
        private String losttime;
        private String photo;
        private int ishandled;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
        }

        public int getLosttype() {
            return losttype;
        }

        public void setLosttype(int losttype) {
            this.losttype = losttype;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getPlaceid() {
            return placeid;
        }

        public void setPlaceid(int placeid) {
            this.placeid = placeid;
        }

        public String getPublishtime() {
            return publishtime;
        }

        public void setPublishtime(String publishtime) {
            this.publishtime = publishtime;
        }

        public String getLosttime() {
            return losttime;
        }

        public void setLosttime(String losttime) {
            this.losttime = losttime;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getIshandled() {
            return ishandled;
        }

        public void setIshandled(int ishandled) {
            this.ishandled = ishandled;
        }
    }
}
