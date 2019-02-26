package com.zhangqianyuan.teamwork.lostandfound.bean;


/**
 * Description
 * 发送评论的bean
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Author$
 * @updateDes
 */
public class ThingDetailBean {

 private  int lostid;
 private Comment comment;

    public int getLostid() {
        return lostid;
    }

    public void setLostid(int lostid) {
        this.lostid = lostid;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment conment) {
        this.comment = conment;
    }

    public  static class Comment {
     private Integer id;
     private String time;
     private String content;

     public Integer getId() {
         return id;
     }

     public void setId(Integer id) {
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

}
