package com.example.naneen.japfoodfinder;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

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
        capturedImage.setImageBitmap(BitmapFactory.decodeFile(filePath));
    }
}
