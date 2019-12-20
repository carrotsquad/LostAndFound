package com.yf107.teamwork.lostandfound.bean;

import java.io.File;

public class FormFile {

    private String name; //表单文件的请求参数
    private String type; //类型 比如img/jpg
    private File file; //需要提交的文件

    public FormFile(String name, String type, File file) {
        this.name = name;
        this.type = type;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
