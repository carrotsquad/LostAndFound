package com.zhangqianyuan.teamwork.lostandfound.bean;

import java.util.List;

public class UpDateMessageBean {

    /**
     * status : 200
     * dynamics : [{"dynamics":{"username":"695488704@qq.com","nickname":"田晓煊","userphoto":"default.jpg","thelost":{"id":342,"typeid":1,"losttype":1,"title":"好过分","description":"广发基金","placeid":1,"publishtime":"20191212160129","losttime":"20191212","photo":"","ishandled":0}},"read":0}]
     */

    private int status;
    private List<DynamicsBeanX> dynamics;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DynamicsBeanX> getDynamics() {
        return dynamics;
    }

    public void setDynamics(List<DynamicsBeanX> dynamics) {
        this.dynamics = dynamics;
    }

    public static class DynamicsBeanX {
        /**
         * dynamics : {"username":"695488704@qq.com","nickname":"田晓煊","userphoto":"default.jpg","thelost":{"id":342,"typeid":1,"losttype":1,"title":"好过分","description":"广发基金","placeid":1,"publishtime":"20191212160129","losttime":"20191212","photo":"","ishandled":0}}
         * read : 0
         */

        private DynamicsBean dynamics;
        private int read;

        public DynamicsBean getDynamics() {
            return dynamics;
        }

        public void setDynamics(DynamicsBean dynamics) {
            this.dynamics = dynamics;
        }

        public int getRead() {
            return read;
        }

        public void setRead(int read) {
            this.read = read;
        }

        public static class DynamicsBean {
            /**
             * username : 695488704@qq.com
             * nickname : 田晓煊
             * userphoto : default.jpg
             * thelost : {"id":342,"typeid":1,"losttype":1,"title":"好过分","description":"广发基金","placeid":1,"publishtime":"20191212160129","losttime":"20191212","photo":"","ishandled":0}
             */

            private String username;
            private String nickname;
            private String userphoto;
            private ThelostBean thelost;

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getUserphoto() {
                return userphoto;
            }

            public void setUserphoto(String userphoto) {
                this.userphoto = userphoto;
            }

            public ThelostBean getThelost() {
                return thelost;
            }

            public void setThelost(ThelostBean thelost) {
                this.thelost = thelost;
            }

            public static class ThelostBean {
                /**
                 * id : 342
                 * typeid : 1
                 * losttype : 1
                 * title : 好过分
                 * description : 广发基金
                 * placeid : 1
                 * publishtime : 20191212160129
                 * losttime : 20191212
                 * photo :
                 * ishandled : 0
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
    }
}
