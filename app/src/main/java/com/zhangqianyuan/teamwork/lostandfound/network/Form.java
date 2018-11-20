package com.zhangqianyuan.teamwork.lostandfound.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.zhangqianyuan.teamwork.lostandfound.bean.FormFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @author MrWangx
 * @description 表单模拟上传工具
 * @date 2018 11 20 16:41
 * */
public class Form {

//    public static void main(String[] args) throws IOException {
//        Map<String, String> params = new HashMap<>();
//        params.put("JSESSIONID", "AF51101D26C28DA6DB8311DB3F602DCE");
//        params.put("thelost", "{\"id\":null,\"typeid\":2,\"losttype\":0,\"title\":\"我的钱包丢了\",\"description\":\"钱包丢了帮我找一下\",\"placeid\":6,\"publishtime\":null,\"losttime\":\"2018112012\",\"photo\":null,\"ishandled\":0}");
//
//        FormFile formFile1 = new FormFile("photos", "img/jpg", new File("C:\\Users\\MrWangx\\Pictures\\u=2271124936,2100494281&fm=26&gp=0.jpg"));
//        FormFile formFile2 = new FormFile("photos", "img/jpg", new File("C:\\Users\\MrWangx\\Pictures\\u=2902753509,3996420548&fm=26&gp=0.jpg"));
//        List<FormFile> files = new ArrayList<>();
//        files.add(formFile1);
//        files.add(formFile2);
//        System.out.println(submit("http://localhost:8080/passlove/user/publishlost", params, files));
//
//
//    }

    private static String BOUNDARY = "AABBCCDD"; //分割的字符,可更改
    private static final String BOUNDARY_SIGN = "--"; //分割的符号 不可更改
    private static final String END = "\r\n"; //换行符 不可更改

    /**
     * @param Url
     * @param params 请求的文字参数
     * @param files 需要上传的文件
     * @return 返回的信息
     * */
    public static void submit(String Url, Map<String, String> params, List<FormFile> files) throws IOException {

        new Thread(){
            @Override
            public void run() {
                try {
                    URL url = new URL(Url);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    //设置允许输入输出流
                    connection.setDoOutput(true);
                    connection.setDoOutput(true);


                    //设置参数
                    connection.setUseCaches(false);

                    //设置请求头
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Connection", "Keep-Alive");
                    connection.setRequestProperty("Charset", "UTF-8");
                    connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

                    DataOutputStream dout = new DataOutputStream(connection.getOutputStream());

                    //表单字符串信息
                    for (Map.Entry<String, String> e : params.entrySet()) {
                        dout.writeBytes(BOUNDARY_SIGN + BOUNDARY + END); //分割
                        dout.write(("Content-Disposition:form-data;name=\"" + e.getKey() + "\"" + END).getBytes("utf-8"));
                        dout.writeBytes(END);
                        dout.write((e.getValue() + END).getBytes("utf-8"));
                    }

                    //文件信息
                    for (FormFile f : files) {
                        String filename  = f.getFile().getName();
                        dout.writeBytes(BOUNDARY_SIGN + BOUNDARY + END); //分割
                        dout.write(("Content-Disposition:form-data;name=\"" + f.getName() + "\";filename=\"" +
                                f.getFile().getName() + "\"" + END).getBytes("utf-8"));
                        dout.write(("Content-Type:" + f.getType() + END).getBytes("utf-8"));
                        dout.writeBytes(END);
                        //读取文件流
                        File file = f.getFile();
                        FileInputStream fin = new FileInputStream(file);
                        byte[] data = new byte[1024];
                        int length = -1;
                        while ((length = fin.read(data)) != -1) { //传输文件
                            dout.write(data, 0, length);
                        }
                        dout.writeBytes(END);
                    }
                    dout.writeBytes(BOUNDARY_SIGN + BOUNDARY + BOUNDARY_SIGN + END); //结束传输

                    dout.flush();
                    dout.close();

                    //获取反馈信息
                    BufferedReader br = new BufferedReader( new InputStreamReader(connection.getInputStream(), "utf-8"));
                    StringBuffer buffer = new StringBuffer();
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        buffer.append(line);
                    }

                    br.close();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
