package com.yf107.teamwork.lostandfound.view.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.RotatedRect;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gloomy fish on 2017/12/07.
 */

public class CardNumberROIFinder {

    public static Bitmap extractNumberROI(Bitmap input, Bitmap template) {
        Mat src = new Mat();
        Mat tpl = new Mat();
        Mat dst = new Mat();
        Mat fixSrc = new Mat();
        int width = input.getWidth();
        int height = input.getHeight();
        double ratio = (double) width / height;
        Log.e("Tess", "降采样使用完成");
        Log.e("Tess", "w = " + width + "h = " + height);
        Utils.bitmapToMat(input, src);
        Utils.bitmapToMat(template, tpl);
        Imgproc.cvtColor(src, dst, Imgproc.COLOR_BGRA2GRAY);//通过cvtColor函数转换为通道图像

        Mat k = Imgproc.getStructuringElement(
                Imgproc.MORPH_RECT, new Size(15, 15), new Point(-1, -1));
        Imgproc.morphologyEx(dst, dst, Imgproc.MORPH_OPEN, k);
        //Imgproc.resize(dst, dst, new Size(349, 620));
        //Canny边缘检测
        // threshold1：表示低阈值T1。
        // threshold2：表示高阈值T2。
        // apertureSize：用于内部计算梯度Sobel。
        // L2gradient：计算图像梯度的计算方法。
        Imgproc.Canny(dst, dst, 180, 360, 5, true);
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Mat hierachy = new Mat();
        //轮廓发现
        //image：输入图像，必须是8位的单通道的图像，轮廓发现的时候该图像会被修改。
        // contours：是List类型，List里面的每个元素都是一个轮廓对应的所有像素点集合。
        // hierarchy：拓扑信息，可以不填写这个参数。
        // mode：返回的轮廓拓扑模式，一共有四种。
        // method：描述轮廓的方法，一般是基于链式编码。
        // offset：是否有位移，默认都是各个像素点没有相对原因的位置移动，所以位移默认是（0,0）。
        Imgproc.findContours(dst, contours, hierachy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE, new Point(0, 0));
        Imgproc.cvtColor(dst, dst, Imgproc.COLOR_GRAY2BGR);

        Rect roiArea = null;
        //获取学生卡的便框
        for (int i = 0; i < contours.size(); i++) {
            List<Point> points = contours.get(i).toList();
            Rect rect = Imgproc.boundingRect(contours.get(i));
            if (rect.width < width && rect.width > (width / 2)) {
                if (rect.height <= (height / 4))
                    continue;
                roiArea = rect;

            }
        }
        Log.e("Tess", "roiArea = " + roiArea);

        //可能出现学生卡所占体积过大无法发现框框的情况
        if (roiArea == null) {
            Rect aaa = new Rect(0, 0, width, height);
            Mat result = src.submat(aaa);
            Log.e("Tess", "result1 = " + result);

            // fix size, in order to match template
            Size fixSize = new Size(500, 500 * ratio);
            //547，342
            //1100,600
            Imgproc.resize(result, fixSrc, fixSize);
            result = fixSrc;
            Log.e("Tess", "result.width = " + result.width() + "result.height = " + result.height());
            Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types

            // find id number ROI
            Rect idNumberROI = new Rect(0, 0, result.width(), result.height());
            //Rect idNumberROI = new Rect((int)(maxLoc.x+tpl.cols()), (int)maxLoc.y, (int)(result.cols() - (maxLoc.x+tpl.cols())), tpl.rows());
            Mat idNumberArea = result.submat(idNumberROI);

            // 返回对象
            Bitmap bap = Bitmap.createBitmap(idNumberArea.cols(), idNumberArea.rows(), conf);
            Utils.matToBitmap(idNumberArea, bap);

            // 释放内存
            idNumberArea.release();
            result.release();
            hierachy.release();
            fixSrc.release();
            src.release();
            dst.release();
            tpl.release();
            k.release();
            return bap;
        } else {
            Mat result = src.submat(roiArea);
            // fix size, in order to match template
            Size fixSize = new Size(1150, 600);
            Imgproc.resize(result, fixSrc, fixSize);
            result = fixSrc;

            // detect location
            int result_cols = result.cols() - tpl.cols() + 1;
            int result_rows = result.rows() - tpl.rows() + 1;
            Mat mr = new Mat(result_rows, result_cols, CvType.CV_32FC1);

            // 开始匹配
            Imgproc.matchTemplate(result, tpl, mr, Imgproc.TM_CCORR_NORMED);
            Core.normalize(mr, mr, 0, 1, Core.NORM_MINMAX, -1);
            Core.MinMaxLocResult minMaxLocResult = Core.minMaxLoc(mr);
            Point maxLoc = minMaxLocResult.maxLoc;
            Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
            // find id number ROI
            Rect idNumberROI = new Rect((int) (maxLoc.x + tpl.cols()) - 90, (int) maxLoc.y, (int) (result.cols() - (maxLoc.x + tpl.cols()) - 100), tpl.rows() - 10);//稍微靠左
            //没有匹配出来
            if (idNumberROI.width < 100) {
                mr.release();
                result.release();
                hierachy.release();
                fixSrc.release();
                src.release();
                dst.release();
                tpl.release();
                k.release();
                return null;
            }
            Mat idNumberArea = result.submat(idNumberROI);
            // 返回对象
            Bitmap bmp = Bitmap.createBitmap(idNumberArea.cols(), idNumberArea.rows(), conf);
            Utils.matToBitmap(idNumberArea, bmp);

            // 释放内存
            idNumberArea.release();
            mr.release();
            result.release();
            hierachy.release();
            fixSrc.release();
            src.release();
            dst.release();
            tpl.release();
            k.release();
            return bmp;
        }
    }
}