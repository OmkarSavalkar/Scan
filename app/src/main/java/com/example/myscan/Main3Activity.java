package com.example.myscan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class Main3Activity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    ZXingScannerView scan;
    String coderes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scan=new ZXingScannerView(this);
        setContentView(scan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Toast.makeText(this, "Allow Camera Permission if Camera not Visible", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void handleResult(Result result) {

        coderes=result.getText();
        AlertDialog.Builder alertdialoguebuilder=new AlertDialog.Builder(this);
        alertdialoguebuilder.setTitle("Scanned Code is:");
        alertdialoguebuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Main3Activity.this, "Ready for another Scan", Toast.LENGTH_SHORT).show();
                scan.resumeCameraPreview(Main3Activity.this);
            }
        });
        alertdialoguebuilder.setPositiveButton("Search Result On GOOGLE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(coderes));
//                startActivity(intent);
                Intent intent=new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, coderes);
                startActivity(intent);
            }
        });
        alertdialoguebuilder.setMessage(coderes);
        AlertDialog alert=alertdialoguebuilder.create();
        alert.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scan.setResultHandler(this);
        scan.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scan.stopCamera();
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
                scan.resumeCameraPreview(Main3Activity.this);
            }
        });
        AlertDialog alertDialog=alertdialogbuilder.create();
        alertDialog.show();

    }
}
