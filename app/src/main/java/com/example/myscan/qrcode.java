package com.example.myscan;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class qrcode extends AppCompatActivity {

    String TAG="GenerateQRCode";
    EditText et;
    ImageView iv;
    Button start;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    String inputvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        iv=findViewById(R.id.qrcode);
        et=findViewById(R.id.text);
        start=findViewById(R.id.but);

    }

    public void button(View view)
    {
        inputvalue=et.getText().toString().trim();
        if (inputvalue.length()>0)
        {
            WindowManager manager=(WindowManager)getSystemService(WINDOW_SERVICE);
            Display d=manager.getDefaultDisplay();
            Point point=new Point();
            d.getSize(point);
            int width=point.x;
            int height=point.y;
            int smallerdimension=width<height ? width:height;
            smallerdimension=smallerdimension*3/4;
            qrgEncoder=new QRGEncoder(inputvalue,null, QRGContents.Type.TEXT,smallerdimension);
            try {
                bitmap=qrgEncoder.getBitmap();
                iv.setImageBitmap(bitmap);
            }
            catch (Exception e)
            {
                Log.v(TAG,e.toString());
            }
        }
        else {
            et.setError("Required");
        }
    }
}
