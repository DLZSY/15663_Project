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


public class SketchFilter extends Filter {

    public SketchFilter(){

    }

    @Override
    public Bitmap filterImage(Bitmap inputImage){

        // Bitmap to ARGB cv_mat
        Mat cvMat = new Mat(inputImage.getWidth(), inputImage.getHeight(), CvType.CV_8UC4);
        Utils.bitmapToMat(inputImage, cvMat);
        // Convert Image to grayscale
        Mat cvMatGray = new Mat(inputImage.getWidth(), inputImage.getHeight(), CvType.CV_8UC1);
        Imgproc.cvtColor(cvMat, cvMatGray, Imgproc.COLOR_BGRA2GRAY);
        cvMatGray.convertTo(cvMatGray, CvType.CV_64FC1);
        Mat cvMatGrayInvert = cvMatGray.clone();
        // Then apply a gaussian blur
        Mat cvMatGrayBlurred = new Mat(inputImage.getWidth(),
                                       inputImage.getHeight(),
                                       CvType.CV_64FC1);
        Size kernelSize = new Size(25, 25);
        Imgproc.GaussianBlur(
                cvMatGrayInvert,
                cvMatGrayBlurred,
                kernelSize,
                0,
                0,
                Core.BORDER_CONSTANT
        );
        // Finally Blend the grayscale image with the blurred negative
        Mat cvMatGrayBlend = cvMatGray.clone();
        Core.divide(cvMatGrayBlend, cvMatGrayBlurred, cvMatGrayBlend, 256);
        cvMatGrayBlend.convertTo(cvMatGrayBlend, CvType.CV_8UC3);
        Bitmap filteredImage = Bitmap.createBitmap(
                cvMat.cols(),
                cvMat.rows(),
                Bitmap.Config.ARGB_8888
        );
        Utils.matToBitmap(cvMatGrayBlend, filteredImage);
        return filteredImage;
    }

    @Override
    public Bitmap filterImage(Bitmap inputImage_first, Bitmap inputImage_second) {
        return null;
    }
}
