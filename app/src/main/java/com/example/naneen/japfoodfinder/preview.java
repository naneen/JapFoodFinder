package com.example.naneen.japfoodfinder;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;

public class Preview extends Activity{
    private ImageView capturedImage;
    private String filePath;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_preview);
        capturedImage= (ImageView) findViewById(R.id.capturedImage);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                filePath= null;
            } else {
                filePath= extras.getString("filePath");
            }
        } else {
            filePath= (String) savedInstanceState.getSerializable("filePath");
        }
        showImage();
    }

    public void showImage(){
//        capturedImage.setImageBitmap(BitmapFactory.decodeFile(filePath));
        rotateImage(BitmapFactory.decodeFile(filePath));
    }

    public void rotateImage(Bitmap bitmap){
        ExifInterface exifInterface = null;
        try {
            exifInterface = new ExifInterface(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
        Matrix matrix = new Matrix();
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.setRotate(90);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.setRotate(180);
                break;
            default:
        }
        Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        capturedImage.setImageBitmap(rotatedBitmap);
    }
}
