package com.example.cameraalbumtest;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.MatOfPoint;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Scalar;
import org.opencv.photo.Photo;

public class BlendFilter extends Filter {

    public BlendFilter(){

    }

    @Override
    public Bitmap filterImage(Bitmap inputImage) {
        return null;
    }

    @Override
    public Bitmap filterImage(Bitmap src, Bitmap dst){
        // src image will be cloned into dst
        Mat srcMat = new Mat(src.getWidth(), src.getHeight(), CvType.CV_8UC4);
        Utils.bitmapToMat(src, srcMat);
        Mat dstMat = new Mat(dst.getWidth(), dst.getHeight(), CvType.CV_8UC4);
        Utils.bitmapToMat(dst, dstMat);
        Imgproc.cvtColor(srcMat, srcMat, Imgproc.COLOR_BGRA2BGR);
        Imgproc.cvtColor(dstMat, dstMat, Imgproc.COLOR_BGRA2BGR);
        // clone all src images to dst image
        Mat src_mask = new Mat(srcMat.size(), srcMat.depth(), new Scalar(255));
        Mat cvMatBlend=new Mat();

        // center of the cloned point
        Point center=new Point(dstMat.cols()/2,dstMat.rows()/2);

        // Normal clone or mixed clone
        Photo.seamlessClone(srcMat, dstMat, src_mask, center, cvMatBlend, Photo.NORMAL_CLONE);
        //Photo.seamlessClone(srcMat, dstMat, src_mask, center, cvMatBlend,  Photo.MIXED_CLONE);
        Bitmap filteredImage = Bitmap.createBitmap(
                dstMat.cols(),
                dstMat.rows(),
                Bitmap.Config.ARGB_8888
        );
        Utils.matToBitmap(cvMatBlend, filteredImage);
        return filteredImage;
    }
}