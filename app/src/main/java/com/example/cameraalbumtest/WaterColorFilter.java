package com.example.cameraalbumtest;

import android.graphics.Bitmap;

// OpenCV related libraries
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.BaseLoaderCallback;
import org.opencv.core.Core;
import org.opencv.core.Size;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.android.Utils;
import org.opencv.photo.Photo;

public class WaterColorFilter {

    public WaterColorFilter(){

    }

    public Bitmap filterImage(Bitmap inputImage){
        // Bitmap to ARGB cv_mat
        Mat cvMat = new Mat(inputImage.getWidth(), inputImage.getHeight(), CvType.CV_8UC4);
        Utils.bitmapToMat(inputImage, cvMat);
        Imgproc.cvtColor(cvMat, cvMat, Imgproc.COLOR_BGRA2BGR);
        // Apply a watercolor filter
        Mat cvMatWaterColor = cvMat.clone();
        Photo.stylization(cvMat, cvMatWaterColor);
        Bitmap filteredImage = Bitmap.createBitmap(
                cvMat.cols(),
                cvMat.rows(),
                Bitmap.Config.ARGB_8888
        );
        Utils.matToBitmap(cvMatWaterColor, filteredImage);
        return filteredImage;
    }
}
