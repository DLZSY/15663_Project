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

public abstract class Filter {

    public abstract Bitmap filterImage(Bitmap inputImage);
    public abstract Bitmap filterImage(Bitmap inputImage_first, Bitmap inputImage_second);

}
