package com.yf107.teamwork.lostandfound.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;


public class BitmapUtil {
        /**
         * 图片压缩-质量压缩
         *
         * @param filePath 源图片路径
         * @return 压缩后的路径
         */

        public static String compressImage (String filePath){

            //原文件
            File oldFile = new File(filePath);


            //压缩文件路径 照片路径/
            String targetPath = oldFile.getPath();
            int quality = 50;//压缩比例0-100
            Bitmap bm = getSmallBitmap(filePath);//获取一定尺寸的图片

            File outputFile = new File(targetPath);
            try {
                if (!outputFile.exists()) {
                    outputFile.getParentFile().mkdirs();
                    //outputFile.createNewFile();
                } else {
//                    outputFile.delete();
                }
                FileOutputStream out1 = new FileOutputStream(outputFile);

                bm.compress(Bitmap.CompressFormat.JPEG, quality, out1);
                out1.close();
            } catch (Exception e) {
                e.printStackTrace();
                return filePath;
            }
            return outputFile.getPath();
        }

        /**
         * 根据路径获得图片信息并按比例压缩，返回bitmap
         * @return
         */
        public static Bitmap getSmallBitmap (String filePath){
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;//只解析图片边沿，获取宽高
            BitmapFactory.decodeFile(filePath, options);
            // 计算缩放比
            options.inSampleSize = calculateInSampleSize(options, 480, 800);
            // 完整解析图片返回bitmap
            options.inJustDecodeBounds = false;

            return BitmapFactory.decodeFile(filePath,options);
        }


        public static int calculateInSampleSize (BitmapFactory.Options options,
        int reqWidth, int reqHeight){
            final int height = options.outHeight;
            final int width = options.outWidth;
            int inSampleSize = 1;
            if (height > reqHeight || width > reqWidth) {
                final int heightRatio = Math.round((float) height / (float) reqHeight);
                final int widthRatio = Math.round((float) width / (float) reqWidth);
                inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
            }
            return inSampleSize;
        }
}