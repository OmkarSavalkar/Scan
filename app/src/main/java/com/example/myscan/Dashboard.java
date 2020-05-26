package com.example.myscan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.scanlibrary.ScanActivity;
import com.scanlibrary.ScanConstants;

import java.io.IOException;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {

    ImageView scannedImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

    }

    @Override
    public void onClick(View view) {
        Intent i=new Intent(Dashboard.this,Main3Activity.class);
        startActivity(i);
    }

    public void scanstore(View view){
        Intent i=new Intent(Dashboard.this,scanstore.class);
        startActivity(i);
    }

    public void Docscancamera(View view){
        int REQUEST_CODE = 99;
        int preference = ScanConstants.OPEN_CAMERA;
        Intent intent = new Intent(this, ScanActivity.class);
        intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE, preference);
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void Galleryopen(View view){
        int REQUEST_CODE = 99;
        int preference = ScanConstants.OPEN_MEDIA;
        Intent intent = new Intent(this, ScanActivity.class);
        intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE, preference);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99 && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getExtras().getParcelable(ScanConstants.SCANNED_RESULT);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                getContentResolver().delete(uri, null, null);
                scannedImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void help(View view){
        Intent i=new Intent(Dashboard.this,help.class);
        startActivity(i);
    }

    public void qrclick(View view){
        Intent i=new Intent(Dashboard.this,qrcode.class);
        startActivity(i);
    }

    public void onBackPressed()
    {
        AlertDialog.Builder alertdialogbuilder=new AlertDialog.Builder(this);
        alertdialogbuilder.setTitle("Confirm Exit...");
        alertdialogbuilder.setIcon(R.drawable.ic_exit);
        alertdialogbuilder.setMessage("Do You really Want To Exit???");
        alertdialogbuilder.setCancelable(false);
        alertdialogbuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alertdialogbuilder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Toast.makeText(Main2Activity.this, "You clicked cancel!", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog=alertdialogbuilder.create();
        alertDialog.show();
    }
}
