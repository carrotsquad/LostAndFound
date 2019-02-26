package com.zhangqianyuan.teamwork.lostandfound.bean;

import java.util.List;

/**
 * Description
 *
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Author$
 * @updateDes
 */
public class CommentFeedBack {
    private  int status;
    private List<Comments> comments;

   public  static class Comments{
        private String username;
        private String nickname;
        private String  photo;
        private int    lostid;
        private Comment comment;

       public Comment getComment() {
           return comment;
       }

       public void setComment(Comment comment) {
           this.comment = comment;
       }

       public  static class Comment{
            private int  id;
            private String time;
            private String content;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }

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

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getLostid() {
            return lostid;
        }

        public void setLostid(int lostid) {
            this.lostid = lostid;
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> dynamics) {
        this.comments = dynamics;
    }
}
